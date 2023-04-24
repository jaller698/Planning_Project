package server;

import server.databse.IDataSaveable;
import shared.AProject;

public class ProjectSaveable extends AProject implements IDataSaveable {

	public ProjectSaveable(String name) {
		super(name);
	}

}
