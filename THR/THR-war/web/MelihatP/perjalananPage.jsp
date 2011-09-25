<%-- 
    Document   : perjalananPage
    Created on : Sep 25, 2011, 10:00:29 PM
    Author     : Didik
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.PaketJalan"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../style/default.css"/>
        <title>Paket Perjalanan</title>
    </head>
    <body>
        <h1>Paket Perjalanan</h1>
        <%
        PaketJalan pj = new PaketJalan();
        ArrayList<PaketJalan> pp = new ArrayList<PaketJalan>();
        pp = pj.getPaketP();

        for(int i=0; i<pp.size();i++){
        %>
        <div class="catalog">
            <div id="item">
                <div id="atr">Nama Paket</div>
                <a href="detailP.jsp?id=<%= pp.get(i).getIdp() %>"><%= pp.get(i).getPaket_nama() %></a>
            </div>
            <div id="item">
                <div id="atr">Deskripsi</div>
                <%= pp.get(i).getDescription() %>
            </div>
            <div id="item">
                <div id="atr">Total Harga</div>
                <%= pp.get(i).getTotal_price() %>
            </div>
            <div id="item">
                <div id="atr">Jumlah Penumpang Dewasa</div>
                <%= pp.get(i).getNadult() %>
            </div>
            <div id="item">
                <div id="atr">Jumlah Penumpang Anak-anak</div>
                <%= pp.get(i).getNchild() %>
            </div>
            <div id="item">
                <div id="atr">Waktu Keberangkatan</div>
                <%= pp.get(i).getTime() %>
            </div>
        </div>
        
        <%
        }
        %>
        
    </body>
</html>
