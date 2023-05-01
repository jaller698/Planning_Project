package application;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {
	String navn;
	ArrayList<Activity> Activityer = new ArrayList<Activity>();
	public int estTid;
	ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();
	public Medarbejder leder;
	private int projID;
	
	public Project(String navn) {
		this.navn=navn;
		Application.projects.addProject(this);
		setID();
		leder = Application.getCurrentActiveUser();
		Application.getCurrentActiveUser().addProject(this);
		Application.setConfirmationMSG("Successfully created project '"+this.navn+"'("+this.projID+")");
	}
	public Project(String navn, Medarbejder leder, int estT) {
		this.navn = navn;
		Application.projects.addProject(this);
		setID();
		this.addProjectLeder(leder);
		this.estTid = estT;
		Application.setConfirmationMSG("Successfully created project '"+this.navn+"'("+this.projID+")");
	}
	
	public Project(String navn, ArrayList<Medarbejder> medarbejdere, Medarbejder leder) {
		this.navn=navn;
		this.medarbejdere = medarbejdere;
	}

	
	public String toString() {
		return this.navn;
	}
	public void addActivity(Activity A) {
		this.Activityer.add(A);
	}
	public void getActivityer() {
		for(Activity a : this.Activityer) {
			System.out.println(a.navn);	
		}
	}
	public Activity getActivity(String actName) {
		for(Activity a : this.Activityer) {
			if(a.navn.equals(actName))
				return a;
		}
		return null;
	}
	
	public void addProjectLeder(Medarbejder m) {
		//properties for den givne medarbejder
		this.leder = m;
		if(m != null)
			m.addProject(this);
	}
	
	//UI method
	public StringProperty getUIName() {
		StringProperty ProjectName = new SimpleStringProperty(navn);
		return ProjectName;
	}
	public ArrayList<Activity> getActivityList(){
		return Activityer;
	}
	public int getID() {
		return projID;
	}
	private void setID() {
		String id = ""+ Application.projects.getProjectID(this);
		switch (id.length()){   /* choice 1 */
			case 1:
				id= "00" + id;
				break;
			case 2: 
				id="0"+id;
				break;
			case 3:
				break;
			default:
				return;
		}
		projID = Integer.parseInt("" + Application.getYear() + id );
	}
	public Medarbejder getProjLeder() {
		return leder;
	}
	
	public void assignActivity(String actName, Medarbejder workerToBeAssigned, Medarbejder actor) {
		if ((workerToBeAssigned == actor || actor == leder) && leder == Application.getCurrentActiveUser()) {
			Activity a = getActivity(actName);
			if (getActivity(actName) != null)
				getActivity(actName).addMedarbejder(workerToBeAssigned);
		}
	}
	
	public void removeActivity(String actName, Medarbejder actor) {
		if (leder == Application.getCurrentActiveUser()) {
			Activity a = getActivity(actName);
			Activityer.remove(a);
		}
	}
}
