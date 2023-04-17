package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Aktivitet;
import application.Application;
import application.Medarbejder;
import application.Projekt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ActivitySteps {	

	@Given("project {string} exists")
	public void projectExists(String projname) {
		new Projekt(projname);
		Projekt p = StepDefinitions.app.findProject(projname);
		assertTrue( p!= null);
	}
	@When("project leader {string} creates activity {string} with {int} hours given in project {string}")
	public void projectLeaderCreatesActivityWithHoursGivenInProject(String employee, String actName, Integer estHours, String projName) {
	    Projekt p = StepDefinitions.app.findProject(projName);
		new Aktivitet(actName,estHours,p);
	    
	}
	@Given("worker {string} is not registered as a project leader for the project {string}")
	public void workerIsNotRegisteredAsAProjectLeaderForTheProject(String employee, String projName) {
	    Projekt p = StepDefinitions.app.findProject(projName);
	    p.addProjektLeder(new Medarbejder("Jens", "test123"));
	}
	@Then("the activity {string} with {int} hours allocated exists under project {string}")
	public void theActivityWithHoursAllocatedExistsUnderProject(String actName, Integer estHours, String projName) {
	    Projekt p = StepDefinitions.app.findProject(projName);
	    Aktivitet a = p.getAktivitet(actName);
	    assertTrue(a.navn.equals(actName));
	    assertTrue(a.getEstHours() == estHours);
	}

	@Given("worker {string} is registered as a project leader for the project {string}")
	public void workerIsRegisteredAsAProjectLeaderForTheProject(String employee, String projname) {
	    Medarbejder m = StepDefinitions.app.findEmployee(employee);
		Projekt p = StepDefinitions.app.findProject(projname);
	    assertTrue(p.getProjLeder().equals(m));
	}



}
