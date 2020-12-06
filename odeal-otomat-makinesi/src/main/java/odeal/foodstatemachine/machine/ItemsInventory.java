package odeal.foodstatemachine.machine;

public class ItemsInventory extends ItemsForSale {

	
	private  int itemCount;
	
	public ItemsInventory() {
		super();
	}
	
	@Override
	public String displayDetails(int quantity) {
		return "";
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	
	
}
