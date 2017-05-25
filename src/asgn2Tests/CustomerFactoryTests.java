package asgn2Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;
/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Person A
 *
 */
public class CustomerFactoryTests {
	// TO DO
	String name = "David";
	String mobileNumber = "0412341234";
	int locationX = 1;
	int locationY = 2;
	
	@Test
	public void pucReturnPickUpCustomer() throws CustomerException {
		Customer customer = CustomerFactory.getCustomer("PUC", name, mobileNumber, locationX, locationY);
		assertEquals(PickUpCustomer.class, customer.getClass());
	}
	
	@Test
	public void dncReturnDroneDeliveryCustomer() throws CustomerException {
		Customer customer = CustomerFactory.getCustomer("DNC", name, mobileNumber, locationX, locationY);
		assertEquals(DroneDeliveryCustomer.class, customer.getClass());
	}
	
	@Test
	public void dvcReturnDriverDeliveryCustomer() throws CustomerException {
		Customer customer = CustomerFactory.getCustomer("DVC", name, mobileNumber, locationX, locationY);
		assertEquals(DriverDeliveryCustomer.class, customer.getClass());
	}
	
	@Test
	public void lowerCaseCustomerCodeWillWork() throws CustomerException {
		Customer customer = CustomerFactory.getCustomer("puc", name, mobileNumber, locationX, locationY);
		assertEquals(PickUpCustomer.class, customer.getClass());
	}
	
	@Test (expected = CustomerException.class)
	public void invalidCustCodeWillThrowException() throws CustomerException {
		Customer customer = CustomerFactory.getCustomer("DUH", name, mobileNumber, locationX, locationY);
	}
	
}
