package application;

import java.util.ArrayList;

import FXML.StartController;

public class Medarbejder {
	public  String navn;
	public  String password;
	int arbejdsTimer;

	public ArrayList<Projekt> p = new ArrayList<Projekt>();
	public ArrayList<Aktivitet> a = new ArrayList<Aktivitet>();
	
	public Medarbejder(String navn, String password) {
		this.navn = navn;
		this.password = password;
		StartController.alleMedarbejdere.add(this);
	}
	
	public boolean tjekLogin() {
		return true;
	}

	public String toString() {
		return navn + " " + password;
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
	public void addProjekt(Projekt P) {
		//fantastiske navne. Slet ikke forvirrende.
		this.p.add(P);
		System.out.println(P.toString() + "added to " + this.navn);
		
	}
}

class ProjektLeder extends Medarbejder {

	public ProjektLeder(String navn, String password) {
		super(navn, password);
		// TODO Auto-generated constructor stub
	}

}
