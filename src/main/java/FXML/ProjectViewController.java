package FXML;

import java.io.IOException;
import java.util.ArrayList;
import application.Application;
import application.Activity;
import application.HelloFX;
import application.Medarbejder;
import application.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

// Skrevet af Christian, Gabriel og Natascha

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
	private TableView<Project> projectTable;
	@FXML
	private TableColumn<Project, String> NameColumn;
	// we can add multiple columns to out tableview, like estimated hours, estimated
	// time of completion, percentage of progression.
	@FXML
	private TableView<Activity> activityTable;
	@FXML
	private TableColumn<Activity, String> activityColumn;
	@FXML
	private TableColumn<Activity, Integer> estHourColumn;
	@FXML
	private TableView<Medarbejder> assignedEmplTable;
	@FXML
	private TableColumn<Medarbejder, String> assignedEmplColumn;

	static ObservableList<Project> data = convertToOL(Application.getCurrentActiveUser().p);
	static ObservableList<Activity> projectActivities;
	static Project currentProject = null;
	static Activity currentActivity = null;

	public Button backToMain;

	public Button toAK;

	public void createAk() throws IOException {
		HelloFX.setRoot("createActivity", StartController.class);

	}

	public void backToMain() throws IOException {
		HelloFX.setRoot("Mainmenu", StartController.class);

	}
	
	//Christian
	@FXML
	public void refresh(ActionEvent e) throws IOException {
		NameColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());
		activityColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());
		estHourColumn.setCellValueFactory(cellData -> cellData.getValue().getUIEstHours());
		assignedEmplColumn.setCellValueFactory(cellData -> cellData.getValue().getUIName());

	}
	//Natascha
	public void logOut() throws IOException {
		app.setCurrentActiveUser(null);
		HelloFX.setRoot("Loginpage", StartController.class);

	}
	//Christian
	public void initialize() {
		if (!data.isEmpty())
			currentProject = data.get(0);
		if (data != null)
			data = convertToOL(app.getCurrentActiveUser().p);
		projectTable.setItems(data);
		activityTable.setItems(projectActivities);
		welcome.setText("hej " + app.getCurrentActiveUser().p);
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

	public static ObservableList<Project> convertToOL(ArrayList<Project> a) {
		ObservableList<Project> o = FXCollections.observableArrayList();
		for (Project P : a) {
			o.add(P);
		}
		return o;
	}

	public static ObservableList<Activity> projectActivities(ArrayList<Activity> a) {
		ObservableList<Activity> o = FXCollections.observableArrayList();
		for (Activity A : a) {
			o.add(A);
		}
		return o;
	}
	//Christian
	private void showProjectDetails(Project p) {
		if (p != null) {
			NameLabel.setText(p.toString());
			projectLeader.setText(p.Leader.toString());
			estHoursLabel.setText(Integer.toString(p.estTid));
			currentProject = p;
			projectActivities = projectActivities(p.getActivityList());
			thisID.setText("" + p.getID());;
			thisEstT.setText("" + p.estTid);

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
	//Christian, Natascha og Gabriel
	private void showActivityDetails(Activity a) {
		if (a != null) {
			activityLabel.setText(a.toString());
			activityNumber.setText("117");
			estHoursLabel.setText("" + a.getEstHours());
			cumHoursLabel.setText("");

		} else {
			activityLabel.setText("");
			activityNumber.setText("");
			estHoursLabel.setText("");
			cumHoursLabel.setText("");
		}

	}
}
