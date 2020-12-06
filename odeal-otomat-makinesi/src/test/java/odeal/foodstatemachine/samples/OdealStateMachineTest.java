package odeal.foodstatemachine.samples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import odeal.foodstatemachine.Application;
import odeal.foodstatemachine.Database;
import odeal.foodstatemachine.machine.DrinkItems;
import odeal.foodstatemachine.machine.ItemsInventory;
import odeal.foodstatemachine.machine.Machine;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@WebAppConfiguration
public class OdealStateMachineTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;

	@Before
	public void setup() throws Exception {
		Database.setupFiles();
		LoadItemsInMachine.createNewItems();
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void test_insufficient_money() throws Exception {
		// test params : amountInserted=0
		// tea cost = 2
		String requestBodyJson = "{\"productName\":\"Tea\",\"amountInserted\":\"0\",\"quantity\":4,\"paymentType\":\"Coins\",\"sugarLevel\":0}";
		String responseMessage = purchaseServiceCall(requestBodyJson);
		System.out.println(responseMessage);
		assertThat(responseMessage).isEqualTo(
				"Amount inserted not sufficient to purchase the item you have to add -8.0 money to take the product");
	}

	@Test
	public void test_sufficient_money() throws Exception {
		// test params : amountInserted=3
		// tea cost was = 2
		String requestBodyJson = "{\"productName\":\"Tea\",\"amountInserted\":\"3.0\",\"quantity\":1,\"paymentType\":\"Coins\",\"sugarLevel\":0}";
		String responseMessage = purchaseServiceCall(requestBodyJson);
		System.out.println(responseMessage);
		assertThat(responseMessage)
				.isEqualTo("purchase success The selected item is TEACost of Tea is 2.0, total payment=2.0 Refund=1.0");
	}

	@Test
	public void test_item_quantity_money() throws Exception {
		String requestBodyJson = "{\"productName\":\"Tea\",\"amountInserted\":\"8.0\",\"quantity\":2,\"paymentType\":\"Coins\",\"sugarLevel\":0}";
		String responseMessage = purchaseServiceCall(requestBodyJson);
		System.out.println(responseMessage);
		assertThat(responseMessage)
				.isEqualTo("purchase success The selected item is TEACost of Tea is 2.0, total payment=4.0 Refund=4.0");
	}

	@Test
	public void test_nocash_inmachine_to_refund() throws Exception {
		// test params: amountInserted=800
		String requestBodyJson = "{\"productName\":\"Tea\",\"amountInserted\":\"800.0\",\"quantity\":2,\"paymentType\":\"Coins\",\"sugarLevel\":0}";
		String responseMessage = purchaseServiceCall(requestBodyJson);
		System.out.println(responseMessage);
		assertThat(responseMessage)
				.isEqualTo("Sorry! The machine doesn't have enough refund money to complete the transaction.");
	}

	private String purchaseServiceCall(String requestBodyJson) throws Exception, UnsupportedEncodingException {
		MvcResult result = mvc.perform(
				post("/productRequestHotDrink").content(requestBodyJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String responseMessage = response.getContentAsString();
		return responseMessage;
	}

	@Test
	public void loadNewItemInMachine() throws Exception {
		// Creating new items
		DrinkItems salep = new DrinkItems();
		salep.setName("Salep");
		salep.setCost(4.0);

		// Machine load
		Machine machine = Database.retrieveMachine(1);

		ItemsInventory loadToMachine = new ItemsInventory();
		loadToMachine.setItem(salep);
		loadToMachine.setItemCount(5);

		// load
		machine.getInventoryOfProductMap().put(salep.getItemName(), loadToMachine);
		Database.addMachine(machine);

		test_maximum_item_can_buy();

	}

	public void test_maximum_item_can_buy() throws Exception {

		String requestBodyJson = "{\"productName\":\"Salep\",\"amountInserted\":\"3.0\",\"quantity\":30,\"paymentType\":\"Coins\",\"sugarLevel\":0}";

		String responseMessage = purchaseServiceCall(requestBodyJson);
		System.out.println("service response " + responseMessage);
		assertThat(responseMessage).isEqualTo("Maximum5items can be bought");
	}

}
