package asgn2Tests;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Person A
 */
public class LogHandlerCustomerTests {
	// TO DO
	
	final static String FILE_1 = Paths.get("logs/20170101.txt").toString();
	final static String FILE_2 = Paths.get("logs/20170102.txt").toString();
	final static String FILE_3 = Paths.get("logs/20170103.txt").toString();
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test (expected = LogHandlerException.class)
	public void noFilenameGivenWillThrowException() throws FileNotFoundException, CustomerException, LogHandlerException {
		ArrayList<Customer> customerList = LogHandler.populateCustomerDataset("");
	} 
	
	@Test (expected = LogHandlerException.class)
	public void fileNotFoundWillThrowException() throws FileNotFoundException, CustomerException, LogHandlerException {
		ArrayList<Customer> customerList = LogHandler.populateCustomerDataset("UNKNOWNED.txt");
	} 
}
