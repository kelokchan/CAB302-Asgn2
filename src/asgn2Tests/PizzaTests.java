package asgn2Tests;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	Pizza mp, mlp, vp;
	Pizza mpTest, mlpTest, vpTest;
	LocalTime orderTime;
	LocalTime deliveryTime;
	LocalTime openTime;
	LocalTime closeTime;
	static final int QUANTITY_FIVE = 5;
	
	@Before
	public void setUp() throws Exception {
		orderTime = LocalTime.parse("20:00:00");
		deliveryTime = LocalTime.parse("20:15:00");
		
		openTime = LocalTime.parse("19:00:00");
		closeTime = LocalTime.parse("23:00:00");

		mp = new MargheritaPizza(QUANTITY_FIVE, orderTime, deliveryTime);
		vp = new VegetarianPizza(QUANTITY_FIVE, orderTime, deliveryTime);
		mlp = new MeatLoversPizza(QUANTITY_FIVE, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void overPizzaOrderLimit() throws PizzaException {
		mpTest = new MargheritaPizza(11, orderTime, deliveryTime);
		vpTest = new VegetarianPizza(11, orderTime, deliveryTime);
		mlpTest = new MeatLoversPizza(11, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void zeroPizzaOrdered() throws PizzaException {
		mpTest = new MargheritaPizza(0, orderTime, deliveryTime);
		vpTest = new VegetarianPizza(0, orderTime, deliveryTime);
		mlpTest = new MeatLoversPizza(0, orderTime, deliveryTime);
	}
	
	@Test 
	public void correctPizzaAmountOrdered() throws PizzaException {
		mpTest = new MargheritaPizza(QUANTITY_FIVE, orderTime, deliveryTime);
		vpTest = new VegetarianPizza(QUANTITY_FIVE, orderTime, deliveryTime);
		mlpTest = new MeatLoversPizza(QUANTITY_FIVE, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void negativeAmountPizzaAmountOrdered() throws PizzaException {
		mpTest = new MargheritaPizza(-5, orderTime, deliveryTime);
		vpTest = new VegetarianPizza(-5, orderTime, deliveryTime);
		mlpTest = new MeatLoversPizza(-5, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void orderBeforeOpening() throws PizzaException {
		mpTest = new MargheritaPizza(QUANTITY_FIVE, openTime.minus(5, MINUTES), deliveryTime);
		vpTest = new VegetarianPizza(QUANTITY_FIVE, openTime.minus(5, MINUTES), deliveryTime);
		mlpTest = new MeatLoversPizza(QUANTITY_FIVE, openTime.minus(5, MINUTES), deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void orderAfterClosing() throws PizzaException {
		mpTest = new MargheritaPizza(QUANTITY_FIVE, closeTime.plus(5, MINUTES), deliveryTime);
		vpTest = new VegetarianPizza(QUANTITY_FIVE, closeTime.plus(5, MINUTES), deliveryTime);
		mlpTest = new MeatLoversPizza(QUANTITY_FIVE, closeTime.plus(5, MINUTES), deliveryTime);
	}
	
	@Test
	public void orderWhenShopOpened() throws PizzaException {
		mpTest = new MargheritaPizza(QUANTITY_FIVE, orderTime, deliveryTime);
		vpTest = new VegetarianPizza(QUANTITY_FIVE, orderTime, deliveryTime);
		mlpTest = new MeatLoversPizza(QUANTITY_FIVE, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void orderTimeSameWithDeliveryTime() throws PizzaException {
		mpTest = new MargheritaPizza(QUANTITY_FIVE, orderTime, orderTime);
		vpTest = new VegetarianPizza(QUANTITY_FIVE, orderTime, orderTime);
		mlpTest = new MeatLoversPizza(QUANTITY_FIVE, orderTime, orderTime);
	}
	
	@Test (expected = PizzaException.class)
	public void deliveryTimeWithinTenMinutes() throws PizzaException {
		mpTest = new MargheritaPizza(QUANTITY_FIVE, orderTime, orderTime.plus(5, MINUTES));
		vpTest = new VegetarianPizza(QUANTITY_FIVE, orderTime, orderTime.plus(5, MINUTES));
		mlpTest = new MeatLoversPizza(QUANTITY_FIVE, orderTime, orderTime.plus(5, MINUTES));
	}
	
	@Test (expected = PizzaException.class)
	public void differenceBetweenOrderTime_DeliveryTimeExceed1hour() throws PizzaException {
		mpTest = new MargheritaPizza(QUANTITY_FIVE, orderTime, deliveryTime.plus(70, MINUTES));
		vpTest = new VegetarianPizza(QUANTITY_FIVE, orderTime, deliveryTime.plus(70, MINUTES));
		mlpTest = new MeatLoversPizza(QUANTITY_FIVE, orderTime, deliveryTime.plus(70, MINUTES));
	}
	
	@Test (expected = PizzaException.class)
	public void deliveryTimeBeforeOrderTime() throws PizzaException {
		mpTest = new MargheritaPizza(QUANTITY_FIVE, orderTime.plus(50, MINUTES), deliveryTime);
		vpTest = new VegetarianPizza(QUANTITY_FIVE, orderTime.plus(50, MINUTES), deliveryTime);
		mlpTest = new MeatLoversPizza(QUANTITY_FIVE, orderTime.plus(50, MINUTES), deliveryTime);
	}
	
	@Test
	public void perPizzaCostCalculatedWhenPizzaCreated() {
		assertEquals(8.0, mp.getPricePerPizza(), 0.000);
		assertEquals(10.0, vp.getPricePerPizza(), 0.000);
		assertEquals(12.0, mlp.getPricePerPizza(), 0.000);
	}
	
	
	@Test
	public void getCostPerPizza(){
		assertEquals(1.5, mp.getCostPerPizza(), 0.000);
		assertEquals(5.5, vp.getCostPerPizza(), 0.000);
		assertEquals(5.0, mlp.getCostPerPizza(), 0.000);
	}
	
	@Test
	public void getPricePerPizza(){
		assertEquals(8.0, mp.getPricePerPizza(), 0.000);
		assertEquals(10.0, vp.getPricePerPizza(), 0.000);
		assertEquals(12.0, mlp.getPricePerPizza(), 0.000);
	}
	
	@Test
	public void getCalculatedOrderCost(){
		assertEquals(7.5, mp.getOrderCost(), 0.000);
		assertEquals(27.5, vp.getOrderCost(), 0.000);
		assertEquals(25.0, mlp.getOrderCost(), 0.000);
	}
	
	@Test
	public void getCalculatedOrderPrice(){
		assertEquals(40.0, mp.getOrderPrice(), 0.000);
		assertEquals(50.0, vp.getOrderPrice(), 0.000);
		assertEquals(60.0, mlp.getOrderPrice(), 0.000);
	}
	
	@Test
	public void getOrderProfit(){
		assertEquals(32.5, mp.getOrderProfit(), 0.000);
		assertEquals(22.5, vp.getOrderProfit(), 0.000);
		assertEquals(35.0, mlp.getOrderProfit(), 0.000);
	}
	
	@Test
	public void margheritaPizzaTopping(){
		assertTrue(mp.containsTopping(PizzaTopping.TOMATO));
		assertTrue(mp.containsTopping(PizzaTopping.CHEESE));
	}
	
	@Test
	public void vegarianPizzaTopping(){
		assertTrue(vp.containsTopping(PizzaTopping.TOMATO));
		assertTrue(vp.containsTopping(PizzaTopping.CHEESE));
		assertTrue(vp.containsTopping(PizzaTopping.EGGPLANT));
		assertTrue(vp.containsTopping(PizzaTopping.MUSHROOM));
		assertTrue(vp.containsTopping(PizzaTopping.CAPSICUM));
	}
	
	@Test
	public void meatLoverPizzaTopping(){
		assertTrue(mlp.containsTopping(PizzaTopping.BACON));
		assertTrue(mlp.containsTopping(PizzaTopping.TOMATO));
		assertTrue(mlp.containsTopping(PizzaTopping.CHEESE));
		assertTrue(mlp.containsTopping(PizzaTopping.PEPPERONI));
		assertTrue(mlp.containsTopping(PizzaTopping.SALAMI));
	}
		
	@Test
	public void toppingNotInMargheritaPizza(){
		assertFalse(mp.containsTopping(PizzaTopping.EGGPLANT));
	}
	
	@Test
	public void toppingNotInMeatLoverPizza(){
		assertFalse(mlp.containsTopping(PizzaTopping.EGGPLANT));
	}
	
	@Test
	public void toppingNotInTheVegetarianPizza(){
		assertFalse(vp.containsTopping(PizzaTopping.BACON));
	}
	
	@Test
	public void getQuantity(){
		assertEquals(QUANTITY_FIVE, mp.getQuantity());
		assertEquals(QUANTITY_FIVE, vp.getQuantity());
		assertEquals(QUANTITY_FIVE, mlp.getQuantity());
	}
	
	@Test
	public void getPizzaType(){
		assertEquals("Margherita", mp.getPizzaType());
		assertEquals("Vegetarian", vp.getPizzaType());
		assertEquals("Meat Lovers", mlp.getPizzaType());
	}
}
