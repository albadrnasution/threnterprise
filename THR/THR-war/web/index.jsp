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
        
        <%@include file="layout/bighead.jsp" %>
        <div id="content-wrapper">
            <div id="content">
                <div id="trias-front-panel"><%
                    if(session.getAttribute("user")==null){ %>
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
                                        else if (request.getParameter("register")!=null){
                                            if (request.getParameter("register").equals("1")) {
                                    %>
                                     <tr>
                                        <td colspan="2" style="color: red">Registeration Success, Please Log in</td>
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
                    <%
                    }else if (session.getAttribute("jenisUser")=="0"){
                    %>
                    <div class="profile-bucket bucket">
                        <div class="paket-icon"><img src="images/person.png" alt="Bingkisan"/></div>
                        <div class="paket-button">
                            <a href="<%= request.getContextPath()%>/home.jsp">Profil</a>
                        </div>  
                    </div>
                    <%
                    }else{
                    %>
                    <div class="profile-bucket bucket">
                        <div class="paket-icon"><img src="images/admin.png" alt="Bingkisan"/></div>
                        <div class="paket-button">
                            <a href="<%= request.getContextPath()%>/staff.jsp">Manajemen</a>
                        </div>  
                    </div>
                    <%
                    }
                    %>
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
        <%@include file="layout/footer.jsp" %>
    </body>
</html>
