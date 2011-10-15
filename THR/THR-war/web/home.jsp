<%-- 
    Document   : home
    Created on : 15-Oct-2011, 04:03:26
    Author     : soleman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
    if(session.getAttribute("Customer")==null){
        response.sendRedirect("index.jsp");
    }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome Home</h1>
    </body>
</html>
