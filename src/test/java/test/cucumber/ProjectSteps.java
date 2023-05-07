package test.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import client.ActivityClient;
import client.Application;
import client.ProjectClient;
import client.UserClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import server.ActivitySaveable;
import server.ProjectSaveable;
import server.ServerCore;
import server.UserSaveable;
import shared.AActivity;


public class ProjectSteps {
	@Given("project {string} has an activity {string} registered")
	public void projectHasAnActivityRegistered(String projName, String actName) {
		ProjectSaveable proj = ServerCore.projects.getProject(projName);
		proj.CreateActivity(actName, 5);
		
		assertTrue(proj.getActivities(actName) != null);
	}
	
	@Given("project {string} has an activity {string} registered with {int} hours allocated")
	public void projectHasAnActivityRegisteredWithHoursAllocated(String projName, String actName, Integer hours) {
		ProjectSaveable proj = ServerCore.projects.getProject(projName);
		ActivitySaveable act = new ActivitySaveable(proj.CreateActivity(actName, hours));
		
		assertTrue(proj.getActivities(act.getName()) != null);
	}
	
	@When("loggedin worker creates a project with the name {string}")
	public void workerCreatesAProjectWithTheName(String projName) {
		UserClient user = Application.serverAPI.userGetUserOfSession(Application.getCurrentActiveSession());
		
		Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), projName, 0, user);
	}

	@Then("the project {string} with {int}-digit serial no. from year and project number is created")
	public void theProjectWithDigitSerialNoFromYearAndProjectNumberIsCreated(String projName, int length) {
		ProjectSaveable proj = ServerCore.projects.getProject(projName);
		
		assertTrue(proj.getName().equals(projName));
		String len = "" + proj.getID();
		assertTrue(len.length() == length);
	}
	
	@Then("a worker {string} is a project leader for project {string}")
	public void aWorkerIsAProjectLeaderForProject(String userName, String projName) {
		ProjectSaveable proj = ServerCore.projects.getProject(projName);
		UserSaveable user = ServerCore.users.getUser(userName)[0];
		
		assertTrue(user.getName().equals(proj.getProjectLeader().getName()));		
	}

	@When("the administrator creates project with project name {string}")
	public void theAdministratorCreatesProjectWithProjectName(String projName) {
		Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), projName, 0, null);
	}

	@When("worker {string} joins activity {string} under project {string}")
	public void workerJoinsActivityUnderProject(String userName, String actName, String projName) {
		ProjectClient proj = Application.serverAPI.projectGetProject(Application.getCurrentActiveSession(), projName);
		UserClient user = Application.serverAPI.userGetAllUsers(Application.getCurrentActiveSession(), userName)[0];
		ActivityClient activity = null;
		
		ArrayList<AActivity> activities = proj.getActivities();

		for (AActivity aActivity : activities) {
			if (aActivity.getName().equals(actName)) {
				activity = new ActivityClient(aActivity);
			}
		}
		
		Application.serverAPI.activityAssignUsertoActivity(Application.getCurrentActiveSession(), activity, user);
	}
	
	@Then("the project {string} has no activity named {string} under it")
	public void theProjectHasNoActivityNamedUnderIt(String projName, String actName) {
		ProjectSaveable proj = ServerCore.projects.getProject(projName);

		assertTrue(proj.getActivities(actName) == null);
	}
	
	@When("number {int} project titled {string} is created")
	public void numberProjectTitledIsCreated(Integer no, String projName) {
	    for(int i = 0; i<no; i++) {
	    	ProjectSaveable proj = ServerCore.projects.getProject("test");
	    }
	    ProjectSaveable proj2 = ServerCore.projects.getProject(projName);
	}
	
	@When("administrator creates a project with the name {string} and estimated time {int}")
	public void administratorCreatesAProjectWithTheNameAndEstimatedTime(String projName, Integer estTime) {
		Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), projName, estTime, null);
	}

	@When("administrator creates a project with the name {string}, with leader {string}, and estimated time {int}")
	public void administratorCreatesAProjectWithTheNameWithLeaderAndEstimatedTime(String projName, String leader, Integer estTime) {
		UserClient target = Application.serverAPI.userGetAllUsers(Application.getCurrentActiveSession(), leader)[0];
		
		Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), projName, estTime, target);
	}

}
