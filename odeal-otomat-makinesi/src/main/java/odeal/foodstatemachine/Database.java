package odeal.foodstatemachine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import odeal.foodstatemachine.machine.FoodItems;
import odeal.foodstatemachine.machine.*;

public class Database implements Serializable {

	private static Set<Machine> machines = new HashSet<Machine>();
	private static HashMap<Integer, Double> totalCost = new HashMap<Integer, Double>();
	private static HashMap<Integer, Double> balanceCash = new HashMap<Integer, Double>();

	public static void setupFiles() throws IOException {
		boolean firstSetup = false;
		try {
			FileInputStream stream = new FileInputStream("machines");
		} catch (FileNotFoundException e) {
			firstSetup = true;
		}
		if (firstSetup) {
			FileOutputStream fos = new FileOutputStream("machines");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(machines);
			oos.close();
			fos = new FileOutputStream("machineId");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(1);
			oos.close();
			
			fos = new FileOutputStream("cashInMachine");
			oos = new ObjectOutputStream(fos);
			//add cash to refund 
			balanceCash.put(1, 20.00);
			oos.writeObject(balanceCash);
			oos.close();
		}
	}

	public static List<FoodItems> getItemsFromList() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("items");
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<FoodItems> items = (List<FoodItems>) ois.readObject();
		ois.close();
		fis.close();
		return items;
	}

	public static void updateItemInfo(String item_name, FoodItems new_item) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("items");
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<FoodItems> item = (List<FoodItems>) ois.readObject();
		ois.close();
		FoodItems itemToDel = null;

		for (FoodItems i : item) {
			if (i.getItemName().equals(item_name)) {
				itemToDel = i;
			}
		}

		item.remove(itemToDel);
		item.add(new_item);
		FileOutputStream fos = new FileOutputStream("items");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(item);
		oos.close();
	}

	public static void addMachine(Machine machine) throws IOException, ClassNotFoundException {
		machines = getAllMachines();
		machines.add(machine);
		FileOutputStream fos = new FileOutputStream("machines");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(machines);
		oos.close();
	}

	public static Set<Machine> getAllMachines() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("machines");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Set<Machine> machine = (Set<Machine>) ois.readObject();
		ois.close();
		fis.close();
		return machine;
	}

	public static Machine retrieveMachine(int id) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("machines");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Set<Machine> machine = (Set<Machine>) ois.readObject();
		ois.close();
		Iterator<Machine> machinesIterator = null;
		machinesIterator = machine.iterator();
		while (machinesIterator.hasNext()) {
			Machine mac = machinesIterator.next();
			if (mac.getId() == id) {
				return mac;
			}
		}
		return null;
	}

	public static void updateMachineInfo(int id, Machine newMachine) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("machines");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Set<Machine> machine = (Set<Machine>) ois.readObject();
		ois.close();
		Machine macToDel = null;

		for (Machine mac : machine) {
			if (mac.getId() == id) {
				macToDel = mac;
			}
		}

		machine.remove(macToDel);
		machine.add(newMachine);
		FileOutputStream fos = new FileOutputStream("machines");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(machine);
		oos.close();
	}

	public static int getMachineId() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("machineId");
		ObjectInputStream ois = new ObjectInputStream(fis);
		int id = (Integer) ois.readObject();
		ois.close();
		return id;
	}

	public static void setMachineId() throws IOException, ClassNotFoundException {
		int id = getMachineId();
		id++;
		FileOutputStream fos = new FileOutputStream("machineId");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject((int) id);
		oos.close();
	}

	// Updating the total earnings of the machine
//	public static void updateCostOfMachine(int id, double amount) throws IOException, ClassNotFoundException {
//		FileInputStream fis = new FileInputStream("earnings");
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		totalCost = (HashMap<Integer, Double>) ois.readObject();
//		ois.close();
//		if (totalCost.containsKey(id)) {
//			double total = totalCost.get(id);
//			total += amount;
//			totalCost.put(id, total);
//		} else {
//			totalCost.put(id, amount);
//		}
//		FileOutputStream fos = new FileOutputStream("earnings");
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(totalCost);
//		oos.close();
//	}
//
//	// Fetching the earnings of the machine
//	public static double getEarningsOfMachine(int id) throws IOException, ClassNotFoundException {
//		FileInputStream fis = new FileInputStream("earnings");
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		totalCost = (HashMap<Integer, Double>) ois.readObject();
//		ois.close();
//		return totalCost.get(id);
//	}

	public static void updateCashInMachine(int id, double amount) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("cashInMachine");
		ObjectInputStream ois = new ObjectInputStream(fis);
		balanceCash = (HashMap<Integer, Double>) ois.readObject();
		ois.close();
		if (balanceCash.containsKey(id)) {
			double total = balanceCash.get(id);
			total -= amount;
			balanceCash.put(id, total);
		} else {
			balanceCash.put(id, amount);
		}
		FileOutputStream fos = new FileOutputStream("cashInMachine");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(balanceCash);
		oos.close();
	}

	// Fetching the balance of the machine
	public static double getBalanceOfMachine(int id) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("cashInMachine");
		ObjectInputStream ois = new ObjectInputStream(fis);
		balanceCash = (HashMap<Integer, Double>) ois.readObject();
		ois.close();
		return balanceCash.get(id);
	}

}
