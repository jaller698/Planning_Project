package shared;

import java.util.ArrayList;

public class AProject { // {Written by Jaller698, GaySupremacy and McQueen24, refactored by Perry02 (Original file: Projekt.java)}
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
	

	
	protected AProject(String name, int estTime) { // {Written by GaySupremacy}
		this.name = name;
		this.estTime = estTime;
	}
	
	public AProject getBase() { // {Written by Perry02}
		return this;
	}
	
	@Override
	public String toString() { // {Written by GaySupremacy}
		return "("+id+")"+name;
	}
	
	// create an activity
	public AActivity CreateActivity(String name, int estTime) { // {Written by Perry02}
		AActivity activity = new AActivity(this, name, estTime);
		
		return activity;
	}
	
	  // add a given activity to this project if it does not exist here
	public void AddActivity(AActivity activity) { // {Written by GaySupremacy}
		if (activity.getProject() == this) {
			if (!activities.contains(activity)) {
				activities.add(activity);
			}
		} else {
			MoveActivity(activity, this);
		}
	}
	
	// moves an activity to another project
	public void MoveActivity(AActivity activity, AProject destination) { // {Written by Perry02}
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
		// TODO
	}
}
