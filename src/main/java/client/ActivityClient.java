package client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import shared.AActivity;
import shared.AProject;

public class ActivityClient extends AActivity { // {Written by Jaller698, refactored by Perry02 (Original file: Aktivitet.java)}

	public ActivityClient(AProject project, String name, int estTime) { // {Written by Jaller698}
		super(project, name, estTime);
	}
	
	public ActivityClient(ProjectClient project, String name, int estTime) { // {Written by Jaller698}
		super(project.getBase(), name, estTime);
	}
	
	public ProjectClient getProjectClient() { // {Written by Perry02}
		return (ProjectClient) project;
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
