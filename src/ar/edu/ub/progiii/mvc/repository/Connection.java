package ar.edu.ub.progiii.mvc.repository;

import java.sql.DriverManager;
/**

 * Esta clase realiza una conexion con la base de datos

 * @author: Federico Franco , Diego Moran
 */
public  class Connection {

    public static java.sql.Connection connection;
    
    /**
     * Metodo estatico que realiza la conexion con la base de datos,  de no realizarse devuelve null
     * @return connection
     */
    public static java.sql.Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://181.239.145.84;databaseName=CINEDB","sa","f3d3fr4nc0");
        } catch (Exception e) {
            connection = null;
        }
        return connection;
    }
}
