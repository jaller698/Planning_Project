package application;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Projekt {
	String navn;
	ArrayList<Aktivitet> aktiviteter = new ArrayList<Aktivitet>();
	int estTid;
	ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();
	public Medarbejder leder;
	private int projID;
	
	public Projekt(String navn) {
		this.navn=navn;
		Application.projects.addProject(this);
		setID();
		leder = Application.getMedarbejder();
		Application.setConfirmationMSG("Successfully created project '"+this.navn+"'("+this.projID+")");
	}
	
	
	public Projekt(String navn, ArrayList<Medarbejder> medarbejdere, Medarbejder leder) {
		this.navn=navn;
		this.medarbejdere = medarbejdere;
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
	public Aktivitet getAktivitet(String actName) {
		for(Aktivitet a : this.aktiviteter) {
			if(a.navn.equals(actName))
				return a;
		}
		return null;
	}
	public void addProjektLeder(Medarbejder m) {
		//properties for den givne medarbejder
		this.leder = m;
	}
	
	//UI method
	public StringProperty getUIName() {
		StringProperty ProjectName = new SimpleStringProperty(navn);
		return ProjectName;
	}
	public ArrayList<Aktivitet> getActivityList(){
		return aktiviteter;
	}
	public int getID() {
		return projID;
	}
	private void setID() {
		String id = ""+ Application.projects.getProjectID(this);
		switch (id.length()){
			case 1:
				id= "00" + id;
				break;
			case 2: 
				id="0"+id;
				break;
		}
		projID = Integer.parseInt("" + Application.getYear() + id );
	}
	public Medarbejder getProjLeder() {
		return leder;
	}
	
	public void assignActivity(String actName, Medarbejder workerToBeAssigned, Medarbejder actor) {
		if ((workerToBeAssigned == actor || actor == leder) && leder == Application.getMedarbejder()) {
			Aktivitet a = getAktivitet(actName);
			if (getAktivitet(actName) != null)
				getAktivitet(actName).addMedarbejder(workerToBeAssigned);
		}
	}

}
