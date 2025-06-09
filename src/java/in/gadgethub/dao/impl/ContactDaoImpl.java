/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.ContactDao;
import in.gadgethub.pojo.Contact;
import in.gadgethub.utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class ContactDaoImpl implements ContactDao {

    @Override
    public boolean saveMessage(Contact contact) {
        Connection conn=DBUtil.getConnection();
        boolean res=false;
        /*if(checkAlreadyExistOrNot(contact.getEmail())){
            return res;            
        }
       */
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("insert into contacts values(?,?)");
            ps.setString(1,contact.getEmail());
            ps.setString(2,contact.getMessage());
            boolean ans=ps.execute(); 
            res=true;
            /*if(ans){
                res=true;
            }*/
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error in saveMessage Method:"+ex);            
        }
        return res;
        
    }

    @Override
    public boolean checkAlreadyExistOrNot(String email) {
        Connection conn=DBUtil.getConnection();
        boolean result=false;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("select * from contacts where email=?");
            ps.setString(1,email);
            rs=ps.executeQuery();
            
            if(rs.next()){ 
                System.out.println("User already exists"+rs.getString(1));
                result=true;                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error in checkAlreayExistOrNot method"+ex);
            
        }
        return result;
        
    }
    
    public String getResponse(String email) {
        Connection conn=DBUtil.getConnection();
        String str="";
        ResultSet rs=null;
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("select * from contacts where email=?");
            ps.setString(1,email);
            rs=ps.executeQuery();
            if(rs.next()){
                str=rs.getString(2);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error in getResponse Method:"+ex);            
        }
        return str;
        
    }
    
    public boolean updateResponse(Contact contact){        
         Connection conn=DBUtil.getConnection();
        boolean result=false;
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("update contacts set message=? where email=?");
            ps.setString(1,contact.getMessage());
            ps.setString(2,contact.getEmail());
            int count=ps.executeUpdate();
            if(count>0){
                result=true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error in updateResponse Method:"+ex);            
        }
        return result;
        
    }
    
    public void deleteResponse(String email){
        Connection conn=DBUtil.getConnection();
        boolean result=false;
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("delete from contacts where email=?");
            ps.setString(1,email);
            int count=ps.executeUpdate();
            if(count>0){
                result=true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error in deleteResponse Method:"+ex);            
        }
        System.out.println("this is result"+result);
        return;
        
        
        
    }
    
    
    
}
