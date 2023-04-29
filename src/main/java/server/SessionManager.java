package server;

import java.util.HashMap;
import java.util.Iterator;
import java.time.Instant;
import client.Application;
import shared.Medarbejder;

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
		if (!name.isBlank() && !name.isBlank()) {
			UserSaveable[] userOfName = ServerCore.users.getUser(name);
			
			for (UserSaveable userSaveable : userOfName) {
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
		if (activeSessions.containsKey(session)) {
			return true;
		}
		
		return false;
	}

	@Override
	public int getUserIDOfSession(String session) { // {Written by Perry02}
		return activeSessions.computeIfAbsent(session, k -> -1);
	}

	@Override
	public Boolean checkIfUserIsActive(int userID) { // {Written by Perry02}
		return activeSessions.containsValue(userID);
	}
}
