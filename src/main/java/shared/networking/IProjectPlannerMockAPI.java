package shared.networking;

import shared.AActivity;
import shared.AProject;
import shared.AUser;

public interface IProjectPlannerMockAPI {
	
	// login and sessions:
	public int userSignUp(String name, String password); // creates a new user and returns userID or -1 if failed in signUp
	
	public String userLogIn(String name, String password); // logs in an existing user and returns an active session
	
	public void userLogOff(String session); // logs a session off
	
	
	
	// users	
	public AUser userGetUser(String session, Integer userID); // returns a user by id
	
	public AUser[] userGetAllUsers(String session); // returns all users
	public AUser[] userGetAllUsers(String session, String name); // returns all users by name
	public AUser[] userGetAllUsers(String session, Boolean online); // returns all users online
	
	public Boolean userCheckIfUserIsOnline(String session, Integer userID); // returns true if user is online by id

	public Boolean userUpdateProfile(String session, String name); // updates a users name
	public Boolean userUpdateProfile(String session, String name, String password); // updates a users name or password, null can be passed
	
	public Boolean userRemoveUser(String session, AUser user); // removes given user
	
	
	
	// projects
	public String projectAddNewProject(String session, AProject project); // creates a new project, returns the projectID if successful otherwise null
	public Boolean projectAddNewProject(String session, AProject project, String projectID); // creates a new project with a specific projectID
	
	public AProject projectGetProject(String session, String projectID); // get a project by projectID
	
	public AProject[] projectGetAllProjects(String session); // returns all project
	public AProject[] projectGetAllProjects(String session, String name); // returns all projects by the given name
	
	public Boolean projectAddUserToProject(String session, String projectID); // add the user session to the project
	public Boolean projectAddUserToProject(String session, String projectID, Integer userID); // add the user by id to the project
	public Boolean projectAddUserToProject(String session, String projectID, Integer[] userIDs); // add all users by id to the project
	
	public Boolean projectRemoveProject(String session, AProject project); // removes given project
	
	
	
	// activities
	public void activityAddNewActivity(String session, AActivity activity, String projectID); // adds a new activity for given projectID
	public void activityAddNewActivity(String session, AActivity activity, AProject project); // adds a new activity for given project
	
	public void activityAssignUsertoActivity(String session, AActivity activity, AUser user); // adds given user to the given activity
	
	public int activityUserAddTime(String session, String activityID, Integer time); // add time associated with a user to an activity, returns total time done

	public Boolean activityRemoveActivity(String session, AActivity activity); // removes given activity
}
