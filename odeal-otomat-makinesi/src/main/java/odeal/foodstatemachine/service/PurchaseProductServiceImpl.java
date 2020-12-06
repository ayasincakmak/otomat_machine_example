package odeal.foodstatemachine.service;

import java.io.IOException;

import org.springframework.stereotype.Component;
import odeal.foodstatemachine.Database;
import odeal.foodstatemachine.machine.ItemsInventory;
import odeal.foodstatemachine.machine.Machine;
import odeal.foodstatemachine.machine.NoCash;
import odeal.foodstatemachine.model.ProductRequestFood;
import odeal.foodstatemachine.model.ProductRequestHotDrink;
import odeal.foodstatemachine.payment.Payment;
import odeal.foodstatemachine.payment.PaymentImplementor;

@Component
public class PurchaseProductServiceImpl implements PurchaseProductService {

	@Override
	public String purchaseServiceHotDrink(ProductRequestHotDrink productRequest) throws PurchaseServiceException {
		Machine machine = getMachineInfo();
		if (machine != null) {
			try {
				return purchaseOperation(machine, productRequest.getProductName(), productRequest.getAmountInserted(),
						productRequest.getSugarLevel(), productRequest.getPaymentType(), productRequest.getQuantity());
			} catch (Exception e) {
				throw new PurchaseServiceException(e.getMessage(), e.getCause());
			}
		} else {
			return ServiceMessages.ERROR_MACHINE_OUT_OF_ORDER;
		}
	}

	@Override
	public String purchaseServiceFood(ProductRequestFood productRequestDTO) throws PurchaseServiceException {
		Machine machine = getMachineInfo();
		if (machine != null) {
			try {
				return purchaseOperation(machine, productRequestDTO.getProductName(),
						productRequestDTO.getAmountInserted(), null, productRequestDTO.getPaymentType(),
						productRequestDTO.getQuantity());
			} catch (Exception e) {
				throw new PurchaseServiceException(e.getMessage(), e.getCause());
			}
		} else {
			return ServiceMessages.ERROR_MACHINE_OUT_OF_ORDER;
		}

	}

	private String purchaseOperation(Machine machine, String productName, double amountInserted, Integer sugarLevel,
			String paymentType, int quantity) throws ClassNotFoundException, IOException {
		ItemsInventory itemsInventory = machine.getInventoryOfProductMap().get(productName);
		if (itemsInventory != null && itemsInventory.getItemCount() > 0 && quantity <= itemsInventory.getItemCount()) {
			Payment payment = PaymentImplementor.paymentOperation(itemsInventory.getItem().getCost() * quantity,
					amountInserted, paymentType);
			double change = payment.getChange();

			machine.CheckMachineState(amountInserted);

			if (change > 0 && machine.getMachine_state() instanceof NoCash) {
				return machine.getNoCashState().purchase();
			}

			else if (change < 0) {
				return "Amount inserted not sufficient to purchase the item you have to add " + change
						+ " money to take the product";
			} else {
				if (itemsInventory != null) {
					itemsInventory.setItemCount(itemsInventory.getItemCount() - 1);
				}

				try {
					Database.updateMachineInfo(1, machine);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return "purchase success " + itemsInventory.getItem().displayDetails(quantity) + " Refund=" + change;
			}
		} else if (itemsInventory != null && itemsInventory.getItemCount() == 0) {
			return ServiceMessages.THE_PRODUCT_IS_OUT_OF_STOCK;
		} else if (itemsInventory != null && quantity > itemsInventory.getItemCount()) {
			return "Maximum" + itemsInventory.getItemCount() + "items can be bought";
		} else {
			return ServiceMessages.ITEMS_INVENTORY_NOT_FOUND;
		}
	}

	private Machine getMachineInfo() {
		Machine machine = null;
		try {
			machine = Database.retrieveMachine(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return machine;
	}
}
