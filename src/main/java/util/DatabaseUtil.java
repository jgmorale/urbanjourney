package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
public static final String URL_FORMAT = "jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	public static Connection getConnection() {
		String serverHost = " https://node49470-env-6096167.jl.serv.net.mx";
		String serverPort = "3306";
		String databaseName = "urbanjourney";
		String databaseUser ="root";
		String databasePass = "TBVgkf19261";
		Connection conn = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = String.format(URL_FORMAT, serverHost, serverPort, databaseName);
			conn = DriverManager.getConnection(url, databaseUser, databasePass);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo algo al realizar la conexion");
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("La conexion no se cerro correctamente");
		}
	}
	
}
