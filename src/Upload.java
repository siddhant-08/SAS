import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Upload {
	
	
	
	   ArrayList<employee> list = new ArrayList<employee>();
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/Supermarket";

	   //  Database credentials
	   static final String USER = "root";
	   static  String PASS ;
	   cryptWithMD5 xymen=new cryptWithMD5();
	
	public void upload(String a)
	{
		       PASS=a;
			   Connection conn = null;
			   java.sql.Statement stmt = null;
			   try{
	

    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(DB_URL, USER, PASS);
    stmt = conn.createStatement();
    
    
    boolean count=false;
    java.sql.DatabaseMetaData dbm = conn.getMetaData();
 
    java.sql.ResultSet tables = dbm.getTables(null, null, "Employee", null);
    if (tables.next()) {
      // Table exists
	  if(count==false) count=true;
    }
 
 
 
 if(count==false)
 { 
    String sql1="CREATE TABLE Employee (ment VARCHAR(10),name VARCHAR(30),empl_id VARCHAR(10),email VARCHAR(30),mobile VARCHAR(30),sales_clerk VARCHAR(15),"
    		
    		+ "password VARCHAR(50),PRIMARY KEY(empl_id))";
    stmt.executeUpdate(sql1);
    
    //insert manager to database
    	
    String s12="INSERT INTO Employee(ment,name,empl_id,email,mobile,sales_clerk,password) "+
	           "VALUES(?,?,?,?,?,?,?)";
	java.sql.PreparedStatement ps=conn.prepareStatement(s12);
	ps.setString(1, "manager");
	ps.setString(2,"Prabhat Mohanty");
	ps.setString(3, "M1000");
	ps.setString(4, "prabh1234@gmail.com");
	ps.setString(5, "9987098765");
	ps.setString(6, "false");
	//System.out.println()
	ps.setString(7, xymen.cryptWithMD5("pra123"));
	ps.executeUpdate();  
    
    create_employee();
    
    for(int i=0;i<list.size();i++)
    {
    	String s1=list.get(i).getname();
    	String s2=list.get(i).getempl_id();
    	String s3=list.get(i).getmob();
    	String s4=list.get(i).getemail();
    	String s5=list.get(i).getpass();
    	String s6;
    	if(list.get(i).get_type()) s6="true";
    	else s6="false";
    	
    	//System.out.println(s1+s2+s5);
    	
    	String s11="INSERT INTO Employee(ment,name,empl_id,email,mobile,sales_clerk,password) "+
    	           "VALUES(?,?,?,?,?,?,?)";
    	java.sql.PreparedStatement ps_1=conn.prepareStatement(s11);
    	ps_1.setString(1, "employee");
    	ps_1.setString(2,s1);
    	ps_1.setString(3, s2);
    	ps_1.setString(4, s4);
    	ps_1.setString(5, s3);
    	ps_1.setString(6, s6);
    	ps_1.setString(7, xymen.cryptWithMD5(s5));
    	ps_1.executeUpdate();  
    	//System.out.println(ti+" records inserted");  
    	
    	//stmt.executeUpdate(s11);
    	
    }
 }
    
    boolean count1=false;
    java.sql.DatabaseMetaData dbm1 = conn.getMetaData();
 
    java.sql.ResultSet tables_1 = dbm1.getTables(null, null, "Item", null);
    if (tables_1.next()) {
      // Table exists

    //	System.out.println("Present");
	  if(count1==false) count1=true;
    }
    
    if(count1==false)
    {
    	//System.out.println("Create");
    	 String sql2="CREATE TABLE Item (name VARCHAR(30),item_id VARCHAR(20),manufacturer VARCHAR(30),MRP DOUBLE,cost DOUBLE,discount DOUBLE "
    	 		+ " ,type VARCHAR(10),quantity DOUBLE,PRIMARY KEY (item_id))";
    	    		
    		
         stmt.executeUpdate(sql2); 	
         
         
         for(int i=0;i<5;i++)
         {
         String s12="INSERT INTO Item(name,item_id,manufacturer,MRP,cost,discount,type,quantity) "+
  	           "VALUES(?,?,?,?,?,?,?,?)";
  	     java.sql.PreparedStatement ps=conn.prepareStatement(s12);
  	     
  	     
  	     String s1,s2,s3,s4;
  	     Double m,c,d,q;
  	     
  	     if(i==0)
  	     {
  	    	 s1="Parle biscuits";
  	    	 s2="1203456789";
  	    	 s3="Parle India";
  	    	 s4="packed";
  	    	 m=13.0;
  	    	 c=10.0;
  	    	 d=0.0;
  	    	 q=100.0;
  	     }
  	     
  	     else if(i==1)
  	     {
  	    	 s1="Basmati Rice";
 	    	 s2="3401256789";
 	    	 s3="Basmati India";
 	    	 s4="packed";
 	    	 m=40.0;
 	    	 c=30.0;
 	    	 d=0.0;
 	    	 q=150.0;
  	     }
  	     else if(i==2)
  	     {
  	    	 s1="Kitkat";
 	    	 s2="5603412789";
 	    	 s3="Cadbury India";
 	    	 s4="packed";
 	    	 m=12.0;
 	    	 c=10.0;
 	    	 d=0.0;
 	    	 q=200.0;
  	     }
  	     else if(i==3)
  	     {
  	    	 s1="Lifebouy handwash";
 	    	 s2="7803456129";
 	    	 s3="LifeBouy India";
 	    	 s4="packed";
 	    	 m=27.0;
 	    	 c=22.0;
 	    	 d=0.0;
 	    	 q=60.0; 
  	     }
  	     else {
  	    	 s1="Apples";
 	    	 s2="1230456789";
 	    	 s3="Apple Fresh";
 	    	 s4="loose";
 	    	 m=20.0;
 	    	 c=17.0;
 	    	 d=0.0;
 	    	 q=30.23;
  	     }
  	     ps.setString(1, s1);
  	     ps.setString(2,s2);
  	     ps.setString(3, s3);
  	     ps.setString(4 ,Double.toString(m) );
  	     ps.setString(5, Double.toString(c));
  	     ps.setString(6, Double.toString(d));
  	     //System.out.println()
  	     ps.setString(8,Double.toString(q) );
  	     ps.setString(7, s4);
  	     ps.executeUpdate(); 
         }
    }
 
    
    conn.close();
    
   
    
 }catch(SQLException se){
     //Handle errors for JDBC
     se.printStackTrace();
  }catch(Exception e){
     //Handle errors for Class.forName
     e.printStackTrace();
  }finally{
     //finally block used to close resources
     try{
        if(stmt!=null)
           stmt.close();
     }catch(SQLException se2){
     }// nothing we can do
     try{
        if(conn!=null)
           conn.close();
     }catch(SQLException se){
        se.printStackTrace();
     }
  }
  
  
}

    
    public void create_employee()
	{
		
		list.add(new employee("Rajat Jain","E1000","rajat@gmail.com","9933982650",true,"raj123"));
		list.add(new employee("Yash Jain","E1001","yash12@gmail.com","9933992650",false,"yas123"));
		list.add(new employee("Pranjal Kumar","E1002","pranjal345@gmail.com","9933992650",true,"pral123"));
		list.add(new employee("Shimli Gupta","E1003","guptacraze@gmail.com","9933912650",false,"shi123"));
		list.add(new employee("Harsh Jain","E1004","harshiitkgp@gmail.com","9933932650",true,"har123"));
		list.add(new employee("Manish Anand","E1005","manishanand12345@gmail.com","9933942650",false,"man123"));
		list.add(new employee("Lalu dasu","E1006","yara567@gmail.com","9933952650",true,"lal123"));
		list.add(new employee("Sachin Dravid","E1007","dravidlegend@gmail.com","9933952650",true,"sac123"));
		list.add(new employee("Lionel Ronaldo","E1008","ballondor@gmail.com","9933972650",false,"lio123"));
		list.add(new employee("Angel Aguero","E1009","passlegend@gmail.com","9933932650",true,"ang123"));
	
		
	}
	
	
    
    
    
    
	

	
    

}