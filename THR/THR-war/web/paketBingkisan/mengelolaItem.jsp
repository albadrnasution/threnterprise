<%-- 
    Document   : mengelolaItem
    Created on : Nov 14, 2011, 3:05:33 PM
    Author     : Didik
--%>

<%@page import="servlet.PerjalananController"%>
<%@page import="com.sun.jndi.url.iiop.iiopURLContext"%>
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
        <title>Megelola Item Paket</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>

        <%
            City c = new City();
            if (request.getParameter("success") != null) {
                if (request.getParameter("success").equals("1")) {%>
        <div style="margin-left: 20px; color: red">Item has been deleted successfully.</div>
        <% } else if (request.getParameter("success").equals("0")) {%>
        <div style="margin-left: 20px; color: red">Failed.</div>
        <% } else if (request.getParameter("success").equals("2")) {%>
        <div style="margin-left: 20px; color: red">Succeed.</div>
        <% }
            }%>

        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">

                    <%
                        if (request.getParameter("manageitem") != null) {
                            if (request.getParameter("manageitem").equals("bingkisan")) {
                    %><h2 style="margin-left: 10px;">Mengelola Item Paket Bingkisan</h2><hr/>
                    <%
                        BingkisanController bc = new BingkisanController();
                        ArrayList<ItemBingkisan> aib = new ArrayList<ItemBingkisan>();
                        aib = bc.getItem();
                    %>

                    <table class="tutturu">
                        <tr>
                            <th style="width: 12%">Nama</th>
                            <th  style="width: 62%">Deskripsi</th>
                            <th  style="width: 12%">Harga</th>
                            <th  style="width: 14%">Id Mudik</th>
                        </tr>
                        <tbody>
                            <%
                                int batas = 7;
                                int pg = 1;
                                if (request.getParameter("pageb") != null) {
                                    pg = Integer.parseInt(request.getParameter("pageb"));
                                }

                                int pages;
                                if (aib.size() % batas == 0) {
                                    pages = (aib.size() / batas);
                                } else {
                                    pages = (aib.size() / batas) + 1;
                                }
                                for (int i = ((pg - 1) * batas); i < ((pg * batas) < (aib.size()) ? (pg * batas) : (aib.size())); ++i) {
                            %>
                            <tr>
                                <td><% if (aib.get(i).getName() != null) {
                                        out.print(aib.get(i).getName());
                                    } else {
                                        out.print("-");
                                    }%></td>
                                <td><% if (aib.get(i).getDescription() != null) {
                                        out.print(aib.get(i).getDescription());
                                    } else {
                                        out.print("-");
                                    }%></td>
                                <td><% if (aib.get(i).getBasic_price() != 0) {
                                        out.print("Rp" + model.DateFormater.formatCurrency(aib.get(i).getBasic_price()));
                                    } else {
                                        out.print("-");
                                    }%></td>
                                <td  style="width: 120px">
                                    <a class="thrbutton" href="<%= request.getContextPath()%>/BingkisanController?mode=edit&tipe=item&id=<% out.print(aib.get(i).getIdi());%>">Edit</a>
                                    <a class="thrbutton" href="<%= request.getContextPath()%>/BingkisanController?mode=delete&tipe=item&id=<% out.print(aib.get(i).getIdi());%>">Delete</a>
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
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>/paketBingkisan/mengelolaPaket.jsp"><< Back</a>
                                </td>
                                <td>
                                    <div>
                                        <%
                                            if (pages > 1) {
                                                for (int i = 1; i <= pages; ++i) {
                                                    if (i == pg) {
                                                        out.print("&nbsp;<b>[" + i + "]</b>&nbsp;");
                                                    } else {
                                                        out.print("&nbsp;<a href='mengelolaItem.jsp?manageitem=bingkisan&pageb=" + i + "'>" + i + "</a>&nbsp;");
                                                    }
                                                    if (i != pages) {
                                                        out.print("");
                                                    }
                                                }
                                            }
                                        %>
                                    </div>
                                </td>
                                <td style="text-align: right;"><a class="thrbutton"  style="height: 32px; width: 180px" href="<%= request.getContextPath()%>/BingkisanController?mode=susun&tipe=item&aksi=create">Create Item</a>
                                </td>
                            </tr>
                        </table>
                    </div>


                    <%

                    } else if (request.getParameter("manageitem").equals("perjalanan")) {
                    %><h2 style="margin-left: 10px;">Mengelola Item Paket Perjalanan</h2><hr/>
                    <table class="tutturu">
                        <tr>
                            <th style="width: 20%">Nama Paket</th>
                            <th style="width: 35%">Deskripsi</th>
                            <th style="width: 8%">Moda</th>
                            <th style="width: 8%">Origin</th>
                            <th style="width: 8%">Destination</th>
                            <th style="width: 8%">Harga Anak</th>
                            <th style="width: 8%">Harga Dewasa</th>
                            <th style="width: 5%">Id Mudik</th>
                        </tr>
                        <tbody>
                            <%
                                int batas = 5;
                                int pg = 1;
                                if (request.getParameter("pagep") != null) {
                                    pg = Integer.parseInt(request.getParameter("pagep"));
                                }
                                PerjalananController pc = new PerjalananController();
                                ArrayList<ItemJalan> ij = new ArrayList<ItemJalan>();
                                ij = pc.getItem();
                                int pages;
                                if (ij.size() % batas == 0) {
                                    pages = (ij.size() / batas);
                                } else {
                                    pages = (ij.size() / batas) + 1;
                                }
                                for (int i = ((pg - 1) * batas); i < ((pg * batas) < (ij.size()) ? (pg * batas) : (ij.size())); ++i) {
                            %>
                            <tr>
                                <td><% if (ij.get(i).getName() != null) {
                                        out.print(ij.get(i).getName());
                                    } else {
                                        out.print("");
                                    }%></td>
                                <td><% if (ij.get(i).getDescription() != null) {
                                        out.print(ij.get(i).getDescription());
                                    } else {
                                        out.print("");
                                    }%></td>
                                <td><% if (ij.get(i).getModa() != null) {
                                        out.print(ij.get(i).getModa());
                                    } else {
                                        out.print("");
                                    }%></td>
                                <td><% if (ij.get(i).getOrigin() != -1) {
                                        out.print(c.getCity(ij.get(i).getOrigin()).getName());
                                    } else {
                                        out.print("");
                                    }%></td>
                                <td><% if (ij.get(i).getDest() != -1) {
                                        out.print(c.getCity(ij.get(i).getDest()).getName());
                                    } else {
                                        out.print("");
                                    }%></td>
                                <td><% if (ij.get(i).getBprice_child() != -1) {
                                        out.print("Rp" + DateFormater.formatCurrency(ij.get(i).getBprice_child()));
                                    } else {
                                        out.print("");
                                    }%></td>
                                <td><% if (ij.get(i).getBprice_adult() != -1) {
                                        out.print("Rp" + DateFormater.formatCurrency(ij.get(i).getBprice_adult()));
                                    } else {
                                        out.print("");
                                    }%></td>
                                <td><% if (ij.get(i).getIdmoedik() != -1) {
                                        out.print(ij.get(i).getIdmoedik());
                                    } else {
                                        out.print("");
                                    }%></td>
                                <td  style="width: 120px">
                                    <a class="thrbutton" href="<%= request.getContextPath()%>/PerjalananController?mode=edit&tipe=item&id=<% out.print(ij.get(i).getIdi());%>">Edit</a>
                                    <a class="thrbutton" href="<%= request.getContextPath()%>/PerjalananController?mode=delete&tipe=item&id=<% out.print(ij.get(i).getIdi());%>">Delete</a>
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
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>/paketPerjalanan/mengelolaPaket.jsp"><< Back</a>
                                </td>
                                <td>
                                    <div>
                                        <%
                                            if (pages > 1) {
                                                for (int i = 1; i <= pages; ++i) {
                                                    if (i == pg) {
                                                        out.print("&nbsp;<b>[" + i + "]</b>&nbsp;");
                                                    } else {
                                                        out.print("&nbsp;<a href='mengelolaItem.jsp?manageitem=perjalanan&pagep=" + i + "'>" + i + "</a>&nbsp;");
                                                    }
                                                    if (i != pages) {
                                                        out.print("");
                                                    }
                                                }
                                            }
                                        %>
                                    </div>
                                </td>
                                <td style="text-align: right;padding-right: 40px;"><a class="thrbutton"  style="height: 32px; width: 180px" href="<%= request.getContextPath()%>/PerjalananController?mode=susun&tipe=item&aksi=create">Create Item</a>
                                </td>
                            </tr>
                        </table>
                    </div>


                    <%   }
                        }
                    %>
                </div>  
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>
<% }
    }%>