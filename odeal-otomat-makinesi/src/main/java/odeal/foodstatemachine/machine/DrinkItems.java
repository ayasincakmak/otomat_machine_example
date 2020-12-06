package odeal.foodstatemachine.machine;

import java.io.Serializable;

public class DrinkItems extends ItemsForSale implements Serializable {

	private int sugarlevel;

	public int getSugarlevel() {
		return sugarlevel;
	}
	
	public DrinkItems () {
		super.setItemType("drink");
	}

	public void setSugarlevel(int sugarlevel) {
		this.sugarlevel = sugarlevel;
	}

	public String displayDetails(int quantity) {
		return "The selected item is " + name.toUpperCase() +"Cost of " + name + " is " + cost+", total payment=" + cost*quantity;
	}


}
