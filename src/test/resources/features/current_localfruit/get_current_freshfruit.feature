Feature: Fresh fruit shall give real-time count of product data for given shop

  To query the fruitstack API for real-time fruit count of product data for given shop, simply attach your preferred
  location to the API's `/current` endpoint as seen in the example request below:
  `https://waarkoop.com/current
  ? access_key = YOUR_ACCESS_KEY
  & query = Dublin'

  Scenario: Correct list of fruit data is returned for single query parameter
    Given request query parameter is "Dublin"
    When the user sends GET request to current endpoint
    Then the API response with correct fruit data for "Dublin" is returned

  Scenario: Missing query error type is returned for empty query parameter
    Given request query parameter is ""
    When the user sends GET request to current endpoint
    Then the "missing_query" error type is returned
    And the API error code is 601
