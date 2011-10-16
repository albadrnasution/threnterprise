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
        <title>Megelola Paket</title>
    </head>
    <body>
        <h1>Mengelola Paket Bingkisan</h1>
        <a href="<%= request.getContextPath()%>/paketPerjalanan/menyusunPPPage.jsp">Susun Paket Perjalanan</a>
        <table border="1">
            <thead>
                <tr>
                    <th>Nama Paket</th>
                    <th>Deskripsi</th>
                    <th>Harga</th>
                    <th>N-adult</th>
                    <th>N-child</th>
                    <th>Waktu</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    PerjalananController pc = new PerjalananController();
                    ArrayList<PaketJalan> ij = new ArrayList<PaketJalan>();
                    ij = pc.showPaket();
                
                for(int i=0; i<ij.size();++i){
                %>
                <tr>
                    <td><%= ij.get(i).getPaket_nama() %></td>
                    <td><%= ij.get(i).getDescription() %></td>
                    <td><%= ij.get(i).getTotal_price() %></td>
                    <td><%= ij.get(i).getNadult() %></td>
                    <td><%= ij.get(i).getNchild() %></td>
                    <td><%= ij.get(i).getTime() %></td>
                    <td><a href="menyusunPPPage.jsp?edit=<%= ij.get(i).getIdp() %>">Edit</a></td>
                    <td><a href="mengelolaPaket.jsp?delete=<%= ij.get(i).getIdp() %>">Delete</a></td>
                </tr>
                <%
               }
                %>
            </tbody>
        </table>

    </body>
</html>
