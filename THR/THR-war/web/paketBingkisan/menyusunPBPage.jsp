<%-- 
    Document   : menyusunPBPage
    Created on : Oct 14, 2011, 1:45:27 PM
    Author     : Didik
--%>

<%@page import="javax.swing.text.Document"%>
<%@page import="model.ItemBingkisan"%>
<%@page import="model.PaketBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.BingkisanController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../style/default.css"/>
        <script type="text/javascript" src="../script/default.js"></script>
        <title>Menyusun Paket Bingkisan</title>
    </head>
    <body>
        <%
        BingkisanController pc = new BingkisanController();
        ArrayList<ItemBingkisan> ij = new ArrayList<ItemBingkisan>();
        ij = pc.getItem();
        
        %>
        
        <h1>Menyusun Paket Bingkisan</h1>
        <form name="susunB" method="POST" action="../BingkisanController">
        
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
            
            <ol id="listitem">
            <% 
            for(int i=0; i<ij.size();++i){
            %>
            <li><input type="checkbox" name="s_item" value="<%= ij.get(i).getIdi()%>" /><%= ij.get(i).getName() %> 
                <div><%= ij.get(i).getDescription()%> Harga Rp.<%=  ij.get(i).getBasic_price() %></div>
                <div>
                    <input type="text" name="nitem" value="" /> 
                </div>
            </li>
            <%
            }
            %>
            </ol>
            
        </div>
        <div id="item">
            <div id="atr">Total Harga</div>
            <input type="text" name="s_harga" value="" />
        </div>
        <input type="submit" value="Create" name="create" />
        </form>
    </body>
</html>
