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
        <%@include file="../layout/head.jsp" %>
        
        <div id="content-wrapper">
            <div id="content">
                
                <div class="list-sheet">
                    <div style="float: left; width: 164px;">
                        <div style="width: 100px; height: 100px; text-align: center; padding: 16px; margin: 16px; background-color: #CCC;">
                            <img src="../images/gift.png" alt="Bingkisan" style="max-height: 100px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 16px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>/paketBingkisan/daftarPaketBingkisan.jsp"><< Back</a>
                        <a class="thrbutton" style="margin-left: 16px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>
                    </div>
                    <div style="float: left; width: 780px; min-height: 140px; margin-bottom: 40px;padding-top: 15px">
                        <h3>Detail Paket</h3>
                        <div>
                            <table id="detail-paket">
                                <tr><td>Nama Paket</td><td>:</td><td>Ini Nama Dummy</td><td rowspan="4">
                                        <button class="thrbutton" style="width: 80px;height: 100px;font-size: 1em">
                                            <img src="../images/Cart.PNG" style="max-height: 48px;margin-top: 6px" /><div style="margin-top: -8px">Buy</div>
                                        </button>
                                    </td></tr>
                                <tr><td>Deskripsi</td><td>:</td><td><p><i>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ...</i></p></td></tr>
                                <tr><td>Harga Paket</td><td>:</td><td>Rp12.000.000,-</td></tr>
                                <tr><td>List Item</td><td>:</td><td></td></tr>
                            </table>
                        </diV>
                        <table id="list-item">
                            <thead>
                                <tr>
                                    <td>Nama Item</td>
                                    <td>Deskripsi</td>
                                </tr>
                            </thead>
                            <tbody>
                        <%
                        BingkisanController bc = new BingkisanController();
                        ArrayList<ItemBingkisan> pp = new ArrayList<ItemBingkisan>();
                        pp = bc.showDetail(request.getParameter("id"));

                        for(int i=0; i<pp.size();i++){
                        %>
                                <tr>
                                    <td><%= pp.get(i).getName() %></td>
                                    <td> <%= pp.get(i).getDescription() %></td>
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
