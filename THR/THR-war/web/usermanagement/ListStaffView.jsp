<%-- 
    Document   : ListUserPage
    Created on : Sep 26, 2011, 2:17:23 AM
    Author     : user
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="model.Staff"%>

<!DOCTYPE html>
<html>
    <%
    if(session.getAttribute("user")==null){
        response.sendRedirect("index.jsp");
    }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Welcome Home</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %>
        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">
                    <h2 style="margin-left: 10px;">List Staff</h2><hr/>
                    <table border="1" class="tutturu">
                        <tr>
                            <th>ID</th>
                            <th>username</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Previlege</th>
                            <th>Aksi</th>
                        </tr>
                        <%
                            Staff a = new Staff();
                            ArrayList<Staff> s = a.getallStaff();
                            for (int i = 0; i < s.size(); i++) {
                                Staff staff = s.get(i);
                        %>
                        <tr>
                            <td><% out.print(staff.getIds());%></td>
                            <td><% out.print(staff.getUsername());%></td>
                            <td><% out.print(staff.getFirst_name());%></td>
                            <td><% out.print(staff.getLast_name());%></td>
                            <td><% out.print(staff.getEmail());%></td>
                            <td><% out.print(staff.getPrevilage());%></td>
                            <td style="width: 120px">
                                <a class="thrbutton" href="?menu=DeleteStaffNotification&&ids=<% out.print(staff.getIds());%>">Edit</a>
                                <a class="thrbutton" href="?menu=DeleteStaffNotification&&ids=<% out.print(staff.getIds());%>">Delete</a>
                            </td>
                        </tr>
                        <% }%>
                    </table>
                    <div style="width: 100%;margin-top: 25px;border-top: 1px solid #CCC;position: absolute;bottom:0px">
                        <table style="width: 100%">
                            <tr style="height: 40px;">
                                <td style="padding-left: 40px;">
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>">Home</a>
                                    <a class="thrbutton" style="height: 32px; width: 100px" href="<%= request.getContextPath()%>/staff.jsp"><< Back</a>
                                </td>
                                <td style="text-align: right;padding-right: 40px;"><a class="thrbutton"  style="height: 32px; width: 180px" href="?menu=AddStaff">Tambah Staff</a></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>
