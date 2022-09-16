package io.swagger.api.factories;

import io.swagger.api.ArtefactApiService;
import io.swagger.api.impl.ArtefactApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-08-17T13:55:34.404Z")
public class ArtefactApiServiceFactory {
    private final static ArtefactApiService service = new ArtefactApiServiceImpl();

    public static ArtefactApiService getArtefactApi() {
        return service;
    }
}
