package dal;
import models.entities.Receta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class RecetaDao implements RecetaDAOI{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public RecetaDao(Connection con) {
    	this.con = con;
    }
    
    public Receta showReceta(int cod) {
        Receta receta = new Receta();
        String sql = "select * from cabreceta where idcabreceta=" + cod;
        try {
//            con = cn.getDBConnection();
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
        }
        return receta;
    }

    @Override
    public List<Receta> listRecetas() {
        String sql = 
			"SELECT \r\n"
			+ "    cabreceta.idCabReceta,\r\n"
			+ "    cabreceta.nombre,\r\n"
			+ "    usuario.nombreUsuario,\r\n"
			+ "    tipocomida.nombre,\r\n"
			+ "    cabreceta.foto,\r\n"
			+ "    cabreceta.link,\r\n"
			+ "    cabreceta.estado\r\n"
			+ "    FROM cabreceta\r\n"
			+ "    LEFT OUTER JOIN usuario ON cabreceta.idUsuario = usuario.idUsuario\r\n"
			+ "    INNER JOIN tipocomida ON cabreceta.tipo = tipocomida.idTipoComida\r\n"
			+ "    ORDER BY cabreceta.idCabReceta";
        List<Receta> lista = new ArrayList<>();
        try {
//            con = cn.getDBConnection();
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
        }

        return lista;
    }

    @Override
    public void createReceta(Receta rec) {
        String sql = "insert into cabreceta(nombre, idUsuario, tipo, foto, link, estado)values(?,?,?,?,?,?)";
        System.err.println("Inside RecetaDao createReceta");
        System.err.println("Receta to be created: " + rec.toString());
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, rec.getNombre());
            ps.setObject(2, rec.getIdUsuario());
            ps.setObject(3, rec.getIdTipoComida());
            ps.setObject(4, rec.getFoto());
            ps.setObject(5, rec.getLinkReceta());
            ps.setObject(6, rec.getEstado());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

    @Override
    public void updateReceta(Receta rec) {
        String sql = "update cabreceta set nombre=?, idUsuario=?, tipo=?, foto=?, link=?, estado=? where idcabreceta=?";
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, rec.getNombre());
            ps.setObject(2, rec.getIdUsuario());
            ps.setObject(3, rec.getIdTipoComida());
            ps.setObject(4, rec.getFoto());
            ps.setObject(5, rec.getLinkReceta());
            ps.setObject(6, rec.getEstado());
            ps.setObject(7, rec.getIdReceta());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

    @Override
    public void deleteReceta(int cod) {
        String sql = "delete from cabreceta where idCabReceta=" + cod;
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

	@Override
	public void storeReceta(Receta receta) {
		// TODO Auto-generated method stub
		
	}
    
}
