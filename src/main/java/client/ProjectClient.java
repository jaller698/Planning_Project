package client;

import shared.AActivity;
import shared.AProject;

public class ProjectClient extends AProject {

	public ProjectClient(String name) {
		super(name);
	}


	@Override
	public ActivityClient CreateActivity(String name) {
		// TODO Auto-generated method stub
		return (ActivityClient) super.CreateActivity(name);
	}
}
