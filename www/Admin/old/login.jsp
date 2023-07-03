<%-- 
    Document   : login
    Created on : Jul 6, 2022, 4:16:16 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
                <link rel="stylesheet" href="css/auth.css">

    </head>
    <body>
      <div class="form">

   <% if(session.getAttribute("sessionUser") != null){ %>
        <% response.sendRedirect("index"); %>
        <% } %>
        <div class="forms">
           <img src="img/adidass.png" alt="adidass">

        <form action="login" method="POST">
        <div class="regisField">
            email
            <input type="text" name="email" id="lEmail">
        </div>  
         <div class="regisField">
            password
            <input type="password" name="password" id="lPassword">
        </div>                
            
            
     
            <button type="submit" value="login">login</button><br>
            <a href="register.jsp">doesnt have account ?</a><br>
        </form>
        <% if(session.getAttribute("isEmpty") == "true"){ %>
            all field cannot null!
        <% } 
            session.setAttribute("isEmpty", null);
        %>
        
        <% if(session.getAttribute("isInvalid") == "true"){ %>
            email or password does not match (invalid)!
        <% } 
            session.setAttribute("isInvalid", null);
        %>  
   </div>
        </div>
    </body>
</html>
