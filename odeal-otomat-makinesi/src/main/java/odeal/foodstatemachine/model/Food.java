package odeal.foodstatemachine.model;

public abstract class Food implements ProductRequest {

	private int quantity;
	private String productName;
	private double amountInserted;
	private String paymentType;
	
	public Food() {}

	public Food(String productName, double amountInserted, int quantity, String paymentType) {

		this.productName = productName;
		this.amountInserted = amountInserted;
		this.quantity = quantity;
		this.paymentType = paymentType;

	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getAmountInserted() {
		return amountInserted;
	}

	public void setAmountInserted(double amountInserted) {
		this.amountInserted = amountInserted;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

}
