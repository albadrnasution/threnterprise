<%-- 
    Document   : DeleteCustomerFormView
    Created on : Oct 15, 2011, 10:12:50 PM
    Author     : hyouda
--%>

<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    Customer customer = (Customer)session.getAttribute("customer");
%>
<h1>Anda mau delete data <% out.print(customer.getFirst_name()); %>?</h1><br />
<h3><a href="<% out.print(session.getAttribute("base_url")); %>UserController?menu=prosesDeleteCustomer&&idc=<% out.print(customer.getIdc()); %>">Ya</a></h3><br />
<h3><a href="<% out.print(session.getAttribute("base_url")); %>UserController?menu=ListCustomer">Tidak</a></h3>
