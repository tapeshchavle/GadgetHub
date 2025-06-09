/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.impl.ContactDaoImpl;
import in.gadgethub.pojo.Contact;
import java.io.IOException;
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
public class ContactServlet extends HttpServlet {

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
        HttpSession session=request.getSession();
        String userEmail = (String) session.getAttribute("userName");
        String password = (String) session.getAttribute("password");
        String userName=request.getParameter("username");
        String useremail=request.getParameter("useremail");
        String message=request.getParameter("message");
        System.out.println(userEmail+" "+password+" "+userName+" "+useremail+" "+message);       
        
        if (userName==null || password==null) {
            response.sendRedirect("login.jsp?message=Access denied ! Please Login as Customer");
        }
       /* else if(!userEmail.equals(useremail)){
            //response.sendRedirect("login.jsp?message=Access denied ! Your Email is not Matched");   
            RequestDispatcher r=request.getRequestDispatcher("./UserHomeServlet");
            request.setAttribute("userMessage", "Your Email Id is not Matched ! Enter Valid Email");
            r.forward(request, response);
        }
        */
        Contact contact=new Contact(useremail,message);
        ContactDaoImpl contactDao=new ContactDaoImpl();
        if(contactDao.checkAlreadyExistOrNot(useremail)){
           // session.setAttribute("userMessage", "Your Response is Aready Exist");                    
            //response.sendRedirect("./UserHomeServlet");  
            request.setAttribute("userMessage", "Your Response is Already Exist");
           // response.sendRedirect("./UserHomeServlet?userMessage=Your Response is Already Exist");              
            
        }else{        
            boolean result=contactDao.saveMessage(contact);
            if(result){
            request.setAttribute("userMessage", "You'r Response is Successfully Added");
            //session.setAttribute("res", true);
            //session.removeAttribute("res");
            //request.setAttribute("res",true);
            
            }
            
            //response.sendRedirect("./UserHomeServlet?userMessage="+result);  
        }
        RequestDispatcher rd=request.getRequestDispatcher("./UserHomeServlet");
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
