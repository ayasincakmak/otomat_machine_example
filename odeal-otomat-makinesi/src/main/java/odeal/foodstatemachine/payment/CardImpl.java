package odeal.foodstatemachine.payment;

import odeal.foodstatemachine.payment.types.CreditCardType;

public class CardImpl extends PaymentImplementor{
	int cardId;
	private CreditCardType creditCardType;

	public void setCardId(int id){
		this.cardId = id;
	}
	
	public CardImpl(CreditCardType creditCardType){
		this.setCreditCardType(creditCardType);
	}
	
	@Override
	public double getChange(double totalAmount, double amtInserted) {
		//calculate the balance and return the card balance
		this.change = amtInserted - totalAmount;
		return this.change;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}

}
