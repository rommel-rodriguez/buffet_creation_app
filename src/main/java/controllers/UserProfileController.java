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
import dal.LoginDao;
import dal.LoginDaoI;
import utils.tools.AppPath;
import utils.tools.SessionTool;

/**
 * Servlet implementation class UsuarioController
 */
//@WebServlet(name = "UsuarioController", urlPatterns = {"/usuarios"})
@WebServlet(name = "UserProfileController", value = {"/profile", "/me"})
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String profileView = new AppPath()
			.convertToView("user/profile.jsp");
	String errorView = new AppPath()
			.convertToView("user/error.jsp");
	SessionTool sessTool = new SessionTool();
       
    public UserProfileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Surround all Connection related code with appropriate try-catch
		// blocks.

		Usuario user = sessTool.getAuthUser(request);
		
		if (user == null)
			getServletContext().getRequestDispatcher(errorView).forward(request, response);

		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();

		System.out.println("Hitting this endpoint: UserProfile");

		request.setAttribute("usuario", user);

		System.out.printf("This is usuariosView's Path: %s\n", profileView);
		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
		getServletContext().getRequestDispatcher(profileView).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Surroung all Connection related code with appropriate try-catch
		// blocks.

		Usuario user = sessTool.getAuthUser(request);

		if (user == null)
			getServletContext().getRequestDispatcher(errorView).forward(request, response);

		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		UsuarioDAOI userDao = new UsuarioDAO(con); 
		UsuarioDAOI lmDao = new UsuarioMicroDao(); // Login microservice DAO

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
				objCat.setEmail(nomUsuario);
				objCat.setClave(clave);
				objCat.setFoto(foto);
				objCat.setTipoUsuario(tipoUsuario);
				objCat.setEstado(estado);
				
				lmDao.createUsuario(objCat);
//				obCat.setClave(" ");
				userDao.createUsuario(objCat);
				doGet(request, response);
				break;
			case "EditarUsuario":
				int idCatEdit = Integer.parseInt(request.getParameter("cod"));
//				Usuario catEditar = userDao.showUsuario(idCatEdit);
				Usuario catEditar = lmDao.showUsuario(idCatEdit);
				request.setAttribute("usuario", catEditar);

				doGet(request, response);
				break;
            case "Actualizar":
            	
				String nombreUp = request.getParameter("txtnom");
				int codCatUp = Integer.parseInt(request.getParameter("txtCod"));
				String fotoUp= request.getParameter("txtfoto");
				String claveUp = request.getParameter("txtclave");
				String tipoUsuarioUp = request.getParameter("cboTipoUsuario");
				String estadoUp = request.getParameter("cboEstado");

				Usuario obUsuarioUp = new Usuario();

				obUsuarioUp.setIdUsuario(codCatUp);
				obUsuarioUp.setNombreUsuario(nombreUp);
				obUsuarioUp.setEmail(nombreUp);
				obUsuarioUp.setClave(claveUp);
				obUsuarioUp.setFoto(fotoUp);
				obUsuarioUp.setTipoUsuario(tipoUsuarioUp);
				obUsuarioUp.setEstado(estadoUp);

				lmDao.updateUsuario(obUsuarioUp);
//				obUsuarioUp.setClave(" ");
				userDao.updateUsuario(obUsuarioUp);

				doGet(request, response);
				break;
            case "EliminarUsuario":
				int idUserElim = Integer.parseInt(request.getParameter("cod"));

				userDao.deleteUsuario(idUserElim);
				lmDao.deleteUsuario(idUserElim);

				doGet(request, response);
				break;
			default:
				Exception e;
				break;
        }
	}

}
