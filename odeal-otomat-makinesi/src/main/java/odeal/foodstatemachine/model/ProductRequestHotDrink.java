package odeal.foodstatemachine.model;


public class ProductRequestHotDrink extends Food {

	private int sugarLevel;

	public ProductRequestHotDrink() {
		super();
	}
	
	public ProductRequestHotDrink(String productName, double amountInserted, int quantity, String paymentType,
			int sugarLevel) {
		super(productName, amountInserted, quantity, paymentType);
		this.sugarLevel = sugarLevel;
	}

	public int getSugarLevel() {
		return sugarLevel;
	}

	public void setSugarLevel(int sugarLevel) {
		this.sugarLevel = sugarLevel;
	}

}
