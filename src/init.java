import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class init {
	
	//ArrayList<employee> list = new ArrayList<employee>();
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/";

	   //  Database credentials
	   static final String USER = "root";
	   static  String PASS ;
	
	public void create_database(String a)
	{
		       PASS=a;
			   Connection conn = null;
			   java.sql.Statement stmt = null;
			   try{
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");

			      //STEP 3: Open a connection
			      //System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);

			      //STEP 4: Execute a query
			      //System.out.println("Creating database...");
			      stmt = conn.createStatement();
			      
			      String sql = "CREATE DATABASE IF NOT EXISTS Supermarket";
			      stmt.executeUpdate(sql);
			     // System.out.println("Database created successfully...");
			      
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
			      }//end finally try
			   }//end try
			  // System.out.println("Goodbye!");
			   
			   
	}
		
		
	
	
     
	
	
	
}