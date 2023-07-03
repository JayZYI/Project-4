<%-- 
    Document   : logout
    Created on : Jul 6, 2022, 10:28:05 PM
    Author     : ASUS
--%>

<%
session.setAttribute("sessionUser",null);
session.invalidate();
response.sendRedirect("index");
%>
