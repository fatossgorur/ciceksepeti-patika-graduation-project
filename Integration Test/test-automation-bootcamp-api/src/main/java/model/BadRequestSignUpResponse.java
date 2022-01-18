package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import helper.Log4j;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BadRequestSignUpResponse {
    @JsonProperty("statusCode")
    private Integer statusCode;
    @JsonProperty("message")
    private List<String> message;
    @JsonProperty("error")
    private String error;

    public Integer getStatusCode() {
        return statusCode;
    }

    public List<String> getMessage() {
        Log4j.info("Bad request sign up response message: "+message);
        return message;
    }

    public String getError() {
        Log4j.info("Bad request sign up response error: "+error);
        return error;
    }

}


