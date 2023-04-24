package shared;

import client.ActivityClient;

public class AProject {
	private String name; // the project name
	public String getName() {return name;}

	private String id; // the projectID
	public String getId() {return id;}

	private AUser projectLeader; // the projectLeader
	public AUser getProjectLeader() {return projectLeader;}
	
	private AActivity[] activities = new AActivity[5];
	
	
	
	public AProject(String name) {
		this.name = name;
	}
	
	public AProject asBase() {
		return this;
	}
	
	public AActivity CreateActivity(String name) {
		AActivity activity = new AActivity(this, name);
		
		AddActivity(activity);
		
		return activity;
	}
	
	public void AddActivity(AActivity activity) {
		
	}
}
