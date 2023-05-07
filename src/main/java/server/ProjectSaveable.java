package server;

import server.database.IDataSaveable;
import shared.AActivity;
import shared.AProject;
import shared.AUser;

public class ProjectSaveable extends AProject implements IDataSaveable { // {Written by Perry02}
	
	public ProjectSaveable(AProject project) {
		super(project.getName(), project.getEstTime());	
	}
	
	public ProjectSaveable(String name, int estTime) {
		super(name, estTime);
	}
	
	
	
	public void SetId(int id) {
		this.id = id;
	}
	
	@Override
	public void setProjectLeader(AUser projectLeader) { // {Written by Perry02}
		this.projectLeader = projectLeader;
	}
	
	// create an activity
	@Override
	public AActivity CreateActivity(String name, int estTime) {
		ActivitySaveable activity = new ActivitySaveable(this, name, estTime);
		
		for (AActivity aActivity : activities) {
			if (aActivity.getName() == activity.getName())
				return aActivity;
		}
		
		if (!activities.contains(activity)) {
			activities.add(activity);
		}
		
		return activity;
	}
	
	// add a given activity to this project if it does not exist here
	@Override
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
	@Override
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

	@Override
	public void RemoveActivity(AActivity activity) {
		AActivity _localActivity = getActivities(activity.getName());
		
		activities.remove(_localActivity);
	}
}
