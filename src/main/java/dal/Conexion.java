
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.configuration.DBConfiguration;

public class Conexion {
    private static Connection con;
    //AMBIENTE DES
    // String url = "jdbc:mysql://localhost:3306/ollitape";
    // TODO: Make this environment dependent
    DBConfiguration dbConfig = new DBConfiguration();
    String url = "jdbc:mysql://db:3306/ollitape";
    String user = "root";
    // String user = "devuser";
    // String pass = "12345";
    String pass = "thisisnotsafe";
    String driverClassName = "com.mysql.jdbc.Driver";

    
    //AMBIENTE PRD
//    String url = "";
//    String user = "";
//    String pass = "";

    public Connection getDBConnection() {
		String dbhost = dbConfig.getHostName();
		int dbport = dbConfig.getPort();
		String dbname = dbConfig.getName();
		String dbUser = dbConfig.getUser();
		String dbPass = dbConfig.getPass();
		// TODO: Add port test, if port == 0, raise error
		String uri = generateDBUri(dbhost, dbport, dbname);
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(url, user, pass);
            con = DriverManager.getConnection(uri, dbUser, dbPass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(String.format("[ERROR] DB URI: %s", uri));
        }
        return con;
    }
    
    private String generateDBUri(
    		String dbhost,
    		int dbport,
    		String dbname) {

    	String format = "jdbc:mysql://%s:%d/%s";
    	String uri = String.format(format, dbhost, dbport, dbname);
    	return uri; 
    	
    }
}
