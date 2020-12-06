package odeal.foodstatemachine.machine;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import odeal.foodstatemachine.Database;

public class Machine implements MachineComponents, Serializable {
	private int id;
	private HashMap<String, ItemsInventory> inventoryOfProductMap = new HashMap<String, ItemsInventory>();
	// changes for state
	MachineState noCash;
	MachineState noA1Item;
	MachineState hasCash;
	MachineState machineOutOfCash;

	private MachineState machine_state;
	int cashInMachine = 200;

	public Machine() throws ClassNotFoundException, IOException {

	}

	public void CheckMachineState(double refund) throws ClassNotFoundException, IOException {
		noCash = new NoCash(this);
		hasCash = new HasCash(this);
		machineOutOfCash = new NoCash(this);
		cashInMachine = (int) Database.getBalanceOfMachine(1);
		if (cashInMachine > 0 && cashInMachine >= (int)refund) {
			setMachine_state(hasCash);
		} else {
			setMachine_state(machineOutOfCash);
		}
	}

	void setMachineState(MachineState newMachineState) {
		setMachine_state(newMachineState);
	}

	void setCashInMachine(int newCashInMachine) {
		cashInMachine = newCashInMachine;
	}

	public MachineState getNoCashState() {
		return machineOutOfCash;
	}

	public String purchase() {
		return getMachine_state().purchase();
	}

	

	public void setMachineId(int id) {
		this.setId(id);
	}

	public int getMachineId() {
		return getId();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MachineState getMachine_state() {
		return machine_state;
	}

	public void setMachine_state(MachineState machine_state) {
		this.machine_state = machine_state;
	}

	public HashMap<String, ItemsInventory> getInventoryOfProductMap() {
		return inventoryOfProductMap;
	}

	public void setInventoryOfProductMap(HashMap<String, ItemsInventory> inventoryOfProductMap) {
		this.inventoryOfProductMap = inventoryOfProductMap;
	}

}