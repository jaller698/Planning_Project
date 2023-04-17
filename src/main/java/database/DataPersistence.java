package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.Application;
import application.Medarbejder;
import application.Projekt;

public class DataPersistence implements IProjectRegister, IUserRegister {
	private static ArrayList<Medarbejder> allWorkers = new ArrayList<Medarbejder>();
	private static HashMap<Integer, Projekt> allProjects = new HashMap<Integer, Projekt>();
	
	// Cache of search properties
	private static HashMap<String, Integer> workerIDs = new HashMap<String, Integer>();
	private static HashMap<String, Integer> projectIDs = new HashMap<String, Integer>();
	
	private static Medarbejder currentEmployee;
	
	public ArrayList<Medarbejder> getWorkers(){
		return allWorkers;
	}
	
	public static void setMedarbejder(Medarbejder m) {
		currentEmployee = m;
	}
	public static Medarbejder getMedarbejder() {
		return currentEmployee;
	}
	
	
	
	public DataPersistence() {
		allWorkers = new ArrayList<Medarbejder>();
		allProjects = new HashMap<Integer, Projekt>();
		
		workerIDs = new HashMap<String, Integer>();
		projectIDs = new HashMap<String, Integer>();
	}
	
	@Override
	public int addUser(Medarbejder user) {
		if (allWorkers.contains(user)) { // check for duplicates
			return -1;
		}
		
		if (allWorkers.add(user)) { // add worker to list
			int id = allWorkers.indexOf(user); // gets the worker id or position in list
			workerIDs.put(user.navn, id); // Caches the worker name
			
			return id; // return worker id or position in list
		}

		return -1; // return -1 if failed to add worker.
	}

	@Override
	public Medarbejder getUser(int userID) {
		return allWorkers.get(userID); // return worker based on ID
	}

	@Override
	public Medarbejder getUser(String userName) {
		if(workerIDs.get(userName) == null)
			return null;
		return allWorkers.get(workerIDs.get(userName)); // return worker based on name
	}

	@Override
	public Medarbejder[] getAllUsers() {
		return (Medarbejder[]) allWorkers.toArray(); // returns all workers as an array
	}
	
	@Override
	public int getUserID(Medarbejder user) {
		return allWorkers.indexOf(user); // returns specific workerID
	}

	@Override
	public void removeUser(int userID) {
		workerIDs.remove(allWorkers.get(userID).navn); // removes cache
		allWorkers.remove(userID); // removes specific worker
	}

	@Override
	public int addProject(Projekt project) {
		if (allProjects.containsValue(project)) { // check for duplicates
			return -1;
		}
		
		int newID = allProjects.size(); // find an unused ID
		allProjects.put(newID, project); // add a new project under the ID
		projectIDs.put(project.toString(), newID); // caches project id for a its name
		return newID;
	}

	@Override
	public void addProject(Projekt project, Integer id) {
		if (allProjects.containsValue(project)) { // check for duplicates
			return;
		}
		
		allProjects.put(id, project); // adds a project with the given key
		projectIDs.put(project.toString(), id); // caches project id for a its name
	}

	@Override
	public Projekt getProject(Integer id) {
		return allProjects.get(id); // returns project with the given key
	}

	@Override
	public Projekt getProject(String name) {
		return allProjects.get(projectIDs.get(name)); // returns project with the given name
	}

	@Override
	public Projekt[] getAllProjects() {
		return (Projekt[]) allProjects.values().toArray(); // returns all projects
	}
	
	@Override
	public int getProjectID(Projekt project) {
		return projectIDs.get(project.toString()); // get project id based on name
	}

	@Override
	public void removeProject(Integer id) {
		projectIDs.remove(allProjects.get(id).toString()); // removes cache
		allProjects.remove(id); // removes project
	}
}
