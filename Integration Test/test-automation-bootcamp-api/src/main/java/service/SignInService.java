package service;

import helper.HelperMethods;
import helper.Log4j;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import spec.RequestSpec;

public class SignInService extends RequestSpec {

    public SignInService() {
        super();
    }

    public JSONObject requestBody(String jsonFileName){
        return HelperMethods.readJsonFile(jsonFileName);
    }

    public Response signInUser(ResponseSpecification responseSpecification,String jsonFileName) {
        //JSONObject signUpRequestBody = HelperMethods.readJsonFile(jsonFileName);
        return RestAssured.given()
                .spec(super.getRequestSpecification())
                .body(requestBody(jsonFileName).toString())
                .when()
                .post("/signin")
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

}
