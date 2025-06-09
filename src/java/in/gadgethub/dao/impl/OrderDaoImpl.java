/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.OrderDAO;
import in.gadgethub.pojo.CartPojo;
import in.gadgethub.pojo.OrderDetailsPojo;
import in.gadgethub.pojo.OrderPojo;
import in.gadgethub.pojo.TransactionPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.IDUtil;
import in.gadgethub.utility.MailMessage;
import in.gadgethub.utility.OrderPlacedEmail;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author hp
 */
public class OrderDaoImpl implements OrderDAO {

    @Override
    public boolean addOrder(OrderPojo order) {
        boolean status = false;
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into orders values(?,?,?,?,?)");
            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getPordId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getAmount());
            ps.setInt(5, 0);
            int count = ps.executeUpdate();
            status = count > 0;

        } catch (SQLException ex) {
            System.out.println("Error in addOrder:" + ex);
            ex.printStackTrace();

        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public boolean addTransaction(TransactionPojo transaction) {
        boolean status = false;
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into transactions values(?,?,?,?)");
            ps.setString(1, transaction.getTransactionId());
            ps.setString(2, transaction.getUserEmail());
            java.util.Date d1 = transaction.getTransTime();
            java.sql.Date d2 = new java.sql.Date(d1.getTime());
            ps.setDate(3, d2);
            ps.setDouble(4, transaction.getAmount());
            int count = ps.executeUpdate();
            status = count > 0;

        } catch (SQLException ex) {
            System.out.println("Error in addTransaction" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public List<OrderPojo> getAllOrders() {
        List<OrderPojo> orderList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from Orders");
            while (rs.next()) {
                OrderPojo order = new OrderPojo();
                order.setOrderId(rs.getString("orderid"));
                order.setProdId(rs.getString("prodid"));
                order.setQuantity(rs.getInt("quantity"));
                order.setShipped(rs.getInt("shipped"));
                order.setAmount(rs.getDouble("amount"));
                orderList.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("Error in getAllOrders Method:" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        return orderList;
    }

    @Override
    public List<OrderDetailsPojo> getAddOrderDetails(String userEmailId) {
        Connection conn = DBUtil.getConnection();
        List<OrderDetailsPojo> orderList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select p.pid as pid,o.orderid as orderid,o.shipped as shipped,p.image as image, p.pname as pname,o.quantity as qty,o.amount as amount,t.transtime as time FROM orders o, products p,transactions t where o.orderid=t.transid and o.prodid=p.pid and t.useremail=?");
            ps.setString(1, userEmailId);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetailsPojo orderDetails = new OrderDetailsPojo();
                orderDetails.setOrderId(rs.getString("orderid"));
                orderDetails.setProdImage(rs.getAsciiStream("image"));
                orderDetails.setProdId(rs.getString("pid"));
                orderDetails.setProdName(rs.getString("pname"));
                orderDetails.setQty(rs.getInt("qty"));
                orderDetails.setAmount(rs.getDouble("amount"));
                orderDetails.setTime(rs.getTimestamp("time"));
                orderDetails.setShipped(rs.getInt("shipped"));
                orderList.add(orderDetails);

            }

        } catch (SQLException ex) {
            System.out.println("Error in getAddOrderDetails Method" + ex);
            ex.printStackTrace();
        }
        //DBUtil.closeResultSet(rs);
        //DBUtil.closeStatement(ps);
        return orderList;

    }

    @Override
    public String shipNow(String orderId, String prodId) {
        String status = "Failure";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("Update orders set shipped=1 where orderid=? and prodId=?");
            ps.setString(1, orderId);
            ps.setString(2, prodId);
            int count = ps.executeUpdate();
            if (count > 0) {
                status = "Order has been shipped successfully";
                //email
                TransactionDaoImpl transaction = new TransactionDaoImpl();
                String emailId = transaction.getUserId(orderId);
                String name = new UserDaoImpl().getUserFirstName(emailId);
                // MailMessage.shippedProduct(emailId, name);
                // Asynchronous email sending
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(() -> {
                    try {
                        MailMessage.shippedProduct(emailId, name);
                    } catch (Exception ex) {
                        System.out.println("Error sending email: " + ex); 
                    }
                });
                executor.shutdown();

            }

        } catch (Exception ex) {
            System.out.println("Error in shipNow method" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public String paymentSuccess(String username, double paidAmount) {
        String status = "Order Placement Failed";
        Connection conn = null;
        CartDaoImpl cartDao = new CartDaoImpl();
        List<CartPojo> cartList = cartDao.getAllCartItems(username);
        if (cartList.isEmpty()) {
            return status;
        }
        System.out.println(username + " " + paidAmount);
        String transactionId = IDUtil.generateTransId();
        TransactionPojo trPojo = new TransactionPojo();
        trPojo.setTransactionId(transactionId);
        trPojo.setUserEmail(username);
        trPojo.setAmount(paidAmount);
        java.util.Date date = new java.util.Date();
        trPojo.setTransTime(date);
        /*Mail Object*/

        OrderPlacedEmail op = new OrderPlacedEmail();
        op.setItems(cartList);
        op.setOrderDate(date.toString());
        op.setOrderTotal(paidAmount);
        op.setOrderNumber(cartList.get(0).getProdId());


        /**/
        boolean result = addTransaction(trPojo);
        if (result == false) {
            return status;
        }
        boolean ordered = true;
        ProductDaoImpl productDAO = new ProductDaoImpl();

        for (CartPojo cartPojo : cartList) {
            double amount = productDAO.getProductPrice(cartPojo.getProdId()) * cartPojo.getQuantity();
            OrderPojo order = new OrderPojo();
            order.setOrderId(transactionId);
            order.setProdId(cartPojo.getProdId());
            order.setQuantity(cartPojo.getQuantity());
            order.setAmount(amount);
            order.setShipped(0);
            ordered = addOrder(order);
            if (!ordered) {
                break;
            }
            ordered = cartDao.removeAProduct(cartPojo.getUseremail(), cartPojo.getProdId());

        }
        if (ordered) {
            status = "Order Placed SuccessFully";
            //mail call
            /*try{
            MailMessage.orderPlaced(username, new UserDaoImpl().getUserFirstName(username), op);
            }catch(Exception ex){
                ex.printStackTrace();
            }
             */
            // Asynchronous email sending
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                try {
                    MailMessage.orderPlaced(username, new UserDaoImpl().getUserFirstName(username), op);
                } catch (Exception ex) {
                    System.out.println("Error sending email: " + ex);
                }
            });
            executor.shutdown();

            System.out.println("Transaction successful:" + transactionId);
        } else {
            System.out.println("Transaction Failed:" + transactionId);
        }

        return status;
    }

    @Override
    public int getSoldQuantity(String prodId) {
        int qty = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select sum(quantity) as quant from orders where prodid=?");
            ps.setString(1, prodId);
            rs = ps.executeQuery();
            if (rs.next()) {
                qty = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error in shipNow method" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        DBUtil.closeResultSet(rs);
        return qty;
    }

    @Override
    public List<OrderPojo> getUnshippedOrderDetails() {
        List<OrderPojo> orderList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from Orders where shipped=0");
            while (rs.next()) {
                OrderPojo order = new OrderPojo();
                order.setOrderId(rs.getString("orderid"));
                order.setProdId(rs.getString("prodid"));
                order.setQuantity(rs.getInt("quantity"));
                order.setShipped(rs.getInt("shipped"));
                order.setAmount(rs.getDouble("amount"));
                orderList.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("Error in getAllOrders Method:" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        return orderList;

    }

    @Override
    public List<OrderPojo> getShippedOrderDetails() {
        List<OrderPojo> orderList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from Orders where shipped=1");
            while (rs.next()) {
                OrderPojo order = new OrderPojo();
                order.setOrderId(rs.getString("orderid"));
                order.setProdId(rs.getString("prodid"));
                order.setQuantity(rs.getInt("quantity"));
                order.setShipped(rs.getInt("shipped"));
                order.setAmount(rs.getDouble("amount"));
                orderList.add(order);

            }
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error in getAllOrders Method:" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);

        return orderList;
    }

}
