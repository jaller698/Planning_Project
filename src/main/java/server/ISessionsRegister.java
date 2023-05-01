package server;

public interface ISessionsRegister { // {Written by Perry02}
	public String loginUser(String name, String password); // logs in a user and retrieves a session id
	
	public void logoutUser(String session); // logs out a user
	
	public boolean checkSession(String session); // checks if a user is logged in
	
	public int getUserIDOfSession(String session); // gets the userID associated with a session
	
	public Integer[] getAllActiveUsers(); // returns all active userIDs
	
	public Boolean checkIfUserIsActive(int userID); // checks if a user is active
}
