Feature: fruitstack API requests shall be authenticated with access_key parameter

  To authenticate with the API, use the base URL and pass your API access key to the API's access_key parameter.
  `https://waarkoop.com/current
  ? access_key = YOUR_ACCESS_KEY`

  Scenario: Request with invalid access_key returns error info
    Given user's access_key parameter is invalid
    When  the user sends GET request to current endpoint
    Then the "You have not supplied a valid API Access Key. [Technical Support: contact@waarkoop.com]" info is returned
    And the API error code is 101

  Scenario: Request with missing access_key returns error info
    Given user's access_key parameter is missing
    When  the user sends GET request to current endpoint
    Then the "You have not supplied an API Access Key. [Required format: access_key=YOUR_ACCESS_KEY]" info is returned
    And the API error code is 101
