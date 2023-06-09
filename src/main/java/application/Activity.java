package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Activity {
	public Project p;
	public HashMap<Integer, Integer> timeWorked = new HashMap<Integer, Integer>(); // <>userID, hours>
	public String navn;
	public int estTime;

	//Natascha
	public Activity(String navn, int estTime, Project p) {
		if (!p.getProjLeader().equals(Application.getCurrentActiveUser())) { /* choice 1 */
			Application.setConfirmationMSG(
					"Insufficient privileges to create activity  '" + navn + "' under Project '" + p.navn + "'");
			return;
		}
		this.navn = navn;
		this.p = p;
		addToProject(this.p);
		this.estTime = estTime;
		Application.setConfirmationMSG(
				"Successfully created activity '" + navn + "' with " + estTime + " hours under '" + p.navn + "'");

	}

	public void addToProject(Project p) {
		p.addActivity(this);
	}

	public void addMedarbejder(Medarbejder m) {
		m.addActivity(this);
	}

	public String toString() {
		return navn;
	}

	public int getEstHours() {
		return estTime;
	}

	public void editActivity(int estTime) {
		this.estTime = estTime;
	}


	
	//Martin
	public void addTime(int hours, Medarbejder user) { // add time to an activity
		// get a userID to associate the time with
		int userID = Application.workers.getUserID(user);

		// adds the time to the hashmap
		// timeWorked.getOrDefault(userID, 0)
		timeWorked.put(userID, timeWorked.computeIfAbsent(userID, k -> 0) + hours);

		addMedarbejder(user);
	}

	public int getTimeDoneByUser(Medarbejder user) { // gets the time done by a specific user
		int userID = Application.workers.getUserID(user);

		return timeWorked.get(userID);
	}

	// UI method
	public StringProperty getUIName() {
		StringProperty ActivityName = new SimpleStringProperty(navn);
		return ActivityName;
	}

	// UI method
	public ObservableValue<Integer> getUIEstHours() {
		ObservableValue<Integer> estHours = new SimpleIntegerProperty(estTime).asObject();
		return estHours;
	}
}
