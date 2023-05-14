package dal;
import models.entities.Receta;
import models.interfaces.ollitaPeCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class RecetaDao implements ollitaPeCRUD{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Receta listarId(int cod) {
        Receta receta = new Receta();
        String sql = "select * from cabreceta where idcabreceta=" + cod;
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                receta.setIdReceta(rs.getInt(1));
                receta.setNombre(rs.getString(2));
                receta.setIdUsuario(rs.getInt(3));
                receta.setTipoComida(rs.getString(4));
                receta.setFoto(rs.getString(5));
                receta.setLinkReceta(rs.getString(6));
                receta.setEstado(rs.getString(7)); 
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
        return receta;
    }

    @Override
    public List listar() {
        String sql = "SELECT cabreceta.idCabReceta, cabreceta.nombre, usuario.nombreUsuario, tipocomida.nombre, cabreceta.foto, cabreceta.link, cabreceta.estado FROM cabreceta INNER JOIN usuario ON cabreceta.idUsuario = usuario.idUsuario INNER JOIN tipocomida ON cabreceta.tipo = tipocomida.idTipoComida ORDER BY cabreceta.idCabReceta";
        List<Receta> lista = new ArrayList<>();
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Receta receta = new Receta();
                receta.setIdReceta(rs.getInt(1));
                receta.setNombre(rs.getString(2));
                receta.setUsuario(rs.getString(3));
                receta.setTipoComida(rs.getString(4));
                receta.setFoto(rs.getString(5));
                receta.setLinkReceta(rs.getString(6));
                receta.setEstado(rs.getString(7));                
                lista.add(receta);
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

    @Override
    public int agregar(Object[] o) {
        String sql = "insert into cabreceta(nombre, idUsuario, tipo, foto, link, estado)values(?,?,?,?,?,?)";
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
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
        String sql = "update insumo set nombre=?, idUsuario=?, tipo=?, foto=?, link=?, estado=? where idcabreceta=?";
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            ps.setObject(7, o[6]);
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
        String sql = "delete from cabreceta where idCabReceta=" + cod;
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
