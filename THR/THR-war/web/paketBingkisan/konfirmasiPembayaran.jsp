<%-- 
    Document   : konfirmasipembayaran
    Created on : Oct 25, 2011, 6:35:48 AM
    Author     : hyouda
--%>

<%@page import="model.PesanBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PesanPaket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% 
        if(session.getAttribute("getpaket")!=null && session.getAttribute("getbingkisan")!=null)
                     {
            
                ArrayList<PesanPaket> pp = (ArrayList<PesanPaket>)session.getAttribute("getpaket");
                ArrayList<PesanBingkisan> pb = (ArrayList<PesanBingkisan>)session.getAttribute("getbingkisan");
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List Pemesanan Perjalanan</h1>
        <table border="1">
            <tr>
                <th>nama_paket</th>
            <th>jumlah_paket</th>
            <th>order date</th>
            <th>due date</th>
            <th>keterangan bayar</th>
            <th>aksi</th>
            </tr>
            <% for(int i=0;i<pp.size();i++) { 
                PesanPaket paket = pp.get(i);
                %>
            <tr>
                <td><% out.print(paket.getPaket_name()); %></td>
            <td><% out.print(paket.getJumlah_paket()); %></td>
            <td><% out.print(paket.getOrder_dateS()); %></td>
            <td><% out.print(paket.getDue_date()); %></td>
            <td><% 
            if(!paket.isPay_status())
                out.print("belum bayar");
            else
            out.print(paket.getPay_date()); %></td>
            <td><a href="<%= request.getContextPath()%>/PesanController?paket=perjalanan&&ido=<%= Integer.toString(paket.getIdo()) %>&&menu=<%
                   // link konfirm/cancel
                   if(paket.isPay_status())
                        out.print("cancelbayar");
                    else
                        out.print("konfirmbayar");%>
                   "><% 
            if(paket.isPay_status())
                out.print("cancel");
            else
                out.print("konfirm");
            %>
            </a>
            </td>
            </tr>
            <% } %>
</table>
<br />
<h1>List Pemesanan Paket Bingkisan</h1>
        <table border="1">
            <tr>
                <th>nama paket</th>
            <th>jumlah_paket</th>
            <th>order date</th>
            <th>due date</th>
            <th>Keterangan bayar</th>
            <th>aksi</th>
            </tr>
            <% for(int i=0;i<pb.size();i++) { 
                PesanBingkisan bingkisan = pb.get(i);
                %>
            <tr>
                <td><% out.print(bingkisan.getPaket_name()); %></td>
            <td><% out.print(bingkisan.getJumlah_paket()); %></td>
            <td><% out.print(bingkisan.getOrder_dateS()); %></td>
            <td><% out.print(bingkisan.getDue_date2()); %></td>
            
            <td><% 
            if(!bingkisan.isPay_status())
                out.print("belum bayar");
            else
            out.print(bingkisan.getPay_date()); %></td>
            <td><a href="<%= request.getContextPath()%>/PesanController?paket=bingkisan&&ido=<%= Integer.toString(bingkisan.getIdo()) %>&&menu=<%
                   // link konfirm/cancel
                   if(bingkisan.isPay_status())
                        out.print("cancelbayar");
                    else
                        out.print("konfirmbayar");%>
                   "><% 
            if(bingkisan.isPay_status())
                out.print("cancel");
            else
                out.print("konfirm");
            %>
            </tr>
            <% } %>
</table>
    </body>
</html>
<%
        }
       // else
      //                 {
       //     response.sendRedirect("../index.jsp");
       //                }
%>
