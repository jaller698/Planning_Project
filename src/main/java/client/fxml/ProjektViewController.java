package client.fxml;

import java.io.IOException;
import java.util.ArrayList;

import client.Application;
import client.HelloFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import shared.Aktivitet;
import shared.Medarbejder;
import shared.Projekt;

public class ProjektViewController {
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
	private TableView<Projekt> projectTable;
	@FXML
	private TableColumn<Projekt, String> NameColumn;
	// we can add multiple columns to out tableview, like estimated hours, estimated
	// time of completion, percentage of progression.
	@FXML
	private TableView<Aktivitet> activityTable;
	@FXML
	private TableColumn<Aktivitet, String> activityColumn;
	@FXML
	private TableColumn<Aktivitet, Integer> estHourColumn;
	@FXML
	private TableView<Medarbejder> assignedEmplTable;
	@FXML
	private TableColumn<Medarbejder, String> assignedEmplColumn;

	static ObservableList<Projekt> data = convertToOL(
			Application.getCurrentActiveUser().p);
	static ObservableList<Aktivitet> projectActivities;
	static Projekt currentProject = null;
	static Aktivitet currentActivity = null;

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
		app.setCurrentActiveUser(null);
		HelloFX.setRoot("Loginpage", StartController.class);

	}

	public void initialize() {		
		if(!data.isEmpty())
			currentProject = data.get(0);
		if(data != null)
			data = convertToOL(
					app.getCurrentActiveUser().p);
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

	public static ObservableList<Projekt> convertToOL(ArrayList<Projekt> a) {
		ObservableList<Projekt> o = FXCollections.observableArrayList();
		for (Projekt P : a) {
			o.add(P);
		}
		return o;
	}

	public static ObservableList<Aktivitet> projectActivities(ArrayList<Aktivitet> a) {
		ObservableList<Aktivitet> o = FXCollections.observableArrayList();
		for (Aktivitet A : a) {
			o.add(A);
		}
		return o;
	}

	private void showProjectDetails(Projekt p) {
		if (p != null) {
			//ERROR!!
			

			NameLabel.setText(p.toString());
			projectLeader.setText(p.leder.toString());
			estHoursLabel.setText(Integer.toString(p.estTid));
			currentProject = p;
			projectActivities = projectActivities(p.getActivityList());
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

	private void showActivityDetails(Aktivitet a) {
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