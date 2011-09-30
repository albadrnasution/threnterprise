<%-- 
    Document   : daftarPaketPerjalanan
    Created on : Sep 30, 2011, 3:54:01 PM
    Author     : Didik
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="servlet.PerjalananController" %>
<%@page import="model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../style/default.css"/>
        <title>Paket Perjalanan</title>
    </head>
    <body>
        <h1>Daftar Paket Perjalanan</h1>
        <div class="left">
            <div>
                <h3>Filter Paket Perjalanan<h3>
            </div>
            <form name="filter" action="../PerjalananController" method="POST">
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
        PerjalananController pc = new PerjalananController();
        ArrayList<PaketJalan> pp = new ArrayList<PaketJalan>();
        pp = pc.showPaket();

        if(pp.isEmpty()){
        %>
        <div>
            Tidak ada paket!
        </div>
        <%
        }else{
        for(int i=0; i<pp.size();i++){
        %>
        <div id="catalog">
            <div id="item">
                <div id="atr">Nama Paket</div>
                <a href="detailPaketPerjalanan.jsp?id=<%= pp.get(i).getIdp() %>"><%= pp.get(i).getPaket_nama() %></a>
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
        }}
        %>
        </div>
    </body>
</html>
