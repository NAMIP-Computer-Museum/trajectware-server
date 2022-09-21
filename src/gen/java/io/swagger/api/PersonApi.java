package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.PersonApiService;
import io.swagger.api.factories.PersonApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Person;

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

@Path("/person")


@io.swagger.annotations.Api(description = "the person API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-09-21T12:58:06.292Z")
public class PersonApi  {
   private final PersonApiService delegate;

   public PersonApi(@Context ServletConfig servletContext) {
      PersonApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("PersonApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (PersonApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = PersonApiServiceFactory.getPersonApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/find")
    
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find persons", notes = "query after persons using some criteria", response = Person.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "person", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Person.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findPersons(@ApiParam(value = "") @QueryParam("name") String name
,@ApiParam(value = "Tags to filter by", allowableValues="Male, Female") @QueryParam("gender") String gender
,@ApiParam(value = "Tags to filter by", allowableValues="GB, US, FR, CA, BE") @QueryParam("country") List<String> country
,@ApiParam(value = "Tags to filter by", allowableValues="permanent, temporary, both, none") @QueryParam("expo") List<String> expo
,@ApiParam(value = "Tags to filter by", allowableValues="apple, google, microsoft, samsung") @QueryParam("company") List<String> company
,@ApiParam(value = "start of date range") @QueryParam("startDate") String startDate
,@ApiParam(value = "end of date range") @QueryParam("endDate") String endDate
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findPersons(name,gender,country,expo,company,startDate,endDate,securityContext);
    }
    @GET
    @Path("/{personId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find an person by its ID", notes = "Returns only one person", response = Person.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "person", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = Person.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "the person has not been found", response = Void.class) })
    public Response getPersonById(@ApiParam(value = "the ID of the person that need to be returned",required=true) @PathParam("personId") Long personId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getPersonById(personId,securityContext);
    }
}
