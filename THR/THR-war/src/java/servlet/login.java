/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Staff;
import util.EmailHandler;

/**
 *
 * @author soleman
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Checking the login and send to the right page
     */
    public void checkLogin(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        HttpServletResponse response = null;
        if (session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp?register=2");
        } else {
            if (session.getAttribute("jenisUser").equals("0")) {
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("staff.jsp");
            }
        }

    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            util.EmailHandler em = new EmailHandler();
            String hashPass = em.getStringMD5(password);
            HttpSession session = request.getSession();
            if (!email.equals("") && !password.equals("")) {
                Customer cust = new Customer();
                Staff staff = new Staff();
                ArrayList<Staff> liststaff = staff.getallStaff();
                ArrayList<Customer> listcust = cust.getallCustomer();
                boolean found = false;
                for (int i = 0; i < listcust.size() && !found; ++i) {
                    if (listcust.get(i).getPassword().equals(hashPass) && listcust.get(i).getEmail().equals(email)) {
                        cust = listcust.get(i);
                        session.setAttribute("user", cust);
                        session.setAttribute("jenisUser", "0");
                        session.setMaxInactiveInterval(3600);
                        response.sendRedirect("index.jsp");
                        found = true;
                    }
                }
                for (int i = 0; i < liststaff.size() && !found; ++i) {
                    if (liststaff.get(i).getPassword().equals(hashPass) && liststaff.get(i).getUsername().equals(email)) {
                        staff = liststaff.get(i);
                        session.setAttribute("user", staff);
                        if (staff.getPrevilage().equals("manager")) {
                            session.setAttribute("jenisUser", "1");
                        } else if (staff.getPrevilage().equals("officer")) {
                            session.setAttribute("jenisUser", "2");
                        } else if (staff.getPrevilage().equals("admin")) {
                            session.setAttribute("jenisUser", "3");
                        }
                        response.sendRedirect("index.jsp");
                        found = true;
                    }
                }
                if (!found || session.getAttribute("user") == null) {
                    response.sendRedirect("index.jsp?success=0");
                }
            } else {
                response.sendRedirect("index.jsp?blank=0");
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
