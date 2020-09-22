package app.repository.daoDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DataSourceDao {

	private static DataSourceDao instancia = null;
	private static Connection connection = null;

	private DataSourceDao() {

	}

	public static DataSourceDao getInstance(){
		if(instancia == null) {
			instancia = new DataSourceDao();
		}
		return instancia;
	}

	public Connection connect() {
		try {
			DriverManager.registerDriver(new Driver());
			connection = DriverManager.getConnection("jdbc:mysql://localhost/proyecto_patrones?serverTimezone=America/Lima", "root", "1234");
		} catch(SQLException e){
			System.out.println("Error to connect DB: " + e.getMessage());
		}
		return connection;
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error to disconnect DB: "  + e.getMessage());
		}
	}

}
