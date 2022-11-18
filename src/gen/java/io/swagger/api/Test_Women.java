package io.swagger.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.swagger.api.impl.DBManager;
import io.swagger.model.Person;

public class Test_Women {
	public static void getLocation(Connection connect) {
			
		String i = "Dorothy";
		String strSql = "SELECT * FROM `Women` WHERE name LIKE ?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, i + "%");
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				String loc = resultSet.getString("callret-4").substring(28);
				loc = loc.replace("-", " ");
				loc = loc.replace("_", " ");
				System.out.printf("Name : %s\n"
								+ "Birthdate : %s\n"
								+ "DeathDate : %s\n"
								+ "Location : %s",resultSet.getString(2), resultSet.getString(3),
														resultSet.getString(4),loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	 
	
}
