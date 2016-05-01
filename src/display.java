import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class display {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextArea txtNoteRestAll;
	private JTextField textField_8;

	private JButton btnFire ;
	private JButton btnUpdate;
	//private JTable table;
	int choice;
	
	
	  /* static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/Supermarket";

	   //  Database credentials
	   static final String USER = "grp12";
	   static final String PASS = "smrutisikha";
	   */
	   Connection conn = null;
	   private JTextField textField_9;
	   private JTextField textField_10;
	   private JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public void work() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//display window = new display();
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
	public display(Connection asd,int a) {
		choice=a;
		conn=asd;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Manager workspace");
		frame.setBounds(100, 100, 750, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 260, 360);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder ( (Border) new TitledBorder ( new EtchedBorder (), "Update prices" ) );

		if(choice==0) panel.setVisible(true);
		else panel.setVisible(false);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						Class.forName("com.mysql.jdbc.Driver");
						//conn = DriverManager.getConnection(DB_URL, USER, PASS);
						String sql="SELECT *"
					    		+ " FROM Item"
					    		+ " WHERE item_id LIKE '%"+textField.getText()+"%' ";
						PreparedStatement pst = conn.prepareStatement(sql);
					    ResultSet result=pst.executeQuery();
					    java.sql.ResultSetMetaData rsmd=result.getMetaData();
					    boolean count=false;
					    
					    while(result.next())
						{
							count=true;
							btnFire.setVisible(true);
							btnUpdate.setVisible(true);
							textField.setEditable(false);
							//textField_9.setText("Employee name:  "+result.getString("name")+"\n Employee_id:  "+result.getString("empl_id")+"\n Employee mobile:  "+result.getString("mobile")+"\n Employee email_id:  "+result.getString("email"));
							textField_1.setText(String.valueOf(result.getDouble("MRP")));
							textField_2.setText(String.valueOf(result.getDouble("cost")));
							textField_3.setText(String.valueOf(result.getDouble("discount")));
							textField_4.setText(result.getString("name"));
							 //display.setText(" COURSENAME: "+rect.setup[choice].coursename+"\n COURSEFEE: Rs "+rect.setup[choice].coursefee+"\n COURSEDATE(yyyy/mm/dd): "+rect.setup[choice].date+"\n DURATION: "+rect.setup[choice].duration+" days\n");
						}
					    if(count==false) {
					    	JOptionPane.showMessageDialog(null,"Error !! enter valid item_id");
					    }
					   // conn.close();
					   // frame.dispose();
					    				
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		textField.setBounds(134, 26, 114, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterItemcode = new JLabel("Enter \n item_code");
		lblEnterItemcode.setBounds(12, 28, 163, 15);
		panel.add(lblEnterItemcode);
		
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 110, 114, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMrp = new JLabel("MRP");
		lblMrp.setBounds(86, 112, 70, 15);
		panel.add(lblMrp);
		
		textField_2 = new JTextField();
		textField_2.setBounds(134, 141, 114, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCostPrice = new JLabel("Cost price");
		lblCostPrice.setBounds(55, 143, 100, 15);
		panel.add(lblCostPrice);
		
		textField_3 = new JTextField();
		textField_3.setBounds(134, 172, 114, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(55, 170, 70, 15);
		panel.add(lblDiscount);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(134, 58, 114, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblItemName = new JLabel("Item name");
		lblItemName.setBounds(22, 57, 113, 22);
		panel.add(lblItemName);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setVisible(false);
		btnUpdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(textField_3.getText().equals("") || textField_2.getText().equals("") || textField_1.getText().equals("")) 
					JOptionPane.showMessageDialog(null, "ERROR empty boxes");
				else if(!(isDouble(textField_3.getText()) && (isDouble(textField_1.getText()) && isDouble(textField_2.getText()))))
					JOptionPane.showMessageDialog(null, "ERROR MRP,cost,discount should be float values");
				else{
				try{
					Class.forName("com.mysql.jdbc.Driver");
				//conn = DriverManager.getConnection(DB_URL, USER, PASS);

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
                   //  double X=result.getDouble("quantity")+Double.parseDouble(txtEnterQuantity.getText());
                     result.updateDouble("discount", Double.parseDouble(textField_3.getText()) );
                     result.updateDouble("MRP", Double.parseDouble(textField_1.getText()));
                     result.updateDouble("cost", Double.parseDouble(textField_2.getText()));
                     result.updateRow();	
          	  }
		    conn.close();
			frame.dispose();
			}
				 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
	      }
			
		});
		btnUpdate.setBounds(134, 323, 117, 25);
		panel.add(btnUpdate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(272, 0, 251, 360);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		if(choice==1) panel_1.setVisible(true);
		else panel_1.setVisible(false);
		panel_1.setBorder ( (Border) new TitledBorder ( new EtchedBorder (), "Appoint Employee" ) );

		
		JLabel lblEnterDetailsOf = new JLabel("Enter details of new employee");
		lblEnterDetailsOf.setBounds(12, 43, 221, 15);
		panel_1.add(lblEnterDetailsOf);
		
		textField_5 = new JTextField();
		textField_5.setBounds(131, 70, 114, 19);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblEmployeename = new JLabel("Employee_name");
		lblEmployeename.setBounds(12, 70, 144, 19);
		panel_1.add(lblEmployeename);
		
		JLabel lblGiveEmplid = new JLabel("Give empl_id");
		lblGiveEmplid.setBounds(12, 108, 114, 15);
		panel_1.add(lblGiveEmplid);
		
		textField_6 = new JTextField();
		textField_6.setBounds(131, 106, 114, 19);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(131, 137, 114, 19);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblTemporary = new JLabel("Temporary");
		lblTemporary.setBounds(22, 139, 104, 15);
		panel_1.add(lblTemporary);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(32, 156, 81, 15);
		panel_1.add(lblPassword);
		
		final JRadioButton rdbtnSalesclerk = new JRadioButton("Sales_clerk");
		rdbtnSalesclerk.setBounds(128, 175, 123, 23);
		panel_1.add(rdbtnSalesclerk);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(textField_5.getText().equals("") || textField_6.getText().equals("") || textField_7.getText().equals("") )
					JOptionPane.showMessageDialog(null, "Error !! Empty Boxes");
				else{
					try {
						String sql= "SELECT* FROM Employee WHERE empl_id = 'textField_6.getText()' ";
						java.sql.PreparedStatement ps_check=conn.prepareStatement(sql);
						ResultSet result=ps_check.executeQuery();
					    java.sql.ResultSetMetaData rsmd=result.getMetaData();
					    int x=0;
					    while(result.next()){
					    	x++;
					    }
					    if(x!=0){
					    	//System.out.println(x);
					    	JOptionPane.showMessageDialog(null, "Employee ID already exists");
					    	//conn.close();
					    }else{
					    	Class.forName("com.mysql.jdbc.Driver");
							//conn = DriverManager.getConnection(DB_URL, USER, PASS);
							String s11="INSERT INTO Employee(ment,name,empl_id,email,mobile,sales_clerk,password) "+
					    	           "VALUES(?,?,?,?,?,?,?)";
					    	java.sql.PreparedStatement ps_1=conn.prepareStatement(s11);
					    	ps_1.setString(1, "employee");
					    	ps_1.setString(2,textField_5.getText());
					    	ps_1.setString(3, textField_6.getText());
					    	ps_1.setString(4, null);
					    	ps_1.setString(5, null);
					    	if(rdbtnSalesclerk.isSelected()) ps_1.setString(6, "true");
					    	else ps_1.setString(6, "false");
					    	ps_1.setString(7, new cryptWithMD5().cryptWithMD5(textField_7.getText()));
					    	ps_1.executeUpdate();
					    	conn.close();
					    }				    	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					frame.dispose();
					
					
				}
			}
		});
		btnAdd.setBounds(128, 323, 117, 25);
		panel_1.add(btnAdd);
		
		JLabel lblRestAllValues = new JLabel("Rest all values have been given");
		lblRestAllValues.setBounds(12, 219, 233, 15);
		panel_1.add(lblRestAllValues);
		
		JLabel lblDefaultValueNull = new JLabel("Default value NULL");
		lblDefaultValueNull.setBounds(12, 235, 233, 15);
		panel_1.add(lblDefaultValueNull);
		
		
		
		JLabel lblSalesclerk = new JLabel("Sales_clerk?");
		lblSalesclerk.setBounds(32, 177, 116, 19);
		panel_1.add(lblSalesclerk);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(522, 0, 216, 360);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		if(choice==2) panel_2.setVisible(true);
		else panel_2.setVisible(false);
		panel_2.setBorder ( (Border) new TitledBorder ( new EtchedBorder (), "Fire Employee" ) );

		
		textField_8 = new JTextField();
		textField_8.addKeyListener(new KeyListener()
				{

					@Override
					public void keyPressed(KeyEvent arg0) {
						// TODO Auto-generated method stub
						if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
						{
							try {
								Class.forName("com.mysql.jdbc.Driver");
								//conn = DriverManager.getConnection(DB_URL, USER, PASS);
								String sql="SELECT *"
							    		+ " FROM Employee"
							    		+ " WHERE empl_id LIKE '%"+textField_8.getText()+"%' ";
								PreparedStatement pst = conn.prepareStatement(sql);
							    ResultSet result=pst.executeQuery();
							    java.sql.ResultSetMetaData rsmd=result.getMetaData();
							    int x=0;
							    
							    while(result.next())
								{
									x++;
									textField_8.setEditable(false);
									btnFire.setVisible(true);
									//textField_9.setText("Employee name:  "+result.getString("name")+"\n Employee_id:  "+result.getString("empl_id")+"\n Employee mobile:  "+result.getString("mobile")+"\n Employee email_id:  "+result.getString("email"));
									textField_9.setText(result.getString("name"));
									textField_10.setText(result.getString("empl_id"));
									textField_11.setText(result.getString("email"));
									
									 //display.setText(" COURSENAME: "+rect.setup[choice].coursename+"\n COURSEFEE: Rs "+rect.setup[choice].coursefee+"\n COURSEDATE(yyyy/mm/dd): "+rect.setup[choice].date+"\n DURATION: "+rect.setup[choice].duration+" days\n");
								}
							    if(x==0) {
							    	JOptionPane.showMessageDialog(null,"Error !! enter valid empl_id");
							    }
							    //else{ 
							    //conn.close();
							    //	frame.dispose();
							    //}
							    				
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
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
		textField_8.setBounds(90, 44, 114, 19);
		panel_2.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblEmplid = new JLabel("empl_id");
		lblEmplid.setBounds(12, 46, 70, 15);
		panel_2.add(lblEmplid);
		btnFire= new JButton("Fire");
		btnFire.setVisible(false);
		btnFire.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.jdbc.Driver");
					//conn = DriverManager.getConnection(DB_URL, USER, PASS);
					String sql="DELETE "
				    		+ " FROM Employee"
				    		+ " WHERE empl_id LIKE '%"+textField_8.getText()+"%'";
				    				
				    Statement stmt = conn.createStatement();
				    stmt.executeUpdate(sql);
				    	
				    //java.sql.ResultSetMetaData rsmd=result.getMetaData();
				    //int columnsNumber=rsmd.getColumnCount();
					
					//conn.close();
					frame.dispose();
						
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    	
				
			}
			
		});
		
		
		
		btnFire.setBounds(87, 323, 117, 25);
		panel_2.add(btnFire);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBounds(90, 112, 114, 19);
		panel_2.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(39, 114, 70, 15);
		panel_2.add(lblName);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setBounds(90, 140, 114, 19);
		panel_2.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(39, 143, 70, 15);
		panel_2.add(lblMobile);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBounds(90, 171, 114, 19);
		panel_2.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(39, 171, 70, 15);
		panel_2.add(lblEmail);
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