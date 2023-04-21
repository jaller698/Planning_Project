package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Application;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sharedCore.Aktivitet;
import sharedCore.Medarbejder;
import sharedCore.Projekt;


public class ActivitySteps {	

	@Given("project {string} exists")
	public void projectExists(String projname) {
		new Projekt(projname);
		Projekt p = StepDefinitions.app.projects.getProject(projname);
		assertTrue( p!= null);
	}
	@When("project leader {string} creates activity {string} with {int} hours given in project {string}")
	public void projectLeaderCreatesActivityWithHoursGivenInProject(String employee, String actName, Integer estHours, String projName) {
	    Projekt p = StepDefinitions.app.projects.getProject(projName);
		new Aktivitet(actName,estHours,p);
	    
	}
	@Given("worker {string} is not registered as a project leader for the project {string}")
	public void workerIsNotRegisteredAsAProjectLeaderForTheProject(String employee, String projName) {
	    Projekt p = StepDefinitions.app.projects.getProject(projName);
	    p.addProjektLeder(new Medarbejder("Jens", "test123"));
	}
	@Then("the activity {string} with {int} hours allocated exists under project {string}")
	public void theActivityWithHoursAllocatedExistsUnderProject(String actName, Integer estHours, String projName) {
	    Projekt p = StepDefinitions.app.projects.getProject(projName);
	    Aktivitet a = p.getAktivitet(actName);
	    assertTrue(a.navn.equals(actName));
	    assertTrue(a.getEstHours() == estHours);
	}

	@Given("worker {string} is registered as a project leader for the project {string}")
	public void workerIsRegisteredAsAProjectLeaderForTheProject(String employee, String projname) {
	    Medarbejder m = StepDefinitions.app.workers.getUser(employee);
		Projekt p = StepDefinitions.app.projects.getProject(projname);
	    assertTrue(p.getProjLeder().equals(m));
	}

	@When("project leader {string} assigns worker {string} to activity {string} under project {string}")
	public void projectLeaderAssignsWorkerToActivityUnderProject(String string, String string2, String string3, String string4) {
		StepDefinitions.app.projects.getProject(string4).assignActivity(string3, StepDefinitions.app.workers.getUser(string2), StepDefinitions.app.workers.getUser(string));
	}
	
	@When("project leader {string} edits the expected time of activity {string}, under project {string}, to {int} hours")
	public void projectLeaderEditsTheExpectedTimeOfActivityUnderProjectToHours(String string, String string2, String string3, Integer int1) {
		Aktivitet a = StepDefinitions.app.projects.getProject(string3).getAktivitet(string2);
		Medarbejder m = StepDefinitions.app.workers.getUser(string);
		
		a.editActivity(int1);
	}
	
	@When("project leader {string} removes the activity {string}, under project {string}")
	public void projectLeaderRemovesTheActivityUnderProject(String string, String string2, String string3) {
		Projekt p = StepDefinitions.app.projects.getProject(string3);
		Medarbejder m = StepDefinitions.app.workers.getUser(string);
		
		p.removeActivity(string2, m);
	}
	
	@Then("worker {string} is assigned to activity {string}")
	public void workerIsAssignedToActivity(String string, String string2) {
		Aktivitet a = StepDefinitions.app.workers.getUser(string).getAktivitet(string2);
		if (a != null) {
			assertTrue(a.navn.equals(string2));
		} else {
			assertTrue(false);
		}
	}
	
	@When("the worker {string} adds {int} hours to activity {string} under project {string}")
	public void theWorkerAddsHoursToActivityUnderProject(String string, Integer int1, String string2, String string3) {
		Medarbejder m = StepDefinitions.app.workers.getUser(string);
		
		StepDefinitions.app.projects.getProject(string3).getAktivitet(string2).addTime(int1, m);
	}
	
	@Then("{int} hours is registered under worker {string} in activity {string}")
	public void hoursIsRegisteredUnderWorkerInActivityOfProject(Integer int1, String string, String string2) {
		Medarbejder m = StepDefinitions.app.workers.getUser(string);
		
		assertTrue(m.getAktivitet(string2).getTimeDoneByUser(m) == int1);
	}
}
