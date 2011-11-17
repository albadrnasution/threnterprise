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
    <% PaketBingkisan pbi = (PaketBingkisan) session.getAttribute("bingkisan");%>
    <body>
        <div>Pesan Paket : <b><%= pbi.getPaket_name()%> </b></div><br />
        <form action="<%= request.getContextPath()%>/PesanController">
            <table class="filter">
                <tr>
                    <td>Jumlah</td>
                    <td>: <input class="filter" type="text" name="jumlah" /></td>
                </tr>
                <tr>
                    <td>Alamat Tujuan</td>
                    <td>: <input class="filter" type="text" name="alamat" /> </td>
                </tr>
                <tr>
                    <td>Due date</td>
                    <td>: <input class="filter" type="text" name="due_date" /> </td>
                </tr>
                <tr>
                    <td><input type="hidden" name="menu" value="prosesbuybingkisan" /></td>
                    <td><input class="thrbutton" type="submit" value="confirm" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
