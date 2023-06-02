package dal;
import java.util.*;
import models.entities.Usuario;

public interface UsuarioDAOI {
	
	
    void createUsuario(Usuario usuario);

    void storeUsuario(Usuario usuario);

    List<Usuario> listUsuarios();

    Usuario showUsuario(int id);

    void updateUsuario(Usuario usuario);

    void deleteUsuario(int id);

}
