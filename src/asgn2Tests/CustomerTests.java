package asgn2Tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
	// TO DO
	DriverDeliveryCustomer driverDeliveryCust;
	DroneDeliveryCustomer droneDeliveryCust;
	PickUpCustomer pickupCust;
	
	@Before
	public void setUp() throws CustomerException {
		driverDeliveryCust = new DriverDeliveryCustomer("David", "0411241122", 1, 1);
		droneDeliveryCust = new DroneDeliveryCustomer("John", "0411241123", 3, 4);
		pickupCust = new PickUpCustomer("Alex", "0411241124", 10, 10);
	}
	
	@Test (expected = CustomerException.class)
	public void constructWhitespaceName() throws CustomerException {
		DriverDeliveryCustomer ddCust = new DriverDeliveryCustomer("        ", "0411241122", 1, 1);
		DroneDeliveryCustomer drdCust = new DroneDeliveryCustomer("        ", "0411241123", 2, 2);
		PickUpCustomer puCust = new PickUpCustomer("        ", "0411241124", 0, 0);
	}
	
	@Test (expected = CustomerException.class)
	public void constructLongName() throws CustomerException {
		DriverDeliveryCustomer ddCust = new DriverDeliveryCustomer("My Name Is Way Too Long To Handle", "0411241122", 1, 1);
		DroneDeliveryCustomer drd = new DroneDeliveryCustomer("My Name Is Way Too Long To Handle", "0411241123", 2, 2);
		PickUpCustomer puCust = new PickUpCustomer("My Name Is Way Too Long To Handle", "0411241124", 0, 0);
	}
	
	@Test (expected = CustomerException.class)
	public void constructEmptyName() throws CustomerException {
		DriverDeliveryCustomer ddCust = new DriverDeliveryCustomer("", "0411241122", 1, 1);
		DroneDeliveryCustomer drdCust = new DroneDeliveryCustomer("", "0411241123", 2, 2);
		PickUpCustomer puCust = new PickUpCustomer("", "0411241124", 0, 0);
	}
	
	@Test (expected = CustomerException.class)
	public void constructPhoneNumberDoesntStartWithZero() throws CustomerException {
		DriverDeliveryCustomer ddCust = new DriverDeliveryCustomer("A", "1411241122", 1, 1);
		DroneDeliveryCustomer drdCust = new DroneDeliveryCustomer("B", "1411241123", 2, 2);
		PickUpCustomer puCust = new PickUpCustomer("C", "1411241124", 0, 0);
	}
	
	@Test (expected = CustomerException.class)
	public void constructPhoneNumberLengthLessThanTen() throws CustomerException {
		DriverDeliveryCustomer ddCust = new DriverDeliveryCustomer("A", "0411", 1, 1);
		DroneDeliveryCustomer drdCust = new DroneDeliveryCustomer("B", "0412", 2, 2);
		PickUpCustomer puCust = new PickUpCustomer("C", "0413", 0, 0);
	}
	
	@Test (expected = CustomerException.class)
	public void constructPhoneNumberLengthMoreThanTen() throws CustomerException {
		DriverDeliveryCustomer ddCust = new DriverDeliveryCustomer("A", "041104110411", 1, 1);
		DroneDeliveryCustomer drdCust = new DroneDeliveryCustomer("B", "041204120412", 2, 2);
		PickUpCustomer puCust = new PickUpCustomer("C", "041304130413", 0, 0);
	}
	
	@Test
	public void pickUpCustLocationAutoConvertedToZero() {
		assertEquals(0, pickupCust.getLocationX());
		assertEquals(0, pickupCust.getLocationY());
	}
	
	@Test
	public void pickupCustDeliveryDistanceZero() {
		assertEquals(0.0, pickupCust.getDeliveryDistance(), 0.000);
	}
	
	@Test
	public void droneCustomergetDeliveryDistanceReturnEuclideanDistance() {
		assertEquals(5.0, droneDeliveryCust.getDeliveryDistance(), 0.000);
	}
	
	@Test
	public void droneCustNegativeLocationXReturnsDeliveryDistanceCorrectly() throws CustomerException {
		droneDeliveryCust = new DroneDeliveryCustomer("Test", "0412341234", -3, 4);
		assertEquals(5.0, droneDeliveryCust.getDeliveryDistance(), 0.000);
	}
	
	@Test
	public void droneCustNegativeLocationYReturnsDeliveryDistanceCorrectly() throws CustomerException {
		droneDeliveryCust = new DroneDeliveryCustomer("Test", "0412341234", 3, -4);
		assertEquals(5.0, droneDeliveryCust.getDeliveryDistance(), 0.000);
	}
	
	@Test
	public void droneCustNegativeLocationXYReturnsDeliveryDistanceCorrectly() throws CustomerException {
		droneDeliveryCust = new DroneDeliveryCustomer("Test", "0412341234", -3, -4);
		assertEquals(5.0, droneDeliveryCust.getDeliveryDistance(), 0.000);
	}
}
