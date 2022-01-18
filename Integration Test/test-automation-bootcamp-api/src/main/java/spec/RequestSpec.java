package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    RequestSpecification requestSpecification;
    public String baseURL= "https://bootcampapi.techcs.io/api/fe/v1/authorization";

    public RequestSpec(){
        requestSpecification=  new RequestSpecBuilder()
                .setBaseUri(baseURL)
                //.addHeader("Authorization","Bearer " +token)
                .setContentType("application/json")
                .setAccept("*/*")
                .build();
    }

    public RequestSpecification getRequestSpecification() {return requestSpecification;}
}
