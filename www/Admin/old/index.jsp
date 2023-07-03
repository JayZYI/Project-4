<%-- 
    Document   : index
    Created on : Jul 6, 2022, 8:05:25 AM
    Author     : ASUS
--%>

<%@page import="Controller.ShoeController" %>
<%@page import="Controller.CategoryController" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="com.google.gson.Gson" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/index.css">
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
        
        <main>
        
        <img id="color" src="img/INI BARUAN.png" alt="adiasa">
        
        <div class="bestselle">
            <div class="display">
               <h1 id="displayNumber">Categories</h1>
            </div>

            <div class="flex-container-row">

                <% 
                    CategoryController cc = new CategoryController();
                    ResultSet rs1 = cc.getAll();
                %>

                <% while(rs1.next()) { %>

                    <div class="buttons">
                        <a href="<%= "category?id=" + rs1.getInt("id") %>"><%= rs1.getString("name") %></a>
                    </div>

                <% } %>
            </div>
        </div>
            
        <div class="bestselle">
            <div class="display">
                <h1 id="displayNumber">New Shoes</h1>
                <a href="shoe">+ add new shoe</a>
            </div>
            <div class="flex-container-row">
                <% 
                    ShoeController sc1 = new ShoeController();
                    ResultSet rs2 = sc1.getAll(true);
                %>
                
                <% while(rs2.next()) { %>
                    <%
                        Gson gson = new Gson();
                        String[] images = gson.fromJson(rs2.getString("images"), String[].class);
                    %>

                    <div class="button">
                       <img id='ho'  src="<%= images[0] %>" alt="adidas12">
                       <p><%=  rs2.getString("name") %></p>
                       <p>Rp<%= rs2.getInt("price") %></p>
                       <button><a href='<%= "detail?id=" + rs2.getInt("id") %>'>Detail</a></button>
                    </div>
                <% } %>
            </div>
        </div>

        <div class="bestselle">
            <div class="display">
                <h1 id="displayNumber">Best Shoes</h1>
            </div>
            <div class="flex-container-row">
                <% 
                    ShoeController sc2 = new ShoeController();
                    ResultSet rs3 = sc2.getAll(false);
                %>
                
                <% while(rs3.next()) { %>
                    <%
                        Gson gson = new Gson();
                        String[] images = gson.fromJson(rs3.getString("images"), String[].class);
                    %>

                    <div class="button">
                       <img id='ho'  src="<%= images[0] %>" alt="adidas12">
                       <p><%=  rs3.getString("name") %></p>
                       <p>Rp<%= rs3.getInt("price") %></p>
                       <button><a href='<%= "detail?id=" + rs3.getInt("id") %>'>Detail</a></button>
                    </div>
                <% } %>
               
            </div>
        </div>
        </main>
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
