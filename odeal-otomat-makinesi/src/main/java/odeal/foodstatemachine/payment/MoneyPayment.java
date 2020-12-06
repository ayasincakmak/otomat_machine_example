package odeal.foodstatemachine.payment;

public class MoneyPayment extends Payment {
	
	public MoneyPayment(double total, double amountInserted, PaymentImplementor paymentImpl) {
		super(total, amountInserted, paymentImpl);
	}
	
	@Override
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public double getChange() {
		return paymentImpl.getChange(this.totalAmount, this.amountInserted);
	}



}
