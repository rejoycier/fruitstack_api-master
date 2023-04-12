
package fruitstack.api.stepdefs;

import fruitstack.FruitStackEndPoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.hc.core5.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringRegularExpression.matchesRegex;

public class CurrentFreshFruitSteps extends BaseTest {

    @Given("request query parameter is {string}")
    public void requestQueryParameterIsAsGiven(String queryParameter) {
        SerenityRest.given()
                .pathParam("query", queryParameter)
                .pathParam("access_key", ACCESS_KEY);;
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
    public void theApiResponseWithCorrectFruitDataIsReturned(String location) {
        restAssuredThat(response -> {
            response
                    .statusCode(HttpStatus.SC_OK)
                    .body("location.name", equalTo(location))
                    .body("current.observation_time", matchesRegex("\\b((1[0-2]|0?[1-9]):([0-5][0-9]) ([AaPp][Mm]))"))
                    .body("current.temperature", hasToString(matchesRegex("-?\\d{1,2}")))
                    .body("current.fruit_code", hasToString(matchesRegex("[1-3]\\d\\d")))
                    .body("current.wind_speed", hasToString(matchesRegex("([0-9]|[1-8][0-9]|9[0-9]|1[0-9]{2}|200)")))
                    .body("current.wind_degree", hasToString(matchesRegex("([0-2]?[0-9]{1,2}|3[0-5][0-9]|360)")))
                    .body("current.wind_dir", matchesRegex("(N|NNE|ENE|NE|E|SSE|ESE|SE|S|SSW|WSW|SW|W|NW|NNW|WNW)"))
                    .body("current.pressure", hasToString(matchesRegex("(9[5-9][0-9]|10[0-4][0-9]|1050)")))
                    .body("current.cloudcover", hasToString(matchesRegex("(0|[1-9][0-9]|100)")))
                    .body("current.is_day", matchesRegex("(yes|no)"));
        });
    }
}
