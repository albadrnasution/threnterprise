<%-- 
    Document   : ListCustomerPage
    Created on : Sep 26, 2011, 2:17:23 AM
    Author     : user
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>

<!DOCTYPE html>
<html>
    <%
    if(session.getAttribute("user")==null){
        response.sendRedirect("index.jsp");
    }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Welcome Home</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>
        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">
                    <h2 style="margin-left: 10px;">List Customer</h2><hr/>
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
                            <td><% out.print(customer.getIdc());%></td>
                            <td><% out.print(customer.getFirst_name());%></td>
                            <td><% out.print(customer.getLast_name());%></td>
                            <td><% out.print(customer.getAddress());%></td>
                            <td><% out.print(customer.getPhone());%></td>
                            <td><% out.print(customer.getEmail());%></td>
                            <td><% out.print(customer.getPlace_of_birth());%></td>
                            <td><% out.print(customer.getDate_of_birth());%></td>
                            <td  style="width: 120px">
                                <a class="thrbutton" href="?menu=DeleteCustomerNotification&&idc=<% out.print(customer.getIdc());%>">Edit</a>
                                <a class="thrbutton" href="?menu=DeleteCustomerNotification&&idc=<% out.print(customer.getIdc());%>">Delete</a>
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
                                <td style="text-align: right;padding-right: 40px;"><a class="thrbutton"  style="height: 32px; width: 180px" href="?menu=AddCustomer">Tambah Customer</a></td>
                            </tr>
                        </table>
                        
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>