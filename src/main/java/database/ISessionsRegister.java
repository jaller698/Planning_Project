package database;

public interface ISessionsRegister {
	public String loginUser(String name, String password); // logs in a user and retrieves a session id
	
	public void logoutUser(String session); // logs out a user
	
	public boolean checkSession(String session); // checks if a user is logged in
}
