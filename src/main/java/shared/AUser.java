package shared;

import java.util.ArrayList;

public abstract class AUser {
	private String name; // the users name
	public String getName() {return name;}
	
	private Integer id; // the users id
	public Integer getId() {return id;}
	
	private ArrayList<AProject> projects = new ArrayList<AProject>();
	private ArrayList<AActivity> activities = new ArrayList<AActivity>();
	
	private int[] timeworked = new ;
	
	public AUser(String name, Integer id) {
		this.name = name;
		this.id = id;
	}
	
	public AUser getBase() {
		return this;
	}
}
