package dal;

import models.*;
import models.entities.Usuario;

public interface LoginDaoI {
	
	Usuario createUser(Usuario user);
	String loginUser(Usuario user); // This should return a token or raise an Exc
	Usuario updateUser(Usuario user);
	Usuario retrieveUser(Usuario user);

}
