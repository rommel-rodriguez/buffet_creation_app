package dal;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.entities.Usuario;
import utils.configuration.LoginMicroserviceConfiguration;

public class LoginDao implements LoginDaoI {

	private HttpClient httpClient;
	String baseLoginUri;
	static String createPath = "api/user/create/";
	static String tokenPath = "api/user/token/";
	static String selfRetrievePath = "api/user/me/";
	static String selfUpdatePath = "api/user/me/";

	private LoginMicroserviceConfiguration loginConfig =
			new LoginMicroserviceConfiguration();

	public LoginDao () {
        this.httpClient = HttpClient.newHttpClient();
        this.baseLoginUri = generateLoginServiceBaseUri("");
	}
	@Override
	public Usuario createUser(Usuario user) {

		String path = generateLoginServiceBaseUri(createPath);
		
		Usuario createdUser = new Usuario();
		
		System.out.println("[INFO] Inside LoginDao.createUser");
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

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(postBody))
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

            if (statusCode == 201) {
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
                return createdUser;

            } else {
                System.out.println("Request failed with status code: " + statusCode);
                System.out.println("Response's body:\n" + responseBody);
            }
        } catch (Exception e) {
            System.out.println("Request failed: " + e);
            System.out.println("Request failed: " + e.getMessage());
            System.out.println("Status Code: " + statusCode);
        }
		return null;
	}

	@Override
	public String loginUser(Usuario user) {
		return null;
	}

	@Override
	public Usuario updateUser(Usuario user) {
		return null;
	}

	@Override
	public Usuario retrieveUser(Usuario user) {
		return null;
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
