package io.swagger.api.factories;

import io.swagger.api.ArtefactApiService;
import io.swagger.api.impl.ArtefactApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-09-21T12:58:06.292Z")
public class ArtefactApiServiceFactory {
    private final static ArtefactApiService service = new ArtefactApiServiceImpl();

    public static ArtefactApiService getArtefactApi() {
        return service;
    }
}
