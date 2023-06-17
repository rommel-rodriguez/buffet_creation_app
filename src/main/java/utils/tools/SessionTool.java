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

}
