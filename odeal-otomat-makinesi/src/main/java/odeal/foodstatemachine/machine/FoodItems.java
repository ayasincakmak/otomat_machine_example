package odeal.foodstatemachine.machine;

import java.io.Serializable;

public class FoodItems extends ItemsForSale implements Serializable {

	public FoodItems() {
		super.setItemType("food");

	}

	public String displayDetails(int quantity) {
		return "The selected item is " + name.toUpperCase() +"Cost of " + name + " is " + cost+  ", total payment="+cost*quantity;
	}

}
