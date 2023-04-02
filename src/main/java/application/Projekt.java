package application;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Projekt {
	String navn;
	ArrayList<Aktivitet> aktiviteter = new ArrayList<Aktivitet>();
	int estTid;
	ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();
	ProjektLeder leder;
	
	public Projekt(String navn) {
		this.navn=navn;
	}
	
	
	public Projekt(String navn, ArrayList<Medarbejder> medarbejdere, Medarbejder leder) {
		this.navn=navn;
		this.medarbejdere=medarbejdere;
		//tilføj lederen på et tidspunkt
	}

	public Projekt(String navn, ArrayList<Medarbejder> medarbejdere, Medarbejder leder, int estTid) {
	
	}
	
	public String toString() {
		return this.navn;
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
		this.leder = new ProjektLeder(navn, navn);
	}
	
	//UI method
	public StringProperty getUIName() {
		StringProperty ProjectName = new SimpleStringProperty(navn);
		return ProjectName;
	}
	public ArrayList<Aktivitet> getActivityList(){
		return aktiviteter;
	}

}
