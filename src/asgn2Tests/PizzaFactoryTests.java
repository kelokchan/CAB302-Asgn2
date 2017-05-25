package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Pizzas.VegetarianPizza;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Person B 
 * 
 */
public class PizzaFactoryTests {
	// TO DO
	LocalTime orderTime;
	LocalTime deliveryTime;
	LocalTime openTime;
	LocalTime closeTime;
	
	@Before
	public void setUp() throws Exception {
		orderTime = LocalTime.parse("20:00:00");
		deliveryTime = LocalTime.parse("20:15:00");
		
	}
	
	@Test (expected = PizzaException.class)
	public void wrongPizzaCode() throws PizzaException {
		PizzaFactory.getPizza("abc", 2, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void blankPizzaCode() throws PizzaException {
		PizzaFactory.getPizza("", 2, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void longPizzaCode() throws PizzaException {
		PizzaFactory.getPizza("abcdefghi", 2, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void shortPizzaCode() throws PizzaException {
		PizzaFactory.getPizza("a", 2, orderTime, deliveryTime);
	}
	
	@Test 
	public void correctPizzaCode() throws PizzaException {
		assertEquals(PizzaFactory.getPizza("PZM", 2, orderTime, deliveryTime), new MargheritaPizza(2, orderTime, deliveryTime));
	}
	
	@Test 
	public void correctPizzaCode2() throws PizzaException {
		assertEquals(PizzaFactory.getPizza("PZV", 2, orderTime, deliveryTime), new VegetarianPizza(2, orderTime, deliveryTime));
	}
	
	@Test 
	public void correctPizzaCode3() throws PizzaException {
		assertEquals(PizzaFactory.getPizza("PZL", 2, orderTime, deliveryTime), new MeatLoversPizza(2, orderTime, deliveryTime));
	}
	
	@Test 
	public void correctPizzaCode_SmallLetter() throws PizzaException {
		assertEquals(PizzaFactory.getPizza("pzm", 2, orderTime, deliveryTime), new MargheritaPizza(2, orderTime, deliveryTime));
	}
	
	@Test 
	public void correctPizzaCode2_SmallLetter() throws PizzaException {
		assertEquals(PizzaFactory.getPizza("pzv", 2, orderTime, deliveryTime), new VegetarianPizza(2, orderTime, deliveryTime));
	}
	
	@Test 
	public void correctPizzaCode3_SmallLetter() throws PizzaException {
		assertEquals(PizzaFactory.getPizza("pzl", 2, orderTime, deliveryTime), new MeatLoversPizza(2, orderTime, deliveryTime));
	}
}
