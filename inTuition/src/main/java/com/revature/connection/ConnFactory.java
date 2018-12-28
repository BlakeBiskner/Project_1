package com.revature.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Singleton Connection Factory (pumps out connections)
 */
public class ConnFactory {
	private static ConnFactory cf = new ConnFactory();

	private ConnFactory() {}

	public static synchronized ConnFactory getInstance() {
		if (cf == null) {
			cf = new ConnFactory();
		}
		return cf;
	}

	public Connection getConnection() {
		Connection conn = null;
		// Don't hardcode url, username, and password (BAD)
		Properties prop = new Properties();
		
		try{
			// Create Connection using properties file
			prop.load(new FileReader("database.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("username"),
											   prop.getProperty("password") );
			conn.setAutoCommit(false);
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
}
