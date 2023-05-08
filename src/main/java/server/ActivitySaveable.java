package server;

import server.database.IDataSaveable;
import shared.AActivity;
import shared.AProject;
import shared.AUser;
import shared.WorkTimeUnit;

public class ActivitySaveable extends AActivity implements IDataSaveable { // {Written by Perry02}

	public ActivitySaveable(AProject project, String name, int estTime) {
		super(project, name, estTime);
	}

	
	
	public ActivitySaveable(AActivity activity) {
		super(activity.getProject(), activity.getName(), activity.getEstTime());
		
		this.timeWorked = activity.getTimeWorked();
	}



	// switches the project this is tied to
	@Override
	public void SwitchProject(AProject destination) { // {Written by Perry02}
		System.out.println("Activity("+this+"): Switch from project: " + project+ " to " + destination);
		
		if (destination != project) {
			AProject tempP = project;
			project = destination;
			tempP.MoveActivity(this, destination);
			project.MoveActivity(this, destination);
		}
	}
	
	// register hours to a user for this activity
	@Override
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



	@Override
	public void editActivity(int hours) {
		this.estTime = hours;
	}	
}
