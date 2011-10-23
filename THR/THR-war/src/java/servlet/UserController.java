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
import model.Privilege;
import model.Staff;
import util.EmailHandler;

/**
 *
 * @author hyouda
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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
            /*
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
            HttpSession session = request.getSession();
            if(request.getParameter("menu")==null) {
                //@include file="home.jsp";
                response.sendRedirect("usermanagement/home.jsp");
            }
            else if(request.getParameter("menu").equals("ListStaff"))
            {
                ArrayList<Staff> s =new ArrayList<Staff>();
                Staff t =new Staff();
                s = t.getallStaff();
                s.size();
                session.setAttribute("staff", s);
                response.sendRedirect("usermanagement/ListStaffView.jsp");
            }
            else if(request.getParameter("menu").equals("ListCustomer"))
            {
                ArrayList<Customer> c =new ArrayList<Customer>();
                Customer t =new Customer();
                c = t.getallCustomer();
                c.size();
                out.print(c.size());
                session.setAttribute("customer", c);
                response.sendRedirect("usermanagement/ListCustomerView.jsp");
            }
            else if(request.getParameter("menu").equals("AddStaff")) {
               Staff st = new Staff();
               String mode= "";
               session.setAttribute("staff", st);
               session.setAttribute("mode", mode);
               response.sendRedirect("usermanagement/StaffFormView.jsp");
            }
            else if(request.getParameter("menu").equals("AddCustomer"))
            {
                Customer cu = new Customer();
                String mode= "";
                session.setAttribute("customer", cu);
               session.setAttribute("mode", mode);
                response.sendRedirect("usermanagement/CustomerFormView.jsp");
            }
            else if(request.getParameter("menu").equals("prosesAddStaff"))
       {
            EmailHandler em = new EmailHandler();
            String passmd5 = em.getStringMD5(request.getParameter("password"));
            Staff staff =new Staff();
            staff.setUsername(request.getParameter("username"));
            staff.setFirst_name(request.getParameter("first_name"));
            staff.setLast_name(request.getParameter("last_name"));
            staff.setEmail(request.getParameter("email"));
            staff.setPrevilage(new Privilege(request.getParameter("previlege")));
            staff.setPassword(passmd5);
            staff.addStaff();
            //String res = staff.addStaff();
            //out.print(res);
            String redirectURL = "UserController?menu=ListStaff";
            response.sendRedirect(redirectURL);
        }
        else if(request.getParameter("menu").equals("prosesAddCustomer"))
       {
            out.print(request.getParameter("menu"));
            EmailHandler em = new EmailHandler();
            String passmd5 = em.getStringMD5(request.getParameter("password"));
            Customer customer =new Customer();
            customer.setFirst_name(request.getParameter("first_name"));
            customer.setLast_name(request.getParameter("last_name"));
            customer.setAddress(request.getParameter("address"));
            customer.setPhone(request.getParameter("phone"));
            customer.setPassword(passmd5);
            customer.setEmail(request.getParameter("email"));
            customer.setPlace_of_birth(request.getParameter("place_of_birth"));
            customer.setDate_of_birth2(request.getParameter("date_of_birth"));
            //customer.addCustomer();
            String res = customer.addCustomer();
            //out.print(request.getParameter("date_of_birth"));
            String redirectURL = "UserController?menu=ListCustomer";
            response.sendRedirect(redirectURL);
        }
            else if(request.getParameter("menu").equals("DeleteStaffNotification"))
       {
            Staff t = new Staff();
            Staff staff = t.getStaff(request.getParameter("ids"));
            session.setAttribute("staff", staff);
            String redirectURL = "usermanagement/DeleteStaffFormView.jsp";
            response.sendRedirect(redirectURL);
       } 
           else if(request.getParameter("menu").equals("DeleteCustomerNotification"))
       {
            Customer c = new Customer();
            Customer customer = c.getCustomer(request.getParameter("idc"));
            session.setAttribute("customer", customer);
            String redirectURL = "usermanagement/DeleteCustomerFormView.jsp";
            response.sendRedirect(redirectURL);
       }
                   
        else if(request.getParameter("menu").equals("prosesDeleteStaff"))
       {
            Staff staff =new Staff();
            staff.deleteStaff(request.getParameter("ids"));
            String redirectURL = "UserController?menu=ListStaff";
            response.sendRedirect(redirectURL);
        }
        
        // delete customer
        else if(request.getParameter("menu").equals("prosesDeleteCustomer"))
       {
            //out.print(request.getParameter("menu"));
            Customer customer =new Customer();
            String res = customer.deleteCustomer(request.getParameter("idc"));
            //out.print(res);
            String redirectURL = "UserController?menu=ListCustomer";
            response.sendRedirect(redirectURL);
        }
        
            // proses edit staff
        
        else if(request.getParameter("menu").equals("prosesEditStaff"))
       {
            Staff staff =new Staff();
            staff.setUsername(request.getParameter("username"));
            staff.setFirst_name(request.getParameter("first_name"));
            staff.setLast_name(request.getParameter("last_name"));
            staff.setEmail(request.getParameter("email"));
            staff.setPrevilage(new Privilege(request.getParameter("previlege")));
            staff.editStaff(request.getParameter("ids"));
            //String res = staff.editStaff(request.getParameter("ids"));
            //out.print(res);
            String redirectURL = "UserController?menu=ListStaff";
            response.sendRedirect(redirectURL);
        }
        
        
        // proses edit customer
        else if(request.getParameter("menu").equals("prosesEditCustomer"))
       {
            Customer customer =new Customer();
            customer.setFirst_name(request.getParameter("first_name"));
            customer.setLast_name(request.getParameter("last_name"));
            customer.setAddress(request.getParameter("address"));
            customer.setPhone(request.getParameter("phone"));
            customer.setEmail(request.getParameter("email"));
            customer.setPlace_of_birth(request.getParameter("place_of_birth"));
            customer.setDate_of_birth2(request.getParameter("date_of_birth"));
            customer.editCustomer(request.getParameter("idc"));
            //customer.addCustomer();
            String res =customer.editCustomer(request.getParameter("idc"));
            //out.print(res);
            String redirectURL = "UserController?menu=ListCustomer";
            response.sendRedirect(redirectURL);
        }
        
         else if(request.getParameter("menu").equals("EditStaff"))
       {
            Staff t = new Staff();
            Staff st = t.getStaff(request.getParameter("ids"));
            String mode= "edit";
           session.setAttribute("staff", st);
           session.setAttribute("mode", mode);
           response.sendRedirect("usermanagement/StaffFormView.jsp");
            //String mode= "edit";
               }
                 
                 
        // edit customer form
        else if(request.getParameter("menu").equals("EditCustomer"))
       {
            Customer c = new Customer();
            Customer customer = c.getCustomer(request.getParameter("idc"));
            String mode= "edit";
           session.setAttribute("customer", customer);
           session.setAttribute("mode", mode);
           response.sendRedirect("usermanagement/CustomerFormView.jsp");
            //String mode= "edit";
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
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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
