<%-- 
    Document   : addstaff
    Created on : Sep 26, 2011, 3:37:16 AM
    Author     : user
--%>

        <%@page import="model.Customer"%>
        <% Customer cu = (Customer)session.getAttribute("customer");
            String mode = (String)session.getAttribute("mode");
        %>
        <h1><% if(mode.equals("edit")) out.print("Edit Customer");
                       else out.print("Tambah Customer"); %></h1>
        <br />
        <form action="<% out.print(session.getAttribute("base_url")); %>UserController?menu=<% if(mode.equals("edit")) out.print("prosesEditCustomer");
                       else out.print("prosesAddCustomer"); %>" method="POST">
                       <% if(mode.equals("edit")) out.print("<input type='hidden' name='idc' value='"+cu.getIdc()+"' />"); %>
            <table>
                <tr>
                    <td>First Name : </td>
                    <td><input type="text" name="first_name" value="<% 
                    if(mode.equals("edit")) out.print(cu.getFirst_name()); %>" /></td>
                </tr>
                <tr>
                    <td>Last Name : </td>
                    <td><input type="text" name="last_name" value="<% 
                    if(mode.equals("edit")) out.print(cu.getLast_name()); %>" /></td>
                </tr>
                <tr>
                    <td>Address : </td>
                    <td><input type="text" name="address" value="<% 
                    if(mode.equals("edit")) out.print(cu.getAddress()); %>" /></td>
                </tr>
                <tr>
                    <td>Phone : </td>
                    <td><input type="text" name="phone" value="<% 
                    if(mode.equals("edit")) out.print(cu.getPhone()); %>" /></td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td><input type="password" name="password" <% 
                    if(mode.equals("edit")) out.print("disabled"); %> /></td>
                </tr>
                <tr>
                    <td>Email : </td>
                    <td><input type="text" name="email" value="<% 
                    if(mode.equals("edit")) out.print(cu.getEmail()); %>" /></td>
                </tr>
                <tr>
                    <td>Place of Birth : </td>
                    <td><input type="text" name="place_of_birth" value="<% 
                    if(mode.equals("edit")) out.print(cu.getPlace_of_birth()); %>" /></td>
                </tr>
                <tr>
                    <td>Date of Birth : </td>
                    <td><input type="text" name="date_of_birth" value="<% 
                    if(mode.equals("edit")) out.print(cu.getDate_of_birth2()); %>" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="<%
                    if(mode.equals("edit")) out.print("Edit"); 
                    else out.print("Tambah"); %>" /></td>
                </tr>
            </table>
        </form>