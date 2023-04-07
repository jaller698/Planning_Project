package application;

import java.util.ArrayList;

public class Application {

	public static ArrayList<Medarbejder> alleMedarbejdere = new ArrayList<Medarbejder>();
	public static ArrayList<Projekt> alleProjekter = new ArrayList<Projekt>();
	private static Medarbejder currentEmployee;
	private static String ConfirmationMSG;
	
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
		Medarbejder m = null;
		for (int i = 0; i < alleMedarbejdere.size();i++) {
			if(alleMedarbejdere.get(i).navn.equals(employeeName))
				m = alleMedarbejdere.get(i);
		}
		return m;
	}
	
	public Projekt findProject(String projName) {
		Projekt p = null;
		for(int i = 0; i < alleProjekter.size(); i++) {
			if(projName.equals(alleProjekter.get(i).toString()))
				p = alleProjekter.get(i);
		}
		return p;
	}
}
