/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.DateFormater;
import model.Staff;
import util.Database;
import util.EmailHandler;

/**
 *
 * @author user
 */
@WebServlet(name = "editProfilController", urlPatterns = {"/editProfilController"})
public class editProfilController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("user").equals("0")) {
                // TODO output your page here
                String first_name = new String();
                String last_name = new String();
                String address = new String();
                String phone = new String();
                String place_of_birth = new String();
                String date_of_birth = new String();
                String password = new String();
                String password_confirm = new String();
                String idc = new String();
                int id;

                String sql = new String();

                first_name = request.getParameter("first_name");
                last_name = request.getParameter("last_name");
                address = request.getParameter("address");
                phone = request.getParameter("phone");
                place_of_birth = request.getParameter("place_of_birth");
                date_of_birth = request.getParameter("date_of_birth");
                if (request.getParameter("managed") == null) {
                    password = request.getParameter("password");
                    password_confirm = request.getParameter("password_confirm");
                }
                idc = request.getParameter("idc");
                id = Integer.parseInt(idc);

                HttpSession session = request.getSession();
                if (session.getAttribute("user") == null) {
                    response.sendRedirect("index.jsp");
                } else {
                    Customer cust = new Customer();
                    if (request.getParameter("managed") == null) {
                        cust = (Customer) session.getAttribute("user");
                    } else {
                        cust = cust.getCustomer(idc);
                    }
                    if (password.equals("")) {
                        if (request.getParameter("managed") == null) {
                            cust.setFirst_name(first_name);
                            cust.setLast_name(last_name);
                            cust.setAddress(address);
                            cust.setPhone(phone);
                            cust.setPlace_of_birth(place_of_birth);
                            cust.setDate_of_birth(DateFormater.getDateFromViewFormat(date_of_birth));
                        }
                        sql = "UPDATE customer SET first_name='" + first_name + "', last_name='" + last_name + "'";
                        if (!address.equals("")) {
                            sql = sql.concat(", address='" + address + "'");
                        } else {
                            sql = sql.concat(", address=Null");
                        }
                        if (!phone.equals("")) {
                            sql = sql.concat(", phone='" + phone + "'");
                        } else {
                            sql = sql.concat(", phone=Null");
                        }
                        if (!place_of_birth.equals("")) {
                            sql = sql.concat(", place_of_birth='" + place_of_birth + "'");
                        } else {
                            sql = sql.concat(", place_of_birth=Null");
                        }
                        if (!date_of_birth.equals("")) {
                            sql = sql.concat(", date_of_birth='" + DateFormater.formatDateToDBFormat(date_of_birth) + "'");
                        } else {
                            sql = sql.concat(", date_of_birth=Null");
                        }
                        sql = sql.concat(" WHERE idc='" + id + "'");
                        System.out.println(sql);
                        Database.setConnection();
                        Database.updatingQuery(sql);
                        if (request.getParameter("managed") != null) {
                            if (request.getParameter("managed").equals("staff")) {
                                response.sendRedirect("usermanagement/ListStaffView.jsp");
                            } else if (request.getParameter("managed").equals("customer")) {
                                response.sendRedirect("usermanagement/ListCustomerView.jsp");
                            }
                        } else {
                            response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                        }
                    } else {
                        if (password.equals(password_confirm)) {
                            EmailHandler e = new EmailHandler();
                            cust.setFirst_name(first_name);
                            cust.setLast_name(last_name);
                            cust.setAddress(address);
                            cust.setPhone(phone);
                            cust.setPlace_of_birth(place_of_birth);
                            cust.setDate_of_birth(DateFormater.getDateFromViewFormat(date_of_birth));
                            cust.setPassword(password);
                            sql = "UPDATE customer SET first_name='" + first_name + "', last_name='" + last_name + "'";
                            if (!address.equals("")) {
                                sql = sql.concat(", address='" + address + "'");
                            } else {
                                sql = sql.concat(", address=Null");
                            }
                            if (!phone.equals("")) {
                                sql = sql.concat(", phone='" + phone + "'");
                            } else {
                                sql = sql.concat(", phone=Null");
                            }
                            if (!place_of_birth.equals("")) {
                                sql = sql.concat(", place_of_birth='" + place_of_birth + "'");
                            } else {
                                sql = sql.concat(", place_of_birth=Null");
                            }
                            if (!date_of_birth.equals("")) {
                                sql = sql.concat(", date_of_birth='" + DateFormater.formatDateToDBFormat(date_of_birth) + "'");
                            } else {
                                sql = sql.concat(", date_of_birth=Null");
                            }
                            try {
                                password = e.getStringMD5(password);
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(editProfilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (!password.equals("")) {
                                sql = sql.concat(", password='" + password + "'");
                            }
                            sql = sql.concat(" WHERE idc='" + id + "'");
                            Database.setConnection();
                            Database.updatingQuery(sql);
                            if (request.getParameter("managed") != null) {
                                if (request.getParameter("managed").equals("staff")) {
                                    response.sendRedirect("usermanagement/ListStaffView.jsp");
                                } else if (request.getParameter("managed").equals("customer")) {
                                    response.sendRedirect("usermanagement/ListCustomerView.jsp");
                                }
                            } else {
                                response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                            }
                        } else {
                            response.sendRedirect("editProfile/editProfilPage.jsp?success=0");
                        }
                    }
                }
            } else {
                // TODO output your page here
                String first_name = new String();
                String last_name = new String();
                String password = new String();
                String password_confirm = new String();
                String idc = new String();
                int id;

                String sql = new String();

                first_name = request.getParameter("first_name");
                last_name = request.getParameter("last_name");
                if (request.getParameter("managed") == null) {
                    password = request.getParameter("password");
                    password_confirm = request.getParameter("password_confirm");
                }
                idc = request.getParameter("idc");
                id = Integer.parseInt(idc);

                HttpSession session = request.getSession();
                if (session.getAttribute("user") == null) {
                    response.sendRedirect("index.jsp");
                } else {
                    Staff cust = new Staff();
                    if (request.getParameter("managed") == null) {
                        cust = (Staff) session.getAttribute("user");
                    } else {
                        cust = cust.getStaff(idc);
                    }

                    if (password.equals("")) {
                        if (request.getParameter("managed") == null) {
                            cust.setFirst_name(first_name);
                            cust.setLast_name(last_name);
                        }
                        sql = "UPDATE staff SET first_name='" + first_name + "', last_name='" + last_name + "'";
                        if (request.getParameter("managed") != null) {
                            if (request.getParameter("managed").equals("staff")) {
                                sql = sql.concat(", previlege='" + request.getParameter("previlege")+"'");
                            }
                        }
                        sql = sql.concat(" WHERE ids='" + id + "'");
                        Database.setConnection();
                        Database.updatingQuery(sql);
                        if (request.getParameter("managed") != null) {
                            if (request.getParameter("managed").equals("staff")) {
                                response.sendRedirect("usermanagement/ListStaffView.jsp");
                            } else if (request.getParameter("managed").equals("customer")) {
                                response.sendRedirect("usermanagement/ListCustomerView.jsp");
                            }
                        } else {
                            response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                        }
                    } else {
                        if (password.equals(password_confirm)) {
                            EmailHandler e = new EmailHandler();
                            cust.setFirst_name(first_name);
                            cust.setLast_name(last_name);
                            cust.setPassword(password);
                            sql = "UPDATE staff SET first_name='" + first_name + "', last_name='" + last_name + "'";
                            try {
                                password = e.getStringMD5(password);
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(editProfilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (!password.equals("")) {
                                sql = sql.concat(", password='" + password + "'");
                            }
                            sql = sql.concat(" WHERE ids='" + id + "'");
                            Database.setConnection();
                            Database.updatingQuery(sql);
                            if (request.getParameter("managed") != null) {
                                if (request.getParameter("managed").equals("staff")) {
                                    response.sendRedirect("usermanagement/ListStaffView.jsp");
                                } else if (request.getParameter("managed").equals("customer")) {
                                    response.sendRedirect("usermanagement/ListCustomerView.jsp");
                                }
                            } else {
                                response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                            }
                        } else {
                            response.sendRedirect("editProfile/editProfilPage.jsp?success=0");
                        }
                    }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(editProfilController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(editProfilController.class.getName()).log(Level.SEVERE, null, ex);
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
