package dal;

import models.entities.Comida;
import models.interfaces.ollitaPeCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ComidaDao implements ComidaDAOI{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public ComidaDao(Connection con) {
    	this.con = con;
    }

    public Comida showComida(int cod) {
        Comida com = new Comida();
        String sql = "select * from  tipocomida where idTipoComida=" + cod;
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                com.setCod(rs.getInt(1));
                com.setNom(rs.getString(2));
            }
        } catch (SQLException e) {
            System.err.println( e);
        }
        return com;
    }

   @Override
    public List<Comida> listComidas() {
        String sql = "select * from  tipocomida order by idTipoComida";
        List<Comida> lista = new ArrayList<>();
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comida cat = new Comida();
                cat.setCod(rs.getInt(1));
                cat.setNom(rs.getString(2));
                lista.add(cat);
            }
        } catch (SQLException e) {
            System.err.println( e);        
        }

        return lista;
    }

    @Override
    public void createComida(Comida com) {
        String sql = "insert into tipocomida(nombre)values(?)";
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, com.getNom());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

    @Override
    public void updateComida(Comida com) {
        String sql = "update tipocomida set nombre=? where idTipoComida=?";
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, com.getNom());
            ps.setObject(2, com.getCod());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

    @Override
    public void deleteComida(int cod) {
        String sql = "delete from tipocomida where idTipoComida=" + cod;
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

	@Override
	public void storeComida(Comida categoria) {
		// TODO Auto-generated method stub
		
	}
}
