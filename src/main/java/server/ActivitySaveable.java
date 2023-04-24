package server;

import server.databse.IDataSaveable;
import shared.AActivity;
import shared.AProject;

public class ActivitySaveable extends AActivity implements IDataSaveable {

	public ActivitySaveable(AProject project, String name) {
		super(project, name);
	}
	
}
