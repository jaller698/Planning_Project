package server;

import shared.Medarbejder;

public interface ISessionsRegister {
	public String loginUser(String name, String password); // logs in a user and retrieves a session id
	
	public void logoutUser(String session); // logs out a user
	
	public boolean checkSession(String session); // checks if a user is logged in
	
	public int getUserOfSession(String session); // gets the userID associated with a session
	
	public String getSessionOfUser(int userID); // gets the session of a user if it exists
	
	public String getSessionOfUser(String userName);
}
