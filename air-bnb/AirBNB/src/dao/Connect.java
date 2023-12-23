package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
	
	private static Connection instance;
	
	private Connect() {
		
	}
	
	public Connection getInstance() throws ClassNotFoundException, SQLException {
		
		if (instance == null) {
			
			instance = getConnection();
			
			return instance;
		}
		
		return instance;
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Connection con = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "databaseURL";
		
		con = DriverManager.getConnection(url, "Username", "Password");
		
		return con;
		
	}
	
}
