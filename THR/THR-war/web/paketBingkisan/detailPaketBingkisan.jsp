<%-- 
    Document   : detailPaketBingkisan
    Created on : Sep 30, 2011, 5:21:18 PM
    Author     : Didik
--%>

<%@page import="model.ItemBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.BingkisanController"%>
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
        BingkisanController bc = new BingkisanController();
        ArrayList<ItemBingkisan> pp = new ArrayList<ItemBingkisan>();
        pp = bc.showDetail(request.getParameter("id"));

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
