
package Modelo.Dao;

import Configuracion.Conexion;
import Modelo.Entidad.Menu;
import Modelo.Interface.ollitaPeCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDao implements ollitaPeCRUD {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

public Menu listarId(int cod) {
        Menu ins = new Menu();
        String sql = "select * from menu where idMenu=" + cod;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ins.setIdMenu(rs.getInt(1));
                ins.setIdDia(rs.getInt(2));
                ins.setIdAlmSegundo(rs.getInt(3));
                ins.setPreAlmSegundo(rs.getDouble(4));
                ins.setTotAlmSegundo(rs.getDouble(5));                
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
        return ins;
    }
      
    
    
    @Override
    public List listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int agregar(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int cod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
