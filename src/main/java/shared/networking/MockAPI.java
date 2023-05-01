package shared.networking;

import java.util.ArrayList;
import java.util.Arrays;

import client.ActivityClient;
import client.Application;
import client.ProjectClient;
import client.UserClient;
import server.ProjectSaveable;
import server.ServerCore;
import server.UserSaveable;

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
	public Boolean userUpdateProfile(String session, String name) { // {Written by Perry02}
		return userUpdateProfile(session, ServerCore.sessions.getUserIDOfSession(session), name, null);
	}

	@Override
	public Boolean userUpdateProfile(String session, String name, String password) { // {Written by Perry02}
		return userUpdateProfile(session, ServerCore.sessions.getUserIDOfSession(session), name, password);
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
	public Integer projectAddNewProject(String session, ProjectClient project) { // {Written by Perry02}
		System.out.println("API: projectAddNewProject: " + project.toString());
		if (!ServerCore.sessions.checkSession(session))
			return -1;
		
		int id = ServerCore.projects.addProject(new ProjectSaveable(project));
		
		System.out.println("API: projectAddNewProject saved project as " + project.toString());
		return id;
	}

	@Override
	public void projectAddNewProject(String session, ProjectClient project, Integer projectID) { // {Written by Perry02}
		System.out.println("API: projectAddNewProject: " + project.toString() + " under id: " + projectID);
		if (!ServerCore.sessions.checkSession(session))
			return;
		
		System.out.println("API: projectAddNewProject saved project as " + project.toString());
		ServerCore.projects.addProject(new ProjectSaveable(project), projectID);
	}

	@Override
	public ProjectClient projectGetProject(String session, Integer projectID) { // {Written by Perry02}
		System.out.println("API: projectGetProject");
		if (!ServerCore.sessions.checkSession(session))
			return null;
		
		return new ProjectClient(ServerCore.projects.getProject(projectID));
	}
	
	@Override
	public ProjectClient projectGetProject(String session, String projectName) {
		// TODO Auto-generated method stub
		return null;
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
		UserSaveable user = ServerCore.users.getUser(userID);
		
		project.setProjectLeader(user);
		
		return new UserClient(user);		
	}

	@Override
	public Boolean projectAddUserToProject(String session, Integer projectID) {
		System.out.println("API: projectAddUserToProject TODO");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean projectAddUserToProject(String session, Integer projectID, Integer userID) {
		System.out.println("API: projectAddUserToProject TODO");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean projectAddUserToProject(String session, Integer projectID, Integer[] userIDs) {
		System.out.println("API: projectAddUserToProject TODO");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean projectRemoveProject(String session, ProjectClient project) {
		System.out.println("API: projectRemoveProject TODO");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activityAddNewActivity(String session, ActivityClient activity, Integer projectID) {
		System.out.println("API: activityAddNewActivity TODO");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activityAddNewActivity(String session, ActivityClient activity, ProjectClient project) {
		System.out.println("API: activityAddNewActivity TODO");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activityAssignUsertoActivity(String session, ActivityClient activity, UserClient user) {
		System.out.println("API: activityAssignUsertoActivity TODO");
		// TODO Auto-generated method stub
		
	}

	@Override
	public int activityUserAddTime(String session, String activityID, Integer time) {
		System.out.println("API: activityUserAddTime TODO");
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean activityRemoveActivity(String session, ActivityClient activity) {
		System.out.println("API: activityRemoveActivity TODO");
		// TODO Auto-generated method stub
		return null;
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
