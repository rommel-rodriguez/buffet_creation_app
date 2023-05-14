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


public class CategoriaDao implements ollitaPeCRUD{

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Categoria listarId(int cod) {
        Categoria cat = new Categoria();
        String sql = "select * from categoria where idCategoria=" + cod;
        try {
            con = cn.getDBConnection();
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
    public List listar() {
        String sql = "select * from categoria order by idCategoria";
        List<Categoria> lista = new ArrayList<>();
        try {
            con = cn.getDBConnection();
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
    public int agregar(Object[] o) {
        String sql = "insert into categoria(nombre)values(?)";
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
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
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        String sql = "update categoria set nombre=? where idCategoria=?";
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
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
        return r;
    }

    @Override
    public void eliminar(int cod) {
        String sql = "delete from categoria where idCategoria=" + cod;
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
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
    }

}
