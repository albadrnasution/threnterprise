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
        <link rel="stylesheet" href="style/default.css" type="text/css" />
        <link rel="stylesheet" href="style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Welcome to THR</title>
    </head>
    <body>
        <div id="header-wrapper">
            <div id="header">
                <div id="thrlogo"><a href="<%= request.getContextPath()%>"><img src="images/thrlogo.png" style="height: 180px" alt="thrlogo" title="tentative logo"/></a></div>
            </div>
        </div>
        <div id="content-wrapper">
            <div id="content">
                <div id="trias-front-panel">
                    <div class="login-bucket bucket">
                        <form action="login" method="POST" style="margin-top: 20px;">
                            <table border="0"  style="width: 100%">
                                <tbody>
                                    <tr>
                                        <td colspan="2">Have an account?</td>
                                    </tr>
                                    <tr>
                                        <td>Email </td>
                                        <td> <input type="text" name="email" value=""  style="width: 100%;height: 24px"/> </td>
                                    </tr>
                                    <tr>
                                        <td>Password </td>
                                        <td> <input type="password" name="password" value=""  style="width: 100%;height: 24px"/> </td>
                                    </tr>
                                    <tr><td></td>
                                        <td  style="text-align: right;height: auto"> <input type="submit" class="thrbutton"value="Login" name="login" style="width: 120px; height: 40px" /> </td>
                                    </tr>
                                    <%
                                        //Checking if register is success
                                        if (request.getParameter("success") != null) {
                                            if (request.getParameter("success").equals("0")) {
                                    %>
                                    <tr>
                                        <td colspan="2" style="color: red">Wrong Email or Password</td>
                                    </tr>
                                    <%                                            }
                                        } else if (request.getParameter("blank")!=null){
                                            if (request.getParameter("blank").equals("0")) {
                                    %>
                                     <tr>
                                        <td colspan="2" style="color: red">Email or password is blank</td>
                                    </tr>
                                    <%
                                                                           }
                                                                                       }
                                    %>
                                    
                                </tbody>
                            </table>
                        </form>
                    </div>
                    <div class="register-bucket bucket">
                        <div class="paket-button" title="Don't have an account?">
                            <a href="<%= request.getContextPath()%>/Registerasi/registerasi.jsp">Register</a>
                        </div>  
                    </div>
                    <div class="profile-bucket bucket" style="display: none"></div>
                    <div class="bingkis-bucket bucket">
                        <div class="paket-icon"><img src="images/gift.png" alt="Bingkisan"/></div>
                        <div class="paket-button">
                            <a href="<%= request.getContextPath()%>/paketBingkisan/daftarPaketBingkisan.jsp">Paket Bingkisan</a>
                        </div>        
                    </div>
                    <div class="jalan-bucket bucket">
                        <div class="paket-icon"><img src="images/suitcase.png" alt="Perjalanan"/></div>
                        <div class="paket-button">
                            <a href="<%= request.getContextPath()%>/paketPerjalanan/daftarPaketPerjalanan.jsp">Paket Perjalanan</a>
                        </div>        
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
