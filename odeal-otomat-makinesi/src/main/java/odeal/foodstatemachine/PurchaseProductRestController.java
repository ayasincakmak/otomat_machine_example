package odeal.foodstatemachine;

import odeal.foodstatemachine.model.ProductRequestFood;
import odeal.foodstatemachine.model.ProductRequestHotDrink;
import odeal.foodstatemachine.service.PurchaseProductServiceImpl;
import odeal.foodstatemachine.service.PurchaseServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseProductRestController {
	
	@Autowired
	private PurchaseProductServiceImpl productService;

	@PostMapping(value = "/productRequestHotDrink")
	public String purchaseService(@RequestBody ProductRequestHotDrink product) throws PurchaseServiceException {
		product.getAmountInserted();
		return productService.purchaseServiceHotDrink(product);
	}

	@RequestMapping(value = "/productRequestFood", method = RequestMethod.POST, headers = "Accept=application/json")
	public String purchaseService(@RequestBody ProductRequestFood product) throws PurchaseServiceException {
		return productService.purchaseServiceFood(product);
	}
}
