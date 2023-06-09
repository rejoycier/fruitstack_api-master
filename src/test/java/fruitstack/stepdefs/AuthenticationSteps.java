package fruitstack.stepdefs;

import fruitstack.cukes.BaseTest;
import io.cucumber.java.en.Given;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.lang3.RandomStringUtils;

public class AuthenticationSteps extends BaseTest {

    @Given("user is authenticated")
    public void userIsAuthenticated() {
        SerenityRest.given()
                .baseUri(BaseTest.BASE_URI)
                .pathParam("access_key", BaseTest.ACCESS_KEY);
    }

    @Given("user's access_key parameter is missing")
    public void usersAccessKeyParameterIsMissing() {
        SerenityRest.given()
                .baseUri(BaseTest.BASE_URI)
                .pathParam("access_key", "")
                .pathParam("query", "Dublin");
    }

    @Given("user's access_key parameter is invalid")
    public void usersAccessKeyParameterIsInvalid() {
        SerenityRest.given()
                .baseUri(BaseTest.BASE_URI)
                .pathParam("access_key", RandomStringUtils.randomAlphanumeric(32))
                .pathParam("query", "Dublin");
    }
}
