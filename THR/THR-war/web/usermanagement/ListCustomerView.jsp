<%-- 
    Document   : ListCustomerPage
    Created on : Sep 26, 2011, 2:17:23 AM
    Author     : user
--%>


<%@page import="model.DateFormater"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>

<!DOCTYPE html>
<html>
    <%
    if(session.getAttribute("user")==null || !session.getAttribute("jenisUser").equals("3")){
        response.sendRedirect("../index.jsp?register=2");
    }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Customer Management</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>
        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">
                    <h2 style="margin-left: 10px;">List Customer</h2><hr/>
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
                    <div style="margin-left: 20px; color: red">User has been deleted successfully.</div>
                    <% } else if (request.getParameter("success").equals("2")) {%>
                    <div style="margin-left: 20px; color: red">User has been edited successfully.</div>
                    <% } else if (request.getParameter("success").equals("3")) {%>
                    <div style="margin-left: 20px; color: red">user has been added successfully.</div>
                    <% }
                        }%>
                    <table border="1" class="tutturu">
                        <tr>
                            <th>No.</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Place of Birth</th>
                            <th>Date of Birth</th>
                            <th>Aksi</th>
                        </tr>
                        <%
                            Customer a = new Customer();
                            ArrayList<Customer> c = a.getallCustomer();
                            for (int i = 0; i < c.size(); i++) {
                                Customer customer = c.get(i);
                        %>
                        <tr>
                            <td><% out.print(i+1);%></td>
                            <td><% if(customer.getFirst_name()!=null) out.print(customer.getFirst_name()); else out.print("-"); %></td>
                            <td><% if(customer.getLast_name()!=null) out.print(customer.getLast_name()); else out.print("-"); %></td>
                            <td><% if(customer.getAddress()!=null) out.print(customer.getAddress());  else out.print("-"); %></td>
                            <td><% if(customer.getPhone()!=null)out.print(customer.getPhone()); else out.print("-"); %></td>
                            <td><% if(customer.getEmail()!=null)out.print(customer.getEmail());  else out.print("-"); %></td>
                            <td><% if(customer.getPlace_of_birth()!=null)out.print(customer.getPlace_of_birth()); else out.print("-"); %></td>
                            <td><% if(customer.getDate_of_birth()!=null)out.print(DateFormater.formatDateToCalFormat(customer.getDate_of_birth())); else out.print("-"); %></td>
                            <td  style="width: 120px">
                                <a class="thrbutton" href="<%= request.getContextPath() %>/userController?manage=kustomer&action=edit&id=<% out.print(customer.getIdc());%>">Edit</a>
                                <a class="thrbutton" href="<%= request.getContextPath() %>/userController?manage=kustomer&action=delete&id=<% out.print(customer.getIdc());%>">Delete</a>
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
                                <td style="text-align: right;padding-right: 40px;"><a class="thrbutton"  style="height: 32px; width: 180px" href="<%= request.getContextPath() %>/userController?manage=kustomer&action=add">Add Customer</a></td>
                            </tr>
                        </table>
                        
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>