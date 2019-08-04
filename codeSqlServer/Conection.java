package conecta;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conection {

	private static Connection cn;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cn = DriverManager.getConnection("jdbc:sqlserver://181.231.30.180;databaseName=CINEDB","sa","f3d3fr4nc0");
		} catch (Exception e) {
			cn = null;
		}
		return cn;
	}
	public static void main(String[] args) throws SQLException {
		Connection pruebaCn = Conection.getConnection();
		if(pruebaCn != null) {
			System.out.println("Conectado");
			System.out.println( pruebaCn.getCatalog());
			Statement stm = pruebaCn.createStatement();
			ResultSet rst = stm.executeQuery("select * from dbo.Cliente");
			while(rst.next()) {
				System.out.println("nombre: " + rst.getString(2));
			}
		}
		else {
			System.out.println("Desconectado");
		}
	}
}
