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
		name = "LogoutController",
		value = {"/logout", "/logout/"})
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String loginView = new AppPath()
			.convertToView("public/login/login.jsp");
	String loginFailureView = new AppPath()
			.convertToView("public/login/error.jsp");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hitting this endpoint" + request.getContextPath());
        HttpSession session = request.getSession(false); // Don't create a new session if it doesn't exist

        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

		getServletContext().getRequestDispatcher(loginView).forward(request, response);
	}

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		doGet(request, response);

	}

}
