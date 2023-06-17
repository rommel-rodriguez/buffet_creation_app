package dal;

import java.util.List;

import models.entities.Usuario;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.entities.Usuario;
import utils.configuration.LoginMicroserviceConfiguration;

public class UsuarioMSDao implements UsuarioDAOI {

	private HttpClient httpClient;
	String baseLoginUri;
	static String createPath = "api/user/users/"; //POST
	static String readPath = "api/user/users/"; //GET
	static String updatePath = "api/user/users/"; // Needs identifier in path
	static String deletePath = "api/user/users/"; // Needs identifier in path
	static String findPath = "api/user/me/"; // Needs identifier in path
	private LoginMicroserviceConfiguration loginConfig =
			new LoginMicroserviceConfiguration();

	@Override
	public void createUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> listUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario showUsuario(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUsuario(int id) {
		// TODO Auto-generated method stub
		
	}

	private String generateLoginServiceBaseUri (String actionPath) {
		String loginHost = loginConfig.getHostName();
//		String loginHost = "localhost";
		int loginPort = loginConfig.getPort();
    	String format = "http://%s:%d/%s";
    	String uri = String.format(format, loginHost, loginPort, actionPath);
    	return uri; 
		
	}
}
