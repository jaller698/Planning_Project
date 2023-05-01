package client;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.AActivity;
import shared.AProject;

public class ProjectClient extends AProject {

	public ProjectClient(String name, int estTime) {
		super(name, estTime);
		
		this.id = Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), this);
	}
	public ProjectClient(String name, int estTime, UserClient projectLeader) {
		super(name, estTime);
		if (projectLeader != null)
			this.projectLeader = projectLeader.getBase();
		
		this.id = Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), this);
	}
	
	public ProjectClient(AProject project) {
		super(project.getName(), project.getEstTime());
		
		this.id = Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), this);
	}
	
	public ProjectClient(AProject project, UserClient projectLeader) {
		super(project.getName(), project.getEstTime());
		this.projectLeader = projectLeader.getBase();
		
		this.id = Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), this);
	}
	
	
	
	public ArrayList<ActivityClient> getActivitiesClient() {
		ArrayList<ActivityClient> list = new ArrayList<ActivityClient>();
		for (Iterator iterator = activities.iterator(); iterator.hasNext();) {
			list.add((ActivityClient) iterator.next());
		}
		
		return list;
	}
	
	public StringProperty getUIName() {
		StringProperty ProjectName = new SimpleStringProperty(name);
		return ProjectName;
	}
}
