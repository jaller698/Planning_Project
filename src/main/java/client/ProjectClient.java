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
	
	private ProjectClient(String name, int estTime) { // {Written by GaySupremacy}
		super(name, estTime);
	}
	
	@Override
	public void setProjectLeader(AUser projectLeader) { // {Written by Perry02}
		this.projectLeader = Application.serverAPI.projectAddProjectLeader(Application.getCurrentActiveSession(), this.id, projectLeader.getId());
	}
	
	@Override
	public AActivity CreateActivity(String name, int estTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AddActivity(AActivity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MoveActivity(AActivity activity, AProject destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RemoveActivity(AActivity activity) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public ArrayList<ActivityClient> getActivitiesClient() { // {Written by McQueen24}
		ArrayList<ActivityClient> list = new ArrayList<ActivityClient>();
		for (Iterator iterator = activities.iterator(); iterator.hasNext();) {
			list.add((ActivityClient) iterator.next());
		}
		
		return list;
	}
	
	public StringProperty getUIName() { // {Written by Jaller698}
		StringProperty ProjectName = new SimpleStringProperty(name);
		return ProjectName;
	}
}
