package odeal.foodstatemachine.samples;

import java.io.IOException;

import odeal.foodstatemachine.Database;
import odeal.foodstatemachine.machine.DrinkItems;
import odeal.foodstatemachine.machine.FoodItems;
import odeal.foodstatemachine.machine.ItemsInventory;
import odeal.foodstatemachine.machine.Machine;

public class LoadItemsInMachine {

	
	public static void createNewItems() throws IOException, ClassNotFoundException {
		// Creating new items or Change item count or item cost
		FoodItems chips = new FoodItems();
		DrinkItems soda = new DrinkItems();
		DrinkItems tea = new DrinkItems();
		DrinkItems water = new DrinkItems();

		chips.setName("Chips");
		chips.setCost(2.00);
		soda.setName("Soda");
		soda.setCost(3.00);
		tea.setName("Tea");
		tea.setCost(2.0);
		water.setName("Water");
		water.setCost(2.15);

		// Makine ilklendir.

		Machine newMachine = new Machine();
		newMachine.setMachineId(1);
		ItemsInventory loadToMachine = new ItemsInventory();
		loadToMachine.setItem(soda);
		loadToMachine.setItemCount(2);
		newMachine.getInventoryOfProductMap().put(soda.getItemName(), loadToMachine);
		
		loadToMachine = new ItemsInventory();
		loadToMachine.setItem(tea);
		loadToMachine.setItemCount(15);
		newMachine.getInventoryOfProductMap().put(tea.getItemName(), loadToMachine);
		
		
		//add new 
		FoodItems pasta = new FoodItems();
		pasta.setName("Pasta");
		pasta.setCost(5.5);
		loadToMachine = new ItemsInventory();
		loadToMachine.setItem(pasta);
		loadToMachine.setItemCount(6);
		newMachine.getInventoryOfProductMap().put(pasta.getItemName(), loadToMachine);
		
		
		Database.addMachine(newMachine);

	}
}
