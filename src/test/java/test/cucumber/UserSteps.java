package test.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Application;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import server.ProjectSaveable;
import server.ServerCore;
import server.UserSaveable;
import shared.AUser;


public class UserSteps {	
	@Given("a worker {string} is registered")
	public void aWorkerIsRegistered(String userName) throws Exception {
		Application.serverAPI.userSignUp(
				userName, // User name
				userName+"password" // Password
				);

		assertTrue(ServerCore.users.getUser(userName)[0].getName().equals(userName));
	}
	
	@Given("worker {string} is signed in")
	public void workerIsSignedIn(String userName) {
		UserSaveable user = ServerCore.users.getUser(userName)[0];
		String session = Application.serverAPI.userLogIn(userName, user.getPassword());
		
		Application.setCurrentActiveSession(session);
		
	    assertTrue(ServerCore.users.getUser(ServerCore.sessions.getUserIDOfSession(session)).getName().equals(userName));
	}
	
	@Given("worker {string} is registered as an admin")
	public void workerIsRegisteredAsAnAdmin(String userName) {
		UserSaveable user = ServerCore.users.getUser(userName)[0];
		user.setAdmin(true);

		assertTrue(user.isAdmin());
	}
	
	
	
	@Then("the project {string} has no project leader")
	public void theProjectHasNoProjectLeader(String projname) {
		ProjectSaveable proj = ServerCore.projects.getProject(projname);
		UserSaveable leader = (UserSaveable) proj.getProjectLeader();
		
		assertTrue(leader == null);
	}
	
	@Then("worker {string} has no assigned activities")
	public void workerHasNoAssignedActivities(String userName) {
		UserSaveable user = ServerCore.users.getUser(userName)[0];

		assertTrue(user.getActivities().isEmpty());
	}
	
	@Then("the message {string} is returned")
	public void theMessageIsReturned(String string) {
	    String msg = Application.getConfirmationMSG();
		System.out.println(string + msg);
	    assertTrue(string.equals(msg));
	}
	
	@When("the user {string} is created")
	public void theUserIsCreated(String userName) {
		Application.serverAPI.userSignUp(
				userName, // User name
				userName+"password" // Password
				);
	}
	
	AUser m2;
	@When("the duplicate user {string} is created")
	public void theDuplicateUserIsCreated(String userName) {
		m2 = Application.serverAPI.userSignUp(
				userName, // User name
				userName+"password" // Password
				);
	}

	@Then("{string} is assigned an id")
	public void isAssignedAnId(String userName) {
		UserSaveable user = ServerCore.users.getUser(userName)[0];
		int id = ServerCore.users.getUserID(user);
		
	    System.out.println(userName + "id: " + id);
	    assertTrue(id != -1);
	}

	@Then("{string} is not assigned an id")
	public void isNotAssignedAnId(String name) {
		
	    int id = ServerCore.users.getUserID(new UserSaveable(m2));
	    System.out.println(name + "id: " + id);
	    assertTrue(id == -1);
	}
}
