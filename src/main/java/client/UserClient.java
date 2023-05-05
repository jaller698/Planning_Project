package client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
	
	public StringProperty getUIName() { // {Written by Jaller698}
		StringProperty EmployeeName = new SimpleStringProperty(name);
		return EmployeeName;
	}
}
