<%-- 
    Document   : index
    Created on : 25-Sep-2011, 17:46:26
    Author     : soleman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to THR</title>
    </head>
    <body style="margin-left: 20px;">
        <h1>Home</h1>
        <a href="<%= request.getContextPath() %>/MelihatP/PerjalanPage.jsp">Daftar Paket Perjalan</a>
        <br />
        <a href="<%= request.getContextPath() %>/MelihatB/BingkisanPage.jsp">Daftar Paket Bingkisan</a>
        <form action="../login" method="POST" style="margin-top: 20px;">
            <table border="0">
                <tbody>
                    <tr>
                        <td colspan="2">Have an account?</td>
                    </tr>
                    <tr>
                        <td>Email </td>
                        <td> <input type="text" name="email" value="" /> </td>
                        </tr>
                    <tr>
                        <td>Password </td>
                        <td> <input type="password" name="password" value="" /> </td>
                    </tr>
                    <tr>
                        <td colspan="2"> <input type="submit" value="Login" name="login" /> </td>
                    </tr>
                    <tr>
                        <td colspan="2">Don't have an account? <a href="<%= request.getContextPath() %>/Registerasi/registerasi.jsp">Register</a></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
