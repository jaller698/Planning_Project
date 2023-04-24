package client;

import shared.AActivity;
import shared.AProject;

public class ProjectClient extends AProject {

	@Override
	public ActivityClient CreateActivity(String name) {
		// TODO Auto-generated method stub
		return (ActivityClient) super.CreateActivity(name);
	}
	
	
	@Override
	public AProject asBase() {
		// TODO Auto-generated method stub
		return null;
	}
}
