package controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.*;
// import dal.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import models.entities.Categoria;
import models.entities.Comida;
// import models.entities.Usuario;
import dal.Conexion;
import dal.CategoriaDAOI;
import dal.CategoriaDAO;
import dal.ComidaDAOI;
import dal.ComidaDao;
import utils.tools.AppPath;

/**
 * Servlet implementation class CategoriaController
 */
//@WebServlet(name = "CategoriaController", urlPatterns = {"/categorias"})
@WebServlet(
		name = "LoginController",
		value = {"/login", "/login/"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String loginView = new AppPath()
			.convertToView("public/login/login.jsp");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//        Connection con = (new Conexion()).getDBConnection();
//		System.out.println("Hitting this endpoint" + request.getContextPath());
//		ComidaDao comDao = new ComidaDao(con); 
//		request.setAttribute("login", listaComidas);
//		System.out.printf("This is loginView's Path: %s\n", loginView);
//		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
//		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
		getServletContext().getRequestDispatcher(loginView).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		ComidaDAOI catDao = new ComidaDao(con); 
		String accion = request.getParameter("accion");

        switch (accion) {
			case "AgregarComida" :
				String nomComida = request.getParameter("txtComida");
				Comida objCat = new Comida(); 
				objCat.setNom(nomComida);
				
				catDao.createComida(objCat);
				doGet(request, response);
				break;
			case "EditarComida":
				int idCatEdit = Integer.parseInt(request.getParameter("cod"));
				Comida catEditar = catDao.showComida(idCatEdit);
				request.setAttribute("comida", catEditar);
				doGet(request, response);
				break;
            case "ActualizarComida":
				String comidaUp = request.getParameter("txtComida");
				int codCatUp = Integer.parseInt(request.getParameter("txtCod"));
				Comida obCatUp = new Comida();
				obCatUp.setCod(codCatUp);
				obCatUp.setNom(comidaUp);
				catDao.updateComida(obCatUp);
				doGet(request, response);
				break;
            case "EliminarComida":
				int idCatElim = Integer.parseInt(request.getParameter("cod"));
				catDao.deleteComida(idCatElim);
				doGet(request, response);
				break;
			default:
				Exception e;
				break;
        }

	}

}
