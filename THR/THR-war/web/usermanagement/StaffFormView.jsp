<%-- 
    Document   : addstaff
    Created on : Sep 26, 2011, 3:37:16 AM
    Author     : user
--%>

        <%@page import="model.Staff"%>
<% 
            Staff st = (Staff)session.getAttribute("staff");
            String mode = (String)session.getAttribute("mode");
        %>
        <h1><% if(mode.equals("edit")) out.print("Edit Staff");
                       else out.print("Tambah Staff"); %></h1>
        <br />
        <form action="<% out.print(session.getAttribute("base_url")); %>UserController?menu=<% if(mode.equals("edit")) out.print("prosesEditStaff");
                       else out.print("prosesAddStaff"); %>" method="POST">
                       <% if(mode.equals("edit")) out.print("<input type='hidden' name='ids' value='"+st.getIds()+"' />"); %>
            <table>
                <tr>
                    <td>Username : </td>
                    <td><input type="text" name="username" value="<% 
                    if(mode.equals("edit")) out.print(st.getUsername()); %>" /></td>
                </tr>
                <tr>
                    <td>First Name : </td>
                    <td><input type="text" name="first_name" value="<% 
                    if(mode.equals("edit")) out.print(st.getFirst_name()); %>" /></td>
                </tr>
                <tr>
                    <td>Last Name : </td>
                    <td><input type="text" name="last_name" value="<% 
                    if(mode.equals("edit")) out.print(st.getLast_name()); %>" /></td>
                </tr>
                <tr>
                    <td>Email : </td>
                    <td><input type="text" name="email" value="<% 
                    if(mode.equals("edit")) out.print(st.getEmail()); %>" /></td>
                </tr>
                <tr>
                    <td>Previlege : </td>
                    <td>
                        <select name="previlege">
                            <option value="manager" <% 
                    if(mode.equals("edit") && st.getPrevilage().equals("manager")) out.print("selected"); %> >manager</option>
                            <option value="officer" <% 
                    if(mode.equals("edit") && st.getPrevilage().equals("officer")) out.print("selected"); %> >officer</option>
                            <option value="admin" <% 
                    if(mode.equals("edit") && st.getPrevilage().equals("admin")) out.print("selected"); %> >admin</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td><input type="password" name="password" <% 
                    if(mode.equals("edit")) out.print("disabled"); %> /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="<%
                    if(mode.equals("edit")) out.print("Edit"); 
                    else out.print("Tambah"); %>" /></td>
                </tr>
            </table>
        </form>
