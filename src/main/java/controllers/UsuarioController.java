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
import dal.UsuarioMicroDao;
import dal.UsuarioDAO;
import dal.Conexion;
import utils.tools.AppPath;
import utils.tools.SessionTool;

/**
 * Servlet implementation class UsuarioController
 */
//@WebServlet(name = "UsuarioController", urlPatterns = {"/usuarios"})
@WebServlet(name = "UsuarioController", value = {"/usuarios", "/usuarios/"})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String usuariosView = new AppPath()
			.convertToView("administration/Usuarios.jsp");
	String errorView = new AppPath()
			.convertToView("administration/error.jsp");
	SessionTool sessTool = new SessionTool();
       
    public UsuarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Surround all Connection related code with appropriate try-catch
		// blocks.

		Usuario user = sessTool.getAuthUser(request);

		if (!sessTool.isAnAdministrator(user, request, response, errorView))
			return;

		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
//		UsuarioDAOI catDao = new UsuarioDAO(con); 
		UsuarioDAOI lmDao = new UsuarioMicroDao(); // Login microservice DAO
		System.out.println("Hitting this endpoint");
		List<Usuario> listaUsuarios = lmDao.listUsuarios();
		request.setAttribute("usuarios", listaUsuarios);
		System.out.printf("This is usuariosView's Path: %s\n", usuariosView);
		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
		getServletContext().getRequestDispatcher(usuariosView).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Surroung all Connection related code with appropriate try-catch
		// blocks.

		Usuario user = sessTool.getAuthUser(request);

		if (!sessTool.isAnAdministrator(user, request, response, errorView))
			return;

		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		UsuarioDAOI userDao = new UsuarioDAO(con); 
		String accion = request.getParameter("accion");

        switch (accion) {
			case "Agregar" :
				String nomUsuario = request.getParameter("txtnom");
				String foto= request.getParameter("txtfoto");
				String clave = request.getParameter("txtclave");
				String tipoUsuario = request.getParameter("cboTipoUsuario");
				String estado = request.getParameter("cboEstado");
				Usuario objCat = new Usuario(); 
				objCat.setNombreUsuario(nomUsuario);
				objCat.setClave(clave);
				objCat.setFoto(foto);
				objCat.setTipoUsuario(tipoUsuario);
				objCat.setEstado(estado);
				
				userDao.createUsuario(objCat);
				doGet(request, response);
				break;
			case "EditarUsuario":
				int idCatEdit = Integer.parseInt(request.getParameter("cod"));
				Usuario catEditar = userDao.showUsuario(idCatEdit);
				request.setAttribute("usuario", catEditar);

				doGet(request, response);
				break;
            case "Actualizar":
            	
				String usuarioUp = request.getParameter("txtnom");
				int codCatUp = Integer.parseInt(request.getParameter("txtCod"));
				String fotoUp= request.getParameter("txtfoto");
				String claveUp = request.getParameter("txtclave");
				String tipoUsuarioUp = request.getParameter("cboTipoUsuario");
				String estadoUp = request.getParameter("cboEstado");

				Usuario obUsuarioUp = new Usuario();

				obUsuarioUp.setIdUsuario(codCatUp);
				obUsuarioUp.setNombreUsuario(usuarioUp);
				obUsuarioUp.setClave(claveUp);
				obUsuarioUp.setFoto(fotoUp);
				obUsuarioUp.setTipoUsuario(tipoUsuarioUp);
				obUsuarioUp.setEstado(estadoUp);

				userDao.updateUsuario(obUsuarioUp);

				doGet(request, response);
				break;
            case "EliminarUsuario":
				int idUserElim = Integer.parseInt(request.getParameter("cod"));

				userDao.deleteUsuario(idUserElim);

				doGet(request, response);
				break;
			default:
				Exception e;
				break;
        }
	}

}
