import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnLogin;
	private JPanel panel;
	private JComboBox comboBox;
	int choice;
	boolean check=false;
	private String scName;
	private JTextField txtChoiceForSalesclerk;
	cryptWithMD5 goku=new cryptWithMD5();
	employee emp;
	Manager man;
	
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/Supermarket";

	   //  Database credentials
	   static final String USER = "root";
	   static String PASS ;
	   Connection conn = null;
	   private JTextField textField_4;
	   private JTextField textField_5;
	   private JPanel panel_2;
	   private JButton btnPasswordChange;
	   private JPasswordField passwordField;
	   private JLabel lblOldPass;
	   private JPasswordField passwordField_1;
	   private JLabel lblNewPass;
	   private JPasswordField passwordField_2;
	   private JLabel lblTypeNewPass;
	   private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public  void log() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//login window = new login();
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
	public login(int a,String pass) {
		PASS=pass;
		choice=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login Portal");
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(232, 38, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(232, 75, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField("Login ID");
		textField_2.setEditable(false);
		textField_2.setBounds(68, 38, 114, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField("PassWord");
		textField_3.setEditable(false);
		textField_3.setBounds(68, 75, 114, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub {
				// TODO Auto-generated method stub
			    //check left out !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!check check check
				if(textField.getText().equals("") || textField_1.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Error !! Empty Boxes !!");
				else {
				String s1="manager";
				String s2="employee";
				String s3="true";
				String s4="false";
				boolean count=false;
				
				try {
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				    if(choice ==1 )
				    {
				    	String sql="SELECT *"
				    		+ " FROM Employee"
				    		+ " WHERE empl_id LIKE '%"+textField.getText()+"%' "
				    				+ "AND password LIKE"
				    		+ " '%"+goku.cryptWithMD5(textField_1.getText())+"%'"
				    				+ "AND ment LIKE '%"+s2+"%'"
				    						+ "AND sales_clerk LIKE '%"+s4+"%'";
				    
				    PreparedStatement pst = conn.prepareStatement(sql);
				    ResultSet result=pst.executeQuery();
				    java.sql.ResultSetMetaData rsmd=result.getMetaData();
				   // int columnsNumber=rsmd.getColumnCount();
					while(result.next())
					{
						count=true;
					}
				    }
				    else if(choice==0)
				    {
				    	//System.out.println("hello");
				    	//System.out.println(goku.cryptWithMD5("pra123")+" "+goku.cryptWithMD5(textField_1.getText()));
				    	String sql="SELECT *"
					    		+ " FROM Employee"
					    		+ " WHERE empl_id LIKE '%"+textField.getText()+"%' "
					    				+ "AND password LIKE"
					    		+ " '%"+goku.cryptWithMD5(textField_1.getText())+"%'"
					    				+ "AND ment LIKE '%"+s1+"%'";
					    
					    PreparedStatement pst = conn.prepareStatement(sql);
					    ResultSet result=pst.executeQuery();
					    java.sql.ResultSetMetaData rsmd=result.getMetaData();
					   // int columnsNumber=rsmd.getColumnCount();
						while(result.next())
						{
							count=true;
						}
						
						
				    }
				    else if(choice==2)
					{
				    	String sql="SELECT *"
					    		+ " FROM Employee"
					    		+ " WHERE empl_id LIKE '%"+textField.getText()+"%' "
					    				+ "AND password LIKE"
					    		+ " '%"+goku.cryptWithMD5(textField_1.getText())+"%'"
					    				+ "AND ment LIKE '%"+s2+"%'"
					    						+ "AND sales_clerk LIKE '%"+s3+"%'";
					    
					    PreparedStatement pst = conn.prepareStatement(sql);
					    ResultSet result=pst.executeQuery();
					    java.sql.ResultSetMetaData rsmd=result.getMetaData();
					   // int columnsNumber=rsmd.getColumnCount();
						while(result.next())
						{
							scName=result.getString("name");
							count=true;
						}
						
					}
				    
				    conn.close();
			    
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				if(count==false) {
					JOptionPane.showMessageDialog(null, "Error Employee_id and Password mismatch");
				}
				else{
				if(choice==0) {
					     mgrscreen x=new mgrscreen(PASS);
				         x.mgr();
			      }
				else if((choice==1) || choice==2 && comboBox.getSelectedIndex()==0) {
					hello y=new hello(PASS);
					y.update();
					     
				}
				else {
					Transaction x= new Transaction(scName,PASS);
				}
				//frame.dispose();
				 
			  }
			
		   }
		}

		});	
				
		
		btnLogin.setBounds(300, 141, 117, 25);
		frame.getContentPane().add(btnLogin);
		
		panel = new JPanel();
		if(choice==2) panel.setVisible(true);
		else panel.setVisible(false);
		panel.setBounds(68, 206, 220, 90);
		panel.setBackground(Color.cyan);
		frame.getContentPane().add(panel);
		
		txtChoiceForSalesclerk = new JTextField();
		txtChoiceForSalesclerk.setText("Choice for sales_clerk");
		panel.add(txtChoiceForSalesclerk);
		txtChoiceForSalesclerk.setColumns(15);
		
		comboBox = new JComboBox();
		panel.add(comboBox);
		
		comboBox.addItem("Update Inventory");
		comboBox.addItem("Transaction");
		
		final JPanel panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setBounds(12, 210, 426, 178);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setBounds(100, 5, 114, 19);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(12, 7, 70, 15);
		panel_1.add(lblMobile);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(245, 7, 70, 15);
		panel_1.add(lblEmail);
		
		textField_5 = new JTextField();
		textField_5.setBounds(290, 5, 114, 19);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBounds(126, 35, 278, 115);
		panel_2.setVisible(false);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(162, 12, 104, 19);
		panel_2.add(passwordField);
		
		lblOldPass = new JLabel("Old pass");
		lblOldPass.setBounds(65, 14, 70, 15);
		panel_2.add(lblOldPass);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(162, 43, 104, 19);
		panel_2.add(passwordField_1);
		
		lblNewPass = new JLabel("New pass");
		lblNewPass.setBounds(65, 41, 70, 15);
		panel_2.add(lblNewPass);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(162, 74, 104, 19);
		panel_2.add(passwordField_2);
		
		lblTypeNewPass = new JLabel("Type new pass again");
		lblTypeNewPass.setBounds(12, 76, 150, 15);
		panel_2.add(lblTypeNewPass);
		
		btnPasswordChange = new JButton("Chng_pass");
		btnPasswordChange.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				check=true;
				panel_2.setVisible(true);
			}
		
		});
		btnPasswordChange.setBounds(0, 36, 114, 25);
		panel_1.add(btnPasswordChange);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println(textField_4.getText());
				if(check==true && ( passwordField.getText().equals("") ||passwordField_1.getText().equals("") || passwordField_2.getText().equals("")) )
					JOptionPane.showMessageDialog(null,"Error !! Empty boxes");
				else if(!textField_4.getText().matches("\\d+")) JOptionPane.showMessageDialog(null,"Error !! Invalid mobile no.");
				else if(!textField_5.getText().contains("@") || !textField_5.getText().contains(".com")) JOptionPane.showMessageDialog(null,"Error !! Invalid email");
				else if(check==true && !(passwordField_1.getText().equals(passwordField_2.getText())))
				{
					JOptionPane.showMessageDialog(null,"Error !! Password mismatch !!");
					passwordField_1.setText("");
					passwordField_2.setText("");
					
				}
				    
				else {
					try {
				
						conn = DriverManager.getConnection(DB_URL, USER, PASS);

				    	String sql="SELECT *"
				    		+ " FROM Employee"
				    		+ " WHERE empl_id LIKE '%"+textField.getText()+"%'";
				    				
				    
				    PreparedStatement ps= conn.prepareStatement(sql,
				    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				    ResultSet result=ps.executeQuery();
				    java.sql.ResultSetMetaData rsmd=result.getMetaData();
				    //int columnsNumber=rsmd.getColumnCount();
					while(result.next())
					     {
                          
                          result.updateString("mobile", textField_4.getText());
                          result.updateString("email",textField_5.getText());
                          if(check==true) result.updateString("password", goku.cryptWithMD5(passwordField_2.getText()));
                          result.updateRow();	
	              	  }
					conn.close();
				
				
			       }
				 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textField_4.setText("");
					check=false;
					textField_5.setText("");
					passwordField.setText("");
					passwordField_1.setText("");
					passwordField_2.setText("");
					textField_1.setText("");
					panel_2.setVisible(false);
					panel_1.setVisible(false);
					
					
					
				}
			}
		});
		btnSave.setBounds(0, 85, 117, 25);
		panel_1.add(btnSave);
		
		JButton btnChangeSeldDetails = new JButton("Change Details");
		btnChangeSeldDetails.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(textField.getText().equals("") || textField_1.getText().equals("")) JOptionPane.showMessageDialog(null, "Error !! Empty Boxes !!");
				else {
					
					try{
						String s1="manager";
						String s2="employee";
						String s3="true";
						String s4="false";
						boolean count = false;
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				    if(choice ==1 )
				    {
				    	String sql="SELECT *"
				    		+ " FROM Employee"
				    		+ " WHERE empl_id LIKE '%"+textField.getText()+"%' "
				    				+ "AND password LIKE"
				    		+ " '%"+goku.cryptWithMD5(textField_1.getText())+"%'"
				    				+ "AND ment LIKE '%"+s2+"%'"
				    						+ "AND sales_clerk LIKE '%"+s4+"%'";
				    
				    PreparedStatement pst = conn.prepareStatement(sql);
				    ResultSet result=pst.executeQuery();
				    java.sql.ResultSetMetaData rsmd=result.getMetaData();
				   // int columnsNumber=rsmd.getColumnCount();
					while(result.next())
					{
						count=true;
						textField_4.setText(result.getString("mobile"));
						textField_5.setText(result.getString("email"));
						emp=new employee(result.getString("name"),result.getString("empl_id"),result.getString("email")
								,result.getString("mobile"),false,result.getString("password"));
						panel_1.setVisible(true);
					}
				    }
				    else if(choice==0)
				    {
				    	String sql="SELECT *"
					    		+ " FROM Employee"
					    		+ " WHERE empl_id LIKE '%"+textField.getText()+"%' "
					    				+ "AND password LIKE"
					    		+ " '%"+goku.cryptWithMD5(textField_1.getText())+"%'"
					    				+ "AND ment LIKE '%"+s1+"%'";
					    
					    PreparedStatement pst = conn.prepareStatement(sql);
					    ResultSet result=pst.executeQuery();
					    java.sql.ResultSetMetaData rsmd=result.getMetaData();
					   // int columnsNumber=rsmd.getColumnCount();
						while(result.next())
						{
							count=true;
							textField_4.setText(result.getString("mobile"));
							textField_5.setText(result.getString("email"));
							man=new Manager(result.getString("name"),result.getString("empl_id"),result.getString("email")
									,result.getString("mobile"),false,result.getString("password"));
							panel_1.setVisible(true);
							
						}
						
						
				    }
				    else if(choice==2)
					{
				    	String sql="SELECT *"
					    		+ " FROM Employee"
					    		+ " WHERE empl_id LIKE '%"+textField.getText()+"%' "
					    				+ "AND password LIKE"
					    		+ " '%"+goku.cryptWithMD5(textField_1.getText())+"%'"
					    				+ "AND ment LIKE '%"+s2+"%'"
					    						+ "AND sales_clerk LIKE '%"+s3+"%'";
					    
					    PreparedStatement pst = conn.prepareStatement(sql);
					    ResultSet result=pst.executeQuery();
					    java.sql.ResultSetMetaData rsmd=result.getMetaData();
					   // int columnsNumber=rsmd.getColumnCount();
						while(result.next())
						{
							count=true;
							textField_4.setText(result.getString("mobile"));
							textField_5.setText(result.getString("email"));
							emp=new employee(result.getString("name"),result.getString("empl_id"),result.getString("email")
									,result.getString("mobile"),true,result.getString("password"));
							panel_1.setVisible(true);
						}
						
					}
				    
				    if(count==false) {
						JOptionPane.showMessageDialog(null, "Error Employee_id and Password mismatch");
					}
						
						
					
				    
				    conn.close();
			    
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
					
				}
				
			}
			
			
		});
		btnChangeSeldDetails.setBounds(30, 140, 175, 25);
		frame.getContentPane().add(btnChangeSeldDetails);
		frame.getContentPane().setBackground(Color.cyan);
		JLabel lblTypeInYour = new JLabel("Type in your employee_id and password !!");
		lblTypeInYour.setBounds(68, 11, 328, 15);
		frame.getContentPane().add(lblTypeInYour);
		//comboBox.addItem(new ComboItem("Visible String 3", "Value 3"));

		
		
	}

	private Component ActionListener() {
		// TODO Auto-generated method stub
		return null;
	}
}