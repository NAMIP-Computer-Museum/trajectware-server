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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-08-17T13:55:34.404Z")
public class MediaApiServiceImpl extends MediaApiService {
	static Map<Long,java.io.File> allPic = new HashMap<>();
	static Map<String,Long> picToPerson = new HashMap<>();
	
	static {
		String filePath_1 = "./src/main/webapp/Media/Image/Micro_pic/2343.jpg";
		java.io.File file_1 = new java.io.File(filePath_1);
    	allPic.put(1L,file_1);
    	String filePath_2 = "./src/main/webapp/Media/Image/Micro_pic/42.jpg";
		java.io.File file_2 = new java.io.File(filePath_2);
    	allPic.put(2L,file_2);
    	String filePath_3 = "./src/main/webapp/Media/Image/Micro_pic/414.jpg";
		java.io.File file_3 = new java.io.File(filePath_3);
    	allPic.put(3L,file_3);
    	String filePath_4 = "./src/main/webapp/Media/Image/OS_pic/20.jpg";
		java.io.File file_4 = new java.io.File(filePath_4);
    	allPic.put(4L,file_4);
    	String filePath_5 = "./src/main/webapp/Media/Image/CPU_pic/4.jpg";
		java.io.File file_5 = new java.io.File(filePath_5);
    	allPic.put(5L,file_5);
	}
	
    @Override
    public Response getImageById(String fileId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        boolean found = false;
        for (Long l = 1L; l < 6L; l++) {
        	int index = allPic.get(l).getName().indexOf(".");
        	String id = allPic.get(l).getName().substring(0, index);
        	if (fileId.equals(id)) {
        		found = true;
        	}
        }
		if (found) {
    		ResponseBuilder response = Response.ok(allPic.get(fileId));
        	response.header("Content-Disposition",
                    "attachment; filename="+fileId+".jpg");
        	return response.build();
    	} else {
        	ResponseBuilder builder = Response.status(Status.NOT_FOUND);
			builder.type(MediaType.APPLICATION_JSON);
			builder.entity("{ \"msg\" : \""+fileId+" not found\" }");
			return builder.build();
        }
        
        //return response.build();
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getVideoById(String fileId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getAudioById(String fileId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    
}
