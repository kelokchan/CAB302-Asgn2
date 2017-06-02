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
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Kuok Kit Chan
 *
 */
public class RestaurantPizzaTests {
	
	final static String FILE_1 = Paths.get("logs/20170101.txt").toString();
	final static String FILE_2 = Paths.get("logs/20170102.txt").toString();
	final static String FILE_3 = Paths.get("logs/20170103.txt").toString();
	final static String INVALID_FILE = Paths.get("logs/Unit_Test/Pizza/InvalidPizzaCode.txt").toString();
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
	
	@Test (expected = PizzaException.class)
	public void processLogInvalidPizzaCode() throws CustomerException, PizzaException, LogHandlerException {
		assertTrue(restaurant.processLog(INVALID_FILE));		
	}
	
	@Test (expected = PizzaException.class)
	public void getPizzaIndexLessThanZero() throws PizzaException {
		restaurant.getPizzaByIndex(-1);
	}
	
	@Test (expected = PizzaException.class)
	public void getPizzaMoreThanSize() throws PizzaException {
		restaurant.getPizzaByIndex(1000);
	}
	
	@Test
	public void getPizzaByIndexReturnPizzaClass() throws PizzaException {
		assertNotNull(restaurant.getPizzaByIndex(1));
	}
	
	@Test 
	public void getNumPizzaOrdersReturnOrdersSize() {
		assertEquals(3, restaurant.getNumPizzaOrders());
	}
	
	@Test
	public void getTotalProfitReturnTotalProfitForOrders() {
		assertEquals(36.5, restaurant.getTotalProfit(), 0.000);
	}
	
	@Test
	public void resetDetailClearPizzaList() {
		restaurant.resetDetails();
		assertEquals(0, restaurant.getNumPizzaOrders());
	}
}
