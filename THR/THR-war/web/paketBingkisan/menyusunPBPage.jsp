<%-- 
    Document   : menyusunPBPage
    Created on : Oct 14, 2011, 1:45:27 PM
    Author     : Didik
--%>

<%@page import="model.IPBingkisan"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="model.ItemBingkisan"%>
<%@page import="model.PaketBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.BingkisanController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <%
        String title = null;
        boolean susun = false;
        BingkisanController pc = new BingkisanController();
        ArrayList<ItemBingkisan> ij = new ArrayList<ItemBingkisan>();
        ArrayList<PaketBingkisan> pj = new ArrayList<PaketBingkisan>();
        ArrayList<ItemBingkisan> pij = new ArrayList<ItemBingkisan>();
        ArrayList<IPBingkisan> ipb = new ArrayList<IPBingkisan>();
        if(request.getParameter("aksi")!=null){
            if(request.getParameter("aksi").equals("susun")){
                ij = pc.getItem();
                title = "Menyusun Paket Bingkisan";
                susun = true;
            }else if(request.getParameter("aksi").equals("edit")){
                pj = pc.showPaket(request.getParameter("id"));
                pij = pc.getItem(request.getParameter("id"));
                title = "Mengubah Paket Bingkisan";
            }
        }
        
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="../script/jquery-1.6.1.js"></script>
        <title><%= title %></title>
    </head>
    <body>
        <div id="header-wrapper">
            <div id="header">
                <div id="thrlogo"><a href="<%= request.getContextPath()%>"><img src="../images/thrlogo.png" style="height: 180px" alt="thrlogo" title="tentative logo"/></a></div>
            </div>
        </div>
        <div id="content-wrapper">
            <div id="content">
                <div class="list-sheet" style="width: 100%;padding-bottom: 40px;">
                    <div style="float: left; width: 204px;">
                        <div style="width: 128px; height: 128px; text-align: center; margin: 40px;margin-bottom: 10px;text-align: center">
                            <img src="../images/gift.png" alt="Bingkisan" style="max-height: 128px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>/paketBingkisan/mengelolaPaket.jsp">Mengelola Paket</a>
                    </div>
                <div style="float: left; width: 740px; min-height: 140px; margin-bottom: 40px;padding-top: 15px;">
                    <h1><%= title %></h1>

        <form name="susunB" method="POST" action="../BingkisanController?act=addPaket">
        <%
        if(request.getParameter("success") != null){
            if(request.getParameter("success").equals("1")){%>
                <div style="color: red">Success. </div>
            <%}else{%>
                <div style="color: red">Failed. </div>
            <%}
        }
        %>
        <div>
            <div id="atr">Nama Paket</div>
            <input type="text" name="s_nama_paket" value="
                   <%
                       if(!susun){
                           if(pj.get(0).getPaket_name()!=null){
                               out.print(pj.get(0).getPaket_name());
                           }
                       }
                    %>
                   " />
        </div>
        <div>
            <div id="atr">Deskripsi</div>
            <input type="text" name="s_desc" value="
                   <%
                       if(!susun){
                           if(pj.get(0).getDescription()!=null){
                               out.print(pj.get(0).getDescription());
                           }
                       }
                    %>
                   " />
        </div>
        <div>
            <div id="atr">Item Paket</div>
            
            <ol id="listitem">
            <% 
            if(susun){
            for(int i=0; i<ij.size();++i){
            %>
            <li><input type="checkbox" name="s_item" value="<%= ij.get(i).getIdi()%>" /><%= ij.get(i).getName() %> 
                <div><%= ij.get(i).getDescription()%> Harga Rp.<%=  ij.get(i).getBasic_price() %></div>
                <div>
                    <input type="text" name="nitem" value="" /> 
                </div>
            </li>
            <%
            }
            }else{
                for(int i=0; i<ij.size();++i){
                    %>
                    <li><input type="checkbox" name="s_item" value="<%= pij.get(i).getIdi()%>" /><%= pij.get(i).getName() %> 
                        <div><%= pij.get(i).getDescription()%> Harga Rp.<%=  pij.get(i).getBasic_price() %></div>
                        <div>
                            <input type="text" name="nitem" value="
                                   <%
                                       
                                    %>
                                   " /> 
                        </div>
                    </li>
                    <%
                    }
            }
            %>
            </ol>
            
        </div>
        <div>
            <div id="atr">Total Harga</div>
            <input type="text" name="s_harga" value="
                   <%
                       if(!susun){
                               out.print(pj.get(0).getPrice());
                       }
                    %>
                   " />
        </div>
        <input type="submit" value="Create" name="create" />
        </form>
            </div>
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
