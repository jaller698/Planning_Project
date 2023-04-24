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
	private Button cancelak;
	@FXML
	private Button leabek;
	@FXML
	private Button createAk;
	@FXML
	private Button Leabe;
	@FXML
	private Button cancel;
	@FXML
	private Button refresh;
	@FXML
	private Label label;
	@FXML
	private Label aktivitetNavn;
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
	public ChoiceBox<Medarbejder> leaderPick = new ChoiceBox<Medarbejder>(
			FXCollections.observableArrayList(app.workers.getAllUsers()));

	@FXML
	public Spinner est;
	@FXML
	public Spinner estak;

	@FXML
	private static Alert alert = new Alert(AlertType.NONE);

	public static int loginIndex;
	
	public void createAktivity() throws IOException {
		Aktivitet a = new Aktivitet(aktivitetNavn.getText(),(int)estak.getValue(), null);
		//tilføj kode til at initialise med et projekt, samt derefter tilføje aktiviteten til projektet
		
		
		
	}

	public void createProjekt() throws IOException {
		
		app.workers.addUser(new Medarbejder("name", "password"));
		System.out.println(app.workers.getAllUsers().toString());
		// TODO TODO
		Projekt p = new Projekt(projektNavn.getText());
		// Application.alleProjekter.add(p);
		if (leaderPick.getValue() != null) 
			p.leder = app.workers.getUser(app.workers.getUserID(leaderPick.getValue()));
		if (est.getValue() != null)
			p.estTid = (int) est.getValue();
		else
			p.estTid = 5;
		
		app.getCurrentActiveUser().addProjekt(p);

		
		app.projects.addProject(p);

		
		HelloFX.setRoot("Mainmenu", StartController.class);
		System.out.println("Projekt tilføjet!");
		System.out.println(app.getConfirmationMSG());
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
		StartController.loginIndex = -1;
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
					loginIndex = app.workers.getUserID(M);
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
		System.out.println("gabriel er irriterende");
		if (signupPassword.getText().equals(signupRepeatPassword.getText())) {
			String userName = signupUsername.getText();
			String password = signupPassword.getText();
			
			Medarbejder newUser = new Medarbejder(userName, password);
			
			loginIndex = app.workers.addUser(newUser);
			
			System.out.println(app.workers.getAllUsers().toString());
			
			app.sessions.loginUser(userName, password);
			
			HelloFX.setRoot("Mainmenu", StartController.class);
		} else {
			System.out.println("farvel");
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

		/*
		 * h.p.add(new Projekt("1h")); h.p.add(new Projekt("2h")); h.p.add(new
		 * Projekt("3h")); h.p.get(0).addAktivitet(new Aktivitet("næbdyr0", 4755));
		 * h.p.get(0).addAktivitet(new Aktivitet("næbdyr1", 565));
		 * h.p.get(0).addAktivitet(new Aktivitet("næbdyr2", 34));
		 * h.p.get(0).addAktivitet(new Aktivitet("næbdyr3", 32));
		 * h.p.get(0).addAktivitet(new Aktivitet("næbdyr4", 32));
		 * h.p.get(1).addAktivitet(new Aktivitet("fisk1", 789));
		 * h.p.get(1).addAktivitet(new Aktivitet("fisk2", 456));
		 * h.p.get(1).addAktivitet(new Aktivitet("fisk3", 258));
		 * h.p.get(1).addAktivitet(new Aktivitet("fisk4", 1234));
		 * h.p.get(1).addAktivitet(new Aktivitet("fisk5", 420)); l.p.add(new
		 * Projekt("1l")); l.p.add(new Projekt("2l")); l.p.add(new Projekt("3l"));
		 */

	}

}
