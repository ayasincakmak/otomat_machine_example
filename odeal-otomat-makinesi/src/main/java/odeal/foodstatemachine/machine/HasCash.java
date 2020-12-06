package odeal.foodstatemachine.machine;

import java.io.Serializable;


public class HasCash implements MachineState,Serializable{
	
	Machine vendingMachine;
	
	public HasCash(Machine newVendingMachine){
		vendingMachine=newVendingMachine;
	}

	public String purchase() {
		return "Machine has money to refund money";
	}
	
}