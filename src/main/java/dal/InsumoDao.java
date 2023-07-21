package dal;

import models.entities.Insumo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * 
 */
// public class InsumoDao implements ollitaPeCRUD{

public class InsumoDao implements InsumoDAOI{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public InsumoDao () {
    	
    }
    public InsumoDao (Connection con) {
    	this.con = con;
    }

    public Insumo showInsumo(int cod) {
        Insumo ins = new Insumo();
        String sql = "select * from insumo where idInsumo=" + cod;
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ins.setCod(rs.getInt(1));
                ins.setNom(rs.getString(2));
                ins.setCodCat(rs.getInt(3));
                ins.setCodMed(rs.getInt(4));
                ins.setPrecio(rs.getDouble(5));
            }
        } catch (SQLException e) {
            System.err.println( e);
        }
        return ins;
    }
    
    @Override
    public List<Insumo> listInsumos () {
        String sql =
          "SELECT idInsumo, nombreInsumo,categoria.nombre, medida.nombre, precio\n"
        + "FROM insumo\n"
        + "INNER JOIN categoria ON insumo.idCategoria = categoria.idCategoria\n"
        + "INNER JOIN medida ON insumo.idMedida = medida.idmedida\n"
        + "ORDER BY idInsumo";

        List<Insumo> lista = new ArrayList<>();
        try {
//            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Insumo ins = new Insumo();
                ins.setCod(rs.getInt(1));
                ins.setNom(rs.getString(2));
                ins.setCategoria(rs.getString(3));
                ins.setMedida(rs.getString(4));
                ins.setPrecio(rs.getDouble(5));
                lista.add(ins);
            }
        } catch (SQLException e) {
            System.err.println( e);        
        }

        return lista;
    }

    @Override
    public void createInsumo(Insumo o) {
        String sql = "insert into insumo(nombreInsumo, idCategoria, idMedida, precio)values(?,?,?,?)";
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o.getNom());
            ps.setObject(2, o.getCodCat());
            ps.setObject(3, o.getCodMed());
            ps.setObject(4, o.getPrecio());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

    @Override
    public void updateInsumo (Insumo ins) {
        // String sql = "update insumo set nombreInsumo=?, idCategoria=?, idMedida=?, precio=? where idInsumo=?";
        String sql = "update insumo set nombreInsumo=?, idCategoria=?, idMedida=?, precio=? where idInsumo=?";
        String nom = ins.getNom();
		int cat = ins.getCodCat();
		int med =  ins.getCodMed();
		double pre = ins.getPrecio();
		int cod = ins.getCod();

		if (cat == 0 || med == 0)
			return;
		

        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nom);
            ps.setObject(2, cat);
            ps.setObject(3, med);
            ps.setObject(4, pre);
            ps.setObject(5, cod);

            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

    @Override
    public void deleteInsumo(int cod) {
        String sql = "delete from insumo where idInsumo=" + cod;
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

	@Override
	public void storeInsumo(Insumo insumo) {
		// TODO Auto-generated method stub
		
	}
}