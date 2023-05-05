package shared;

import java.util.ArrayList;

public abstract class AUser { // {Written by GaySupremacy and Perry02, refactored by Perry02 (Original file: Medarbejder.java)}
	protected String name; // the users name
	public String getName() {return name;}
	
	protected Integer id; // the users id
	public Integer getId() {return id;}
	
	protected ArrayList<AProject> projects = new ArrayList<AProject>(); // the users projects
	public ArrayList<AProject> getProjects() {return projects;} // {Written by GaySupremacy}
	
	protected ArrayList<AActivity> activities = new ArrayList<AActivity>(); // the users activities
	public ArrayList<AActivity> getActivities() {return activities;} // {Written by GaySupremacy}
	
	
	
	protected AUser() {}
	
	public AUser getBase() { // {Written by Perry02}
		return this;
	}
	
	@Override
	public String toString() { // {Written by GaySupremacy}
		return "("+id+"):"+name;
	}
	
	// assign a project to this user
	public abstract void AssignProject(AProject project);
	
	// remove a project to this user
	public abstract void UnAssignProject(AProject project);
	
	// assign an activity to this user
	public abstract void AssignActivity(AActivity activity);
	
	// remove an activity to this user
	public abstract void UnAssignActivity(AActivity activity);
	
	// register hours for an activity for this user
	public abstract void RegisterHours(AActivity activity, int hours);
	
	public abstract int GetTotalTime();
}
