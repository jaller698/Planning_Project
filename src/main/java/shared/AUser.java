package shared;

import java.util.ArrayList;

public abstract class AUser {
	private String name; // the users name
	public String getName() {return name;}
	
	private Integer id; // the users id
	public Integer getId() {return id;}
	
	private ArrayList<AProject> projects = new ArrayList<AProject>(); // the users projects
	public ArrayList<AProject> getProjects() {return projects;}
	
	private ArrayList<AActivity> activities = new ArrayList<AActivity>(); // the users activities
	public ArrayList<AActivity> getActivities() {return activities;}
	
	
	
	public AUser(String name, Integer id) {
		this.name = name;
		this.id = id;
	}
	
	public AUser getBase() {
		return this;
	}
	
	public void AssignProject(AProject project) { // assign a project to this user
		if (!projects.contains(project)) {
			projects.add(project);
		} else {
			// TODO error 
		}
	}
	
	public void AssignActivity(AActivity activity) { // assign an activity to this user
		AssignProject(activity.getProject());
		
		if (!activities.contains(activity)) {
			activities.add(activity);
		} else {
			// TODO error
		}
	}
	
	public void RegisterHours(AActivity activity, int hours) { // register hours for an activity for this user
		AssignActivity(activity);
		
		activity.RegisterHours(this, hours);
	}
	
	public int GetTotalTime() {
		int totalTime = 0;
		
		for (AActivity activity : activities) {
			totalTime = activity.GetTotalTime(this);
		}
		
		return totalTime;
	}
}
