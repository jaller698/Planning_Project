package application;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medarbejder {
	public String navn;
	public String password;
	int arbejdsTimer;
	private boolean admin;
	private boolean projectLeader;
	public ArrayList<Project> p = new ArrayList<Project>();
	public ArrayList<Activity> a = new ArrayList<Activity>();

	public Medarbejder(String navn, String password) {
		this.navn = navn;
		this.password = password;
		Application.workers.addUser(this);
	}

	//public boolean tjekLogin() {
	//	return true;
	//}

	public String toString() {
		return this.navn;
	}

	//public void appointProjectleader(Project p, Medarbejder m) {

	//	p.addProjectLeader(this);
	//}

	public ArrayList<Project> getProjects() {
		return p;
	}

	//public ArrayList<Activity> getPA() {

	//	return a;
	//}

	public void addActivity(Activity A) {
		// fantastiske navne. Slet ikke forvirrende.
		this.a.add(A);
	}
	
	public Activity getActivity(String actName) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).navn.equals(actName))
				return a.get(i);
		}
		return null;
	}

	public void addProject(Project P) {
		// fantastiske navne. Slet ikke forvirrende.
		this.p.add(P);
		//System.out.println(P.toString() + "added to " + this.navn);

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

