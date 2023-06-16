<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="utils.tools.AppPath" %>
  
<c:set var="appPath" value="<%= new utils.tools.AppPath()%>" />
<c:set var="scriptsPath" value="${appPath.convertToView('templating/Scripts.jsp')}" />

<c:set var = "contextPath" value = "${pageContext.servletContext.contextPath}" /> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script
      src="https://kit.fontawesome.com/64d58efce2.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="${contextPath}/assets/css/login.css" />
    <title>Sign Up</title>
  </head>
  <body>
    <div class="container">
      <div class="forms-container">
        <div class="signin-signup">
          <form action="${contextPath}/signup" method="POST" class="sign-in-form">
            <img src="${contextPath}/assets/img/LaOllita_logo.png" class="image2" alt="" />

            <h2 class="title">Ingresar</h2>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input type="email" placeholder="Ingresar Email" name="emailUsuario" />
            </div>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input type="text" placeholder="Nombre Usuario" name="nombreUsuario" value="Anonymus" />
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password" placeholder="Contraseña" name="clave1"/>
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password" placeholder="Repetir Contraseña" name="clave2"/>
            </div>
            <input type="submit" value="Registrarse" class="btn solid" name="btnAcceder"/>
            <!-- 
            <p class="social-text">
              O iniciar sesión por medio de una red social
            </p>
            <div class="social-media">
              <a href="#" class="social-icon">
                <i class="fab fa-facebook-f"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-twitter"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-google"></i>
              </a>
              <a href="#" class="social-icon">
                <i class="fab fa-linkedin-in"></i>
              </a>
            </div>
             -->
          </form>

          <form action="Controlador?menu=Usuarios" class="sign-up-form">
            <img src="${contextPath}/assets/img/LaOllita_logo.png" class="image2" alt="" />

            <h2 class="title">Registro</h2>
            <div class="input-field">
              <i class="fas fa-user"></i>
              <input type="text" name="txtUsuario" placeholder="Usuario" />
            </div>
            <div class="input-field">
              <i class="fas fa-lock"></i>
              <input type="password" name="txtPassword" placeholder="Contraseña" />
            </div>
            <input type="submit" class="btn" value="Registrame" name="btnRegistrar"/>
          </form>
        </div>
      </div>

    </div>

    <script src="${contextPath}/assets/js/login.js"></script>
  </body>
</html>

