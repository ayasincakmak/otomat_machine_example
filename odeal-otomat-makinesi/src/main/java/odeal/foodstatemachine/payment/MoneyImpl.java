package odeal.foodstatemachine.payment;

import odeal.foodstatemachine.payment.types.CashType;

public class MoneyImpl extends PaymentImplementor {

	private CashType moneyType;

	public MoneyImpl(CashType moneyType) {
		this.setMoneyType(moneyType);
	}

	@Override
	public double getChange(double totalAmount, double amtInserted) {
		this.change = amtInserted - totalAmount;
		return this.change;
	}

	public CashType getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(CashType moneyType) {
		this.moneyType = moneyType;
	}

}
