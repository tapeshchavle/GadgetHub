/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package in.gadgethub.dao.impl;
import java.sql.*;
import in.gadgethub.dao.CartDAO;
import in.gadgethub.pojo.CartPojo;
import in.gadgethub.pojo.DemandPojo;
import in.gadgethub.utility.DBUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class CartDaoImpl implements CartDAO {
    //when clicking on + , - or update quentity
    @Override
    public String updateProductInCart(CartPojo cart) {
        Connection conn=DBUtil.getConnection();
        String status="Failed to Add into Cart";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("select * from usercart where prodid=? and useremail=?");
            ps.setString(1, cart.getProdId());
            ps.setString(2,cart.getUseremail());
            rs=ps.executeQuery();
            if(rs.next()){
                if(cart.getQuantity()==0){
                    PreparedStatement ps1=conn.prepareStatement("delete from usercart where prodid=? and useremail=?");
                    int x=ps1.executeUpdate();
                    if(x==1)
                        status="Item sucessfully deleted";                    
                }else if(cart.getQuantity()>0){
                    PreparedStatement ps2=conn.prepareStatement("update usercart set quantity=? where prodid=? and useremail=?");
                    ps2.setInt(1,cart.getQuantity());
                    ps2.setString(2, cart.getProdId());
                    ps2.setString(3,cart.getUseremail());
                    int x=ps2.executeUpdate();
                    if(x==1)
                        status="Item sucessfully Updated";
                }
                
            }else{
                PreparedStatement ps3=conn.prepareStatement("insert into usercart values(?,?,?)");
                ps3.setString(1,cart.getUseremail());
                ps3.setString(2,cart.getProdId());
                ps3.setInt(3,cart.getQuantity());
                int x=ps3.executeUpdate();
                if(x==1)
                    status="Item Sucessfully Added into the Cart";
                
            }
        }catch(SQLException ex){
            status="Could not update the product";
            System.out.println("Error in AddProduct in Cart Method"+ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return status;      
        
        
      }

    @Override
    public String addProductInCart(CartPojo cart) {
        Connection conn=DBUtil.getConnection();
        String status="Failed to add into cart";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("select * from usercart where prodid=? and useremail=?");
            ps.setString(1,cart.getProdId());
            ps.setString(2,cart.getUseremail());
            rs=ps.executeQuery();
            if(rs.next()){
                ProductDaoImpl prod=new ProductDaoImpl();
                int stockQty=prod.getProductQuantity(cart.getProdId());
                int newQty=cart.getQuantity()+rs.getInt("quantity");
                if(stockQty<newQty){
                      status="Only "+stockQty+" no of items are available in our stock so we adding "+stockQty+" in your cart";
                      DemandPojo demandPojo=new DemandPojo();
                      demandPojo.setProdId(cart.getProdId());
                      demandPojo.setUseremail(cart.getUseremail());
                      demandPojo.setDemandQuantity(newQty-stockQty);
                      DemandDaoImpl demandDao=new DemandDaoImpl();
                      boolean result=demandDao.addProduct(demandPojo);
                      if(result==true){
                          status="We will mail you when "+(newQty-stockQty)+" no of items will be available";
                      }
                }else{
                    cart.setQuantity(newQty);
                    status=updateProductInCart(cart);
                }                    
                
            }
            
        }catch(SQLException ex){
            System.out.println("Addition failed due to cart!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        DBUtil.closeResultSet(rs);
        return status;     
        
        
        
        }

    @Override
    public List<CartPojo> getAllCartItems(String userId) {
        List<CartPojo> itemList=new ArrayList<>();
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;        
        try{
            ps=conn.prepareStatement("select * from usercart where useremail=?");
            ps.setString(1,userId);
            rs=ps.executeQuery();
            while(rs.next()){
                CartPojo cart=new CartPojo();
                cart.setUseremail(rs.getString("useremail"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setProdId(rs.getString("prodId"));
                itemList.add(cart);
                
            }
            
        }catch(Exception ex){
            System.out.println("Error in getAllCartItems:"+ex);
            ex.printStackTrace();
            
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return itemList;
     }

    @Override
    public int getCartItemCount(String userId, String itemId) {
        if(userId==null || itemId==null)
            return 0;
        Connection conn=DBUtil.getConnection();
        int count=0;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("select quantity from usercart where useremail=? and prodid=?");
            ps.setString(1,userId);
            ps.setString(2,itemId);
            rs=ps.executeQuery();
            if(rs.next()){
                count=rs.getInt(1);
            }
            
            
        }catch(Exception ex){
            System.out.println("Error in getCartItemCount Method"+ex);
            ex.printStackTrace();
        }
       // DBUtil.closeResultSet(rs);
       // DBUtil.closeStatement(ps);
        return count;        
    }

    @Override
    public String removeProductFromCart(String userId, String prodId) {
        Connection conn=DBUtil.getConnection();
        String status="Product Removal failed";
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        ResultSet rs=null;
        try{
            ps1=conn.prepareStatement("select * from usercart where useremail=? and prodId=?");
            ps1.setString(1,userId);
            ps1.setString(2,prodId);
            rs=ps1.executeQuery();
            if(rs.next()){
                int prodQuantity=rs.getInt("quantity");
                prodQuantity-=1;
                if(prodQuantity>0){
                    //update
                    ps2=conn.prepareStatement("update usercart set quantity=? where useremail=? and prodid=?");
                    ps2.setInt(1, prodQuantity);
                    ps2.setString(2,userId);
                    ps2.setString(3,prodId);
                    int k=ps2.executeUpdate();
                    if(k>0){
                        status="Product successfully removed from the Cart";
                    }
                }else{
                    //delete karna hai
                    ps2=conn.prepareStatement("delete from usercart where useremail=? and prodid=?");
                 
                    ps2.setString(1,userId);
                    ps2.setString(2,prodId);
                    int k=ps2.executeUpdate();
                    if(k>0){
                        status="Product successfully removed from the Cart";
                    }
                }
                
            }
        }catch(SQLException ex){
            status="Removal Failed due to exception";
            System.out.println("Error in removeProduct from cart method"+ex);
            ex.printStackTrace();
            
        }
       
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps2);
        DBUtil.closeStatement(ps1);
        return status;
    }

     //method call during payment
    @Override
    public Boolean removeAProduct(String userId, String prodId) {
        Boolean flag=false;
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("delete from usercart where useremail=? and prodId=?");
            ps.setString(1,userId);
            ps.setString(2,prodId);
            int count=ps.executeUpdate();
            if(count>0)
                flag=true;           
            
        }catch(Exception ex){
            System.out.println("Error in removeAProduct Method"+ex);
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return flag;
      }  
    
}
