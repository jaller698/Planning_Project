package server.database;

import java.util.ArrayList;

import shared.Projekt;

public interface IProjectRegister {
	public int addProject(Projekt project); // stores new project
	
	public void addProject(Projekt project, Integer id); // stores new project under given id
	
	public Projekt getProject(Integer id); // retrieves a project from given id
	
	public Projekt getProject(String name); // retrieves a project from given name
	
	public Projekt[] getAllProjects(); // retrieves all projects
	
	public ArrayList<Projekt> getAllProjectsAsList(); // retrieves all projects as a list
	
	public int getProjectID(Projekt project); // gets a specific projectID
	
	// TODO add administrator check
	public void removeProject(Integer id); // removes project from given id
}
