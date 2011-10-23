<%-- 
    Document   : home
    Created on : Sep 26, 2011, 3:30:26 AM
    Author     : user
--%>


        <h1>Hello World!</h1>
        <a href="<% out.print(request.getContextPath()); %>/UserController?menu=ListStaff">staff</a><br />
        <a href="<% out.print(request.getContextPath()); %>/UserController?menu=ListCustomer">customer</a><br />
