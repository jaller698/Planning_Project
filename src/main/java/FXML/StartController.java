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
import FXML.ProjektViewController;

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
	public ChoiceBox<Projekt> PP =  new ChoiceBox<Projekt>(FXCollections.observableArrayList(app.projects.getAllProjectsAsList()));

	
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
	public ChoiceBox<Projekt> projectPick = new ChoiceBox<Projekt>(FXCollections.observableArrayList(app.projects.getAllProjectsAsList()));
	
	@FXML
	private static Alert alert = new Alert(AlertType.NONE);
	
	public void createAktivity() throws IOException {
		new Activity(ActivityNavn.getText(),Integer.valueOf(estak.getText()), projectPick.getValue());
		HelloFX.setRoot("projektview", ProjektViewController.class);
	}
	
	
	public void goAdd(ActionEvent e) throws IOException {
		HelloFX.setRoot("Addlead", StartController.class);
	}
	
	public void AddLeader() throws IOException {
		PP.getValue().addProjektLeder(Emp.getValue());
		HelloFX.setRoot("Mainmenu", StartController.class);
	}

	public void createProjekt() throws IOException {
		// Application.alleProjekter.add(p);
		int estTid = 0;
		if (Integer.valueOf(est.getText()) != null) {
			 estTid = Integer.valueOf(est.getText());
		}
		if (leaderPick.getValue() != null) {
			Projekt p = new Projekt(projektNavn.getText(), app.workers.getUser(app.workers.getUserID(leaderPick.getValue())), estTid);
			p.medarbejdere.add(app.getCurrentActiveUser());
		}
		else {
			Projekt p = new Projekt(projektNavn.getText(),estTid);
		}

		HelloFX.setRoot("Mainmenu", StartController.class);
		//System.out.println("Projekt tilføjet!");
		//System.out.println(app.getConfirmationMSG());
		if (app.getConfirmationMSG() != null) {
			confirmMSGPopup(null);
		}
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

	public static void confirmMSGPopup(ActionEvent e) {
		alert.setAlertType(AlertType.INFORMATION);
		alert.setHeaderText(Application.getConfirmationMSG());
		alert.show();
	}

	public void toProjektCreater() throws IOException {
		// leaderPick.setItems(FXCollections.observableArrayList(data.getWorkers()));

		HelloFX.setRoot("ProjektCreater", StartController.class);
		// leaderPick.setItems(FXCollections.observableArrayList(data.getWorkers()));

	}

	public void viewProjects() throws IOException {
		HelloFX.setRoot("projektview", ProjektViewController.class);
	}

	public void go(ActionEvent e) throws IOException {
		boolean checkSuccesful = false;
		if ((loginUsername.getText() != null && loginPassword.getText() != null)) {
//			
//			for (int i = 0; i < alleMedarbejdere.size(); i++) {
//				if (alleMedarbejdere.get(i).navn.equals(loginUsername.getText()) == true && alleMedarbejdere.get(i).password.equals(loginPassword.getText()) == true) {
//					checkSuccesful = true;
//					//loginIndex = alleMedarbejdere.indexOf();
//					loginIndex = i;
//					System.out.println(loginIndex);
//				}
//			}
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

	public void addUser(ActionEvent e) throws IOException {
		String userName = signupUsername.getText();
		String password = signupPassword.getText();
		if(userName.isBlank()) {
			app.setConfirmationMSG("Username must not be blank!");
			confirmMSGPopup(null);

		}
		else if(app.workers.getUser(userName) != null) {
			app.setConfirmationMSG("This username is already taken");
			confirmMSGPopup(null);
		}
		else if(password.isBlank()) {
			app.setConfirmationMSG("Password must not be blank!");
			confirmMSGPopup(null);
		}
		else if(!password.equals(signupRepeatPassword.getText())) {
			app.setConfirmationMSG("Password does not match!");
			confirmMSGPopup(null);

		}
		else if (signupPassword.getText().equals(signupRepeatPassword.getText())) {
			
			
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

	public void initialize() {
		leaderPick.getItems().clear();
		leaderPick.setItems(FXCollections.observableArrayList(app.workers.getAllUsers()));
		Emp.getItems().clear();
		Emp.setItems(FXCollections.observableArrayList(app.workers.getAllUsers()));
		projectPick.getItems().clear();
		projectPick.setItems(FXCollections.observableArrayList(app.projects.getAllProjectsAsList()));
		PP.getItems().clear();
		PP.setItems(FXCollections.observableArrayList(app.projects.getAllProjectsAsList()));
		/*
		 * h.p.add(new Projekt("1h")); h.p.add(new Projekt("2h")); h.p.add(new
		 * Projekt("3h")); h.p.get(0).addActivity(new Activity("næbdyr0", 4755));
		 * h.p.get(0).addActivity(new Activity("næbdyr1", 565));
		 * h.p.get(0).addActivity(new Activity("næbdyr2", 34));
		 * h.p.get(0).addActivity(new Activity("næbdyr3", 32));
		 * h.p.get(0).addActivity(new Activity("næbdyr4", 32));
		 * h.p.get(1).addActivity(new Activity("fisk1", 789));
		 * h.p.get(1).addActivity(new Activity("fisk2", 456));
		 * h.p.get(1).addActivity(new Activity("fisk3", 258));
		 * h.p.get(1).addActivity(new Activity("fisk4", 1234));
		 * h.p.get(1).addActivity(new Activity("fisk5", 420)); l.p.add(new
		 * Projekt("1l")); l.p.add(new Projekt("2l")); l.p.add(new Projekt("3l"));
		 */

	}

}
