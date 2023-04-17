package example.cucumber;

import io.cucumber.java.en.*;

public class Login {

	@Given("a worker {int} is registered under the name {string} with the password {string}")
	public void aWorkerIsRegisteredUnderTheNameWithThePassword(Integer int1, String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("worker {int} is signed off")
	public void workerIsSignedOff(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("worker {int} logs on with username {string} and password {string}")
	public void workerLogsOnWithUsernameAndPassword(Integer int1, String string, String string2) {
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

	@Given("worker {int} is signed in")
	public void workerIsSignedIn(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("worker {int} changes their password from {string} to {string}")
	public void workerChangesTheirPasswordFromTo(Integer int1, String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("worker {int} has the password {string}")
	public void workerHasThePassword(Integer int1, String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("worker {int} is registered as an admin")
	public void workerIsRegisteredAsAnAdmin(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("admin {int} changes worker {int} password from {string} to {string}")
	public void adminChangesWorkerPasswordFromTo(Integer int1, Integer int2, String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("admin {int} registeres new worker {int} under the name {string} with the password {string}")
	public void adminRegisteresNewWorkerUnderTheNameWithThePassword(Integer int1, Integer int2, String string,
			String string2) {
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
}
