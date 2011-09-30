<%-- 
    Document   : detailPaketPerjalanan
    Created on : Sep 30, 2011, 4:24:57 PM
    Author     : Didik
--%>

<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.PerjalananController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../style/default.css"/>
        <title>Detail Paket</title>
    </head>
    <body>
        <h1>Detail Paket</h1>
        <%
        PerjalananController pc = new PerjalananController();
        ArrayList<ItemJalan> ij = new ArrayList<ItemJalan>();
        ij = pc.showDetail(request.getParameter("id"));

        for(int i=0; i<ij.size();i++){
        %>
        <div class="catalog">
            <div id="item">
                <div id="atr">Nama Paket</div>
                <%= ij.get(i).getName() %>
            </div>
            <div id="item">
                <div id="atr">Deskripsi</div>
                <%= ij.get(i).getDescription() %>
            </div>
            <div id="item">
                <div id="atr">Moda</div>
                <%= ij.get(i).getModa() %>
            </div>
            <div id="item">
                <div id="atr">Asal</div>
                <%= ij.get(i).getOrigin() %>
            </div>
            <div id="item">
                <div id="atr">Tujuan</div>
                <%= ij.get(i).getDest() %>
            </div>
            <div id="item">
                <div id="atr">Harga Dewasa</div>
                <%= ij.get(i).getBprice_adult() %>
            </div>
            <div id="item">
                <div id="atr">Harga Anak-anak</div>
                <%= ij.get(i).getBprice_child() %>
            </div>
        </div>
        
        <%
        }
        %>
        
    </body>
</html>
