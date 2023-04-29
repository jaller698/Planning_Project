package server.database;

import java.util.ArrayList;

import server.UserSaveable;

public interface IUserRegister { // {Written by Perry02}
	
	public int addUser(UserSaveable user); // stores new user and returns the new users ID
	
	public UserSaveable getUser(int userID); // retrieves a user based on the users ID
	
	public UserSaveable[] getUser(String userName); // retrieves a user based on a users name
	
	public UserSaveable[] getAllUsers(); // retrieves all users
	
	public ArrayList<UserSaveable> getAllUsersAsList(); // retrieves all users as list
	
	public int getUserID(UserSaveable user); // gets a specific userID
	
	public void removeUser(int userID); // removes user based on user ID
}
