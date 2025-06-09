/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.OrderDetailsPojo;
import in.gadgethub.pojo.OrderPojo;
import in.gadgethub.pojo.TransactionPojo;
import java.util.List;

/**
 *
 * @author hp
 */
public interface OrderDAO {
    public boolean addOrder(OrderPojo order);
    
    public boolean addTransaction(TransactionPojo transaction);
    
    public List<OrderPojo> getAllOrders();
    
    public List<OrderDetailsPojo> getAddOrderDetails(String userEmailId);
    
    public String shipNow(String orderId,String prodId);
    
    public String paymentSuccess(String username,double paidAmount);  
    
    public int getSoldQuantity(String prodId);
    
    public List<OrderPojo> getUnshippedOrderDetails();
    
    public List<OrderPojo> getShippedOrderDetails();
    
    
}
