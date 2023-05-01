package shared;

import java.util.ArrayList;

import client.ActivityClient;

public class AProject {
	protected String name; // the project name
	public String getName() {return name;}

	protected Integer id; // the projectID
	public Integer getId() {return id;}
	
	protected int estTime; // the estimated time for this project
	public int getEstTime() {return estTime;}

	protected AUser projectLeader; // the projectLeader
	public AUser getProjectLeader() {return projectLeader;}
	
	protected ArrayList<AActivity> activities = new ArrayList<AActivity>(); // the activities tied to this project
	public ArrayList<AActivity> getActivities() {return activities;}
	

	
	protected AProject(String name, int estTime) {
		this.name = name;
		this.estTime = estTime;
	}
	
	public AProject getBase() {
		return this;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "("+id+")"+name;
	}
	
	public AActivity CreateActivity(String name, int estTime) { // create an activity
		AActivity activity = new AActivity(this, name, estTime);
		
		return activity;
	}
	
	public void AddActivity(AActivity activity) { // add a given activity to this project if it does not exist here
		if (activity.getProject() == this) {
			if (!activities.contains(activity)) {
				activities.add(activity);
			}
		} else {
			MoveActivity(activity, this);
		}
	}
	
	public void MoveActivity(AActivity activity, AProject destination) { // moves an activity to another project
		System.out.println("Project("+ this +"): Move activity from: " + activity + " to " + destination);
		if (destination == this) {
			if (activity.getProject() != destination) {
				activity.getProject().MoveActivity(activity, destination);
			} else {
				activities.add(activity);
			}
			
		} else if (activities.contains(activity)) {
			activities.remove(activity);
			if (activity.getProject() != destination) {
				activity.SwitchProject(destination);
			}
		}
	}
	
	public void RemoveActivity(AActivity activity) {
		
	}
}
