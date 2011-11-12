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
                    ArrayList<PesanBingkisan> ab = new ArrayList<PesanBingkisan>();
                    ArrayList<PesanBingkisan> abi = new ArrayList<PesanBingkisan>();
                    
                    ap = sc.showPesanP("");
                    if (session.getAttribute("sort") != null) {
                        if (session.getAttribute("PesanPaket") != null && session.getAttribute("sort").equals("1")) {
                            session.setAttribute("sort", "0");
                            ap = (ArrayList<PesanPaket>) session.getAttribute("PesanPaket");
                        }
                    }
                    
                    ab = sc.showPesanB("");
                    if (session.getAttribute("sortb") != null) {
                        if (session.getAttribute("PesanBingkisan") != null && session.getAttribute("sortb").equals("1")) {
                            session.setAttribute("sortb", "0");
                            ab = (ArrayList<PesanBingkisan>) session.getAttribute("PesanBingkisan");
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
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=paket">Nama Paket </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=order">Tanggal Pemesanan </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=payment">Status Pembayaran </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=jumlah">Jumlah Paket </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=harga">Harga </a></td>
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
                        <td> <%if(ap.get(i).getTotal_pendapatan() != -1){ out.print(ap.get(i).getTotal_pendapatan()); } %> </td>
                        
                    </tr>
                    <%
                    }

                    %>
                    
                </table>
                    
                <table>
                    <tr>
                        <td>No</td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=item">Nama Item </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=jitem">Jumlah </a></td>
                    </tr>
                    <%
                    api = sc.showPesanItemP("");
                    if (session.getAttribute("sortitem") != null) {
                        if (session.getAttribute("PesanItem") != null && session.getAttribute("sortitem").equals("1")) {
                            session.setAttribute("sortitem", "0");
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
                    
                <table>
                    <tr>
                        <td>No</td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=paket">Nama Paket </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=order">Tanggal Pemesanan </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=payment">Status Pembayaran </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=jumlah">Jumlah Paket </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=harga">Harga </a></td>
                    </tr>
                    <%
                    for(int i=0; i<ab.size();i++){
                    %>
                    <tr>
                        <td> <% if(i!=-1){ out.print(i+1); } %> </td>
                        <td> <% if(!ab.get(i).getPaket_name().equals(null)){ out.print(ab.get(i).getPaket_name()); } %> </td>
                        <td> <% if(!ab.get(i).getOrder_dateS().equals(null)){ out.print(ab.get(i).getOrder_dateS()); } %> </td>
                        <td> <% if(ab.get(i).isPay_status()){ out.print("Lunas"); }else{ out.print("Belum Lunas"); } %> </td>
                        <td> <%if(!ab.get(i).getPaket_name().equals(null)){ out.print(ab.get(i).getJumlah_paket()); } %> </td>
                        <td> <%if(ab.get(i).getTotal_pendapatan() != -1){ out.print(ab.get(i).getTotal_pendapatan()); } %> </td>
                        
                    </tr>
                    <%
                    }

                    %>
                    
                </table>
                    
                <table>
                    <tr>
                        <td>No</td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=item">Nama Item </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=jitem">Jumlah </a></td>
                        <td><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=hitem">Total </a></td>
                    </tr>
                    <%
                    abi = sc.showPesanItemB("");
                    if (session.getAttribute("sortitemb") != null) {
                        if (session.getAttribute("PesanItemBingkisan") != null && session.getAttribute("sortitemb").equals("1")) {
                            session.setAttribute("sortitemb", "0");
                            abi = (ArrayList<PesanBingkisan>) session.getAttribute("PesanItemBingkisan");
                        }
                    }
                    for(int i=0; i<abi.size();i++){
                    %>
                    <tr>
                        <td> <% if(i!=-1){ out.print(i+1); } %> </td>
                        <td> <% if(!abi.get(i).getItem_name().equals(null)){ out.print(abi.get(i).getItem_name()); } %> </td>
                        <td> <% if(abi.get(i).getJumlah_item() != 0){ out.print(abi.get(i).getJumlah_item()); } %> </td>
                        <td> <% if(abi.get(i).getTotal_pendapatan() != 0){ out.print(abi.get(i).getTotal_pendapatan()); } %> </td>                       
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
