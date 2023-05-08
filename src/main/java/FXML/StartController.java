package FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import FXML.ProjectViewController;

//Skrevet af Gabriel, Christian, Natascha

public class StartController {
	Application app = Application.singleton();
	@FXML
	private Button begin;

	@FXML
	private Button Leabe;
	@FXML
	private Button cancel;
	@FXML
	private Button refresh;
	@FXML
	private Label label;

	@FXML
	TextField loginUsername = new TextField();
	@FXML
	TextField loginPassword = new TextField();
	@FXML
	private Button toLogin;
	@FXML
	private Button toSignup;

	@FXML
	TextField signupUsername = new TextField();
	@FXML
	TextField signupPassword = new TextField();
	@FXML
	TextField signupRepeatPassword = new TextField();
	@FXML
	private Button signup;
	@FXML
	private Button viewProjects;
	@FXML
	private Button logOut;
	@FXML
	public TextField projektNavn = new TextField();

	@FXML
	private Button addLeader;
	@FXML
	private Button addL;
	@FXML
	private Button Cal;

	@FXML
	public ChoiceBox<Project> PP = new ChoiceBox<Project>(
			FXCollections.observableArrayList(app.projects.getAllProjectsAsList()));

	@FXML
	private Button toCreater;

	@FXML
	public ChoiceBox<Medarbejder> leaderPick = new ChoiceBox<Medarbejder>(
			FXCollections.observableArrayList(app.workers.getAllUsers()));

	@FXML
	public ChoiceBox<Medarbejder> Emp = new ChoiceBox<Medarbejder>(
			FXCollections.observableArrayList(app.workers.getAllUsers()));

	@FXML
	TextField est = new TextField();

	@FXML
	private Button cancelak;
	@FXML
	private Button leabek;
	@FXML
	private Button createAk;

	@FXML
	TextField ActivityNavn = new TextField();
	@FXML
	TextField estak = new TextField();

	@FXML
	public ChoiceBox<Project> projectPick = new ChoiceBox<Project>(
			FXCollections.observableArrayList(app.projects.getAllProjectsAsList()));

	@FXML
	private static Alert alert = new Alert(AlertType.NONE);

	// Gabriel indtil andet sagt.
	public void createAktivity() throws IOException {
		new Activity(ActivityNavn.getText(), Integer.valueOf(estak.getText()), projectPick.getValue());
		HelloFX.setRoot("ProjectView", ProjectViewController.class);
	}

	public void goAdd(ActionEvent e) throws IOException {
		HelloFX.setRoot("Addlead", StartController.class);
	}

	public void AddLeader() throws IOException {
		PP.getValue().addProjectLeader(Emp.getValue());
		HelloFX.setRoot("Mainmenu", StartController.class);
	}

	// Gabriel og Christian
	public void createProjekt() throws IOException {
		// Application.alleProjekter.add(p);
		int estTid = 0;
		if (Integer.valueOf(est.getText()) != null) {
			estTid = Integer.valueOf(est.getText());
		}
		if (leaderPick.getValue() != null) {
			Project p = new Project(projektNavn.getText(),
					app.workers.getUser(app.workers.getUserID(leaderPick.getValue())), estTid);
			p.medarbejdere.add(app.getCurrentActiveUser());
		} else {
			Project p = new Project(projektNavn.getText(), estTid);
		}

		HelloFX.setRoot("Mainmenu", StartController.class);
		if (app.getConfirmationMSG() != null) {
			confirmMSGPopup(null);
		}
	}

	// Natascha
	public void logOut() throws IOException {
		app.setCurrentActiveUser(null);
		HelloFX.setRoot("Loginpage", StartController.class);
	}

	// Christian
	public static void confirmMSGPopup(ActionEvent e) {
		alert.setAlertType(AlertType.INFORMATION);
		alert.setHeaderText(Application.getConfirmationMSG());
		alert.show();
	}

	public void toProjectCreater() throws IOException {
		HelloFX.setRoot("ProjectCreater", StartController.class);
	}

	public void viewProjects() throws IOException {
		HelloFX.setRoot("ProjectView", ProjectViewController.class);
	}

	// Natascha
	public void go(ActionEvent e) throws IOException {
		boolean checkSuccesful = false;
		if ((loginUsername.getText() != null && loginPassword.getText() != null)) {

			for (Medarbejder M : app.workers.getAllUsers()) {
				if (M.navn.toLowerCase().equals(loginUsername.getText().toLowerCase()) == true
						&& M.password.equals(loginPassword.getText()) == true) {
					checkSuccesful = true;
					app.setCurrentActiveUser(M);
					System.out.println(app.getCurrentActiveUser());
				}
			}
		}
		if (!checkSuccesful) {
			System.out.println("You fucked it");
		} else {
			System.out.println("well done");

			HelloFX.setRoot("Mainmenu", StartController.class);

		}
	}

	public void goMain(ActionEvent e) throws IOException {
		HelloFX.setRoot("Mainmenu", StartController.class);
	}

	public void goToLogin(ActionEvent e) throws IOException {
		HelloFX.setRoot("Loginpage", StartController.class);
	}

	public void goToSignup(ActionEvent e) throws IOException {
		HelloFX.setRoot("Signuppage", StartController.class);
	}

	// Christian og Natascha
	public void addUser(ActionEvent e) throws IOException {
		String userName = signupUsername.getText();
		String password = signupPassword.getText();
		if (userName.isBlank()) {
			app.setConfirmationMSG("Username must not be blank!");
			confirmMSGPopup(null);

		} else if (app.workers.getUser(userName) != null) {
			app.setConfirmationMSG("This username is already taken");
			confirmMSGPopup(null);
		} else if (password.isBlank()) {
			app.setConfirmationMSG("Password must not be blank!");
			confirmMSGPopup(null);
		} else if (!password.equals(signupRepeatPassword.getText())) {
			app.setConfirmationMSG("Password does not match!");
			confirmMSGPopup(null);

		} else if (signupPassword.getText().equals(signupRepeatPassword.getText())) {

			Medarbejder newUser = new Medarbejder(userName, password);

			app.setCurrentActiveUser(newUser);

			System.out.println(app.workers.getAllUsers().toString());

			app.sessions.loginUser(userName, password);

			HelloFX.setRoot("Mainmenu", StartController.class);
		}

	}

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		System.out.println("You clicked me!");
		label.setText("Hello World!");
		HelloFX.setRoot("Loginpage", StartController.class);
	}

	// Natascha
	public void initialize() {
		leaderPick.getItems().clear();
		leaderPick.setItems(FXCollections.observableArrayList(app.workers.getAllUsers()));
		Emp.getItems().clear();
		Emp.setItems(FXCollections.observableArrayList(app.workers.getAllUsers()));
		projectPick.getItems().clear();
		projectPick.setItems(FXCollections.observableArrayList(app.projects.getAllProjectsAsList()));
		PP.getItems().clear();
		PP.setItems(FXCollections.observableArrayList(app.projects.getAllProjectsAsList()));
	}

}
