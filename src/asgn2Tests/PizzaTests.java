package asgn2Tests;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.PizzaTopping;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	// TO DO
	MeatLoversPizza p;
	MeatLoversPizza test;
	LocalTime orderTime;
	LocalTime deliveryTime;
	LocalTime openTime;
	LocalTime closeTime;
	
	@Before
	public void setUp() throws Exception {
		orderTime = LocalTime.parse("20:00:00");
		deliveryTime = LocalTime.parse("20:15:00");
		
		openTime = LocalTime.parse("19:00:00");
		closeTime = LocalTime.parse("23:00:00");

		p = new MeatLoversPizza(5, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void overPizzaOrderLimit() throws PizzaException {
		test = new MeatLoversPizza(11, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void zeroPizzaOrdered() throws PizzaException {
		test = new MeatLoversPizza(0, orderTime, deliveryTime);
	}
	
	@Test 
	public void correctPizzaAmountOrdered() throws PizzaException {
		test = new MeatLoversPizza(5, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void negativeAmountPizzaAmountOrdered() throws PizzaException {
		test = new MeatLoversPizza(-5, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void orderBeforeOpening() throws PizzaException {
		test = new MeatLoversPizza(5, openTime.minus(5, MINUTES), deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void orderAfterClosing() throws PizzaException {
		test = new MeatLoversPizza(5, closeTime.plus(5, MINUTES), deliveryTime);
	}
	
	@Test
	public void orderWhenShopOpen() throws PizzaException {
		test = new MeatLoversPizza(5, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void orderTimeSameWithDeliveryTime() throws PizzaException {
		test = new MeatLoversPizza(5, orderTime, orderTime);
	}
	
	@Test (expected = PizzaException.class)
	public void differenceBetweenOrderTime_DeliveryTimeExceed1hour() throws PizzaException {
		test = new MeatLoversPizza(5, orderTime, deliveryTime.plus(70, MINUTES));
	}
	
	@Test (expected = PizzaException.class)
	public void deliveryTimeLargerThanOrderTime() throws PizzaException {
		test = new MeatLoversPizza(5, orderTime.plus(50, MINUTES), deliveryTime);
	}
	
	@Test
	public void meatLoverPizzaTopping(){
		assertTrue(p.containsTopping(PizzaTopping.BACON));
		assertTrue(p.containsTopping(PizzaTopping.TOMATO));
		assertTrue(p.containsTopping(PizzaTopping.CHEESE));
		assertTrue(p.containsTopping(PizzaTopping.PEPPERONI));
		assertTrue(p.containsTopping(PizzaTopping.SALAMI));
	}
	
	@Test
	public void toppingNotInTheMeatLoverPizza(){
		assertFalse(p.containsTopping(PizzaTopping.EGGPLANT));
		assertFalse(p.containsTopping(PizzaTopping.MUSHROOM));
		assertFalse(p.containsTopping(PizzaTopping.CAPSICUM));
	}
	
	@Test
	public void getInitialPizzaCost(){
		assertEquals(p.getCostPerPizza(), 0.0, 0.000);
	}
	
	@Test
	public void getPerPizzaPrice(){
		assertEquals(p.getPricePerPizza(), 12.0, 0.000);
	}
	
	@Test
	public void getQuantity(){
		assertEquals(p.getQuantity(), 5);
	}
	
	@Test
	public void getPizzaType(){
		assertEquals(p.getPizzaType(), "Meat Lovers");
	}
	
	@Test
	public void getInitialCostPerPizza(){
		p.calculateCostPerPizza();
		assertEquals(p.getCostPerPizza(), 5, 0.000);
	}
	
	@Test
	public void calculateCostPerPizza(){
		p.calculateCostPerPizza();
		assertEquals(p.getCostPerPizza(), 5, 0.000);
	}
	
	@Test
	public void getInitialOrderCost(){
		assertEquals(p.getOrderCost(), 0.0, 0.000);
	}
	
	@Test
	public void getCalculatedOrderCost(){
		p.calculateCostPerPizza();
		assertEquals(p.getOrderCost(), 25.0, 0.000);
	}
	
	@Test
	public void getCalculatedOrderPrice(){
		assertEquals(p.getOrderPrice(), 60.0, 0.000);
	}
	
	@Test
	public void getOrderProfit(){
		p.calculateCostPerPizza();
		assertEquals(p.getOrderProfit(), 35.0, 0.000);
	}
}
