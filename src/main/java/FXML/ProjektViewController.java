package FXML;

import java.io.IOException;
import java.util.ArrayList;

import application.Aktivitet;
import application.HelloFX;
import application.Medarbejder;
import application.Projekt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProjektViewController {
	@FXML
	public Button refresh;
	@FXML 
	Button logout;
	@FXML
	public Label welcome = new Label();
	@FXML
	private Label NameLabel;
	@FXML
    private TableView<Projekt> projectTable;
	@FXML
    private TableColumn<Projekt, String> NameColumn;
	//we can add multiple columns to out tableview, like estimated hours, estimated time of completion, percentage of progression.
	@FXML
    private TableView<Aktivitet> activityTable;
	@FXML
    private TableColumn<Aktivitet, String> activityColumn;
	@FXML
    private TableView<Medarbejder> assignedEmplTable;
	@FXML
    private TableColumn<Medarbejder, String> assignedEmplColumn;
	
	ObservableList<Projekt> data = convertToOL(StartController.alleMedarbejdere.get(StartController.loginIndex).p);
	static String currentProject;
	
	public Button backToMain;
	public void backToMain() throws IOException {
		HelloFX.setRoot("Mainmenu", StartController.class);

	}
	
	
	@FXML
	public void refresh(ActionEvent e) throws IOException {
		//projektTable.setItems(data);
		NameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		//activityColumn.setCellValueFactory(cellData -> cellData.getValue().getName(pid));
		//assignedEmplColumn.setCellValueFactory(cellData -> cellData.getValue().getName(aid));

	}
	public void logOut() throws IOException {
		//sættes til -1 da index ikke kan være negativt. Tænker at vi implementerer et tjek for det. Det er mest bare så der ikke sker noget
		//fucky wucky shit, men det burde egentlig aldrig blive et problem siden man ikke kan komme nogen stedet fra login page/signup page uden at logge ind
		//og dermed skifte index. Bare extra safety. 
		StartController.loginIndex = -1;
		HelloFX.setRoot("Loginpage", StartController.class);
		
	}
	public void initialize() {
		projectTable.setItems(data);
		welcome.setText("hej " + StartController.alleMedarbejdere.get(StartController.loginIndex).navn);
		NameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		//activityColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		//assignedEmplColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
	    showProjectDetails(null);
		projectTable.getSelectionModel().selectedItemProperty().addListener(
	           (observable, oldValue, newValue) -> showProjectDetails(newValue));
	}
	

	public ObservableList<Projekt> convertToOL(ArrayList<Projekt> a) {
		ObservableList<Projekt> o = FXCollections.observableArrayList();
		for(Projekt P : a) {
			o.add(P);
		}
		return o;
	}
	private void showProjectDetails(Projekt p) {
	    if (p != null) {
	        NameLabel.setText(p.toString());
	        currentProject = p.toString();

	    } else {
	        NameLabel.setText("Project Name");
	        currentProject = null;
	    }
	}
}
