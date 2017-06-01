package asgn2Pizzas;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.MINUTES;
import asgn2Exceptions.PizzaException;

/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Person A
 *
 */
public abstract class Pizza  {
	int quantity;
	LocalTime orderTime, deliveryTime;
	String type;
	double price;
	double cost;
	double orderCost;
	double orderPrice;
	double orderProfit;
	private ArrayList<PizzaTopping> toppings;
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 * 
	 */
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		// TO DO	
		LocalTime openTime = LocalTime.parse("19:00:00");
		LocalTime closeTime = LocalTime.parse("23:00:00");

		this.cost = 0.0;
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.type = type;
		this.price = price;
		this.orderCost = 0.0;
		this.orderPrice = 0.0;
		this.orderProfit = 0.0;
		
		if (orderTime.isBefore(openTime)) {
			throw new PizzaException("Kitchen is not open yet!");
		}
		if (orderTime.isAfter(closeTime)) {
			throw new PizzaException("Kitchen is closed!");
		}
		if (quantity <= 0) {
			throw new PizzaException("The order must contains at least one pizza!");
		}
		if (quantity > 10) {
			throw new PizzaException("You only can order 10 pizzas in one order!");
		}
		if(orderTime.until(deliveryTime, MINUTES) < 10){
			throw new PizzaException("Your Pizza has not ready yet. It needs at least 10 minutues to cook.");
		}
		if(orderTime.until(deliveryTime, MINUTES) > 60){
			throw new PizzaException("The pizza has been thrown out!");
		}

		toppings = new ArrayList<PizzaTopping>();

		switch (type) {
			case "Margherita":
				toppings.add(PizzaTopping.TOMATO);
				toppings.add(PizzaTopping.CHEESE);
				break;
			case "Vegetarian":
				toppings.add(PizzaTopping.EGGPLANT);
				toppings.add(PizzaTopping.TOMATO);
				toppings.add(PizzaTopping.CHEESE);
				toppings.add(PizzaTopping.MUSHROOM);
				toppings.add(PizzaTopping.CAPSICUM);
				break;
			case "Meat Lovers":
				toppings.add(PizzaTopping.BACON);
				toppings.add(PizzaTopping.TOMATO);
				toppings.add(PizzaTopping.CHEESE);
				toppings.add(PizzaTopping.PEPPERONI);
				toppings.add(PizzaTopping.SALAMI);
				break;
			default:
				throw new PizzaException("Wrong Pizza Code!");
		}
		

	}

	/**
	 * Calculates how much a pizza would cost to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		// TO DO
		for (PizzaTopping pizzaTopping : toppings) {
			cost += pizzaTopping.getCost();
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		// TO DO
		// Reset the cost and calculate 
		cost = 0;
		calculateCostPerPizza();
		return cost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		// TO DO
		return price;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		// TO DO
		orderCost = (getCostPerPizza() * quantity);
		return orderCost;
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		// TO DO
		orderPrice = (getPricePerPizza() * quantity);
		return orderPrice;
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		// TO DO
		orderProfit = getOrderPrice() - getOrderCost();
		return orderProfit;
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		// TO DO
		if(this.toppings.contains(topping)){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		// TO DO
		return quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		// TO DO
		return type;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}

	
	
}
