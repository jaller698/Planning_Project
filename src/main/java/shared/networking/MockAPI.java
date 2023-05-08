package shared.networking;

import java.util.ArrayList;
import java.util.Arrays;

import client.ActivityClient;
import client.Application;
import client.ProjectClient;
import client.UserClient;
import server.ActivitySaveable;
import server.ProjectSaveable;
import server.ServerCore;
import server.UserSaveable;
import shared.AActivity;
import shared.AProject;

public class MockAPI implements IProjectPlannerMockAPI { // {Written by Perry02, Jaller698}
	
	// To be assumed as the server side of the API

	@Override
	public UserClient userSignUp(String name, String password) { // {Written by Perry02}
		System.out.print("API: userSignUp - ");
		UserSaveable newUser = new UserSaveable(name, password);
		
		System.out.println("new user: " + newUser);
		return new UserClient(newUser);
	}

	@Override
	public String userLogIn(String name, String password) { // {Written by Perry02}
		System.out.print("API: userLogIn step 1 - name: " + name + " - password: " + password);
		String session = ServerCore.sessions.loginUser(name, password);
		
		System.out.println("API: userLogIn step 2 - session: " + session);
		return session;
	}

	@Override
	public void userLogOff(String session) { // {Written by Perry02}
		System.out.println("API: userLogOff");
		ServerCore.sessions.logoutUser(session);
	}

	@Override
	public UserClient userGetUser(String session, Integer userID) { // {Written by Perry02}
		System.out.print("API: userGetUser - ");
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		UserClient user = new UserClient(ServerCore.users.getUser(userID));
		System.out.println("user: " + user);
		return user;
	}
	
	@Override
	public UserClient userGetUserOfSession(String session) { // {Written by Perry02}
		if (!ServerCore.sessions.checkSession(session))
			return null;

		return new UserClient(ServerCore.users.getUser(ServerCore.sessions.getUserIDOfSession(session)));
	}

	@Override
	public UserClient[] userGetAllUsers(String session) { // {Written by Perry02}
		System.out.print("API: userGetAllUsers - ");
		if (!ServerCore.sessions.checkSession(session))
			return new UserClient[0];
		
		UserClient[] users = UserSaveableToClient(ServerCore.users.getAllUsers());
		System.out.println("API: userGetAllUsers returning user: " + users);
		return users;
	}

	@Override
	public UserClient[] userGetAllUsers(String session, String name) { // {Written by Perry02}
		System.out.print("API: userGetAllUsers by name - ");
		if (!ServerCore.sessions.checkSession(session))
			return new UserClient[0];
		
		UserClient[] users = UserSaveableToClient(ServerCore.users.getUser(name));
		System.out.println("API: userGetAllUsers by name returning user: " + users);
		return users;
	}

	@Override
	public UserClient[] userGetAllUsers(String session, Boolean online) { // {Written by Perry02}
		System.out.print("API: userGetAllUsers online - ");
		if (!ServerCore.sessions.checkSession(session))
			return new UserClient[0];
		
		Integer[] activeUserIDs = ServerCore.sessions.getAllActiveUsers();
		UserClient[] activeUsers = new UserClient[activeUserIDs.length];
		
		for (int i = 0; i < activeUserIDs.length; i++) {
			activeUsers[i] = new UserClient(ServerCore.users.getUser(activeUserIDs[i]));
		}
		
		System.out.println("online user: " + activeUsers);
		return activeUsers;
	}
	
	@Override
	public ArrayList<UserClient> userGetAllUsersAsList(String session) { // {Written by Perry02}
		System.out.println("API: userGetAllUsersAsList");
		return new ArrayList<UserClient>(Arrays.asList(userGetAllUsers(session)));
	}

	@Override
	public ArrayList<UserClient> userGetAllUsersAsList(String session, String name) { // {Written by Perry02}
		System.out.println("API: userGetAllUsersAsList by name");
		return new ArrayList<UserClient>(Arrays.asList(userGetAllUsers(session, name)));
	}

	@Override
	public ArrayList<UserClient> userGetAllUsersAsList(String session, Boolean online) { // {Written by Perry02}
		System.out.println("API: userGetAllUsersAsList online");
		return new ArrayList<UserClient>(Arrays.asList(userGetAllUsers(session, online)));
	}

	@Override
	public Boolean userCheckIfUserIsOnline(String session, Integer userID) { // {Written by Perry02}
		System.out.println("API: userCheckIfUserIsOnline");
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		return ServerCore.sessions.checkIfUserIsActive(userID);
	}
	
	@Override
	public Boolean userUpdateProfile(String session, int userIDTarget, String name, String password) { // {Written by Jaller698 (Originally from Application.java)}
		System.out.print("API: userUpdateProfile - ");
		UserSaveable A = ServerCore.users.getUser(ServerCore.sessions.getUserIDOfSession(session));
		
		if(A != null && A.isAdmin() || ServerCore.sessions.getUserIDOfSession(session) == userIDTarget) {
			UserSaveable M = ServerCore.users.getUser(userIDTarget);
			
			if (!name.isBlank()) {
				M.changeName(name);
				System.out.print("name: " + name);
			}
			if (!password.isBlank()) {
				M.changePassword(password);
				System.out.print("password: " + password);
			}
			
			Application.setConfirmationMSG("Successfully changed "+M.getId()+"("+M.getName()+")'s password");
			
			System.out.println("updated");
			return true;
		}
		System.out.println("failed to update");
		return false;
	}

	@Override
	public Boolean userRemoveUser(String session, int userID) { // {Written by Perry02}
		System.out.print("API: userRemoveUser - ");
		UserSaveable A = ServerCore.users.getUser(ServerCore.sessions.getUserIDOfSession(session));
		
		if(A != null && A.isAdmin()) {
			ServerCore.users.removeUser(userID);
			
			System.out.println("removed: " + userID);
			return true;
		}

		System.out.println("failed to remove: " + userID);
		return false;
	}
	
	@Override
	public ProjectClient projectAddNewProject(String session, String name, int estTime, UserClient projectLeader) {
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		ProjectSaveable project = new ProjectSaveable(name, estTime);
		
		if (projectLeader != null) {
			UserSaveable user = ServerCore.users.getUser(projectLeader.getId());
			project.setProjectLeader(user);
		}
		
		ServerCore.projects.addProject(project);
		
		return new ProjectClient(project);
	}

	@Override
	public ProjectClient projectGetProject(String session, Integer projectID) { // {Written by Perry02}
		System.out.println("API: projectGetProject by id: " + projectID);
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		return new ProjectClient(ServerCore.projects.getProject(projectID));
	}
	
	@Override
	public ProjectClient projectGetProject(String session, String projectName) {
		System.out.println("API: projectGetProject by name: " + projectName);
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		
		return new ProjectClient(ServerCore.projects.getProject(projectName));
	}

	@Override
	public ProjectClient[] projectGetAllProjects(String session) { // {Written by Perry02}
		System.out.println("API: projectGetAllProjects");
		if (!ServerCore.sessions.checkSession(session))
			return new ProjectClient[0];
		
		return ProjectSaveableToClient(ServerCore.projects.getAllProjects());
	}

	@Override
	public ProjectClient[] projectGetAllProjects(String session, String name) { // {Written by Perry02}
		System.out.println("API: projectGetAllProjects by name");
		if (!ServerCore.sessions.checkSession(session))
			return new ProjectClient[0];
		
		ProjectSaveable[] projectsToSort = ServerCore.projects.getAllProjects();
		ProjectClient[] projects = new ProjectClient[projectsToSort.length];
		
		for (int i = 0; i < projectsToSort.length; i++) {
			projects[i] = new ProjectClient(projectsToSort[i]);
		}
		
		return projects;
	}
	
	@Override
	public ArrayList<ProjectClient> projectGetAllProjectsAsList(String session) { // {Written by Perry02}
		System.out.println("API: projectGetAllProjectsAsList");
		return new ArrayList<ProjectClient>(Arrays.asList(projectGetAllProjects(session)));
	}

	@Override
	public ArrayList<ProjectClient> projectGetAllProjectsAsList(String session, String name) { // {Written by Perry02}
		System.out.println("API: projectGetAllProjectsAsList by name");
		return new ArrayList<ProjectClient>(Arrays.asList(projectGetAllProjects(session, name)));
	}
	
	@Override
	public UserClient projectAddProjectLeader(String session, Integer projectID, Integer userID) {
		System.out.println("API: projectAddProjectLeader - add project leader: " + userID + " to project: " + userID);
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		ProjectSaveable project = ServerCore.projects.getProject(projectID);
		if (userID == null) {
			project.setProjectLeader(null);
			return null;
		}
		
		UserSaveable user = ServerCore.users.getUser(userID);
		project.setProjectLeader(user);
		
		return new UserClient(user);		
	}

	@Override
	public ActivityClient activityAddNewActivity(String session, String name, Integer estTime, Integer projectID) {// {Written by Perry02}
		System.out.println("API: activityAddNewActivity");
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		ProjectSaveable project = ServerCore.projects.getProject(projectID);
		
		project.CreateActivity(name, estTime);
		
		return new ActivityClient(project.CreateActivity(name, estTime));
	}

	@Override
	public ActivityClient activityAssignUsertoActivity(String session, ActivityClient activity, UserClient user) {// {Written by Perry02}
		System.out.println("API: activityAssignUsertoActivity");
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		AActivity _activity = ServerCore.projects.getProject(activity.getProject().getName()).getActivities(activity.getName());
		
		if (_activity == null) {
			Application.setConfirmationMSG("Unable to find activity '"+activity.getName()+"' under '"+activity.getProject().getName()+"'");
			return null;
		}
		
		UserSaveable _user = ServerCore.users.getUser(user.getId());
		_user.AssignActivity(_activity);
		
		Application.setConfirmationMSG("Successfully added "+(_user.getId()+1)+"("+_user.getName()+") to activity '"+_activity.getName()+"' under '"+_activity.getProject().getName()+"'");
		
		return activity;
	}
	
	@Override
	public ActivityClient activityEditActivity(String session, ActivityClient activity, String name, Integer estTime) {// {Written by Perry02}
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		AActivity _activity = ServerCore.projects.getProject(activity.getProject().getId()).getActivities(activity.getName());
		
		if (_activity == null)
			return null;
		
		_activity.editActivity(estTime);
		
		return new ActivityClient(_activity);
	}

	@Override
	public int activityUserAddTime(String session, ActivityClient activity, Integer time) {// {Written by Perry02}
		System.out.println("API: activityUserAddTime");
		if (!ServerCore.sessions.checkSession(session))
			return -1;
		
		AActivity _activity = ServerCore.projects.getProject(activity.getProject().getId()).getActivities(activity.getName());
		
		_activity.RegisterHours(ServerCore.users.getUser(ServerCore.sessions.getUserIDOfSession(session)), time);

		return 1;
	}
	
	@Override
	public void activityMoveActivity(String session, ActivityClient activity, ProjectClient destination) {// {Written by Perry02}
		System.out.println("API: activityMoveActivity");
		if (!ServerCore.sessions.checkSession(session))
			return;
		
		ProjectSaveable dest = ServerCore.projects.getProject(destination.getId());
		ProjectSaveable proj = ServerCore.projects.getProject(activity.getProject().getId());
		AActivity act = ServerCore.projects.getProject(activity.getProject().getId()).getActivities(activity.getName());
		
		AProject oldproj = activity.getProject();
		
		act.SwitchProject(proj);
		
		destination = new ProjectClient(dest);
		oldproj = new ProjectClient(proj);
		activity = new ActivityClient(act);
	}

	@Override
	public Boolean activityRemoveActivity(String session, ActivityClient activity) {// {Written by Perry02}
		System.out.println("API: activityRemoveActivity");
		if (!ServerCore.sessions.checkSession(session))
			return false;
		
		ProjectSaveable project = ServerCore.projects.getProject(activity.getProject().getId());
		
		project.RemoveActivity(activity);
		
		return true;
	}
	
	
	
	private UserClient[] UserSaveableToClient(UserSaveable[] usersSaveables) {  // {Written by Perry02}
		System.out.println("API: UserSaveableToClient");
		
		UserClient[] userClients = new UserClient[usersSaveables.length];
		
		for (int i = 0; i < usersSaveables.length; i++) {
			userClients[i] = new UserClient(usersSaveables[i]);
		}
		
		return userClients;
	}
	
	private ProjectClient[] ProjectSaveableToClient(ProjectSaveable[] projectSaveables) {  // {Written by Perry02}
		System.out.println("API: ProjectSaveableToClient");
		
		ProjectClient[] projectClients = new ProjectClient[projectSaveables.length];
		
		for (int i = 0; i < projectSaveables.length; i++) {
			projectClients[i] = new ProjectClient(projectSaveables[i]);
		}
		
		return projectClients;
	}
}
