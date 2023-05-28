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
// import models.entities.Usuario;
import dal.CategoriaDAOI;
import dal.CategoriaDAO;
import dal.Conexion;
import utils.tools.AppPath;

/**
 * Servlet implementation class CategoriaController
 */
//@WebServlet(name = "CategoriaController", urlPatterns = {"/categorias"})
@WebServlet(name = "InsumoController", value = {"/insumos", "/insumos/"})
public class InsumoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Conexion conFactory = new Conexion();
	Connection con = conFactory.getDBConnection();
	CategoriaDAOI catDao = new CategoriaDAO(con); 
	String categoriasView = new AppPath()
			.convertToView("administration/Categorias.jsp");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsumoController() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Hitting this endpoint");
		List<Categoria> listaCategorias = catDao.listCategorias();
		request.setAttribute("categorias", listaCategorias);
//		request.getRequestDispatcher("Vista/Mantenimiento/Categorias.jsp").forward(request, response);
		System.out.printf("This is categoriasView's Path: %s\n", categoriasView);
		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
//		request.getRequestDispatcher(categoriasView).forward(request, response);
		getServletContext().getRequestDispatcher(categoriasView).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
