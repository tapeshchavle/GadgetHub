/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.impl.CartDaoImpl;
import in.gadgethub.dao.impl.ProductDaoImpl;
import in.gadgethub.pojo.CartPojo;
import in.gadgethub.pojo.ProductPojo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class CartDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("password");
        String userType = (String) session.getAttribute("userType");
        if (userType == null || !userType.equalsIgnoreCase("customer")) {
            response.sendRedirect("login.jsp?message=Access denied ! Please login as customer");
        } 
        //some more code will come here
        String addStr=request.getParameter("add");
        if(addStr!=null){
            String uid=request.getParameter("uid");
            String pid=request.getParameter("pid");
            int avail=Integer.parseInt(request.getParameter("avail"));
            int cartQty=Integer.parseInt(request.getParameter("qty"));
            CartDaoImpl cartDao=new CartDaoImpl();
            if(addStr.equals("1")){
                cartQty+=1;
                CartPojo cartPojo=new CartPojo();
                cartPojo.setProdId(pid);
                cartPojo.setQuantity(cartQty);
                cartPojo.setUseremail(uid);
                cartDao.updateProductInCart(cartPojo);
            }else{
                cartQty-=1;
                CartPojo cartPojo=new CartPojo();
                cartPojo.setProdId(pid);
                cartPojo.setQuantity(cartQty);
                cartPojo.setUseremail(uid);
                cartDao.updateProductInCart(cartPojo);
            }
        }
        
         CartDaoImpl cartDao=new CartDaoImpl(); 
         ProductDaoImpl productDao=new ProductDaoImpl(); 
         List<CartPojo>cartItems=cartDao.getAllCartItems(userName); 
         Map<String,Object>map=new HashMap<>(); 
         for(CartPojo cp:cartItems){ 
             String prodId=cp.getProdId(); 
             ProductPojo product=productDao.getProductDetails(prodId); 
             map.put(prodId,product); 
         } 
        RequestDispatcher rd=request.getRequestDispatcher("cartDetails.jsp"); 
        request.setAttribute("userName",userName); 
        request.setAttribute("cartItems",cartItems); 
        request.setAttribute("map",map); 
        rd.forward(request, response);    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
