import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

public class Transaction extends JFrame {

	private List<Product> itemList;
	private String salesClerkName;
	private int transactionID;
	
	
	private JPanel billArea;
	private JTextField prodID;
	private JTextField qty;
	private JLabel clerk_name;
	private JTable itemsTable;
	private JScrollPane tablePane; 
	private JButton addButton;
	
	private DefaultTableModel tm;
	private double total;
	private Date date;
	/**
	 * Launch the application.
	 */
	 
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/Supermarket";

    static final String USER = "root";
    static String PASS ;
    Connection conn = null;
    private JTextField totalField;
    private JButton btnNewTrans;

	public Transaction(String sclName,String a){
		PASS=a;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		salesClerkName=sclName;
		this.setVisible(true);
		itemList=new ArrayList<Product>();
		initSwingComps();
		updateDatabase();
		//this.dispose();
		//conn.close();
	}
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaction frame = new Transaction("sfgv");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	/**
	 * Create the frame.this.setDefaultClthis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);oseOperation(JFrame.DISPOSE_ON_CLOSE);
	 */
	private void initSwingComps(){

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		billArea = new JPanel();
		billArea.setBorder(new EmptyBorder(2, 2, 2, 2));
		this.setContentPane(billArea);
		billArea.setLayout(null);
		billArea.setBackground(Color.WHITE);
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(26, 44, 92, 15);
		billArea.add(lblProductId);
		
		JLabel lblQty = new JLabel("Qty.");
		lblQty.setBounds(227, 44, 70, 15);
		billArea.add(lblQty);
		
		prodID = new JTextField();
		prodID.setBounds(12, 71, 184, 19);
		billArea.add(prodID);
		prodID.setColumns(10);
		
		qty = new JTextField("1");
		qty.setBounds(227, 71, 47, 19);
		billArea.add(qty);
		qty.setColumns(10);
		
		
		clerk_name = new JLabel(salesClerkName);
		clerk_name.setBounds(454, 12, 117, 15);
		billArea.add(clerk_name);
		
		JButton addButton = new JButton("Add Item");
		addButton.setBounds(454, 44, 117, 49);
		
		billArea.add(addButton);
		addButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e){
				addItem(e);
			}	

			
		});

        JButton btnTotal = new JButton("TOTAL");
        btnTotal.setBounds(401, 702, 117, 25);
        billArea.add(btnTotal);

        btnTotal.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e){
				findTotal(e);
			}
		});

        
        totalField = new JTextField();
        totalField.setBounds(227, 702, 114, 19);
        billArea.add(totalField);
        totalField.setColumns(10);
        
        btnNewTrans = new JButton("Reset");
        btnNewTrans.setBounds(43, 702, 117, 25);
        billArea.add(btnNewTrans);
    
        btnNewTrans.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e){
				resetTrans(e);
			}
		});

		initTable();
        
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        
	}
	private void initTable(){
		
		itemsTable=new JTable();
		tablePane= new JScrollPane();
		
		itemsTable.setAutoCreateRowSorter(true);
        itemsTable.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Product ID", "Product Name", "MRP", "Quantity","Discount","Price"
            }
        ) 
        {
            private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
                String.class, String.class, Double.class, Double.class,Double.class,Double.class
            };
            boolean[] editable = new boolean [] {
                false, false, false, false, false, false
            };
        });
        
        itemsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        itemsTable.setColumnSelectionAllowed(true);
        itemsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        itemsTable.getTableHeader().setReorderingAllowed(false);
        tablePane.setViewportView(itemsTable);
        itemsTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(240);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        itemsTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        itemsTable.getColumnModel().getColumn(5).setPreferredWidth(60);
        
        tablePane.setBounds(20, 100, 563, 600);
		
        billArea.add(tablePane);
        tm=(DefaultTableModel)itemsTable.getModel();
	}
	
	private void addItem(java.awt.event.ActionEvent e){
		try{
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					
					Product prod;
					boolean alreadyPresent=false;
					if((prodID.getText()).equals("")){
				        JOptionPane.showMessageDialog(null, "No product ID entered!");
				    }
				    else if((qty.getText()).equals("")){
				        JOptionPane.showMessageDialog(null, "No item quantity entered!");
				    }
					else
				    {
						
						for(Product p:itemList)
				        {
				            if(p.getProductID().equals(prodID.getText()))
				            {
				                
				            	p.setQuantity(p.getQuantity()+Double.parseDouble(qty.getText()));
				                alreadyPresent=true;
			                    
				                String sql="SELECT*"
							    		+ " FROM Item"
							    		+ " WHERE item_id LIKE '%"+prodID.getText()+"%'";
							    				
				                PreparedStatement ps= conn.prepareStatement(sql,
							    		    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
							    ResultSet result=ps.executeQuery();
							    java.sql.ResultSetMetaData rsmd=result.getMetaData();
							   
								//result.updateDouble("quantity",(double)p.getQuantity());   
			                    while(result.next())
			                    {
				                if(p.getQuantity() > result.getDouble("quantity")){
							    	JOptionPane.showMessageDialog(null, "Not Enough Stock !!");
							    	break;
				                }
				                else{
				                	fillBill();
				                }
			                    }
				                result.close();
				                break;

				            } 
				           
				        }
			        }
				        
			        if(alreadyPresent==false)
			        {
			        
		        		String sql="SELECT*"
					    		+ " FROM Item"
					    		+ " WHERE item_id LIKE '%"+prodID.getText()+"%'";
					    
		        		//PreparedStatement ps_1= conn.prepareStatement(sql,
				    		//    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		        		
		            	PreparedStatement ps_1= conn.prepareStatement(sql,
				    		    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					    ResultSet result=ps_1.executeQuery();
					    java.sql.ResultSetMetaData rsmd=result.getMetaData();
					    
					    int x=0;

					    while(result.next()){
						    String name_ = result.getString("name");
			            	String manufacturer_= result.getString("manufacturer");
			            	String prodID_=result.getString("item_id");
			            	double MRP_= result.getDouble("MRP");
			            	double cost_=result.getDouble("cost");
			            	double discount_= result.getDouble("discount");
			            	double quantity_=Double.parseDouble(qty.getText());
			            	
						    prod = new Product(name_,manufacturer_,prodID_,
						    					cost_,discount_,quantity_,MRP_);
			               
						    if(prod.getQuantity() > result.getDouble("quantity")){
						    	JOptionPane.showMessageDialog(null, "Not Enough Stock !!");
						    }
						    if(prod!=null){
			                    //prod.setQuantity(Double.parseDouble(qty.getText()));
			                    itemList.add(prod);
			                   
			            		//conn.close();           
			            		fillBill();
			                }
						    x++;
						    
		            	}if(x==0){
		        		    System.out.println(prodID.getText());
		        		    //conn.close();
		        		    JOptionPane.showMessageDialog(null, "Product not found !");
		            	
		            	}
		            	 
		            	//result.close();
			        }
			      conn.close();
				}catch(SQLException sqlexp){
					sqlexp.printStackTrace();
				}
	}	
	
	private void fillBill(){
        int i=0;
        
        tm.getDataVector().removeAllElements();
        tm.fireTableDataChanged();
        
        for(Product prod: itemList){
            
        	tm.addRow(new Object[6]);  
            
            tm.setValueAt(prod.getProductID(), i, 0);
            
            tm.setValueAt(prod.getName(), i, 1);
            tm.setValueAt(prod.getMRP(), i, 2);
            tm.setValueAt(prod.getQuantity(), i, 3);            
            tm.setValueAt(prod.getDiscount(), i, 4);
            tm.setValueAt(prod.getMRP()*(1-prod.getDiscount()/100)*prod.getQuantity(), i, 5);         
            i++;
        }
	}
	
	private void updateDatabase(){
		try{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			for (Product p:itemList){
				    String sql="SELECT*"
				    		+ " FROM Item"
				    		+ " WHERE item_id LIKE '%"+p.getProductID()+"%'";
				    				
	                PreparedStatement ps= conn.prepareStatement(sql,
			    		    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				    ResultSet result=ps.executeQuery();
				    java.sql.ResultSetMetaData rsmd=result.getMetaData();
				    
					while(result.next()) result.updateDouble("quantity",(result.getDouble("quantity") - (double)p.getQuantity()));   
			}
			conn.close();
		}catch(SQLException ee){
            	ee.printStackTrace();
		}
	}
	
	private void resetTrans(java.awt.event.ActionEvent e){
	    //transID = id.newBill(empId);

		updateSalesHistory();
		updateDatabase();
		
		total = 0;
        totalField.setText("");

        DefaultTableModel toReset;
        toReset = (DefaultTableModel)itemsTable.getModel();
        toReset.getDataVector().removeAllElements();
        toReset.fireTableDataChanged();
        
        itemList.clear();
        prodID.setText("");
        qty.setText("1"); 
	}
	
	private void findTotal(java.awt.event.ActionEvent e){
		int i;
		total=0;
		int numRow= itemList.size();
		for(i=0;i<numRow;i++){
			total+=(double)itemsTable.getValueAt(i, 5);
		}
		String toDisp="" + total;
		totalField.setText(toDisp);
	}
	
	private void updateSalesHistory(){
		java.util.Calendar cal=Calendar.getInstance();
		java.util.Date date=new java.util.Date();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Random random=new Random();
		transactionID= random.nextInt(1000000000 - 100000001) + 100000001;
		try{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			for (Product p:itemList){
		
        		String s11="INSERT INTO Sales_history(salesTime, transaction_id, item_id, quantity, net_sales, profit) "+
		                   "VALUES(?,?,?,?,?,?)";
		        java.sql.PreparedStatement ps_1=conn.prepareStatement(s11);
		        ps_1.setDate(1,new java.sql.Date(cal.getTime().getTime()));
		        ps_1.setInt(2, transactionID);
		        ps_1.setString(3, p.getProductID());
		        ps_1.setDouble(4,p.getQuantity());
		        ps_1.setDouble(5, p.getMRP()*(1-p.getDiscount()/100)*p.getQuantity());
		        ps_1.setDouble(6, (p.getMRP()*(1-p.getDiscount()/100) - p.getCost())*p.getQuantity());
		        ps_1.executeUpdate();  
		               
			}
			conn.close();
		}catch(SQLException ee){
            	ee.printStackTrace();
		}
	}
}