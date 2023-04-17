package example.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Application;
import application.Medarbejder;
import database.SessionManager;
import io.cucumber.java.en.*;

public class Login {
	int i = 0;
	@Given("a worker {int} is registered under the name {string} with the password {string}")
	public void aWorkerIsRegisteredUnderTheNameWithThePassword(Integer id, String name, String PWD) {
		Medarbejder newWorker = new Medarbejder(name,PWD);
		assertTrue(StepDefinitions.app.workers.getUser(name).navn.equals(newWorker.navn));
		assertTrue(StepDefinitions.app.workers.getUser(name).password.equals(newWorker.password));
		System.out.println("count" + i);
		i++;

		
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
		String navn = StepDefinitions.app.workers.getUser(id).navn;
		assertFalse(StepDefinitions.sm.checkSession(navn));
	}

	@Given("worker {int} is signed in")
	public void workerIsSignedIn(Integer id) {
		Medarbejder M = StepDefinitions.app.workers.getUser(id);
		StepDefinitions.app.setMedarbejder(M);
		assertTrue(StepDefinitions.sm.checkSession(M.navn));
	}

	@When("worker {int} changes their password from {string} to {string}")
	public void workerChangesTheirPasswordFromTo(Integer id, String currentPWD, String newPWD) {
		Medarbejder M = StepDefinitions.app.workers.getUser(id);
		M.changePassword(currentPWD, newPWD);
	}

	@Then("worker {int} has the password {string}")
	public void workerHasThePassword(Integer id, String PWD) {	
		Medarbejder M = StepDefinitions.app.workers.getUser(id);
		assertTrue(M.password.equals(PWD));
	}

	@Given("worker {int} is registered as an admin")
	public void workerIsRegisteredAsAnAdmin(Integer id) {
		Medarbejder M = StepDefinitions.app.workers.getUser(id);
		M.setAdmin(true);
		assertTrue(M.isAdmin());

	}

	@When("admin {int} changes worker {int} password from {string} to {string}")
	public void adminChangesWorkerPasswordFromTo(Integer aid, Integer mid, String currentPWD, String newPWD) {
		StepDefinitions.app.AdminChangePassword(aid, mid, newPWD);
	}

	@When("admin {int} registeres new worker {int} under the name {string} with the password {string}")
	public void adminRegisteresNewWorkerUnderTheNameWithThePassword(Integer aid, Integer mid, String Name,
			String Pwd) {
		new Medarbejder(Name,Pwd);
	}

	@Then("worker {int} exists")
	public void workerExists(Integer id) {
		assertTrue(StepDefinitions.app.workers.getUser(id) != null);
	}

	@Then("worker {int} has the name {string}")
	public void workerHasTheName(Integer id, String name) {
		Medarbejder B = StepDefinitions.app.workers.getUser(name);
		System.out.println(B.navn);
		System.out.println(StepDefinitions.app.workers.getUserID(B));
		assertTrue(id.equals(StepDefinitions.app.workers.getUserID(B)));
	}
}
