package shared;

import java.util.ArrayList;

import client.Application;
import shared.AUser;

public abstract class AProject { // {Written by Jaller698, GaySupremacy and McQueen24, refactored by Perry02 (Original file: Projekt.java)}
	protected String name; // the project name
	public String getName() {return name;}

	protected Integer id; // the projectID
	public Integer getId() {return id;}
	
	protected int estTime; // the estimated time for this project
	public int getEstTime() {return estTime;}

	protected AUser projectLeader; // the projectLeader
	public AUser getProjectLeader() {return projectLeader;}
	public abstract void setProjectLeader(AUser projectLeader);
	
	protected ArrayList<AActivity> activities = new ArrayList<AActivity>(); // the activities tied to this project
	public ArrayList<AActivity> getActivities() {return activities;}
	public AActivity getActivities(String name) {
		for (AActivity aActivity : activities) {
			if (aActivity.getName().equals(name)) {
				return aActivity;
			}
		}
		return null;
	}
	

	
	protected AProject(String name, int estTime) { // {Written by GaySupremacy}
		this.name = name;
		this.estTime = estTime;
	}
	
	@Override
	public String toString() { // {Written by GaySupremacy}
		return "("+id+")"+name;
	}
	
	public int getID() {
		String _id = this.id.toString();
		switch (_id.length()){   /* choice 1 */
			case 1:
				_id= "00" + _id;
				break;
			case 2: 
				_id="0"+_id;
				break;
			case 3:
				break;
			default:
				return -1;
		}
		return Integer.parseInt("" + Application.getYear() + _id);
	}
	
	// create an activity
	public abstract AActivity CreateActivity(String name, int estTime);
	
	// add a given activity to this project if it does not exist here
	public abstract void AddActivity(AActivity activity);
	
	// moves an activity to another project
	public abstract void MoveActivity(AActivity activity, AProject destination);
	
	public abstract void RemoveActivity(AActivity activity);
}
