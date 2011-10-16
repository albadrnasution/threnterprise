<%-- 
    Document   : DeleteStaffFormView
    Created on : Oct 15, 2011, 10:13:08 PM
    Author     : hyouda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>Anda mau delete data <% out.print(staff.getUsername()); %>?</h1><br />
<h3><a href="?menu=prosesDeleteStaff&&ids=<% out.print(staff.getIds()); %>">Ya</a></h3><br />
<h3><a href="?menu=ListStaff">Tidak</a></h3>
