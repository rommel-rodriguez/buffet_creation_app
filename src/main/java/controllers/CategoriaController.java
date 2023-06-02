package controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import dal.Conexion;
import utils.tools.AppPath;

/**
 * Servlet implementation class CategoriaController
 */
//@WebServlet(name = "CategoriaController", urlPatterns = {"/categorias"})
@WebServlet(name = "CategoriaController", value = {"/categorias", "/categorias/"})
public class CategoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String categoriasView = new AppPath()
			.convertToView("administration/Categorias.jsp");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaController() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Surroung all Connection related code with appropriate try-catch
		// blocks.
		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		CategoriaDAOI catDao = new CategoriaDAO(con); 
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
		// TODO: Surroung all Connection related code with appropriate try-catch
		// blocks.
		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		CategoriaDAOI catDao = new CategoriaDAO(con); 
		String accion = request.getParameter("accion");
//		System.out.printf(
//				"Categorias PostMethod: accion=%s\n",
//				accion
//				);

//
        switch (accion) {
			case "AgregarCategoria" :
				String nomCategoria = request.getParameter("txtCategoria");
				Categoria objCat = new Categoria(); 
				objCat.setNom(nomCategoria);
				
				catDao.createCategoria(objCat);
				doGet(request, response);
				break;
			case "EditarCategoria":
				int idCatEdit = Integer.parseInt(request.getParameter("cod"));
				Categoria catEditar = catDao.showCategoria(idCatEdit);
				request.setAttribute("categoria", catEditar);
//				request.getRequestDispatcher("Controlador?menu=Categorias&accion=Listar").forward(request, response);
				doGet(request, response);
				break;
            case "ActualizarCategoria":
				String categoriaUp = request.getParameter("txtCategoria");
				int codCatUp = Integer.parseInt(request.getParameter("txtCod"));
				Categoria obCatUp = new Categoria();
				obCatUp.setCod(codCatUp);
				obCatUp.setNom(categoriaUp);
				catDao.updateCategoria(obCatUp);
				doGet(request, response);
				break;
            case "EliminarCategoria":
				int idCatElim = Integer.parseInt(request.getParameter("cod"));
				catDao.deleteCategoria(idCatElim);
				doGet(request, response);
				break;
			default:
				Exception e;
				break;
        }
	}

}
