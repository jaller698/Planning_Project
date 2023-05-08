package client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import shared.AActivity;
import shared.AProject;
import shared.AUser;
import shared.WorkTimeUnit;

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
	
	public ActivityClient(AActivity activity) { // {Written by Jaller698}
		super(activity.getProject(), activity.getName(), activity.getEstTime());
		
		this.timeWorked = activity.getTimeWorked();
	}
	
	@Override
	public void RegisterHours(AUser user, int hours) {
		int time = Application.serverAPI.activityUserAddTime(Application.getCurrentActiveSession(), this, hours);
		
		if (time >= 0) {
			user.AssignActivity(this);
			WorkTimeUnit _time = null;
			
			if (timeWorked.containsKey(user)) {
				_time = timeWorked.get(user);
			} else {
				_time = new WorkTimeUnit(user);
				timeWorked.put(user, _time);
			}
			
			_time.AddTime(hours);
		}
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

	@Override
	public void editActivity(int hours) {
		AActivity activity = Application.serverAPI.activityEditActivity(Application.getCurrentActiveSession(), this, name, hours);
		if (activity != null) {
			this.name = activity.getName();
			this.estTime = activity.getEstTime();
			this.project = activity.getProject();
			this.timeWorked = activity.getTimeWorked();
		}
	}
}
