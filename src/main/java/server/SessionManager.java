package server;

import java.util.HashMap;
import java.time.Instant;
import client.Application;

public class SessionManager implements ISessionsRegister { // {Written by Perry02 and Jaller698}
	private static HashMap<String, Integer> activeSessions = new HashMap<String, Integer>();

	public SessionManager() { // {Written by Perry02}
		activeSessions = new HashMap<String, Integer>();
		
		System.out.println("DataPersistence: Reset persistent values");
	}
	
	// small function to create unique sessions
	private String CreateSession(String text) { // {Written by Perry02}
		return text + Instant.now();
	}
	
	
	
	@Override
	public String loginUser(String name, String password) { // {Written by Jaller698, refactored by Perry02}
		System.out.println("SessionManager: loginUser step 1 - name: " + name + " - password: " + password);
		if (!name.isBlank() && !password.isBlank()) {
			UserSaveable[] userOfName = ServerCore.users.getUser(name); // TODO name calling does not work currently
			
			System.out.println("SessionManager: loginUser step 2 check users: " + userOfName.length);
			
			for (UserSaveable userSaveable : userOfName) {
				System.out.println("SessionManager: loginUser user: " + userSaveable);
				if (userSaveable.CheckPassword(password)) {
					String session = CreateSession(userSaveable.toString());
					
					activeSessions.put(session, userSaveable.getId());
					
					Application.setConfirmationMSG("Successfully logged in");
					return session;
				}
			}
		}

		Application.setConfirmationMSG("Failed login: Wrong username or password");
		return null;
	}

	@Override
	public void logoutUser(String session) { // {Written by Perry02}
		activeSessions.remove(session);
	}

	@Override
	public boolean checkSession(String session) { // {Written by Jaller698, refactored by Perry02}
		System.out.print("SessionManager: checkSession - ");
		if (activeSessions.containsKey(session)) {
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		return false;
	}

	@Override
	public int getUserIDOfSession(String session) { // {Written by Perry02}
		return activeSessions.computeIfAbsent(session, k -> -1);
	}
	
	@Override
	public Integer[] getAllActiveUsers() { // {Written by Perry02}
		return activeSessions.values().toArray(new Integer[activeSessions.values().size()]);
	}

	@Override
	public Boolean checkIfUserIsActive(int userID) { // {Written by Perry02}
		return activeSessions.containsValue(userID);
	}
}
