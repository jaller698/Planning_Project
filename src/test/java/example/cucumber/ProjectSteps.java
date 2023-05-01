package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Activity;
import application.Application;
import application.Medarbejder;
import application.Projekt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProjectSteps {
	@Given("project {string} has an activity {string} registered")
	public void projectHasAnActivityRegistered(String string, String string2) {
		Projekt p = StepDefinitions.app.projects.getProject(string);
		new Activity(string2, 10, p);
		assertTrue(p.getActivity(string2).navn == string2);
	}
	
	@Given("project {string} has an activity {string} registered with {int} hours allocated")
	public void projectHasAnActivityRegisteredWithHoursAllocated(String string, String string2, Integer int1) {
		Projekt p = StepDefinitions.app.projects.getProject(string);
		new Activity(string2, int1, p);
		assertTrue(p.getActivity(string2).navn == string2);
	}
	
	@When("loggedin worker creates a project with the name {string}")
	public void workerCreatesAProjectWithTheName(String projectName) {
		StepDefinitions.app.getCurrentActiveUser().addProjekt(new Projekt(projectName));
	}

	@Then("the project {string} with {int}-digit serial no. from year and project number is created")
	public void theProjectWithDigitSerialNoFromYearAndProjectNumberIsCreated(String projName, int length) {
		Projekt p = StepDefinitions.app.projects.getProject(projName);
		assertTrue(p.toString().equals(projName));
		String len = "" + p.getID();
		assertTrue(len.length() == length);
	}
	@Then("a worker {string} is a project leader for project {string}")
	public void aWorkerIsAProjectLeaderForProject(String name, String projName) {
		Projekt p = null;
		Medarbejder m = StepDefinitions.app.workers.getUser(name);
		for (int i = 0; i < m.getProjekts().size();i++){
			if(m.getProjekts().get(i).toString().equals(projName))
				p = m.getProjekts().get(i);
		}
		assertTrue(p != null);
		assertTrue(m != null);
		
	}

	@When("the administrator creates project with project name {string}")
	public void theAdministratorCreatesProjectWithProjectName(String projName) {
		new Projekt(projName);
	}

	@When("worker {string} joins activity {string} under project {string}")
	public void workerJoinsActivityUnderProject(String name, String aName, String pName) {
	    Projekt p = StepDefinitions.app.projects.getProject(pName);
	    Activity a = p.getActivity(aName);
	    Medarbejder M = StepDefinitions.app.workers.getUser(name);
	    a.addMedarbejder(M);
	}
	
	@Then("the project {string} has no activity named {string} under it")
	public void theProjectHasNoActivityNamedUnderIt(String string, String string2) {
		Projekt p = StepDefinitions.app.projects.getProject(string);
		assertTrue(p.getActivity(string2) == null);
	}
}
