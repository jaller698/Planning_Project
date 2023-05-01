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
	public void AssignProject(AProject project) { // {Written by GaySupremacy}
		if (!projects.contains(project)) {
			projects.add(project);
		} else {
			// TODO error 
		}
	}
	
	// remove a project to this user
	public void UnAssignProject(AProject project) { // {Written by GaySupremacy} 
		projects.remove(project);
	}
	
	// assign an activity to this user
	public void AssignActivity(AActivity activity) { // {Written by GaySupremacy}
		AssignProject(activity.getProject());
		
		if (!activities.contains(activity)) {
			activities.add(activity);
		} else {
			// TODO error
		}
	}
	
	// remove an activity to this user
	public void UnAssignActivity(AActivity activity) { // {Written by Perry02}
		activities.remove(activity);
	}
	
	// register hours for an activity for this user
	public void RegisterHours(AActivity activity, int hours) {  // {Written by GaySupremacy}
		AssignActivity(activity);
		
		activity.RegisterHours(this, hours);
	}
	
	public int GetTotalTime() { // {Written by Perry02}
		int totalTime = 0;
		
		for (AActivity activity : activities) {
			totalTime = activity.GetTotalTime(this);
		}
		
		return totalTime;
	}
}
