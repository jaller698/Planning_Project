package example.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Medarbejder;
import application.Project;
import io.cucumber.java.en.*;

public class ProjectLeaderSteps {
	@Given("a worker {int} is registered under the name {string}")
	public void aWorkerIsRegisteredUnderTheName(Integer id, String name) {
	    Medarbejder M = new Medarbejder(name, "test123");
	    assertTrue((--id).equals(StepDefinitions.app.workers.getUserID(M)));
	}

	@When("an admin {string} removes worker {string} as a project leader from project {string}")
	public void anAdminRemovesWorkerAsAProjectLeaderFromProject(String aName, String eName, String pName) {
	    Project p = StepDefinitions.app.projects.getProject(pName);
	    p.addProjectLeder(null);
	}
	@Given("worker {string} is project leader for the project {string}")
	public void workerIsProjectLeaderForTheProject(String eName, String pName) {
		Project p = StepDefinitions.app.projects.getProject(pName);
		Medarbejder M = StepDefinitions.app.workers.getUser(eName);
	    p.addProjectLeder(M);
	}

	@Given("the project {string} has no project leader assigned")
	public void theProjectHasNoProjectLeaderAssigned(String pName) {
	    Project p = StepDefinitions.app.projects.getProject(pName);
	    System.out.println(p.getProjLeder().navn);
	    p.addProjectLeder(null);
	    assertTrue(p.getProjLeder() == null);
	}

	@When("an admin {string} sets worker {string} as a project leader for project {string}")
	public void anAdminSetsWorkerAsAProjectLeaderForProject(String aName, String eName, String pName) {
		Project p = StepDefinitions.app.projects.getProject(pName);
		Medarbejder M = StepDefinitions.app.workers.getUser(eName);
	    p.addProjectLeder(M);
	}

}
