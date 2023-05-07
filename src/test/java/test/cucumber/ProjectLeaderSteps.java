package test.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Application;
import client.ProjectClient;
import client.UserClient;
import io.cucumber.java.en.*;
import server.ProjectSaveable;
import server.ServerCore;
import server.UserSaveable;

public class ProjectLeaderSteps {
	@Given("a worker {int} is registered under the name {string}")
	public void aWorkerIsRegisteredUnderTheName(Integer id, String name) {
	    /*Medarbejder M = new Medarbejder(name, "test123");
	    assertTrue((--id).equals(StepDefinitions.app.workers.getUserID(M)));*/
		
		UserSaveable user = new UserSaveable(name, name+"password");
		assertTrue((--id).equals(ServerCore.users.getUserID(user)));
	}

	@When("an admin {string} removes worker {string} as a project leader from project {string}")
	public void anAdminRemovesWorkerAsAProjectLeaderFromProject(String adminName, String userName, String projName) {
		ProjectClient proj = Application.serverAPI.projectGetProject(Application.getCurrentActiveSession(), projName);
		proj.setProjectLeader(null);
	}
	
	@Given("worker {string} is project leader for the project {string}")
	public void workerIsProjectLeaderForTheProject(String userName, String projName) {
		ProjectSaveable proj = ServerCore.projects.getProject(projName);
		UserSaveable user = ServerCore.users.getUser(userName)[0];
		
		proj.setProjectLeader(user);
	}

	@Given("the project {string} has no project leader assigned")
	public void theProjectHasNoProjectLeaderAssigned(String projName) {
		ProjectSaveable proj = ServerCore.projects.getProject(projName);
		
		assertTrue(proj.getProjectLeader() == null);
	}

	@When("an admin {string} sets worker {string} as a project leader for project {string}")
	public void anAdminSetsWorkerAsAProjectLeaderForProject(String adminName, String userName, String projName) {
		ProjectClient proj = Application.serverAPI.projectGetProject(Application.getCurrentActiveSession(), projName);
		UserClient user = Application.serverAPI.userGetAllUsers(Application.getCurrentActiveSession(), userName)[0];
		
		Application.serverAPI.projectAddProjectLeader(Application.getCurrentActiveSession(), proj.getId(), user.getId());
	}

}
