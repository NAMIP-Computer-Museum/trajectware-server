package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Artefact;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.swagger.api.NotFoundException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-08-17T13:55:34.404Z")
public class ArtefactApiServiceImpl extends ArtefactApiService {
	static Map<Long,Artefact> allData = new HashMap<>();
	
	static Artefact[] tab;
	Artefact[] selection = new Artefact[tab.length];
	int t;
	Artefact[] finalSelection;
	int ts;
	Artefact[] orderedSelection;
	static String path = "./src/main/webapp/Artefact/ArtefactById.txt";
	File selectionFilePath;
	

	static {
		Artefact art_1 = new Artefact();
    	art_1.setId(1L);
    	art_1.setName("Micral N");
    	art_1.setDescription("Windows 3, lancé en 1992 par Microsoft, a été la première version de Windows à pouvoir être qualifiée de système d'exploitation. Les versions précédentes étant uniquement des interfaces graphiques utilisant MS-DOS. Il exploite notamment le mode protégé 32 bits (à partir des processeurs 386).");
    	art_1.setCreationdate("1973");
    	art_1.setCountry("France");
    	List<String> pic_1 = new ArrayList<>();
    	pic_1.add("./Micro_pic/2343.jpg");
    	art_1.setPhotos(pic_1);
    	List<Tag> list_1 = new ArrayList<>();
    	Tag t_1 = new Tag();
    	t_1.setId("Micro");
    	list_1.add(t_1);
    	art_1.setTags(list_1);
    	
    	Artefact art_2 = new Artefact();
    	art_2.setId(2L);
    	art_2.setName("Apple II Plus");
    	art_2.setDescription(" *Champ à compléter* ");
    	art_2.setCreationdate("1979");
    	art_2.setCountry("");
    	List<String> pic_2 = new ArrayList<>();
    	pic_2.add("./Micro_pic/42.jpg");
    	art_2.setPhotos(pic_2);
    	List<Tag> list_2 = new ArrayList<>();
    	list_2.add(t_1);
    	art_2.setTags(list_2);
    	
    	Artefact art_3 = new Artefact();
    	art_3.setId(3L);
    	art_3.setName("iMac G3");
    	art_3.setDescription(" *Champ à compléter* ");
    	art_3.setCreationdate("1998");
    	art_3.setCountry("");
    	List<String> pic_3 = new ArrayList<>();
    	pic_3.add("./Micro_pic/414.jpg");
    	art_3.setPhotos(pic_3);
    	List<Tag> list_3 = new ArrayList<>();
    	list_3.add(t_1);
    	art_3.setTags(list_3);
    	
    	Artefact art_4 = new Artefact();
    	art_4.setId(4L);
    	art_4.setName("Linux");
    	art_4.setDescription(" *Champ à compléter* ");
    	art_4.setCreationdate("1991");
    	art_4.setCountry("");
    	List<String> pic_4 = new ArrayList<>();
    	pic_4.add("./OS_pic/20.jpg");
    	art_4.setPhotos(pic_4);
    	List<Tag> list_4 = new ArrayList<>();
    	Tag t_2 = new Tag();
    	t_2.setId("OS");
    	list_4.add(t_2);
    	art_4.setTags(list_4);
    	
    	Artefact art_5 = new Artefact();
    	art_5.setId(5L);
    	art_5.setName("Pentium 4");
    	art_5.setDescription(" *Champ à compléter* ");
    	art_5.setCreationdate("2001");
    	art_5.setCountry("");
    	List<String> pic_5 = new ArrayList<>();
    	pic_5.add("./CPU_pic/4.jpg");
    	art_5.setPhotos(pic_5);
    	List<Tag> list_5 = new ArrayList<>();
    	Tag t_3 = new Tag();
    	t_3.setId("CPU");
    	list_5.add(t_3);
    	art_5.setTags(list_5);
    	
    	allData.put(1L, art_1);
	    allData.put(2L, art_2);
	    allData.put(3L, art_3);
	    allData.put(4L, art_4);
	    allData.put(5L, art_5);
	    
		tab = new Artefact[] {art_1,art_2,art_3,art_4,art_5};	    
	}

	
	@Override
    public Response findArtefact( @NotNull List<String> tags,  String startDate,  String endDate, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
		Connection connect = null;
		try {
			connect = DBManager.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Artefact> list = new ArrayList<>();
		Artefact[] tab;
		if (tags.size() == 0) {
			if (startDate == null && endDate == null) {
				list = Utilitaire.returnArtAllTable(connect);
				tab = list.toArray(new Artefact[0]);
				return Response.ok(tab).build();
				
			} else {
				list = Utilitaire.filterArtByDate(connect, startDate, endDate);
				tab = list.toArray(new Artefact[0]);
				return Response.ok(tab).build();
			}
		} else {
			if (startDate == null && endDate == null) {
				list = Utilitaire.filterArtByTags(connect, tags);
				tab = list.toArray(new Artefact[0]);
				 return Response.ok(tab).build();
			} else {
				list = Utilitaire.filterArtByTagsAndDate(connect, tags, startDate, endDate);
				tab = list.toArray(new Artefact[0]);
				 return Response.ok(tab).build();
				
			}
		}	
	}
	
    
    @Override
    public Response getArtefactById(Long artefactId, SecurityContext securityContext) throws NotFoundException {    	
		Connection connect = null;
		try {
			connect = DBManager.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Artefact art = Utilitaire.findArtById(connect, artefactId);
		if (art != null) {
			Test_Women.getLocation(connect);
			return Response.ok(art).build();
		} else {
			ResponseBuilder builder = Response.status(Status.NOT_FOUND);
			builder.type(MediaType.APPLICATION_JSON);
			builder.entity("{ \"msg\" : \""+artefactId+" not found\" }");
			return builder.build();
		}
		
    }
}
