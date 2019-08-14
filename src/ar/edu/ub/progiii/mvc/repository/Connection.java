package ar.edu.ub.progiii.mvc.repository;

import java.sql.DriverManager;

public  class Connection {

    public static java.sql.Connection connection;

    public static java.sql.Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://181.231.30.180;databaseName=CINEDB","sa","f3d3fr4nc0");
        } catch (Exception e) {
            connection = null;
        }
        return connection;
    }
}
