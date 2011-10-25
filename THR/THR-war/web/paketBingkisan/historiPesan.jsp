<%-- 
    Document   : historipesan
    Created on : Oct 25, 2011, 6:35:48 AM
    Author     : hyouda
--%>

<%@page import="model.PesanBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PesanPaket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% 
                ArrayList<PesanPaket> pp = (ArrayList<PesanPaket>)session.getAttribute("getpaket");
                ArrayList<PesanBingkisan> pb = (ArrayList<PesanBingkisan>)session.getAttribute("getbingkisan");
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List Pemesanan Perjalanan</h1>
        <table border="1">
            <tr>
            <th>IDO</th>
            <th>IDP</th>
            <th>IDC</th>
            <th>jumlah_paket</th>
            <th>order date</th>
            <th>due date</th>
            <th>pay_status</th>
            </tr>
            <% for(int i=0;i<pp.size();i++) { 
                PesanPaket paket = pp.get(i);
                %>
            <tr>
                <td><% out.print(paket.getIdo()); %></td>
            <td><% out.print(paket.getIdp()); %></td>
            <td><% out.print(paket.getIdc()); %></td>
            <td><% out.print(paket.getJumlah_paket()); %></td>
            <td><% out.print(paket.getOrder_date()); %></td>
            <td><% out.print(paket.getDue_date()); %></td>
            </tr>
            <% } %>
</table>
<br />
<h1>List Pemesanan Paket Bingkisan</h1>
        <table border="1">
            <tr>
            <th>IDO</th>
            <th>IDP</th>
            <th>IDC</th>
            <th>jumlah_paket</th>
            <th>order date</th>
            <th>due date</th>
            <th>pay_status</th>
            </tr>
            <% for(int i=0;i<pb.size();i++) { 
                PesanBingkisan bingkisan = pb.get(i);
                %>
            <tr>
                <td><% out.print(bingkisan.getIdo()); %></td>
            <td><% out.print(bingkisan.getIdp()); %></td>
            <td><% out.print(bingkisan.getIdc()); %></td>
            <td><% out.print(bingkisan.getJumlah_paket()); %></td>
            <td><% out.print(bingkisan.getOrder_date()); %></td>
            <td><% out.print(bingkisan.getDue_date()); %></td>
            </tr>
            <% } %>
</table>
    </body>
</html>
