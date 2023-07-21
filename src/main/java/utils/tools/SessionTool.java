package utils.tools;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
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
import dal.Conexion;
import dal.LoginDaoI;
import dal.LoginDao;
import utils.tools.AppPath;
import models.entities.Usuario;
import utils.configuration.LoginMicroserviceConfiguration;

public class SessionTool {
	private LoginDaoI loginDao;
	
	public SessionTool () {
        this.loginDao = new LoginDao();
	}
	
	public Usuario authenticate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String authToken = (String) session.getAttribute("token");
		Usuario user = null;
		if (authToken == null) {
			System.err.println("[ERROR] Auth Token is Null");
			return null;
		}

		user = loginDao.retrieveUser(authToken);

		if (user == null) {
			System.err.println("[ERROR] Auth User is Null");
			return null;
		}
		
		return user;
	}

	public Usuario authenticateToken(HttpServletRequest request) {

		String authHeader = request.getHeader("Authorization");
		String authToken = null;
		Usuario user = null;

		// TODO This might raise an exception in some cases
		if (authHeader != null) {
			String headerParts[] = authHeader.split(" ");
			if (headerParts.length != 2) {
				System.err.println("[ERROR] Auth Token has to have 'Token '  at the start");
				return null;
			}
			authToken = headerParts[1];
		}

			
		if (authToken == null) {
			System.err.println("[ERROR] Auth Token is Null");
			return null;
		}

		user = loginDao.retrieveUser(authToken);

		if (user == null) {
			System.err.println("[ERROR] Auth User is Null");
			return null;
		}
		
		return user;
	}
	
	
	public Usuario getAuthUser(HttpServletRequest request) {

		Usuario user = null;

		user = authenticate(request);

		return user;
	}

	public Usuario getAuthUserWithHeader(HttpServletRequest request) {

		Usuario user = null;

		user = authenticateToken(request);

		return user;
	}

	public boolean isAnAdministrator (
			Usuario user,
			HttpServletRequest request,
			HttpServletResponse response,
			String errorView) {
		
		if ( (user == null) || !user.getTipoUsuario().equals("Administrador")) {
			request.setAttribute("errorType", "Sitio Restringido");
			request.setAttribute(
					"errorMessage",
					"Lo sentimos, esta pagina esta reservada solo para personal");
			try {
				request.getServletContext().getRequestDispatcher(errorView).forward(request, response);
				return false;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				System.out.println("[ERROR] Error while dispatching to Error View");
				return false;
			}
		}
		request.setAttribute("authUser", user);// Maybe better to set a Hashmap
		// or static class here
		return true;
	}

	public String getToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String authToken = (String) session.getAttribute("token");
		if (authToken == null) {
			System.err.println("[ERROR] Auth Token is Null");
			return null;
		}
		
		return authToken;
	}

}
