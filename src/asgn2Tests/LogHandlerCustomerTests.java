package asgn2Tests;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Lee Chun Voo
 */
public class LogHandlerCustomerTests {
	
	
	final static String FILE_1 = Paths.get("logs/20170101.txt").toString();
	final static String FILE_2 = Paths.get("logs/20170102.txt").toString();
	final static String FILE_3 = Paths.get("logs/20170103.txt").toString();
	final static String FILE_4 = Paths.get("logs/Unit_Test/Customer/WrongTimeFormat.txt").toString();
	final static String FILE_5 = Paths.get("logs/Unit_Test/Customer/InvalidLocationXFormat.txt").toString();
	final static String FILE_6 = Paths.get("logs/Unit_Test/Customer/InvalidLocationYFormat.txt").toString();
	final static String FILE_7 = Paths.get("logs/Unit_Test/WrongFormatFile.jpg").toString();
	final static String FILE_8 = Paths.get("logs/Unit_Test/Customer/LocationXOverflow2.txt").toString();
	final static String FILE_9 = Paths.get("logs/Unit_Test/Customer/LocationYOverflow2.txt").toString();
	final static String FILE_10 = Paths.get("logs/Unit_Test/Customer/LocationYOverflow.txt").toString();
	final static String FILE_11 = Paths.get("logs/Unit_Test/Customer/LocationXOverflow.jpg").toString();
	final static String FILE_12 = Paths.get("logs/Unit_Test/Customer/DoubleFormatLocationY.txt").toString();
	final static String FILE_13 = Paths.get("logs/Unit_Test/Customer/DoubleFormatLocationX.jpg").toString();
	final static String FILE_14 = Paths.get("logs/Unit_Test/InsufficientData.txt").toString();

	@Test (expected = LogHandlerException.class)
	public void noFilenameGivenWillThrowException() throws CustomerException, LogHandlerException {
		LogHandler.populateCustomerDataset("");
	} 
	
	@Test (expected = LogHandlerException.class)
	public void fileNotFoundWillThrowException() throws CustomerException, LogHandlerException {
		LogHandler.populateCustomerDataset("UNKNOWNED.txt");
	} 
	
	@Test
	public void correctFileName()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_3);
	}
	
	@Test (expected = LogHandlerException.class)
	public void wrongLocationXFormatWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_5);
	}
	
	@Test (expected = LogHandlerException.class)
	public void wrongLocationYFormatWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_6);
	}
	
	@Test (expected = LogHandlerException.class)
	public void wrongFileFormatWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_7);
	}
	
	@Test (expected = LogHandlerException.class)
	public void lessThanIntegerNegativeLimitLocationXWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_8);
	}
	
	@Test (expected = LogHandlerException.class)
	public void lessThanIntegerNegativeLimitLocationYWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_9);
	}

	@Test (expected = LogHandlerException.class)
	public void exceedIntegerLimitLocationYWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_10);
	}
	
	@Test (expected = LogHandlerException.class)
	public void exceedIntegerLimitLocationXWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_11);
	}
	
	@Test (expected = LogHandlerException.class)
	public void doubleFormatLocationYWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_12);
	}
	
	@Test (expected = LogHandlerException.class)
	public void doubleValueLocationYWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_13);
	}
	
	@Test (expected = LogHandlerException.class)
	public void insufficientDataWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(FILE_14);
	}
	
	@Test 
	public void correctCreateCustomer()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingDataWillThrowException()throws LogHandlerException, CustomerException{
		LogHandler.createCustomer("19:00:00,19:20:00,0123456789,DVC,5,5,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void invalidLocationXFormatWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,TEST,5,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void invalidLocationYFormatWillThrowException()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,TEST,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void exceedIntegerLimitLocationXWillThrowExceptionForCreateCustomer()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,0123456789,DVC,21474836471,5,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void exceedIntegerLimitLocationYWillThrowExceptionForCreateCustomer()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,0123456789,DVC,5,21474836471,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void lessThanIntegerNegativeLimitLocationXWillThrowExceptionForCreateCustomer()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,0123456789,DVC,-21474836471,5,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void lessThanIntegerNegativeLimitLocationYWillThrowExceptionForCreateCustomer()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,0123456789,DVC,5,-21474836471,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void doubleValueLocationYWillThrowExceptionForCreateCustomer()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,0123456789,DVC,5.5,5,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void doubleFormatLocationYWillThrowExceptionForCreateCustomer()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00,0123456789,DVC,5,5.5,PZV,2");
	}
	
	@Test (expected = LogHandlerException.class)
	public void insufficientDataWillThrowExceptionForCreateCustomer()throws CustomerException, LogHandlerException{
		LogHandler.createCustomer("19:00:00,19:20:00");
	}

}
