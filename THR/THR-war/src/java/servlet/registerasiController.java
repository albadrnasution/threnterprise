/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import util.EmailHandler;

/**
 *
 * @author soleman
 */
@WebServlet(name = "registerasi", urlPatterns = {"/registerasi"})
public class registerasiController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String email = request.getParameter("email");
            // TODO output your page here
            if (first_name.equals("") || last_name.equals("") || email.equals("")) {
                response.sendRedirect("Registerasi/registerasi.jsp?email=0");
            } else {
                EmailHandler eh = new EmailHandler();
                Customer cust = new Customer();
                ArrayList<Customer> listcustomer = cust.getallCustomer();
                //Check if exist
                boolean found = false;
                for (int i = 0; i < listcustomer.size() && !found; ++i) {
                    if (email.equals(listcustomer.get(i).getEmail())) {
                        found = true;
                    }
                }
                try {
                    if (found) {
                        response.sendRedirect("Registerasi/registerasi.jsp?email=1");
                    } else {
                        String pass = eh.sendFirstPassword(email, first_name + " " + last_name);
                        Customer.insertCustomer(first_name, last_name, email, pass);
                        response.sendRedirect("index.jsp?register=1");
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(registerasiController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(registerasiController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(registerasiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
