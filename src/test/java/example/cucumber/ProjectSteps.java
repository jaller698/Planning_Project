package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import application.Application;
import application.Medarbejder;
import application.Projekt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProjectSteps {
	private Application app = new Application();
	
	@When("loggedin worker creates a project with the name {string}")
	public void workerCreatesAProjectWithTheName(String projectName) {
		app.getMedarbejder().addProjekt(new Projekt(projectName));
	}

	@Then("the project {string} with {int}-digit serial no. from year and project number is created")
	public void theProjectWithDigitSerialNoFromYearAndProjectNumberIsCreated(String projName, int length) {
		Projekt p = app.alleProjekter.get(app.alleProjekter.size()-1);
		assertTrue(p.toString().equals(projName));
		String len = "" + p.getID();
		assertTrue(len.length() == length);
	}
	@Then("a worker {string} is a project leader for project {string}")
	public void aWorkerIsAProjectLeaderForProject(String name, String projName) {
		Projekt p = null;
		Medarbejder m = app.findEmployee(name);
		for (int i = 0; i < m.getProjekts().size();i++){
			if(m.getProjekts().get(i).toString().equals(projName))
				p = m.getProjekts().get(i);
		}
		assertTrue(p != null);
		assertTrue(m != null);
		
	}
	@Then("the message {string} is returned")
	public void theMessageIsReturned(String string) {
	    String msg = app.getConfirmationMSG();
	    System.out.println(msg);
	    assertTrue(string.equals(msg));
	}
	@When("the administrator creates project with project name {string}")
	public void theAdministratorCreatesProjectWithProjectName(String projName) {
		new Projekt(projName);
	}
}
