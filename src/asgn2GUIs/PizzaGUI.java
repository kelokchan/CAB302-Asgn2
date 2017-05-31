package asgn2GUIs;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;

import javax.print.attribute.IntegerSyntax;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * This class is the graphical user interface for the rest of the system.
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable
 * and ActionLister. It should contain an instance of an
 * asgn2Restaurant.PizzaRestaurant object which you can use to interact with the
 * rest of the system. You may choose to implement this class as you like,
 * including changing its class signature – as long as it maintains its core
 * responsibility of acting as a GUI for the rest of the system. You can also
 * use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final int WIDTH = screenSize.width * 2 / 3;
	public final int HEIGHT = screenSize.height * 2 / 3;

	private PizzaRestaurant restaurant;
	private JPanel pnlOne;
	private JPanel pnlBtn;
	private JPanel pnlThree;
	private JPanel pnlFour;
	private JPanel pnlFive;
	private JButton btnLog;
	private JButton btnCust;
	private JButton btnOrder;
	private JButton btnReset;
	private JFileChooser chooser;
	private JTextArea areDisplay;
	private JPanel pnlDisplay;
	String[][] array;
	String[] Custcolumns = new String[] { "Customer Name", "Mobile Number", "Customer Type", "Location-X", "Location-Y",
			"Delivery Distance" };
	String[] Ordercolumns = new String[] { "Pizza Type", "Quantity", "Order Price", "Order Cost", "Order Profit" };
	DefaultTableModel custModel;
	private JTable custTable;
	DefaultTableModel OrderModel;
	private JTable OrderTable;
	JScrollPane tablepane;
	File br = null;

	/**
	 * Creates a new Pizza GUI with the specified title
	 * 
	 * @param title
	 *            - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		// TO DO
		super(title);
	}

	@Override
	public void run() {
		// TO DO
		createGUI();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// Get event source
		Object src = evt.getSource();
		// Consider the alternatives - not all active at once.
		if (src == btnLog) {
			JFileChooser chooser = this.chooser;
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt File", "txt");
			chooser.setFileFilter(filter);
			int returnValue = chooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				try {
					br = new File(selectedFile.getPath());
					restaurant = new PizzaRestaurant();
					restaurant.processLog(br.getPath());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		}
		if (src == btnCust) {
			if (br != null) {
				String[][] custlist = new String[restaurant.getNumCustomerOrders()][6];
				for (int i = 0; i < restaurant.getNumCustomerOrders(); i++) {		
					try {
					custlist[i][0] = restaurant.getCustomerByIndex(i).getName();
					custlist[i][1] = restaurant.getCustomerByIndex(i).getMobileNumber();
					custlist[i][2] = restaurant.getCustomerByIndex(i).getCustomerType();
					custlist[i][3] = Integer.toString(restaurant.getCustomerByIndex(i).getLocationX());
					custlist[i][4] = Integer.toString(restaurant.getCustomerByIndex(i).getLocationY());
					custlist[i][5] = String.valueOf((restaurant.getCustomerByIndex(i).getDeliveryDistance()));
					} catch (CustomerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				custModel = new DefaultTableModel(custlist, Custcolumns);
				custTable = new JTable(custModel);
				tablepane.setViewportView(custTable);
			} else {
				JOptionPane.showMessageDialog(this, "No File Selected", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (src == btnOrder) {
			if (br != null) {
				String[][] orderlist = new String[restaurant.getNumPizzaOrders()][5];
				for (int i = 0; i < restaurant.getNumCustomerOrders(); i++) {		

					try {
						orderlist[i][0] = restaurant.getPizzaByIndex(i).getPizzaType();
	
					orderlist[i][1] = Integer.toString(restaurant.getPizzaByIndex(i).getQuantity());
					orderlist[i][2] = String.valueOf(restaurant.getPizzaByIndex(i).getOrderPrice());
					orderlist[i][3] = String.valueOf(restaurant.getPizzaByIndex(i).getOrderCost());
					orderlist[i][4] = String.valueOf(restaurant.getPizzaByIndex(i).getOrderProfit());
					} catch (PizzaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				OrderModel = new DefaultTableModel(orderlist, Ordercolumns);
				OrderTable = new JTable(OrderModel);
				tablepane.setViewportView(OrderTable);
			} else {
				JOptionPane.showMessageDialog(this, "No File Selected", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (src == btnReset) {
			restaurant.resetDetails();
			tablepane.setViewportView(null);
			
		}

	}

	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		pnlOne = createPanel(Color.LIGHT_GRAY);
		pnlBtn = createPanel(Color.LIGHT_GRAY);
		pnlThree = createPanel(Color.LIGHT_GRAY);
		pnlFour = createPanel(Color.LIGHT_GRAY);

		btnLog = createButton("Load a LOG file");
		btnCust = createButton("Customer Details");
		btnOrder = createButton("Order Details");
		btnReset = createButton("Reset");
		tablepane = new JScrollPane(custTable);
		this.getContentPane().add(tablepane, BorderLayout.CENTER);
		layoutButtonPanel();
		chooser = createFileChooser();
		this.getContentPane().add(pnlOne, BorderLayout.PAGE_START);
		this.getContentPane().add(pnlBtn, BorderLayout.PAGE_END);
		this.getContentPane().add(pnlThree, BorderLayout.LINE_START);
		this.getContentPane().add(pnlFour, BorderLayout.LINE_END);
		repaint();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(screenSize.width / 2 - this.getSize().width / 2,
				screenSize.height / 2 - this.getSize().height / 2);
		pack();
	}

	private JButton createButton(String str) {
		JButton var = new JButton(str);
		var.addActionListener(this);
		return var;
	}

	private JPanel createPanel(Color c) {
		JPanel var = new JPanel();
		var.setBackground(c);
		return var;
	}

	private JFileChooser createFileChooser() {
		JFileChooser var = new JFileChooser(new File("C:\\Users\\GaryVLC\\Desktop\\cab302-asgn2\\logs"));
		var.setDialogTitle("Open a Text File");
		return var;
	}

	private JTextArea createTextArea() {
		JTextArea areDisplay = new JTextArea();
		areDisplay.setEditable(false);
		areDisplay.setLineWrap(true);
		areDisplay.setFont(new Font("Arial", Font.BOLD, 24));
		areDisplay.setBorder(BorderFactory.createEtchedBorder());
		return areDisplay;
	}

	private void layoutButtonPanel() {

		GridBagLayout layout = new GridBagLayout();
		pnlBtn.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		addToPanel(pnlBtn, btnLog, constraints, 0, 0, 2, 1);
		addToPanel(pnlBtn, btnOrder, constraints, 3, 0, 2, 1);
		addToPanel(pnlBtn, btnCust, constraints, 0, 2, 2, 1);
		addToPanel(pnlBtn, btnReset, constraints, 3, 2, 2, 1);
		btnOrder.setMargin(new Insets(10, 10, 10, 10));
		btnLog.setMargin(new Insets(10, 10, 10, 10));
		btnCust.setMargin(new Insets(10, 10, 10, 10));
		btnReset.setMargin(new Insets(10, 10, 10, 10));

	}

	private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}
}
