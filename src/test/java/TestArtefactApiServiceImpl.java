import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.Test;

import io.swagger.api.impl.ArtefactApiServiceImpl;
import io.swagger.api.impl.Utilitaire;
import io.swagger.model.Artefact;
import io.swagger.model.Tag;

public class TestArtefactApiServiceImpl {
	
	@Test
	public void testOneArtefactHashMap() {
		Artefact art_5 = new Artefact();
    	art_5.setId(5L);
    	art_5.setName("Pentium 4");
    	art_5.setDescription(" *Champ à compléter* ");
    	art_5.setCreationdate("2001");
    	art_5.setCountry("fr");
    	
    	Map<Long,Artefact> allData = new HashMap<>();
    	allData.put(5L, art_5);
    	
    	assertEquals(art_5.getName(),allData.get(5L).getName());
    	assertEquals(art_5.getDescription(),allData.get(5L).getDescription());
    	assertEquals(art_5.getCreationdate(),allData.get(5L).getCreationdate());
    	assertEquals(art_5.getCountry(),allData.get(5L).getCountry());
	}
	
	@Test
	public void testSeveralArtefacts() {
		Artefact art_2 = new Artefact();
    	art_2.setId(2L);
    	art_2.setName("Apple II Plus");
    	art_2.setDescription(" *Champ à compléter* ");
    	art_2.setCreationdate("1979");
    	art_2.setCountry("");
    	
    	Artefact art_3 = new Artefact();
    	art_3.setId(3L);
    	art_3.setName("iMac G3");
    	art_3.setDescription(" *Champ à compléter* ");
    	art_3.setCreationdate("1998");
    	art_3.setCountry("");
    	
    	Artefact[] tab = new Artefact[] {art_2,art_3};
    	
    	
    	assertEquals(art_2.getName(),tab[0].getName());
    	assertEquals(art_2.getDescription(),tab[0].getDescription());
    	assertEquals(art_2.getCreationdate(),tab[0].getCreationdate());
    	assertEquals(art_2.getCountry(),tab[0].getCountry());
    	
    	assertEquals(art_3.getName(),tab[1].getName());
    	assertEquals(art_3.getDescription(),tab[1].getDescription());
    	assertEquals(art_3.getCreationdate(),tab[1].getCreationdate());
    	assertEquals(art_3.getCountry(),tab[1].getCountry());
	}
	
	@Test
	public void testFindById() {
		Artefact art_2 = new Artefact();
    	art_2.setId(2L);
    	art_2.setName("Apple II Plus");
    	art_2.setDescription(" *Champ à compléter* ");
    	art_2.setCreationdate("1979");
    	art_2.setCountry("");
    	
    	Map<Long,Artefact> allData = new HashMap<>();
    	allData.put(2L, art_2);
    	
    	Long artefactId = 2L;
    	
    	/*if (allData.containsKey(artefactId) ) {
    		System.out.println(allData.get(artefactId));
    	} else {
    		System.out.println("ArtefactId not found");
    	}*/
    	
    assertEquals(art_2,allData.get(artefactId));

    	
	}
	
	@Test
	public void testFindByDate() {
		Artefact art_2 = new Artefact();
    	art_2.setId(2L);
    	art_2.setName("Apple II Plus");
    	art_2.setDescription(" *Champ à compléter* ");
    	art_2.setCreationdate("1979");
    	art_2.setCountry("");
    	
    	Artefact art_3 = new Artefact();
    	art_3.setId(3L);
    	art_3.setName("iMac G3");
    	art_3.setDescription(" *Champ à compléter* ");
    	art_3.setCreationdate("04/09/1998");
    	art_3.setCountry("");
    	
    	Artefact[] tab = new Artefact[] {art_2,art_3};
    	String startDate = "1975";
    	String endDate = "1985";
    	Artefact[] selection = new Artefact[tab.length];
		int j = 0;
		Date d_1 = new Date();
		Date d_2 = new Date();
		Date d = new Date();
		
		d_1 = Utilitaire.stringToDate(startDate);
		d_2 = Utilitaire.stringToDate(endDate);
		
		for (int i = 0; i < tab.length; i++) {
			d = Utilitaire.stringToDate(tab[i].getCreationdate());
			System.out.println(d);
			if (d.after(d_1) && d.before(d_2)) {
				selection[j] = tab[i];
				j++;
			}
		}
		if (selection[0] == null) {
			System.out.println("No artefact found");
		}
		
		System.out.println(selection);
		System.out.println(tab);
		
		assertEquals(art_2.getName(),selection[0].getName());
		assertNotEquals(art_3.getName(), selection[0].getName());
		assertEquals(j+1,selection.length);
	}
	
	@Test
	public void findByTags() {
		Artefact art_2 = new Artefact();
    	art_2.setId(2L);
    	art_2.setName("Apple II Plus");
    	art_2.setDescription(" *Champ à compléter* ");
    	art_2.setCreationdate("1979");
    	art_2.setCountry("");
    	List<Tag> list_2 = new ArrayList<>();
    	Tag t_2 = new Tag();
    	t_2.setId("Micro");
    	list_2.add(t_2);
    	art_2.setTags(list_2);
    	
    	Artefact art_3 = new Artefact();
    	art_3.setId(3L);
    	art_3.setName("iMac G3");
    	art_3.setDescription(" *Champ à compléter* ");
    	art_3.setCreationdate("04/09/1998");
    	art_3.setCountry("");
    	List<Tag> list_3 = new ArrayList<>();
    	Tag t_3 = new Tag();
    	t_3.setId("Micro");
    	list_3.add(t_3);
    	art_3.setTags(list_3);
    	
	}
}
