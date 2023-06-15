package controllers;
import java.io.IOException;

import java.sql.Date;
import java.util.List;
import java.sql.*;

import dal.CategoriaDAO;
import dal.ComidaDao;
import dal.InsumoDao;
import dal.MedidaDao;
import dal.RecetaDao;
import dal.UsuarioDAO;
import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.entities.*;

@WebServlet(name="Controlador", urlPatterns = "/Controlador")
public class Controlador extends HttpServlet {
    Categoria cat = new Categoria();
	Conexion conFactory = new Conexion();
//	Connection con = conFactory.getDBConnection();
//	CategoriaDAOI catDao = new CategoriaDAO(con); 
    Comida com = new Comida();
//    ComidaDao comdao = new ComidaDao();
    Medida medida = new Medida();
    MedidaDao medao = new MedidaDao();
    Insumo insumo = new Insumo();
    InsumoDao insdao = new InsumoDao();
    Receta receta = new Receta();
//    RecetaDao rectdao = new RecetaDao();
    Usuario usuario = new Usuario();
//    UsuarioDAO userdao = new UsuarioDAO();
    static final double igv = 0.18;
    String numComprobante;
    int codProveedor;
    Date fechaCompra;
    double precioTransporte;
    int item;
    int cod;
    String numSerie;
    String descripcion;
    double precio;
    int cant;
    int medidaProducto;
    int marca;
    int categoria;
    int comprobante;
    double precioCompra;
    double precioVenta;
    double subtotal;
    double igvCompra;
    double subtotalCompra;
    double totalPagar;
    String flag;
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
