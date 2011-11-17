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
    <% PaketJalan pbi = (PaketJalan) session.getAttribute("perjalanan");%>
    <body>
        <div>Pesan Paket : <b><%= pbi.getPaket_nama()%></b> </div>&nbsp;
        <form action="<%= request.getContextPath()%>/PesanController">
            <div>Jumlah : <input type="text" name="jumlah" class="filter"/>&nbsp;
                <input type="hidden" name="menu" value="prosesbuyperjalanan" /></div>
            <div><input class="thrbutton" type="submit" value="confirm" /></div>
        </form>
    </body>
</html>
