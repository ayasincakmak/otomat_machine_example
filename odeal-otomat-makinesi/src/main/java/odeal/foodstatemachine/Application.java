package odeal.foodstatemachine;

import java.io.IOException;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import odeal.foodstatemachine.machine.DrinkItems;
import odeal.foodstatemachine.machine.FoodItems;
import odeal.foodstatemachine.machine.ItemsInventory;
import odeal.foodstatemachine.machine.Machine;
/**
 * 
 * @author ycakmak
 *
 */
@SpringBootApplication
public class Application {
	public static void main(String args[]) throws ClassNotFoundException, IOException {
		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		app.run(args);
		Database.setupFiles();
		createNewItems();
	}

	public static void createNewItems() throws IOException, ClassNotFoundException {
		// Creating new items
		FoodItems chips = new FoodItems();
		DrinkItems soda = new DrinkItems();
		DrinkItems tea = new DrinkItems();
		FoodItems water = new FoodItems();
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
		
		Database.addMachine(newMachine);

	}
}
