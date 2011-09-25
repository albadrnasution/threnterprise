<%-- 
    Document   : bingkisanPage
    Created on : Sep 25, 2011, 10:00:42 PM
    Author     : Didik
--%>


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.PaketBingkisan"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../style/default.css"/>
        <title>Paket Bingkisan</title>
    </head>
    <body>
        <h1>Paket Bingkisan</h1>
        <%
        PaketBingkisan pb = new PaketBingkisan();
        ArrayList<PaketBingkisan> apb = new ArrayList<PaketBingkisan>();
        apb = pb.getPaketB();

        for(int i=0; i<apb.size();i++){
        %>
        <div class="catalog">
            <div id="item">
                <div id="atr">Nama Paket</div>
                <a href="detailB.jsp?id=<%= apb.get(i).getIdp() %>"><%= apb.get(i).getPaket_name() %></a>
            </div>
            <div id="item">
                <div id="atr">Deskripsi</div>
                <%= apb.get(i).getDescription() %>
            </div>
            <div id="item">
                <div id="atr">Harga</div>
                <%= apb.get(i).getPrice() %>
            </div>
        </div>
        
        <%
        }
        %>
        
    </body>
</html>

