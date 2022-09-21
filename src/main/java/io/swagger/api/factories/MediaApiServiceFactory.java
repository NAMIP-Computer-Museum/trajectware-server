package io.swagger.api.factories;

import io.swagger.api.MediaApiService;
import io.swagger.api.impl.MediaApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-09-21T12:58:06.292Z")
public class MediaApiServiceFactory {
    private final static MediaApiService service = new MediaApiServiceImpl();

    public static MediaApiService getMediaApi() {
        return service;
    }
}
