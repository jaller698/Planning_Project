package shared;

public class AActivity {
	private String name; // the activities name
	public String getName() {return name;}
	
	private AProject project; // the project the activity is tied to
	public AProject getProject() {return project;}
	
	
	
	public AActivity(AProject project, String name) {
		this.name = name;
		
		project.AddActivity(this);
		this.project = project;
	}
	
	public AActivity getBase() {
		return this;
	}
}
