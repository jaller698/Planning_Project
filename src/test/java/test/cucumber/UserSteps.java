package test.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import FXML.StartController;
import application.Application;
import application.Medarbejder;
import application.Projekt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class UserSteps {	
	@Given("a worker {string} is registered")
	public void aWorkerIsRegistered(String s) throws Exception {
		Medarbejder newWorker = new Medarbejder(
		s, // User name
		"Cucumber123" // Password
		);
		StepDefinitions.app.workers.addUser(newWorker);
		
		assertTrue(StepDefinitions.app.workers.getUser(s).navn.equals(newWorker.navn));
	    //throw new io.cucumber.java.PendingException("the user register is missing an {int} identifier");
	}
	
	@Given("worker {string} is signed in")
	public void workerIsSignedIn(String employee) {
		Medarbejder m = StepDefinitions.app.workers.getUser(employee);
		StepDefinitions.app.setCurrentActiveUser(m);
	    assertTrue(StepDefinitions.app.getCurrentActiveUser().navn.equals(employee));
	}
	@Given("worker {string} is registered as an admin")
	public void workerIsRegisteredAsAnAdmin(String employee) {
		Medarbejder m = StepDefinitions.app.workers.getUser(employee);
		m.setAdmin(true);
		assertTrue(m.isAdmin());
	}
	@Given("worker {string} is a project leader")
	public void workerIsAProjectLeader(String employee) {
	    Medarbejder m = StepDefinitions.app.workers.getUser(employee);
	    m.setProjectLeader(true);
	    assertTrue(m.isProjectleader());
	}
	@Then("the project {string} has no project leader")
	public void theProjectHasNoProjectLeader(String projname) {
		Projekt p = StepDefinitions.app.projects.getProject(projname);
		p.getProjLeder();
	}
	
	@Then("worker {string} has no assigned activities")
	public void workerHasNoAssignedActivities(String string) {
		assertTrue(StepDefinitions.app.workers.getUser(string).a.size() == 0);
	}
	@Then("the message {string} is returned")
	public void theMessageIsReturned(String string) {
	    String msg = StepDefinitions.app.getConfirmationMSG();
		System.out.println(string + msg);
	    assertTrue(string.equals(msg));
	}
	
	@When("the user {string} is created")
	public void theUserIsCreated(String name) {
	    Medarbejder m = new Medarbejder(name, "test123");
	}
	Medarbejder m2;
	@When("the duplicate user {string} is created")
	public void theDuplicateUserIsCreated(String name) {
	    m2 = new Medarbejder(name, "test123");
	}

	@Then("{string} is assigned an id")
	public void isAssignedAnId(String name) {
	    Medarbejder m = StepDefinitions.app.workers.getUser(name);
	    int id = StepDefinitions.app.workers.getUserID(m);
	    assertTrue(id != -1);
	}

	@Then("{string} is not assigned an id")
	public void isNotAssignedAnId(String name) {
	    int id = StepDefinitions.app.workers.getUserID(m2);
	    assertTrue(id == -1);
	}
}
