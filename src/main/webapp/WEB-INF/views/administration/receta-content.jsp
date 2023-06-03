<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@page import="java.util.List"%>

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
                                    <h4><i class="fas fa-carrot"></i> Mantenimiento de Recetas</h4>
                                    <hr class="mb-4">
                                    <% Receta receta = (Receta) request.getAttribute("receta");%>
                                    <form class="needs-validation" id="frmReceta" action="${rootPath}/recetas/" method="POST">
                                        <input type="hidden" value="${receta.getIdReceta()}" id="txtCod" name="txtCod" readonly="true">
                                        <div class="row">
                                            <div class="col-md-2 mb-3">
                                                <input type="text" class="form-control" value="${receta.getNombre()}" name="txtnom" id="txtnom" placeholder="Nombre de Receta" required>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <input type="text" class="form-control" value="1" name="txtuser" id="txtuser" readonly="true" required> Administrador
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <input type="hidden" value="<%= (receta != null ? receta.getIdTipoComida() : "")%>" id="txtComida" name="txtComida" readonly="true">
                                                <select class="form-select" name="cboComidas" id="cboComidas" required>
                                                    <option value="">--Elegir Comida--</option>
                                                    <% List<Comida> Comidas = (List<Comida>) request.getAttribute("comidas");
                                                        if (Comidas != null)
                                                            for (Comida com : Comidas) {
                                                                if (receta != null && com.getCod() == receta.getIdTipoComida()) {
                                                    %>
                                                    <option value="<%=com.getCod()%>" selected><%=com.getNom()%></option>
                                                    <%} else {
                                                    %>
                                                    <option value="<%=com.getCod()%>"><%=com.getNom()%></option>
                                                    <%}
                                                        }%>
                                                </select>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <input type="text" class="form-control" value="${receta.getFoto()}" name="txtfoto" id="txtfoto" placeholder="Seleccionar foto" required>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <input type="text" class="form-control" value="${receta.getLink()}" name="txtlink" id="txtlink" placeholder="Ingrese Link" required>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                            <select class="form-select" name="cboEstado" id="cboEstado" required>
                                                    <option value="">--Estado de Receta--</option>
                                                    <option value="P">Publico</option>
                                                    <option value="R">Restringido</option>
                                                </select>
                                            </div>
                                            <div class="col-md-2 mb-3">
                                                <button type="submit" name="accion" id="btnGuardarReceta" value="Agregar" class="btn btn-success btn-circle w-100"><i class="fas fa-plus"></i> Agregar</button>
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
                                                <th scope="col">USUARIO</th>
                                                <th scope="col">COMIDA</th>
                                                <th scope="col">FOTO</th>
                                                <th scope="col">LINK</th>.
                                                <th scope="col">ESTADO</th>
                                                <th scope="col">ACCIONES</th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center text-muted">
                                            <c:forEach var="receta" items="${recetas}">
                                                <tr>
                                                    <td>${receta.getIdReceta()}</th>
                                                    <td>${receta.getNombre()}</td>
                                                    <td>${receta.getUsuario()}</th>
                                                    <td>${receta.getTipoComida()}</th>
                                                    <td>${receta.getFoto()}</th>
                                                    <td>${receta.getLinkReceta()}</th>
                                                    <td>${receta.getEstado()}</th>
                                                    <td>
                                                        <a class="btn btn-sm btn-primary btn-circle" href="${rootPath}/recetas/?accion=AgregarIngredientes&cod=${receta.getIdReceta()}"><i class="fas fa-add"></i></a>
                                                        <a class="btn btn-sm btn-primary btn-circle" href="${rootPath}/recetas/?accion=Editar&cod=${receta.getIdReceta()}"><i class=" fas fa-edit"></i></a>
                                                        <a class="btn btn-sm btn-danger btn-circle" href="${rootPath}/recetas/?accion=Eliminar&cod=${receta.getIdReceta()}"><i class="fas fa-trash-alt"></i></a>
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