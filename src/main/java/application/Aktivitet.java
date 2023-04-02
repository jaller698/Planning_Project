package application;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Aktivitet {
	public Projekt p;
	public int[] tidBrugt;
	public ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();
	public String navn;
	private int estTime;

	// creating a new aktivity has to automatically add said activity to the
	// project...
	// can i do that in the constructor?
	public Aktivitet(String navn, int estTime, Projekt p) {
		this.navn = navn;
		this.p = p;
		addToProject(this.p);
		this.estTime = estTime;
		/*
		 * Format for at add medarbejdere for et projekt? like jeg gætter på at vi har
		 * et stort, samlet array med alle medarbejdere i firmaet men hvordan vælger man
		 * dem der skal tilføjest? i main programmet mener jeg når man kalder
		 * constructoren
		 * 
		 * 
		 * Eller gør vi det udenfor konstruktøren? kan man først adde medarbejdere efter
		 * aktiviteten er oprettet?
		 */

	}

	public Aktivitet(String navn, int estTime) {
		this.navn = navn;
		this.estTime = estTime;

		/*
		 * Format for at add medarbejdere for et projekt? like jeg gætter på at vi har
		 * et stort, samlet array med alle medarbejdere i firmaet men hvordan vælger man
		 * dem der skal tilføjest? i main programmet mener jeg når man kalder
		 * constructoren
		 * 
		 * 
		 * Eller gør vi det udenfor konstruktøren? kan man først adde medarbejdere efter
		 * aktiviteten er oprettet?
		 */

	}

	public void addToProject(Projekt p) {
		p.addAktivitet(this);
	}

	public void addMedarbejder(Medarbejder m) {
		medarbejdere.add(m);
		m.addAktivitet(this);
	}

	public String toString() {
		return navn;
	}

	public int getEstHours() {
		return estTime;
	}

	// UI method
	public StringProperty getUIName() {
		StringProperty ActivityName = new SimpleStringProperty(navn);
		return ActivityName;
	}

	// UI method
	public StringProperty getUIEstHours() {
		String s = "" + estTime;
		StringProperty estHours = new SimpleStringProperty(s);
		return estHours;
	}
}
