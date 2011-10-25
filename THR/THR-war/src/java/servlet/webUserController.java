/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Staff;
import model.Webservice;

/**
 *
 * @author soleman
 */
@WebServlet(name = "webUserController", urlPatterns = {"/webUserController"})
public class webUserController extends HttpServlet {

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
            if (request.getParameter("manage").equals("staff")) {
                if (request.getParameter("action") != null) {
                    if (request.getParameter("action").equals("delete")) {
                        Staff s = new Staff();
                        s.deleteStaff(request.getParameter("id"));
                        response.sendRedirect("usermanagement/ListStaffView.jsp?success=1");
                    } else if (request.getParameter("action").equals("edit")) {
                        response.sendRedirect("editProfile/editProfilPage.jsp?manage=edit&target=staff&id=" + request.getParameter("id"));
                    } else if(request.getParameter("action").equals("add")){
                        response.sendRedirect("editProfile/editProfilPage.jsp?manage=add&target=staff");
                    }
                } else {
                    response.sendRedirect("usermanagement/ListStaffView.jsp");
                }
            } else if (request.getParameter("manage").equals("kustomer")) {
                if (request.getParameter("action") != null) {
                    if (request.getParameter("action").equals("delete")) {
                        Customer s = new Customer();
                        s.deleteCustomer(request.getParameter("id"));
                        response.sendRedirect("usermanagement/ListCustomerView.jsp?success=1");
                    } else if (request.getParameter("action").equals("edit")) {
                        response.sendRedirect("editProfile/editProfilPage.jsp?manage=edit&target=customer&id=" + request.getParameter("id"));
                    }else if(request.getParameter("action").equals("add")){
                        response.sendRedirect("editProfile/editProfilPage.jsp?manage=add&target=customer");
                    }
                } else {
                    response.sendRedirect("usermanagement/ListCustomerView.jsp");
                }
            }
            else if (request.getParameter("manage").equals("ws")){
                if(request.getParameter("action")!=null){
                    if(request.getParameter("action").equals("delete")){
                        Webservice ws = new Webservice();
                        ws.deleteWebService(request.getParameter("id"));
                        response.sendRedirect("manageWS/webservicePage.jsp?success=1");
                    }
                    else if(request.getParameter("action").equals("edit")){
                        response.sendRedirect("manageWS/editWebservicePage.jsp?manage=edit&target=ws&id="+request.getParameter("id"));
                    }
                    else if(request.getParameter("action").equals("add")){
                        response.sendRedirect("manageWS/editWebservicePage.jsp?manage=add&target=ws");
                    }
                } else {
                    response.sendRedirect("manageWS/webservicePage.jsp");
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
