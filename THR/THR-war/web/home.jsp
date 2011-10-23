<%-- 
    Document   : home
    Created on : 15-Oct-2011, 04:03:26
    Author     : soleman
--%>

<%@page import="model.DateFormater"%>
<%@page import="java.text.DateFormat"%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
if(session.getAttribute("user")== null){
    response.sendRedirect("../index.jsp");
} else {
Customer r = (Customer) session.getAttribute("user");
%>   
<!DOCTYPE html>
<html>
    <%
    if(session.getAttribute("user")==null){
        response.sendRedirect("index.jsp");
    }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/default.css" type="text/css" />
        <link rel="stylesheet" href="style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Welcome Home</title>
    </head>
    <body>
        <%@include file="layout/bighead.jsp" %>
        <div id="content-wrapper">
            <div id="content">
                <div class="list-sheet" style="width: 100%;padding-bottom: 40px;">
                    <div style="float: left; width: 204px;">
                        <div style="width: 128px; height: 128px; text-align: center; margin: 40px;margin-bottom: 10px;text-align: center">
                            <img src="images/person.png" alt="Bingkisan" style="max-height: 128px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>
                        <a  class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;" href="<%= request.getContextPath()%>/editProfile/editProfilPage.jsp">Edit Profile</a>
                        <a  class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;" href="">Shopping History</a>
                    </div>
                    <div style="float: left; width: 740px; min-height: 140px; margin-bottom: 40px;padding-top: 15px;">
                        
                        <h2>Welcome Home</h2>
                        <hr/><br/>
                        <div>
                            <table>
                                <tr>
                                    <td>First Name</td>
                                    <td>: <% if(r.getFirst_name()!=null)out.print(r.getFirst_name());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td>: <% if(r.getLast_name()!=null)out.print(r.getLast_name());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Registered Email</td>
                                    <td>: <% if(r.getFirst_name()!=null)out.print(r.getEmail());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Phone</td>
                                    <td>: <% if(r.getPhone()!=null)out.print(r.getPhone());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Address</td>
                                    <td>: <% if(r.getAddress()!=null)out.print(r.getAddress());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Place of Birth</td>
                                    <td>: <% if(r.getPlace_of_birth()!=null)out.print(r.getPlace_of_birth());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Date of Birth</td>
                                    <td>: <% if(r.getDate_of_birth()!=null)out.print(DateFormater.formatDateToCalFormat(r.getDate_of_birth()));else {out.print("-");} %></td>
                                </tr>
                            </table>
                        </div>   
                    </div>  
                </div>
            </div>
        </div>
        <%@include file="layout/footer.jsp" %>
    </body>
</html>
<% } %>