<%-- 
    Document   : register
    Created on : Jul 6, 2022, 4:16:24 PM
    Author     : ASUS
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="Controller.CategoryController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new shoe</title>
        <link rel="stylesheet" href="css/auth.css">
    </head>
    <body>

        <div class="form">
                <img src="img/adidass.png" alt="adidass">
        <div class="forms">

        <% if(session.getAttribute("sessionUser") == null){ %>
        <% response.sendRedirect("login"); %>
        <% } %>
        
        <form action="shoe" method="post">
            <div class="regisField">
                category
                <% 
                    CategoryController cc = new CategoryController();
                    ResultSet rs = cc.getAll();
                %>
                <select name="category_id" id="rPassword">
                    <% while(rs.next()) { %>

                    <option value="<%= rs.getInt("id") %>"><%= rs.getString("name") %></option>

                    <% } %>
                </select>
                name
                <input type="text" name="name" id="rPassword" required>
                      description
                <input type="text" name="description" id="rPassword" required >
                          price
                <input type="number" name="price" id="rPassword" required>
                          stock
                <input type="number" name="stock" id="rPassword" required >
                 size
                <input type="number" name="size" id="rPassword" required >
                                image url 1
                <input type="text" name="image1" id="rPassword" required>
                                image url 2
                <input type="text" name="image2" id="rPassword" required >
                image url 3
                <input type="text" name="image3" id="rPassword" required >
                image url 4
                <input type="text" name="image4" id="rPassword" required >
                <br><br>

                <button type="submit" value="register">Create Shoe</button><br>
            </div>
        </form>
        
        <% if(session.getAttribute("isEmpty") == "true"){ %>
            all field cannot null!
        <% } 
            session.setAttribute("isEmpty", null);
        %>
        
        <% if(session.getAttribute("isDuplicate") == "true"){ %>
            shoe name is duplicated!
        <% } 
            session.setAttribute("isDuplicate", null);
        %>
    </body>
</html>

