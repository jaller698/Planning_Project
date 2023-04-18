package application;

import java.util.ArrayList;

import database.*;

public final class Application {
	private final static Application singleton = new Application();

	private static Medarbejder currentActiveUser;
	private static String ConfirmationMSG;
	
	private static DataPersistence database = new DataPersistence();
	public static IProjectRegister projects = database;
	public static IUserRegister workers = database;
	public static ISessionsRegister sessions = new SessionManager();
	
	private Application() {}
	
	public static Application singleton() {
		return Application.singleton;
	}
	
	public void reset() {
		currentActiveUser = null;
		ConfirmationMSG = null;
		
		database = new DataPersistence();
		projects = database;
		workers = database;
		
		System.out.println("Application: Created a new clean slate");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setConfirmationMSG("");
	}
	
	public static Medarbejder getCurrentActiveUser() {
		return currentActiveUser;
	}
	
	public static void setCurrentActiveUser(Medarbejder m) {
		currentActiveUser = m;
	}
	
	public static int getYear() {
		return 23;
	}

	public static String getConfirmationMSG() {
		return ConfirmationMSG;
	}
	
	public static void setConfirmationMSG(String msg){
		ConfirmationMSG = msg;
	}
	
	public void AdminChangePassword(int aid, int mid, String newPWD) {
		Medarbejder A = workers.getUser(--aid);
		if(A != null && A.isAdmin()) {
			Medarbejder M = workers.getUser(--mid);
			M.changePassword(newPWD);
			Application.setConfirmationMSG("Successfully changed "+mid+"("+M.navn+")'s password");
		}
	}
}
