package io.swagger.api.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import io.swagger.model.Artefact;
import io.swagger.model.Person;

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
				System.out.println("element qui compare : "+tableau[j]);
				da = stringToDate(tableau[j].getCreationdate());
				System.out.println(tableau[j]);
				if (d.after(da)) {
					count++;
				}
			}
			order.put(count, tableau[i]);
		}
		for (int i = 0; i < tableau.length; i++) {
			System.out.println((i+1)+"e element : "+tableau[i]);
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
		System.out.println("t : "+selection.length);
		int ts = 0;
		Date d_1 = new Date();
		Date d_2 = new Date();
		Date d = new Date();
		
		d_1 = stringToDate(startDate);
		d_2 = stringToDate(endDate);
		for (int i = 0; i < selection.length; i++) {
			System.out.println(selection[i]);
			System.out.println("index : "+i+" t : "+selection.length);
			d = stringToDate(selection[i].getCreationdate());
			if (d.after(d_1) && d.before(d_2)) {
				finalSelection[ts] = selection[i];
				System.out.println("selection : "+selection[i]+" final : "+finalSelection[ts]);
				ts++;
			}
		}
		System.out.println("taille après 2e filtre : "+finalSelection.length);
		if (finalSelection.length == selection.length) {
			for (int i = ts; i < selection.length; i++) {
				System.out.println("element à supprimer : "+finalSelection[ts]);
				finalSelection = ArrayUtils.remove(finalSelection, ts);
			}
		}
		System.out.println("taille apres suppression null : "+finalSelection.length);
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
}
