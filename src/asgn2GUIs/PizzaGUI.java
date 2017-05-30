package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature – as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
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
	private JButton btnLoad;
	private JButton btnFind;
	private JButton btnUnload;
	private JButton btnSwitch;
	private JFileChooser chooser;
	private JTextArea areDisplay;
	private JPanel pnlDisplay;
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
		createGUI();
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		//Get event source
		Object src=evt.getSource();
		//Consider the alternatives - not all active at once.
		if (src==btnLoad) {
			JFileChooser chooser = this.chooser;
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt File", "txt");
	        chooser.setFileFilter(filter);
	        int returnValue = chooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = chooser.getSelectedFile();
		      areDisplay.setText(selectedFile.getName());
		      try{
		    	  BufferedReader br = new BufferedReader((new FileReader(selectedFile.getPath())));
		    	  String line = "";
		    	  String s = "";
		    	  while((line = br.readLine()) != null){
		    		  s += line;
		    	  } 
		    	  areDisplay.setText(s);
		    	  if(br != null)
		    		  br.close();
		      }catch(Exception e){
		    	  JOptionPane.showMessageDialog(null, e.getMessage());
		      }    
	        }
		}
		if (src==btnFind) {

		}
		if (src==btnUnload) {

		}
		if (src==btnSwitch) {
			JButton btn = ((JButton) src);
			areDisplay.setText(null);
		}

	}
	private void createGUI(){
		
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		pnlOne = createPanel(Color.LIGHT_GRAY);
		pnlBtn = createPanel(Color.LIGHT_GRAY);
		pnlThree = createPanel(Color.LIGHT_GRAY);
		pnlFour = createPanel(Color.LIGHT_GRAY);
		pnlFive = createPanel(Color.WHITE);
		pnlDisplay = createPanel(Color.WHITE);
		btnLoad = createButton("Load a LOG file");
		btnFind = createButton("Total Profits Made");
		btnUnload = createButton("Total Distance Travelled");
		btnSwitch = createButton("Reset");
		areDisplay = createTextArea();
		layoutButtonPanel();
		layoutTextAreaPanel();
		pnlDisplay.add(areDisplay);
		chooser = createFileChooser();
		this.getContentPane().add(pnlOne,BorderLayout.PAGE_START);
		this.getContentPane().add(pnlBtn,BorderLayout.PAGE_END);
		this.getContentPane().add(pnlThree,BorderLayout.LINE_START);
		this.getContentPane().add(pnlFour,BorderLayout.LINE_END);
		this.getContentPane().add(pnlDisplay,BorderLayout.CENTER);
		repaint();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setLocation(screenSize.width/2-this.getSize().width/2, screenSize.height/2-this.getSize().height/2);
		pack();
	}
	private JButton createButton(String str){
		JButton var = new JButton(str);
		var.addActionListener(this);
		return var;
	}
	
	private JPanel createPanel(Color c){
		JPanel var = new JPanel();
		var.setBackground(c);
		return var;
	}
	
	private JFileChooser createFileChooser(){
	    JFileChooser var = new JFileChooser(new File("C:\\Users\\GaryVLC\\Desktop\\cab302-asgn2"));
	    var.setDialogTitle("Open a Text File");
	    return var;
	}
	
	private JTextArea createTextArea(){
		JTextArea areDisplay = new JTextArea();
		areDisplay.setEditable(false);
		areDisplay.setLineWrap(true);
		areDisplay.setFont(new Font("Arial",Font.BOLD,24));
		areDisplay.setBorder(BorderFactory.createEtchedBorder());
		return areDisplay;
	}
	
	private void layoutButtonPanel(){
		
		GridBagLayout layout = new GridBagLayout();
		pnlBtn.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		addToPanel(pnlBtn, btnLoad, constraints, 0, 0, 2, 1);
		addToPanel(pnlBtn, btnUnload, constraints, 3, 0, 2, 1);
		addToPanel(pnlBtn, btnFind, constraints, 0, 2, 2, 1);
		addToPanel(pnlBtn, btnSwitch, constraints, 3, 2, 2, 1);
		btnUnload.setMargin(new Insets(10,10,10,10));
		btnLoad.setMargin(new Insets(10,10,10,10));
		btnFind.setMargin(new Insets(10,10,10,10));
		btnSwitch.setMargin(new Insets(10,10,10,10));

	}
	
	private void layoutTextAreaPanel(){
		BorderLayout layout = new BorderLayout();
		pnlDisplay.setLayout(layout);
	}
	
	private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h){
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}
}
