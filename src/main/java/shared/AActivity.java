package shared;

import java.util.HashMap;

public class AActivity {
	private String name; // the activities name
	public String getName() {return name;}
	
	private int estTime; // the estimated time for this activity
	public int getEstTime() {return estTime;}
	
	private AProject project; // the project the activity is tied to
	public AProject getProject() {return project;}
	
	private HashMap<AUser, WorkTimeUnit> timeWorked = new HashMap<AUser, WorkTimeUnit>();
	
	
	
	public AActivity(AProject project, String name, int estTime) {
		this.name = name;
		this.estTime = estTime;
		this.project = project;
		
		project.AddActivity(this);
	}
	
	public AActivity getBase() {
		return this;
	}
	
	@Override
	public String toString() {
		return project.toString()+':'+name;
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
	
	public void SwitchProject(AProject destination) { // switches the project this is tied to
		System.out.println("Activity("+this+"): Switch from project: " + project+ " to " + destination);
		
		if (destination != project) {
			AProject tempP = project;
			project = destination;
			tempP.MoveActivity(this, destination);
			project.MoveActivity(this, destination);
		}
	}
}
