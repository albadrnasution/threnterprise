<%-- 
    Document   : menyusunPaketPerjalanan
    Created on : Oct 14, 2011, 1:46:29 PM
    Author     : Didik
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.PerjalananController" %>
<%@page import="model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp?register=2");
    } else {
        if (session.getAttribute("jenisUser").equals("0")) {
            response.sendRedirect("../index.jsp?register=2");
        } else {
            Staff r = (Staff) session.getAttribute("user");

%>   
<!DOCTYPE html>
<html>
    <%               
        String title = null;
        boolean susun = false;
        PerjalananController pc = new PerjalananController();
        ArrayList<ItemJalan> ij = new ArrayList<ItemJalan>();
        ArrayList<PaketJalan> pj = new ArrayList<PaketJalan>();
        ArrayList<ItemJalan> pij = new ArrayList<ItemJalan>();
        if (request.getParameter("aksi") != null) {
            if (request.getParameter("aksi").equals("susun")) {
                ij = pc.getItem();
                title = "Menyusun Paket Perjalanan";
                susun = true;                
            } else if (request.getParameter("aksi").equals("edit")) {
                ij = pc.getItem();
                pj = pc.showPaket(request.getParameter("id"));
                pij = pc.getItem(request.getParameter("id"));
                title = "Mengubah Paket Perjalanan";
            }
        }

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <style type="text/css">
            div.scroll
            {
            width:410px;
            height:300px;
            overflow:auto;
            }
        </style>
        <script type="text/javascript" src="../script/jquery-1.6.1.js"></script>
        <title><%= title%></title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>

        <div id="content-wrapper">
            <div id="content">
                <div class="list-sheet" style="width: 100%;padding-bottom: 40px;">
                    <div style="float: left; width: 204px;">
                        <div style="width: 128px; height: 128px; text-align: center; margin: 40px;margin-bottom: 10px;text-align: center">
                            <img src="../images/suitcase.png" alt="Perjalanan" style="max-height: 128px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>/paketPerjalanan/mengelolaPaket.jsp">Mengelola Paket</a>
                    </div>
                    <div style="float: left; width: 740px; min-height: 140px; margin-bottom: 40px;padding-top: 15px;">
                        <h1><%= title%></h1>
                        <form action="../PerjalananController?act=<%
                            if (request.getParameter("aksi").equals("edit")) {
                                out.print("editPaket");
                            } else if (request.getParameter("aksi").equals("susun")) {
                                out.print("addPaket");
                            }%>" name="editPaket" method="POST" id="editProfil">

                            <%
                                if (request.getParameter("success") != null) {
                                    if (request.getParameter("success").equals("0")) {
                            %>
                            <div style="color: red">Failed. </div>
                            <%                            } else if (request.getParameter("success").equals("1")) {
                            %>
                            <div style="color: red">Updated successfully. </div>
                            <%                            }
                                }
                            %>
                            <input class="filter" type="hidden" name="s_id" value="<%
                                if (!susun) {
                                    if (pj.get(0).getIdp() != 0) {
                                        out.print(pj.get(0).getIdp());
                                    }
                                }
                                   %>
                                   " />
                            <table>
                                <tbody>
                                    <tr>
                                        <td>Nama Paket</td>
                                        <td>:<input class="filter" type="text" name="s_nama_paket" value="<%
                                            if (!susun) {
                                                out.print(pj.get(0).getPaket_nama());
                                            }
                                                    %>" /></td>
                                    </tr>
                                    <tr>
                                        <td>Deskripsi</td>
                                        <td>:<textarea class="filter" name="s_desc" rows="4" cols="48"><%
                                            if (!susun) {
                                                if (pj.get(0).getDescription() != null) {
                                                    out.print(pj.get(0).getDescription());
                                                }
                                            }
                                                %></textarea></td>
                                    </tr>
                                    <tr>
                                        <td>Item Paket</td>
                                        <td>:<br/><div class="scroll"><%
                                            if (!susun) {
                                                for(int i=0;i<ij.size();++i){
                                                    for (int j = 0; j < pij.size(); ++j) {
                                            %>
                                            <input type="checkbox" name="s_item" value="<%= ij.get(i).getIdi()%>" <%
                                                    if (ij.get(i).getIdi() == pij.get(j).getIdi()) {
                                                        out.print("checked");
                                                    }

                                                }%>/><% out.print(i + 1);%>.<% out.print(ij.get(i).getName());%> <br/>
                                            <div><% out.print(ij.get(i).getDescription());%></div><br/>
                                            <%
                                                }
                                            } else {
                                                for(int i=0; i<ij.size() ;++i){
                                            %>
                                            <input type="checkbox" name="s_item" value="<%= ij.get(i).getIdi()%>"/><% out.print(i + 1);%>.<% out.print(ij.get(i).getName());%> <br/>
                                            <div><% out.print(ij.get(i).getDescription());%></div><br/>
                                            <%
                                                    }
                                                }
                                            
                                            %></div></td>
                                    </tr>
                                    <tr>
                                        <td>Total Harga</td>
                                        <td>:<input class="filter" type="text" name="s_harga" value="<%
                                            if (!susun) {
                                                out.print(pj.get(0).getTotal_price());
                                            }
                                                    %>" /></td>
                                    </tr>
                                    <tr>
                                        <td>Jumlah Penumpang Dewasa</td>
                                        <td>:<input class="filter" type="text" name="s_nadult" value="<%
                                            if (!susun) {
                                                out.print(pj.get(0).getNadult());
                                            }
                                                    %>" /></td>
                                    </tr>
                                    <tr>
                                        <td>Jumlah Penumpang Anak-anak</td>
                                        <td>:<input class="filter" type="text" name="s_nchild" value="<%
                                            if (!susun) {
                                                out.print(pj.get(0).getNchild());
                                            }
                                                    %>" /></td>
                                    </tr>
                                    <tr>
                                        <td>Waktu Keberangkatan</td>
                                        <td>:<input class="filter" type="text" name="s_time" value="<%if (!susun) {
                                                            out.print(DateFormater.formatDateToCalFormat(pj.get(0).getTimeD()));
                                                        }%>" /></td>
                                    </tr>
                                    <tr align="center">
                                        <td style="text-align: right" colspan="2">
                                            <input  class="thrbutton" type="submit" name="submitpaket" id="submitProfil" style="width: 150px;height: 40px;"  /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>

<% }
    }%>