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
        <script type="text/javascript" src="../script/jquery-1.6.1.js"></script>
        <title>Daftar Paket Perjalanan</title>
    </head>
    <body>
        <div id="header-wrapper">
            <div id="header">
                <div id="thrlogo"><a href="<%= request.getContextPath()%>"><img src="../images/thrlogo-long.png" style="height: 80px" alt="thrlogo" title="tentative logo"/></a></div>
            </div>
        </div>
        <div id="content-wrapper">
            <div id="content">
                <div class="list-sheet">
                    <div style="float: left; width: 164px;">
                        <div style="width: 100px; height: 100px; text-align: center; padding: 16px; margin: 16px; background-color: #CCC;">
                            <img src="../images/suitcase.png" alt="Bingkisan" style="max-height: 100px;" />
                        </div>
                    </div>
                    <div style="float: left; width: 780px; min-height: 140px; margin-bottom: 40px">
                        <div id="filter-bingkisan">
                            <form name="filter" action="../PerjalananController" method="POST">
                                <table class="filter" >
                                    <tr>
                                        <td>Harga : </td>
                                        <td><select name="operator" class="filter" style="">
                                                <option> > </option>
                                                <option> < </option>
                                            </select>
                                            <input type="text" class="filter" name="harga" value="" style="width: 345px;" />
                                        </td><td></td>
                                    </tr>
                                    <tr><td>Name : </td>
                                        <td><input type="text" class="filter" name="name" value="" /></td><td></td>
                                    </tr>
                                    <tr><td>Route : </td>
                                        <td>
                                            <select name="origin" class="filter" style="width: 150px">
                                                <option> Origin </option>
                                                <option> Origin 1 </option>
                                                <option> Origin 2 </option>
                                            </select>
                                            <select name="destination" class="filter" style="width: 150px">
                                                <option> Destination </option>
                                                <option> Destination 1 </option>
                                                <option> Destination 2 </option>
                                            </select>
                                        
                                        </td>
                                        <td><input type="submit" value="Filter" name="filter" class="thrbutton"/></td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div id="list-perjalanan">
                                    
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
                            
                                <div class="li-perjalanan">
                                    <div class="cat-left">
                                         <img src="../images/suitcase.png" alt="Bingkisan" style="max-height: 24px;" />
                                    </div>
                                    <div class="cat-middle">
                                        <a href="detailPaketPerjalanan.jsp?id=<%= pp.get(i).getIdp() %>"><%= pp.get(i).getPaket_nama() %></a><br/>
                                        <p style="font-size: 0.9em">Berangkat pada <b><%= pp.get(i).getTime() %></b></p>
                                        <p style="font-size: 0.8em"><i><%= pp.get(i).getDescription() %></i></p>
                                    </div>
                                    <div class="cat-vault">
                                        <img src="../images/coin.PNG" alt="Bingkisan" style="max-height: 18px;" /> <span style="font-size: 0.9em">Rp<%= pp.get(i).getTotal_price() %>,-</span><br/>
                                        <img src="../images/adult.png" alt="Bingkisan" style="max-height: 18px;" /> <span style="font-size: 0.9em">Adult : <%= pp.get(i).getNadult() %></span><br/>
                                        <img src="../images/child.png" alt="Bingkisan" style="max-height: 18px;" /> <span style="font-size: 0.9em">Child : <%= pp.get(i).getNchild() %></span><br/>
                                    </div>
                                    <div class="cat-right">
                                        <a class="thrbutton" href="detailPaketPerjalanan.jsp?id=<%= pp.get(i).getIdp() %>">Details</a>
                                        <input type="button" value="Buy" name="filter" class="thrbutton"/>
                                    </div>
                                </div>

                            <%
                            }}
                            %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="footer-wrapper">
            <div id="footer">
                &COPY; 2011, Anpau Ltd.
                <span class="footer-link"><a href="#">About Us</a></span>
            </div>
        </div>
    </body>
</html>
