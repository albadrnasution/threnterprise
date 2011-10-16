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
        <title>Megelola Paket</title>
    </head>
    <body>
        <h1>Mengelola Paket Bingkisan</h1>
        <a href="<%= request.getContextPath()%>/paketBingkisan/menyusunPBPage.jsp">Susun Paket Bingkisan</a>        
        <table border="1">
            <thead>
                <tr>
                    <th>Nama Paket</th>
                    <th>Deskripsi</th>
                    <th>Harga</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    BingkisanController pc = new BingkisanController();
                    ArrayList<PaketBingkisan> ij = new ArrayList<PaketBingkisan>();
                    ij = pc.showPaket();
                
                for(int i=0; i<ij.size();++i){
                %>
                <tr>
                    <td><%= ij.get(i).getPaket_name() %></td>
                    <td><%= ij.get(i).getDescription() %></td>
                    <td><%= ij.get(i).getPrice() %></td>
                    <td><a href="menyusunPBPage.jsp?edit=<%= ij.get(i).getIdp() %>">Edit</a></td>
                    <td><a href="mengelolaPaket.jsp?delete=<%= ij.get(i).getIdp() %>">Delete</a></td>
                </tr>
                <%
               }
                %>
                
                <%
                PaketBingkisan p = new PaketBingkisan();
                p = pc.deletePaket(request.getParameter("delete"));
                %>
            </tbody>
        </table>

    </body>
</html>
