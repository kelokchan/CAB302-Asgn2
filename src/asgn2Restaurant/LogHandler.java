package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Lee Chun Voo and Kuok Kit Chan
 *
 */
public class LogHandler {
	
	
	static BufferedReader br;
	public static final String COMMA = ",";

	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above such as:
	 * 						1. Inability to parse location X and Y string to integer format (unparseable type, overflow)
	 * 						2. File type is not .txt or equivalent
	 * 						3. String array after split out of bound
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		
		ArrayList<Customer> customerList;

		try {
			customerList = new ArrayList<Customer>();
			br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while(line != null) {
				Customer customer = createCustomer(line);
				customerList.add(customer);
				line = br.readLine();
			}			
			return customerList;
		} catch (CustomerException ce) {
			throw new CustomerException(ce.getMessage());
		} catch (Exception e) {
			throw new LogHandlerException(e.getMessage());
		}		
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above such as:
	 * 						1. Inability to parse orderTime/deliveryTime to LocalTime (unparseable type)
	 * 						2. File type is not .txt or equivalent (File is not a text file)
	 * 						3. Inability to parse quantity to integer format (unparseable type)
	 * 						4. String array after split out of bound
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		
		ArrayList<Pizza> pizzaList;

		try {
			pizzaList = new ArrayList<Pizza>();
			br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while(line != null) {
				Pizza pizza = createPizza(line);
				pizzaList.add(pizza);
				line = br.readLine();
			}			
			return pizzaList;
		} catch (PizzaException pe) {
			throw new PizzaException(pe.getMessage());
		} catch (Exception e) {
			throw new LogHandlerException(e.getMessage());
		}	
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file such as:
	 * 						1. Inability to parse location X and Y string to integer format (unparseable type, overflow)
	 * 						2. File type is not .txt or equivalent
	 * 						3. String array after split out of bound
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		
		try {
			String[] lineArr = line.split(COMMA);
			String name = lineArr[2];
			String mobileNumber = lineArr[3];
			String customerCode = lineArr[4];
			int locationX = Integer.parseInt(lineArr[5]);
			int locationY = Integer.parseInt(lineArr[6]);
			
			return CustomerFactory.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
		} catch (CustomerException ce) {
			throw new CustomerException(ce.getMessage());
		} catch (Exception ce) {
			throw new LogHandlerException(ce.getMessage());
		}
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file such as:
	 * 						1. Inability to parse orderTime/deliveryTime to LocalTime (unparseable type)
	 * 						2. File type is not .txt or equivalent (File is not a text file)
	 * 						3. Inability to parse quantity to integer format (unparseable type)
	 * 						4. String array after split out of bound
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		
		try {
			String[] lineArr = line.split(COMMA);
			LocalTime orderTime = LocalTime.parse(lineArr[0]);
			LocalTime deliveryTime = LocalTime.parse(lineArr[1]);
			String pizzaCode = lineArr[7];
			int quantity = Integer.parseInt(lineArr[8]);
			
			return PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
		} catch (PizzaException pe) {
			throw new PizzaException(pe.getMessage());
		} catch (Exception ce) {
			throw new LogHandlerException(ce.getMessage());
		}
	}

}
