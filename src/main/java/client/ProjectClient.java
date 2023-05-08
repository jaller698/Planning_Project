package client;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.AActivity;
import shared.AProject;
import shared.AUser;

public class ProjectClient extends AProject { // {Written by Jaller698, GaySupremacy and McQueen24, refactored by Perry02 (Original file: Projekt.java)}

	/*
	public ProjectClient(String name, int estTime) { // {Written by GaySupremacy}
		super(name, estTime);
		
		//this.id = Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), this);
	}
	public ProjectClient(String name, int estTime, UserClient projectLeader) { // {Written by GaySupremacy}
		super(name, estTime);
		if (projectLeader != null)
			this.projectLeader = projectLeader.getBase();
		
		//this.id = Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), this);
	}
	
	public ProjectClient(AProject project) { // {Written by Perry02}
		super(project.getName(), project.getEstTime());
		
		//this.id = Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), this);
	}
	
	public ProjectClient(AProject project, UserClient projectLeader) { // {Written by Perry02}
		super(project.getName(), project.getEstTime());
		this.projectLeader = projectLeader.getBase();
		
		//this.id = Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), this);
	}
	*/
	
	public ProjectClient(AProject project) { // {Written by GaySupremacy}
		super(project.getName(), project.getEstTime());
		
		this.id = project.getId();
		this.projectLeader = project.getProjectLeader();
		this.activities = project.getActivities();
	}
	
	
	
	@Override
	public void setProjectLeader(AUser projectLeader) { // {Written by Perry02}
		if (projectLeader == null) {
			this.projectLeader = Application.serverAPI.projectAddProjectLeader(Application.getCurrentActiveSession(), this.id, null);;
		} else {
			this.projectLeader = Application.serverAPI.projectAddProjectLeader(Application.getCurrentActiveSession(), this.id, projectLeader.getId());
		}
	}
	
	@Override
	public AActivity CreateActivity(String name, int estTime) {
		ActivityClient activity = Application.serverAPI.activityAddNewActivity(Application.getCurrentActiveSession(), name, estTime, id);
		if (activity == null)
			return null;
		
		activities.add(activity);
		
		return activity;
	}

	@Override
	public void MoveActivity(AActivity activity, AProject destination) {
		Application.serverAPI.activityMoveActivity(Application.getCurrentActiveSession(), new ActivityClient(activity), new ProjectClient(destination));
	}

	@Override
	public void RemoveActivity(AActivity activity) {
		if (Application.serverAPI.activityRemoveActivity(Application.getCurrentActiveSession(), new ActivityClient(activity))) {
			activities.remove(activity);
		}
	}
	
	
	
	
	public ArrayList<ActivityClient> getActivitiesClient() { // {Written by McQueen24}
		ArrayList<ActivityClient> list = new ArrayList<ActivityClient>();		
		for (AActivity aActivity : activities) {
			list.add(new ActivityClient(aActivity));
		}
		
		return list;
	}
	
	public StringProperty getUIName() { // {Written by Jaller698}
		StringProperty ProjectName = new SimpleStringProperty(name);
		return ProjectName;
	}
}
