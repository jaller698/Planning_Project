package server;

import client.Application;
import server.database.IDataSaveable;
import shared.AActivity;
import shared.AProject;
import shared.AUser;

public class UserSaveable extends AUser implements IDataSaveable { // {Written by Jaller698 and Perry02, refactored by Perry02 (Original file: Medarbejder.java)}
	private boolean admin;
	private String password;
	public String getPassword() {return password;} // for use in tests

	public UserSaveable(String name, String password) { // {Written by Perry02}
		super();
		
		this.name = name;
		this.password = password;
		id = ServerCore.users.addUser(this);
	}
	
	public UserSaveable(AUser user) { // {Written by Perry02}
		this.name = user.getName();
		this.id = user.getId();
		this.projects = user.getProjects();
		this.activities = user.getActivities();
	}

	// assign a project to this user
	@Override
	public void AssignProject(AProject project) { // {Written by GaySupremacy}
		if (!projects.contains(project)) {
			projects.add(project);
		} else {
			// TODO error 
		}
	}
	
	// remove a project to this user
	@Override
	public void UnAssignProject(AProject project) { // {Written by GaySupremacy} 
		projects.remove(project);
	}
	
	// assign an activity to this user
	@Override
	public AActivity AssignActivity(AActivity activity) { // {Written by GaySupremacy}
		AssignProject(activity.getProject());
		
		if (!activities.contains(activity)) {
			activities.add(activity);
			return activity;
		} else {
			return null;
		}
	}
	
	// remove an activity to this user
	@Override
	public void UnAssignActivity(AActivity activity) { // {Written by Perry02}
		activities.remove(activity);
	}
	
	// register hours for an activity for this user
	@Override
	public void RegisterHours(AActivity activity, int hours) {  // {Written by GaySupremacy}
		AssignActivity(activity);
		
		activity.RegisterHours(this, hours);
	}
	
	@Override
	public int GetTotalTime() { // {Written by Perry02}
		int totalTime = 0;
		
		for (AActivity activity : activities) {
			totalTime = activity.GetTotalTime(this);
		}
		
		return totalTime;
	}
	
	
	
	public static UserSaveable getUser(AUser user) { // {Written by Perry02}
		return ServerCore.users.getUser(user.getId());
	}
	
	public Boolean CheckPassword(String password) {  // {Written by Perry02}
		return this.password.equals(password);
	}

	public Boolean isAdmin() { // {Written by Jaller698}
		return admin;
	}

	public void setAdmin(Boolean b) { // {Written by Jaller698}
		admin = b;
	}
	
	public void changePassword(String newPWD) { // {Written by Jaller698}
		this.password = newPWD;
		Application.setConfirmationMSG("Successfully changed password");
	}
	
	public void changePassword(String currentPWD, String newPWD) { // {Written by Jaller698}
		if (this.password.equals(currentPWD)) {
			password = newPWD;
			Application.setConfirmationMSG("Successfully changed password");
		}
	}
	
	public void changeName(String name) { // {Written by Perry02}
		this.name = name;
	}
}
