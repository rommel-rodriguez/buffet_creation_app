package utils.configuration;

public class DBConfiguration {
	private String ENVVAR_HOSTNAME = "DB_HOST";
	private String ENVVAR_DBNAME = "DB_NAME";
	private String ENVVAR_DBPORT = "DB_PORT";
	private String ENVVAR_DBUSER = "DB_USER";
	private String ENVVAR_DBPASSWORD = "DB_PASS";
	
	public String getHostName() {
		return System.getenv(ENVVAR_HOSTNAME);
	}
	public String getName() {
		return System.getenv(ENVVAR_DBNAME);
	}
	public String getPort() {
		return System.getenv(ENVVAR_DBPORT);
	}
	public String getUser() {
		return System.getenv(ENVVAR_DBUSER);
	}
	public String getPass() {
		return System.getenv(ENVVAR_DBPASSWORD);
	}

}
