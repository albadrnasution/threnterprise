<%-- 
    Document   : detailPaketPerjalanan
    Created on : Sep 30, 2011, 4:24:57 PM
    Author     : Didik
--%>

<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.PerjalananController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../style/default.css"/>
        <title>Detail Paket</title>
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
                        <a class="thrbutton" style="margin-left: 16px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>/paketPerjalanan/daftarPaketPerjalanan.jsp"><< Back</a>
                        <a class="thrbutton" style="margin-left: 16px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>
                    </div>
                    <div style="float: left; width: 780px; min-height: 140px; margin-bottom: 40px;padding-top: 15px">
                        <h3>Detail Paket</h3>
                        <table id="detail-paket">
                            <tr><td>Nama Paket</td><td>:</td><td>Nama Paket Perjalanan Dummy</td><td rowspan="7">
                                    <button class="thrbutton" style="width: 80px;height: 100px;font-size: 1em">
                                        <img src="../images/Cart.PNG" style="max-height: 48px;margin-top: 6px" /><div style="margin-top: -8px">Buy</div>
                                    </button>
                                </td></tr>
                            <tr><td>Deskripsi</td><td>:</td><td><p><i>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ...</i></p></td></tr>
                            <tr><td>Asal</td><td>:</td><td>Kota Asal </td></tr>
                            <tr><td>Tujuan</td><td>:</td><td>Kota Tujuan </td></tr>
                            <tr><td>Paket</td><td>:</td><td><img src="../images/adult.png" alt="Bingkisan" class="ico-mini"  /> Dewasa : 1, <img src="../images/child.png" alt="Bingkisan" class="ico-mini" /> Anak-anak N </td></tr>
                            <tr><td>Harga Paket</td><td>:</td><td><img src="../images/coin.PNG" alt="Bingkisan" class="ico-mini"  /> Rp12.000.000,-</td></tr>
                            <tr><td>Daftar Perjalanan dalam paket</td><td>:</td><td></td></tr>
                        </table>
                        <table id="list-item">
                            <thead>
                                <tr>
                                    <td>Nama Item</td>
                                    <td>Deskripsi</td>
                                    <td>Moda</td>
                                    <td>Asal</td>
                                    <td>Tujuan</td>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            PerjalananController pc = new PerjalananController();
                            ArrayList<ItemJalan> ij = new ArrayList<ItemJalan>();
                            ij = pc.showDetail(request.getParameter("id"));

                            for(int i=0; i<ij.size();i++){
                                       %>
                               <tr>
                                   <td> <%= ij.get(i).getName() %></td>
                                   <td> <%= ij.get(i).getDescription() %></td>
                                   <td> <%= ij.get(i).getModa() %></td>
                                   <td> <%= ij.get(i).getOrigin() %></td>
                                   <td> <%= ij.get(i).getDest() %></td>
                               </tr>
                            <%
                            }
                            %>
                            </tbody>
                        </table>
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