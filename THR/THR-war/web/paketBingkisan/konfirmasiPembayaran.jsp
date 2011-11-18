<%-- 
    Document   : konfirmasipembayaran
    Created on : Oct 25, 2011, 6:35:48 AM
    Author     : hyouda
--%>

<%@page import="model.DateFormater"%>
<%@page import="model.PesanKirimBingkisan"%>
<%@page import="model.PesanBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PesanPaket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if (session.getAttribute("getpaket") != null && session.getAttribute("getbingkisan") != null) {

            ArrayList<PesanPaket> pp = (ArrayList<PesanPaket>) session.getAttribute("getpaket");
            ArrayList<PesanBingkisan> pb = (ArrayList<PesanBingkisan>) session.getAttribute("getbingkisan");
            ArrayList<PesanKirimBingkisan> pk = (ArrayList<PesanKirimBingkisan>) session.getAttribute("getkirim");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <title>Konfirmasi Pembayaran</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %> 
        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">
                    <h2 style="margin-left: 10px;">List Pemesanan Perjalanan</h2><hr/>
                    <table border="1" class="tutturu">
                        <tr>
                            <th>Nama</th>
                            <th>Nama Paket</th>
                            <th>Jumlah</th>
                            <th>Tagihan</th>
                            <th>Transfer</th>
                            <th>Due Date</th>
                            <th>Tanggal Transfer</th>
                            <th>Aksi</th>
                        </tr>
                        <% for (int i = 0; i < pp.size(); i++) {
                                PesanPaket paket = pp.get(i);
                        %>
                        <tr>
                            <td><% out.print(paket.getFirst_name()+" "+paket.getLast_name());%></td>
                            <td><% out.print(paket.getPaket_name());%></td>
                            <td><% out.print(paket.getJumlah_paket());%></td>
                            <td><% out.print("Rp"+DateFormater.formatCurrency(paket.getJumlah_paket()*paket.getHarga_paket()));%></td>
                            <td><% out.print("Rp"+DateFormater.formatCurrency(paket.getUang_terbayar()));%></td>
                            <td><% out.print(DateFormater.formatDateToCalFormat(paket.getPay_date()));%></td>
                            <td><%
                                if (paket.getPay_date() == null) {
                                    out.print("belum bayar");
                                } else if (paket.isPay_status()) {
                                    out.print(DateFormater.formatDateToCalFormat(paket.getPay_date()));
                                } else {
                                    out.print("belum dicek");
                                }%></td>
                            <td><a href="<%= request.getContextPath()%>/PesanController?paket=perjalanan&&ido=<%= Integer.toString(paket.getIdo())%>&&menu=<%
                                // link konfirm/cancel
                                if (paket.isPay_status()) {
                                    out.print("cancelbayar");
                                } else {
                                    out.print("konfirmbayar");
                                }%>
                                   "><%
                                       if (paket.isPay_status()) {
                                           out.print("<input type=\"button\" value=\"Cancel\" name=\"filter\" class=\"thrbutton\"/>");
                                       } else {
                                           out.print("<input type=\"button\" value=\"Konfirm\" name=\"filter\" class=\"thrbutton\"/>");
                                       }
                                    %>
                                </a>
                            </td>
                        </tr>
                        <% }%>
                    </table>
                    <br />
                    <h2 style="margin-left: 10px;">List Pemesanan Bingkisan</h2><hr/>
                    <table border="1" class="tutturu">
                        <tr>
                            <th>Nama</th>
                            <th>Nama Paket</th>
                            <th>Jumlah</th>
                            <th>Tujuan</th>
                            <th>Tagihan</th>
                            <th>Transfer</th>
                            <th>Due Date</th>
                            <th>Tanggal Transfer</th>
                            <th>Aksi</th>
                        </tr>
                        <% 
                        for (int i = 0; i < pb.size(); i++) {
                                PesanBingkisan bingkisan = pb.get(i);
                                PesanKirimBingkisan kirim = pk.get(i);
                        %>
                        <tr>
                            <td><% out.print(bingkisan.getFirst_name()+" "+bingkisan.getLast_name());%></td>
                            <td><% out.print(bingkisan.getPaket_name());%></td>
                            <td><% out.print(bingkisan.getJumlah_paket());%></td>
                            <td><% out.print(kirim.getAlamat());%></td>
                            <td><% out.print(DateFormater.formatCurrency(bingkisan.getHarga_paket()*bingkisan.getJumlah_paket()));%></td>
                            <td><% out.print(DateFormater.formatCurrency(bingkisan.getUang_terbayar()));%></td>
                            <td><% out.print(DateFormater.formatDateToCalFormat(bingkisan.getDue_date2()));%></td>
                            <td><%
                                if (!bingkisan.isPay_status()) {
                                    out.print("belum bayar");
                                } else {
                                    out.print(DateFormater.formatDateToCalFormat(bingkisan.getPay_date()));
                                }%></td>
                            <td><a href="<%= request.getContextPath()%>/PesanController?paket=bingkisan&&ido=<%= Integer.toString(bingkisan.getIdo())%>&&menu=<%
                                if (bingkisan.isPay_status()) {
                                    out.print("cancelbayar");
                                } else {
                                    out.print("konfirmbayar");
                                }
                                // link konfirm/cancel
                                /*if (bingkisan.getPay_date() == null) {
                                    out.print("belum bayar");
                                } else if (bingkisan.isPay_status()) {
                                    out.print(bingkisan.getPay_date());
                                } else {
                                    out.print("belum dicek");
                                }*/
                                   %>"><%
                                       if (bingkisan.isPay_status()) {
                                           out.print("<input type=\"button\" value=\"Cancel\" name=\"filter\" class=\"thrbutton\"/>");
                                       } else {
                                           out.print("<input type=\"button\" value=\"Konfirm\" name=\"filter\" class=\"thrbutton\"/>");
                                       }
                                    %></a>
                        </tr>
                        <% }%>
                    </table>
                    <div style="width: 100%;margin-top: 25px;border-top: 1px solid #CCC;position: absolute;bottom:0px">
                        <table style="width: 100%">
                            <tr style="height: 40px;">
                                <td style="padding-left: 40px;">
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>">Home</a>
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>/staff.jsp"><< Back</a>
                                </td>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>
<%
    }
    // else
    //                 {
    //     response.sendRedirect("../index.jsp");
    //                }
%>
