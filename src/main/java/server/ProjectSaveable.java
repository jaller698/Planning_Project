package server;

import server.database.IDataSaveable;
import shared.AProject;
import shared.AUser;

public class ProjectSaveable extends AProject implements IDataSaveable { // {Written by Perry02}

	public void SetId(int id) {
		this.id = id;
	}
	
	public ProjectSaveable(AProject project) {
		super(project.getName(), project.getEstTime());	
	}
	
	public ProjectSaveable(String name, int estTime) {
		super(name, estTime);
	}
	
	@Override
	public void setProjectLeader(AUser projectLeader) { // {Written by Perry02}
		this.projectLeader = projectLeader;
	}

}
