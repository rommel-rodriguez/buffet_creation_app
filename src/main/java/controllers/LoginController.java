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
		// session.setAttribute("token", authToken);	
		// Trying another token persistence with header 
		String authHeader = String.format("Bearer %s", authToken);

//        response.setHeader("Authorization", authToken);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Vary", "Authorization");
        response.setHeader("Authorization", authHeader);
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin


        // TODO: We got a problem here. The url does not change to / or /index.jsp
        // it stays at /login. Better do a post back to login or something
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//        response.sendRedirect(request.getContextPath());
		return;
	}

}
