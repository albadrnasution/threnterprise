<%-- 
    Document   : mengelolaPaket
    Created on : Oct 15, 2011, 1:52:15 AM
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
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Megelola Paket Perjalanan</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>

        <% if (request.getParameter("success") != null) {
                if (request.getParameter("success").equals("1")) {%>
        <div style="margin-left: 20px; color: red">Success.</div>
        <% } else if (request.getParameter("success").equals("0")) {%>
        <div style="margin-left: 20px; color: red">Failed.</div>
        <% }
                }%>

        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">
                    <h2 style="margin-left: 10px;">Mengelola Paket Perjalanan</h2><hr/>
                    <a href="<%= request.getContextPath()%>/PerjalananController?mode=susun">Susun Paket Perjalanan</a>
                    <table border="1" class="tutturu">
                        <tr>
                            <th>Nama Paket</th>
                            <th>Deskripsi</th>
                            <th>Harga</th>
                            <th>N-adult</th>
                            <th>N-child</th>
                            <th>Waktu</th>
                            <th>Aksi</th>
                        </tr>
                        <%
                            PerjalananController pc = new PerjalananController();
                            ArrayList<PaketJalan> ij = new ArrayList<PaketJalan>();
                            ij = pc.showPaket();

                            for (int i = 0; i < ij.size(); ++i) {
                        %>
                        <tr>
                            <td><% if (ij.get(i).getPaket_nama() != null) {
                        out.print(ij.get(i).getPaket_nama());
                    } else {
                        out.print("-");
                    }%></td>
                            <td><% if (ij.get(i).getDescription() != null) {
                        out.print(ij.get(i).getDescription());
                    } else {
                        out.print("-");
                    }%></td>
                            <td><% if (ij.get(i).getTotal_price() != 0) {
                        out.print(ij.get(i).getTotal_price());
                    } else {
                        out.print("0");
                    }%></td>
                            <td><% if (ij.get(i).getNadult() != 0) {
                        out.print(ij.get(i).getNadult());
                    } else {
                        out.print("0");
                    }%></td>
                            <td><% if (ij.get(i).getNchild() != 0) {
                        out.print(ij.get(i).getNchild());
                    } else {
                        out.print("0");
                    }%></td>
                            <td><% if (ij.get(i).getTime() != null) {
                        out.print(ij.get(i).getTime());
                    } else {
                        out.print("-");
                    }%></td>
                            <td  style="width: 120px">
                                <a class="thrbutton" href="<%= request.getContextPath()%>/PerjalananController?mode=edit&id=<% out.print(ij.get(i).getIdp());%>">Edit</a>
                                <a class="thrbutton" href="<%= request.getContextPath()%>/PerjalananController?mode=delete&id=<% out.print(ij.get(i).getIdp());%>">Delete</a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>
