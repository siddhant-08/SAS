import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class hello {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtEnterItemcode;
	private JTextField txtEnterQuantity;
	private JTextField txtEnterQuantity_1;
	private JTextField textField_1;
	private JTextField txtManufacturer;
	private JTextField textField_2;
	private JTextField txtMrp;
	private JTextField txtMrp_1;
	private JTextField txtCost;
	private JTextField textField_3;
	private JTextField txtName;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField txtType;
	private JButton btnSearch;
	boolean count = false;
	
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/Supermarket";

	   //  Database credentials
	   static final String USER = "root";
	   static String PASS;
	   Connection conn = null;

	/**
	 * Launch the application.
	 */
	public  void update() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 //update_inventory  window = new  update_inventory ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public  hello (String a) {
		PASS=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Update Inventory");
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(51, 77, 352, 56);
		frame.getContentPane().add(panel);
		
		txtEnterQuantity_1 = new JTextField();
		txtEnterQuantity_1.setText("Enter quantity");
		panel.add(txtEnterQuantity_1);
		txtEnterQuantity_1.setColumns(10);
		
		txtEnterQuantity = new JTextField();
		//txtEnterQuantity.setText();
		panel.add(txtEnterQuantity);
		txtEnterQuantity.setColumns(10);
		
		final JLabel lblKg = new JLabel("Kg");
		lblKg.setVisible(false);
		panel.add(lblKg);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setBounds(51, 149, 353, 200);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtMrp_1 = new JTextField();
		txtMrp_1.setText("MRP");
		panel_1.add(txtMrp_1);
		txtMrp_1.setColumns(10);
		
		txtMrp = new JTextField();
		panel_1.add(txtMrp);
		txtMrp.setColumns(10);
		
		txtManufacturer = new JTextField();
		txtManufacturer.setText("Manufacturer");
		panel_1.add(txtManufacturer);
		txtManufacturer.setColumns(10);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		txtCost = new JTextField();
		txtCost.setText("Costprice");
		panel_1.add(txtCost);
		txtCost.setColumns(10);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("Name");
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		textField_5 = new JTextField();
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		txtType = new JTextField();
		txtType.setText("Type");
		panel_1.add(txtType);
		txtType.setColumns(10);
		
		textField_6 = new JTextField();
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener(){

			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			    if(count==true)
			    {
			    	if(txtEnterQuantity.getText().equals("")) JOptionPane.showMessageDialog(null,"Enter something in quantity box");
			    	else if(!isDouble(txtEnterQuantity.getText())) JOptionPane.showMessageDialog(null,"Quantity should be a float value");
			    	else {
			    		
							panel.setVisible(true);
							panel_1.setVisible(true);
						    try {
								conn = DriverManager.getConnection(DB_URL, USER, PASS);

						    	String sql="SELECT *"
						    		+ " FROM Item"
						    		+ " WHERE item_id LIKE '%"+textField.getText()+"%'";
						    				
						    
						    	PreparedStatement ps= conn.prepareStatement(sql,
						    		    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						    ResultSet result=ps.executeQuery();
						    java.sql.ResultSetMetaData rsmd=result.getMetaData();
						    //int columnsNumber=rsmd.getColumnCount();
							while(result.next())
							     {
                                     double X=result.getDouble("quantity")+Double.parseDouble(txtEnterQuantity.getText());
                                     result.updateDouble("quantity", X);
                                     result.updateRow();	
			              	  }
							conn.close();
						    }
						    catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						    frame.dispose();
			    	}
			    }
			    else{
			    	if(textField_5.getText().equals("") || textField_2.getText().equals("") || txtMrp.getText().equals("") || textField_3.getText().equals("")
			    			|| textField_6.getText().equals("") || txtEnterQuantity.getText().equals("")) 
			    		JOptionPane.showMessageDialog(null, "Error !! Empty Boxes");
			    	else if(!(isDouble(txtMrp.getText()) && isDouble(textField_3.getText()) && isDouble(txtEnterQuantity.getText())))
			    			JOptionPane.showMessageDialog(null,"Error MRP,costprice should be float values");
			    	
			    	
			    	else {
			    		try {
							conn = DriverManager.getConnection(DB_URL, USER, PASS);
							String s11="INSERT INTO Item(name,item_id,manufacturer,MRP,cost,discount,type,quantity) "+
					    	           "VALUES(?,?,?,?,?,?,?,?)";
							
							java.sql.PreparedStatement ps=conn.prepareStatement(s11);
							ps.setString(1, textField_5.getText());
							ps.setString(2, textField.getText());
							ps.setString(3, textField_2.getText());
							ps.setDouble(4, Double.parseDouble(txtMrp.getText()));
							ps.setDouble(5, Double.parseDouble(textField_3.getText()));
							ps.setDouble(6, 0.00);
							ps.setString(7, textField_6.getText());
							ps.setDouble(8, Double.parseDouble(txtEnterQuantity.getText()));
							
							ps.executeUpdate();
							conn.close();
							
							
							
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

			    		frame.dispose();
				    				
				    
				  
			    		
			    		
			    	}
			    	
			    	
			    }
			    
			}

		});
				// TODO Auto-generated method stub
				
			
		
		btnUpdate.setBounds(287, 363, 117, 25);
		frame.getContentPane().add(btnUpdate);
		
		textField = new JTextField();
		textField.setBounds(170, 26, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtEnterItemcode = new JTextField();
		txtEnterItemcode.setText("Enter item_code");
		txtEnterItemcode .setEditable(false);
		txtEnterItemcode.setBounds(56, 26, 114, 19);
		frame.getContentPane().add(txtEnterItemcode);
		txtEnterItemcode.setColumns(10);
		
		//btnSearch = new JButton("Search");
		textField.addKeyListener(new KeyListener()
				{

					@Override
					public void keyPressed(KeyEvent arg0) {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
						if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
						if(textField.getText().equals("")) JOptionPane.showMessageDialog(null,"Enter Something");
						else {
							textField.setEditable(false);
							panel.setVisible(true);
							panel_1.setVisible(true);
						    try {
								conn = DriverManager.getConnection(DB_URL, USER, PASS);

						    	String sql="SELECT *"
						    		+ " FROM Item"
						    		+ " WHERE item_id LIKE '%"+textField.getText()+"%'";
						    				
						    
						    PreparedStatement pst = conn.prepareStatement(sql);
						    ResultSet result=pst.executeQuery();
						    java.sql.ResultSetMetaData rsmd=result.getMetaData();
						    //int columnsNumber=rsmd.getColumnCount();
							while(result.next())
							{
                                  count=true; 
                                  
                                  textField_5.setText(result.getString("name"));
                                  textField_2.setText(result.getString("manufacturer"));
                                  txtMrp.setText(String.valueOf(result.getDouble("MRP")));
                                  textField_3.setText(String.valueOf(result.getDouble("cost")));
                                 // textField_4.setText(rsmd.getColumnName(6));
                                  textField_6.setText(result.getString("type"));
                                  if(textField_6.getText().equals("loose")) lblKg.setVisible(true);
                                  textField_5.setEditable(false);
                                  textField_2.setEditable(false);
                                  textField_3.setEditable(false);
                                 // textField_4.setEditable(false);
                                  textField_6.setEditable(false);
                                  txtMrp.setEditable(false);
                                  
                                  
  							}
			
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            

							
						}
						
					}
					}

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
			
				});
		
		
			}
	
	boolean isDouble(String str)
	{
		try{
			Double.parseDouble(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	
}
			
