/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dal;

import models.entities.Categoria;
import models.interfaces.ollitaPeCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO implements CategoriaDAOI{

//    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public CategoriaDAO (Connection con) {
    	this.con = con;
    }
    
    public Categoria showCategoria(int id) {
        Categoria cat = new Categoria();
        // TODO: Give proper/secure form to this query string
        String sql = "select * from categoria where idCategoria=" + id;
        try {
            // con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cat.setCod(rs.getInt(1));
                cat.setNom(rs.getString(2));
            }
        } catch (SQLException e) {
            System.err.println( e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println( ex);
                }
            }
        }
        return cat;
    }
   @Override
    public List<Categoria> listCategorias() {
        String sql = "select * from categoria order by idCategoria";
        List<Categoria> lista = new ArrayList<>();
        try {
            // con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setCod(rs.getInt(1));
                cat.setNom(rs.getString(2));
                lista.add(cat);
            }
        } catch (SQLException e) {
        	System.err.println("[ERROR] Problem withe the database");
            System.err.println(e);        
        } catch (NullPointerException e) {
        	System.err.println("[ERROR] The Conexion method returns null");
            System.err.println(e);        
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println( ex);    
                }
            }
        }
        return lista;
    }

    @Override
    public void createCategoria(Categoria categoria) {
        String sql = "insert into categoria(nombre)values(?)";
        try {
            // con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            //ps.setObject(1, o[0]);
            ps.setString(1, categoria.getNom());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println( ex);    
                }
            }
        }
        return;
    }

    @Override
    public void updateCategoria(Categoria categoria) {
        String sql = "update categoria set nombre=? where idCategoria=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNom());
            ps.setInt(2, categoria.getCod());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
        return;
    }

    @Override
    public void deleteCategoria(int cod) {
    	// TODO: Instead of catching the exception, I think its better to let
    	// it propagate upstream and let the controller handle it in the same
    	// way it handles the databse connection.
        String sql = "delete from categoria where idCategoria=" + cod;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }
	@Override
	public void storeCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		
	}

}
