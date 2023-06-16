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
    <meta http-equiv="refresh" content="5;url=${contextPath}/login" />
    <script
      src="https://kit.fontawesome.com/64d58efce2.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="${contextPath}/assets/css/login.css" />
    <title>Formulario de Ingreso y Registro</title>
  </head>
  <body>
    <div class="container">
      <div class="forms-container">
        <h2></h2>
      </div>

      <div class="panels-container">
        <div class="panel left-panel">
          <div class="content">
            <h3> Registro Exitoso, sera redirigido a Login en 5 segundos</h3>
            <a href="${contextPath}/signup">
							<button class="btn transparent" id="sign-up-btn">Registro</button>
            </a>
          </div>
          <img src="${contextPath}/assets/img/log2.svg" class="image" alt="" />
        </div>
        <div class="panel right-panel">
          <div class="content">
            <h3>¿Ya tienes una cuenta?</h3>
            <p>
              Ingresa y sigue actualizando tus recetas y llevando control de lo
              que comes y de tu presupuesto. ¡Te extrañamos!
            </p>
            <button class="btn transparent" id="sign-in-btn">
              Inicia Sesión
            </button>
          </div>
          <img src="${contextPath}/assets/img/register2.svg" class="image" alt="" />
        </div>
      </div>
    </div>

    <script src="${contextPath}/assets/js/login.js"></script>
  </body>
</html>

