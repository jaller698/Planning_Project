package shared;

import java.util.HashMap;

public class AActivity {
	private String name; // the activities name
	public String getName() {return name;}
	
	private AProject project; // the project the activity is tied to
	public AProject getProject() {return project;}
	
	private HashMap<AUser, WorkTimeUnit> timeWorked = new HashMap<AUser, WorkTimeUnit>();
	
	
	
	public AActivity(AProject project, String name) {
		this.name = name;
		
		project.AddActivity(this);
		this.project = project;
	}
	
	public AActivity getBase() {
		return this;
	}
	
	public void RegisterHours(AUser user, int hours) { // register hours to a user for this activity
		user.AssignActivity(this);
		WorkTimeUnit time = null;
		
		if (timeWorked.containsKey(user)) {
			time = timeWorked.get(time);
		} else {
			time = new WorkTimeUnit(user);
			timeWorked.put(user, time);
		}
		
		time.AddTime(hours);
	}
	
	public int GetTotalTime() {
		int totalTime = 0;
		
		for (WorkTimeUnit timeUnit : timeWorked.values()) {
			totalTime += timeUnit.GetTotalTime();
		}
		
		return totalTime;
	}
	
	public int GetTotalTime(AUser user) {
		return timeWorked.get(user).GetTotalTime();
	}
}
