package application;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {
	String navn;
	ArrayList<Activity> Activities = new ArrayList<Activity>();
	public int estTid;
	public ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();
	public Medarbejder Leader;
	private int projID;
//
	public Project(String navn) {
		this.navn=navn;
		Application.projects.addProject(this);
		setID();
		Leader = Application.getCurrentActiveUser();
		Application.getCurrentActiveUser().addProject(this);
		Application.setConfirmationMSG("Successfully created project '"+this.navn+"'("+this.projID+")");
	}

	public Project(String navn, Medarbejder Leader, int estT) {
		this.navn = navn;
		Application.projects.addProject(this);
		setID();
		this.addProjectLeader(Leader);
		this.estTid = estT;
		Application.setConfirmationMSG("Successfully created project '" + this.navn + "'(" + this.projID + ")");
	}

	public Project(String navn, int estT) {
		this.navn = navn;
		Application.projects.addProject(this);
		setID();
		this.estTid = estT;
		Application.setConfirmationMSG("Successfully created project '" + this.navn + "'(" + this.projID + ")");
	}

	public String toString() {
		return this.navn;
	}
	public void addActivity(Activity A) {
		this.Activities.add(A);
	}
	//public void getActivities() {
	//	for(Activity a : this.Activities) {
	//		System.out.println(a.navn);	
	//	}
	//}
	public Activity getActivity(String actName) {
		for(Activity a : this.Activities) {
			if(a.navn.equals(actName))
				return a;
		}
		return null;
	}

	public void addProjectLeader(Medarbejder m) {
		// properties for den givne medarbejder
		if (this.Leader == null || Application.getCurrentActiveUser().isAdmin() || Application.getCurrentActiveUser().equals(this.getProjLeader())) {
			this.Leader = m;
			if(m != null)
				m.addProject(this);
		}
		else {
			System.out.println("Project already has leader");
		}
	}

	// UI method
	public StringProperty getUIName() {
		StringProperty ProjectName = new SimpleStringProperty(navn);
		return ProjectName;
	}
	public ArrayList<Activity> getActivityList(){
		return Activities;
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
		projID = Integer.parseInt("" + Application.getYear() + id);
	}
	public Medarbejder getProjLeader() {
		return Leader;
	}

	public void assignActivity(String actName, Medarbejder workerToBeAssigned, Medarbejder actor) {
		if ((workerToBeAssigned == actor || actor == Leader) && Leader == Application.getCurrentActiveUser()) {
			Activity a = getActivity(actName);
			if (getActivity(actName) != null)
				getActivity(actName).addMedarbejder(workerToBeAssigned);
		}
	}

	public void removeActivity(String actName, Medarbejder actor) {
		if (Leader == Application.getCurrentActiveUser()) {
			Activity a = getActivity(actName);
			Activities.remove(a);
		}
	}
}
