package io.swagger.api.factories;

import io.swagger.api.MediaApiService;
import io.swagger.api.impl.MediaApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-08-17T13:55:34.404Z")
public class MediaApiServiceFactory {
    private final static MediaApiService service = new MediaApiServiceImpl();

    public static MediaApiService getMediaApi() {
        return service;
    }
}
