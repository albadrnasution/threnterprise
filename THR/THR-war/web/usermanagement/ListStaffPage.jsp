<%-- 
    Document   : ListUserPage
    Created on : Sep 26, 2011, 2:17:23 AM
    Author     : user
--%>


        <%@page import="model.Staff"%>
<h1>List Staff</h1>
        <table border="1">
            <tr>
            <th>ID</th>
            <th>username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Previlege</th>
            <th>Password</th>
            <th>Aksi</th>
            </tr>
            <% for(int i=0;i<s.size();i++) { 
                Staff staff = s.get(i);
                %>
            <tr>
                <td><% out.print(staff.getIds()); %></td>
            <td><% out.print(staff.getUsername()); %></td>
            <td><% out.print(staff.getFirst_name()); %></td>
            <td><% out.print(staff.getLast_name()); %></td>
            <td><% out.print(staff.getEmail()); %></td>
            <td><% out.print(staff.getPrevilage()); %></td>
            <td><% out.print(staff.getPassword()); %></td>
            <td><a href="?menu=deletestaff&&ids=<% out.print(staff.getIds());%>">delete</a></td>
            </tr>
            <% } %>
</table>
<a href="?menu=addstaff">tambah petugas</a>