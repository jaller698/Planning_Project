package application;

import java.util.ArrayList;

public class Application {

	public static ArrayList<Medarbejder> alleMedarbejdere = new ArrayList<Medarbejder>();
	public static ArrayList<Projekt> alleProjekter = new ArrayList<Projekt>();
	private static Medarbejder currentEmployee;
	private static String CreationMSG;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

	public String getCreationmsg() {
		
		return CreationMSG;
	}
	
	public static void setCreationMSG(String msg){
		CreationMSG = msg;
	}
	
}
