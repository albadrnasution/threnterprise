<%-- 
    Document   : konfirmasiPembayaranForm
    Created on : Nov 17, 2011, 10:29:06 PM
    Author     : hyouda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="../PesanController" method="get">
            <input type="hidden" name="menu" value="konfirmpesan" />
            <input type="hidden" name="ido" value="<% out.print(request.getParameter("ido")); %>" />
            <input type="hidden" name="jenispaket" value="<% out.print(request.getParameter("jenispaket")); %>" />
            nomor rekening: <input type="text" name="no_rekening" /><br />
            besar pembayaran: <input type="text" name="uang_pembayaran" /><br />
            tanggal pembayaran: <input type="text" name="pay_date" /><br />
            <input type="submit" value="konfirm" />
        </form>
    </body>
</html>
