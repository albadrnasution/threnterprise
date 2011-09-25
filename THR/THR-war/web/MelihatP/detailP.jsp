<%-- 
    Document   : detailP
    Created on : Sep 26, 2011, 1:44:57 AM
    Author     : Didik
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ItemJalan"%>
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
        ItemJalan pj = new ItemJalan();
        ArrayList<ItemJalan> pp = new ArrayList<ItemJalan>();
        pp = pj.getItemP(request.getParameter("id"));

        for(int i=0; i<pp.size();i++){
        %>
        <div class="catalog">
            <div id="item">
                <div id="atr">Nama Paket</div>
                <%= pp.get(i).getName() %>
            </div>
            <div id="item">
                <div id="atr">Deskripsi</div>
                <%= pp.get(i).getDescription() %>
            </div>
            <div id="item">
                <div id="atr">Moda</div>
                <%= pp.get(i).getModa() %>
            </div>
            <div id="item">
                <div id="atr">Asal</div>
                <%= pp.get(i).getOrigin() %>
            </div>
            <div id="item">
                <div id="atr">Tujuan</div>
                <%= pp.get(i).getDest() %>
            </div>
            <div id="item">
                <div id="atr">Harga Dewasa</div>
                <%= pp.get(i).getBprice_adult() %>
            </div>
            <div id="item">
                <div id="atr">Harga Anak-anak</div>
                <%= pp.get(i).getBprice_child() %>
            </div>
        </div>
        
        <%
        }
        %>
        
    </body>
</html>
