package client;

import shared.AUser;

public class UserClient extends AUser {
	public Boolean online;
	
	public UserClient(String name, Integer id) {
		super(name, id);
	}
}
