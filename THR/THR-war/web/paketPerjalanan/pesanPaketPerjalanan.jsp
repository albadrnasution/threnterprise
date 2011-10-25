<%-- 
    Document   : pesanPaketPerjalanan
    Created on : Oct 24, 2011, 5:08:42 PM
    Author     : hyouda
--%>

<%@page import="model.PaketJalan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% PaketJalan pbi = (PaketJalan)session.getAttribute("perjalanan"); %>
    <body>
        pesan paket <%= pbi.getPaket_nama() %> <br />
        <form action="<%= request.getContextPath() %>/PesanController">
            Jumlah : <input type="text" name="jumlah" /> <br />
            Due date : <input type="text" name="due_date" /> <br />
            <input type="hidden" name="menu" value="prosesbuyperjalanan" />
            <input type="submit" value="confirm" />
        </form>
    </body>
</html>
