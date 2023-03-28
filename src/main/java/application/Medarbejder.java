package application;

import java.util.ArrayList;

public class Medarbejder {
	static String navn;
	int arbejdsTimer;

	ArrayList<Projekt> p = new ArrayList<Projekt>();
	ArrayList<Aktivitet> a = new ArrayList<Aktivitet>();

	
	public boolean tjekLogin() {
		return true;
	}

	public void appointProjektleader(Projekt p, Medarbejder m) {
		
		
		p.addProjektLeder(this);
	}

	public ArrayList<Projekt> getProjekts() {
		return p;
	}

	public ArrayList<Aktivitet> getPA() {

		return a;
	}

	public void addWorkHours(int hours) {

	}
	public void addAktivitet(Aktivitet A) {
		//fantastiske navne. Slet ikke forvirrende.
		this.a.add(A);
	}

}

class ProjektLeder extends Medarbejder {

}
