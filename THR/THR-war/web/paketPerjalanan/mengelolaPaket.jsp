<%-- 
    Document   : mengelolaPaket
    Created on : Oct 15, 2011, 1:52:15 AM
    Author     : Didik
--%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp?register=2");
    } else {
        if (session.getAttribute("jenisUser").equals("0")) {
            response.sendRedirect("../index.jsp?register=2");
        } else {
            Staff r = (Staff) session.getAttribute("user");

%>   
<%@page import="java.text.DateFormat"%>
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
                    <table class="tutturu">
                        <tr>
                            <th style="width: 20%">Nama Paket</th>
                            <th style="width: 46%">Deskripsi</th>
                            <th style="width: 10%">Harga</th>
                            <th style="width: 7%">N-adult</th>
                            <th style="width: 7%">N-child</th>
                            <th style="width: 10%">Waktu</th>
                            <th style="width: 10%">Aksi</th>
                        </tr>
                        <tbody>
                        <%
                            int batas = 5;
                            int pg = 1;
                            if(request.getParameter("page")!=null){
                                    pg = Integer.parseInt(request.getParameter("page"));
                            }
                            PerjalananController pc = new PerjalananController();
                            ArrayList<PaketJalan> ij = new ArrayList<PaketJalan>();
                            ij = pc.showPaket();
                            int pages;
                            if(ij.size()%batas==0){
                                pages = (ij.size()/batas);
                            }else{
                                pages = (ij.size()/batas)+1;
                            }
                            for(int i=((pg-1)*batas); i<((pg*batas)<(ij.size())?(pg*batas):(ij.size())) ;++i){
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
                        out.print("Rp"+DateFormater.formatCurrency(ij.get(i).getTotal_price()));
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
                        </tbody>
                    </table>
                    <div style="width: 100%;margin-top: 25px;border-top: 1px solid #CCC;position: absolute;bottom:0px">
                    <table style="width: 100%">
                        <tr style="height: 40px;">
                            <td style="padding-left: 40px;">
                                <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>">Home</a>
                                <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>/staff.jsp"><< Back</a>
                            </td>
                            <td>
                                    <div>
                                        <%
                                        if(pages>1){
                                            for(int i=1; i<=pages;++i){
                                                if(i==pg){
                                                        out.print("&nbsp;<b>["+i+"]</b>&nbsp;");
                                                }else{
                                                        out.print("&nbsp;<a href='mengelolaPaket.jsp?page="+i+"'>"+i+"</a>&nbsp;");
                                                }
                                                if(i!=pages){
                                                        out.print("");
                                                }
                                            }
                                        }
                                        %>
                                    </div>
                                    </td>
                            <td style="text-align: right;padding-right: 40px;"><a class="thrbutton"  style="height: 32px; width: 180px" href="<%= request.getContextPath()%>/PerjalananController?mode=susun">Create Packet</a></td>
                        </tr>
                    </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>
<% }
    }%>