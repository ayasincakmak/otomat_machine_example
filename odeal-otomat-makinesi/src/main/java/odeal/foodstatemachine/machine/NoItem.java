package odeal.foodstatemachine.machine;

import java.io.Serializable;


class NoA1Item implements MachineState,Serializable{
	
	Machine vendingMachine;
	
	public NoA1Item(Machine newVendingMachine){
		vendingMachine=newVendingMachine;
	}

	public String purchase() {
		System.out.println("Sorry! secilen urun makinede bulunmamaktadir ");
		return "Item out of stock..";
	}
	
}