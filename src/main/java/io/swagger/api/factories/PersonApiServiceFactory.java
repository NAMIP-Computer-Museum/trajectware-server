package io.swagger.api.factories;

import io.swagger.api.PersonApiService;
import io.swagger.api.impl.PersonApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2022-09-21T12:58:06.292Z")
public class PersonApiServiceFactory {
    private final static PersonApiService service = new PersonApiServiceImpl();

    public static PersonApiService getPersonApi() {
        return service;
    }
}
