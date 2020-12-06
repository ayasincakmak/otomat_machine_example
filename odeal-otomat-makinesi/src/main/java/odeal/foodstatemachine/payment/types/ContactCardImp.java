package odeal.foodstatemachine.payment.types;

public class ContactCardImp extends PaymentFactory implements CreditCardType{

	public String getCreditCardType() {
		return "Contact Card";
	}

}
