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
import models.entities.Usuario;
// import models.entities.Usuario;
import dal.UsuarioDAOI;
import dal.UsuarioDAO;
import dal.Conexion;
import utils.tools.AppPath;

/**
 * Servlet implementation class UsuarioController
 */
//@WebServlet(name = "UsuarioController", urlPatterns = {"/usuarios"})
@WebServlet(name = "UsuarioController", value = {"/usuarios", "/usuarios/"})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String usuariosView = new AppPath()
			.convertToView("administration/Usuarios.jsp");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Surround all Connection related code with appropriate try-catch
		// blocks.
		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		UsuarioDAOI catDao = new UsuarioDAO(con); 
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Hitting this endpoint");
		List<Usuario> listaUsuarios = catDao.listUsuarios();
		request.setAttribute("usuarios", listaUsuarios);
//		request.getRequestDispatcher("Vista/Mantenimiento/Usuarios.jsp").forward(request, response);
		System.out.printf("This is usuariosView's Path: %s\n", usuariosView);
		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
//		request.getRequestDispatcher(usuariosView).forward(request, response);
		getServletContext().getRequestDispatcher(usuariosView).forward(request, response);
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
		UsuarioDAOI catDao = new UsuarioDAO(con); 
		String accion = request.getParameter("accion");
//		System.out.printf(
//				"Usuarios PostMethod: accion=%s\n",
//				accion
//				);

//
        switch (accion) {
			case "AgregarUsuario" :
				String nomUsuario = request.getParameter("txtUsuario");
				Usuario objCat = new Usuario(); 
				objCat.setNombreUsuario(nomUsuario);
				
				catDao.createUsuario(objCat);
//				flag = "CAT";
//				request.setAttribute("flag", flag);
//				request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
//				request.getRequestDispatcher(usuariosView).forward(request, response);
				doGet(request, response);
				break;
			case "EditarUsuario":
				int idCatEdit = Integer.parseInt(request.getParameter("cod"));
				Usuario catEditar = catDao.showUsuario(idCatEdit);
				request.setAttribute("usuario", catEditar);
//				request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
				doGet(request, response);
				break;
            case "ActualizarUsuario":
				String usuarioUp = request.getParameter("txtUsuario");
				int codCatUp = Integer.parseInt(request.getParameter("txtCod"));
//				Object[] obCatUp = new Object[2];
				Usuario obUsuarioUp = new Usuario();
				obUsuarioUp.setIdUsuario(codCatUp);
				obUsuarioUp.setNombreUsuario(usuarioUp);
//				obCatUp[0] = usuarioUp;
//				obCatUp[1] = codCatUp;
//				catDao.(obCatUp);
				catDao.updateUsuario(obUsuarioUp);
				doGet(request, response);
//				request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
				break;
			default:
				Exception e;
				break;
        }
	}

}