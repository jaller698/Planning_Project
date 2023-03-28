package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProjectSteps {
	@Given("project {string} exists")
	public void projectExists(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("project {string} has an activity {string} registered")
	public void projectHasAnActivityRegistered(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("project {string} has an activity {string} registered with {int} hours allocated")
	public void projectHasAnActivityRegisteredWithHoursAllocated(String string, String string2, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("the project {string} has no project leader assigned")
	public void theProjectHasNoProjectLeaderAssigned(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
	
	@When("project leader {int} assigns worker {int} to activity {string} under project {string}")
	public void projectLeaderAssignsWorkerToActivityUnderProject(Integer int1, Integer int2, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("project leader {int} creates activity {string} with {int} hours given in project {string}")
	public void projectLeaderCreatesActivityWithHoursGivenInProject(Integer int1, String string, Integer int2, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("project leader {int} edits the expected time of activity {string}, under project {string}, to {int} hours")
	public void projectLeaderEditsTheExpectedTimeOfActivityUnderProjectToHours(Integer int1, String string, String string2, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("project leader {int} removes the activity {string}, under project {string}")
	public void projectLeaderRemovesTheActivityUnderProject(Integer int1, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
	
	@Then("the project {string} with {int}-digit serial no. from year and project number is created")
	public void theProjectWithDigitSerialNoFromYearAndProjectNumberIsCreated(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the project {string} has no project leader")
	public void theProjectHasNoProjectLeader(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the project {string} has no activity named {string} under it")
	public void theProjectHasNoActivityNamedUnderIt(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
