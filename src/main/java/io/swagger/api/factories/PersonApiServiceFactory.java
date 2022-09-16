package io.swagger.api.factories;

import io.swagger.api.PersonApiService;
import io.swagger.api.impl.PersonApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-08-17T13:55:34.404Z")
public class PersonApiServiceFactory {
    private final static PersonApiService service = new PersonApiServiceImpl();

    public static PersonApiService getPersonApi() {
        return service;
    }
}
