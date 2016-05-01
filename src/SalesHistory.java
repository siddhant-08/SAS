import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SalesHistory {

       static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
       static final String DB_URL = "jdbc:mysql://localhost/Supermarket";

       //  Database credentials
       static final String USER = "root";
       static  String PASS ;
       
       public static void init(String a)
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
             
                java.sql.ResultSet tables = dbm.getTables(null, null, "Sales_history", null);
                if (tables.next()) {
                  // Table exists
                  if(count==false) count=true;
                }
                
                if(count==false)
                {
                
                    String sql1="CREATE TABLE Sales_history ( salesTime Date,transaction_id int(20),item_id VARCHAR(30),quantity DOUBLE,net_sales DOUBLE,profit DOUBLE,"
                            
                      + "PRIMARY KEY(item_id,salesTime,transaction_id))";
                    stmt.executeUpdate(sql1);   
                
                    conn.close();
           }
            
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
    
}