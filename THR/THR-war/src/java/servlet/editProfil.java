/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Database;
import util.EmailHandler;

/**
 *
 * @author user
 */
@WebServlet(name = "editProfil", urlPatterns = {"/editProfil"})
public class editProfil extends HttpServlet {

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
            // TODO output your page here
            String first_name;
            String last_name;
            String address;
            String phone;
            String place_of_birth;
            String date_of_birth;
            String password;
            String password_confirm;
            String idc;
            int id;
            
            Database db = new Database();
            String sql="";
            
            first_name=request.getParameter("first_name");
            last_name=request.getParameter("last_name");
            address=request.getParameter("address");
            phone=request.getParameter("phone");
            place_of_birth=request.getParameter("place_of_birth");
            date_of_birth=request.getParameter("date_of_birth");
            password=request.getParameter("password");
            password_confirm=request.getParameter("password_confirm");
            idc=request.getParameter("idc");
            id=Integer.parseInt(idc);
            
            if(password.equals("")){
                sql="UPDATE customer SET first_name='"+first_name+"', last_name='"+last_name+"', address='"+address+"', phone='"+phone+"', place_of_birth='"+place_of_birth+"', date_of_birth='"+date_of_birth+"' WHERE idc='"+id+"'";
            }
            else{
                if(password.equals(password_confirm)){
                    EmailHandler e = new EmailHandler();
                    try {
                        password = e.getStringMD5(password);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(editProfil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sql="UPDATE customer SET first_name='"+first_name+"', last_name='"+last_name+"', address='"+address+"', phone='"+phone+"', place_of_birth='"+place_of_birth+"', date_of_birth='"+date_of_birth+"', password='"+password+"' WHERE idc='"+id+"'";
                }
                /*else{
                    String path=request.getRequestURI();
                    String Script = "<html><head><script type='text/javascript'>"
                            + "function hoa() {alert('"+path+"'); window.location='"+path+"';}</script></head>"
                            + "<body onload='hoa();'>asdasdada</body>"
                            + "</html>";
                    out.print(Script);
                }*/
            }
            db.updatingQuery(sql);
            //String s="success";
            //request.setAttribute("sa", s);
            response.sendRedirect("editProfile/editProfilPage.jsp");
            /*out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editProfil</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProfil at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
             
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
