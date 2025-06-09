/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.impl.CartDaoImpl;
import in.gadgethub.dao.impl.DemandDaoImpl;
import in.gadgethub.dao.impl.ProductDaoImpl;
import in.gadgethub.pojo.CartPojo;
import in.gadgethub.pojo.DemandPojo;
import in.gadgethub.pojo.ProductPojo;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddToCartServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("password");
        String userType = (String) session.getAttribute("userType");
        if (userType == null || !userType.equalsIgnoreCase("customer")) {
            response.sendRedirect("login.jsp?message=Access denied ! Please login as customer");
            return ;
            
        }
        
        String userId=userName;
        String prodId=request.getParameter("pid");
        int pQty=Integer.parseInt(request.getParameter("pqty"));
        String action=request.getParameter("action");
        
        CartDaoImpl cartDao=new CartDaoImpl();
        ProductDaoImpl productDao=new ProductDaoImpl();
        
        ProductPojo productPojo=productDao.getProductDetails(prodId);
        int avQty=productDao.getProductQuantity(prodId);
        int cartQty=cartDao.getCartItemCount(userId,prodId);        
        //mera code
        String message="Product not found";
        pQty+=cartQty;
        if(action!=null){
            //logic for buy now
            cartDao.updateProductInCart(new CartPojo(userId,prodId,pQty));
            RequestDispatcher rd=request.getRequestDispatcher("./CartDetailServlet");
            rd.forward(request,response);
        }else if(pQty==cartQty){            
            //logic for remove
            cartDao.removeAProduct(userId, prodId);
            message="Product Removed from the Cart";
            RequestDispatcher rd=request.getRequestDispatcher("userhome.jsp");
           // out.println("<script>document.getElementbyId('message').innerHTML='"+message+"'</script>");    
            List<ProductPojo> products=productDao.getAllProducts();
            request.setAttribute("message",message);
             Map<String,Integer> map=new HashMap<>();
            for(ProductPojo product:products){
                int qty=cartDao.getCartItemCount(userName,product.getProdId());
                map.put(product.getProdId(),qty);
            }
            request.setAttribute("userName", userName);
            request.setAttribute("message",message);
            request.setAttribute("products",products);
            request.setAttribute("map",map);            
            rd.forward(request,response);
            
        }else if(avQty<pQty){
            //demand
            if(avQty==0){
                message="Product out of stock!";
            }else{
                cartDao.updateProductInCart(new CartPojo(userId,prodId,avQty));
                message="Only "+avQty+" no of "+productPojo.getProdName()+" are available";
            }
            DemandDaoImpl demand=new DemandDaoImpl();
            DemandPojo demandPojo=new DemandPojo(userId,prodId,pQty-avQty);
            boolean flag=demand.addProduct(demandPojo);
            if(flag){
                message+="<br/>Later we will mail you when "+productPojo.getProdName()+" will available";
            }
        }else{
            //logic for add to cart
            String status = cartDao.updateProductInCart(new CartPojo(userId, prodId, pQty)); 
    
            //out.println("<script>document.getElementById('message').innerHTML='" + status + "'</script>");       
 
             RequestDispatcher rd=request.getRequestDispatcher("userhome.jsp");
  
           // out.println("<script>document.getElementbyId('message').innerHTML='"+message+"'</script>");    
            List<ProductPojo> products=productDao.getAllProducts();
             Map<String,Integer> map=new HashMap<>();
            for(ProductPojo product:products){
                int qty=cartDao.getCartItemCount(userName,product.getProdId());
                map.put(product.getProdId(),qty);
            }
            request.setAttribute("userName", userName);
            request.setAttribute("products",products);
            request.setAttribute("map",map);    
            request.setAttribute("message",status);
            rd.forward(request,response); 
        }
        
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
