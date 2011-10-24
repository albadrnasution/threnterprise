<%-- 
    Document   : editProfilPage
    Created on : Sep 25, 2011, 12:28:57 PM
    Author     : user
--%>

<%@page import="model.Staff"%>
<%@page import="model.DateFormater"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Customer" %>

<%    
    if (session.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp?register=2");
    } else {
        String jenisUser = new String();
        Customer r = new Customer();
        Staff s = new Staff();
        String managed = new String();
        boolean add = false;
        if (session.getAttribute("jenisUser").equals("0")) {
            r = (Customer) session.getAttribute("user");
            jenisUser = (String) session.getAttribute("jenisUser");
        } else {
            s = (Staff) session.getAttribute("user");
            jenisUser = (String) session.getAttribute("jenisUser");
        }
        if (request.getParameter("manage") != null) {
            if (request.getParameter("manage").equals("edit")) {
                if (request.getParameter("target").equals("staff")) {
                    s = s.getStaff(request.getParameter("id"));
                    managed = request.getParameter("target");
                } else if (request.getParameter("target").equals("customer")) {
                    r = r.getCustomer(request.getParameter("id"));
                    jenisUser = "0";
                    managed = request.getParameter("target");
                }
            } else if (request.getParameter("manage").equals("add")) {
                if (request.getParameter("target").equals("staff")) {
                    s = new Staff();
                    managed = request.getParameter("target");
                    jenisUser = "1";
                    add = true;
                } else if (request.getParameter("target").equals("customer")) {
                    r = new Customer();
                    managed = request.getParameter("target");
                    jenisUser = "0";
                    add = true;
                }
            }
        }
        
%>        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="../script/jquery-1.6.1.js"></script>
        <title><% if (add) {
                out.print("Add User Page");
            } else {
                out.print("Edit Profile Page");
            }%></title>

    </head>
    <div id="header-wrapper">
        <div id="header">
            <div id="thrlogo"><a href="<%= request.getContextPath()%>"><img src="../images/thrlogo.png" style="height: 180px" alt="thrlogo" title="tentative logo"/></a></div>
        </div>
    </div>
    <div id="content-wrapper">
        <div id="content">
            <div class="list-sheet" style="width: 100%;padding-bottom: 40px;">
                <div style="float: left; width: 204px;">
                    <div style="width: 128px; height: 128px; text-align: center; margin: 40px;margin-bottom: 10px;text-align: center">
                        <img src="../images/person.png" alt="Bingkisan" style="max-height: 128px;" />
                    </div>
                    <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>
                    <br/><a  class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;" href="">Shopping History</a>
                </div>
                <div style="float: left; width: 740px; min-height: 140px; margin-bottom: 40px;padding-top: 15px;">
                    <h1><% if (add) {
                            out.print("Add User Page");
                        } else {
                            out.print("Edit Profile Page");
                        }%></h1>
                    <form  action="../editProfilController?user=<%= jenisUser%><% if (!managed.equals("")) {
                            out.print("&managed=" + managed);
                        }
                        if (add) {
                            out.print("&action=add");
                        }                        %>  " name="editProfil" method="POST" id="editProfil">
                        <%                            
                            if (request.getParameter("success") != null) {
                                if (request.getParameter("success").equals("0")) {
                        %>
                        <div style="color: red">Password is not correct, or first name and last name should not be blank. </div>
                        <%                            } else if (request.getParameter("success").equals("1")) {
                        %>
                        <div style="color: red">Profile updated successfully. </div>
                        <%                            }
                            }
                        %>
                        <table>
                            <tr>
                                <td>First Name</td>
                                <td>: <input class="filter" type="text" name="first_name" id="first_name" value="<% //if (r.getFirst_name() != null) {
                                    if (!add) {
                                        if (!jenisUser.equals("0")) {
                                            if (s.getFirst_name() != null) {
                                                out.print(s.getFirst_name());
                                            } else {
                                                out.print("");
                                            }
                                        } else {
                                            if (r.getFirst_name() != null) {
                                                out.print(r.getFirst_name());
                                            } else {
                                                out.print("");
                                            }
                                        }
                                    }%>"/></td>
                            </tr>
                            <tr>
                                <td>Last Name</td>
                                <td>: <input class="filter" type="text" name="last_name" id="last_name" value="<%                                    
                                    if (!add) {
                                        if (!jenisUser.equals("0")) {
                                            if (s.getLast_name() != null) {
                                                out.print(s.getLast_name());
                                            }
                                        } else {
                                            if (r.getLast_name() != null) {
                                                out.print(r.getLast_name());
                                            }
                                        }
                                    }%>" /></td>
                            </tr>
                            <% if (add) {%>
                            <tr>
                                <td>Email</td>
                                <td>: <input class="filter" type="text" name="email" id="email" value=""/></td>
                            </tr>
                            <% if (managed.equals("staff")) {%>
                            <tr>
                                <td>Username</td>
                                <td>: <input class="filter" type="text" name="username" id="email" value=""/></td>
                            </tr>
                            <% }
                                }
                                if (jenisUser.equals("0") || (!add && managed.equals("customer"))) {%>
                            <tr>
                                <td>Phone</td>
                                <td>: <input class="filter" type="text" name="phone" id="phone" value="<% if (!add) {
                                        if (r.getPhone() != null) {
                                            out.print(r.getPhone());
                                        }
                                    }%>" /></td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td>: <input class="filter" type="text" name="address" id="address" value="<% if (!add) {
                                        if (r.getAddress() != null) {
                                            out.print(r.getAddress());
                                        }
                                    }%>" /></td>
                            </tr>
                            <tr>
                                <td>Place of Birth</td>
                                <td>: <input class="filter" type="text" name="place_of_birth" id="place_of_birth" value="<% if (!add) {
                                        if (r.getPlace_of_birth() != null) {
                                            out.print(r.getPlace_of_birth());
                                        }
                                    }%>" /></td>
                            </tr>
                            <tr>
                                <td>Date of Birth</td>
                                <td>: <input class="filter" type="text" name="date_of_birth" id="date_of_birth" value="<% if (!add) {
                                        if (r.getDate_of_birth() != null) {
                                            out.print(DateFormater.formatDateToCalFormat(r.getDate_of_birth()));
                                        }
                                    }%>" /></td>
                            </tr>
                            <% }%>
                            <%                                
                                if (managed.equals("staff")) {
                            %>
                            <tr>
                                <td>Previlege</td>
                                <td>: <select name="previlege" class="filter" style="width: 150px">
                                        <option value="officer" <% if (!add) {
                                                if (s.getPrevilage().equals("officer")) {
                                                    out.print("selected");
                                                }
                                            }%>>Officer</option>
                                        <option value="manager" <% if (!add) {
                                                if (s.getPrevilage().equals("manager")) {
                                                    out.print("selected");
                                                }
                                            }%>>Manager</option>
                                        <option value="admin" <% if (!add) {
                                                if (s.getPrevilage().equals("admin")) {
                                                    out.print("selected");
                                                }
                                            }%>>Admin</option>
                                    </select>
                                </td>
                            </tr>
                            <%                                }
                                if (managed.equals("") || add) {
                            %>
                            <tr>
                                <td>New Password</td>
                                <td>: <input class="filter" type="password" name="password" id="password" /></td>
                            </tr>
                            <tr>
                                <td>Confirm New Password</td>
                                <td>: <input class="filter" type="password" name="password_confirm" id="password_confirm" /></td>
                            </tr>
                            <%                                }
                            %>
                            <tr>
                                <td>
                                    <input type="hidden" name="idc" value="<%                                        
                                        if (!add) {
                                            if (!jenisUser.equals("0")) {
                                                if (s.getIds() != 0) {
                                                    out.print(s.getIds());
                                                }
                                            } else {
                                                if (r.getIdc() != 0) {
                                                    out.print(r.getIdc());
                                                }
                                            }
                                        }
                                           %>" />
                                </td>
                            </tr>
                            <tr align="center">
                                <td style="text-align: right" colspan="2"><a href="<% if (managed.equals("staff")) {
                                        out.print(request.getContextPath() + "/usermanagement/ListStaffView.jsp");
                                    } else if (managed.equals("customer")) {
                                        out.print(request.getContextPath() + "/usermanagement/ListCustomerView.jsp");
                                    } else if (jenisUser.equals("0")) {
                                        out.print(request.getContextPath() + "/home.jsp");
                                    } else if (!jenisUser.equals("0")) {
                                        out.print(request.getContextPath() + "/staff.jsp");
                                    }%>"><button class="thrbutton" name="cancelEdit" id="cancelEdit" style="width: 150px;height: 40px;" >Cancel</button></a>
                                    <input  class="thrbutton" type="submit" name="submitProfil" id="submitProfil" style="width: 150px;height: 40px;"  /></td>
                            </tr>
                        </table>
                    </form>
                </div>  
            </div>
        </div>
    </div>
    <div id="footer-wrapper">
        <div id="footer">
            &COPY; 2011, Anpau Ltd.
            <span class="footer-link"><a href="#">About Us</a></span>
        </div>
    </div>
</body>
</html>
<%        
    }
%>
