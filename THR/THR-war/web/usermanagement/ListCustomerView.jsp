<%-- 
    Document   : ListCustomerPage
    Created on : Sep 26, 2011, 2:17:23 AM
    Author     : user
--%>


        <%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
        <% 
                ArrayList<Customer> c = (ArrayList<Customer>)session.getAttribute("customer");
        %>
<h1>List Customer</h1>
        <table border="1">
            <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Password</th>
            <th>Email</th>
            <th>Place of Birth</th>
            <th>Date of Birth</th>
            <th>Aksi</th>
            </tr>
            <% for(int i=0;i<c.size();i++) { 
                Customer customer = c.get(i);
                %>
            <tr>
                <td><% out.print(customer.getIdc()); %></td>
            <td><% out.print(customer.getFirst_name()); %></td>
            <td><% out.print(customer.getLast_name()); %></td>
            <td><% out.print(customer.getAddress()); %></td>
            <td><% out.print(customer.getPhone()); %></td>
            <td><% out.print(customer.getPassword()); %></td>
            <td><% out.print(customer.getEmail()); %></td>
            <td><% out.print(customer.getPlace_of_birth()); %></td>
            <td><% out.print(customer.getDate_of_birth()); %></td>
            <td><a href="<% out.print(session.getAttribute("base_url")); %>UserController?menu=DeleteCustomerNotification&&idc=<% out.print(customer.getIdc());%>">delete</a> | 
                <a href="<% out.print(session.getAttribute("base_url")); %>UserController?menu=EditCustomer&&idc=<% out.print(customer.getIdc());%>">edit</a></td>
            </tr>
            <% } %>
</table>
<a href="<% out.print(session.getAttribute("base_url")); %>UserController?menu=AddCustomer">tambah customer</a><br />
<a href="<% out.print(session.getAttribute("base_url")); %>UserController">kembali</a> 