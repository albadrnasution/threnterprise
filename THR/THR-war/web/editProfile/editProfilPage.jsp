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
<% String i=request.getParameter("i");
    if(i=="1"){
        out.print("<script type='text/javascript'>alert('update success')</script>");
    }
%>
        
        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Page</title>
        <script type="text/javascript">
            function validate(){
                pass=document.getElementById("password").val;ue;
                conf=document.getElementById("password_confirm").value;
                if(pass!=conf){
                    alert("Passwords not equal!"+pass+" "+conf);
                }
                else{
                    document.forms["editProfil"].submit();
                }
            }
            
            function home(){
                window.location("/THR-war/index.jsp");
            }
        </script>
    </head>
    <body>
        
        <h1>Edit Profile Page</h1>
        <form name="editProfil" method="POST" id="editProfil" action="../editProfil">
        <table>
            <tr>
                <td>First Name</td>
                <td>: <input type="text" name="first_name" id="first_name" value="<% if(r.getFirst_name()!=null)out.print(r.getFirst_name()); %>"/></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>: <input type="text" name="last_name" id="last_name" value="<% if(r.getLast_name()!=null)out.print(r.getLast_name()); %>" /></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>: <input type="text" name="phone" id="phone" value="<% if(r.getPhone()!=null)out.print(r.getPhone()); %>" /></td>
            </tr>
            <tr>
                <td>Address</td>
                <td>: <input type="text" name="address" id="address" value="<% if(r.getAddress()!=null)out.print(r.getAddress()); %>" /></td>
            </tr>
            <tr>
                <td>Place of Birth</td>
                <td>: <input type="text" name="place_of_birth" id="place_of_birth" value="<% if(r.getPlace_of_birth()!=null)out.print(r.getPlace_of_birth()); %>" /></td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td>: <input type="text" name="date_of_birth" id="date_of_birth" value="<% if(r.getDate_of_birth()!=null)out.print(r.getDate_of_birth()); %>" /></td>
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
                <td>
                    <input type="hidden" name="idc" value="1" />
                </td>
            </tr>
            <tr align="center">
                <td><a href="/THR-war/index.jsp"><button name="cancelEdit" id="cancelEdit" onclick="home()" value="Cancel" >cancel</button></a></td>
                <td><input type="submit" name="submitProfil" id="submitProfil" onclick="validate();return false" /></td>
            </tr>
        </table>
        </form>
    </body>
</html>
