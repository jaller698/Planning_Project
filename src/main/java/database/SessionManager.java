package database;

import java.util.HashMap;

import application.Medarbejder;

public class SessionManager implements ISessionsRegister {
	private static HashMap<Integer, Medarbejder> activeSessions = new HashMap<Integer, Medarbejder>();

	
	@Override
	public String loginUser(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logoutUser(String session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkSession(String session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getUserOfSession(String session) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSessionOfUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionOfUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
