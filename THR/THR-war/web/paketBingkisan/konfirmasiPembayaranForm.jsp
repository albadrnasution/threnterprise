<%-- 
    Document   : konfirmasiPembayaranForm
    Created on : Nov 17, 2011, 10:29:06 PM
    Author     : hyouda
--%>

<%@page import="java.util.Calendar"%>
<%@page import="model.DateFormater"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="../PesanController" method="get">
            <table>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="menu" value="konfirmpesan" />
                        <input type="hidden" name="ido" value="<% out.print(request.getParameter("ido"));%>" />
                        <input type="hidden" name="jenispaket" value="<% out.print(request.getParameter("jenispaket"));%>" />
                    </td>
                </tr>
                <tr>
                    <td>nomor rekening</td>
                    <td>: <input type="text" name="no_rekening" class="filter"/></td>
                </tr>
                <tr>
                    <td>besar pembayaran</td>
                    <td>: <input type="text" name="uang_pembayaran" class="filter" value="<%= request.getParameter("tagihan") %>"/></td>
                </tr>
                <tr>
                    <td>tanggal pembayaran</td>
                    <td>: <input type="text" name="pay_date" class="filter" value="<% 
                            out.print(DateFormater.formatDateToCalFormat(Calendar.getInstance().getTime()));%>"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="konfirm" class="thrbutton"/>    </td>
                </tr>

            </table>
        </form>
    </body>
</html>
