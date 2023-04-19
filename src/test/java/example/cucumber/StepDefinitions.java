package example.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Application;
import core.sessions.SessionManager;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinitions {
	public static Application app = Application.singleton();
	public static SessionManager sm = new SessionManager();
	// TODO
	
	/*@ParameterType("red|blue|yellow")  // regexp
	public Color color(String color){  // type, name (from method)
	    return new Color(color);       // transformer function
	}*/
	
	/*@Then("the message {string} is returned")
	public void the_message_is_returned(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}*/
	
	@Given("a clean slate")
	public void aCleanSlate() {
		app.reset();
		sm = new SessionManager();
	}
}