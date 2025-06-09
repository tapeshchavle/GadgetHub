
package in.gadgethub.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author hp
 */
public class DBUtil {
    public static Connection conn;  
    //This method close the database connection
    public static void closeConnection(){
        try{
            if(conn!=null){
                conn.close();
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
    
    //This method return the Connection object
    public static Connection getConnection(){
        if(conn==null){
         try{               
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-S1QPSLM6:1521/xe","gadgethub","abc");                       
                
            }catch(SQLException ex){
                System.out.println("Error in this place"); 
                ex.printStackTrace();
            }
        }
        return conn;
    }
    //this method close the ResuletSet
    
    public static void closeResultSet(ResultSet rs){
       try{
           rs.close();
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }
    
    //This method close the PreparedStatement
    public static void closeStatement(PreparedStatement st){
        try{
            st.close();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
}
