package odeal.foodstatemachine.machine;

import java.io.Serializable;


public class NoCash implements MachineState,Serializable{
	
	Machine vendingMachine;
	
	public NoCash(Machine newVendingMachine){
		vendingMachine=newVendingMachine;
	}

	public String purchase() {
		return "Sorry! The machine doesn't have enough refund money to complete the transaction.";
	}
	
}