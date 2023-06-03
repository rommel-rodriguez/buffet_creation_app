<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@page import="java.util.List"%>

<%@page import="models.entities.Usuario"%>
<%@page import="models.entities.Comida"%>
<%@page import="models.entities.Receta"%>
<%@page import="models.entities.Insumo"%>
<%@page import="models.entities.Medida" %>
<%@page import="models.entities.Categoria" %>

<%-- <%@ include file="/WEB-INF/views/templating/base.jsp" %>--%>


                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12 order-md-1">
                            <div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
                                <div class="card-body">
                                    <h4><i class="fas fa-carrot"></i> Mantenimiento de Usuarios</h4>
                                    <hr class="mb-4">
                                    <% Usuario usuario = (Usuario) request.getAttribute("usuario");%>
                                    <form class="needs-validation" id="frmUsuario" action="${rootPath}/usuarios/" method="POST">
                                        <input type="hidden" value="${usuario.getIdUsuario()}" id="txtCod" name="txtCod" readonly="true">
                                        <div class="row">
                                            <div class="col-md-2 mb-3">
                                                <input type="text" class="form-control" value="${usuario.getNombreUsuario()}" name="txtnom" id="txtnom" placeholder="Nombre de Usuario" required>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <input type="password" class="form-control" value="${usuario.getClave()}" name="txtclave" id="txtnom" placeholder="Contraseña" required>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <input type="text" class="form-control" value="${usuario.getFoto()}" name="txtfoto" id="txtfoto" placeholder="Seleccionar foto" required>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                            <select class="form-select" name="cboTipoUsuario" id="cboTipoUsuario" required>
                                                    <option value="">--Tipo de Usuario--</option>
                                                    <option value="Administrador">Administrador</option>
                                                    <option value="Cliente">Cliente</option>
                                                </select>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                            <select class="form-select" name="cboEstado" id="cboEstado" required>
                                                    <option value="">--Estado--</option>
                                                    <option value="P">Publico</option>
                                                    <option value="R">Restringido</option>
                                                </select>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <button type="submit" name="accion" id="btnGuardarUsuario" value="Agregar" class="btn btn-success btn-circle w-100"><i class="fas fa-plus"></i> Agregar</button>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <button type="submit" name="accion" value="Actualizar" class="btn btn-primary btn-circle w-100"><i class="fas fa-sync-alt"></i> Actualizar</button>                 
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="card border-light my-2 p-1 bg-body rounded shadow-sm">
                                <div class="card-body">
                                    <table id="tablaReceta" class="table table-hover table-sm table-bordered">
                                        <thead class="table-dark text-center">
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">NOMBRE</th>
                                                <%--<th scope="col">CLAVE</th>--%>
                                                <th scope="col">FOTO</th>
                                                <th scope="col">TIPO DE USUARIO</th>
                                                <th scope="col">ESTADO</th>
                                                <th scope="col">ACCIONES</th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center text-muted">
                                            <c:forEach var="usuario" items="${usuarios}">
                                                <tr>
                                                    <td>${usuario.getIdUsuario()}</th>
                                                    <td>${usuario.getNombreUsuario()}</td>
                                                    <%--<td>${usuario.getClave()}</th>--%>
                                                    <td>${usuario.getFoto()}</th>
                                                    <td>${usuario.getTipoUsuario()}</th>
                                                    <td>${usuario.getEstado()}</th>
                                                    <td>
                                                    <!-- 
                                                        <a class="btn btn-sm btn-primary btn-circle" href="Controlador?menu=Usuario&accion=Editar&cod=${usuario.getIdUsuario()}"><i class=" fas fa-edit"></i></a>
                                                        <a class="btn btn-sm btn-danger btn-circle" href="Controlador?menu=Usuario&accion=Eliminar&cod=${usuario.getIdUsuario()}"><i class="fas fa-trash-alt"></i></a>
                                                     -->
																												<form id="in_table_form" action="${rootPath}/usuarios/" method="POST">
																													<%--
																													<a class="btn btn-sm btn-primary btn-circle" href="${rootPath}/usuarios/?accion=EditarUsuario&cod=${usuario.getCod()}"><i class=" fas "></i></a>
																													<a class="btn btn-sm btn-danger btn-circle" href="Controlador?menu=Usuarios&accion=EliminarUsuario&cod=${usuario.getCod()}"><i class="fas fa-trash-alt"></i></a>
																													 --%>
																													<input type="hidden" value="${usuario.getIdUsuario()}" name="cod" readonly="true">
																													<button type="submit" name="accion" value="EditarUsuario" class="btn btn-sm btn-primary btn-circle" ><i class="fas fa-edit"></i> </button>
																													<button type="submit" name="accion" value="EliminarUsuario" class="btn btn-sm btn-danger btn-circle" ><i class="fas fa-trash-alt"></i></button>
																												</form>
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>