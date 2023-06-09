package test.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Activity;
import application.Application;
import application.Medarbejder;
import application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class ActivitySteps {	

	@Given("project {string} exists")
	public void projectExists(String projname) {
		new Project(projname);
		Project p = StepDefinitions.app.projects.getProject(projname);
		assertTrue( p!= null);
	}
	@When("project leader {string} creates activity {string} with {int} hours given in project {string}")
	public void projectLeaderCreatesActivityWithHoursGivenInProject(String employee, String actName, Integer estHours, String projName) {
	    Project p = StepDefinitions.app.projects.getProject(projName);
		new Activity(actName,estHours,p);
	    
	}
	@Given("worker {string} is not registered as a project leader for the project {string}")
	public void workerIsNotRegisteredAsAProjectLeaderForTheProject(String employee, String projName) {
	    Project p = StepDefinitions.app.projects.getProject(projName);
	    p.addProjectLeader(new Medarbejder("Jens", "test123"));
	}
	@Then("the activity {string} with {int} hours allocated exists under project {string}")
	public void theActivityWithHoursAllocatedExistsUnderProject(String actName, Integer estHours, String projName) {
	    Project p = StepDefinitions.app.projects.getProject(projName);
	    Activity a = p.getActivity(actName);
	    assertTrue(a.navn.equals(actName));
	    assertTrue(a.getEstHours() == estHours);
	}

	@Given("worker {string} is registered as a project leader for the project {string}")
	public void workerIsRegisteredAsAProjectLeaderForTheProject(String employee, String projname) {
	    Medarbejder m = StepDefinitions.app.workers.getUser(employee);
		Project p = StepDefinitions.app.projects.getProject(projname);
	    assertTrue(p.getProjLeader().equals(m));
	}

	@When("project leader {string} assigns worker {string} to activity {string} under project {string}")
	public void projectLeaderAssignsWorkerToActivityUnderProject(String string, String string2, String string3, String string4) {
		StepDefinitions.app.projects.getProject(string4).assignActivity(string3, StepDefinitions.app.workers.getUser(string2), StepDefinitions.app.workers.getUser(string));
	}
	
	@When("project leader {string} edits the expected time of activity {string}, under project {string}, to {int} hours")
	public void projectLeaderEditsTheExpectedTimeOfActivityUnderProjectToHours(String string, String string2, String string3, Integer int1) {
		Activity a = StepDefinitions.app.projects.getProject(string3).getActivity(string2);
		Medarbejder m = StepDefinitions.app.workers.getUser(string);
		
		a.editActivity(int1);
	}
	
	@When("project leader {string} removes the activity {string}, under project {string}")
	public void projectLeaderRemovesTheActivityUnderProject(String string, String string2, String string3) {
		Project p = StepDefinitions.app.projects.getProject(string3);
		Medarbejder m = StepDefinitions.app.workers.getUser(string);
		
		p.removeActivity(string2, m);
	}
	
	@Then("worker {string} is assigned to activity {string}")
	public void workerIsAssignedToActivity(String string, String string2) {
		Activity a = StepDefinitions.app.workers.getUser(string).getActivity(string2);
		if (a != null) {
			assertTrue(a.navn.equals(string2));
		} else {
			assertTrue(false);
		}
	}
	
	@When("the worker {string} adds {int} hours to activity {string} under project {string}")
	public void theWorkerAddsHoursToActivityUnderProject(String string, Integer int1, String string2, String string3) {
		Medarbejder m = StepDefinitions.app.workers.getUser(string);
		
		StepDefinitions.app.projects.getProject(string3).getActivity(string2).addTime(int1, m);
	}
	
	@Then("{int} hours is registered under worker {string} in activity {string}")
	public void hoursIsRegisteredUnderWorkerInActivityOfProject(Integer int1, String string, String string2) {
		Medarbejder m = StepDefinitions.app.workers.getUser(string);
		
		assertTrue(m.getActivity(string2).getTimeDoneByUser(m) == int1);
	}
}
