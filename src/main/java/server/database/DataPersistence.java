package server.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import client.Application;
import server.UserSaveable;
import server.ProjectSaveable;

public class DataPersistence implements IProjectRegister, IUserRegister { // {Written by Perry02}
	private static ArrayList<UserSaveable> allUsers = new ArrayList<UserSaveable>();
	private static HashMap<Integer, ProjectSaveable> allProjects = new HashMap<Integer, ProjectSaveable>();
	
	// Cache of search properties
	private static HashMap<String, Integer[]> userIDs = new HashMap<String, Integer[]>();
	private static HashMap<String, Integer> projectIDs = new HashMap<String, Integer>();
	
	public DataPersistence() {		
		allUsers = new ArrayList<UserSaveable>();
		allProjects = new HashMap<Integer, ProjectSaveable>();
		
		userIDs = new HashMap<String, Integer[]>();
		projectIDs = new HashMap<String, Integer>();
		
		System.out.println("DataPersistence: Reset persistent values");
	}
	
	@Override
	public int addUser(UserSaveable user) {
		if (allUsers.contains(user)) { // check for duplicates
			return -1;
		}
		
		if (allUsers.add(user)) { // add user to list
			int id = allUsers.indexOf(user); // gets the user id or position in list
			
			if (userIDs.get(user.getName()) != null) {
				Integer[] oldUserIDs = userIDs.get(user.getName());
				Integer[] newUserIDs = Arrays.copyOf(oldUserIDs, oldUserIDs.length + 1);
				newUserIDs[oldUserIDs.length] = id;
				
				userIDs.put(user.getName(), newUserIDs); // Adds the users to the cache
				
				return id; // return user id or position in list
			}
			
			userIDs.put(user.getName(), new Integer[] {id}); // Caches the users name
			
			return id; // return user id or position in list
		}

		return -1; // return -1 if failed to add user.
	}

	@Override
	public UserSaveable getUser(int userID) {
		return allUsers.get(userID); // return user based on ID
	}

	@Override
	public UserSaveable[] getUser(String userName) {
		if(userIDs.get(userName) == null)
			return null;
		
		Integer[] _userIDs = userIDs.get(userName);
		UserSaveable[] _usersOut = new UserSaveable[_userIDs.length];
		
		for (int i = 0; i < _userIDs.length; i++) {
			_usersOut[i] = getUser(i);
		}
		
		return _usersOut; // return users based on name
	}
	
	@Override
	public UserSaveable[] getAllUsers() {
		
		return allUsers.toArray(new UserSaveable[allUsers.size()]); // returns all users as an array
	}

	@Override
	public ArrayList<UserSaveable> getAllUsersAsList() {
		return new ArrayList<UserSaveable>(allUsers); // returns all users as an array as list
	}
	
	@Override
	public int getUserID(UserSaveable user) {
		return allUsers.indexOf(user); // returns specific userID
	}

	@Override
	public void removeUser(int userID) {
		userIDs.remove(allUsers.get(userID).getName()); // removes cache
		allUsers.remove(userID); // removes specific user
	}

	@Override
	public int addProject(ProjectSaveable project) {
		if (allProjects.containsValue(project)) { // check for duplicates
			return -1;
		}
		
		int newID = allProjects.size(); // find an unused ID
		while (allProjects.containsKey(newID)) {
			newID++;
		}
		
		allProjects.put(newID, project); // add a new project under the ID
		projectIDs.put(project.toString(), newID); // caches project id for a its name
		return newID;
	}

	@Override
	public void addProject(ProjectSaveable project, Integer id) {
		if (allProjects.containsValue(project)) { // check for duplicates
			return;
		}
		
		allProjects.put(id, project); // adds a project with the given key
		projectIDs.put(project.toString(), id); // caches project id for a its name
	}

	@Override
	public ProjectSaveable getProject(Integer id) {
		return allProjects.get(id); // returns project with the given key
	}

	@Override
	public ProjectSaveable getProject(String name) {
		return allProjects.get(projectIDs.get(name)); // returns project with the given name
	}

	@Override
	public ProjectSaveable[] getAllProjects() { // returns all projects as an array
		// a roundabout fix for the toArray() function as it refuses to work with the Projekt class
		Collection<ProjectSaveable> tempValues = allProjects.values(); // all projects currently saved
		ProjectSaveable[] array = new ProjectSaveable[tempValues.size()]; // array the size of the amount of projects
 		tempValues.toArray(array); // then all projects are added to the array
		
		return array; // returns all projects
	}
	
	@Override
	public int getProjectID(ProjectSaveable project) {
		return projectIDs.get(project.toString()); // get project id based on name
	}

	@Override
	public void removeProject(Integer id) {
		projectIDs.remove(allProjects.get(id).toString()); // removes cache
		allProjects.remove(id); // removes project
	}

	@Override
	public ArrayList<ProjectSaveable> getAllProjectsAsList() {
		return new ArrayList<ProjectSaveable>(Arrays.asList(getAllProjects()));
	}
}
