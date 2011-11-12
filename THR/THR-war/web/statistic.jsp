<%-- 
    Document   : statistic
    Created on : Nov 11, 2011, 8:02:56 PM
    Author     : Didik
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="servlet.StatisticController"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/default.css" type="text/css" />
        <link rel="stylesheet" href="style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Statistic</title>
    </head>
    <body>
        <%@include file="layout/head.jsp" %>
        <div id="content-wrapper">
            <div id="content">
                
                <%
                    StatisticController sc = new StatisticController();
                    ArrayList<PesanPaket> ap = new ArrayList<PesanPaket>();
                    ArrayList<PesanPaket> api = new ArrayList<PesanPaket>();
                    
                    ap = sc.showPesan("");
                    if (session.getAttribute("sort") != null) {
                        if (session.getAttribute("PesanPaket") != null && session.getAttribute("sort").equals("1")) {
                            session.setAttribute("sort", "0");
                            ap = (ArrayList<PesanPaket>) session.getAttribute("PesanPaket");
                        }
                    }
                    

                %>
                
                <form name="statistik" action="../SatisticController" method="POST">
                    <div>Packet Type </div>
                    <div>
                        <select name="ptipe">
                            <option>Paket Bingkisan</option>
                            <option>Paket Perjalanan</option>
                        </select>
                        
                    </div>
                </form>
                
                <table>
                    <tr>
                        <td>No</td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?orderby=paket">Nama Paket </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?orderby=order">Tanggal Pemesanan </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?orderby=payment">Status Pembayaran </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?orderby=jumlah">Jumlah Paket </a></td>
                    </tr>
                    <%
                    for(int i=0; i<ap.size();i++){
                    %>
                    <tr>
                        <td> <% if(i!=-1){ out.print(i+1); } %> </td>
                        <td> <% if(!ap.get(i).getPaket_name().equals(null)){ out.print(ap.get(i).getPaket_name()); } %> </td>
                        <td> <% if(!ap.get(i).getOrder_dateS().equals(null)){ out.print(ap.get(i).getOrder_dateS()); } %> </td>
                        <td> <% if(ap.get(i).isPay_status()){ out.print("Lunas"); }else{ out.print("Belum Lunas"); } %> </td>
                        <td> <%if(!ap.get(i).getPaket_name().equals(null)){ out.print(ap.get(i).getJumlah_paket()); } %> </td>
                        
                    </tr>
                    <%
                    }

                    %>
                    
                </table>
                    
                <table>
                    <tr>
                        <td>No</td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?orderby=item">Nama Item </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?orderby=jitem">Jumlah </a></td>
                    </tr>
                    <%
                    api = sc.showPesanItem("");
                    if (session.getAttribute("sort") != null) {
                        if (session.getAttribute("PesanItem") != null && session.getAttribute("sort").equals("1")) {
                            session.setAttribute("sort", "0");
                            api = (ArrayList<PesanPaket>) session.getAttribute("PesanItem");
                        }
                    }
                    for(int i=0; i<api.size();i++){
                    %>
                    <tr>
                        <td> <% if(i!=-1){ out.print(i+1); } %> </td>
                        <td> <% if(!api.get(i).getItem_name().equals(null)){ out.print(api.get(i).getItem_name()); } %> </td>
                        <td> <% if(api.get(i).getJumlah_item() != 0){ out.print(api.get(i).getJumlah_item()); } %> </td>
                        
                    </tr>
                    <%
                    }

                    %>
                    
                </table>
            </div>
        </div>
        <%@include file="layout/footer.jsp" %>
    </body>
</html>
