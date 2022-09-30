package io.swagger.api.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBManager {
	
	public static final String DB_URL= "jdbc:mariadb://cl1-sql12.phpnet.org/durnal8";
	public static Logger logger = Logger.getLogger(DBManager.class);
	
	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("coucou");
		}
	}
	
	private static Connection connect;
	
	private static Connection createConnection() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	
	public static Connection getConnection() {
		logger.info("****** USER DIR: "+System.getProperty("user.dir"));
		if (connect == null) createConnection();
		return connect;
	}
}
