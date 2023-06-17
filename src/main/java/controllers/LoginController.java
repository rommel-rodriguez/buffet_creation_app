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
import models.entities.Usuario;
// import models.entities.Usuario;
import dal.Conexion;
import dal.LoginDaoI;
import dal.LoginDao;
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
	private LoginDaoI loginDao;
	String loginView = new AppPath()
			.convertToView("public/login/login.jsp");
	String loginFailureView = new AppPath()
			.convertToView("public/login/error.jsp");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        this.loginDao = new LoginDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//        Connection con = (new Conexion()).getDBConnection();
		System.out.println("Hitting this endpoint" + request.getContextPath());
//		request.setAttribute("login", listaComidas);
		getServletContext().getRequestDispatcher(loginView).forward(request, response);
	}

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		String authToken = null;
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("clave");
		Usuario loginUser = new Usuario();
		loginUser.setEmail(email);
		loginUser.setClave(password);
		
		
		System.out.printf(
				"[INFO] User trying to Login:\n%s %s\n",
				email,
				password
				);
		
		if (password.isEmpty()) {
			// send message to logging error dispatch and return
			request.setAttribute("errorType", "Login Error");
			request.setAttribute("errorMessage", "The password field is Empty");
			getServletContext().getRequestDispatcher(loginFailureView).forward(request, response);
			return;
			
		}		// Do I need a LoginDao here?
		
		authToken = loginDao.loginUser(loginUser);
		
		if (authToken == null) {
			request.setAttribute("errorType", "Login Error");
			request.setAttribute("errorMessage", "Null authToken Returned");
			getServletContext().getRequestDispatcher(loginFailureView).forward(request, response);
			return;
		}
		session.setAttribute("token", authToken);	
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

		return;
	}

}
