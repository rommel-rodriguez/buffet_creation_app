<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var = "contextPath" value = "${pageContext.servletContext.contextPath}" /> 

<%
String rootPath = request.getContextPath();
request.setAttribute("rootPath", rootPath);
%>

<c:set var="appPath" value="<%= new utils.tools.AppPath()%>" />
<c:set var="cabeceraPath" value="${appPath.convertToView('templating/Cabecera.jsp')}" />
<c:set var="menuPath" value="${appPath.convertToView('templating/Menu.jsp')}" />
<c:set var="barraPath" value="${appPath.convertToView('templating/BarraSuperior.jsp')}" />
<c:set var="scriptsPath" value="${appPath.convertToView('templating/Scripts.jsp')}" />

<c:set var="rootPath" scope="request" value="${pageContext.servletContext.contextPath}" />
<%-- <c:set var="rootPath" scope="request" value="<%= request.getContextPath() %>" />--%>


<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
	<meta charset="UTF-8">
	<title>Cacerola</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="16x16" href=<c:out value="${contextPath}/assets/img/cerdito.png"/> >
	<link href="https://cdn.datatables.net/1.11.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
	<!-- 
	<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	 -->
	<link rel="stylesheet" href="${contextPath}/assets/css/estilos.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

</head>
<body>
    <%-- <jsp:include page="${cabeceraPath}" /> --%>
        <div class="wrapper text-muted">
            <!-- Sidebar  -->
						<jsp:include page="${menuPath}" />
            <!-- Page Content  -->
            <div id="content">
								<jsp:include page="${barraPath}" />
                <div class="container-fluid">
						<%-- <%@ include file="${contentPath}" %>--%>
						<jsp:include page="${contentPath}" />

                
                </div>
            </div>
         </div>

			<jsp:include page="${scriptsPath}" />
</body>
</html>