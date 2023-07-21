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
                                    <h4><i class="fas fa-carrot"></i> Perfil de Usuario</h4>
                                    <hr class="mb-4">
                                    <% Usuario usuario = (Usuario) request.getAttribute("usuario");%>
                                    <form class="needs-validation" id="frmUsuario" action="" method="POST">
                                        <input type="hidden" value="${usuario.getIdUsuario()}" id="txtCod" name="txtCod" readonly="true">
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-6 mb-8">
                                                <label _ngcontent-serverapp-c31="" for="email">Email</label>
                                                <input type="text" class="form-control" value="${usuario.getNombreUsuario()}" name="txtnom" id="email" placeholder="Nombre de Usuario" required>
                                            </div>
                                        </div>
                                        <div class="row form-group">
																					<div class="col-md-6 mb-8">
																							<label _ngcontent-serverapp-c31="" for="pass1">Nueva Password</label>
																							<input type="password" class="form-control" value="" name="pass1" id="pass1" placeholder="Contraseña" required>
																					</div>
                                        </div>
                                        <div class="row form-group">
																					<div class="col-md-6 mb-8">
																							<label _ngcontent-serverapp-c31="" for="pass2">Reingrese la nueva Password</label>
																							<input type="password" class="form-control" value="" name="pass2" id="pass2" placeholder="Contraseña" required>
																					</div>
                                        </div>
                                        <div class="row form-group">
																					<div class="col-md-6 mb-8">
																							<label _ngcontent-serverapp-c31="" for="txtfoto"> Foto</label>
																							<input type="text" class="form-control" value="${usuario.getFoto()}" name="txtfoto" id="txtfoto" placeholder="Seleccionar foto" required>
																					</div>
                                        </div>
                                        <!-- 
                                        <div class="row">
																					<div class="col-md-2 mb-3">
                                            <select class="form-select" name="cboTipoUsuario" id="cboTipoUsuario" required>
																							<c:forEach var="tipoUsuario" items="${['Administrador', 'Cliente']}">
																									<c:if test="${usuario.getTipoUsuario() eq tipoUsuario}">
																											<option value="${tipoUsuario}" selected>${tipoUsuario}</option>
																									</c:if>
																									<c:if test="${usuario.getTipoUsuario() ne tipoUsuario}">
																											<option value="${tipoUsuario}">${tipoUsuario}</option>
																									</c:if>
																							</c:forEach>
																						</select>
																					</div>
                                        </div>
                                        <div class="row">
																					<div class="col-md-2 mb-3">
                                            <select class="form-select" name="cboEstado" id="cboEstado" required>
																									<option value="">--Estado--</option>
																									<option value="P">Publico</option>
																									<option value="R">Restringido</option>
																						</select>
																					</div>
                                        </div>
                                         -->
                                        <div class="row">
                                            <div class="col-md-2 mb-3">
                                                <button type="submit" name="accion" value="Actualizar" class="btn btn-primary btn-circle w-100"><i class="fas fa-sync-alt"></i> Actualizar</button>                 
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>