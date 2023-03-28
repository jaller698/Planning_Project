package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class UserSteps {
	@Given("a worker {int} is registered")
	public void aWorkerIsRegistered(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("a worker {int} is registered under the name {string}")
	public void aWorkerIsRegisteredUnderTheName(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("a worker {int} is registered under the name {string} with the password {string}")
	public void aWorkerIsRegisteredUnderTheNameWithThePassword(Integer int1, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("worker {int} is signed in")
	public void workerIsSignedIn(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("worker {int} is signed off")
	public void workerIsSignedOff(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("worker {int} is registered as an admin")
	public void workerIsRegisteredAsAnAdmin(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("worker {int} is registered as a project leader for the project {string}")
	public void workerIsRegisteredAsAProjectLeaderForTheProject(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
	
	@When("worker {int} logs on with username {string} and password {string}")
	public void workerLogsOnWithUsernameAndPassword(Integer int1, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("worker {int} changes their password from {string} to {string}")
	public void workerChangesTheirPasswordFromTo(Integer int1, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("admin {int} changes worker {int} password from {string} to {string}")
	public void adminChangesWorker2sPasswordFromTo(Integer int1, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("admin {int} registeres new worker {int} under the name {string} with the password {string}")
	public void adminRegisteresNewWorkerUnderTheNameWithThePassword(Integer int1, Integer int2, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("worker {int} creates a project with the name {string}")
	public void workerCreatesAProjectWithTheName(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("the administrator creates project with project name {string}")
	public void theAdministratorCreatesProjectWithProjectName(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("an admin {int} removes worker {int} as a project leader from project {string}")
	public void anAdminRemovesWorkerAsAProjectLeaderFromProject(Integer int1, Integer int2, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("an admin {int} sets worker {int} as a project leader for project {string}")
	public void anAdminSetsWorkerAsAProjectLeaderForProject(Integer int1, Integer int2, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("worker {int} joins activity {string} under project {string}")
	public void workerJoinsActivityUnderProject(Integer int1, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("the worker {int} adds {int} hours to activity {string} under project {string}")
	public void theWorkerAddsHoursToActivityUnderProject(Integer int1, Integer int2, String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
	
	@Then("worker {int} exists")
	public void workerExists(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("worker {int} has the name {string}")
	public void workerHasTheName(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("worker {int} is logged on")
	public void workerIsLoggedOn(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("worker {int} is logged off")
	public void workerIsLoggedOff(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("worker {int} has the password {string}")
	public void workerHasThePassword(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("worker {int} is assigned to activity {string}")
	public void workerIsAssignedToActivity(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("worker {int} has no assigned activities")
	public void workerHasNoAssignedActivities(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("a worker {int} is a project leader for project {string}")
	public void aWorkerIsAProjectLeaderForProject(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
