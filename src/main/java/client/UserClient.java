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
	private UserClient() {}
	
	@Override
	public void AssignProject(AProject project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UnAssignProject(AProject project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AssignActivity(AActivity activity) {
		// TODO Auto-generated method stub
		
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
