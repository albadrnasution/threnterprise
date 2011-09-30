<%-- 
    Document   : daftarPaketBingkisan
    Created on : Sep 30, 2011, 5:21:01 PM
    Author     : Didik
--%>

<%@page import="model.PaketBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.BingkisanController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../style/default.css"/>
        <title>Paket Bingkisan</title>
    </head>
    <body>
        <h1>Paket Bingkisan</h1>
        <div class="left">
            <div>
                <h3>Filter Paket Bingkisan<h3>
            </div>
            <form name="filter" action="../BingkisanController" method="POST">
                <div>
                    Harga
                </div>
                <select name="operator">
                    <option> > </option>
                    <option> < </option>
                </select>
                <input type="text" name="harga" value="" />
                <div>
                    Name
                </div>
                <input type="text" name="name" value="" />
                <div>
                    Description
                </div>    
                <input type="text" name="desc" value="" /><br/>
                <input type="submit" value="Submit" name="filter" />
            </form>
        </div>
        <div class="right">
        <%
        BingkisanController bc = new BingkisanController();
        ArrayList<PaketBingkisan> apb = new ArrayList<PaketBingkisan>();
        apb = bc.showPaket();

        for(int i=0; i<apb.size();i++){
        %>
        <div id="catalog">
            <div id="item">
                <div id="atr">Nama Paket</div>
                <a href="detailPaketBingkisan.jsp?id=<%= apb.get(i).getIdp() %>"><%= apb.get(i).getPaket_name() %></a>
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
        </div>
    </body>
</html>
