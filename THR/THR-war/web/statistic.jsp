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
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("index.jsp?register=2");
    } else {
        if (session.getAttribute("jenisUser").equals("0")) {
            response.sendRedirect("index.jsp?register=2");
        } else {
            Staff r = (Staff) session.getAttribute("user");

%>  
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
                <div class="list-sheet">
                <%
                    StatisticController sc = new StatisticController();
                    ArrayList<PesanPaket> ap = new ArrayList<PesanPaket>();
                    ArrayList<PesanPaket> api = new ArrayList<PesanPaket>();
                    ArrayList<PesanBingkisan> ab = new ArrayList<PesanBingkisan>();
                    ArrayList<PesanBingkisan> abi = new ArrayList<PesanBingkisan>();
                    int total1 = 0, total2 = 0, total3 = 0, total4 = 0;
                    //ap = sc.showPesanP("");
                    if (session.getAttribute("sort") != null) {
                        if (session.getAttribute("PesanPaket") != null && session.getAttribute("sort").equals("1")) {
                            session.setAttribute("sort", "0");
                            ap = (ArrayList<PesanPaket>) session.getAttribute("PesanPaket");
                        }
                    }
                    
                    //ab = sc.showPesanB("");
                    if (session.getAttribute("sortb") != null) {
                        if (session.getAttribute("PesanBingkisan") != null && session.getAttribute("sortb").equals("1")) {
                            session.setAttribute("sortb", "0");
                            ab = (ArrayList<PesanBingkisan>) session.getAttribute("PesanBingkisan");
                        }
                    }

                %>
                
                <div style="float: left; width: 164px;">
                        <div style="width: 100px; height: 100px; text-align: center; padding: 16px; margin: 16px; background-color: #CCC;">
                            <img src="images/stats.png" alt="Statistic" style="max-height: 100px;" />
                        </div>
                        <input class="thrbutton" style="margin-left: 16px;margin-top: 10px;width: 132px;"  type="button" value="Print this page" onClick="window.print()">
                </div>
                <div style="float: left; width: 780px; min-height: 140px; margin-bottom: 40px">
                <h1>Statistic</h1>
                <div>        
                <form name="statistik" action="StatisticController?mode=stats" method="POST">
                    <table>
                        <tr>
                            <td>Jenis Paket</td>
                            <td>
                                <select name="ptipe"  style="height: 30px; width: 300px;">
                                    <option>Paket Bingkisan</option>
                                    <option>Paket Perjalanan</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Daftar Item atau Paket</td>
                            <td>
                                <select name="pitem"  style="height: 30px; width: 300px;">
                                    <option>Item</option>
                                    <option>Paket</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Start Date</td>
                            <td>End Date</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" style="height: 25px; width: 300px;" name="start" value="" />
                            </td>
                            <td>
                                <input type="text" style="height: 25px; width: 300px;" name="end" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value="Stats" name="stats" class="thrbutton"/></td>
                        </tr>
                    </table>
                </form>
                </div>
                </div>
                        
                <% if (request.getParameter("empty") != null) {
                        if (request.getParameter("empty").equals("1")) {%>
                <div style="margin-left: 20px; color: red">No result.</div>
                <%}
                }
                if (session.getAttribute("showtable") != null) {
                    if (session.getAttribute("showtable").equals("4")) {
                        session.setAttribute("showtable", "0");
                %>
                <table border="1" class="tutturu">
                    <tr>
                        <td style="width: 5%">No</td>
                        <td style="width: 30%"><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=paket">Nama Paket </a></td>
                        <td style="width: 25%"><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=order">Tanggal Pemesanan </a></td>
                        <td style="width: 15%"><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=payment">Status Pembayaran </a></td>
                        <td style="width: 10%"><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=jumlah">Jumlah </a></td>
                        <td style="width: 15%"><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=harga">Harga </a></td>
                    </tr>
                    <%
                    for(int i=0; i<ap.size();++i){
                    %>
                    <tr>
                        <td> <% if(i!=-1){ out.print(i); } %> </td>
                        <td> <% if(!ap.get(i).getPaket_name().equals(null)){ out.print(ap.get(i).getPaket_name()); } %> </td>
                        <td> <% if(!ap.get(i).getOrder_dateS().equals(null)){ out.print(ap.get(i).getOrder_dateS()); } %> </td>
                        <td> <% if(ap.get(i).isPay_status()){ out.print("Lunas"); }else{ out.print("Belum Lunas"); } %> </td>
                        <td> <%if(!ap.get(i).getPaket_name().equals(null)){ out.print(ap.get(i).getJumlah_paket()); } %> </td>
                        <td> <%if(ap.get(i).getTotal_pendapatan() != -1){ out.print("Rp"+DateFormater.formatCurrency(ap.get(i).getTotal_pendapatan())); } %> </td>
                        
                    </tr>
                    <%
                    total4 = total4 + ap.get(i).getTotal_pendapatan();
                    }
                    %>      
                    <tr><td colspan="4"></td>
                        <td style="text-align: left">Total</td>
                        <td>
                            <% if(total4!=-1){ out.print("Rp"+DateFormater.formatCurrency(total4)); } %>
                        </td>
                    </tr>
                </table>
                
                <% }else if (session.getAttribute("showtable").equals("3")) {
                            session.setAttribute("showtable", "0");
                %>
                    
                <table border="1" class="tutturu">
                    <tr>
                        <td style="width: 5%">No</td>
                        <td style="width: 80%"><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=item">Nama Item </a></td>
                        <td style="width: 15%"><a href="<%= request.getContextPath() %>/StatisticController?type=perjalanan&&orderby=jitem">Jumlah </a></td>
                    </tr>
                    <%
                    //api = sc.showPesanItemP("");
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
                    <% 
                    }else if (session.getAttribute("showtable").equals("2")) {
                            session.setAttribute("showtable", "0");
                    %>
                <table border="1" class="tutturu">
                    <tr>
                        <td style="width: 5%">No</td>
                        <td style="width: 30%"><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=paket">Nama Paket </a></td>
                        <td style="width: 25%"><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=order">Tanggal Pemesanan </a></td>
                        <td style="width: 15%"><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=payment">Status Pembayaran </a></td>
                        <td style="width: 10%"><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=jumlah">Jumlah </a></td>
                        <td style="width: 15%"><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=harga">Harga </a></td>
                    </tr>
                    <%
                    for(int i=0; i<ab.size();++i){
                    %>
                    <tr>
                        <td> <% if(i!=-1){ out.print(i); } %> </td>
                        <td> <% if(!ab.get(i).getPaket_name().equals(null)){ out.print(ab.get(i).getPaket_name()); } %> </td>
                        <td> <% if(!ab.get(i).getOrder_dateS().equals(null)){ out.print(ab.get(i).getOrder_dateS()); } %> </td>
                        <td> <% if(ab.get(i).isPay_status()){ out.print("Lunas"); }else{ out.print("Belum Lunas"); } %> </td>
                        <td> <%if(!ab.get(i).getPaket_name().equals(null)){ out.print(ab.get(i).getJumlah_paket()); } %> </td>
                        <td> <%if(ab.get(i).getTotal_pendapatan() != -1){ out.print("Rp"+DateFormater.formatCurrency(ab.get(i).getTotal_pendapatan())); } %> </td>
                        
                    </tr>
                    <%
                        total2 = total2 + ab.get(i).getTotal_pendapatan();
                    }
                    %>
                    <tr><td colspan="4"></td>
                        <td style="text-align: left">Total</td>
                        <td>
                            <% if(total2!=-1){ out.print("Rp"+DateFormater.formatCurrency(total2)); } %>
                        </td>
                    </tr>
                </table>
                    <%
                     }else if (session.getAttribute("showtable").equals("1")) {
                            session.setAttribute("showtable", "0");
                    %>
                <table border="1" class="tutturu">
                    <tr>
                        <td style="width: 5%">No</td>
                        <td style="width: 70%"><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=item">Nama Item </a></td>
                        <td style="width: 10%"><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=jitem">Jumlah </a></td>
                        <td style="width: 15%"><a href="<%= request.getContextPath() %>/StatisticController?type=bingkisan&&orderby=hitem">Harga </a></td>
                    </tr>
                    <%
                    //abi = sc.showPesanItemB("");
                    if (session.getAttribute("sortitemb") != null) {
                        if (session.getAttribute("PesanItemBingkisan") != null && session.getAttribute("sortitemb").equals("1")) {
                            session.setAttribute("sortitemb", "0");
                            abi = (ArrayList<PesanBingkisan>) session.getAttribute("PesanItemBingkisan");
                        }
                    }
                    total1 = 0;
                    for(int i=0; i<abi.size();i++){
                    %>
                    <tr>
                        <td> <% if(i!=-1){ out.print(i+1); } %> </td>
                        <td> <% if(!abi.get(i).getItem_name().equals(null)){ out.print(abi.get(i).getItem_name()); } %> </td>
                        <td> <% if(abi.get(i).getJumlah_item() != 0){ out.print(abi.get(i).getJumlah_item()); } %> </td>
                        <td> <% if(abi.get(i).getTotal_pendapatan() != 0){ out.print("Rp"+DateFormater.formatCurrency(abi.get(i).getTotal_pendapatan())); } %> </td>                       
                    </tr>
                    <%
                        total1 = total1 + abi.get(i).getTotal_pendapatan();
                    }
                   
                    %>
                    <tr><td colspan="2"></td>
                        <td style="text-align: left">Total</td>
                        <td>
                            <% if(total1!=-1){ out.print("Rp"+DateFormater.formatCurrency(total1)); } %>
                        </td>
                    </tr>
                </table>
                        
                <%      
                    }
                   }
                %>
            </div>
            </div>
        </div>
        <%@include file="layout/footer.jsp" %>
    </body>
</html>
<% }
}%>