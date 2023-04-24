package server;

import server.database.IDataSaveable;
import shared.AProject;

public class ProjectSaveable extends AProject implements IDataSaveable {

	public ProjectSaveable(String name) {
		super(name);
	}

}
