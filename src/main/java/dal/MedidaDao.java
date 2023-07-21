/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dal;

import models.entities.Medida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MedidaDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Medida listarId(int cod) {
        Medida med = new Medida();
        String sql = "select * from  where idMedida=" + cod;
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                med.setCod(rs.getInt(1));
                med.setNom(rs.getString(2));
                med.setPref(rs.getString(3));
                
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
        return med;
    }
    
    public List listar() {
        String sql = "select * from medida";
        List<Medida> lista = new ArrayList<>();
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medida med = new Medida();
                med.setCod(rs.getInt(1));
                med.setNom(rs.getString(2));
                med.setPref(rs.getString(3));
                lista.add(med);
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
        return lista;
    }

}
