package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import FXML.StartController;
import application.Application;
import core.common.Medarbejder;
import core.common.Projekt;
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
}
