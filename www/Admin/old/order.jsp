<%-- 
    Document   : order
    Created on : Jul 12, 2022, 4:43:21 PM
    Author     : ASUS
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controller.OrderController"%>
<%@page import="Controller.UserController"%>
<%@page import="Controller.OrderDetailController"%>

<%
        OrderController oc = new OrderController();
        UserController uc = new UserController();

        String name = (String) session.getAttribute("sessionUser");
        ResultSet rs1 = uc.getUser(name);
        
        if(rs1.next()){
            ResultSet rs2 = oc.checkUser(rs1.getInt("id"));
            if(rs2.next()){                    
                boolean res  = oc.updateStatus(rs2.getInt("id"));
%>

            <% if(res) { %>
            order success!
            <% }else{ %>
            order failed!
            <% } %>

<% } %>
<%  } %>
<a href="cart.jsp">back?</a>