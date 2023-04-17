package example.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Application;
import application.Medarbejder;
import database.SessionManager;
import io.cucumber.java.en.*;

public class Login {

	@Given("a worker {int} is registered under the name {string} with the password {string}")
	public void aWorkerIsRegisteredUnderTheNameWithThePassword(Integer id, String name, String PWD) {
		Medarbejder newWorker = new Medarbejder(name,PWD);
		assertTrue(StepDefinitions.app.workers.getUser(name).navn.equals(newWorker.navn));
		assertTrue(StepDefinitions.app.workers.getUser(name).password.equals(newWorker.password));

		
	}

	@Given("worker {int} is signed off")
	public void workerIsSignedOff(Integer id) {
		StepDefinitions.sm.logoutUser(null);
		String name = StepDefinitions.app.workers.getUser(--id).toString();
		assertFalse(StepDefinitions.sm.checkSession(name));
	}

	@When("worker {int} logs on with username {string} and password {string}")
	public void workerLogsOnWithUsernameAndPassword(Integer id, String name, String PWD) {
		String s = StepDefinitions.sm.loginUser(name, PWD);
	}

	@Then("worker {int} is logged on")
	public void workerIsLoggedOn(Integer id) {
		String navn = StepDefinitions.app.workers.getUser(--id).navn;
		assertTrue(StepDefinitions.sm.checkSession(navn));
	}

	@Then("worker {int} is logged off")
	public void workerIsLoggedOff(Integer id) {
		String navn = StepDefinitions.app.workers.getUser(--id).navn;
		assertFalse(StepDefinitions.sm.checkSession(navn));
	}

	@Given("worker {int} is signed in")
	public void workerIsSignedIn(Integer id) {
		Medarbejder M = StepDefinitions.app.workers.getUser(--id);
		StepDefinitions.app.setMedarbejder(M);
		assertTrue(StepDefinitions.sm.checkSession(M.navn));
	}

	@When("worker {int} changes their password from {string} to {string}")
	public void workerChangesTheirPasswordFromTo(Integer id, String currentPWD, String newPWD) {
		Medarbejder M = StepDefinitions.app.workers.getUser(--id);
		M.changePassword(currentPWD, newPWD);
	}

	@Then("worker {int} has the password {string}")
	public void workerHasThePassword(Integer id, String PWD) {	
		Medarbejder M = StepDefinitions.app.workers.getUser(--id);
		assertTrue(M.password.equals(PWD));
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
