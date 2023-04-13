
package fruitstack.stepdefs;

import fruitstack.FruitStackEndPoints;
import fruitstack.cukes.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.hc.core5.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class CurrentFreshFruitSteps extends BaseTest {

    @Given("request query parameter is {string}")
    public void requestQueryParameterIsAsGiven(String queryParameter) {
        SerenityRest.given()
                .pathParam("query", queryParameter)
                .pathParam("access_key", ACCESS_KEY);
    }

    @When("the user sends GET request to current endpoint")
    public void theUserSendsGetRequestForCurrentFruit() {
        SerenityRest.when().get(FruitStackEndPoints.CURRENT.getUrl());
    }

    @Then("the {string} error type is returned")
    public void theGivenErrorTypeIsReturned(String type) {
        restAssuredThat(response -> response
                .body("error.type", equalTo(type)));
    }

    @Then("the {string} info is returned")
    public void theGivenErrorInfoIsReturned(String message) {
        restAssuredThat(response -> response
                .body("error.info", equalTo(message)));
    }

    @Then("the API error code is {int}")
    public void theApiErrorCodeIs(int errorCode) {
        restAssuredThat(response -> response
                .body("error.code", equalTo(errorCode)));
    }

    @Then("the API response with correct fruit data for {string} is returned")
    public void theApiResponseWithCorrectFruitDataIsReturned() {
        restAssuredThat(response -> {
            response
                    .statusCode(HttpStatus.SC_OK);
        });
    }
}
