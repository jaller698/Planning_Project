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
	public void project_exists(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("project {string} has an activity {string} registered")
	public void project_has_an_activity_registered(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("a worker {int} is a project leader for project {string}")
	public void aWorkerIsAProjectLeaderForProject(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("the worker {int} adds {int} hours to activity {string} under project {string}")
	public void the_worker_adds_hours_to_activity_under_project(Integer int1, Integer int2, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("project leader {int} assigns worker {int} to activity {string} under project {string}")
	public void projectLeaderAssignsWorkerToActivityUnderProject(Integer int1, Integer int2, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("worker {int} creates activity {string} with {int} hours given in project {string}")
	public void workerCreatesActivityWithHoursGivenInProject(Integer int1, String string, Integer int2, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("{int} hours is registered under worker {int} in activity {string}")
	public void hours_is_registered_under_worker_in_activity(Integer int1, Integer int2, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the message {string} is returned")
	public void the_message_is_returned(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the activity {string} with hours {int} allocated is added to the project {string}")
	public void theActivityWithHoursAllocatedIsAddedToTheProject(String string, Integer int1, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
