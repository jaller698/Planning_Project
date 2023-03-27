package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProjectSteps {
	private String errorMessage;
	
	@Given("project {string} exists")
	public void projectExists(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("project {string} does not exist")
	public void projectDoesNotExist(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("an admin creates activiy with title {string}")
	public void anAdminCreatesActiviyWithTitle(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("the project leader creates activiy with title {string}")
	public void theProjectLeaderCreatesActiviyWithTitle(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("activity number {int}")
	public void activityNumber(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("allocates {int} hours to the activity")
	public void allocatesHoursToTheActivity(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the activity {string} with the activity number {int}, expected hours {int} is added to the project {string} and the message {string} is returned")
	public void theActivityWithTheActivityNumberExpectedHoursIsAddedToTheProjectAndTheMessageIsReturned(String string, String string2, String string3, String string4, String string5) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the error message {string} is returned.")
	public void theErrorMessageIsReturned(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
