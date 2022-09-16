package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ArtefactApiService;
import io.swagger.api.factories.ArtefactApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Artefact;

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

@Path("/artefact")


@io.swagger.annotations.Api(description = "the artefact API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-08-17T13:55:34.404Z")
public class ArtefactApi  {
   private final ArtefactApiService delegate;

   public ArtefactApi(@Context ServletConfig servletContext) {
      ArtefactApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("ArtefactApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (ArtefactApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = ArtefactApiServiceFactory.getArtefactApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/find")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find artefacts", notes = "query after artefacts some criteria", response = Artefact.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "artefact", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Artefact.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid tag value", response = Void.class) })
    public Response findArtefactq(@ApiParam(value = "Tags to filter by",required=true, allowableValues="CPU, IHM, Micro, OS, APP, ...") @QueryParam("tags") List<String> tags
,@ApiParam(value = "start of date range") @QueryParam("startDate") String startDate
,@ApiParam(value = "end of date range") @QueryParam("endDate") String endDate
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findArtefactq(tags,startDate,endDate,securityContext);
    }
    @GET
    @Path("/{artefactId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find an artefact by its ID", notes = "Returns only one artefact", response = Artefact.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "artefact", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Artefact.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "the invention has not been found", response = Void.class) })
    public Response getArtefactById(@ApiParam(value = "the ID of the invention that need to be returned",required=true) @PathParam("artefactId") Long artefactId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getArtefactById(artefactId,securityContext);
    }
}
