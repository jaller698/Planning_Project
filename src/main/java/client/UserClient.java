package client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.AActivity;
import shared.AProject;
import shared.AUser;

public class UserClient extends AUser { // {Written by Jaller698, refactored by Perry02 (Original file: Medarbejder.java)}
	/*
	public UserClient(AUser user) {
		super();
		this.name = user.getName();
		this.id = user.getId();
		this.projects = user.getProjects();
		this.activities = user.getActivities();
	}	
	*/
	public UserClient(AUser user) {
		this.name = user.getName();
		this.id = user.getId();
		this.projects = user.getProjects();
		this.activities = user.getActivities();
	}
	
	@Override
	public void AssignProject(AProject project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UnAssignProject(AProject project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AActivity AssignActivity(AActivity activity) {
		
		AActivity _activity = Application.serverAPI.activityAssignUsertoActivity(Application.getCurrentActiveSession(), new ActivityClient(activity), this);
		if (_activity == null) {
			return null;
		}
		
		activities.add(_activity);
		return _activity;
	}

	@Override
	public void UnAssignActivity(AActivity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RegisterHours(AActivity activity, int hours) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int GetTotalTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	// UI
	
	public StringProperty getUIName() { // {Written by Jaller698}
		StringProperty EmployeeName = new SimpleStringProperty(name);
		return EmployeeName;
	}
}
