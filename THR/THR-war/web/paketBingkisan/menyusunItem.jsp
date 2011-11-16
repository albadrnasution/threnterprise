<%-- 
    Document   : menyusunItem
    Created on : Nov 15, 2011, 2:15:13 AM
    Author     : Didik
--%>

<%@page import="model.City"%>
<%@page import="model.ItemJalan"%>
<%@page import="model.Webservice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ItemBingkisan"%>
<%@page import="model.Staff"%>
<%-- 
    Document   : menyusunPBPage
    Created on : Oct 14, 2011, 1:45:27 PM
    Author     : Didik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <%
        boolean susun = false;
        String title = null;

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="../script/jquery-1.6.1.js"></script>
        <title></title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>

        <div id="content-wrapper">
            <div id="content">
                <div class="list-sheet" style="width: 100%;padding-bottom: 40px;">
                    <%
                        if (request.getParameter("item") != null && request.getParameter("option") != null) {
                            if (request.getParameter("item").equals("bingkisan")) {
                                ArrayList<ItemBingkisan> aib = new ArrayList<ItemBingkisan>();
                                ItemBingkisan ib = new ItemBingkisan();
                                if (request.getParameter("option").equals("create")) {
                                    title = "Menyusun Item Bingkisan";
                                    aib = ib.getItem();
                                    susun = true;
                                }
                                if (request.getParameter("option").equals("edit")) {
                                    title = "Mengubah Item Bingkisan";
                                    aib = ib.getItemBingkisan(request.getParameter("id"));
                                }
                    %>
                    <div style="float: left; width: 204px;">
                        <div style="width: 128px; height: 128px; text-align: center; margin: 40px;margin-bottom: 10px;text-align: center">
                            <img src="../images/gift.png" alt="Bingkisan" style="max-height: 128px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>/paketBingkisan/mengelolaItem.jsp?manageitem=bingkisan">Back</a>
                    </div>
                    <h1><%= title%></h1>

                    <form name="susunI" method="POST" action="../BingkisanController<%
                        if (request.getParameter("option").equals("create")) {
                            out.print("?act=addItem");
                        } else if (request.getParameter("option").equals("edit")) {
                            out.print("?act=editItem");
                        }
                          %>
                          ">
                        <table>
                            <tr>
                                <td>
                                    <input type="hidden" name="idi" value="<%
                                        if (!susun) {
                                            if (aib.get(0).getIdi() != -1) {
                                                out.print(aib.get(0).getIdi());
                                            }
                                        }
                                           %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Nama Item</td>
                                <td>:<input  class="filter" type="text" name="nama_item" value="<%
                                    if (!susun) {
                                        if (aib.get(0).getName() != null) {
                                            out.print(aib.get(0).getName());
                                        }
                                    }
                                             %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Deskripsi</td>
                                <td>:<input class="filter"  type="text" name="desc" value="<%
                                    if (!susun) {
                                        if (aib.get(0).getDescription() != null) {
                                            out.print(aib.get(0).getDescription());
                                        }
                                    }
                                            %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Harga</td>
                                <td>:<input  class="filter" type="text" name="harga" value="<%
                                    if (!susun) {
                                        out.print(aib.get(0).getBasic_price());
                                    }
                                             %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Id Tempe</td>
                                <%
                                    ArrayList<Webservice> aw = new ArrayList<Webservice>();
                                    Webservice w = new Webservice();
                                    aw = w.getWSByjenis("bingkisan");
                                %>
                                <td>:<select name="idtempe" class="filter" style="width: 150px">
                                        <option> From Webservice </option>
                                        <%
                                            for (int i = 0; i < aw.size(); ++i) {
                                        %>
                                        <option value="<%= aw.get(i).getId()%>" <% if (!susun) {
                                                if (aw.get(i).getJenis().equals("bingkisan") || aw.get(i).getJenis().equals("bingkisan")) {
                                                    if (aw.get(i).getId() == aib.get(0).getId_tempe()) {
                                                        out.print("selected");
                                                    }
                                                }
                                            }%> ><%= aw.get(i).getNama()%></option>
                                        <%
                                            }%>
                                    </select>
                                </td>
                            </tr>
                            <tr align="center">
                                <td style="text-align: right" colspan="2">
                                    <input  class="thrbutton" type="submit" name="submititem" id="submitProfil" style="width: 150px;height: 40px;"  /></td>
                            </tr>
                        </table>
                    </form>


                    <%

                    } else if (request.getParameter("item").equals("perjalanan")) {
                        ArrayList<ItemJalan> aij = new ArrayList<ItemJalan>();
                        ItemJalan ij = new ItemJalan();
                        if (request.getParameter("option").equals("create")) {
                            title = "Menyusun Item Perjalanan";
                            aij = ij.getItem();
                            susun = true;
                        }
                        if (request.getParameter("option").equals("edit")) {
                            title = "Mengubah Item Perjalanan";
                            aij = ij.getItemJalan(request.getParameter("id"));
                        }
                    %>
                    <div style="float: left; width: 204px;">
                        <div style="width: 128px; height: 128px; text-align: center; margin: 40px;margin-bottom: 10px;text-align: center">
                            <img src="../images/suitcase.png" alt="Perjalanan" style="max-height: 128px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>/paketBingkisan/mengelolaItem.jsp?manageitem=perjalanan">Back</a>
                    </div> 

                    <h1><%= title%></h1>

                    <form name="susunI" method="POST" action="../PerjalananController<%
                        if (request.getParameter("option").equals("create")) {
                            out.print("?act=addItem");
                        } else if (request.getParameter("option").equals("edit")) {
                            out.print("?act=editItem");
                        }
                          %>
                          ">
                        <table>
                            <tr>
                                <td>
                                    <input type="hidden" name="idi" value="<%
                                        if (!susun) {
                                            if (aij.get(0).getIdi() != -1) {
                                                out.print(aij.get(0).getIdi());
                                            }
                                        }
                                           %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Nama Item</td>
                                <td>:<input  class="filter" type="text" name="nama_item" value="<%
                                    if (!susun) {
                                        if (aij.get(0).getName() != null) {
                                            out.print(aij.get(0).getName());
                                        }
                                    }
                                             %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Deskripsi</td>
                                <td>:<input class="filter"  type="text" name="desc" value="<%
                                    if (!susun) {
                                        if (aij.get(0).getDescription() != null) {
                                            out.print(aij.get(0).getDescription());
                                        }
                                    }
                                            %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Moda Transportasi</td>
                                <td>:<input  class="filter" type="text" name="moda" value="<%
                                    if (!susun) {
                                        out.print(aij.get(0).getModa());
                                    }
                                %>"/>
                                </td>
                            </tr>
                            <tr>
                                <td id="moda"></td>
                            </tr>
                            <tr><td>Route : </td>
                                <%
                                    City city = new City();
                                    ArrayList<City> lcity = new ArrayList<City>();
                                    lcity = city.getCity();
                                %>
                                <td>:<select name="origin" class="filter" style="width: 200px">
                                        <option> Origin </option>
                                        <%
                                            for (int i = 0; i < lcity.size(); ++i) {
                                        %>
                                        <option value="<%= lcity.get(i).getIdcity()%>" 
                                                <% if (!susun) {
                                                        if (lcity.get(i).getIdcity() == aij.get(0).getOrigin()) {
                                                            out.print("selected");
                                                        }
                                                    }%>
                                                ><%= lcity.get(i).getName()%></option>
                                        <% }%>
                                    </select>
                                    <select name="dest" class="filter" style="width: 200px">
                                        <option> Destination </option>
                                        <%
                                            for (int i = 0; i < lcity.size(); ++i) {
                                        %>
                                        <option value="<%= lcity.get(i).getIdcity()%>"
                                                <% if (!susun) {
                                                        if (lcity.get(i).getIdcity() == aij.get(0).getDest()) {
                                                            out.print("selected");
                                                        }
                                                    }%>
                                                ><%= lcity.get(i).getName()%></option>
                                        <% }%>
                                    </select>

                                </td>
                            </tr>
                            <tr>
                                <td>Harga Anak-anak</td>
                                <td>:<input  class="filter" type="text" name="hargachild" value="<%
                                    if (!susun) {
                                        out.print(aij.get(0).getBprice_child());
                                    }
                                             %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Harga Dewasa</td>
                                <td>:<input  class="filter" type="text" name="hargaadult" value="<%
                                    if (!susun) {
                                        out.print(aij.get(0).getBprice_adult());
                                    }
                                             %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Id Moedik</td>
                                <%
                                    ArrayList<Webservice> aw = new ArrayList<Webservice>();
                                    Webservice w = new Webservice();
                                    aw = w.getWSByjenis("travel");
                                %>
                                <td>:<select name="idmoedik" class="filter" style="width: 405px">
                                        <option> From Webservice </option>
                                        <%
                                            for (int i = 0; i < aw.size(); ++i) {
                                        %>
                                        <option value="<%= aw.get(i).getId()%>" <% if (!susun) {
                                                if (aw.get(i).getJenis().equals("travel") || aw.get(i).getJenis().equals("travel")) {
                                                    if (aw.get(i).getId() == aij.get(0).getIdmoedik()) {
                                                        out.print("selected");
                                                    }
                                                }
                                            }%> ><%= aw.get(i).getNama()%></option>
                                        <%
                                            }%>
                                    </select>
                                </td>
                            </tr>
                            <tr align="center">
                                <td style="text-align: right" colspan="2">
                                    <input  class="thrbutton" type="submit" name="submititem" id="submitProfil" style="width: 150px;height: 40px;"  /></td>
                            </tr>
                        </table>
                    </form>




                    <%

                                if (request.getParameter("option").equals("create")) {
                                }
                            }
                        }
                    %>
                </div>
            </div>
        </div>
        <div id="footer-wrapper">
            <div id="footer">
                &COPY; 2011, Anpau Ltd.
                <span class="footer-link"><a href="#">About Us</a></span>
            </div>
        </div>
    </body>
</html>
