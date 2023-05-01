package server;

import server.database.IDataSaveable;
import shared.AActivity;
import shared.AProject;

public class ActivitySaveable extends AActivity implements IDataSaveable { // {Written by Perry02}

	public ActivitySaveable(AProject project, String name, int estTime) {
		super(project, name, estTime);
	}

}
