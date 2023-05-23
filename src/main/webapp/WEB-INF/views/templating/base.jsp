<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
    <jsp:include page="${cabeceraPath}" />
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