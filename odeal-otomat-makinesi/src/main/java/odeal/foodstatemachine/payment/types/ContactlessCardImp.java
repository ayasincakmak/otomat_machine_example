package odeal.foodstatemachine.payment.types;

public class ContactlessCardImp extends PaymentFactory implements CreditCardType{

	public String getCreditCardType() {
		return "Contactless Card";
	}

}
