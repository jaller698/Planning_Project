package client;

import java.sql.Date;
import java.util.*;

import server.ServerCore;
import shared.networking.*;

public final class Application { // {Written by Jaller698 and Perry02}
	private final static Application singleton = new Application();
	private static ServerCore backendServer;

	private static String currentSession;
	private static String ConfirmationMSG;
	
	public static IProjectPlannerMockAPI serverAPI = new MockAPI();
	
	private Application() {} // {Written by Perry02}
	
	public static Application singleton() { // {Written by Perry02}
		return Application.singleton;
	}
	
	public void reset() { // {Written by Perry02}
		currentSession = null;
		ConfirmationMSG = null;
		
		backendServer.reset();
		serverAPI = new MockAPI();
		
		System.out.println("Application: Created a new clean slate");
	}
	
	
	public static void main(String[] args) { // {Written by Jaller698}
		// TODO Auto-generated method stub
		setConfirmationMSG("");
	}
	
	public static String getCurrentActiveSession() { // {Written by Perry02}
		return currentSession;
	}
	
	public static void setCurrentActiveSession(String session) { // {Written by Perry02}
		currentSession = session;
	}
	
	public static int getYear() { // {Written by Jaller698}
		return 23;
	}

	public static String getConfirmationMSG() { // {Written by Jaller698}
		return ConfirmationMSG;
	}
	
	public static void setConfirmationMSG(String msg){ // {Written by Jaller698}
		ConfirmationMSG = msg;
	}
}
