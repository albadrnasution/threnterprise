<%-- 
    Document   : detailB
    Created on : Sep 26, 2011, 4:21:19 AM
    Author     : Didik
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.ItemBingkisan"%>
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
        ItemBingkisan pj = new ItemBingkisan();
        ArrayList<ItemBingkisan> pp = new ArrayList<ItemBingkisan>();
        pp = pj.getItemB(request.getParameter("id"));

        for(int i=0; i<pp.size();i++){
        %>
        <div class="catalog">
            <div id="item">
                <div id="atr">Nama</div>
                <%= pp.get(i).getName() %>
            </div>
            <div id="item">
                <div id="atr">Deskripsi</div>
                <%= pp.get(i).getDescription() %>
            </div>
            <div id="item">
                <div id="atr">Harga Dasar</div>
                <%= pp.get(i).getBasic_price() %>
            </div>
        </div>
        
        <%
        }
        %>
    </body>
</html>
