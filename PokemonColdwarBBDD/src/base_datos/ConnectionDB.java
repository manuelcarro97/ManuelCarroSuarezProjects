package base_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

	private static final String USER = "root";
	private static final String PWD = "";
	private static final String URL = "jdbc:mysql://localhost:3306/coldwar";
	//	openConnection

	public Connection openConnection() {

//			System.out.println("Connecting database...");

			Connection con = null;
			// intentamos la conexion a la base de datos
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(URL, USER, PWD);
//				System.out.println("Database connected!");

			} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database! ", e);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// devolvemos el valor de la conexion
			return con;
		}



	//	closeConnection
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
