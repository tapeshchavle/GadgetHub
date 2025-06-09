/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package in.gadgethub.dao.impl;

import java.sql.*;
import in.gadgethub.dao.TransactionDAO;
import in.gadgethub.utility.DBUtil;

/**
 *
 * @author hp
 */
public class TransactionDaoImpl implements TransactionDAO {

    @Override
    public String getUserId(String transId) {
        String userId="";
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("select useremail from transactions where transid=?");
            ps.setString(1,transId);
            rs=ps.executeQuery();
            if(rs.next()){
                userId=rs.getString(1);
                                
            }
        }catch(Exception ex){
            System.out.println("Error in getUserId method"+ex);
            ex.printStackTrace();
        }
        return userId;        
        
        }
    
}
