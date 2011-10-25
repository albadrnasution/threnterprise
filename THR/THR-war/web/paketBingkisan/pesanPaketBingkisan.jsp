<%-- 
    Document   : pesanPaketBingkisan
    Created on : Oct 23, 2011, 3:22:44 PM
    Author     : hyouda
--%>

<%@page import="model.PaketBingkisan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% PaketBingkisan pbi = (PaketBingkisan)session.getAttribute("bingkisan"); %>
    <body>
        pesan paket <%= pbi.getPaket_name() %> <br />
        <form action="<%= request.getContextPath() %>/PesanController">
            Jumlah : <input type="text" name="jumlah" /> <br />
            Alamat Tujuan : <input type="text" name="alamat" /> <br />
            Due date : <input type="text" name="due_date" /> <br />
            <input type="hidden" name="menu" value="prosesbuybingkisan" />
            <input type="submit" value="confirm" />
        </form>
    </body>
</html>
