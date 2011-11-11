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
                    <table border="1" class="tutturu">
                        <tr>
                            <th>Nama Paket</th>
                            <th>Deskripsi</th>
                            <th>Harga</th>
                            <th>Aksi</th>
                        </tr>
                        <tbody>
                            <%
                                BingkisanController pc = new BingkisanController();
                                ArrayList<PaketBingkisan> ij = new ArrayList<PaketBingkisan>();
                                ij = pc.showPaket();

                                for (int i = 0; i < ij.size(); ++i) {
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
                            out.print(ij.get(i).getPrice());
                        } else {
                            out.print("-");
                        }%></td>
                                <td  style="width: 120px">
                                    <a class="thrbutton" href="<%= request.getContextPath()%>/BingkisanController?mode=edit&id=<% out.print(ij.get(i).getIdp());%>">Edit</a>
                                    <a class="thrbutton" href="<%= request.getContextPath()%>/BingkisanController?mode=delete&id=<% out.print(ij.get(i).getIdp());%>">Delete</a>
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
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>/.jsp"><< Back</a>
                                </td>
                                <td style="text-align: right;padding-right: 40px;"><a class="thrbutton"  style="height: 32px; width: 180px" href="<%= request.getContextPath()%>/BingkisanController?mode=susun">Create Packet</a></td>
                            </tr>
                        </table>
                    </div>
                </div>  
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>
