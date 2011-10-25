<%-- 
    Document   : webservicePage
    Created on : Oct 24, 2011, 1:32:36 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Webservice"%>
<!DOCTYPE html>
<html>
    <%
        if (session.getAttribute("user") == null || !session.getAttribute("jenisUser").equals("3")) {
            response.sendRedirect("../index.jsp?register=2");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Web Service Management</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>
        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">
                    <h2 style="margin-left: 10px;">Web Services</h2><hr/>
                    <% if (request.getParameter("error") != null) {
                            if (request.getParameter("error").equals("1")) {%>
                    <div style="margin-left: 20px; color: red">Adding new user Failed, Password is incorrect.</div>
                    <% } else if (request.getParameter("error").equals("2")) {%>
                    <div style="margin-left: 20px; color: red">Adding new user Failed, First  name, Last name, Username, or Password can not be blank.</div>
                    <% } else if (request.getParameter("error").equals("3")) {%>
                    <div style="margin-left: 20px; color: red">Adding new user Failed, User already exists.</div>
                    <% } else if (request.getParameter("error").equals("4")) {%>
                    <div style="margin-left: 20px; color: red">Edit User Failed, First name,Last name can not be blank.</div>
                    <% }
                    } else if (request.getParameter("success") != null) {
                        if (request.getParameter("success").equals("1")) {%>
                    <div style="margin-left: 20px; color: red">Web service has been deleted successfully.</div>
                    <% } else if (request.getParameter("success").equals("2")) {%>
                    <div style="margin-left: 20px; color: red">Web Service has been edited successfully.</div>
                    <% } else if (request.getParameter("success").equals("3")) {%>
                    <div style="margin-left: 20px; color: red">Web Service has been added successfully.</div>
                    <% }
                        }%>
                    <table border="1" class="tutturu">
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Server</th>
                            <th>Port</th>
                            <th>Username</th>
                            <th>Password</th>
                        </tr>
                        <%
                            Webservice a = new Webservice();
                            ArrayList<Webservice> s = a.getAllWebservice();
                            for (int i = 0; i < s.size(); i++) {
                                Webservice ws = s.get(i);
                        %>
                        <tr>
                            <td><% out.print(i + 1);%></td>
                            <td><% out.print(ws.getNama());%></td>
                            <td><% out.print(ws.getJenis());%></td>
                            <td><% out.print(ws.getServer()); %></td>
                            <td><% if (ws.getPort() != null) {
                                    out.print(ws.getPort());
                                } else {
                                    out.print("-");
                                }%></td>
                            <td><% if (ws.getUsername() != null) {
                                    out.print(ws.getUsername());
                                } else {
                                    out.print("-");
                                }%></td>
                            <td><% if (ws.getPassword() != null) {
                                    out.print(ws.getPassword());
                                } else {
                                    out.print("-");
                                }%></td>
                            <td style="width: 120px">
                                <a class="thrbutton" href="<%= request.getContextPath()%>/webUserController?manage=ws&action=edit&id=<% out.print(ws.getId());%>">Edit</a>
                                <a class="thrbutton" href="<%= request.getContextPath()%>/webUserController?manage=ws&action=delete&id=<% out.print(ws.getId());%>">Delete</a>
                            </td>
                        </tr>
                        <% }%>
                    </table>
                    <div style="width: 100%;margin-top: 25px;border-top: 1px solid #CCC;position: absolute;bottom:0px">
                        <table style="width: 100%">
                            <tr style="height: 40px;">
                                <td style="padding-left: 40px;">
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>">Home</a>
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>/staff.jsp"><< Back</a>
                                </td>
                                <td style="text-align: right;padding-right: 40px;"><a class="thrbutton"  style="height: 32px; width: 180px" href="<%= request.getContextPath()%>/webUserController?manage=ws&action=add">Add Web Service</a></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>