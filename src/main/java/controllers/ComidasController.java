package controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import dal.ComidaDao;
import utils.tools.AppPath;

/**
 * Servlet implementation class CategoriaController
 */
//@WebServlet(name = "CategoriaController", urlPatterns = {"/categorias"})
@WebServlet(
		name = "ComidasController",
		value = {"/comidas", "/comidas/", "/comidas/*"})
public class ComidasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String comidasView = new AppPath()
			.convertToView("administration/Comidas.jsp");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComidasController() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Hitting this endpoint" + request.getContextPath());
		ComidaDao comDao = new ComidaDao(); 
		List listaComidas = comDao.listar();
		request.setAttribute("comidas", listaComidas);
//		List<Categoria> listaCategorias = catDao.listCategorias();
//		request.setAttribute("categorias", listaCategorias);
//		request.getRequestDispatcher("Vista/Mantenimiento/Categorias.jsp").forward(request, response);
		System.out.printf("This is comidasView's Path: %s\n", comidasView);
		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
//		request.getRequestDispatcher(comidasView).forward(request, response);
		getServletContext().getRequestDispatcher(comidasView).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String comida = request.getParameter("txtComida");
		Object[] obCom = new Object[1];
		obCom[0] = comida;
		ComidaDao comDao = new ComidaDao(); 
		comDao.agregar(obCom);
//		getServletContext().getRequestDispatcher(
//				request.getContextPath() + "/" +
//				"Controlador?menu=Comidas&accion=Listar"
//				).forward(request, response);
		getServletContext()
			.getRequestDispatcher(comidasView)
			.forward(request, response);

	}

}
