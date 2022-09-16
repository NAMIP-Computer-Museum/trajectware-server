import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.swagger.model.Artefact;
import io.swagger.model.Person;

public class TestPersonApiServiceImpl {

	@Test
	public void testFindPersonById() {
		Person pers_1 = new Person();
		pers_1.setId(1L);
		pers_1.setFirstname("Ada");
		pers_1.setLastname("Lovelace");
		pers_1.setBirthdate("10/12/1815");
		pers_1.setDeathdate("27/11/1852");
		pers_1.setCountry("GB");
		
		Map<Long,Person> allPerson = new HashMap<>();
    	allPerson.put(1L, pers_1);
    	
    	Long personId = 1L;
    	
    	assertEquals(pers_1,allPerson.get(personId));
	}

}
