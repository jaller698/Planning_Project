package server;

import sharedCore.Projekt;

public interface IProjectRegister {
	public int addProject(Projekt project); // stores new project
	
	public void addProject(Projekt project, Integer id); // stores new project under given id
	
	public Projekt getProject(Integer id); // retrieves a project from given id
	
	public Projekt getProject(String name); // retrieves a project from given name
	
	public Projekt[] getAllProjects(); // retrieves all projects
	
	public int getProjectID(Projekt project); // gets a specific projectID
	
	// TODO add administrator check
	public void removeProject(Integer id); // removes project from given id
}
