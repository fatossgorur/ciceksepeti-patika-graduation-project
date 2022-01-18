package model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import helper.Log4j;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessSignUpResponse {
    @JsonProperty("access_token")
    private String access_token;

    public String getAccess_token() {
        Log4j.info("Success sign up response token: "+access_token);
        return access_token;
    }

}
