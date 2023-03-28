package application;

import java.util.ArrayList;

public class Projekt {
	String navn;
	ArrayList<Aktivitet> aktiviteter = new ArrayList<Aktivitet>();
	int estTid;
	ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();
	ProjektLeder leder;
	

	public Projekt(String navn, ArrayList<Medarbejder> medarbejdere, Medarbejder leder) {
	
	}

	public Projekt(String navn, ArrayList<Medarbejder> medarbejdere, Medarbejder leder, int estTid) {
	
	}
	
	public void addAktivitet(Aktivitet A) {
		this.aktiviteter.add(A);
	}
	public void getAktiviteter() {
		for(Aktivitet a : this.aktiviteter) {
			System.out.println(a.navn);	
		}
	}


	public void addProjektLeder(Medarbejder m) {
		//properties for den givne medarbejder
		this.leder = new ProjektLeder();
	}
}
