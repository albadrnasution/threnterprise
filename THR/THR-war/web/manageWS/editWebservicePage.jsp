<%-- 
    Document   : editWebservicePage
    Created on : Oct 25, 2011, 4:58:01 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Webservice" %>
<%    
    if (session.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp?register=2");
    } else {
        String jenisUser = new String();
        Webservice ws = new Webservice();
        String managed = new String();
        boolean add = false;
        
        if (request.getParameter("manage") != null) {
            if (request.getParameter("manage").equals("edit")) {
                if (request.getParameter("target").equals("ws")) {
                    ws = ws.getWebservice(request.getParameter("id"));
                    managed = request.getParameter("target");
                } 
            } else if (request.getParameter("manage").equals("add")) {
                if (request.getParameter("target").equals("ws")) {
                    ws = new Webservice();
                    managed = request.getParameter("target");
                    add = true;
                } 
            }
        }
        
%>        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="../script/jquery-1.6.1.js"></script>
        <title><% if (add) {
                out.print("Add User Page");
            } else {
                out.print("Edit Profile Page");
            }%></title>

    </head>
    <div id="header-wrapper">
        <div id="header">
            <div id="thrlogo"><a href="<%= request.getContextPath()%>"><img src="../images/thrlogo.png" style="height: 180px" alt="thrlogo" title="tentative logo"/></a></div>
        </div>
    </div>
    <div id="content-wrapper">
        <div id="content">
            <div class="list-sheet" style="width: 100%;padding-bottom: 40px;">
                <div style="float: left; width: 204px;">
                    <div style="width: 128px; height: 128px; text-align: center; margin: 40px;margin-bottom: 10px;text-align: center">
                        <img src="../images/person.png" alt="Bingkisan" style="max-height: 128px;" />
                    </div>
                    <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>
                    <br/><a  class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;" href="">Shopping History</a>
                </div>
                <div style="float: left; width: 740px; min-height: 140px; margin-bottom: 40px;padding-top: 15px;">
                    <h1><% if (add) {
                            out.print("Add Web Service");
                        } else {
                            out.print("Edit Web Srevice");
                        }%></h1>
                        
                    <form  action="../webserviceController?<% if (!managed.equals("")) {
                            out.print("managed=" + managed);
                        }
                        if (add) {
                            out.print("&action=add");
                        }%>  " name="editWS" method="POST" id="editWS">
                        <% if(request.getParameter("success")!=null){
                            if(request.getParameter("success").equals("0")){%>
                                <div style="color: red">Name and Server should not be blank. </div>
                            <%}
                        }%>
                        
                        <table>
                            <tr>
                                <td>Name</td>
                                <td>: <input class="filter" type="text" name="nama" id="nama" value="<% //if (r.getFirst_name() != null) {
                                    if (!add) {
                                        if (ws.getNama() != null) {
                                            out.print(ws.getNama());
                                        } 
                                   }else {
                                        out.print("");
                                   }
                                   %>"/></td>
                            </tr>
                            <tr>
                                <td>Type</td>
                                <td>:  <select name="jenis" class="filter" style="width: 150px">
                                        <option value="travel" <% if (!add) {
                                                if (ws.getJenis().equals("travel")) {
                                                    out.print("selected");
                                                }
                                            }%>>Travel</option>
                                        <option value="bingkisan" <% if (!add) {
                                                if (ws.getJenis().equals("bingkisan")) {
                                                    out.print("selected");
                                                }
                                            }%>>Bingkisan</option>
                                    </select>
                                </td>  
                            </tr>
                            <tr>
                                <td>Server</td>
                                <td>: <input class="filter" type="text" name="server" id="server" value="<%
                                    if (!add){
                                        if (ws.getServer() != null){
                                            out.print(ws.getServer());
                                        }
                                    }else {
                                        out.print("");
                                    }
                                %>"/></td>
                            </tr>
                            <tr>
                                <td>Port</td>
                                <td>: <input class="filter" type="text" name="port" id="port" value="<%
                                    if(!add){
                                        if(ws.getPort() != null){
                                            out.print(ws.getPort());
                                        }
                                    }else{
                                        out.print("");
                                    }
                                %>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Username</td>
                                <td>: <input class="filter" type="text" name="username" id="username" value="<%
                                    if(!add){
                                        if(ws.getUsername() != null){
                                            out.print(ws.getUsername());
                                        }
                                    }else {
                                        out.print("");
                                    }
                                %>" /></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td>: <input class="filter" type="text" name="password" id="password" value="<%
                                    if(!add){
                                        if(ws.getPassword() != null){
                                            out.print(ws.getPassword());
                                        }
                                    }else {
                                        out.print("");
                                    }
                                %>" /></td>
                            </tr>
                            
                            <tr>
                                <td>
                                    <input type="hidden" name="idws" value="<%                                        
                                        if (!add) {
                                            
                                                if (ws.getId() != 0) {
                                                    out.print(ws.getId());
                                                }
                                            
                                        }
                                           %>" />
                                </td>
                            </tr>
                            <tr align="center">
                                <td style="text-align: right" colspan="2"><a href="<% if (managed.equals("ws")) {
                                        out.print(request.getContextPath() + "/manageWS/webservicePage.jsp"); }%>">
                                    <button class="thrbutton" name="cancelEdit" id="cancelEdit" style="width: 150px;height: 40px;" >Cancel</button></a>
                                    <input  class="thrbutton" type="submit" name="submitProfil" id="submitProfil" style="width: 150px;height: 40px;"  /></td>
                            </tr>
                        </table>
                    </form>
                </div>  
            </div>
        </div>
    </div>
    <div id="footer-wrapper">
        <div id="footer">
            &COPY; 2011, Anpau Ltd.
            <span class="footer-link"><a href="#">About Us</a></span>
        </div>
    </div>
</body>
</html>
<%        
    }
%>
