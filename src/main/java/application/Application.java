package application;

import java.util.ArrayList;

public class Application {

	public static ArrayList<Medarbejder> alleMedarbejdere = new ArrayList<Medarbejder>();
	public static ArrayList<Projekt> alleProjekter = new ArrayList<Projekt>();
	private static Medarbejder currentEmployee;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Medarbejder getMedarbejder() {
		return currentEmployee;
	}
	
	public static void setMedarbejder(Medarbejder m) {
		currentEmployee = m;
	}
}
