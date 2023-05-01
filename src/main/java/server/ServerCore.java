package server;

import server.database.*;

public class ServerCore { // {Written by Perry02}
	
	// main server function
	
	private final static ServerCore singleton = new ServerCore();
	
	private static DataPersistence database = new DataPersistence();
	public static IProjectRegister projects = database;
	public static IUserRegister users = database;
	public static ISessionsRegister sessions = new SessionManager();
	
	private ServerCore() {} // {Written by Perry02}
	
	public static ServerCore singleton() { // {Written by Perry02}
		return ServerCore.singleton;
	}
	
	public void reset() { // {Written by Perry02}		
		database = new DataPersistence();
		projects = database;
		users = database;
		
		System.out.println("ServerCore: Created a new clean slate");
	}
}
