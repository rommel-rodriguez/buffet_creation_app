
package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static Connection con;
    //AMBIENTE DES
    // String url = "jdbc:mysql://localhost:3306/ollitape";
    // TODO: Make this environment dependent
    String url = "jdbc:mysql://db:3306/ollitape";
    String user = "root";
    // String user = "devuser";
    // String pass = "12345";
    String pass = "thisisnotsafe";

    
    //AMBIENTE PRD
//    String url = "";
//    String user = "";
//    String pass = "";

    public Connection Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
