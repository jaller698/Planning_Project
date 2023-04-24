package shared.networking;

import shared.AActivity;
import shared.AProject;
import shared.AUser;

public class MockAPI implements IProjectPlannerMockAPI {

	@Override
	public int userSignUp(String name, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String userLogIn(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void userLogOff(String session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AUser userGetUser(String session, Integer userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AUser[] userGetAllUsers(String session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AUser[] userGetAllUsers(String session, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AUser[] userGetAllUsers(String session, Boolean online) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean userCheckIfUserIsOnline(String session, Integer userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean userUpdateProfile(String session, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean userUpdateProfile(String session, String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean userRemoveUser(String session, AUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String projectAddNewProject(String session, AProject project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean projectAddNewProject(String session, AProject project, String projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AProject projectGetProject(String session, String projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AProject[] projectGetAllProjects(String session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AProject[] projectGetAllProjects(String session, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean projectAddUserToProject(String session, String projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean projectAddUserToProject(String session, String projectID, Integer userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean projectAddUserToProject(String session, String projectID, Integer[] userIDs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean projectRemoveProject(String session, AProject project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activityAddNewActivity(String session, AActivity activity, String projectID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activityAddNewActivity(String session, AActivity activity, AProject project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activityAssignUsertoActivity(String session, AActivity activity, AUser user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int activityUserAddTime(String session, String activityID, Integer time) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean activityRemoveActivity(String session, AActivity activity) {
		// TODO Auto-generated method stub
		return null;
	}

}
