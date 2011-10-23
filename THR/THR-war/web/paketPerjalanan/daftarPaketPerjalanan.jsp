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
        <%@include file="../layout/head.jsp" %>
        <div id="content-wrapper">
            <div id="content">
                <div class="list-sheet">
                    <div style="float: left; width: 164px;">
                        <div style="width: 100px; height: 100px; text-align: center; padding: 16px; margin: 16px; background-color: #CCC;">
                            <img src="../images/suitcase.png" alt="Bingkisan" style="max-height: 100px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 16px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>

                    </div>
                    <div style="float: left; width: 780px; min-height: 140px; margin-bottom: 40px">
                        <div id="filter-bingkisan">
                            <form name="filter" action="../PerjalananController?mode=cari" method="POST">
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
                                        <%
                                        City city = new City();
                                        ArrayList<City> lcity = new ArrayList<City>();
                                        lcity=city.getCity();
                                        %>
                                        <td>
                                            <select name="origin" class="filter" style="width: 150px">
                                                <option> Origin </option>
                                                <%
                                                for(int i=0;i<lcity.size();++i){
                                                %>
                                                <option value="<%= lcity.get(i).getIdcity() %>"><%= lcity.get(i).getName() %></option>
                                                <% } %>
                                            </select>
                                            <select name="destination" class="filter" style="width: 150px">
                                                <option> Destination </option>
                                                <%
                                                for(int i=0;i<lcity.size();++i){
                                                %>
                                                <option value="<%= lcity.get(i).getIdcity() %>"><%= lcity.get(i).getName() %></option>
                                                <% } %>
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
                                    if (session.getAttribute("filter") != null) {
                                        if (session.getAttribute("PaketBingkisan") != null && session.getAttribute("filter").equals("1")) {
                                            session.setAttribute("filter", "0");
                                            pp = (ArrayList<PaketJalan>) session.getAttribute("PaketJalan");
                                        }
                                    }
                                    if (request.getParameter("empty") != null) {
                                        if (request.getParameter("empty").equals("1")) {
                                            pp = new ArrayList<PaketJalan>();
                                        }
                                    }

                                    if (request.getParameter("empty") != null) {
                                        if (request.getParameter("empty").equals("1")) {
                                %>
                                <div style="color: red">no results</div>
                                <%                                    }
                                } else if (pp.isEmpty()) {
                                %>
                                <div>
                                    Tidak ada Paket
                                </div>
                                <%                                }
                                    for (int i = 0; i < pp.size(); i++) {
                                %>

                                <div class="li-perjalanan">
                                    <div class="cat-left">
                                        <img src="../images/suitcase.png" alt="Bingkisan" style="max-height: 24px;" />
                                    </div>
                                    <div class="cat-middle">
                                        <a href="detailPaketPerjalanan.jsp?id=<%= pp.get(i).getIdp()%>"><%= pp.get(i).getPaket_nama()%></a><br/>
                                        <p style="font-size: 0.9em">Berangkat pada <b><%= pp.get(i).getTime()%></b></p>
                                        <p style="font-size: 0.8em"><i><%= pp.get(i).getDescription()%></i></p>
                                    </div>
                                    <div class="cat-vault">
                                        <span style="font-size: 0.9em"><img src="../images/coin.PNG" alt="Bingkisan" class="ico-mini"  /> Rp<%= pp.get(i).getTotal_price()%>,-</span><br/>
                                        <span style="font-size: 0.9em"><img src="../images/adult.png" alt="Bingkisan" class="ico-mini" /> Adult : <%= pp.get(i).getNadult()%></span><br/>
                                        <span style="font-size: 0.9em"><img src="../images/child.png" alt="Bingkisan" class="ico-mini" /> Child : <%= pp.get(i).getNchild()%></span><br/>
                                    </div>
                                    <div class="cat-right">
                                        <a class="thrbutton" href="detailPaketPerjalanan.jsp?id=<%= pp.get(i).getIdp()%>">Details</a>
                                        <input type="button" value="Buy" name="filter" class="thrbutton"/>
                                    </div>
                                </div>

                                <%
                                        }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>
