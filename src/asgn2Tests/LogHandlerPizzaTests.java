package asgn2Tests;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Pizza objects in
 * the asgn2Restaurant.LogHander class.
 * 
 * @author Kuok Kit Chan
 * 
 */
public class LogHandlerPizzaTests {

	final static String FILE_1 = Paths.get("logs/20170101.txt").toString();
	final static String FILE_2 = Paths.get("logs/20170102.txt").toString();
	final static String FILE_3 = Paths.get("logs/20170103.txt").toString();
	final static String FILE_4 = Paths.get("logs/Unit_Test/Pizza/Pizza/WrongQuantityFormat.txt").toString();
	final static String FILE_5 = Paths.get("logs/Unit_Test/WrongFormatFile.jpg").toString();
	final static String FILE_6 = Paths.get("logs/Unit_Test/Pizza/QuantityIntOverflow.txt").toString();
	final static String FILE_7 = Paths.get("logs/Unit_Test/Pizza/QuantityIntOverflow2.txt").toString();
	final static String FILE_8 = Paths.get("logs/Unit_Test/Pizza/OverHourLimitAtDeliveryTime.txt").toString();
	final static String FILE_9 = Paths.get("logs/Unit_Test/Pizza/OverMinuteLimitAtDeliveryTime.txt").toString();
	final static String FILE_10 = Paths.get("logs/Unit_Test/Pizza/InvalidFormatAtDeliveryTime.txt").toString();
	final static String FILE_11 = Paths.get("logs/Unit_Test/Pizza/DoubleValueAtDeliveryTime.txt").toString();
	final static String FILE_12 = Paths.get("logs/Unit_Test/Pizza/NegativeValueAtDeliveryTime.txt").toString();
	final static String FILE_13 = Paths.get("logs/Unit_Test/Pizza/OverHourLimitAtOrderTime.txt").toString();
	final static String FILE_14 = Paths.get("logs/Unit_Test/Pizza/InvalidFormatAtOrderTime.txt").toString();
	final static String FILE_15 = Paths.get("logs/Unit_Test/Pizza/DoubleValueAtOrderTime.txt").toString();
	final static String FILE_16 = Paths.get("logs/Unit_Test/Pizza/OverMinuteLimitAtOrderTime").toString();
	final static String FILE_17 = Paths.get("logs/Unit_Test/Pizza/NegativeValueAtOrderTime.txt").toString();
	final static String FILE_18 = Paths.get("logs/Unit_Test/Pizza/DoubleFormatQuantity.txt").toString();
	final static String FILE_19 = Paths.get("logs/Unit_Test/InsufficientData.txt").toString();

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = LogHandlerException.class)
	public void noFilenameGivenWillThrowException() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset("");
	}

	@Test(expected = LogHandlerException.class)
	public void fileNotFoundWillThrowException() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset("UNKNOWNED.txt");
	}

	@Test
	public void correctFileName() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_3);
	}

	@Test(expected = LogHandlerException.class)
	public void wrongQuantityFormatWillThrowException() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_4);
	}

	@Test(expected = LogHandlerException.class)
	public void wrongFileFormatWillThrowException() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_5);
	}

	@Test(expected = LogHandlerException.class)
	public void exceedIntegerLimitQuantityWillThrowException() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_6);
	}

	@Test(expected = LogHandlerException.class)
	public void lessThanIntegerNegativeLimitQuantityWillThrowException() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_7);
	}

	@Test(expected = LogHandlerException.class)
	public void overHourLimitAtDeliveryTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_8);
	}

	@Test(expected = LogHandlerException.class)
	public void overMinuteLimitAtDeliveryTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_9);
	}

	@Test(expected = LogHandlerException.class)
	public void invalidFormatAtDeliveryTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_10);
	}

	@Test(expected = LogHandlerException.class)
	public void doubleValueAtDeliveryTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_11);
	}

	@Test(expected = LogHandlerException.class)
	public void negativeValueAtDeliveryTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_12);
	}

	@Test(expected = LogHandlerException.class)
	public void overHourLimitAtOrderTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_13);
	}

	@Test(expected = LogHandlerException.class)
	public void invalidFormatAtOrderTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_14);
	}

	@Test(expected = LogHandlerException.class)
	public void doubleValueAtOrderTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_15);
	}

	@Test(expected = LogHandlerException.class)
	public void overMinuteLimitAtOrderTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_16);
	}

	@Test(expected = LogHandlerException.class)
	public void negativeValueAtOrderTime() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_17);
	}

	@Test(expected = LogHandlerException.class)
	public void doubleFormatQuantity() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_18);
	}
	
	@Test(expected = LogHandlerException.class)
	public void insufficientDataWillThrowException() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(FILE_19);
	}

	@Test(expected = LogHandlerException.class)
	public void wrongQuantityFormatWillThrowExceptionForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,abc");
	}

	@Test(expected = LogHandlerException.class)
	public void exceedIntegerLimitQuantityWillThrowExceptionForCreatePizza()
			throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,21474836471");
	}

	@Test(expected = LogHandlerException.class)
	public void lessThanIntegerNegativeLimitQuantityWillThrowExceptionForCreatePizza()
			throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,-21474836471");
	}

	@Test(expected = LogHandlerException.class)
	public void doubleFormatQuantityWillThrowExceptionForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2.50");
	}

	@Test(expected = LogHandlerException.class)
	public void overHourLimitAtDeliveryTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,159:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void overMinuteLimitAtDeliveryTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,19:75:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void invalidFormatAtDeliveryTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,asdasd,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void doubleValueAtDeliveryTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,19.50,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void negativeValueAtDeliveryTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:00:00,-19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void overHourLimitAtOrderTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("305:00:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void invalidFormatAtOrderTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("asdasd,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void doubleValueAtOrderTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19.50:00:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void overMinuteLimitAtOrderTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("19:80:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}

	@Test(expected = LogHandlerException.class)
	public void negativeValueAtOrderTimeForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("-19:00:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}
	
	@Test(expected = LogHandlerException.class)
	public void insufficientDataWillThrowExceptionForCreatePizza() throws PizzaException, LogHandlerException {
		LogHandler.createPizza("-19:00:00,19:20:00,Casey Jones,0123456789,DVC,10,5,PZV,2");
	}
}
