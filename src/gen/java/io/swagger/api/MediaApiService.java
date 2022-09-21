package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;


import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-09-21T12:58:06.292Z")
public abstract class MediaApiService {
    public abstract Response getAudioById(String fileId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getImageById(String fileId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getVideoById(String fileId,SecurityContext securityContext) throws NotFoundException;
}
