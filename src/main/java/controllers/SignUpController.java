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
import utils.configuration.LoginMicroserviceConfiguration;
import utils.tools.AppPath;

/**
 * Servlet implementation class CategoriaController
 */
//@WebServlet(name = "CategoriaController", urlPatterns = {"/categorias"})
@WebServlet(
		name = "SignUpController",
		value = {"/signup", "/signup/"})
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String signupView = new AppPath()
			.convertToView("public/login/signup.jsp");
	String signupSuccessView = new AppPath()
			.convertToView("public/login/signup_success.jsp");
	String signupFailureView = new AppPath()
			.convertToView("public/login/signup.jsp");
	
	private LoginMicroserviceConfiguration loginConfig =
			new LoginMicroserviceConfiguration();
	
	private LoginDaoI loginDao;
       
    public SignUpController() {
        super();
        this.loginDao = new LoginDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        Connection con = (new Conexion()).getDBConnection();
		System.out.println("Hitting this endpoint" + request.getContextPath());
		System.out.printf("This is signupView's Path: %s\n", signupView);
		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
//		request.getRequestDispatcher(signupView).forward(request, response);
		getServletContext().getRequestDispatcher(signupView).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();

		String email = request.getParameter("emailUsuario");
		String nombre = request.getParameter("nombreUsuario");
		String first_password = request.getParameter("clave1");
		String second_password = request.getParameter("clave2");
		Usuario createdUser = new Usuario();
		
		
		System.out.printf(
				"[INFO] User trying to register:\n%s %s %s %s\n",
				email,
				nombre,
				first_password,
				second_password
				);
		
		if (first_password.isEmpty() || second_password.isEmpty()) {
			// send message to logging error dispatch and return
			
		} else if (! first_password.equals(second_password)) {
			// send message to logging error dispatch and return
		getServletContext().getRequestDispatcher(signupSuccessView).forward(request, response);
			
		}
		// Do I need a LoginDao here?
		createdUser.setEmail(email);
		createdUser.setNombreUsuario(nombre);
		createdUser.setClave(first_password);
		Usuario returnedUser = loginDao.createUser(createdUser);
		
		if (returnedUser!= null) {
			getServletContext().getRequestDispatcher(signupSuccessView).forward(request, response);
		}
		

//        switch (accion) {
//			case "AgregarComida" :
//				String nomComida = request.getParameter("txtComida");
//				Comida objCat = new Comida(); 
//				objCat.setNom(nomComida);
//				
//				catDao.createComida(objCat);
//				doGet(request, response);
//				break;
//			case "EditarComida":
//				int idCatEdit = Integer.parseInt(request.getParameter("cod"));
//				Comida catEditar = catDao.showComida(idCatEdit);
//				request.setAttribute("comida", catEditar);
//				doGet(request, response);
//				break;
//            case "ActualizarComida":
//				String comidaUp = request.getParameter("txtComida");
//				int codCatUp = Integer.parseInt(request.getParameter("txtCod"));
//				Comida obCatUp = new Comida();
//				obCatUp.setCod(codCatUp);
//				obCatUp.setNom(comidaUp);
//				catDao.updateComida(obCatUp);
//				doGet(request, response);
//				break;
//            case "EliminarComida":
//				int idCatElim = Integer.parseInt(request.getParameter("cod"));
//				catDao.deleteComida(idCatElim);
//				doGet(request, response);
//				break;
//			default:
//				Exception e;
//				break;
//        }

	}

}
