package shared;

import java.util.HashMap;

public class AActivity { // {Written by Jaller698, GaySupremacy and Perry02, refactored by Perry02 (Original file: aktivitet.java)}
	protected String name; // the activities name
	public String getName() {return name;}
	
	protected int estTime; // the estimated time for this activity
	public int getEstTime() {return estTime;}
	
	protected AProject project; // the project the activity is tied to
	public AProject getProject() {return project;}
	
	protected HashMap<AUser, WorkTimeUnit> timeWorked = new HashMap<AUser, WorkTimeUnit>();
	
	
	
	public AActivity(AProject project, String name, int estTime) { // {Written by GaySupremacy}
		this.name = name;
		this.estTime = estTime;
		this.project = project;
		
		project.AddActivity(this);
	}
	
	public AActivity getBase() { // {Written by Perry02}
		return this;
	}
	
	@Override
	public String toString() { // {Written by Jaller698}
		return project.toString()+':'+name;
	}
	
	// register hours to a user for this activity
	public void RegisterHours(AUser user, int hours) { // {Written by Perry02}
		user.AssignActivity(this);
		WorkTimeUnit time = null;
		
		if (timeWorked.containsKey(user)) {
			time = timeWorked.get(user);
		} else {
			time = new WorkTimeUnit(user);
			timeWorked.put(user, time);
		}
		
		time.AddTime(hours);
	}
	
	public int GetTotalTime() { // {Written by Perry02}
		int totalTime = 0;
		
		for (WorkTimeUnit timeUnit : timeWorked.values()) {
			totalTime += timeUnit.GetTotalTime();
		}
		
		return totalTime;
	}
	
	public int GetTotalTime(AUser user) {  // {Written by Perry02}
		return timeWorked.get(user).GetTotalTime();
	}	
	
	// switches the project this is tied to
	public void SwitchProject(AProject destination) { // {Written by Perry02}
		System.out.println("Activity("+this+"): Switch from project: " + project+ " to " + destination);
		
		if (destination != project) {
			AProject tempP = project;
			project = destination;
			tempP.MoveActivity(this, destination);
			project.MoveActivity(this, destination);
		}
	}
}
