package application;

import java.util.ArrayList;

public class Medarbejder {
	String navn;
	int arbejdsTimer;
	
	ArrayList<Projekt> p = new ArrayList<Projekt>();
	ArrayList<Aktivitet> a = new ArrayList<Aktivitet>();

	
	
	public boolean tjekLogin() {
		return true;
	}
	public void addProjektleader(Projekt p) {
		
	}
	public ArrayList<Projekt> getProjekts(){
		
		
		return p;
	}
	public ArrayList<Aktivitet> getPA(){
		
		
		return a;
	}
	public void addWorkHours(int hours) {
		
	}
	
}

class ProjektLeder extends Medarbejder {
	
}
