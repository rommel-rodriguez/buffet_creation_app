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
import models.entities.Insumo;
import models.entities.Usuario;
// import models.entities.Usuario;
import dal.InsumoDAOI;
import dal.InsumoDao;
import dal.MedidaDao;
import dal.CategoriaDAO;
import dal.CategoriaDAOI;
import dal.Conexion;
import utils.tools.AppPath;
import utils.tools.SessionTool;

/**
 * Servlet implementation class InsumoController
 */
//@WebServlet(name = "InsumoController", urlPatterns = {"/insumos"})
@WebServlet(name = "InsumoController", value = {"/insumos", "/insumos/"})
public class InsumoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String insumosView = new AppPath()
			.convertToView("administration/Insumos.jsp");
	String errorView = new AppPath()
			.convertToView("administration/error.jsp");
	SessionTool sessTool = new SessionTool();
       
    public InsumoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Usuario user = sessTool.getAuthUser(request);

		if (!sessTool.isAnAdministrator(sessTool.getAuthUser(request), request, response, errorView))
			return;

		System.out.println("Hitting this endpoint");
		Conexion conFactory = new Conexion();
		Connection con = conFactory.getDBConnection();
		InsumoDAOI insDao = new InsumoDao(con); 
		// New connection for this other dao, else the connection is closed in
		// the dao before it.
		// CategoriaDAOI catDao = new CategoriaDAO(); 
		CategoriaDAOI catDao = new CategoriaDAO(conFactory.getDBConnection()); 
		List<Insumo> listaInsumos = insDao.listInsumos();
		List<Categoria> listaCategorias = catDao.listCategorias();
		// TODO: Change medida DAO later.
		List listaMedidas = new MedidaDao().listar(); // <-- TODO: Change this!
		request.setAttribute("insumos", listaInsumos);
		request.setAttribute("medidas", listaMedidas);
		request.setAttribute("categorias", listaCategorias);
//		request.getRequestDispatcher("Vista/Mantenimiento/Insumos.jsp").forward(request, response);
		System.out.printf("This is insumosView's Path: %s\n", insumosView);
		System.out.printf("This is the Request context Path: %s\n", request.getContextPath());
		System.out.printf("This is the context Path: %s\n", getServletContext().getContextPath());
//		request.getRequestDispatcher(insumosView).forward(request, response);
		getServletContext().getRequestDispatcher(insumosView).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!sessTool.isAnAdministrator(sessTool.getAuthUser(request), request, response, errorView))
			return;

		Conexion conFactory = new Conexion();
        Connection con;
		con = conFactory.getDBConnection();
		InsumoDAOI insDao = new InsumoDao(con); 
		String accion = request.getParameter("accion");

        switch (accion) {
			case "Agregar" :
				System.out.println("[DEBUG] Testing agregar button failing");
				String nom = request.getParameter("txtnom");
				int categ = Integer.parseInt(request.getParameter("cboCategoria"));
				int med = Integer.parseInt(request.getParameter("cboMedida"));
				double prec = Double.parseDouble(request.getParameter("txtprecio"));
				if ( !(prec >= 0)) {
					doGet(request, response);
					return;
				}
				Insumo ins = new Insumo(); 
				ins.setNom(nom);
				ins.setCodCat(categ);
				ins.setPrecio(prec);
				ins.setCodMed(med);
				insDao.createInsumo(ins);
				doGet(request, response);
				break;
			case "EditarInsumo":
				int idInsEdit = Integer.parseInt(request.getParameter("cod"));
				Insumo insEditar = insDao.showInsumo(idInsEdit);
				request.setAttribute("insumo", insEditar);
				doGet(request, response);
				break;
            case "Actualizar":
				String nomUp = request.getParameter("txtnom");
				int categUp = Integer.parseInt(request.getParameter("cboCategoria"));
				int medUp = Integer.parseInt(request.getParameter("cboMedida"));
				double precUp = Double.parseDouble(request.getParameter("txtprecio"));
				String codUp = request.getParameter("txtCod");
				Insumo insUp = new Insumo(); 
				insUp.setNom(nomUp);
				insUp.setCodCat(categUp);
				insUp.setCodMed(medUp);
				insUp.setPrecio(precUp);
				insUp.setCod(Integer.parseInt(codUp));
				insDao.updateInsumo(insUp);
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println( e);        
				}
            	System.err.println("[INFO] Updating Insumo" + insUp.toString());
				doGet(request, response);
				break;
            case "EliminarInsumo":
            	
				int idProElim = Integer.parseInt(request.getParameter("cod"));
				insDao.deleteInsumo(idProElim);
				doGet(request, response);

				break;
			default:
				Exception e;
				break;
        }
        try {
        	con.close();
        } catch (SQLException e) {
            System.err.println( e);        
        }
	}

}
