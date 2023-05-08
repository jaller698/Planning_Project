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
	
	public DataPersistence() {  // {Written by Perry02}
		allUsers = new ArrayList<UserSaveable>();
		allProjects = new HashMap<Integer, ProjectSaveable>();
		
		userIDs = new HashMap<String, Integer[]>();
		projectIDs = new HashMap<String, Integer>();
		
		System.out.println("DataPersistence: Reset persistent values");
	}
	
	@Override
	public int addUser(UserSaveable user) {  // {Written by Perry02}
		System.out.println("DataPersistence: addUser:" + user.getName());
		if (allUsers.contains(user)) { // check for duplicates
			return -1;
		}
		
		if (allUsers.add(user)) { // add user to list
			int id = allUsers.indexOf(user); // gets the user id or position in list
			System.out.println("DataPersistence: userID:" + id);
			
			if (userIDs.get(user.getName()) != null) {
				Integer[] oldUserIDs = userIDs.get(user.getName());
				Integer[] newUserIDs = Arrays.copyOf(oldUserIDs, oldUserIDs.length + 1);
				newUserIDs[oldUserIDs.length] = id;
				
				userIDs.put(user.getName(), newUserIDs); // Adds the users to the cache
				
				return id; // return user id or position in list
			} else {
				userIDs.put(user.getName(), new Integer[] {id}); // Caches the users name
			}
			
			return id; // return user id or position in list
		}

		return -1; // return -1 if failed to add user.
	}

	@Override
	public UserSaveable getUser(int userID) { // {Written by Perry02}
		System.out.print("DataPersistence: getUser by id: " + userID);
		return allUsers.get(userID); // return user based on ID
	}

	@Override
	public UserSaveable[] getUser(String userName) { // {Written by Perry02}
		System.out.print("DataPersistence: getUser by name: " + userName);
		
		if(userIDs.get(userName) == null)
			return null;
		
		Integer[] _userIDs = userIDs.get(userName);
		UserSaveable[] _usersOut = new UserSaveable[_userIDs.length];
		
		System.out.print("DataPersistence: getUser by name - users: ");
		for (int i = 0; i < _userIDs.length; i++) {
			System.out.print(_userIDs[i].toString());
		}
		System.out.println();
		
		for (int i = 0; i < _userIDs.length; i++) {
			_usersOut[i] = getUser(_userIDs[i]);
		}
		
		return _usersOut; // return users based on name
	}
	
	@Override
	public UserSaveable[] getAllUsers() { // {Written by Perry02}
		System.out.println("DataPersistence: getAllUsers");
		return allUsers.toArray(new UserSaveable[allUsers.size()]); // returns all users as an array
	}

	@Override
	public ArrayList<UserSaveable> getAllUsersAsList() { // {Written by Perry02}
		return new ArrayList<UserSaveable>(allUsers); // returns all users as an array as list
	}
	
	@Override
	public int getUserID(UserSaveable user) { // {Written by Perry02}
		return allUsers.indexOf(user); // returns specific userID
	}

	@Override
	public void removeUser(int userID) { // {Written by Perry02}
		userIDs.remove(allUsers.get(userID).getName()); // removes cache
		allUsers.remove(userID); // removes specific user
	}

	@Override
	public int addProject(ProjectSaveable project) { // {Written by Perry02}
		if (allProjects.containsValue(project) || projectIDs.containsKey(project.getName())) { // check for duplicates
			return -1;
		}
		
		int newID = allProjects.size(); // find an unused ID
		while (allProjects.containsKey(newID)) {
			newID++;
		}
		
		project.SetId(newID);
		allProjects.put(newID, project); // add a new project under the ID
		projectIDs.put(project.getName(), newID); // caches project id for a its name
		return newID;
	}

	@Override
	public void addProject(ProjectSaveable project, Integer id) { // {Written by Perry02}
		if (allProjects.containsValue(project) || projectIDs.containsKey(project.getName())) { // check for duplicates
			return;
		}
		
		project.SetId(id);
		allProjects.put(id, project); // adds a project with the given key
		projectIDs.put(project.getName(), id); // caches project id for a its name
	}

	@Override
	public ProjectSaveable getProject(Integer id) { // {Written by Perry02}
		return allProjects.get(id); // returns project with the given key
	}

	@Override
	public ProjectSaveable getProject(String name) { // {Written by Perry02}
		return allProjects.get(projectIDs.get(name)); // returns project with the given name
	}

	// returns all projects as an array
	@Override
	public ProjectSaveable[] getAllProjects() { // {Written by Perry02}
		// a roundabout fix for the toArray() function as it refuses to work with the Projekt class
		Collection<ProjectSaveable> tempValues = allProjects.values(); // all projects currently saved
		ProjectSaveable[] array = new ProjectSaveable[tempValues.size()]; // array the size of the amount of projects
 		tempValues.toArray(array); // then all projects are added to the array
		
		return array; // returns all projects
	}
	
	@Override
	public int getProjectID(ProjectSaveable project) { // {Written by Perry02}
		return projectIDs.get(project.toString()); // get project id based on name
	}

	@Override
	public void removeProject(Integer id) { // {Written by Perry02}
		projectIDs.remove(allProjects.get(id).toString()); // removes cache
		allProjects.remove(id); // removes project
	}

	@Override
	public ArrayList<ProjectSaveable> getAllProjectsAsList() { // {Written by Perry02}
		return new ArrayList<ProjectSaveable>(Arrays.asList(getAllProjects()));
	}
}
