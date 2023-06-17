package dal;

import java.util.Map;
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
		
//		Map<String, ?> userData = new HashMap<>();
		
		System.out.println("[INFO] Inside LoginDao.createUser");
		System.out.println("[INFO] Usuario: " + user);
		System.out.println("[INFO] Create Path: " + createPath);
		System.out.println("[INFO] Full Create Path: " + path);
		
		if (user.getNombreUsuario().isBlank())
			user.setNombreUsuario("Anonymous");

		ObjectMapper objectMapper = new ObjectMapper();

//		JsonNode payload = objectMapper.createObjectNode()
//				.put("email", user.getEmail())
//				.put("password", user.getClave())
//				.put("name", user.getNombreUsuario())
//				.put("user_type", "Cliente")
//				.put("user_state", "R");
//		
//		String postBody = payload.toString();

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
                return null;
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

			return createdUser;

        } catch (Exception e) {
            System.out.println("Request failed: " + e);
            System.out.println("Request failed: " + e.getMessage());
            System.out.println("Status Code: " + statusCode);
        }

		return null;
	}

	@Override
	public String loginUser(Usuario user) {
		String token = null;
		String path = generateLoginServiceBaseUri(tokenPath);
		
//		Map<String, ?> userData = new HashMap<>();
		
		System.out.println("[INFO] Inside LoginDao.loginUser");
		System.out.println("[INFO] Token Path: " + tokenPath);
		System.out.println("[INFO] Full Create Path: " + path);
		
//		if (user.getNombreUsuario() == null ||  user.getNombreUsuario().isBlank())
//			user.setNombreUsuario("Anonymous");

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode payload = objectMapper.createObjectNode()
				.put("email", user.getEmail())
				.put("password", user.getClave());
		
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

            if (statusCode != 200) {
                System.err.println("Request failed with status code: " + statusCode);
                System.err.println("Response's body:\n" + responseBody);
                return null;
            }

			responseBody = response.body();
			
			if (! responseBody.contains("token")) {
                System.err.println(
                		"[ERROR] Failed Retrieving the token"
						+ statusCode);
                System.err.println("[ERROR] Response's body:\n" + responseBody);
				return null;
			}

			System.out.println("[INFO] Response's body:\n" + responseBody);

			// Parse the JSON response using Jackson
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			System.out.println("[INFO] JsonNode:\n" + jsonNode);
			// Access the token value
			token = jsonNode.get("token").asText();
            System.out.println("[INFO] Successfully acquiered token: " + token);

			return token;
        } catch (Exception e) {
            System.out.println("Request failed: " + e);
            System.out.println("Request failed: " + e.getMessage());
            System.out.println("Status Code: " + statusCode);
        }

		return null;
	}

	@Override
	public Usuario updateUser(Usuario user) {
		return null;
	}

	@Override
	public Usuario retrieveUser(String token) {

		String path = generateLoginServiceBaseUri(selfRetrievePath);
    	String tokenLine = String.format("Token %s", token);
    	Usuario retrievedUser = new Usuario();
		
		System.out.println("[INFO] Inside LoginDao.retrieveUser");
		System.out.println("[INFO] Token Path: " + tokenPath);
		System.out.println("[INFO] Full Create Path: " + path);
		
		ObjectMapper objectMapper = new ObjectMapper();
        
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(path))
				.header("accept", "application/json")
				.header("Authorization", tokenLine)
				.build();


        String responseBody = null;
		int statusCode = 0; 
        try {
            HttpResponse<String> response = httpClient
            		.send(
						request,
						HttpResponse.BodyHandlers.ofString()
					);
            statusCode = response.statusCode();

            if (statusCode != 200) {
                System.err.println("Request failed with status code: " + statusCode);
                System.err.println("Response's body:\n" + responseBody);
                return null;
            }

			responseBody = response.body();
			
			if (responseBody == null) {
                System.err.println(
                		"[ERROR] Failed Retrieving the User's Data"
						+ statusCode);
                System.err.println("[ERROR] Response's body is null");
				return null;
			}

			System.out.println("[INFO] Response's body:\n" + responseBody);

			// Parse the JSON response using Jackson
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			System.out.println("[INFO] JsonNode:\n" + jsonNode);
			// Access the token value
			retrievedUser.setIdUsuario(jsonNode.get("id").asInt()) ;
			retrievedUser.setEmail(jsonNode.get("email").asText()) ;
//			retrievedUser.setNombreUsuario(jsonNode.get("name").asText()) ;
			retrievedUser.setNombreUsuario(jsonNode.get("email").asText()) ;
			retrievedUser.setTipoUsuario(jsonNode.get("user_type").asText()) ;
			retrievedUser.setEstado(jsonNode.get("user_state").asText()) ;
			retrievedUser.setFoto(".") ;
            System.out.println(
            		"[INFO] Successfully Retrieved User:\n" +
					retrievedUser);

			return retrievedUser;

        } catch (Exception e) {
            System.out.println("Request failed: " + e);
            System.out.println("Request failed: " + e.getMessage());
            System.out.println("Status Code: " + statusCode);
        }

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
	
//	private String jsonPayloadFromMap(Map map) throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonResult = mapper.writerWithDefaultPrettyPrinter()
//		  .writeValueAsString(map);	
//		return jsonResult;
//	}
	
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
