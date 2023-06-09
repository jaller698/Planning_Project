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


public class ProjectSteps {
	@Given("project {string} has an activity {string} registered")
	public void projectHasAnActivityRegistered(String string, String string2) {
		Project p = StepDefinitions.app.projects.getProject(string);
		new Activity(string2, 10, p);
		assertTrue(p.getActivity(string2).navn == string2);
	}
	
	@Given("project {string} has an activity {string} registered with {int} hours allocated")
	public void projectHasAnActivityRegisteredWithHoursAllocated(String string, String string2, Integer int1) {
		Project p = StepDefinitions.app.projects.getProject(string);
		new Activity(string2, int1, p);
		assertTrue(p.getActivity(string2).navn == string2);
	}
	
	@When("loggedin worker creates a project with the name {string}")
	public void workerCreatesAProjectWithTheName(String projectName) {
		StepDefinitions.app.getCurrentActiveUser().addProject(new Project(projectName));
	}

	@Then("the project {string} with {int}-digit serial no. from year and project number is created")
	public void theProjectWithDigitSerialNoFromYearAndProjectNumberIsCreated(String projName, int length) {
		Project p = StepDefinitions.app.projects.getProject(projName);
		assertTrue(p.toString().equals(projName));
		String len = "" + p.getID();
		assertTrue(len.length() == length);
	}
	@Then("a worker {string} is a project leader for project {string}")
	public void aWorkerIsAProjectLeaderForProject(String name, String projName) {
		Project p = null;
		Medarbejder m = StepDefinitions.app.workers.getUser(name);
		for (int i = 0; i < m.getProjects().size();i++){
			if(m.getProjects().get(i).toString().equals(projName))
				p = m.getProjects().get(i);
		}
		assertTrue(p != null);
		assertTrue(m != null);
		
	}

	@When("the administrator creates project with project name {string}")
	public void theAdministratorCreatesProjectWithProjectName(String projName) {
		new Project(projName);
	}

	@When("worker {string} joins activity {string} under project {string}")
	public void workerJoinsActivityUnderProject(String name, String aName, String pName) {
	    Project p = StepDefinitions.app.projects.getProject(pName);
	    Activity a = p.getActivity(aName);
	    Medarbejder M = StepDefinitions.app.workers.getUser(name);
	    a.addMedarbejder(M);
	}
	
	@Then("the project {string} has no activity named {string} under it")
	public void theProjectHasNoActivityNamedUnderIt(String string, String string2) {
		Project p = StepDefinitions.app.projects.getProject(string);
		assertTrue(p.getActivity(string2) == null);
	}
	@When("number {int} project titled {string} is created")
	public void numberProjectTitledIsCreated(Integer no, String Name) {
	    for(int i = 0; i<no; i++) {
	    	Project p = new Project("test");
	    }
	    Project p2 = new Project(Name);
	}
	@When("administrator creates a project with the name {string} and estimated time {int}")
	public void administratorCreatesAProjectWithTheNameAndEstimatedTime(String projname, Integer estTime) {
	    Project P = new Project(projname,estTime);
	}

	@When("administrator creates a project with the name {string}, with leader {string}, and estimated time {int}")
	public void administratorCreatesAProjectWithTheNameWithLeaderAndEstimatedTime(String projname, String leader, Integer estTime) {
	    Medarbejder m = StepDefinitions.app.workers.getUser(leader);
	    Project P = new Project(projname,m,estTime);
	}

}
