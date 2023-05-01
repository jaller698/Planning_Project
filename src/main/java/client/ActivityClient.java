package client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import shared.AActivity;
import shared.AProject;

public class ActivityClient extends AActivity {

	public ActivityClient(AProject project, String name, int estTime) {
		super(project, name, estTime);
	}
	
	public ActivityClient(ProjectClient project, String name, int estTime) {
		super(project.getBase(), name, estTime);
	}
	
	public ProjectClient getProjectClient() {
		return (ProjectClient) project;
	}
	
	
	
	// UI method
	public StringProperty getUIName() {
		StringProperty ActivityName = new SimpleStringProperty(name);
		return ActivityName;
	}

	// UI method
	public ObservableValue<Integer> getUIEstHours() {
		ObservableValue<Integer> estHours = new SimpleIntegerProperty(estTime).asObject();
		return estHours;
	}
}
