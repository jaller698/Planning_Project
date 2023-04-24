package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Aktivitet {
	public Projekt p;
	public HashMap<Integer, Integer> timeWorked = new HashMap<Integer, Integer>(); // <>userID, hours>
	public String navn;
	public int estTime;

	// creating a new aktivity has to automatically add said activity to the
	// project...
	// can i do that in the constructor?
	public Aktivitet(String navn, int estTime, Projekt p) {
		if(!p.getProjLeder().equals(Application.getCurrentActiveUser())) {
			Application.setConfirmationMSG("Insufficient privileges to create activity  '"+navn+"' under Project '"+p.navn+"'");
			return;
		}
		this.navn = navn;
		this.p = p;
		addToProject(this.p);
		this.estTime = estTime;
		Application.setConfirmationMSG("Successfully created activity '"+ navn+"' with "+estTime +" hours under '"+p.navn +"'");
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
		m.addAktivitet(this);
	}

	public String toString() {
		return navn;
	}

	public int getEstHours() {
		return estTime;
	}
	
	public void editActivity(String navn) {
		this.navn = navn;
	}
	
	public void editActivity(int estTime) {
		this.estTime = estTime;
	}
	
	public void editActivity(String navn, int estTime) { // edit an activity after the fact
		this.navn = navn;
		this.estTime = estTime;
	}
	
	public void addTime(int hours, Medarbejder user) { // add time to an activity
		// get a userID to associate the time with
		int userID = Application.workers.getUserID(user); 
		
		// adds the time to the hashmap
		//timeWorked.getOrDefault(userID, 0)
		timeWorked.put(userID, timeWorked.computeIfAbsent(userID, k -> 0) + hours);
		
		addMedarbejder(user);
	}
	
	public int getTimeDone() { //gets the total time done
		int totalTime = 0;
		
		for (Iterator<Integer> iterator = timeWorked.keySet().iterator(); iterator.hasNext();) {
			totalTime += (Integer) iterator.next();
		}
		
		return totalTime;
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
