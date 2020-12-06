package odeal.foodstatemachine.payment;

import odeal.foodstatemachine.payment.types.CashMoney;
import odeal.foodstatemachine.payment.types.CoinMoney;
import odeal.foodstatemachine.payment.types.ContactCardImp;

public abstract class PaymentImplementor {
	protected double change;

	public abstract double getChange(double totalAmount, double amtInserted);

	public static Payment paymentOperation(double totalAmount, double amtInserted, String paymentType) {
		if (paymentType.equals("Coins")) {
			return new MoneyPayment(totalAmount, amtInserted, new MoneyImpl(new CoinMoney()));
		} else if (paymentType.equals("Cash")) {
			return new MoneyPayment(totalAmount, amtInserted, new MoneyImpl(new CashMoney()));
		} else {
			return new CardPayment(totalAmount, amtInserted, new CardImpl(new ContactCardImp()));
		}
	}
}
