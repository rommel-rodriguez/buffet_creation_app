package dal;
import models.entities.Usuario;
import models.interfaces.ollitaPeCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDAO implements UsuarioDAOI{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public UsuarioDAO (Connection con) {
    	this.con = con;
    }
    
    
    public Usuario showUsuario(int cod) {
        Usuario usuario = new Usuario();
        String sql = "select * from usuario where idUsuario=" + cod;
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setClave(rs.getString(3));
                usuario.setFoto(rs.getString(4));
                usuario.setTipoUsuario(rs.getString(5));
                usuario.setEstado(rs.getString(6));
            }
        } catch (SQLException e) {
            System.err.println( e);
        }
        return usuario;
    }

    @Override
    public List<Usuario> listUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> lista = new ArrayList<>();
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setClave(rs.getString(3));
                usuario.setFoto(rs.getString(4));
                usuario.setTipoUsuario(rs.getString(5)); 
                usuario.setEstado(rs.getString(6));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println( e);        
        }
        return lista;
    }

    @Override
    public void createUsuario(Usuario usuario) {
        String sql = "insert into usuario(nombreUsuario, clave, foto, tipoUsuario, estado)values(?,?,?,?,?)";
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, usuario.getNombreUsuario());
            ps.setObject(2, usuario.getClave()); // NOTE: Should no longer do anything
            ps.setObject(3, usuario.getFoto());
            ps.setObject(4, usuario.getTipoUsuario());
            ps.setObject(5, usuario.getEstado());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        } 
    }

    @Override
    public void updateUsuario (Usuario usuario) {
        String sql = "update usuario set nombreUsuario=?, clave=?, foto=?, tipoUsuario=?, estado=? where idUsuario=?";
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, usuario.getNombreUsuario());
            ps.setObject(2, usuario.getClave());
            ps.setObject(3, usuario.getFoto());
            ps.setObject(4, usuario.getTipoUsuario());
            ps.setObject(5, usuario.getEstado());
            ps.setObject(6, usuario.getIdUsuario());

            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        }
    }

    @Override
    public void deleteUsuario (int cod) {
        String sql = "delete from usuario where idusuario=" + cod;
        try {
            con = cn.getDBConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e);        
        } 
    }

	@Override
	public void storeUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}
}