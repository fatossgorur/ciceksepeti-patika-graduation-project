package service;

import helper.HelperMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import spec.RequestSpec;

public class SignUpService extends RequestSpec {

    public SignUpService() {
        super();
    }

    public JSONObject requestBody(String jsonFileName){
        return HelperMethods.readJsonFile(jsonFileName);
    }

    public Response signUpUser(ResponseSpecification responseSpecification,String jsonFileName) {
        return RestAssured.given()
                .spec(super.getRequestSpecification())
                .body(requestBody(jsonFileName).toString())
                .when()
                .post("/signup")
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }
}
