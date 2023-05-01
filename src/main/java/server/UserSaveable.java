package server;

import client.Application;
import server.database.IDataSaveable;
import shared.AUser;

public class UserSaveable extends AUser implements IDataSaveable { // {Written by Jaller698 and Perry02, refactored by Perry02 (Original file: Medarbejder.java)}
	private boolean admin;
	private String password;

	public UserSaveable(String name, String password) {
		super();
		
		this.name = name;
		this.password = password;
		id = ServerCore.users.addUser(this);
	}
	
	public static UserSaveable getUser(AUser user) { // {Written by Perry02}
		return ServerCore.users.getUser(user.getId());
	}
	
	public Boolean CheckPassword(String password) {  // {Written by Perry02}
		return this.password.equals(password);
	}

	public Boolean isAdmin() { // {Written by Jaller698}
		return admin;
	}

	public void setAdmin(Boolean b) { // {Written by Jaller698}
		admin = b;
	}
	
	public void changePassword(String newPWD) { // {Written by Jaller698}
		this.password = newPWD;
		Application.setConfirmationMSG("Successfully changed password");
	}
	
	public void changePassword(String currentPWD, String newPWD) { // {Written by Jaller698}
		if (this.password.equals(currentPWD)) {
			password = newPWD;
			Application.setConfirmationMSG("Successfully changed password");
		}
	}
	
	public void changeName(String name) { // {Written by Perry02}
		this.name = name;
	}
}
