package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import helper.Log4j;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnauthorizedSignInResponse {
    @JsonProperty("statusCode")
    private Integer statusCode;
    @JsonProperty("message")
    private String message;

    public Integer getStatusCode() {
        Log4j.info("Unauthorized sign in response status code: "+statusCode);
        return statusCode;
    }

    public String getMessage() {
        Log4j.info("Unauthorized sign in response message: "+message);
        return message;
    }

}
