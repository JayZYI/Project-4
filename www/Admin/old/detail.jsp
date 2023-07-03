<%-- 
    Document   : detail
    Created on : Jul 11, 2022, 6:23:23 PM
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
        <title>Detail Shoe</title>
        <link rel='stylesheet'  href='css/detail.css'>
    </head>
    <body>
        <header>
            <div class="header">
                <a id="p3" href="index">Adidas</a>
                    <% if(session.getAttribute("sessionUser") != null){ %>
                    welcome, <%= session.getAttribute("sessionUser") %> |
                    <p id="p4"><a  href="logout"> logout</a></p><p id="p5"><a  href="cart"> cart</a></p>
                    <% }else { %>
                    <p id="p4"><a id="p4" href="login">login</a></p><p  id="p5"><a href="register">register</a></p>
                    <% } %>
            </div>
        </header>
        
        <% 
            String id = request.getParameter("id");
            ShoeController sc = new ShoeController();
            ResultSet rs1 = sc.getById(Integer.valueOf(id));
        %>
        
     
       <main class="container">
            
        <% if(rs1.next()) { %>
            <%=  rs1.getString("sold") %>
            <%=  rs1.getInt("stock") %>
            <%=  rs1.getInt("size") %>
               
            <%
                Gson gson = new Gson();
                String[] images = gson.fromJson(rs1.getString("images"), String[].class);
            %>
            
              <div class="left-column"> <!-- picture container -->
          <div class="left-column-image"> <!-- images -->
              
            <img class="active" src="<%= images[0] %>" alt=""> 
            <img class="active" src="<%= images[1] %>" alt="">
            <img class="active" src="<%= images[2] %>" alt=""> 
            <img class="active" src="<%= images[3] %>" alt=""> 
            <svg onclick="plusDivs(-1)" id="prev" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--! Font Awesome Pro 6.1.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. --><path d="M9.375 233.4l128-128c12.5-12.5 32.75-12.5 45.25 0s12.5 32.75 0 45.25L109.3 224H480c17.69 0 32 14.31 32 32s-14.31 32-32 32H109.3l73.38 73.38c12.5 12.5 12.5 32.75 0 45.25c-12.49 12.49-32.74 12.51-45.25 0l-128-128C-3.125 266.1-3.125 245.9 9.375 233.4z"/></svg>
            <svg onclick="plusDivs(1)" id="next" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--! Font Awesome Pro 6.1.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. --><path d="M502.6 278.6l-128 128c-12.51 12.51-32.76 12.49-45.25 0c-12.5-12.5-12.5-32.75 0-45.25L402.8 288H32C14.31 288 0 273.7 0 255.1S14.31 224 32 224h370.8l-73.38-73.38c-12.5-12.5-12.5-32.75 0-45.25s32.75-12.5 45.25 0l128 128C515.1 245.9 515.1 266.1 502.6 278.6z"/></svg>
          </div>
          </div>
       
          <div class="right-column"> <!-- side panel -->
       
            <div class="product-description">
              <span>• <%=  rs1.getString("category_name") %></span>
              <h1><i><%=  rs1.getString("name") %></i></h1>
            
              <p>
                <%=  rs1.getString("description") %>
              </p>
            </div>
            
        
            <form action="detail" method="post">
                <div class="stock-config">
                  <div class="stock-choose">
                    <span>Stock :</span> 
                    <span id="stock"><%=  rs1.getInt("stock") %></span>
                  </div>
                </div>

                <div class="product-size" ><p>Size</p>
                  <select id="size" name="size">
                    <option value="<%=  rs1.getInt("size") %>" selected><%=  rs1.getInt("size") %></option>
                  </select>
                    qty
                <input type="number" name="qty" required min="1" max="<%= rs1.getInt("stock") %>">
                </div>
                 
                <input type="hidden" name="shoeId" value="<%= rs1.getString("id") %>">

                <div class="product-price">
                  <span>Rp.<%=  rs1.getString("price") %></span>
                    <% if(session.getAttribute("sessionUser") == null){ %>
                    <input type="button" value="insert to cart" class="cart-btn">
                    <a href="login">login first!</a>
                    <% }else { %>
                        <input type="submit" value="insert to cart" class="cart-btn">
                    <%  } %>
                </div>
            </form>
          </div>
        <% } %>
        
        
         <% 
            int category_id = rs1.getInt("category_id");
            ResultSet rs2 = sc.getByCategory(Integer.valueOf(id), category_id, true);
        %>
        
       
        <div class="related-img">
          <p id="related-text"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--! Font Awesome Pro 6.1.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. --><path d="M223.7 130.8L149.1 7.77C147.1 2.949 141.9 0 136.3 0H16.03c-12.95 0-20.53 14.58-13.1 25.18l111.3 158.9C143.9 156.4 181.7 137.3 223.7 130.8zM256 160c-97.25 0-176 78.75-176 176S158.8 512 256 512s176-78.75 176-176S353.3 160 256 160zM348.5 317.3l-37.88 37l8.875 52.25c1.625 9.25-8.25 16.5-16.63 12l-46.88-24.62L209.1 418.5c-8.375 4.5-18.25-2.75-16.63-12l8.875-52.25l-37.88-37C156.6 310.6 160.5 299 169.9 297.6l52.38-7.625L245.7 242.5c2-4.25 6.125-6.375 10.25-6.375S264.2 238.3 266.2 242.5l23.5 47.5l52.38 7.625C351.6 299 355.4 310.6 348.5 317.3zM495.1 0H375.7c-5.621 0-10.83 2.949-13.72 7.77l-73.76 122.1c42 6.5 79.88 25.62 109.5 53.38l111.3-158.9C516.5 14.58 508.9 0 495.1 0z"/></svg>Related Image</p>
          <div class="flex-container-row">
            <% while(rs2.next()) { %>
             <%
                Gson gson = new Gson();
                String[] images = gson.fromJson(rs2.getString("images"), String[].class);
            %>
              <div class="button">
                    <img id='ho' src=" <%= images[0] %>" alt="adidas 9">
                    <p><%=  rs2.getString("name") %></p>    
                    <p>Rp<%=  rs2.getString("price") %></p>
                    <a href="<%= "detail?id=" + rs2.getInt("id") %>">detail</a>
                </div>
             <% } %>
        </div>
        </div>
          
       </main>
        
    </body>
    <footer>
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
    <script src="js/detail.js"></script>

</html>
