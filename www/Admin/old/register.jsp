<%-- 
    Document   : register
    Created on : Jul 6, 2022, 4:16:24 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
                        <link rel="stylesheet" href="css/auth.css">

    </head>
    <body>

        		<div class="form">
                            	<img src="img/adidass.png" alt="adidass">
			<div class="forms">

        <% if(session.getAttribute("sessionUser") != null){ %>
        <% response.sendRedirect("index"); %>
        <% } %>
        
        <form action="register" method="post">
         <div class="regisField">
            name
            <input type="text" name="name" id="rName">
            </div>
            
           <div class="regisField">
            email
            <input type="email" name="email" id="rEmail">
           </div>
            
            <div class="regisField">
            password
            <input type="password" name="password" id="rPassword" >
             </div>
            <button type="submit" value="register">Register</button><br>
            <a href="login">already have account?</a>
        </form>
        <% if(session.getAttribute("isEmpty") == "true"){ %>
            all field cannot null!
        <% } 
            session.setAttribute("isEmpty", null);
        %>
        
        <% if(session.getAttribute("isDuplicate") == "true"){ %>
            email or name is already registered!
        <% } 
            session.setAttribute("isDuplicate", null);
        %>
    </body>
</html>

