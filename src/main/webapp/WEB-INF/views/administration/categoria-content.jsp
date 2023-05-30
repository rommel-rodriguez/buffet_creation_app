<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- <%@ include file="/WEB-INF/views/templating/base.jsp" %>--%>

	<div class="container-fluid">
			<div class="row">
					<div class="col-md-6 order-md-1">
									<div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
											<div class="card-body">
													<h4><i class="fas fa-list-ol"></i> Mantenimiento de Categorías</h4>
													<hr class="mb-4">
													<form class="needs-validation" action="${rootPath}/categorias/" method="POST">
															<input type="hidden" value="${categoria.getCod()}" name="txtCod" readonly="true">
															<div class="row">
																	<div class="col-md-6 mb-3">
																	<input type="text" class="form-control" value="${categoria.getNom()}" name="txtCategoria" placeholder="Nombre de la categoría"  required>
																	</div>
																	<div class="col-md-3 mb-3">
																			<button type="submit" name="accion" value="AgregarCategoria" class="btn btn-success btn-circle w-100"><i class="fas fa-plus"></i> Agregar</button>
																	</div>
																	<div class="col-md-3 mb-3">
																			<button type="submit" name="accion" value="ActualizarCategoria" class="btn btn-primary btn-circle w-100"><i class="fas fa-sync-alt"></i> Actualizar</button>
																	</div>
															</div>
													</form>
											</div>
									</div>
									<div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
											<div class="card-body">
													<table id="tablaCategoria" class="table table-hover table-sm table-bordered">
															<thead class="table-dark text-center">
																	<tr>
																			<th scope="col">ID</th>
																			<th scope="col">CATEGORIA</th>
																			<th scope="col">ACCIONES</th>
																	</tr>
															</thead>
															<tbody class="text-center text-muted">
																	<c:forEach var="categ" items="${categorias}">
																			<tr>
																					<td>${categ.getCod()}</th>
																					<td>${categ.getNom()}</td>
																					<td>
																							<%-- <a class="btn btn-sm btn-primary btn-circle" href="Controlador?menu=Categorias&accion=EditarCategoria&cod=${categ.getCod()}"><i class=" fas fa-edit"></i></a>--%>
																						<form id="in_table_form" action="${rootPath}/categorias/" method="POST">
																						  <%--
																							<a class="btn btn-sm btn-primary btn-circle" href="${rootPath}/categorias/?accion=EditarCategoria&cod=${categ.getCod()}"><i class=" fas "></i></a>
																							<a class="btn btn-sm btn-danger btn-circle" href="Controlador?menu=Categorias&accion=EliminarCategoria&cod=${categ.getCod()}"><i class="fas fa-trash-alt"></i></a>
																						   --%>
																							<input type="hidden" value="${categ.getCod()}" name="cod" readonly="true">
																							<button type="submit" name="accion" value="EditarCategoria" class="btn btn-sm btn-primary btn-circle" ><i class="fas fa-edit"></i> </button>
																							<button type="submit" name="accion" value="EliminarCategoria" class="btn btn-sm btn-danger btn-circle" ><i class="fas fa-trash-alt"></i></button>
																						</form>
																					</td>
																			</tr> 
																	</c:forEach>
															</tbody>
													</table>
											</div>
									</div>
					</div>
																	<div class="col-md-6 mb-3 order-md-2">
							<div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
									<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
											<div class="carousel-indicators">
													<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
													<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
													<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
											</div>
											<div class="carousel-inner">
													<div class="carousel-item active">
															<img src="${rootPath}/assets/img/food1.jpg" class="d-block w-100" alt="...">
													</div>
													<div class="carousel-item">
															<img src="${rootPath}/assets/img/food2.jpg" class="d-block w-100" alt="...">
													</div>
													<div class="carousel-item">
															<img src="${rootPath}/assets/img/food3.jpg" class="d-block w-100" alt="...">
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
									<p>Elabora tu plan semanal, tenemos un extenso catálogo de ingredientes. </p>
									<p>¿Se te antojó un lomo saltado? ¿Quizá una ensalada César? Agrega snacks, ensaladas, bebidas y más.</p>
									<button class="btn btn-outline-light btn-block" type="button">Visualiza tus presupuestos</button>
							</div>
					</div>
			</div>
	</div>