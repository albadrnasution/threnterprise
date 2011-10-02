<%-- 
    Document   : UserController.jsp
    Created on : Sep 26, 2011, 2:55:26 AM
    Author     : user
--%>

<%@page import="model.Customer"%>
<%@page import="util.EmailHandler"%>
<%@page import="model.Privilege"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Staff" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        // home menu
        if(request.getParameter("menu")==null) {%>
        <%@include file="home.jsp"  %>
            <%
                       }
        // list staff menu
               else if(request.getParameter("menu").equals("liststaff"))
                       {
        ArrayList<Staff> s =new ArrayList<Staff>();
        Staff t =new Staff();
        s = t.getallStaff();
        s.size();
        %>
        <%@include file="ListStaffPage.jsp" %>
        <% } 
        // list customer menu
               else if(request.getParameter("menu").equals("listcustomer"))
                       {
        ArrayList<Customer> c =new ArrayList<Customer>();
        Customer t =new Customer();
        c = t.getallCustomer();
        c.size();
        out.print(c.size());
        %>
        <%@include file="ListCustomerPage.jsp" %>
        <% } 
        // add staff menu
               else if(request.getParameter("menu").equals("addstaff")) {
               
        %>
        <%@include file="staffForm.jsp" %>
        <% }
        else if(request.getParameter("menu").equals("addcustomer")) {
               
        %>
        <%@include file="customerForm.jsp" %>
        <% }
        
        // proses add staff
        else if(request.getParameter("menu").equals("addstaffprocess"))
       {
            
            EmailHandler em = new EmailHandler();
            String passmd5 = em.getStringMD5(request.getParameter("password"));
            Staff staff =new Staff();
            staff.setUsername(request.getParameter("username"));
            staff.setFirst_name(request.getParameter("first_name"));
            staff.setLast_name(request.getParameter("last_name"));
            staff.setEmail(request.getParameter("email"));
            staff.setPrevilage(new Privilege(request.getParameter("previlege")));
            staff.setPassword(passmd5);
            staff.addStaff();
            //String res = staff.addStaff();
            //out.print(res);
            String redirectURL = "UserController.jsp?menu=liststaff";
            response.sendRedirect(redirectURL);
        }
        
        else if(request.getParameter("menu").equals("addcustomerprocess"))
       {
            out.print(request.getParameter("menu"));
            EmailHandler em = new EmailHandler();
            String passmd5 = em.getStringMD5(request.getParameter("password"));
            Customer customer =new Customer();
            customer.setFirst_name(request.getParameter("first_name"));
            customer.setLast_name(request.getParameter("last_name"));
            customer.setAddress(request.getParameter("address"));
            customer.setPhone(request.getParameter("phone"));
            customer.setPassword(passmd5);
            customer.setEmail(request.getParameter("email"));
            customer.setPlace_of_birth(request.getParameter("place_of_birth"));
            customer.setDate_of_birth2(request.getParameter("date_of_birth"));
            //customer.addCustomer();
            String res = customer.addCustomer();
            out.print(res);
            //String redirectURL = "UserController.jsp?menu=listcustomer";
            //response.sendRedirect(redirectURL);
        }
        
        else if(request.getParameter("menu").equals("deletestaff"))
       {
            Staff staff =new Staff();
            staff.deleteStaff(request.getParameter("ids"));
            String redirectURL = "UserController.jsp?menu=liststaff";
            response.sendRedirect(redirectURL);
        }
        
        else if(request.getParameter("menu").equals("deletecustomer"))
       {
            //out.print(request.getParameter("menu"));
            Customer customer =new Customer();
            String res = customer.deleteCustomer(request.getParameter("idc"));
            out.print(res);
            //String redirectURL = "UserController.jsp?menu=listcustomer";
            //response.sendRedirect(redirectURL);
        }
        %>
        
    </body>
</html>
