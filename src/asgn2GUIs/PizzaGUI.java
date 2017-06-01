package asgn2GUIs;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.IconView;

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
	/**
	 * To remove the no serialVersionUID set warning
	 */
	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final int WIDTH = screenSize.width * 2 / 3;
	public final int HEIGHT = screenSize.height * 2 / 3;

	private PizzaRestaurant restaurant;
	private JPanel pnlOne;
	private JPanel pnlBtn;
	private JPanel pnlThree;

	private JButton btnLog;
	private JButton btnReset;
	private JButton btnCust;
	private JButton btnOrder;
	private JButton btnCalDist;
	private JButton btnCalProfit;

	private JFileChooser chooser;

	String[] custColumnHeaders = new String[] { "Customer Name", "Mobile Number", "Customer Type", "Location-X",
			"Location-Y", "Delivery Distance" };
	String[] orderColumnHeaders = new String[] { "Pizza Type", "Quantity", "Order Price", "Order Cost",
			"Order Profit" };

	private DefaultTableModel custModel;
	private JTable custTable;
	private DefaultTableModel OrderModel;
	private JTable OrderTable;
	private JScrollPane tablePane;
	private File selectedFile = null;

	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		// TO DO
		super(title);
	}

	@Override
	public void run() {
		// TO DO
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            createGUI();
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// Get event source
		Object src = evt.getSource();

		if (src == btnLog) {
			this.chooser = new JFileChooser();
			this.chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt File", "txt");
			chooser.setFileFilter(filter);

			int returnValue = chooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				selectedFile = chooser.getSelectedFile();

				try {
					selectedFile = new File(selectedFile.getPath());
					restaurant = new PizzaRestaurant();
					if (restaurant.processLog(selectedFile.getPath())) {
						JOptionPane.showMessageDialog(this, "Log file loaded successfully");
						btnLog.setText(selectedFile.getName());
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (src == btnCust) {
			if (selectedFile != null) {
				String[][] custlist = new String[restaurant.getNumCustomerOrders()][6];
				for (int i = 0; i < restaurant.getNumCustomerOrders(); i++) {
					try {
						custlist[i][0] = restaurant.getCustomerByIndex(i).getName();
						custlist[i][1] = restaurant.getCustomerByIndex(i).getMobileNumber();
						custlist[i][2] = restaurant.getCustomerByIndex(i).getCustomerType();
						custlist[i][3] = Integer.toString(restaurant.getCustomerByIndex(i).getLocationX());
						custlist[i][4] = Integer.toString(restaurant.getCustomerByIndex(i).getLocationY());
						custlist[i][5] = String.format("%.2f", restaurant.getCustomerByIndex(i).getDeliveryDistance());
					} catch (CustomerException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				custModel = new DefaultTableModel(custlist, custColumnHeaders) {
					/**
					 * To remove the no serialVersionUID set warning
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false; // Disable cell editing
					}
				};
				custTable = new JTable(custModel);
				tablePane.setViewportView(custTable);
			} else {
				JOptionPane.showMessageDialog(this, "No File Selected", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (src == btnOrder) {
			if (selectedFile != null) {
				String[][] orderlist = new String[restaurant.getNumPizzaOrders()][5];
				for (int i = 0; i < restaurant.getNumCustomerOrders(); i++) {
					try {
						orderlist[i][0] = restaurant.getPizzaByIndex(i).getPizzaType();
						orderlist[i][1] = Integer.toString(restaurant.getPizzaByIndex(i).getQuantity());
						orderlist[i][2] = String.format("%.2f", restaurant.getPizzaByIndex(i).getOrderPrice());
						orderlist[i][3] = String.format("%.2f", restaurant.getPizzaByIndex(i).getOrderCost());
						orderlist[i][4] = String.format("%.2f", restaurant.getPizzaByIndex(i).getOrderProfit());
					} catch (PizzaException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				OrderModel = new DefaultTableModel(orderlist, orderColumnHeaders) {
					/**
					 * To remove the no serialVersionUID set warning
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false; // Disable cell editing
					}
				};
				OrderTable = new JTable(OrderModel);
				tablePane.setViewportView(OrderTable);
			} else {
				JOptionPane.showMessageDialog(this, "No File Selected", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (src == btnReset) {
			restaurant.resetDetails();
			selectedFile = null;
			tablePane.setViewportView(null);
			btnLog.setText("Load a Log File");
		}
		
		if (src == btnCalProfit) {
			if (selectedFile != null)
				JOptionPane.showMessageDialog(this, "Total profie earned: $" + String.format("%.2f", restaurant.getTotalProfit()));
			else
				JOptionPane.showMessageDialog(this, "No File Selected", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if (src == btnCalDist) {
			if (selectedFile != null)
				JOptionPane.showMessageDialog(this, "Total delivery distance: " + String.format("%.2f", restaurant.getTotalDeliveryDistance()));
			else
				JOptionPane.showMessageDialog(this, "No File Selected", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		pnlOne = createPanel(Color.LIGHT_GRAY);
		pnlBtn = createPanel(Color.LIGHT_GRAY);
		pnlThree = createPanel(Color.LIGHT_GRAY);

		btnLog = createButton("Load a Log File");
		btnReset = createButton("Reset");
		btnCust = createButton("Customer Details");
		btnOrder = createButton("Order Details");
		btnCalDist = createButton("Calculate Total Distance");
		btnCalProfit = createButton("Calculate Total Profit");

		tablePane = new JScrollPane(custTable);
		this.getContentPane().add(tablePane, BorderLayout.CENTER);
		layoutButtonPanel();
		this.getContentPane().add(pnlOne, BorderLayout.PAGE_START);
		this.getContentPane().add(pnlBtn, BorderLayout.PAGE_END);
		this.getContentPane().add(pnlThree, BorderLayout.LINE_START);
		repaint();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(screenSize.width / 2 - this.getSize().width / 2,
				screenSize.height / 2 - this.getSize().height / 2);
		pack();
		System.out.println(Integer.valueOf(btnCalProfit.getHeight()));
	}

	private JButton createButton(String str) {
		JButton var = new JButton(str);
		var.addActionListener(this);
		var.setPreferredSize(new Dimension(230, 50));
		return var;
	}

	private JPanel createPanel(Color c) {
		JPanel var = new JPanel();
		var.setBackground(c);
		return var;
	}

	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlBtn.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;

		btnLog.setFont(new Font("Garamond", Font.BOLD, 15));
		btnReset.setFont(new Font("Garamond", Font.BOLD, 15));
		btnOrder.setFont(new Font("Garamond", Font.BOLD, 15));
		btnCust.setFont(new Font("Garamond", Font.BOLD, 15));
		btnCalDist.setFont(new Font("Garamond", Font.BOLD, 15));
		btnCalProfit.setFont(new Font("Garamond", Font.BOLD, 15));
		
		addToPanel(pnlBtn, btnLog, constraints, 0, 0, 2, 1);
		addToPanel(pnlBtn, btnReset, constraints, 0, 2, 2, 1);
		addToPanel(pnlBtn, btnOrder, constraints, 3, 0, 2, 1);
		addToPanel(pnlBtn, btnCust, constraints, 3, 2, 2, 1);
		addToPanel(pnlBtn, btnCalDist, constraints, 5, 0, 2, 1);
		addToPanel(pnlBtn, btnCalProfit, constraints, 5, 2, 2, 1);
		
		btnLog.setMargin(new Insets(10, 10, 10, 10));
		btnReset.setMargin(new Insets(10, 10, 10, 10));
		btnOrder.setMargin(new Insets(10, 10, 10, 10));
		btnCust.setMargin(new Insets(10, 10, 10, 10));
		btnCalDist.setMargin(new Insets(10, 10, 10, 10));
		btnCalProfit.setMargin(new Insets(10, 10, 10, 10));
	}

	private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}
}
