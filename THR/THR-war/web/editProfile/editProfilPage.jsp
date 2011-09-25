<%-- 
    Document   : editProfilPage
    Created on : Sep 25, 2011, 12:28:57 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Customer" %>

<% Customer c = new Customer(); %>
<% Customer r = new Customer(); %>
<% r = c.getCustomer(1); %>
        
        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Page</title>
    </head>
    <body>
        
        <h1>Edit Profile Page</h1>
        <form name="editProfil" method="POST" action="">
        <table>
            <tr>
                <td>First Name</td>
                <td>: <input type="text" name="first_name" id="first_name" value="<% out.print(r.getFirst_name()); %>"/></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>: <input type="text" name="last_name" id="last_name" value="<% out.print(r.getLast_name()); %>" /></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>: <input type="text" name="phone" id="phone" value="<% out.print(r.getPhone()); %>" /></td>
            </tr>
            <tr>
                <td>Address</td>
                <td>: <input type="text" name="address" id="address" value="<% out.print(r.getAddress()); %>" /></td>
            </tr>
            <tr>
                <td>Place of Birth</td>
                <td>: <input type="text" name="place_of_birth" id="place_of_birth" value="<% out.print(r.getPlace_of_birth()); %>" /></td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td>: <input type="text" name="date_of_birth" id="date_of_birth" value="<% out.print(r.getDate_of_birth()); %>" /></td>
            </tr>
            <tr>
                <td>New Password</td>
                <td>: <input type="password" name="password" id="password" /></td>
            </tr>
            <tr>
                <td>Confirm New Password</td>
                <td>: <input type="password" name="password_confirm" id="password_confirm" /></td>
            </tr>
            <tr>
                <td><input type="submit" name="submitProfil" id="submitProfil" /></td>
            </tr>
        </table>
        </form>
    </body>
</html>
