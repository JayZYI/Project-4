<%-- 
    Document   : category
    Created on : Jul 11, 2022, 5:10:32 PM
    Author     : ASUS
--%>
<%@page import="Controller.ShoeController" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="com.google.gson.Gson" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shoes By Category</title>
        <link rel="stylesheet" href="css/category.css">
    </head>
    <body>
        <header>
            <p id="p3">Adidas</p>
             <% if(session.getAttribute("sessionUser") != null){ %>
            welcome, <%= session.getAttribute("sessionUser") %> |
                <a id="p4" href="logout"> logout</a><a id="p5" href="cart"> cart</a>
            <% }else { %>
            <a id="p4" href="login">login</a><a href="register" id="p5">register</a>
            <% } %>
        </header>
        
        <% 
            String id = request.getParameter("id");
            ShoeController sc = new ShoeController();
            ResultSet rs = sc.getByCategory(Integer.valueOf(id),0,false);
        %>

        
        <div class="flex-container-column card">
            <div class="bestselle">
            <div class="display">
                <h1 id="displayNumber">
                    Category 
                    <% if(rs.next()) { %>
                    <%= rs.getString("category_name") %>
                    <% } %>
                </h1>
            </div>
                
            <div class="flex-container-row">
                <% while(rs.next()) { %>
                    <%
                        Gson gson = new Gson();
                        String[] images = gson.fromJson(rs.getString("images"), String[].class);
                    %>
                    <div class="button ">
                        <img id='ho' src="<%= images[0] %>" alt="adidas8">
                        <p><%=  rs.getString("name") %></p>
                        <p><%=  rs.getString("price") %></p>
                        <button><a href="<%= "detail?id=" + rs.getInt("id") %>">Detail</a></button>
                    </div>    
                <% } %>
            </div>
            </div>
        </div>
            
            
    <footer>
        <div class="one">
            <p>Adidas</p>
            <p>Lorem ipsum, dolor sit amet consectetur <br>
                adipisicing elit. Iste, quaerat.</p>
            <a href="https://www.instagram.com/adidas/"><img src="img/ig.png"></a>
            <a href="https://www.facebook.com/adidasID/"><img src="img/fb.png"></a>

            <a href="https://twitter.com/adidas"><img src="img/twitter.png"></a>
 
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
