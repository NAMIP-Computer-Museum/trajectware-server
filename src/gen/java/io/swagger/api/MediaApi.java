package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.MediaApiService;
import io.swagger.api.factories.MediaApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/media")


@io.swagger.annotations.Api(description = "the media API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-08-17T13:55:34.404Z")
public class MediaApi  {
   private final MediaApiService delegate;

   public MediaApi(@Context ServletConfig servletContext) {
      MediaApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("MediaApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (MediaApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = MediaApiServiceFactory.getMediaApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/image/{fileId}")
    
    @Produces({ "image/jpg" })
    @io.swagger.annotations.ApiOperation(value = "Find a picture by its ID", notes = "Returns only one file", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "media", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Access forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "the event has not been found", response = Void.class) })
    public Response getImageById(@ApiParam(value = "the ID of the image that need to be returned",required=true) @PathParam("fileId") Long fileId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getImageById(fileId,securityContext);
    }
}
