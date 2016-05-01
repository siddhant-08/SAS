import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.Statement;

import java.awt.FlowLayout;

public class mgrscreen {

	private JFrame frame;
	DefaultTableModel model;
	//private JTable table;
    static JTable table;
    JPanel panel;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/Supermarket";

	   //  Database credentials
	   static final String USER = "root";
	   static  String PASS;
	   Connection conn = null;

	String[] columnNames = {"Item name", "Item_id", "Manufacturer","MRP","cost","discount", "Quantity"};
	private JTextField txtEnterItemid;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void mgr() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//mgrscreen window = new mgrscreen();
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
	public mgrscreen(String a) {
		PASS=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Manager Portal");
		frame.setResizable(false);
		frame.setBounds(100, 100, 710, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnInventoryCheck = new JButton("Inventory check");
		btnInventoryCheck.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
					
					 
					 
					 panel.setVisible(true);
					 //conn.close();
					 
				
			   
			    		
			}
			
			
			
		});
		btnInventoryCheck.setBounds(25, 50, 175, 35);
		frame.getContentPane().add(btnInventoryCheck);
		
		JButton btnStatisticsCheck = new JButton("Statistics");
		btnStatisticsCheck.setBounds(250, 50, 175, 35);
		btnStatisticsCheck.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 Statistics s =new Statistics(PASS);
			}
			
        });
		frame.getContentPane().add(btnStatisticsCheck);
		frame.getContentPane().setBackground(Color.PINK);
		JButton btnChangePrice = new JButton("Change price");
		btnChangePrice.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 try {
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					display x=new display(conn,0);
					x.work();
					
					/*String s1="1203456789";
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					String sql="SELECT *"
				    		+ " FROM Item"
				    		+ " WHERE item_id LIKE '%"+s1+"%' ";
				    				
				    
				    PreparedStatement pst = conn.prepareStatement(sql);
				    ResultSet result=pst.executeQuery();
				    java.sql.ResultSetMetaData rsmd=result.getMetaData();
				   // int columnsNumber=rsmd.getColumnCount();
					while(result.next())
					{
						System.out.print(result.getDouble("cost"));
					}
					
					conn.close();*/
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
        });
		btnChangePrice.setBounds(470, 50, 175, 35);
		frame.getContentPane().add(btnChangePrice);
		
		JButton btnAppointEmpl = new JButton("Appoint employee");
		btnAppointEmpl.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 try {
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					display x=new display(conn,1);
					x.work();
					//conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
      });
		btnAppointEmpl.setBounds(25, 100, 175, 35);
		frame.getContentPane().add(btnAppointEmpl);
		
		JButton btnFireEmpl = new JButton("Fire employee");
		btnFireEmpl.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 try {
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					display x=new display(conn,2);
					x.work();
					//conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
			
		});
		btnFireEmpl.setBounds(250, 100, 175, 35);
		frame.getContentPane().add(btnFireEmpl);
		
		panel = new JPanel();
		panel.setBounds(50, 200, 680, 430);
		frame.getContentPane().add(panel);
		
		
		model = new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		    //model.isCellEditable(row, column);

	        model.setColumnIdentifiers(columnNames);
	        
	        table = new JTable();
	        table.setModel(model);
	        /*TableColumn column = null;
	        for (int i = 1; i < 8; i++) {
	            column = table.getColumnModel().getColumn(i);
	            if (i <=3) {
	                column.setPreferredWidth(100); //third column is bigger
	            } else {
	                column.setPreferredWidth(50);
	            }
	        }*/

	        

	        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

	        table.setFillsViewportHeight(true);

	        JScrollPane scroll = new JScrollPane(table);

	        scroll.setHorizontalScrollBarPolicy(

	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	        scroll.setVerticalScrollBarPolicy(

	                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        //scroll.setBounds(50,500,500,400);
	        panel.add(scroll);
	        
	        txtEnterItemid = new JTextField();
	        scroll.setColumnHeaderView(txtEnterItemid);
	        txtEnterItemid.setColumns(10);
	        
	        JButton btnNewButton = new JButton("DONE");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		model = new DefaultTableModel();
	        		panel.setVisible(false);
	        	}
	        });
	        panel.add(btnNewButton);
	        
	        //JButton btnNewButton_2 = new JButton("New button");
	        //panel.add(btnNewButton_2);
	        panel.setVisible(false);
	        
	        JButton btnNewButton_1 = new JButton("LogOut");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		frame.dispose();
	        	}
	        });
	        btnNewButton_1.setBounds(370, 267, 175, 35);
	        frame.getContentPane().add(btnNewButton_1);
	        
	        JButton btnRefresh = new JButton("Refresh");
	        btnRefresh.addActionListener(new ActionListener() {
	        	
	        	        public void actionPerformed(ActionEvent evnt1) {
	        	            reloadData();
							 model.fireTableDataChanged();
	        	        }
                   });
	        	
	       
	        btnRefresh.setBounds(475, 100, 175, 35);
	        frame.getContentPane().add(btnRefresh);
		
		
	

	
	}



protected void reloadData() {
	// TODO Auto-generated method stub
	String sql="SELECT *"
    		+ " FROM Item";
	PreparedStatement pst;
	try {
	   /* String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL = "jdbc:mysql://localhost/Supermarket";
		String USER = "grp12";
		String PASS = "hesoyam";
		*/
		for(int x=model.getRowCount()-1;x>=0;x--)
			model.removeRow(x);
		
		 Connection conn = null;
		 conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 pst = conn.prepareStatement(sql);
		 ResultSet result=pst.executeQuery();
		 java.sql.ResultSetMetaData rsmd=result.getMetaData();
		// model=new DefaultTableModel();
		 
		 while(result.next())
			{
			//System.out.print(result.getDouble("cost"));
			 model.addRow(new Object[]{result.getString("name"),result.getString("item_id"),result.getString("manufacturer"),
					 result.getDouble("MRP"),result.getDouble("cost"),result.getDouble("discount"),result.getDouble("quantity")});
			}
		// table.setModel(model);
		 
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}