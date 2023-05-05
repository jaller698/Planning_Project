package database;

import java.util.ArrayList;

import application.Project;

public interface IProjectRegister {
	public int addProject(Project project); // stores new project
	
	public void addProject(Project project, Integer id); // stores new project under given id
	
	public Project getProject(Integer id); // retrieves a project from given id
	
	public Project getProject(String name); // retrieves a project from given name
	
	public Project[] getAllProjects(); // retrieves all projects
	
	public ArrayList<Project> getAllProjectsAsList(); // retrieves all projects as a list
	
	public int getProjectID(Project project); // gets a specific projectID
	
	// TODO add administrator check
	public void removeProject(Integer id); // removes project from given id
}
