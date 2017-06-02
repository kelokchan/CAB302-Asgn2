package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Lee Chun Voo
 */
public class RestaurantCustomerTests {
	
	final static String FILE_1 = Paths.get("logs/20170101.txt").toString();
	final static String FILE_2 = Paths.get("logs/20170102.txt").toString();
	final static String FILE_3 = Paths.get("logs/20170103.txt").toString();
	final static String INVALID_FILE = Paths.get("logs/Unit_Test/Customer/InvalidCustCode.txt").toString();
	private PizzaRestaurant restaurant;
	
	@Before
	public void setUp() throws Exception {
		restaurant = new PizzaRestaurant();
		restaurant.processLog(FILE_1);
	}
	
	@Test
	public void processLogReturnsTrueIfProcessesSuccessfully() throws CustomerException, PizzaException, LogHandlerException {
		assertTrue(restaurant.processLog(FILE_1));
	}
	
	@Test (expected = LogHandlerException.class)
	public void processLogEmptyFilename() throws CustomerException, PizzaException, LogHandlerException {
		assertTrue(restaurant.processLog(""));		
	}
	
	@Test (expected = CustomerException.class)
	public void processLogInvalidCustCode() throws CustomerException, PizzaException, LogHandlerException {
		assertTrue(restaurant.processLog(INVALID_FILE));		
	}
	
	@Test (expected = CustomerException.class)
	public void getCustomerIndexLessThanZero() throws CustomerException {
		restaurant.getCustomerByIndex(-1);
	}
	
	@Test (expected = CustomerException.class)
	public void getCustomerMoreThanSize() throws CustomerException {
		restaurant.getCustomerByIndex(1000);
	}
	
	@Test
	public void getCustByIndexReturnCustClass() throws CustomerException {
		assertNotNull(restaurant.getCustomerByIndex(1));
	}
	
	@Test 
	public void getNumCustomerOrdersReturnOrdersSize() {
		assertEquals(3, restaurant.getNumCustomerOrders());
	}
	
	@Test
	public void getTotalDeliveryDistanceReturnTotalDistanceOfCustomers() {
		assertEquals(15.0, restaurant.getTotalDeliveryDistance(), 0.000);
	}
	
	@Test
	public void resetDetailClearCustomerList() {
		restaurant.resetDetails();
		assertEquals(0, restaurant.getNumCustomerOrders());
	}
}
