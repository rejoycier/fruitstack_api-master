# Funky Fruits & Veg - fruitstack_api-master - README.md - API testing project

'Funky Fruits & Veg' API testing project is aimed at testing a public fruit grocery API.
The project uses BDD approach and utilise such features of Cucumber, Serenity and RestAssured java library for efficient testing of these endpoints.

## Prerequisites

**Install Applications**

1.  IntelliJ => latest version 
    1. You can find IntelliJ CE (2022.2.2) here:
        - [Windows](https://download.jetbrains.com/idea/ideaIC-2022.2.2.exe) (exe)
        - [Linux](https://download.jetbrains.com/idea/ideaIC-2022.2.2.tar.gz) (tar.gz)
        - [Mac](https://download.jetbrains.com/idea/ideaIC-2022.2.2.dmg) (dmg)

    1. Java will need to be installed [Java 17.0.5 2022-10-18 LTS] is the one I have used, this can be installed either through IntelliJ (it will prompt for this during the setup) or it can be installed by you as the end-user.
        - You can find a suitable version of Java here: https://adoptopenjdk.net/, which will be extracted to a working path and will be added to the project.
            - For Example, A step-by-step guide can be seen [here](https://java.com/en/download/help/path.html)
          
1. Open IntelliJ and complete the initial setup of the editor, choose whatever colour scheme that suits you this can be changed at a later date. There is no additional plugins required.

1. Additional plugins 'Cucumber for Java' and 'Gherkin' and 'Maven'
    1. This can be done by following the flow:
        1. Click the IntelliJ IDEA option from the toolbar.
        1. Then click Preferences
        1. In the Preferences window, you click on Plugins.
        1. In the Plugin marketplace, search for Cucumber for Java and install the plugin
        1. Also in the plugin marketplace, search for 'Gherkin' and 'Maven' and install the plugins
        1. Restart IntelliJ

   > **PLEASE NOTE:** If you have **JBehave** plugin installed this needs to be disabled as this will interfere with the gherkin files.

## Repo

Git:

    git clone https://gitlab.com/joyce.morris/fruitstack_api.git
    cd fruitstack_api


Or simply [download a zip](https://gitlab.com/joyce.morris/fruitstackapi_tests/-/archive/master/fruitstack_api-master.zip) file.

## Run tests

- Run from maven:

  (using below command automatically generates serenity html report under target/site/reports)
  ```
  mvn clean verify
  ```
  
- Run from ide:
 
  (Run the `RunCucumberTest` class) - in this case, all scenario has `@skip` tag will be skipped).
    - If you use IntelliJ as your editor this should be preconfigured and should exist under the run configurations.

You can generate full Serenity reports by running `mvn clean verify`. 

## Serenity report

You can generate full Serenity reports by running `mvn clean verify`. This includes both the living documentation from the feature files
and also details of the REST requests and responses that were executed during the test.

Generated html Serenity report with test results and scenarios details is stored under: `**target/site/reports/**`. Please open `**index.html**` to see report

When running tests from configured Gitlab CI/CD job, please open `**index.html**` inside artifacts (reports/ dir) to see the report.

## Configuration

1. **Base URI** and **Access Key** can be changed in `src/test/java/fruitstack.api/stepdefs/BaseTest.class` 

## The project directory structure

The project follows the standard directory structure used in most Cucumber and Serenity projects:
```Gherkin
src
  + test
    + java                              
      + fruitstack.api                  Supporting code
        + cukes                           Test runner for all feature files
        + stepdefs                        Definitions of steps from feature files
    + resources
      + features                          Feature files 
          + authentication                
          + current_fruit   

```

## Fruitstack API

Documentation about the webservice API can be found here: [API Docs](https://waarkoop.com/?utm_source=any-api&utm_medium=OwnedSites)
> **NOTE:** Each Feature file contains brief explanation about endpoint under test.

## How to write new test
1. Add new feature file under `src/test/resources/features`
    1. For new API endpoint create separate directory
    1. Create feature file with descriptive name
    1. Write scenario(s) using Gherkin syntax Given-When-Then (please see "Coding Conventions" section below for guidelines)
2. Add java definitions for feature steps in appropriate *Steps.java file under `src/test/java/fruitstack.api/stepdefs`
3. Run newly created scenario to verify it works.

## Coding Conventions 

Best practices for writing feature files/scenarios
- **Scenario description**: Write what you are testing, not how you are testing that (for the „how” the GWT is used.). Write it in a short, clear sentence trying to highlight the requirement part for which the given scenario is created.
- Keep the GWT order in your tests (for this annotating existing steps is a technique). However, there are cases, where the WhenThenWhenThen makes sense.
- Use **@Given** to express the preconditions of a test execution. Use passive sentences, @Given is mostly not an "action". E.g "I push the button" --> "the button is pushed"
- Use **@When** only for the functionality you want to test in the scenario (use only 1 or 2 Whens in a scenario) For all other pre-requisites, use @Given  (alias the When steps if needed)
- Use **@Then** only for assertions. Use it to describe the actual outcome, not an expected. DO NOT use "shall happen", but DO use "happened". A statement instead of an expectation.
- Try to write reusable steps (make them parametrized)
- Do not save the words, step definitions shall be descriptive and clear, but not technical sentences.
- Do not use capital letters for the steps, since it will start anyways with Given, When or Then.
- Add quote for exact button names e.g: `user clicks on "Login" button` -> the button text is exactly `Login`
- When you have to values in connection use the same unit in the tests (e.g: do not set 1 minute timeout and execute 10000 millisec algorithm, but: 2 minute timeout and 1 minute execution)
- When testing result, you can be code specific: e.g: when a json response shall contain a "myMessage" filled with "xyz": And I get the response with "xyz" message  --> And I get the response with "myMessage" is "xyz".
