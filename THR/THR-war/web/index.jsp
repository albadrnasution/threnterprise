<%-- 
    Document   : index
    Created on : 25-Sep-2011, 17:46:26
    Author     : soleman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@page import="model.City" %>
        <%
        City c = new City();
        out.print(c.getCity().get(0).getName());
        out.print(c.getCity().get(1).getName());
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <a href="<%= request.getContextPath() %>/MelihatP/PerjalanPage.jsp">Daftar Paket Perjalan</a>
        <a href="<%= request.getContextPath() %>/MelihatB/BingkisanPage.jsp">Daftar Paket Bingkisan</a>
        <a href="<%= request.getContextPath() %>/Registerasi/registerasi.jsp">Registerasi</a>
    </body>
</html>
