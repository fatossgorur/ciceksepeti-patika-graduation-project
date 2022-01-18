package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import helper.Log4j;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConflictSignUpResponse {
    @JsonProperty("statusCode")
    private Integer statusCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("error")
    private String error;

    public Integer getStatusCode() {
        Log4j.info("Conflict sign up response status code: "+statusCode);
        return statusCode;
    }

    public String getMessage() {
        Log4j.info("Conflict sign up response message: "+message);
        return message;
    }

    public String getError() {
        Log4j.info("Conflict sign up response error: "+error);
        return error;
    }

}
