<%-- 
    Document   : mengelolaPaket
    Created on : Oct 15, 2011, 1:52:15 AM
    Author     : Didik
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="servlet.BingkisanController" %>
<%@page import="model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp?register=2");
    } else {
        if (session.getAttribute("jenisUser").equals("0")) {
            response.sendRedirect("../index.jsp?register=2");
        } else {
            Staff r = (Staff) session.getAttribute("user");

%>   
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Megelola Paket Bingkisan</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>

        <% if (request.getParameter("success") != null) {
                if (request.getParameter("success").equals("1")) {%>
        <div style="margin-left: 20px; color: red">Paket has been deleted successfully.</div>
        <% } else if (request.getParameter("success").equals("0")) {%>
        <div style="margin-left: 20px; color: red">Failed.</div>
        <% } else if (request.getParameter("success").equals("2")) {%>
        <div style="margin-left: 20px; color: red">Succeed.</div>
        <% }
                }%>

        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">
                    <h2 style="margin-left: 10px;">Mengelola Paket Bingkisan</h2><hr/>
                    <table class="tutturu">
                        <tr>
                            <th style="width: 12%">Nama Paket</th>
                            <th  style="width: 62%">Deskripsi</th>
                            <th  style="width: 12%">Harga</th>
                            <th  style="width: 14%">Aksi</th>
                        </tr>
                        <tbody>
                            <%
                                int batas = 7;
                                int pg = 1;
                                if(request.getParameter("page")!=null){
                                        pg = Integer.parseInt(request.getParameter("page"));
                                }
                                BingkisanController pc = new BingkisanController();
                                ArrayList<PaketBingkisan> ij = new ArrayList<PaketBingkisan>();
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
                                <td><% if (ij.get(i).getPaket_name() != null) {
                            out.print(ij.get(i).getPaket_name());
                        } else {
                            out.print("-");
                        }%></td>
                                <td><% if (ij.get(i).getDescription() != null) {
                            out.print(ij.get(i).getDescription());
                        } else {
                            out.print("-");
                        }%></td>
                                <td><% if (ij.get(i).getPrice() != 0) {
                            out.print("Rp"+ model.DateFormater.formatCurrency(ij.get(i).getPrice()));
                        } else {
                            out.print("-");
                        }%></td>
                                <td  style="width: 120px">
                                    <a class="thrbutton" href="<%= request.getContextPath()%>/BingkisanController?mode=edit&tipe=paket&id=<% out.print(ij.get(i).getIdp());%>">Edit</a>
                                    <a class="thrbutton" href="<%= request.getContextPath()%>/BingkisanController?mode=delete&tipe=paket&id=<% out.print(ij.get(i).getIdp());%>">Delete</a>
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
                                <td style="text-align: right;"><a class="thrbutton"  style="height: 32px; width: 180px" href="<%= request.getContextPath()%>/BingkisanController?mode=susun&tipe=paket">Create Packet</a>
                                    <a class="thrbutton"  style="height: 32px; width: 180px" href="<%= request.getContextPath()%>/BingkisanController?mode=susun&tipe=item">Manage Item</a>
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
<% }
    }%>