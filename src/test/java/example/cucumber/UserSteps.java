package example.cucumber;

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
	Application app = new Application();
	
	@Given("a worker {string} is registered")
	public void aWorkerIsRegistered(String s) throws Exception {
		Medarbejder newWorker = new Medarbejder(
		s, // User name
		"Cucumber123" // Password
		);
		assertTrue(Application.alleMedarbejdere.get(0).navn.equals(newWorker.navn));
		app.setMedarbejder(newWorker);
	    //throw new io.cucumber.java.PendingException("the user register is missing an {int} identifier");
	}
	
	@Given("worker {string} is signed in")
	public void workerIsSignedIn(String employee) {
	    assertTrue(app.getMedarbejder().navn.equals(employee));
	}
	@Given("worker {string} is registered as an admin")
	public void workerIsRegisteredAsAnAdmin(String employee) {
		Medarbejder m = null;
		for (int i = 0; i < app.alleMedarbejdere.size();i++) {
			if(app.alleMedarbejdere.get(i).navn.equals(employee))
				m = app.alleMedarbejdere.get(i);
		}
		m.setAdmin(true);
		assertTrue(m.isAdmin());
	}
	@Then("the project {string} has no project leader")
	public void theProjectHasNoProjectLeader(String projname) {
		Projekt p = null;
		for(int i = 0; i < app.alleProjekter.size(); i++) {
			if(projname.equals(app.alleProjekter.get(i).toString()))
				p = app.alleProjekter.get(i);
		}
		p.getProjLeder();
	}

}
