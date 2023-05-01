package shared.networking;

import java.util.ArrayList;

import client.ActivityClient;
import client.ProjectClient;
import client.UserClient;

public interface IProjectPlannerMockAPI { // {Written by Perry02}
	
	// To be assumed as the client side of the API
	
	// login and sessions:
	public UserClient userSignUp(String name, String password); // creates a new user and returns the new user or null if failed in signUp
	
	public String userLogIn(String name, String password); // logs in an existing user and returns an active session
	
	public void userLogOff(String session); // logs a session off
	
	
	
	// users	
	public UserClient userGetUser(String session, Integer userID); // returns a user by id
	
	public UserClient[] userGetAllUsers(String session); // returns all users
	public UserClient[] userGetAllUsers(String session, String name); // returns all users by name
	public UserClient[] userGetAllUsers(String session, Boolean online); // returns all users online
	public ArrayList<UserClient> userGetAllUsersAsList(String session); // as list versions
	public ArrayList<UserClient> userGetAllUsersAsList(String session, String name);
	public ArrayList<UserClient> userGetAllUsersAsList(String session, Boolean online);
	
	public Boolean userCheckIfUserIsOnline(String session, Integer userID); // returns true if user is online by id

	public Boolean userUpdateProfile(String session, String name); // updates a users name
	public Boolean userUpdateProfile(String session, String name, String password); // updates a users name or password, null can be passed
	public Boolean userUpdateProfile(String session, int userIDTarget, String name, String password); // updates a specific users name or password, null can be passed
	
	public Boolean userRemoveUser(String session, int userID); // removes given user
	
	
	
	// projects
	public Integer projectAddNewProject(String session, ProjectClient project); // creates a new project, returns the projectID if successful otherwise null
	public void projectAddNewProject(String session, ProjectClient project, Integer projectID); // creates a new project with a specific projectID
	
	public ProjectClient projectGetProject(String session, Integer projectID); // get a project by projectID
	
	public ProjectClient[] projectGetAllProjects(String session); // returns all project
	public ProjectClient[] projectGetAllProjects(String session, String name); // returns all projects by the given name
	public ArrayList<ProjectClient> projectGetAllProjectsAsList(String session); // as list versions
	public ArrayList<ProjectClient> projectGetAllProjectsAsList(String session, String name);
	
	
	public Boolean projectAddUserToProject(String session, Integer projectID); // add the user session to the project
	public Boolean projectAddUserToProject(String session, Integer projectID, Integer userID); // add the user by id to the project
	public Boolean projectAddUserToProject(String session, Integer projectID, Integer[] userIDs); // add all users by id to the project
	
	public Boolean projectRemoveProject(String session, ProjectClient project); // removes given project
	
	
	
	// activities
	public void activityAddNewActivity(String session, ActivityClient activity, Integer projectID); // adds a new activity for given projectID
	public void activityAddNewActivity(String session, ActivityClient activity, ProjectClient project); // adds a new activity for given project
	
	public void activityAssignUsertoActivity(String session, ActivityClient activity, UserClient user); // adds given user to the given activity
	
	public int activityUserAddTime(String session, String activityID, Integer time); // add time associated with a user to an activity, returns total time done

	public Boolean activityRemoveActivity(String session, ActivityClient activity); // removes given activity
}
