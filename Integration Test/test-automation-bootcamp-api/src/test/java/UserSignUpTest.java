import io.restassured.response.Response;
import model.BadRequestSignUpResponse;
import model.ConflictSignUpResponse;
import model.SuccessSignUpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import spec.ResponseSpec;
import java.util.List;

public class UserSignUpTest extends Utils {

    @Test(priority = 1)
    //Control sign up request body
    public void controlRequestBody() {
        JSONObject userRequestBody = signUpService.requestBody("SignUp");
        String email = userRequestBody.getString("email");
        String password = userRequestBody.getString("password");
        Assert.assertTrue(email.contains("@"));
        Assert.assertNotNull(email);
        Assert.assertTrue(password.length() <= 20);
        Assert.assertTrue(password.length() >= 8);
        Assert.assertNotNull(password);
    }

    @Test(priority = 2)
    //User successfully signed-up
    public void successUserSignUp() {
        //check response body
        Response userSignUp = signUpService.signUpUser(ResponseSpec.checkStatusCodeCreated(), "SignUp");
        SuccessSignUpResponse response = userSignUp.as(SuccessSignUpResponse.class);
        //userSignUp.prettyPeek();
        String accessToken = userSignUp.getBody().jsonPath().getString("access_token");
        Assert.assertEquals(response.getAccess_token(), accessToken);
        Assert.assertNotNull(accessToken);
    }

    @Test(priority = 3)
    //User already signed-up
    public void userAlreadySignUp() {
        //check response body
        Response userAlreadySignUp = signUpService.signUpUser(ResponseSpec.checkStatusCodeConflict(), "SignUp");
        ConflictSignUpResponse response = userAlreadySignUp.as(ConflictSignUpResponse.class);
        //userAlreadySignUp.prettyPeek();
        Integer statusCode = userAlreadySignUp.getBody().jsonPath().getInt("statusCode");
        String message = userAlreadySignUp.getBody().jsonPath().getString("message");
        String error = userAlreadySignUp.getBody().jsonPath().getString("error");
        Assert.assertEquals(response.getStatusCode(), statusCode);
        Assert.assertEquals(response.getMessage(), message);
        Assert.assertEquals(response.getError(), error);
        Assert.assertNotNull(statusCode);
        Assert.assertNotNull(message);
        Assert.assertNotNull(error);
        Assert.assertEquals((int) statusCode, 409);
        Assert.assertTrue(message.contains("User already exist!"));
        Assert.assertTrue(error.contains("Conflict"));
    }

    @Test(priority = 4)
    //Email and password should not be empty
    public void useAlreadySignUp() {
        //check response body
        Response userFieldNotEmptySignUp = signUpService.signUpUser(ResponseSpec.checkStatusCodeBadRequest(), "InvalidEmailPassword");
        BadRequestSignUpResponse response = userFieldNotEmptySignUp.as(BadRequestSignUpResponse.class);
        //userAlreadySignUp.prettyPeek();
        Integer statusCode = userFieldNotEmptySignUp.getBody().jsonPath().getInt("statusCode");
        List<String> message = userFieldNotEmptySignUp.getBody().jsonPath().getList("message");
        String error = userFieldNotEmptySignUp.getBody().jsonPath().getString("error");
        Assert.assertEquals(response.getStatusCode(), statusCode);
        Assert.assertEquals(response.getMessage(), message);
        Assert.assertEquals(response.getError(), error);
        Assert.assertNotNull(statusCode);
        Assert.assertNotNull(message);
        Assert.assertNotNull(error);
        Assert.assertEquals((int) statusCode, 400);
        Assert.assertTrue(message.contains("email should not be empty"));
        Assert.assertTrue(error.contains("Bad Request"));
    }

}
