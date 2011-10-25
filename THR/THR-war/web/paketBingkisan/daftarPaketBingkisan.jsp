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
        <script type="text/javascript" src="../script/jquery-1.6.1.js"></script>
        <title>Daftar Paket Bingkisan</title>
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
                            <img src="../images/gift.png" alt="Bingkisan" style="max-height: 100px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 16px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>

                    </div>
                    <div style="float: left; width: 780px; min-height: 140px; margin-bottom: 40px">
                        <!--                        <h1>Paket Bingkisan</h1>-->
                        <div id="filter-bingkisan">
                            <form name="filter" action="../BingkisanController?mode=cari" method="POST">
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
                                    <tr><td>Description : </td>
                                        <td><input type="text" class="filter" name="desc" value="" /></td>
                                        <td><input type="submit" value="Filter" name="filter" class="thrbutton"/></td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div id="list-bingkisan">
                            <%
                                BingkisanController bc = new BingkisanController();
                                ArrayList<PaketBingkisan> apb = new ArrayList<PaketBingkisan>();
                                apb = bc.showPaket();
                                if (session.getAttribute("filter") != null) {
                                    if (session.getAttribute("PaketBingkisan") != null && session.getAttribute("filter").equals("1")) {
                                        session.setAttribute("filter", "0");
                                        apb = (ArrayList<PaketBingkisan>) session.getAttribute("PaketBingkisan");
                                    }
                                }
                                if (request.getParameter("empty") != null) {
                                    if (request.getParameter("empty").equals("1")) {
                                        apb = new ArrayList<PaketBingkisan>();
                                    }
                                }
                                for (int i = 0; i < apb.size(); i++) {
                            %>

                            <div class="li-bingkisan">
                                <div class="cat-left">
                                    <img src="../images/gift.png" alt="Bingkisan" style="max-height: 24px;" />
                                </div>
                                <div class="cat-middle">
                                    <a href="detailPaketBingkisan.jsp?id=<%= apb.get(i).getIdp()%>"><%= apb.get(i).getPaket_name()%></a><br/>
                                    <p style="font-size: 0.8em"><i><%= apb.get(i).getDescription()%></i></p>
                                </div>
                                <div class="cat-right">
                                    <img src="../images/coin.PNG" alt="Bingkisan" class="ico-mini"  />  <span>Rp<%= apb.get(i).getPrice()%></span><br/>
                                    <a class="thrbutton" href="detailPaketBingkisan.jsp?id=<%= apb.get(i).getIdp()%>">Details</a>
                                    <a href="<%= request.getContextPath() %>/PesanController?menu=buybingkisan&&idp=<%= apb.get(i).getIdp()%>"><input type="button" value="Buy" name="filter" class="thrbutton"/></a>
                                </div>
                            </div>

                            <%
                                }
                                if (request.getParameter("empty") != null) {
                                    if (request.getParameter("empty").equals("1")) {
                            %>
                            <div style="color: red">no results</div>
                            <%                                    }
                                } else if(apb.isEmpty()){
                                    %>
                                    <div>
                                        Tidak ada Paket
                                    </div>
                            <%
                                }
                            %>
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
