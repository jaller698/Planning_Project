package server;

import server.database.IDataSaveable;
import shared.AProject;

public class ProjectSaveable extends AProject implements IDataSaveable { // {Written by Perry02}

	public void SetId(int id) {
		this.id = id;
	}
	
	public ProjectSaveable(AProject project) {
		super(project.getName(), project.getEstTime());
		
	}

}
