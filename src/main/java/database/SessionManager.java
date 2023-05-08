package database;

import java.util.HashMap;

import application.Application;
import application.Medarbejder;

//Christian
public class SessionManager implements ISessionsRegister {
	private static HashMap<Integer, Medarbejder> activeSessions = new HashMap<Integer, Medarbejder>();

	public SessionManager() {
		activeSessions = new HashMap<Integer, Medarbejder>();
		
		System.out.println("DataPersistence: Reset persistent values");
	}
	
	
	@Override
	public String loginUser(String name, String password) {
		if ((name != null && password != null)) {
			boolean loginCheck = false;
			Medarbejder L = Application.workers.getUser(name);
			if(L == null)
				return "test";
			else if(L.password.equals(password)) {
				loginCheck = true;
				Application.setCurrentActiveUser(L);
				Application.setConfirmationMSG("Successfully logged in");
				return null;
			}
		}
		Application.setConfirmationMSG("Failed login: Wrong username or password");
		return null;
	}

	@Override
	public void logoutUser(String session) {
		Application.setCurrentActiveUser(null);
	}

	@Override
	public boolean checkSession(String employeeName) {
		if(Application.getCurrentActiveUser() == null)
			return false;
		else if(employeeName.equals(Application.getCurrentActiveUser().navn))
			return true;
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
