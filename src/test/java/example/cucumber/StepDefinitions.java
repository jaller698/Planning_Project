package example.cucumber;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;

public class StepDefinitions {
	
	// TODO
	
	/*@ParameterType("red|blue|yellow")  // regexp
	public Color color(String color){  // type, name (from method)
	    return new Color(color);       // transformer function
	}*/
	
	@Then("the message {string} is returned")
	public void the_message_is_returned(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}