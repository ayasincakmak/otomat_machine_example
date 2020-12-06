package odeal.foodstatemachine.service;

import odeal.foodstatemachine.model.ProductRequestFood;
import odeal.foodstatemachine.model.ProductRequestHotDrink;

public interface PurchaseProductService {

	String purchaseServiceHotDrink(ProductRequestHotDrink prdocutRequestDTO) throws PurchaseServiceException;

	String purchaseServiceFood(ProductRequestFood prdocutRequestDTO) throws PurchaseServiceException;

}
