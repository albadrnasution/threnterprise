<%-- 
    Document   : editProfilPage
    Created on : Sep 25, 2011, 12:28:57 PM
    Author     : user
--%>

<%@page import="model.DateFormater"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Customer" %>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp");
    } else {
        Customer r = (Customer) session.getAttribute("user");
        String notif = request.getParameter("n");
%>        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="../script/jquery-1.6.1.js"></script>
        <title>Edit Profile Page</title>

    </head>
    <body onload="notif(<% out.print(notif);%>);">
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
                        <h1>Edit Profile Page</h1>
                        <form  action="../editProfilController" name="editProfil" method="POST" id="editProfil">
                            <%
                            if(request.getParameter("success")!=null){
                                if (request.getParameter("success").equals("0")) {
                            %>
                            <div style="color: red">Password Yang dimasukkan tidak sama</div>
                            <%                            } else if (request.getParameter("success").equals("1")) {
                            %>
                            <div style="color: red">Update Profile Berhasil dilakukan</div>
                            <%                            }
                                                               }
                            %>
                            <table>
                                <tr>
                                    <td>First Name</td>
                                    <td>: <input class="filter" type="text" name="first_name" id="first_name" value="<% if (r.getFirst_name() != null) {
                                        out.print(r.getFirst_name());
                                    }%>"/></td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td>: <input class="filter" type="text" name="last_name" id="last_name" value="<% if (r.getLast_name() != null) {
                                        out.print(r.getLast_name());
                                    }%>" /></td>
                                </tr>
                                <tr>
                                    <td>Phone</td>
                                    <td>: <input class="filter" type="text" name="phone" id="phone" value="<% if (r.getPhone() != null) {
                                        out.print(r.getPhone());
                                    }%>" /></td>
                                </tr>
                                <tr>
                                    <td>Address</td>
                                    <td>: <input class="filter" type="text" name="address" id="address" value="<% if (r.getAddress() != null) {
                                        out.print(r.getAddress());
                                    }%>" /></td>
                                </tr>
                                <tr>
                                    <td>Place of Birth</td>
                                    <td>: <input class="filter" type="text" name="place_of_birth" id="place_of_birth" value="<% if (r.getPlace_of_birth() != null) {
                                        out.print(r.getPlace_of_birth());
                                    }%>" /></td>
                                </tr>
                                <tr>
                                    <td>Date of Birth</td>
                                    <td>: <input class="filter" type="text" name="date_of_birth" id="date_of_birth" value="<% if (r.getDate_of_birth() != null) {
                                        out.print(DateFormater.formatDateToCalFormat(r.getDate_of_birth()));
                                    }%>" /></td>
                                </tr>
                                <tr>
                                    <td>New Password</td>
                                    <td>: <input class="filter" type="password" name="password" id="password" /></td>
                                </tr>
                                <tr>
                                    <td>Confirm New Password</td>
                                    <td>: <input class="filter" type="password" name="password_confirm" id="password_confirm" /></td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="idc" value="<%= r.getIdc()%>" />
                                    </td>
                                </tr>
                                <tr align="center">
                                    <td style="text-align: right" colspan="2"><a href="../home.jsp"><button class="thrbutton" name="cancelEdit" id="cancelEdit" style="width: 150px;height: 40px;" >Cancel</button></a>
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
