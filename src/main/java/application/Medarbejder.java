package application;

import java.util.ArrayList;

public class Medarbejder {
	public static String navn;
	public static String password;
	int arbejdsTimer;

	ArrayList<Projekt> p = new ArrayList<Projekt>();
	ArrayList<Aktivitet> a = new ArrayList<Aktivitet>();
	
	public Medarbejder(String navn, String password) {
		this.navn = navn;
		this.password = password;
	}
	
	public boolean tjekLogin() {
		return true;
	}

	public String toString() {
		return navn+" "+password;
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

	public ProjektLeder(String navn, String password) {
		super(navn, password);
		// TODO Auto-generated constructor stub
	}

}
