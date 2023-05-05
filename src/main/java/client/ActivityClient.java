package client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import shared.AActivity;
import shared.AProject;
import shared.AUser;

public class ActivityClient extends AActivity { // {Written by Jaller698, refactored by Perry02 (Original file: Aktivitet.java)}

	/*
	public ActivityClient(AProject project, String name, int estTime) { // {Written by Jaller698}
		super(project, name, estTime);
	}
	
	public ActivityClient(ProjectClient project, String name, int estTime) { // {Written by Jaller698}
		super(project.getBase(), name, estTime);
	}
	
	public ProjectClient getProjectClient() { // {Written by Perry02}
		return (ProjectClient) project;
	}
	*/
	
	private ActivityClient(AProject project, String name, int estTime) { // {Written by Jaller698}
		super(project, name, estTime);
	}
	
	@Override
	public void RegisterHours(AUser user, int hours) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int GetTotalTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetTotalTime(AUser user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void SwitchProject(AProject destination) {
		// TODO Auto-generated method stub
		
	}
	
	

	// UI method
	public StringProperty getUIName() { // {Written by Jaller698}
		StringProperty ActivityName = new SimpleStringProperty(name);
		return ActivityName;
	}

	// UI method
	public ObservableValue<Integer> getUIEstHours() { // {Written by Jaller698}
		ObservableValue<Integer> estHours = new SimpleIntegerProperty(estTime).asObject();
		return estHours;
	}
}
