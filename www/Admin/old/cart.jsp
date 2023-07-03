<%-- 
    Document   : cart
    Created on : Jul 11, 2022, 10:55:46 PM
    Author     : ASUS
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="Controller.UserController"%>
<%@page import="Controller.OrderDetailController" %>
<%@page import="Controller.OrderController" %>
<%@page import="java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
                <link rel="stylesheet" href="css/cart.css">
    </head>
    <body>
         <% if(session.getAttribute("sessionUser") == null){ %>
            <% response.sendRedirect("login"); %>
        <% } %>
        <header>
            <a id="p3" href="index">Adidas</a>
             <% if(session.getAttribute("sessionUser") != null){ %>
            welcome, <%= session.getAttribute("sessionUser") %> |
                <a id="p4" href="logout"> logout</a>
            <% }else { %>
            <a id="p4" href="login">login</a><a href="register" id="p5">register</a>
            <% } %>
        </header>
        
         <table rules="rows">
        <tr>
            <th id="class1">Image</th>
            <th id="class2">Name</th>
            <th id="class3">Size</th>            
            <th id="class4">Price</th>
            <th id="class5"></th>
            <th id="class6">Qty</th>
            <th id="class7">Total Price</th> 
        </tr>
        <% 
            OrderDetailController odc = new OrderDetailController(); 
            OrderController oc = new OrderController();
            UserController uc = new UserController();
            
            String name = (String) session.getAttribute("sessionUser");
            ResultSet rs1 = uc.getUser(name);
            
            if(rs1.next()){
                ResultSet rs2 = oc.checkUser(rs1.getInt("id"));
                if(rs2.next()){                    
                    ResultSet rs3 = odc.get(rs2.getInt("id"));
                    while(rs3.next()) { 
        %>
        
        <tr>
            <%
                Gson gson = new Gson();
                String[] images = gson.fromJson(rs3.getString("images"), String[].class);
            %>
            <td id="class1"><img id='ho' src="<%= images[0] %>" alt="adaidas6"></td>
            <td id="class2" VALIGN=TOP><%= rs3.getString("name") %></td>
            <td id="class3" VALIGN=TOP><%= rs3.getInt("size") %></td>
            <td id="class4" VALIGN=TOP >Rp. <%= rs3.getInt("price") %></td>
            <td id="class5" VALIGN=TOP>x</td>
            <td id="class6" VALIGN=TOP ><%= rs3.getInt("qty") %></td>
            <td id="class7" VALIGN=TOP>Rp. <%= rs3.getInt("total_price") %><br></td>
        </tr>
        <% } %>
             <% }else { %>
                cart empty!
                <a href="index">home</a>
             <% } %>
        <% } %>
        <tr>
            <td id="class1"></td>
            <td id="class2"></td>
            <td id="class3"></td>
            <td id="class4"></td>
            <%
            ResultSet rs4 = uc.getUser(name);

            if(rs4.next()){
                 ResultSet rs5 = oc.checkUser(rs1.getInt("id"));
                 if(rs5.next()) { 
            %>
            <td id="class5">Total Price : </td>
            <td id="class6">Rp. <%= rs5.getInt("total_price") %></td>
            <td id="class7"><a href="order.jsp">order!</a></td>
            <% } %>
            <% } %>
        </tr>
     </table>
           <footer>
        <!--Description Social Media-->

        <div class="one">
            <p>Adidas</p>
            <p>Lorem ipsum, dolor sit amet consectetur <br>
                adipisicing elit. Iste, quaerat.</p>
            <a href="https://www.instagram.com/adidas/"><img src="ig.png"></a>
            <a href="https://www.facebook.com/adidasID/"><img src="fb.png"></a>

            <a href="https://twitter.com/adidas"><img src="twitter.png"></a>

        </div>
        <div class="two">
            <p>Get Help</p>
            <p>About Us</p>
            <p>Contact Us</p>
            <p>Return policy</p>
            <p>Privacy Policy</p>
            <p>Payment Policy</p>
        </div>
        <div class="three">
            <p>About us</p>
            <p>News</p>
            <p>Service</p>
            <p>Our Policy</p>
            <p>Customer Care</p>
            <p>Faq's</p>
        </div>
        </footer>
    </body>
</html>
