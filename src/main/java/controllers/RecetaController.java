package controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
// import dal.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.entities.Comida;
import models.entities.Receta;
// import models.entities.Usuario;
import dal.RecetaDAOI;
import dal.RecetaDao;
import dal.ComidaDao;
import dal.ComidaDAOI;
import dal.Conexion;
import utils.tools.AppPath;
import utils.tools.SessionTool;

/**
 * Servlet implementation class RecetaController
 */
//@WebServlet(name = "RecetaController", urlPatterns = {"/recetas"})
@WebServlet(name = "RecetaController", value = {"/recetas", "/recetas/"})
public class RecetaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String recetasView = new AppPath()
			.convertToView("administration/Receta.jsp");
	String errorView = new AppPath()
			.convertToView("administration/error.jsp");
	SessionTool sessTool = new SessionTool();
       
    public RecetaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!sessTool.isAnAdministrator(sessTool.getAuthUser(request), request, response, errorView))
			return;

		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		RecetaDAOI recDao = new RecetaDao(con); 
//		ComidaDAOI comDao = new ComidaDao(con); 
		ComidaDAOI comDao = new ComidaDao(conFactory.getDBConnection()); 
		System.out.println("Hitting this endpoint");
		List<Receta> listaRecetas = recDao.listRecetas();
		List<Comida> listaComidas = comDao.listComidas();
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("recetas", listaRecetas);
		request.setAttribute("comidas", listaComidas);
		System.out.printf("This is recetasView's Path: %s\n", recetasView);
		System.out.printf("This is recetasView's recetas: %s\n", listaRecetas);
		System.out.printf("This is recetasView's comidas: %s\n", listaComidas);
		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
		getServletContext().getRequestDispatcher(recetasView).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!sessTool.isAnAdministrator(sessTool.getAuthUser(request), request, response, errorView))
			return;

		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		RecetaDAOI recDao = new RecetaDao(con); 
		String accion = request.getParameter("accion");

		Receta rec = new Receta();
        switch (accion) {
			case "Agregar" :
				System.out.println("[DEBUG] Testing [RECETA] agregar button failing");
				String nom = request.getParameter("txtnom");
				int usuario = Integer.parseInt(request.getParameter("txtuser"));
				int comida = Integer.parseInt(request.getParameter("cboComidas"));
				String foto = request.getParameter("txtfoto");
				String link = request.getParameter("txtlink");
				String estado = request.getParameter("cboEstado");

				rec.setNombre(nom);
				rec.setIdUsuario(usuario);
				rec.setIdTipoComida(comida);
				rec.setFoto(foto);
				rec.setLinkReceta(link);
				rec.setEstado(estado);

				recDao.createReceta(rec);

				doGet(request, response);
				break;
			case "Editar":
				
				int idRectEdit = Integer.parseInt(request.getParameter("cod"));
				Receta RectEditar = recDao.showReceta(idRectEdit);
				request.setAttribute("receta", RectEditar);
				
				doGet(request, response);
				break;
            case "Actualizar":
            	
				String nomup = request.getParameter("txtnom");
				int comidaup = Integer.parseInt(request.getParameter("cboComidas"));
				String fotoup = request.getParameter("txtfoto");
				String linkup = request.getParameter("txtlink");
				String estadoup = request.getParameter("cboEstado");
				int codUp =  Integer.parseInt( request.getParameter("txtCod"));
				int idUserUp =  Integer.parseInt( request.getParameter("txtuser"));
            	
				rec.setNombre(nomup);
				rec.setIdUsuario(idUserUp);
				rec.setIdTipoComida(comidaup);
				rec.setFoto(fotoup);
				rec.setLinkReceta(linkup);
				rec.setEstado(estadoup);
				rec.setIdReceta(codUp);

				recDao.updateReceta(rec);
            	
				doGet(request, response);
				break;
            case "AgregarIngredientes":
            	System.err.println("I need to implement this");
            	break;
            case "Eliminar":
				int idRecElim = Integer.parseInt(request.getParameter("cod"));
				recDao.deleteReceta(idRecElim);
				doGet(request, response);
				break;
			default:
				Exception e;
				break;
        }
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				System.err.println( ex);    
			}
		}
	}

}
