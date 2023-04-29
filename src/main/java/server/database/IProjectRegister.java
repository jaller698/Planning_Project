package server.database;

import java.util.ArrayList;

import server.ProjectSaveable;

public interface IProjectRegister { // {Written by Perry02}
	
	public int addProject(ProjectSaveable project); // stores new project
	
	public void addProject(ProjectSaveable project, Integer id); // stores new project under given id
	
	public ProjectSaveable getProject(Integer id); // retrieves a project from given id
	
	public ProjectSaveable getProject(String name); // retrieves a project from given name
	
	public ProjectSaveable[] getAllProjects(); // retrieves all projects
	
	public ArrayList<ProjectSaveable> getAllProjectsAsList(); // retrieves all projects as a list
	
	public int getProjectID(ProjectSaveable project); // gets a specific projectID
	
	// TODO add administrator check
	public void removeProject(Integer id); // removes project from given id
}
