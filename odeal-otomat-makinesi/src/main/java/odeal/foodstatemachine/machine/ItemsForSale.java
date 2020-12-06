package odeal.foodstatemachine.machine;

import java.io.Serializable;

public abstract class ItemsForSale implements Serializable {

	protected String name;
	protected double cost;
	private String itemType;
	private ItemsForSale item;

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return this.cost;
	}

	public String getItemName() {
		return this.name;
	}

	public abstract String displayDetails(int quantity);

	public ItemsForSale getItem() {
		return item;
	}

	public void setItem(ItemsForSale item) {
		this.item = item;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	
	

}
