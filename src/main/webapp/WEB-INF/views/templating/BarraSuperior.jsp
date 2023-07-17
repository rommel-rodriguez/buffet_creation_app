<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <button id="sidebarCollapse" class="btn btn-light" type="button"><i
                class="fas fa-bars"></i></button>
        <button class="btn btn-light d-inline-block d-lg-none ml-auto collapsed" data-bs-toggle="collapse" data-bs-target="#barra" aria-expanded="false"><i
                class="fas fa-angle-down"></i></button>
        <div class="collapse navbar-collapse justify-content-end" id="barra">
            <ul class="nav navbar-nav ml-auto">
            <!-- 
                <li class="nav-item">
                    <a class="dropdown-item disabled" href="javascript:void(0)"><i class="fas fa-user"></i> User</a>
                </li>
                <li class="nav-item">
                    <a class="dropdown-item disabled" href="javascript:void(0)"><i class="fas fa-smile"></i> Id</a>
                </li>
            
             -->
								<c:choose>
										<c:when test="${empty sessionScope.token}">
												<li class="nav-item  disabled">
														<a class="dropdown-item" href="${rootPath}/login">
														 <!--  <i class="fa fa-sign-in"></i> -->
															 <i class="bi bi-door-open"></i>
															 Login
														</a>
													
												</li>
										</c:when>
										<c:otherwise>
												
												<li class="nav-item  disabled">
														<a class="dropdown-item" href="${rootPath}/profile">
														 <!--  <i class="fa fa-sign-in"></i> -->
															 <i class="bi bi-person"></i>
															 Profile
														</a>
													
												</li>
												<!--  <a href="logoutServlet">Logout</a> -->
												<li class="nav-item  disabled">
														<a class="dropdown-item" href="${rootPath}/logout">
														 <!--  <i class="fa fa-sign-in"></i> -->
																<i class="bi bi-box-arrow-right"></i>
																Logout
														</a>
													
												</li>
										</c:otherwise>
								</c:choose>

            </ul>
        </div>
    </div>
    <input type="hidden" value="${flag}" id="txtFlag" name="txtCod" readonly="true">
</nav>