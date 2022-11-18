package io.swagger.api.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.ArrayUtils;

import io.swagger.model.Artefact;
import io.swagger.model.Person;
import io.swagger.model.Tag;

public class Utilitaire {
	// Méthodes communes 
	public static Date stringToDate(String date) {
		SimpleDateFormat simpleFormat;
		if (date.length() == 10) {
			simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		} else {
			simpleFormat = new SimpleDateFormat("yyyy");
		}
		try {
			return simpleFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Artefact[] putInOrder(Artefact[] tableau) {
		int count;
		Date d = new Date();
		Date da = new Date();
		Map<Integer,Artefact> order = new HashMap<>();
		for (int i = 0; i < tableau.length; i++) {
			count = 0;
			//System.out.println("element à comparer : "+tableau[i]);
			for (int j = 0; j < tableau.length; j++) {
				d = stringToDate(tableau[i].getCreationdate());
				da = stringToDate(tableau[j].getCreationdate());
				if (d.after(da)) {
					count++;
				}
			}
			order.put(count, tableau[i]);
		}
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = order.get(i);
		}
		return tableau;
	}
	
	public static Artefact[] filterByTags(List<String> tags, Artefact[] tabArt) { //, Person[] tabPers) {
		
		tabArt = new Artefact[ArtefactApiServiceImpl.tab.length];
		if (tags.size() != 0) {
		    int t = 0;
			for (int i = 0; i < ArtefactApiServiceImpl.tab.length; i++) {
				if (tags.get(0).equals(ArtefactApiServiceImpl.tab[i].getTags().get(0).getId())) {
					tabArt[t] = ArtefactApiServiceImpl.tab[i];
					t++;
				}
			}
			if (tabArt.length == ArtefactApiServiceImpl.tab.length) {
				for (int i = t; i < ArtefactApiServiceImpl.tab.length; i++) {
					tabArt = ArrayUtils.remove(tabArt, t);
				}
			}
			return tabArt;
		} else {
			return ArtefactApiServiceImpl.tab;
		}
		//System.out.println("taille après suppresion null : "+selection.length);
		//return tabArt;
//		System.out.println("Tableau des artefacts : "+tabArt
//							+" tableau des personnes : "+tabPers);
	}
	
	public static Artefact[] filterByDate(String startDate, String endDate, Artefact[] selection) { //, Person[] tabPers) {
		Artefact[] finalSelection = new Artefact[selection.length];
		int ts = 0;
		Date d_1 = new Date();
		Date d_2 = new Date();
		Date d = new Date();
		
		d_1 = stringToDate(startDate);
		d_2 = stringToDate(endDate);
		for (int i = 0; i < selection.length; i++) {
			d = stringToDate(selection[i].getCreationdate());
			if (d.after(d_1) && d.before(d_2)) {
				finalSelection[ts] = selection[i];
				ts++;
			}
		}
		if (finalSelection.length == selection.length) {
			for (int i = ts; i < selection.length; i++) {
				finalSelection = ArrayUtils.remove(finalSelection, ts);
			}
		}
		return finalSelection;
	}
	
// -----------------------------------------------------------------------//

	// Méthodes non utilisées
	public static void writeArtefacts(File file, Artefact[] tab) {
		try (
		        BufferedWriter bw = new BufferedWriter(new FileWriter(file))
		    ) {
		        for (int l = 0; l < tab.length; l++) {
		        	String element = tab[l].toString();
		        	bw.write(String.valueOf(element));
	                bw.newLine();bw.newLine();
	                bw.write("----------------------------------------------"
	                		+ "---------------------------------------------");
	                bw.newLine();bw.newLine();
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
// -------------------------------------------------------------------------
	
	// Méthodes SQL
	public static List<Artefact> returnArtAllTable(Connection connect) {
		// 1- Affiche la table GENERAL
		List<Artefact> list = new ArrayList<>();
		String all = "SELECT * FROM GENERAL";
		//System.out.println(connection);
		try (Statement statement = connect.createStatement();
				ResultSet resultSet = statement.executeQuery(all)) {
			while (resultSet.next()) {
				/*
				int rsId = resultSet.getInt(1);
				String rsAnnee = resultSet.getString(2);
				String rsSociété = resultSet.getString(3);
				String rsName = resultSet.getString(4);
				String rsLocalisation = resultSet.getString(5);
				String rsType = resultSet.getString(7);
				
				System.out.printf("%d: %s - %s - %s - %s - %s\n", rsId,
						rsName, rsAnnee, rsSociété, rsLocalisation, rsType);
				*/
				Artefact art = createArtefact(resultSet);
				list.add(art);
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Person> returnPersAllTable(Connection connect) {
		// 1- Affiche la table GENERAL
		List<Person> list = new ArrayList<>();
		String all = "SELECT * FROM GENERAL";
		//System.out.println(connection);
		try (Statement statement = connect.createStatement();
				ResultSet resultSet = statement.executeQuery(all)) {
			while (resultSet.next()) {
				Person pers = createPerson(resultSet);
				list.add(pers);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Person> filterByDate(Connection connect, String startDate, String endDate) {
		List<Person> list = new ArrayList<>();
		String date = "SELECT * FROM GENERAL WHERE Annee BETWEEN ? AND ? ORDER BY Full_Date";
		try (PreparedStatement pStatement = connect.prepareStatement(date)) {
			// Permet de remplacer les "?" par l'argument donné ici artefactId
			pStatement.setString(1, startDate);
			pStatement.setString(2, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {					
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Person> filterByCompany(Connection connect, List<String> company) {
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Société LIKE ?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, company.get(0));
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while(resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers); 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Person> filterByCompanyAndDate(Connection connect, List<String> company, String startDate, String endDate) {
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Société LIKE ? AND Annee BETWEEN ? AND ?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, company.get(0));
			pStatement.setString(2, startDate);
			pStatement.setString(3, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while(resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void filterByExpo(Connection connect, List<String> expo) {
		
	}
	
	public static void filterByExpoAndDate(Connection connect, List<String> expo, String startDate, String endDate) {
		
	}

	public static void filterByExpoAndCompany(Connection connect, List<String> expo, List<String> company) {
		// TODO Auto-generated method stub
	}

	public static void filterByExpoCompanyAndDate(Connection connect, List<String> expo, List<String> company,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static List<Person> filterByCountry(Connection connect, List<String> country) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Localisation LIKE ?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, country.get(0) + "%");
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Person> filterByCountryAndDate(Connection connect, List<String> country, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Localisation LIKE ? AND Annee BETWEEN ? AND ?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, country.get(0) + "%");
			pStatement.setString(2, startDate);
			pStatement.setString(3, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Person> filterByCountryAndCompany(Connection connect, List<String> country, List<String> company) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Localisation LIKE ? AND Société LIKE ?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, country.get(0) + "%");
			pStatement.setString(2, company.get(0) + "%");
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Person> filterByCountryCompanyAndDate(Connection connect, List<String> country, List<String> company,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Localisation LIKE ? AND "
				+ "Société LIKE ? AND Annee BETWEEN ? AND ?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, country.get(0) + "%");
			pStatement.setString(2, company.get(0) + "%");
			pStatement.setString(3, startDate);
			pStatement.setString(4, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return list;
	}

	public static void filterByCountryAndExpo(Connection connect, List<String> country, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByCountryExpoAndDate(Connection connect, List<String> country, List<String> expo,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByCountryCompanyAndExpo(Connection connect, List<String> country, List<String> company,
			List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByCountryCompanyExpoAndDate(Connection connect, List<String> country, List<String> company,
			List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGender(Connection connect, String gender) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderAndDate(Connection connect, String gender, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderAndCompany(Connection connect, String gender, List<String> company) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCompanyAndDate(Connection connect, String gender, List<String> company,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderAndExpo(Connection connect, String gender, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderExpoAndDate(Connection connect, String gender, List<String> expo, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCompanyAndExpo(Connection connect, String gender, List<String> company,
			List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCompanyExpoAndDate(Connection connect, String gender, List<String> company,
			List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderAndCountry(Connection connect, String gender, List<String> country) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCountryAndDate(Connection connect, String gender, List<String> country,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCompanyAndCountry(Connection connect, String gender, List<String> company,
			List<String> country) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCompanyCountryAndDate(Connection connect, String gender, List<String> company,
			List<String> country, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCountryAndExpo(Connection connect, String gender, List<String> country,
			List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCountryExpoAndDate(Connection connect, String gender, List<String> country,
			List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCompanyCountryAndExpo(Connection connect, String gender, List<String> company,
			List<String> country, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByGenderCompanyCountryExpoAndDate(Connection connect, String gender, List<String> company,
			List<String> country, List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}
	
	public static List<Person> filterByName(Connection connect, String name) {
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Titre LIKE ?";
    	try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, name + "%");
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	public static List<Person> filterByNameAndDate(Connection connect, String name, String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Titre LIKE ? AND Annee BETWEEN ? AND ?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, name + "%");
			pStatement.setString(2, startDate);
			pStatement.setString(3, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Person> filterByNameAndCompany(Connection connect, String name, List<String> company) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Titre LIKE ? AND Société LIKE ?";
    	try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, name + "%");
			pStatement.setString(2, company.get(0) + "%");
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	public static List<Person> filterByNameCompanyAndDate(Connection connect, String name, List<String> company,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Titre LIKE ?"+
						"AND Société LIKE ? AND Annee BETWEEN ? AND ?";
    	try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, name + "%");
			pStatement.setString(2, company.get(0) + "%");
			pStatement.setString(3, startDate);
			pStatement.setString(4, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	public static void filterByNameAndExpo(Connection connect, String name, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameExpoAndDate(Connection connect, String name, List<String> expo, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameExpoAndCompany(Connection connect, String name, List<String> expo,
			List<String> company) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameExpoCompanyAndDate(Connection connect, String name, List<String> expo,
			List<String> company, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static List<Person> filterByNameAndCountry(Connection connect, String name, List<String> country) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Titre LIKE ? AND Localisation LIKE ?";
    	try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, name + "%");
			pStatement.setString(2, country.get(0) + "%");
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	public static List<Person> filterByNameCountryAndDate(Connection connect, String name, List<String> country,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Titre LIKE ? AND Localisation LIKE ?"+
						"AND Annee BETWEEN ? AND ?";
    	try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, name + "%");
			pStatement.setString(2, country.get(0) + "%");
			pStatement.setString(3, startDate);
			pStatement.setString(4, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	public static List<Person> filterByNameCountryAndCompany(Connection connect, String name, List<String> country,
			List<String> company) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Titre LIKE ? AND Localisation LIKE ?"+
						"AND Société LIKE ?";
    	try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, name + "%");
			pStatement.setString(2, country.get(0) + "%");
			pStatement.setString(3, company.get(0) + "%");
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	public static List<Person> filterByNameCountryCompanyAndDate(Connection connect, String name, List<String> country,
			List<String> company, String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<>();
		String strSql = "SELECT * FROM GENERAL WHERE Titre LIKE ? AND Localisation LIKE ?"+
						"AND Société LIKE ?";
    	try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setString(1, name + "%");
			pStatement.setString(2, country.get(0) + "%");
			pStatement.setString(3, company.get(0) + "%");
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Person pers = createPerson(resultSet);
					list.add(pers);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	public static void filterByNameCountryAndExpo(Connection connect, String name, List<String> country,
			List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameCountryExpoAndDate(Connection connect, String name, List<String> country,
			List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameCountryCompanyAndExpo(Connection connect, String name, List<String> country,
			List<String> company, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameCountryCompanyExpoAndDate(Connection connect, String name, List<String> country,
			List<String> company, List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static List<Person> filterByNameAndGender(Connection connect, String name, String gender) {
		// TODO Auto-generated method stub
		if (gender.equals("female")) {
			List<Person> list = new ArrayList<>();
			String strSql = "SELECT * FROM Women WHERE name LIKE ?";
	    	try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
				pStatement.setString(1, "%" + name + "%");
				try (ResultSet resultSet = pStatement.executeQuery()) {
					while (resultSet.next()) {
						Person pers = new Person();
						int spaceIndex = resultSet.getString(2).indexOf(" ");
						pers.setFirstname(resultSet.getString(2).substring(0,spaceIndex));
						pers.setLastname(resultSet.getString(2).substring(spaceIndex + 1));
						pers.setBirthdate(resultSet.getString(3));
						pers.setDeathdate(resultSet.getString(4));
						if (resultSet.getString(5).equals("")) { pers.setCountry("");}
						else {pers.setCountry(resultSet.getString(5).substring(28).replace("-", " ").replace("_", " "));}
						pers.setDescription(resultSet.getString(6));
						list.add(pers);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return list;	
		} else {
			return null;
		}		
	}

	public static void filterByNameGenderAndDate(Connection connect, String name, String gender, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderAndCompany(Connection connect, String name, String gender,
			List<String> company) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCompanyAndDate(Connection connect, String name, String gender,
			List<String> company, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderAndExpo(Connection connect, String name, String gender, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderExpoAndDate(Connection connect, String name, String gender, List<String> expo,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCompanyAndExpo(Connection connect, String name, String gender,
			List<String> company, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCompanyExpoAndDate(Connection connect, String name, String gender,
			List<String> company, List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderAndCountry(Connection connect, String name, String gender,
			List<String> country) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCountryAndDate(Connection connect, String name, String gender,
			List<String> country, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCompanyAndCountry(Connection connect, String name, String gender,
			List<String> company, List<String> country) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCompanyCountryAndDate(Connection connect, String name, String gender,
			List<String> company, List<String> country, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCountryAndExpo(Connection connect, String name, String gender,
			List<String> country, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCountryExpoAndDate(Connection connect, String name, String gender,
			List<String> country, List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCompanyCountryAndExpo(Connection connect, String name, String gender,
			List<String> company, List<String> country, List<String> expo) {
		// TODO Auto-generated method stub
		
	}

	public static void filterByNameGenderCompanyCountryExpoAndDate(Connection connect, String name, String gender,
			List<String> company, List<String> country, List<String> expo, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}
	
	public static Person findPersById(Connection connect, Long personId) {
		String strSql = "SELECT * FROM GENERAL WHERE Id=?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setLong(1, personId);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				Person pers = createPerson(resultSet);
				return pers;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Artefact findArtById(Connection connect, Long artefactId) {
		// TODO Auto-generated method stub
		String strSql = "SELECT * FROM GENERAL WHERE Id=?";
		try (PreparedStatement pStatement = connect.prepareStatement(strSql)) {
			pStatement.setLong(1, artefactId);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				Artefact art = createArtefact(resultSet);
				return art;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Artefact> filterArtByDate(Connection connect, String startDate, String endDate) {
		// TODO Auto-generated method stub
		//3- Affiche les éléments dans une fourchette de dates
		List<Artefact> ListArt = new ArrayList<>();
		String date = "SELECT * FROM GENERAL WHERE Annee BETWEEN ? AND ? ORDER BY Full_Date";
		try (PreparedStatement pStatement = connect.prepareStatement(date)) {
			pStatement.setString(1, startDate);
			pStatement.setString(2, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Artefact art = createArtefact(resultSet);
					ListArt.add(art);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ListArt;
	}

	public static List<Artefact> filterArtByTags(Connection connect, List<String> tags) {
		// TODO Auto-generated method stub
		// 2- Affiche les éléments de GENERAL dont le type est ?
		List<Artefact> list = new ArrayList<>();
		String filtered = "SELECT * FROM GENERAL WHERE Type LIKE ? ORDER BY Full_Date";
		try (PreparedStatement pStatement = connect.prepareStatement(filtered)) {
			pStatement.setString(1, tags.get(0));
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {
					Artefact art = createArtefact(resultSet);
					list.add(art);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Artefact> filterArtByTagsAndDate(Connection connect, List<String> tags, String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<Artefact> list = new ArrayList<>();
		String filtered = "SELECT * FROM GENERAL WHERE Type LIKE ? "+
				"AND Annee BETWEEN ? AND ? ORDER BY Full_Date";
		try (PreparedStatement pStatement = connect.prepareStatement(filtered)) {
			pStatement.setString(1, tags.get(0));
			pStatement.setString(2, startDate);
			pStatement.setString(3, endDate);
			try (ResultSet resultSet = pStatement.executeQuery()) {
				while (resultSet.next()) {					
					Artefact art = createArtefact(resultSet);
					list.add(art);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static Artefact createArtefact(ResultSet resultSet) throws SQLException {
		Artefact art = new Artefact();
		art.setId((long) resultSet.getInt(1));
		art.setName(resultSet.getString(4));
		art.setCountry(resultSet.getString(5));
		art.setCreationdate(resultSet.getString(2));
		art.setCompany(resultSet.getString(3));
		art.setDescription(resultSet.getString(6));
		List<Tag> l = new ArrayList<>();
    	Tag t = new Tag();
    	t.setId(resultSet.getString(7));
    	t.setDescription(legend(t));
    	l.add(t);
    	art.setTags(l);
		
		return art;
	}
	
	public static Person createPerson(ResultSet resultSet) throws SQLException {
		Person pers = new Person();
		pers.setId((long) resultSet.getInt(1));
		pers.setFirstname(resultSet.getString(4));
		pers.setCountry(resultSet.getString(5));
		pers.setCompany(resultSet.getString(3));
		pers.setDescription(resultSet.getString(6));
		
		return pers;
	}
	
	public static String legend(Tag tag) {
		String desc = "";
		if (tag.getId().equals("R")) desc = "Récompense";
		if (tag.getId().equals("H")) desc = "Repère historique";
		if (tag.getId().equals("E")) desc = "Electricité";
		if (tag.getId().equals("Ec")) desc = "Ecriture";
		if (tag.getId().equals("S")) desc = "Stockage";
		if (tag.getId().equals("C")) desc = "Communication";
		if (tag.getId().equals("Ca")) desc = "Calcul";
		if (tag.getId().equals("Ent")) desc = "Entreprise";
		if (tag.getId().equals("IO")) desc = "Input Output";
		if (tag.getId().equals("HW")) desc = "Hardware";
		if (tag.getId().equals("SW")) desc = "Software";
		if (tag.getId().equals("T")) desc = "Théorie";
		if (tag.getId().equals("SOL")) desc = "Fait de société lié à l'informatique";
		if (tag.getId().equals("OS")) desc = "Operating system";
		return desc;
	}
	
// -------------------------------------------------------------------------
	// Anciennes méthodes 
	
	/*
    @Override
    public Response getPersonById(Long personId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
    	if (allPerson.containsKey(personId)) {
        	return Response.ok(allPerson.get(personId)).build();
        } else {
        	ResponseBuilder builder = Response.status(Status.NOT_FOUND);
			builder.type(MediaType.APPLICATION_JSON);
			builder.entity("{ \"msg\" : \""+personId+" not found\" }");
			return builder.build();
        }
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }*/
	
	/*
    @Override
    public Response findPersons( String name, String gender, List<String> country, List<String> expo, List<String> company, String startDate, String endDate, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
    	boolean someone = false;
    	Person wantedPerson = allPerson.get(0L);
    	if (name.equals("all")) {
			return Response.ok(tab).build();
		} else {
			for (Long l = 1L; l < 11L; l++) {
	    		if (allPerson.get(l).getLastname().equals(name)) {
	            	someone = true;
	            	wantedPerson = allPerson.get(l);
	    		}
	    	}
		}
    	if (!someone) {
    		ResponseBuilder builder = Response.status(Status.NOT_FOUND);
			builder.type(MediaType.APPLICATION_JSON);
			builder.entity("{ \"msg\" : \""+name+" not found\" }");
			return builder.build();  
		}
    	//return Response.ok(tab).build();
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
		return Response.ok(wantedPerson).build();
    }
    */
	
	/*
    @Override
    public Response getArtefactById(Long artefactId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!    	
    	if (allData.containsKey(artefactId)) {
        	return Response.ok(allData.get(artefactId)).build();
        } else {
        	ResponseBuilder builder = Response.status(Status.NOT_FOUND);
			builder.type(MediaType.APPLICATION_JSON);
			builder.entity("{ \"msg\" : \""+artefactId+" not found\" }");
			return builder.build();
        }
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    */
	
	/*
	@Override
    public Response findArtefact( @NotNull List<String> tags,  String startDate,  String endDate, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
		if (tags.size() == 0) {
			if (startDate == null && endDate == null) {
				return Response.ok(tab).build();
			} else {
				finalSelection = Utilitaire.filterByDate(startDate, endDate, tab);
				orderedSelection = Utilitaire.putInOrder(finalSelection);
				return Response.ok(orderedSelection).build();
			}
		} if (tags.get(0).equals("Ordered")) {
			 if (startDate == null && endDate == null) {
				 orderedSelection = Utilitaire.putInOrder(tab);
				 return Response.ok(orderedSelection).build();
			 } else {
				 finalSelection = Utilitaire.filterByDate(startDate, endDate, tab);
				 orderedSelection = Utilitaire.putInOrder(finalSelection);
				 return Response.ok(orderedSelection).build();
			 } 
		} else {
			//selectionFilePath = new File("./src/main/webapp/Artefact/selectedArtefacts.txt");
			// -- 1er filtrage
			finalSelection = Utilitaire.filterByTags(tags,selection);
			if (startDate == null && endDate == null) {
				orderedSelection = Utilitaire.putInOrder(finalSelection);
				//Utilitaire.writeArtefacts(selectionFilePath, orderedSelection);
				return Response.ok(orderedSelection).build();
			
			} else {
				// -- 2e filtrage
				finalSelection = Utilitaire.filterByDate(startDate, endDate, finalSelection);
			}
			Artefact[] orderedSelection = Utilitaire.putInOrder(finalSelection);
			//return Response.ok(orderedSelection).build();
			//Utilitaire.writeArtefacts(selectionFilePath, orderedSelection);
			return Response.ok(orderedSelection).build();
		}
	}
	*/
}
