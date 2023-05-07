package test.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import client.ActivityClient;
import client.Application;
import client.ProjectClient;
import client.UserClient;
import server.ActivitySaveable;
import server.ProjectSaveable;
import server.ServerCore;
import server.UserSaveable;
import shared.AActivity;
import shared.AUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ActivitySteps {	

	@Given("project {string} exists")
	public void projectExists(String projname) {
		int id = ServerCore.projects.addProject(new ProjectSaveable(projname, 0));
		
		assertTrue(id!= -1);
		
		ProjectSaveable project = ServerCore.projects.getProject(projname);
		assertTrue(project!= null);
	}
	
	@When("project leader {string} creates activity {string} with {int} hours given in project {string}")
	public void projectLeaderCreatesActivityWithHoursGivenInProject(String employee, String actName, Integer estHours, String projName) {
	    String session = Application.getCurrentActiveSession();
		ProjectClient project = Application.serverAPI.projectGetProject(session, projName);
	    
		ActivityClient activity = new ActivityClient(project.CreateActivity(actName, estHours));
	}
	
	@Given("worker {string} is not registered as a project leader for the project {string}")
	public void workerIsNotRegisteredAsAProjectLeaderForTheProject(String employee, String projName) {
		String session = Application.getCurrentActiveSession();
		UserSaveable user = ServerCore.users.getUser(ServerCore.sessions.getUserIDOfSession(session));
		ProjectClient project = Application.serverAPI.projectGetProject(session, projName);
		
		//project.setProjectLeader(user);
	    
	    assertTrue(project.getProjectLeader()!= user);
	}
	
	@Then("the activity {string} with {int} hours allocated exists under project {string}")	
	public void theActivityWithHoursAllocatedExistsUnderProject(String actName, Integer estHours, String projName) {
		ProjectSaveable project = ServerCore.projects.getProject(projName);
		ActivitySaveable activity = (ActivitySaveable) project.getActivities(actName);
		
		assertTrue(activity.getName().equals(actName));
		assertTrue(activity.getEstTime() == estHours);
	}

	@Given("worker {string} is registered as a project leader for the project {string}")
	public void workerIsRegisteredAsAProjectLeaderForTheProject(String userName, String projName) {
		ProjectSaveable project = ServerCore.projects.getProject(projName);
		UserSaveable user = ServerCore.users.getUser(userName)[0];
		
		project.setProjectLeader(user);

	    assertTrue(project.getProjectLeader().equals(user));
	}

	@When("project leader {string} assigns worker {string} to activity {string} under project {string}")
	public void projectLeaderAssignsWorkerToActivityUnderProject(String projectLeaderName, String targetName, String actName, String projName) {
		ProjectClient project = Application.serverAPI.projectGetProject(Application.getCurrentActiveSession(), projName);
		if (project.getActivities(actName) != null) { 
			ActivityClient activity = new ActivityClient(project.getActivities(actName));
			UserClient targetUser = Application.serverAPI.userGetAllUsers(Application.getCurrentActiveSession(), targetName)[0];
			
			
			Application.serverAPI.activityAssignUsertoActivity(Application.getCurrentActiveSession(), activity, targetUser);
		}
	}
	
	@When("project leader {string} edits the expected time of activity {string}, under project {string}, to {int} hours")
	public void projectLeaderEditsTheExpectedTimeOfActivityUnderProjectToHours(String projectLeaderName, String actName, String projName, Integer hours) {
		ProjectClient project = Application.serverAPI.projectGetProject(Application.getCurrentActiveSession(), projName);
		ActivityClient activity = new ActivityClient(project.getActivities(actName));
		
		activity.editActivity(hours);
	}
	
	@When("project leader {string} removes the activity {string}, under project {string}")
	public void projectLeaderRemovesTheActivityUnderProject(String projectLeaderName, String actName, String projName) {
		ProjectClient project = Application.serverAPI.projectGetProject(Application.getCurrentActiveSession(), projName);
		ActivityClient activity = new ActivityClient(project.getActivities(actName));
		
		project.RemoveActivity(activity);
	}
	
	@Then("worker {string} is assigned to activity {string}")
	public void workerIsAssignedToActivity(String userName, String actName) {
		UserClient user = Application.serverAPI.userGetAllUsers(Application.getCurrentActiveSession(), userName)[0];
		
		ArrayList<AActivity> activities = user.getActivities();
		
		for (AActivity aActivity : activities) {
			if (aActivity.getName().equals(actName)) {
				assertTrue(true);
				return;
			}
		}
		assertTrue(false);
	}
	
	@When("the worker {string} adds {int} hours to activity {string} under project {string}")
	public void theWorkerAddsHoursToActivityUnderProject(String userName, Integer hours, String actName, String projName) {
		ProjectClient project = Application.serverAPI.projectGetProject(Application.getCurrentActiveSession(), projName);
		ActivityClient activity = new ActivityClient(project.getActivities(actName));
		UserClient user = Application.serverAPI.userGetAllUsers(Application.getCurrentActiveSession(), userName)[0];
		
		activity.RegisterHours(user, hours);
	}
	
	@Then("{int} hours is registered under worker {string} in activity {string}")
	public void hoursIsRegisteredUnderWorkerInActivityOfProject(Integer hours, String userName, String actName) {
		UserSaveable user = ServerCore.users.getUser(userName)[0];
		
		assertTrue(user.getActivities(actName).GetTotalTime(user) == hours);
	}
}
