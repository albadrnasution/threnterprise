<%-- 
    Document   : menyusunPaketPerjalanan
    Created on : Oct 14, 2011, 1:46:29 PM
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
        <title>Menyusun Paket Perjalanan</title>
    </head>
    <body>
        <%
        PerjalananController pc = new PerjalananController();
        ArrayList<ItemJalan> ij = new ArrayList<ItemJalan>();
        ij = pc.getItem();
        %>
        
        <h1>Menyusun Paket Perjalanan</h1>
        <form name="submit" action="../PerjalananController" method="POST">
        
        <div id="item">
            <div id="atr">Nama Paket</div>
            <input type="text" name="s_nama_paket" value="" />
        </div>
        <div id="item">
            <div id="atr">Deskripsi</div>
            <input type="text" name="s_desc" value="" />
        </div>
        <div id="item">
            <div id="atr">Item Paket</div>
            <% 
            for(int i=0; i<ij.size();++i){
                ArrayList it = new  ArrayList();
            %>
            <input type="checkbox" name="s_item" value="<%= ij.get(i).getIdi()%>" /><%= ij.get(i).getName() %> <br/>
            <div><%= ij.get(i).getDescription() %></div>
            <%
            }
            %>
        </div>
        <div id="item">
            <div id="atr">Total Harga</div>
            <input type="text" name="s_harga" value="" />
        </div>
        <div id="item">
            <div id="atr">Jumlah Penumpang Dewasa</div>
            <input type="text" name="s_nadult" value="" />
        </div>
        <div id="item">
            <div id="atr">Jumlah Penumpang Anak-anak</div>
            <input type="text" name="s_nchild" value="" />
        </div>
        <div id="item">
            <div id="atr">Waktu Keberangkatan</div>
            <input type="text" name="s_time" value="" />
        </div>
        <input type="submit" value="Create" name="create" />
        </form>
    </body>
</html>
