package application;

import java.util.ArrayList;

import database.IProjectRegister;
import database.IUserRegister;
import database.dataPersistence;

public class Application {

	//public static ArrayList<Medarbejder> alleMedarbejdere = new ArrayList<Medarbejder>();
	//public static ArrayList<Projekt> alleProjekter = new ArrayList<Projekt>();
	private static Medarbejder currentEmployee;
	private static String ConfirmationMSG;
	
	private static dataPersistence database = new dataPersistence();
	public static IProjectRegister projects = database;
	public static IUserRegister workers = database;
	
	public Application() {
		currentEmployee = null;
		ConfirmationMSG = null;
		
		database = new dataPersistence();
		projects = database;
		workers = database;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setConfirmationMSG("");
	}
	
	public static Medarbejder getMedarbejder() {
		return currentEmployee;
	}
	
	public static void setMedarbejder(Medarbejder m) {
		currentEmployee = m;
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
	public Medarbejder findEmployee(String employeeName){
		return workers.getUser(employeeName);
	}
	
	public Projekt findProject(String projName) {
		return projects.getProject(projName);
	}
	
	public void AdminChangePassword(int aid, int mid, String newPWD) {
		Medarbejder A = workers.getUser(aid);
		if(A != null && A.isAdmin()) {
			Medarbejder M = workers.getUser(mid);
			M.changePassword(newPWD);
			Application.setConfirmationMSG("Successfully changed "+mid+"("+M.navn+")'s password");
		}
	}
}
