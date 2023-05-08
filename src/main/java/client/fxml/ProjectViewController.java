package client.fxml;

import java.io.IOException;
import java.util.ArrayList;

import client.ActivityClient;
import client.Application;
import client.HelloFX;
import client.ProjectClient;
import client.UserClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProjectViewController {
	Application app = Application.singleton();
	@FXML
	public Button refresh;
	
	@FXML
	Button logout;
	@FXML
	public Label welcome = new Label();
	@FXML
	private Label NameLabel;
	@FXML
	private Label activityLabel;
	@FXML
	private Label activityNumber;
	@FXML
	private Label estHoursLabel;
	@FXML
	private Label cumHoursLabel;
	@FXML
	private Label projectLeader;
	@FXML
	private Label projID;
	@FXML
	private Label projEstT;
	@FXML
	private Label thisID;
	@FXML
	private Label thisEstT;
	@FXML
	private TableView<ProjectClient> projectTable;
	@FXML
	private TableColumn<ProjectClient, String> NameColumn;
	// we can add multiple columns to out tableview, like estimated hours, estimated
	// time of completion, percentage of progression.
	@FXML
	private TableView<ActivityClient> activityTable;
	@FXML
	private TableColumn<ActivityClient, String> activityColumn;
	@FXML
	private TableColumn<ActivityClient, Integer> estHourColumn;
	@FXML
	private TableView<UserClient> assignedEmplTable;
	@FXML
	private TableColumn<UserClient, String> assignedEmplColumn;

	static ObservableList<ProjectClient> data = convertToOL(Application.serverAPI.projectGetAllProjectsAsList(Application.getCurrentActiveSession()));
	static ObservableList<ActivityClient> projectActivities;
	static ProjectClient currentProject = null;
	static ActivityClient currentActivity = null;

	public Button backToMain;

	public Button toAK;
	
	
	public void createAk() throws IOException {
		HelloFX.setRoot("createAktivitet", StartController.class);

	}
	
	public void backToMain() throws IOException {
		HelloFX.setRoot("Mainmenu", StartController.class);

	}

	@FXML
	public void refresh(ActionEvent e) throws IOException {
		// projektTable.setItems(data);
		NameColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());
		activityColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());
		estHourColumn.setCellValueFactory(cellData -> cellData.getValue().getUIEstHours());
		assignedEmplColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());

	}

	public void logOut() throws IOException {
		// sættes til -1 da index ikke kan være negativt. Tænker at vi implementerer et
		// tjek for det. Det er mest bare så der ikke sker noget
		// fucky wucky shit, men det burde egentlig aldrig blive et problem siden man
		// ikke kan komme nogen stedet fra login page/signup page uden at logge ind
		// og dermed skifte index. Bare extra safety.
		app.setCurrentActiveSession(null);
		HelloFX.setRoot("Loginpage", StartController.class);

	}

	public void initialize() {		
		data = convertToOL(Application.serverAPI.projectGetAllProjectsAsList(Application.getCurrentActiveSession()));
		
		if(!data.isEmpty())
			currentProject = data.get(0);
		if(data != null)
			data = convertToOL(app.serverAPI.projectGetAllProjectsAsList(Application.getCurrentActiveSession()));
		projectTable.setItems(data);
		activityTable.setItems(projectActivities);
		welcome.setText("hej " + app.serverAPI.projectGetAllProjectsAsList(Application.getCurrentActiveSession()));
		NameColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());
		activityColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());
		estHourColumn.setCellValueFactory(cellData -> cellData.getValue().getUIEstHours());
		assignedEmplColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());
		showProjectDetails(currentProject);
		showActivityDetails(currentActivity);
		projectTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showProjectDetails(newValue));
		activityTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showActivityDetails(newValue));
	}

	public static ObservableList<ProjectClient> convertToOL(ArrayList<ProjectClient> a) {
		ObservableList<ProjectClient> o = FXCollections.observableArrayList();
		for (ProjectClient P : a) {
			o.add(P);
		}
		return o;
	}

	public static ObservableList<ActivityClient> projectActivities(ArrayList<ActivityClient> a) {
		ObservableList<ActivityClient> o = FXCollections.observableArrayList();
		for (ActivityClient A : a) {
			o.add(A);
		}
		return o;
	}

	private void showProjectDetails(ProjectClient p) {
		if (p != null) {
			//ERROR!!
			

			NameLabel.setText(p.toString());
			UserClient projectleader = new UserClient(p.getProjectLeader());
			if (projectleader != null) {
				projectLeader.setText(projectleader.toString());
			} else {
				projectLeader.setText("none");
			}
			
			estHoursLabel.setText(Integer.toString(p.getEstTime()));
			currentProject = p;
			projectActivities = projectActivities(p.getActivitiesClient());
			
			thisID.setText("" + p.getID());
			thisEstT.setText("" + p.getEstTime());
			
			activityTable.setItems(projectActivities);
			if (!projectActivities.isEmpty()) {
				currentActivity = projectActivities.get(0);
			} else {
				currentActivity = null;
			}

			showActivityDetails(currentActivity);

		} else {
			NameLabel.setText("Project Name");
			currentProject = null;
			projectActivities = null;
			activityTable.setItems(projectActivities);

		}
	}

	private void showActivityDetails(ActivityClient a) {
		if (a != null) {
			activityLabel.setText(a.toString());
			activityNumber.setText("117");
			estHoursLabel.setText("" + a.getEstTime());
			cumHoursLabel.setText("");


		} else {
			activityLabel.setText("");
			activityNumber.setText("");
			estHoursLabel.setText("");
			cumHoursLabel.setText("");
		}
		

	}
}
