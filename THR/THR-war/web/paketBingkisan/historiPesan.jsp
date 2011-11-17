<%-- 
    Document   : historipesan
    Created on : Oct 25, 2011, 6:35:48 AM
    Author     : hyouda
--%>

<%@page import="model.PesanKirimBingkisan"%>
<%@page import="model.PesanBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PesanPaket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% 
        if(session.getAttribute("getpaket")!=null && session.getAttribute("getbingkisan")!=null && session.getAttribute("getkirim")!=null)
                     {
            
                ArrayList<PesanPaket> pp = (ArrayList<PesanPaket>)session.getAttribute("getpaket");
                ArrayList<PesanBingkisan> pb = (ArrayList<PesanBingkisan>)session.getAttribute("getbingkisan");
                ArrayList<PesanKirimBingkisan> pk = (ArrayList<PesanKirimBingkisan>)session.getAttribute("getkirim");
                
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List Pemesanan Perjalanan</h1>
        <table border="1">
            <tr>
                <th>nama_paket</th>
            <th>jumlah_paket</th>
            <th>order date</th>
            <th>due date</th>
            <th>keterangan bayar</th>
            </tr>
            <% for(int i=0;i<pp.size();i++) { 
                PesanPaket paket = pp.get(i);
                %>
            <tr>
                <td><% out.print(paket.getPaket_name()); %></td>
            <td><% out.print(paket.getJumlah_paket()); %></td>
            <td><% out.print(paket.getOrder_dateS()); %></td>
            <td><% out.print(paket.getDue_date()); %></td>
            <td><% 
            if(paket.getPay_date()==null)
                out.print("belum bayar");
            else
            out.print(paket.getPay_date()); %></td>
            </tr>
            <% } %>
</table>
<br />
<h1>List Pemesanan Paket Bingkisan</h1>
        <table border="1">
            <tr>
                <th>nama paket</th>
            <th>jumlah_paket</th>
            <th>alamat tujuan</th>
            <th>order date</th>
            <th>due date</th>
            
            <th>pay date</th>
            </tr>
            <% for(int i=0;i<pb.size();i++) { 
                PesanBingkisan bingkisan = pb.get(i);
                PesanKirimBingkisan kirim = pk.get(i);
                %>
            <tr>
                <td><% out.print(bingkisan.getPaket_name()); %></td>
            <td><% out.print(bingkisan.getJumlah_paket()); %></td>
            <td><% out.print(kirim.getAlamat()); %></td>
            <td><% out.print(bingkisan.getOrder_dateS()); %></td>
            <td><% out.print(bingkisan.getDue_date2()); %></td>
            
            <td><% 
            if(bingkisan.getPay_date()==null)
                out.print("belum bayar");
            else
            out.print(bingkisan.getPay_date()); %></td>
            </tr>
            <% } %>
</table>
    </body>
</html>
<%
        }
        else
                       {
            response.sendRedirect("../index.jsp");
                       }
%>
