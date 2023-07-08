<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var = "contextPath" value = "${pageContext.servletContext.contextPath}" /> 

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="refresh" content="5;url=${contextPath}" />
    <script
      src="https://kit.fontawesome.com/64d58efce2.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="assets/css/login.css" />
    <title>Login Error</title>
  </head>
  <body>
    <div class="container">
      <div class="forms-container">
        <div class="signin-signup">
          <form action="ControladorUsuario" method="POST" class="sign-in-form">
            <img src="assets/img/LaOllita_logo.png" class="image2" alt="" />

            <h2 class="title"><%= request.getAttribute("errorType") %></h2>
            <p class="social-text">
							<%= request.getAttribute("errorMessage") %>
							You will be redirected in 5 seconds
            </p>
            <a href="${contextPath}/login"><span class="fa fa-user-circle" style="align-content: center"></span>Regresa al Login</a>
          </form>
        </div>
      </div>
    </div>

    <script src="assets/js/login.js"></script>
  </body>
</html>

