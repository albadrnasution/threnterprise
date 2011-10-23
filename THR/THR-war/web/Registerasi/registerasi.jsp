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
        <link type="text/css" rel="stylesheet" href="../style/default.css"/>
        <title>Registeration Page</title>
    </head>
    <body>
        <div id="header-wrapper">
            <div id="header">
                <div id="thrlogo"><a href="<%= request.getContextPath()%>"><img src="../images/thrlogo.png" style="height: 180px" alt="thrlogo" title="tentative logo"/></a></div>
            </div>
        </div>
        <div id="content-wrapper">
            <div id="content">
                <div class="list-sheet" style="width: 100%;padding-bottom: 100px">
                    <div style="float: left; width: 204px;">
                        <div style="width: 128px; height: 128px; text-align: center; margin: 40px;text-align: center">
                            <img src="../images/register.png" alt="Bingkisan" style="max-height: 128px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>
                    </div>
                    <div style="float: left; width: 740px; min-height: 140px; margin-bottom: 40px;padding-top: 15px;">
                        <h1>Register</h1>
                        <%
                            if(request.getParameter("email")!=null){
                                if(request.getParameter("email").equals("1")){
                        %>
                        <div style="color: red">Alamat has been registered</div>
                        <%
                                                   } else if(request.getParameter("email").equals("0")){
                                                       %>
                         <div style="color: red">Form is blank</div>
                        <%
                                                   }
                                                               }
                        %>
                        <form name="registerasi" action="../registerasi" method="POST">
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td colspan="2">Welcome to THR</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 120px;">First Name</td>
                                        <td> <input type="text" name="first_name" value=""  class="filter" /> </td>
                                    </tr>
                                    <tr>
                                        <td>Last Name</td>
                                        <td> <input type="text" name="last_name" value=""  class="filter" /> </td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td> <input type="text" name="email" value=""  class="filter" /> </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="text-align: right"> <input style="width: 150px; height: 40px" class="thrbutton" type="submit" value="Register" name="register" /> </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
            
        <div id="footer-wrapper">
            <div id="footer">
                &COPY; 2011, Anpau Ltd.
                <span class="footer-link"><a href="#">About Us</a></span>
            </div>
        </div>
    </body>
</html>
