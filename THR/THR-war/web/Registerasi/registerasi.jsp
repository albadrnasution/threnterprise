<%-- 
    Document   : registerasi
    Created on : 25-Sep-2011, 19:40:33
    Author     : soleman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registeration Page</title>
    </head>
    <body style="margin-left: 30px;">
        <h1>Registeration</h1>
        <form name="registerasi" action="../registerasi" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td colspan="4">Welcome to THR</td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td> <input type="text" name="first_name" value="" /> </td>
                        <td>Last Name</td>
                        <td> <input type="text" name="last_name" value="" /> </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td colspan="3"><input type="text" name="email" value="" /></td>
                    </tr>
                    <tr>
                        <td colspan="4"><input type="submit" value="register" name="register" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
