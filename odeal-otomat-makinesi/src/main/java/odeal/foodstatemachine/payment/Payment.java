package odeal.foodstatemachine.payment;

public abstract class Payment {
	protected double totalAmount;
	protected double amountInserted;
	protected String paymentMethod;
	protected PaymentImplementor paymentImpl;
	
	public Payment(double total, double amountInserted, PaymentImplementor paymentImpl){
		this.amountInserted = amountInserted;
		this.totalAmount = total;
		this.paymentImpl = paymentImpl;
	}
	
	public double getTotalAmount(){
		return this.totalAmount;
	}
	
	public double getAmountInserted(){
		return this.amountInserted;
	}
	
	public abstract void setPaymentMethod(String paymentMethod);
	public abstract double getChange();
	
}
