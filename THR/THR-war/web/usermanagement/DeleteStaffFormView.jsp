<%-- 
    Document   : DeleteStaffFormView
    Created on : Oct 15, 2011, 10:13:08 PM
    Author     : hyouda
--%>

<%@page import="model.Staff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    Staff staff = (Staff)session.getAttribute("staff");
%>
<h1>Anda mau delete data <% out.print(staff.getUsername()); %>?</h1><br />
<h3><a href="<% out.print(session.getAttribute("base_url")); %>UserController?menu=prosesDeleteStaff&&ids=<% out.print(staff.getIds()); %>">Ya</a></h3><br />
<h3><a href="<% out.print(session.getAttribute("base_url")); %>UserController?menu=ListStaff">Tidak</a></h3>
