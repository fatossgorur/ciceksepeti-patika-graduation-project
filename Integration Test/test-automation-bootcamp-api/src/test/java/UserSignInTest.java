import io.restassured.response.Response;
import model.SuccessSignInResponse;
import model.UnauthorizedSignInResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import spec.ResponseSpec;

public class UserSignInTest extends Utils {

    @Test(priority = 1)
    //Control sign in request body
    public void controlRequestBody() {
        JSONObject userRequestBody = signUpService.requestBody("SignIn");
        String email = userRequestBody.getString("email");
        String password = userRequestBody.getString("password");
        Assert.assertTrue(email.contains("@"));
        Assert.assertNotNull(email);
        Assert.assertTrue(password.length() <= 20);
        Assert.assertTrue(password.length() >= 8);
        Assert.assertNotNull(password);
    }

    @Test(priority = 2)
    //User successfully signed-in
    public void successUserSignIn() {
        Response userSignIn = signInService.signInUser(ResponseSpec.checkStatusCodeCreated(), "SignIn");
        SuccessSignInResponse response = userSignIn.as(SuccessSignInResponse.class);
        //userSignIn.prettyPeek();
        String accessToken = userSignIn.getBody().jsonPath().getString("access_token");
        Assert.assertEquals(response.getAccess_token(), accessToken);
        Assert.assertNotNull(accessToken);
    }

    @Test(priority = 3)
    //User submitted a wrong email or password
    public void userUnauthorizedSignIn() {
        Response userUnauthorizedSignIn = signInService.signInUser(ResponseSpec.checkStatusCodeUnauthorized(), "WrongEmailPassword");
        UnauthorizedSignInResponse response = userUnauthorizedSignIn.as(UnauthorizedSignInResponse.class);
        //userUnauthorizedSignIn.prettyPeek();
        Integer statusCode = userUnauthorizedSignIn.getBody().jsonPath().getInt("statusCode");
        String message = userUnauthorizedSignIn.getBody().jsonPath().getString("message");
        Assert.assertEquals(response.getStatusCode(), statusCode);
        Assert.assertEquals(response.getMessage(), message);
    }

}
