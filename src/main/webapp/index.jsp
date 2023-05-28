<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="utils.tools.AppPath" %>
  
<c:set var="appPath" value="<%= new utils.tools.AppPath()%>" />
<c:set var="cabeceraPath" value="${appPath.convertToView('templating/Cabecera.jsp')}" />
<c:set var="menuPath" value="${appPath.convertToView('templating/Menu.jsp')}" />
<c:set var="barraPath" value="${appPath.convertToView('templating/BarraSuperior.jsp')}" />
<c:set var="scriptsPath" value="${appPath.convertToView('templating/Scripts.jsp')}" />

<c:set var = "contextPath" value = "${pageContext.servletContext.contextPath}" /> 

<%--
    <%
        String contextPath = request.getContextPath();
        String testPath = "this/is/a/test/path"; 
    %>
--%>
<%
    String anotherPath = "this/is/another/path"; 
%>
<!DOCTYPE html>
<html>
    <%-- <%@ include file='Vista/Genericos/Cabecera.jsp' %> --%>
    <jsp:include page="${cabeceraPath}" />
    <body>
        <div class="wrapper text-muted">
            <!-- Sidebar  -->
            <%-- <%@ include file='WEB-INF/views/templating/Menu.jsp' %> --%>
						<jsp:include page="${menuPath}" />
            <!-- Page Content  -->
            <div id="content">
                <%-- <%@ include file='Vista/Genericos/BarraSuperior.jsp' %> --%>
								<jsp:include page="${barraPath}" />
                <div class="ollita">
                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
                                <div class="card-body">
                                    <h3><i class="fas fa-home"></i> Home</h3>
                                    <h5 class="text-muted font-weight-normal mb-0 w-100 text-truncate"><i class="fas fa-arrow-right"></i> Cacerola - Tu alternativa para comer saludable y de acuerdo a tu presupuesto.</h5>  
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
                                <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                                    <div class="carousel-indicators">
                                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                                    </div>
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                            <img src="/assets/img/food1.jpg" class="d-block w-100" alt="...">
                                        </div>
                                        <div class="carousel-item">
                                            <img src="/assets/img/food2.jpg" class="d-block w-100" alt="...">
                                        </div>
                                        <div class="carousel-item">
                                            <img src="/assets/img/food3.jpg" class="d-block w-100" alt="...">
                                        </div>
                                    </div>
                                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Anterior</span>
                                    </button>
                                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Siguiente</span>
                                    </button>
                                </div>
                            </div>
                            <div class="p-5 text-white bg-dark rounded-3">
                                <h5>Este es el primer paso a cumplir tu objetivo</h5>
                                <p>Elabora tu plan semanal, tenemos un extenso cat�logo de ingredientes. </p>
                                <p>�Se te antoj� un lomo saltado? �Quiz� una ensalada C�sar? Agrega snacks, ensaladas, bebidas y m�s.</p>
                                <button class="btn btn-outline-light btn-block" type="button">Visualiza tus presupuestos</button>
                            </div>
                        </div>
                        <div class="col-md-8 mb-3">
                            <div class="p-4 text-white bg-dark rounded-3">
                                <div class="row">
                                    <div class="col-md-10 mb-3">
                                        <h4>Testimonios</h4>
                                        <p>Los testimonios de nuestros clientes, avalan nuestro servicio. Ollita.pe siempre se encuentra atento al feedback de sus clientes para brindar un mejor servicio. </p>
                                    </div>
                                    <div class="col-md-2 mb-3 text-center">
                                        <h4 class="display-4"><i class="fas fa-comment-dots"></i></h4>
                                        <button class="btn btn-outline-light btn-block" type="button">Ver m�s</button>
                                    </div> 
                                </div>
                            </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
                                            <div class="card-body">
                                                <h5 class="card-title">Ahorr� tiempo sin dejar de comer saludable</h5>
                                                <p class="card-text">Armando Casas, residente en Lima de 27 a�os, encontr� que Ollita.pe es m�s que un simple men� nutritivo. Se trata de una experiencia culinaria que enriquece su d�a a d�a con sabor y salud.</p>
                                                <span style="float:left;">Lima-Per�</span><span style="float:right;"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i></span>
                                            </div>
                                        </div>
                                        <div class="card border-light my-2 p-1 bg-success text-white rounded shadow-sm">
                                            <div class="card-body">
                                                <h5 class="card-title">La salud es el reflejo de nuestros h�bitos</h5>
                                                <p class="text-white">Cynthia Vizcarra es una feliz cliente. Hoy comprob� que Ollita.pe le ayuda a cumplir tu objetivo incluso si hay una pandemia global declarada.</p>
                                                <span style="float:left;">Lima-Per�</span><span style="float:right;"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <div class="card border-light my-2 p-1 bg-primary text-white rounded shadow-sm">
                                            <div class="card-body">
                                                <h5 class="card-title">La salud es el reflejo de nuestros h�bitos</h5>
                                                <p class="text-white">Cynthia Vizcarra es una feliz cliente. Hoy comprob� que Ollita.pe le ayuda a cumplir tu objetivo incluso si hay una pandemia global declarada.</p>
                                                <span style="float:left;">Lima-Per�</span><span style="float:right;"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></span>
                                            </div>
                                        </div>
                                        <div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
                                            <div class="card-body">
                                                <h5 class="card-title">Ahorr� tiempo sin dejar de comer saludable</h5>
                                                <p class="card-text">Armando Casas, residente en Lima de 27 a�os, encontr� que Ollita.pe es m�s que un simple men� nutritivo. Se trata de una experiencia culinaria que enriquece su d�a a d�a con sabor y salud.</p>
                                                <span style="float:left;">Lima-Per�</span><span style="float:right;"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%-- <%@ include file='Vista/Genericos/Scripts.jsp' %>--%>
						<jsp:include page="${scriptsPath}" />
    </body>
</html>
