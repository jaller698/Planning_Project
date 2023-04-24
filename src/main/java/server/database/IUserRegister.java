package server.database;

import java.util.ArrayList;

import shared.Medarbejder;

public interface IUserRegister {
	public int addUser(Medarbejder user); // stores new user and returns the new users ID
	
	public Medarbejder getUser(int userID); // retrieves a user based on the users ID
	
	public Medarbejder getUser(String userName); // retrieves a user based on a users name
	
	// TODO add administrator check
	public ArrayList<Medarbejder> getAllUsers(); // retrieves all users
	
	public int getUserID(Medarbejder user); // gets a specific userID
	
	// TODO add administrator check
	public void removeUser(int userID); // removes user based on user ID
}
