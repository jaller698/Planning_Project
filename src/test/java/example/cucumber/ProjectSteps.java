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


public class ProjectSteps {
	@Given("project {string} has an activity {string} registered")
	public void projectHasAnActivityRegistered(String string, String string2) {
		Projekt p = StepDefinitions.app.projects.getProject(string);
		new Aktivitet(string2, 10, p);
		assertTrue(p.getAktivitet(string2).navn == string2);
	}
	
	@When("loggedin worker creates a project with the name {string}")
	public void workerCreatesAProjectWithTheName(String projectName) {
		StepDefinitions.app.getMedarbejder().addProjekt(new Projekt(projectName));
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
		Medarbejder m = StepDefinitions.app.findEmployee(name);
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
}
