import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import com.toedter.calendar.JDateChooser;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.toedter.calendar.JSpinnerDateEditor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Transparency;
public class Statistics extends JFrame {

	private JPanel statPanel;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private JTable itemsTable;
	private JScrollPane tablePane; 
	private DefaultTableModel tm;
	private JDateChooser jdc;
	private JDateChooser endDateChooser;
	private JLabel  label1;
	private double totalNetSales=0;
	private double totalProfit=0;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/Supermarket";

    static final String USER = "root";
    static String PASS ;
    Connection conn = null;
	
    private ArrayList<String> itemIDs=new ArrayList<String>();
    private JTextField profitTB;
    private JTextField netSalesTB;
//    private JTextField textField;
  //  private JTextField textField_1;
    
	public Statistics(String a) {
		PASS=a;
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 700);
		statPanel = new JPanel();
		statPanel.setBackground(Color.WHITE);
		statPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		statPanel.setLayout(null);
		setContentPane(statPanel);
		
		
		jdc=new JDateChooser("yyyy/MM/dd", "##/##/####", '_');
		jdc.setCalendar(Calendar.getInstance());
		jdc.setBounds(25, 55, 120,25);
		statPanel.add(jdc);
		
		JLabel lblStartingDate = new JLabel("Starting Date");
		lblStartingDate.setBounds(32, 12, 143, 15);
		statPanel.add(lblStartingDate);
		
		endDateChooser = new JDateChooser("yyyy/MM/dd", "##/##/####", '_');
		endDateChooser.setCalendar(Calendar.getInstance());
		endDateChooser.setBounds(260, 55, 120, 25);
		statPanel.add(endDateChooser);
		
		JLabel lblEndingDate = new JLabel("Ending Date");
		lblEndingDate.setBounds(260, 12, 143, 15);
		statPanel.add(lblEndingDate);
		
		JButton btnGetStats = new JButton("Get Stats");
		btnGetStats.setBounds(150, 92, 117, 25);
		statPanel.add(btnGetStats);

        btnGetStats.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e){
				genStats(e);
			}
		});
        
        BufferedImage img = null;
        //File file= new File("sample.txt");
        //System.out.println(file.getAbsolutePath());
        try {
            img = ImageIO.read(new File("stats.jpg"));
        } catch (IOException e) {
        	e.printStackTrace();
        }
        BufferedImage scaled=resize(
	            img,311, 393);
        label1=new JLabel(new ImageIcon(scaled));
        label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setVerticalAlignment(JLabel.CENTER);
		label1.setBounds(402, 176, 311, 393);
		statPanel.add(label1);
        initTable();
    
	}
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	private void initTable(){
	
		itemsTable=new JTable();
		tablePane= new JScrollPane();
		
		itemsTable.setAutoCreateRowSorter(true);
	    itemsTable.setModel(new DefaultTableModel(
	        new Object [][] {},
	        new String [] {
	            "Product ID","Net Sales", "Profit"
	        }
	    ) 
	    {
	        private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
	            String.class,Double.class, Double.class
	        };
	        boolean[] editable = new boolean [] {
	            false, false, false
	        };
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	            return editable[columnIndex];
	        };
	    });
	    
	    itemsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
	    itemsTable.setColumnSelectionAllowed(true);
	    itemsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	    itemsTable.getTableHeader().setReorderingAllowed(false);
	    tablePane.setViewportView(itemsTable);
	    itemsTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    itemsTable.getColumnModel().getColumn(0).setPreferredWidth(100);
	    itemsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
	    itemsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
	    //itemsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
	    
	    tablePane.setBounds(54, 125, 305, 500);
		
	    statPanel.add(tablePane);
	    
	    profitTB = new JTextField();
	    profitTB.setBounds(260, 624, 99, 19);
	    statPanel.add(profitTB);
	    profitTB.setColumns(10);
	    
	    netSalesTB = new JTextField();
	    netSalesTB.setColumns(10);
	    netSalesTB.setBounds(150, 624, 99, 19);
	    statPanel.add(netSalesTB);
	    
	/*    textField = new JTextField();
	    textField.setBounds(32, 37, 114, 19);
	    statPanel.add(textField);
	    textField.setColumns(10);
	    
	    textField_1 = new JTextField();
	    textField_1.setBounds(249, 39, 114, 19);
	    statPanel.add(textField_1);
	    textField_1.setColumns(10);
	  */  
	    
	    
	    tm=(DefaultTableModel)itemsTable.getModel();
	  /*  ChartPanel chartPanel=new ChartPanel(barchart);
	    chartPanel.setBounds(395, 150, 343, 500);
	    statPanel.add(chartPanel);
	    DefaultCategoryDataset dataset=new DefaultCategoryDataset();
	    barchart=ChartFactory.createBarChart("Statistics", "Items", "Values", dataset, PlotOrientation.HORIZONTAL, true, true, false);
	    */
	   /* CategoryDataset dataset= ch(
	        "Statistics of sales",
	        "Items",
	        "Values",
	        PlotOrientation.HORIZONTAL,
	        true,true,false);
	    		
	    		
	     );
	    		
	    */
	    
        
	}

	private void genStats(java.awt.event.ActionEvent e){
		try{
			DefaultTableModel toReset;
	        toReset = (DefaultTableModel)itemsTable.getModel();
	        toReset.getDataVector().removeAllElements();
	        toReset.fireTableDataChanged();
	        
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// this part is for overall stats
			startDate= jdc.getDate();
		    endDate= endDateChooser.getDate();
			//SimpleDateFormat form=new SimpleDateFormat("dd/MM/yyyy");
			//startDate=form.parse(textField.getText());
			//endDate=form.parse(textField_1.getText());
			//System.out.println(startDate.getTime());
			java.sql.Date sql_1=new java.sql.Date(startDate.getTime());
			java.sql.Date sql_2=new java.sql.Date(endDate.getTime());
			
			/*String sql="SELECT*"
		    		+ " FROM Sales_history "
		    		+ "WHERE salesTime >= ? AND salesTime <= ? ";
							
			//System.out.println(startDate);

			//System.out.println(endDate);
            PreparedStatement ps= conn.prepareStatement(sql,
		    		    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setDate(1,sql_1);
            ps.setDate(2, sql_2);
		    ResultSet result=ps.executeQuery();
		    java.sql.ResultSetMetaData rsmd=result.getMetaData();
		    */  
		    // this is for itemwise stats
		    
			String uniqueItems= "select " + 
		    					"distinct "+
		    					"item_id " + "from Sales_history"+
		    					" where salesTime >= "
		    					+ "? AND salesTime <= ? ";
		    
		    PreparedStatement ps1= conn.prepareStatement(uniqueItems,
	    		    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    //System.out.println(sql_1);

			//System.out.println(sql_2);
		    
			ps1.setDate(1,sql_1);
            ps1.setDate(2, sql_2);
		    
            ResultSet result1=ps1.executeQuery();
		    java.sql.ResultSetMetaData rsmd1=result1.getMetaData();
		   
		    // itemIDs stores the unique IDs in the date interval
		    
		    while(result1.next()){
		      // System.out.println(result1.getString(1));
			   itemIDs.add(result1.getString(1));
		    }
		    
		    int i=0;
		    double profit=0;
		    double netSales=0;
		    // ResultSet temp;
		    for(String itemID : itemIDs){
		    	//System.out.println(itemID);
		    	String sql1="SELECT*"
			    		+ " FROM Sales_history "
			    		+ "WHERE salesTime >= ? AND salesTime <= ? ";
								
				//System.out.println(startDate);

				//System.out.println(endDate);
	            PreparedStatement ps11= conn.prepareStatement(sql1,
			    		    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            ps11.setDate(1,sql_1);
	            ps11.setDate(2, sql_2);
			    ResultSet temp=ps11.executeQuery();
			   // java.sql.ResultSetMetaData rsmd=result.getMetaData();
			    
		    	while(temp.next()){
		    		//  System.out.println(temp.getString("item_id")+" "+itemID);
		    		if(temp.getString("item_id").equals(itemID)){
		    		//	System.out.println(temp.getDouble("profit"));
		    		//	System.out.println(temp.getDouble("net_sales"));
		    			profit+= temp.getDouble("profit");
		    			netSales+=temp.getDouble("net_sales");
		    		}
		    	}
		    	//temp=ps11.executeQuery();
		    	tm.addRow(new Object[4]);
		    	//java.util.Date toWrite= new java.util.Date((temp.getDate("salesTime")).getTime());
		    	//tm.setValueAt(toWrite.toString(), i, 0);
		    	tm.setValueAt(itemID, i, 0);
		    	tm.setValueAt(netSales, i, 1);
		    	tm.setValueAt(profit, i, 2);
		    	
		    	i++;
		    	totalProfit+=profit;
		    	totalNetSales+=netSales;
		    	profit=0;
		    	netSales=0;
		    }
		    String pro="" + totalProfit;
		    String ns="" + totalNetSales;
		    profitTB.setText(pro);
		    netSalesTB.setText(ns);
		    totalProfit=0;
		    totalNetSales=0;
		    conn.close();
		}catch(SQLException es){
			es.printStackTrace();
		}
	}
}