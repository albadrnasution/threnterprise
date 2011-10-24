/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
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
     * Variable managed contains two value staff and customer. This variable shows on edit request
     * To handle new user, check variable action on parameter.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            boolean add = false;
            if (request.getParameter("action") != null) {
                if (request.getParameter("action").equals("add")) {
                    add = true;
                }
            }
            //Handle For edit, add new Customer
            if (request.getParameter("user").equals("0")) {
                // Declare all variables on edit
                String first_name = new String();
                String last_name = new String();
                String address = new String();
                String phone = new String();
                String place_of_birth = new String();
                String date_of_birth = new String();
                String password = new String();
                String password_confirm = new String();
                String idc = new String();
                int id = 0;
                //Declare variable on add new user
                String email = new String();

                String sql = new String();

                //Get All variables from parameters
                first_name = request.getParameter("first_name");
                last_name = request.getParameter("last_name");
                address = request.getParameter("address");
                phone = request.getParameter("phone");
                place_of_birth = request.getParameter("place_of_birth");
                date_of_birth = request.getParameter("date_of_birth");
                //in management user, admin can not to change password
                if (request.getParameter("managed") == null || add) {
                    password = request.getParameter("password");
                    password_confirm = request.getParameter("password_confirm");
                }
                //when add new user, no idc
                if (!add) {
                    idc = request.getParameter("idc");
                    id = Integer.parseInt(idc);
                }
                //Get variable when add new user
                if (add) {
                    email = request.getParameter("email");
                }

                HttpSession session = request.getSession();
                //Session invalid
                if (session.getAttribute("user") == null) {
                    response.sendRedirect("index.jsp?register=2");
                } else {
                    //Creating object customer
                    Customer cust = new Customer();
                    //If not in management user, object should be from session
                    if (request.getParameter("managed") == null) {
                        cust = (Customer) session.getAttribute("user");
                    } else {
                        cust = cust.getCustomer(idc);
                    }
                    //Checking Validation add, edit. All are unsuccessfull request
                    if (first_name.equals("") || last_name.equals("")) {
                        //Should be redirect
                        if ((request.getParameter("managed") != null) || (add && (password.equals("") || email.equals("")))) {
                            //Error : first_name, last_name, email, or password is blank
                            if (request.getParameter("managed").equals("staff")) {
                                if (add) {
                                    response.sendRedirect("usermanagement/ListStaffView.jsp?error=2");
                                } else {
                                    response.sendRedirect("usermanagement/ListStaffView.jsp?error=4");
                                }
                            } else if (request.getParameter("managed").equals("customer")) {
                                if (add) {
                                    response.sendRedirect("usermanagement/ListCustomerView.jsp?error=2");
                                } else {
                                    response.sendRedirect("usermanagement/ListCustomerView.jsp?error=4");
                                }
                            }
                        } else {
                            //Error : First_name or last_name is blank
                            response.sendRedirect("editProfile/editProfilPage.jsp?success=0");
                        }
                    } else {
                        //Editing without changing password, Add new user impossible to enter this condition
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
                                    if (add) {
                                        response.sendRedirect("usermanagement/ListStaffView.jsp?success=3");
                                    } else {
                                        response.sendRedirect("usermanagement/ListStaffView.jsp?success=2");
                                    }
                                } else if (request.getParameter("managed").equals("customer")) {
                                    if (add) {
                                        response.sendRedirect("usermanagement/ListCustomerView.jsp?success=3");
                                    } else {
                                        response.sendRedirect("usermanagement/ListCustomerView.jsp?success=2");
                                    }
                                }
                            } else {
                                response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                            }
                        } else {
                            //Editing in management, impossible enter this condition
                            if (password.equals(password_confirm)) {
                                EmailHandler e = new EmailHandler();
                                //If not in mode adding new user, set all attributes of object and query builder
                                if (!add) {
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
                                    //Saving into database
                                    Database.setConnection();
                                    Database.updatingQuery(sql);
                                    if (request.getParameter("managed") != null) {
                                        if (request.getParameter("managed").equals("staff")) {
                                            if (add) {
                                                response.sendRedirect("usermanagement/ListStaffView.jsp?success=3");
                                            } else {
                                                response.sendRedirect("usermanagement/ListStaffView.jsp?success=2");
                                            }
                                        } else if (request.getParameter("managed").equals("customer")) {
                                            if (add) {
                                                response.sendRedirect("usermanagement/ListCustomerView.jsp?success=3");
                                            } else {
                                                response.sendRedirect("usermanagement/ListCustomerView.jsp?success=2");
                                            }
                                        }
                                    } else {
                                        response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                                    }
                                } else if (add) {
                                    //Declaring for saving new user and query builder
                                    //Check is exist
                                    boolean found = false;
                                    ArrayList<Customer> lcust = cust.getallCustomer();
                                    for (int i = 0; i < lcust.size() && !found; ++i) {
                                        if (email.equals(lcust.get(i).getEmail())) {
                                            found = true;
                                        }
                                    }
                                    if (found) {
                                        //Redirect Sending Error message
                                        if (request.getParameter("managed").equals("staff")) {
                                            response.sendRedirect("usermanagement/ListStaffView.jsp?error=3");
                                        } else if (request.getParameter("managed").equals("customer")) {
                                            response.sendRedirect("usermanagement/ListCustomerView.jsp?error=3");
                                        }
                                    } else if (!found) {
                                        //Continue for saving
                                        sql = "INSERT INTO customer(first_name,last_name, address, phone, password, email, place_of_birth, date_of_birth)";
                                        //Gathering values
                                        sql = sql.concat("VALUES('" + first_name + "','" + last_name + "'");
                                        if (!address.equals("")) {
                                            sql = sql.concat(",'" + address + "'");
                                        } else {
                                            sql = sql.concat(",Null");
                                        }
                                        if (!phone.equals("")) {
                                            sql = sql.concat(",'" + phone + "'");
                                        } else {
                                            sql = sql.concat(", Null");
                                        }
                                        try {
                                            password = e.getStringMD5(password);
                                        } catch (NoSuchAlgorithmException ex) {
                                            Logger.getLogger(editProfilController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        sql = sql.concat(", '" + password + "'");
                                        sql = sql.concat(", '" + email + "'");
                                        if (!place_of_birth.equals("")) {
                                            sql = sql.concat(", '" + place_of_birth + "'");
                                        } else {
                                            sql = sql.concat(", Null");
                                        }
                                        if (!date_of_birth.equals("")) {
                                            sql = sql.concat(", '" + DateFormater.formatDateToDBFormat(date_of_birth) + "'");
                                        } else {
                                            sql = sql.concat(", Null");
                                        }
                                        sql = sql.concat(")");
                                        //Saving into database
                                        Database.setConnection();
                                        Database.updatingQuery(sql);
                                        if (request.getParameter("managed") != null) {
                                            if (request.getParameter("managed").equals("staff")) {
                                                if (add) {
                                                    response.sendRedirect("usermanagement/ListStaffView.jsp?success=3");
                                                } else {
                                                    response.sendRedirect("usermanagement/ListStaffView.jsp?success=2");
                                                }
                                            } else if (request.getParameter("managed").equals("customer")) {
                                                if (add) {
                                                    response.sendRedirect("usermanagement/ListCustomerView.jsp?success=3");
                                                } else {
                                                    response.sendRedirect("usermanagement/ListCustomerView.jsp?success=2");
                                                }
                                            }
                                        } else {
                                            response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                                        }
                                    }
                                }
                            } else {
                                //Password incorrect
                                if (request.getParameter("managed") != null) {
                                    //Error : first_name, last_name, email, or password is blank
                                    if (request.getParameter("managed").equals("staff")) {
                                        response.sendRedirect("usermanagement/ListStaffView.jsp?error=1");
                                    } else if (request.getParameter("managed").equals("customer")) {
                                        response.sendRedirect("usermanagement/ListCustomerView.jsp?error=1");
                                    }
                                } else {
                                    //Error : First_name or last_name is blank
                                    response.sendRedirect("editProfile/editProfilPage.jsp?success=0");
                                }
                            }
                        }
                    }
                }
            } else {
                //Declaring all variables
                String first_name = new String();
                String last_name = new String();
                String password = new String();
                String password_confirm = new String();
                String idc = new String();
                int id = 0;

                //Declaring variable when adding new user
                String email = new String();
                String username = new String();
                String previlege = new String();

                String sql = new String();

                //Get all variables from parameters
                first_name = request.getParameter("first_name");
                last_name = request.getParameter("last_name");

                //When edit not in management mode or entering new user 
                if (request.getParameter("managed") == null || add) {
                    password = request.getParameter("password");
                    password_confirm = request.getParameter("password_confirm");
                }
                //When not adding new user idc is available
                if (!add) {
                    idc = request.getParameter("idc");
                    id = Integer.parseInt(idc);
                }
                if (add) {
                    email = request.getParameter("email");
                    username = request.getParameter("username");
                    previlege = request.getParameter("previlege");
                }

                HttpSession session = request.getSession();
                //If session invalid, redirected
                if (session.getAttribute("user") == null) {
                    response.sendRedirect("index.jsp");
                } else {
                    //Checking Validation add, edit. All are unsuccessfull request
                    if (first_name.equals("") || last_name.equals("")) {
                        //Should be redirect
                        if ((request.getParameter("managed") != null) || (add && (password.equals("") || email.equals("") || username.equals("") || previlege.equals("")))) {
                            //Error : first_name, last_name, email, or password, previlege, username is blank
                            if (request.getParameter("managed").equals("staff")) {
                                if (add) {
                                    response.sendRedirect("usermanagement/ListStaffView.jsp?error=2");
                                } else {
                                    response.sendRedirect("usermanagement/ListStaffView.jsp?error=4");
                                }
                            } else if (request.getParameter("managed").equals("customer")) {
                                if (add) {
                                    response.sendRedirect("usermanagement/ListCustomerView.jsp?error=2");
                                } else {
                                    response.sendRedirect("usermanagement/ListCustomerView.jsp?error=4");
                                }
                            }
                        } else {
                            //Error : First_name or last_name is blank
                            response.sendRedirect("editProfile/editProfilPage.jsp?success=0");
                        }
                    } else {

                        //Creating an object staff
                        Staff cust = new Staff();
                        //if not in management mode, object should be from session
                        if (request.getParameter("managed") == null) {
                            cust = (Staff) session.getAttribute("user");
                        } else {
                            cust = cust.getStaff(idc);
                        }

                        //When adding a new user, impossible to enter this condition
                        if (password.equals("")) {
                            //Modified object when editing from account owner
                            if (request.getParameter("managed") == null) {
                                cust.setFirst_name(first_name);
                                cust.setLast_name(last_name);
                            }
                            //building sql
                            sql = "UPDATE staff SET first_name='" + first_name + "', last_name='" + last_name + "'";
                            //In management mode, previlege can be edited
                            if (request.getParameter("managed") != null) {
                                if (request.getParameter("managed").equals("staff")) {
                                    sql = sql.concat(", previlege='" + request.getParameter("previlege") + "'");
                                }
                            }
                            sql = sql.concat(" WHERE ids='" + id + "'");
                            Database.setConnection();
                            Database.updatingQuery(sql);
                            //Respone page
                            if (request.getParameter("managed") != null) {
                                if (request.getParameter("managed").equals("staff")) {
                                    if (add) {
                                        response.sendRedirect("usermanagement/ListStaffView.jsp?success=3");
                                    } else {
                                        response.sendRedirect("usermanagement/ListStaffView.jsp?success=2");
                                    }
                                } else if (request.getParameter("managed").equals("customer")) {
                                    if (add) {
                                        response.sendRedirect("usermanagement/ListCustomerView.jsp?success=3");
                                    } else {
                                        response.sendRedirect("usermanagement/ListCustomerView.jsp?success=2");
                                    }
                                }
                            } else {
                                response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                            }
                        } else {
                            //Adding a new user, possible enter this condition
                            if (password.equals(password_confirm)) {
                                EmailHandler e = new EmailHandler();
                                //Condition in editing account
                                if (!add) {
                                    cust.setFirst_name(first_name);
                                    cust.setLast_name(last_name);
                                    cust.setPassword(password);
                                    //building sql
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
                                            if (add) {
                                                response.sendRedirect("usermanagement/ListStaffView.jsp?success=3");
                                            } else {
                                                response.sendRedirect("usermanagement/ListStaffView.jsp?success=2");
                                            }
                                        } else if (request.getParameter("managed").equals("customer")) {
                                            if (add) {
                                                response.sendRedirect("usermanagement/ListCustomerView.jsp?success=3");
                                            } else {
                                                response.sendRedirect("usermanagement/ListCustomerView.jsp?success=2");
                                            }
                                        }
                                    } else {
                                        response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                                    }
                                } else if (add) {
                                    //Condition in adding new account
                                    try {
                                        password = e.getStringMD5(password);
                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(editProfilController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    //Checking user
                                    ArrayList<Staff> lcust = cust.getallStaff();
                                    boolean found = false;
                                    for (int i = 0; i < lcust.size() && !found; ++i) {
                                        if (username.equals(lcust.get(i).getUsername())) {
                                            found = true;
                                        }
                                    }
                                    if (found) {
                                        //Error User sudah ada
                                        if (request.getParameter("managed").equals("staff")) {
                                            response.sendRedirect("usermanagement/ListStaffView.jsp?error=3");
                                        } else if (request.getParameter("managed").equals("customer")) {
                                            response.sendRedirect("usermanagement/ListCustomerView.jsp?error=3");
                                        }
                                    } else if (!found) {
                                        //Building sql
                                        sql = "INSERT INTO staff(username, first_name, last_name, email, previlege, password) VALUES('" + username + "','" + first_name + "','" + last_name + "','" + email + "','" + previlege + "','" + password + "')";
                                        Database.setConnection();
                                        Database.updatingQuery(sql);
                                        if (request.getParameter("managed") != null) {
                                            if (request.getParameter("managed").equals("staff")) {
                                                if (add) {
                                                    response.sendRedirect("usermanagement/ListStaffView.jsp?success=3");
                                                } else {
                                                    response.sendRedirect("usermanagement/ListStaffView.jsp?success=2");
                                                }
                                            } else if (request.getParameter("managed").equals("customer")) {
                                                if (add) {
                                                    response.sendRedirect("usermanagement/ListCustomerView.jsp?success=3");
                                                } else {
                                                    response.sendRedirect("usermanagement/ListCustomerView.jsp?success=2");
                                                }
                                            }
                                        } else {
                                            response.sendRedirect("editProfile/editProfilPage.jsp?success=1");
                                        }
                                    }
                                }
                            } else {
                                //Error Password
                                if (request.getParameter("managed") != null) {
                                    //Error : first_name, last_name, email, or password is blank
                                    if (request.getParameter("managed").equals("staff")) {
                                        response.sendRedirect("usermanagement/ListStaffView.jsp?error=1");
                                    } else if (request.getParameter("managed").equals("customer")) {
                                        response.sendRedirect("usermanagement/ListCustomerView.jsp?error=1");
                                    }
                                } else {
                                    //Error : First_name or last_name is blank
                                    response.sendRedirect("editProfile/editProfilPage.jsp?success=0");
                                }
                            }
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
