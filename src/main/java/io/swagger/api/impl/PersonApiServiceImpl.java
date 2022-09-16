package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-08-17T13:55:34.404Z")
public class PersonApiServiceImpl extends PersonApiService {
	static Map<Long,Person> allPerson = new HashMap<>();
	static Person[] tab;
//	static String filePath = "./NAM-IP.jpg";
//	static java.io.File file_1 = new java.io.File(filePath);
//	static List<java.io.File> list = new ArrayList<java.io.File>(1);
	
	
	static {
		Person pers_1 = new Person();
		pers_1.setId(1L);
		pers_1.setFirstname("Ada");
		pers_1.setLastname("LOVELACE");
		pers_1.setBirthdate("10/12/1815");
		pers_1.setDeathdate("27/11/1852");
		pers_1.setCountry("GB");
		//pers_1.description("");
		//pers_1.occupation("");
		//pers_1.affiliate("");
		List<Tag> list_1 = new ArrayList<>();
    	Tag t_1 = new Tag();
    	t_1.id("1");
    	t_1.description("mathématiques");
    	list_1.add(t_1);
    	pers_1.setTags(list_1);
    	List<java.io.File> list = new ArrayList<java.io.File>(1);
    	java.io.File file = new java.io.File("./src/main/webapp/Media/"
    										+ "Image/Person_pic/Lovelace.jpg");
    	list.add(file);
    	pers_1.setPhotos(list);
    	
    	Person pers_2 = new Person();
		pers_2.setId(2L);
		pers_2.setFirstname("Grace");
		pers_2.setLastname("HOPPER");
		pers_2.setBirthdate("1906");
		pers_2.setDeathdate("1992");
		pers_2.setCountry("US");
		//pers_2.description("";)
		//pers_2.occupation("");
		//pers_2.affiliate("");
		List<Tag> list_2 = new ArrayList<>();
    	Tag t_2 = new Tag();
    	t_2.id("1");
    	t_2.description("mathématiques");
    	list_2.add(t_2);
    	pers_2.setTags(list_2);
//    	list.add(file_1);
//    	pers_2.setPhotos(list);
    	
    	Person pers_3 = new Person();
		pers_3.setId(3L);
		pers_3.setFirstname("Jean E.");
		pers_3.setLastname("SAMMET");
		pers_3.setBirthdate("1928");
		pers_3.setDeathdate("2017");
		pers_3.setCountry("US");
		//pers_3.description("";)
		//pers_3.occupation("");
		//pers_3.affiliate("");
		List<Tag> list_3 = new ArrayList<>();
    	Tag t_3 = new Tag();
    	t_3.id("1");
    	t_3.description("mathématiques");
    	list_3.add(t_3);
    	pers_3.setTags(list_3);
//    	list.add(file_1);
//    	pers_3.setPhotos(list);
    	
    	Person pers_4 = new Person();
		pers_4.setId(4L);
		pers_4.setFirstname("Karen");
		pers_4.setLastname("SPÄRCK JONES");
		pers_4.setBirthdate("1935");
		pers_4.setDeathdate("2007");
		pers_4.setCountry("GB");
		//pers_4.description("";)
		//pers_4.occupation("");
		//pers_4.affiliate("");
		List<Tag> list_4 = new ArrayList<>();
    	Tag t_4 = new Tag();
    	t_4.id("1");
    	t_4.description("mathématiques");
    	list_4.add(t_4);
    	pers_4.setTags(list_4);
//    	list.add(file_1);
//    	pers_4.setPhotos(list);
    	
    	Person pers_5 = new Person();
		pers_5.setId(5L);
		pers_5.setFirstname("Mary");
		pers_5.setLastname("ALLEN WILKES");
		pers_5.setBirthdate("1937");
		pers_5.setDeathdate("");
		pers_5.setCountry("US");
		//pers_5.description("";)
		//pers_5.occupation("");
		//pers_5.affiliate("");
		List<Tag> list_5 = new ArrayList<>();
    	Tag t_5 = new Tag();
    	t_5.id("1");
    	t_5.description("mathématiques");
    	list_5.add(t_5);
    	pers_5.setTags(list_5);
//    	list.add(file_1);
//    	pers_5.setPhotos(list);
    	
    	Person pers_6 = new Person();
		pers_6.setId(6L);
		pers_6.setFirstname("Margaret");
		pers_6.setLastname("HAMILTON");
		pers_6.setBirthdate("1938");
		pers_6.setDeathdate("");
		pers_6.setCountry("US");
		//pers_6.description("";)
		//pers_6.occupation("");
		//pers_6.affiliate("");
		List<Tag> list_6 = new ArrayList<>();
    	Tag t_6 = new Tag();
    	t_6.id("1");
    	t_6.description("mathématiques");
    	list_6.add(t_6);
    	pers_6.setTags(list_6);
//    	list.add(file_1);
//    	pers_6.setPhotos(list);
    	
    	Person pers_7 = new Person();
		pers_7.setId(7L);
		pers_7.setFirstname("Susan");
		pers_7.setLastname("KARE");
		pers_7.setBirthdate("1954");
		pers_7.setDeathdate("");
		pers_7.setCountry("US");
		//pers_7.description("";)
		//pers_7.occupation("");
		//pers_7.affiliate("");
		List<Tag> list_7 = new ArrayList<>();
    	Tag t_7 = new Tag();
    	t_7.id("1");
    	t_7.description("mathématiques");
    	list_7.add(t_7);
    	pers_7.setTags(list_7);
//    	list.add(file_1);
//    	pers_7.setPhotos(list);
    	
    	Person pers_8 = new Person();
		pers_8.setId(8L);
		pers_8.setFirstname("Sally");
		pers_8.setLastname("FLOYD");
		pers_8.setBirthdate("1950");
		pers_8.setDeathdate("2019");
		pers_8.setCountry("US");
		//pers_8.description("";)
		//pers_8.occupation("");
		//pers_8.affiliate("");
		List<Tag> list_8 = new ArrayList<>();
    	Tag t_8 = new Tag();
    	t_8.id("1");
    	t_8.description("mathématiques");
    	list_8.add(t_8);
    	pers_8.setTags(list_8);
//    	list.add(file_1);
//    	pers_8.setPhotos(list);
    	
    	Person pers_9 = new Person();
		pers_9.setId(9L);
		pers_9.setFirstname("Michelle");
		pers_9.setLastname("BAKER");
		pers_9.setBirthdate("1957");
		pers_9.setDeathdate("");
		pers_9.setCountry("US");
		//pers_9.description("";)
		//pers_9.occupation("");
		//pers_9.affiliate("");
		List<Tag> list_9 = new ArrayList<>();
    	Tag t_9 = new Tag();
    	t_9.id("1");
    	t_9.description("mathématiques");
    	list_9.add(t_9);
    	pers_9.setTags(list_9);
//    	list.add(file_1);
//    	pers_9.setPhotos(list);
    	
    	Person pers_10 = new Person();
		pers_10.setId(10L);
		pers_10.setFirstname("Sue");
		pers_10.setLastname("GARDNER");
		pers_10.setBirthdate("1967");
		pers_10.setDeathdate("");
		pers_10.setCountry("CA");
		//pers_10.description("";)
		//pers_10.occupation("");
		//pers_10.affiliate("");
		List<Tag> list_10 = new ArrayList<>();
    	Tag t_10 = new Tag();
    	t_10.id("1");
    	t_10.description("mathématiques");
    	list_10.add(t_10);
    	pers_10.setTags(list_10);
//    	list.add(file_1);
//    	pers_10.setPhotos(list);
    	
    	Person pers_0 = new Person();
		pers_0.setId(0L);
		pers_0.setFirstname("One");
		pers_0.setLastname("NO");
		pers_0.setBirthdate("Never born");
		pers_0.setDeathdate("Will never die");
		pers_0.setCountry("From nowhere");
		//pers_1.description("";)
		//pers_1.occupation("");
		//pers_1.affiliate("");
    	
    	allPerson.put(1L,pers_1);
    	allPerson.put(2L,pers_2);
    	allPerson.put(3L,pers_3);
    	allPerson.put(4L,pers_4);
    	allPerson.put(5L,pers_5);
    	allPerson.put(6L,pers_6);
    	allPerson.put(7L,pers_7);
    	allPerson.put(8L,pers_8);
    	allPerson.put(9L,pers_9);
    	allPerson.put(10L,pers_10);
    	allPerson.put(0L, pers_0);

		
		tab = new Person [] {pers_1,pers_2,pers_3,pers_4,pers_5,
								pers_6,pers_7,pers_8,pers_9,pers_10,};
	}
	
    @Override
    public Response findPersons( @NotNull String name, SecurityContext securityContext) throws NotFoundException {
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
    }
}
