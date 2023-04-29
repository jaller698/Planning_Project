package server.database;

import java.util.ArrayList;

import server.UserSaveable;

public interface IUserRegister { // {Written by Perry02}
	
	public int addUser(UserSaveable user); // stores new user and returns the new users ID
	
	public UserSaveable getUser(int userID); // retrieves a user based on the users ID
	
	public UserSaveable[] getUser(String userName); // retrieves a user based on a users name
	
	// TODO add administrator check
	public ArrayList<UserSaveable> getAllUsers(); // retrieves all users
	
	public int getUserID(UserSaveable user); // gets a specific userID
	
	// TODO add administrator check
	public void removeUser(int userID); // removes user based on user ID
}
