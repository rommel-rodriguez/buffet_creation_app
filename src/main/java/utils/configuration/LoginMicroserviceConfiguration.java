package utils.configuration;

public class LoginMicroserviceConfiguration {
	private  static String SERVICE_HOSTNAME = "LOGIN_HOST";
	private static String SERVICE_PORT = "LOGIN_PORT";
	
	public String getHostName() {
		return System.getenv(SERVICE_HOSTNAME);
	}

	public int getPort() {
		int port = 0;
		try {
			port = Integer.parseInt(System.getenv(SERVICE_PORT));
		} catch (Exception e){
			System.err.println(e);
		}
		return port;
	}

}
