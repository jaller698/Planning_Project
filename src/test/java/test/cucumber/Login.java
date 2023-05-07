package test.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Application;
import client.UserClient;
import deprecated.Medarbejder;
import io.cucumber.java.en.*;
import server.ServerCore;
import server.SessionManager;
import server.UserSaveable;

public class Login {
	int i = 0;
	@Given("a worker {int} is registered under the name {string} with the password {string}")
	public void aWorkerIsRegisteredUnderTheNameWithThePassword(Integer id, String name, String PWD) {
		UserClient user = Application.serverAPI.userSignUp(name, PWD);
		
		assertTrue(ServerCore.users.getUser(user.getId()).getName().equals(name));
		assertTrue(ServerCore.users.getUser(user.getId()).getPassword().equals(PWD));
		
		System.out.println("count" + i);
		i++;
	}

	@Given("worker {int} is signed off")
	public void workerIsSignedOff(Integer id) {
		assertFalse(ServerCore.sessions.getUserIDOfSession(Application.getCurrentActiveSession()) >= 0);
	}

	@When("worker {int} logs on with username {string} and password {string}")
	public void workerLogsOnWithUsernameAndPassword(Integer id, String name, String PWD) {
		String session = Application.serverAPI.userLogIn(name, PWD);
		
		Application.setCurrentActiveSession(session);
	}

	@Then("worker {int} is logged on")
	public void workerIsLoggedOn(Integer id) {
		assertFalse(ServerCore.sessions.getUserIDOfSession(Application.getCurrentActiveSession()) == -1);
		assertTrue(Application.getCurrentActiveSession() != null);
	}

	@Then("worker {int} is logged off")
	public void workerIsLoggedOff(Integer id) {
		assertFalse(ServerCore.sessions.getUserIDOfSession(Application.getCurrentActiveSession()) >= 0);
		assertTrue(Application.getCurrentActiveSession() == null);
	}

	@Given("worker {int} is signed in")
	public void workerIsSignedIn(Integer id) {
		UserSaveable user = ServerCore.users.getUser(--id);
		
		String session = Application.serverAPI.userLogIn(user.getName(), user.getPassword());
		Application.setCurrentActiveSession(session);
		
		assertTrue(!Application.getCurrentActiveSession().isBlank());
	}

	@When("worker {int} changes their password from {string} to {string}")
	public void workerChangesTheirPasswordFromTo(Integer id, String currentPWD, String newPWD) {
		Application.serverAPI.userUpdateProfile(Application.getCurrentActiveSession(), --id, "", newPWD);
	}

	@Then("worker {int} has the password {string}")
	public void workerHasThePassword(Integer id, String PWD) {	
		UserSaveable user = ServerCore.users.getUser(--id);
		
		assertTrue(user.getPassword().equals(PWD));
	}

	@Given("worker {int} is registered as an admin")
	public void workerIsRegisteredAsAnAdmin(Integer id) {
		UserSaveable user = ServerCore.users.getUser(--id);
		user.setAdmin(true);
		
		assertTrue(user.isAdmin());
	}

	@When("admin {int} changes worker {int} password from {string} to {string}")
	public void adminChangesWorkerPasswordFromTo(Integer aid, Integer mid, String currentPWD, String newPWD) {
		UserClient targetUser = Application.serverAPI.userGetUser(Application.getCurrentActiveSession(), --mid);
		UserClient admin = Application.serverAPI.userGetUser(Application.getCurrentActiveSession(), --aid);
		
		Application.serverAPI.userUpdateProfile(Application.getCurrentActiveSession(), targetUser.getId(), "", newPWD);
	}

	@When("admin {int} registeres new worker {int} under the name {string} with the password {string}")
	public void adminRegisteresNewWorkerUnderTheNameWithThePassword(Integer aid, Integer mid, String Name, String Pwd) {
		UserClient user = Application.serverAPI.userSignUp(Name, Pwd);
	}

	@Then("worker {int} exists")
	public void workerExists(Integer id) {
		assertTrue(ServerCore.users.getUser(--id) != null);
	}

	@Then("worker {int} has the name {string}")
	public void workerHasTheName(Integer id, String name) {
		UserSaveable B = ServerCore.users.getUser(name)[0];
		assertTrue((--id).equals(ServerCore.users.getUserID(B)));
	}
}
