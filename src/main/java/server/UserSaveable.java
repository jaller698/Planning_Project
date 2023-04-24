package server;

import server.database.IDataSaveable;
import shared.AUser;

public class UserSaveable extends AUser implements IDataSaveable {

	public UserSaveable(String name, Integer id) {
		super(name, id);
	}

}
