package shared;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import client.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medarbejder {
	public String navn;
	public String password;
	int arbejdsTimer;
	private boolean admin;
	private boolean projectLeader;
	public ArrayList<Projekt> p = new ArrayList<Projekt>();
	public ArrayList<Aktivitet> a = new ArrayList<Aktivitet>();

	public Medarbejder(String navn, String password) {
		this.navn = navn;
		this.password = password;
		Application.workers.addUser(this);
	}

	public boolean tjekLogin() {
		return true;
	}

	public String toString() {
		return this.navn;
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
		// fantastiske navne. Slet ikke forvirrende.
		this.a.add(A);
	}
	
	public Aktivitet getAktivitet(String actName) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).navn.equals(actName))
				return a.get(i);
		}
		return null;
	}

	public void addProjekt(Projekt P) {
		// fantastiske navne. Slet ikke forvirrende.
		this.p.add(P);
		System.out.println(P.toString() + "added to " + this.navn);

	}

	// UI method
	public StringProperty getUIName() {
		StringProperty EmployeeName = new SimpleStringProperty(navn);
		return EmployeeName;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean b) {
		admin = b;
	}

	public Boolean isProjectleader() {
		return projectLeader;
	}

	public void setProjectLeader(Boolean b) {
		projectLeader = b;
	}

	public void changePassword(String currentPWD, String newPWD) {
		if (this.password.equals(currentPWD)) {
			password = newPWD;
			Application.setConfirmationMSG("Successfully changed password");
		}
	}

	public void changePassword(String newPWD) {
		this.password = newPWD;
		Application.setConfirmationMSG("Successfully changed password");
	}
}

class ProjektLeder extends Medarbejder {

	public ProjektLeder(String navn, String password) {
		super(navn, password);
		// TODO Auto-generated constructor stub
	}

}
