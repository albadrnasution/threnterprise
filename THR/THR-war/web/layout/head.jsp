<%-- 
    Document   : head
    Created on : Oct 16, 2011, 4:49:57 PM
    Author     : Albadr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="header-wrapper">
    <div id="header">
        <div id="thrlogo"><a href="<%= request.getContextPath()%>"><img src="<%= request.getContextPath()%>/images/thrlogo-long.png" style="height: 80px" alt="thrlogo" title="tentative logo"/></a></div>
                <%
                    if (session.getAttribute("user") != null) {
                %>
        <a class="thrbutton" href="<%= request.getContextPath()%>/logout" style="position: absolute;bottom: 10px;right: 40px;width: 120px;height: 30px;line-height: 30px;font-size: 0.9em">Logout</a>
        <%            }
        %>
    </div>
</div>