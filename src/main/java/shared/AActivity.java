package shared;

import java.util.HashMap;

public abstract class AActivity { // {Written by Jaller698, GaySupremacy and Perry02, refactored by Perry02 (Original file: aktivitet.java)}
	protected String name; // the activities name
	public String getName() {return name;}
	
	protected int estTime; // the estimated time for this activity
	public int getEstTime() {return estTime;}
	
	protected AProject project; // the project the activity is tied to
	public AProject getProject() {return project;}
	
	protected HashMap<AUser, WorkTimeUnit> timeWorked = new HashMap<AUser, WorkTimeUnit>();
	
	
	
	protected AActivity(AProject project, String name, int estTime) { // {Written by GaySupremacy}
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
	public abstract void RegisterHours(AUser user, int hours);
	
	public abstract int GetTotalTime();
	
	public abstract int GetTotalTime(AUser user);	
	
	// switches the project this is tied to
	public abstract void SwitchProject(AProject destination);
}
