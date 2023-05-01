package server;

import server.database.IDataSaveable;
import shared.AActivity;
import shared.AProject;

public class ActivitySaveable extends AActivity implements IDataSaveable {

	public ActivitySaveable(AProject project, String name, int estTime) { // {Written by Perry02}
		super(project, name, estTime);
		// TODO Auto-generated constructor stub
	}

}
