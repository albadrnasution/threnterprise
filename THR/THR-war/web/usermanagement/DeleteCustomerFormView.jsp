<%-- 
    Document   : DeleteCustomerFormView
    Created on : Oct 15, 2011, 10:12:50 PM
    Author     : hyouda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>Anda mau delete data <% out.print(customer.getFirst_name()); %>?</h1><br />
<h3><a href="?menu=prosesDeleteCustomer&&idc=<% out.print(customer.getIdc()); %>">Ya</a></h3><br />
<h3><a href="?menu=ListCustomer">Tidak</a></h3>
