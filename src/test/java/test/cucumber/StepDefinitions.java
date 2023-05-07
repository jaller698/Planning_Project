package test.cucumber;

import client.Application;
import io.cucumber.java.en.Given;
import server.ServerCore;

public class StepDefinitions {	
	@Given("a clean slate")
	public void aCleanSlate() {
		Application.singleton().reset();
		ServerCore.singleton().reset();
	}
}