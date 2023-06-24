package dal;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.entities.Usuario;
import utils.configuration.LoginMicroserviceConfiguration;

public class UsuarioMicroDao implements UsuarioDAOI {
	private LoginMicroserviceConfiguration loginConfig =
			new LoginMicroserviceConfiguration();

	// TODO: All of these path should be saved together in 
	// some other place.
	String baseLoginUri;
	static String listPath = "api/user/users/";
	static String createPath = listPath; 
	static String updatePath = listPath; 
	static String deletePath = listPath;
	static String findByIdPath = listPath;

	private HttpClient httpClient;
	
	
	public UsuarioMicroDao () {
        this.httpClient = HttpClient.newHttpClient();
        this.baseLoginUri = generateLoginServiceBaseUri("");
	}

	@Override
	public void createUsuario(Usuario user) {

		String path = generateLoginServiceBaseUri(createPath);
		
		Usuario createdUser = new Usuario();
		
		System.out.println("[INFO] Inside Usuario.createUser");
		System.out.println("[INFO] Usuario: " + user);
		System.out.println("[INFO] Create Path: " + createPath);
		System.out.println("[INFO] Full Create Path: " + path);
		
		if (user.getNombreUsuario().isBlank())
			user.setNombreUsuario("Anonymous");

		ObjectMapper objectMapper = new ObjectMapper();


		JsonNode payload = objectMapper.createObjectNode()
				.put("email", user.getEmail())
				.put("password", user.getClave())
				.put("name", user.getNombreUsuario())
				.put("user_type", "Cliente")
				.put("user_state", "R");
		
		String postBody = payload.toString();


        HttpRequest request = generateJsonPayloadRequest(postBody, "post", path);
        
        String responseBody = null;
		int statusCode = 0; 
        try {
            HttpResponse<String> response =
            		httpClient
            		.send(
						request,
						HttpResponse.BodyHandlers.ofString()
					);
            statusCode = response.statusCode();

            if (statusCode != 201) {
                System.out.println("Request failed with status code: " + statusCode);
                System.out.println("Response's body:\n" + responseBody);
                // TODO: Here I should raise an exception for the controller to
                // handle.
                return;
            }

			responseBody = response.body();

			System.out.println("[INFO] Response's body:\n" + responseBody);

			// Parse the JSON response using Jackson
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			System.out.println("[INFO] JsonNode:\n" + jsonNode);
			// Access the token value
			int id = jsonNode.get("id").asInt();
			String email = jsonNode.get("email").asText();
			String name = jsonNode.get("name").asText();
			String tipoUsuario = jsonNode.get("user_type").asText();
			String estado = jsonNode.get("user_state").asText();

			createdUser.setIdUsuario(id);
			createdUser.setEmail(email);
			createdUser.setNombreUsuario(name);
			createdUser.setTipoUsuario(tipoUsuario);
			createdUser.setEstado(estado);

			// TODO: Raise exception here and add throws to function.
			return;

        } catch (Exception e) {
            System.out.println("Request failed: " + e);
            System.out.println("Request failed: " + e.getMessage());
            System.out.println("Status Code: " + statusCode);
        }

		// TODO: Raise exception here and add throws to function.
		return;

	}

	@Override
	public void storeUsuario(Usuario usuario) {

	}

	@Override
	public List<Usuario> listUsuarios() {

		String path = generateLoginServiceBaseUri(createPath);
		List<Usuario> usuarios = new ArrayList<>();
		
		
		System.out.println("[INFO] Inside UsuarioMicroDao.listUsuarios");
		System.out.println("[INFO] Create Path: " + createPath);
		System.out.println("[INFO] Full Create Path: " + path);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
        
		// NOTE: Do I need my token here?
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(path))
				.header("accept", "application/json")
				.build();

        String responseBody = null;
		int statusCode = 0; 
        try {
            HttpResponse<String> response =
            		httpClient
            		.send(
						request,
						HttpResponse.BodyHandlers.ofString()
					);
            statusCode = response.statusCode();

            if (statusCode != 200) {
                System.out.println("Request failed with status code: " + statusCode);
                System.out.println("Response's body:\n" + responseBody);
                // TODO: Here I should raise an exception for the controller to
                // handle.
                return null;
            }

			responseBody = response.body();

			System.out.println("[INFO] Response's body:\n" + responseBody);

			// Parse the JSON response using Jackson
			JsonNode jsonNode = objectMapper.readTree(responseBody);
			
			jsonNode.forEach( (userNode) -> {
				Usuario currentUser = new Usuario();
				int id = userNode.get("id").asInt();
				String email = userNode.get("email").asText();
				String name = userNode.get("name").asText();
				String tipoUsuario = userNode.get("user_type").asText();
				String estado = userNode.get("user_state").asText();
				currentUser.setIdUsuario(id);
				currentUser.setEmail(email);
//				currentUser.setNombreUsuario(name);
				currentUser.setNombreUsuario(email);
				currentUser.setTipoUsuario(tipoUsuario);
				currentUser.setEstado(estado);
				// Add the current user to the usuarios list
				usuarios.add(currentUser);
			});

			// TODO: Raise exception here and add throws to function.
			return usuarios;

        } catch (Exception e) {
            System.out.println("Request failed: " + e);
            System.out.println("Request failed: " + e.getMessage());
            System.out.println("Status Code: " + statusCode);
        }

		// TODO: Raise exception here and add throws to function.
		return null;
	}

	@Override
	public Usuario showUsuario(int id) {
		return null;
	}

	@Override
	public void updateUsuario(Usuario usuario) {

	}

	@Override
	public void deleteUsuario(int id) {

	}

	private String generateLoginServiceBaseUri (String actionPath) {
		String loginHost = loginConfig.getHostName();
		int loginPort = loginConfig.getPort();
    	String format = "http://%s:%d/%s";
    	String uri = String.format(format, loginHost, loginPort, actionPath);
    	return uri; 
		
	}

	private HttpRequest generateJsonPayloadRequest (
			String jsonBody,
			String method,
			String path) {

			Builder builder = HttpRequest.newBuilder()
					.uri(URI.create(path))
					.header("accept", "application/json")
					.header("Content-Type", "application/json");

             HttpRequest.BodyPublisher bodyPublisher =
            		 HttpRequest.BodyPublishers.ofString(jsonBody);
			switch (method) {
				case "post": 
					builder.POST(bodyPublisher);
					break;
				case "put": 
					builder.PUT(bodyPublisher);
					break;
				default:
					return null;
			}
			
			return builder.build();
	}

}
