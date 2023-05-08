package client.fxml;

import java.io.IOException;
import javafx.scene.control.Alert;
import client.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;

public class StartController { // {Written by GaySupremacy, NA33AT0R and Jaller698}
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
	public ChoiceBox<ProjectClient> PP =  new ChoiceBox<ProjectClient>(FXCollections.observableArrayList(app.serverAPI.projectGetAllProjectsAsList(app.getCurrentActiveSession())));

	@FXML
	private Button toCreater;
	
	@FXML
	public ChoiceBox<UserClient> leaderPick = new ChoiceBox<UserClient>(
			FXCollections.observableArrayList(app.serverAPI.userGetAllUsersAsList(app.getCurrentActiveSession())));

	@FXML
	public ChoiceBox<UserClient> Emp = new ChoiceBox<UserClient>(
			FXCollections.observableArrayList(app.serverAPI.userGetAllUsersAsList(app.getCurrentActiveSession())));
	
	@FXML
	TextField est = new TextField();
	
	@FXML
	private Button cancelak;
	@FXML
	private Button leabek;
	@FXML
	private Button createAk;
	
	@FXML
	TextField aktivitetNavn = new TextField();
	@FXML
	TextField estak = new TextField();
	
	@FXML
	public ChoiceBox<ProjectClient> projectPick = new ChoiceBox<ProjectClient>(FXCollections.observableArrayList(app.serverAPI.projectGetAllProjectsAsList(app.getCurrentActiveSession())));
	
	@FXML
	private static Alert alert = new Alert(AlertType.NONE);
	
	public void createAktivity() throws IOException { // {Written by Jaller698}
		//new ActivityClient(projectPick.getValue(), aktivitetNavn.getText(),Integer.valueOf(estak.getText()));
		projectPick.getValue().CreateActivity(aktivitetNavn.getText(), Integer.valueOf(estak.getText()));
		HelloFX.setRoot("ProjectView", ProjectViewController.class);
		
		//tilføj kode til at initialise med et projekt, samt derefter tilføje aktiviteten til projektet
		
		
		
	}
	
	public void goAdd(ActionEvent e) throws IOException { // {Written by NA33AT0R}
		HelloFX.setRoot("Addlead", StartController.class);
	}
	
	public void AddLeader() throws IOException { // {Written by NA33AT0R}
		PP.getValue().setProjectLeader(Emp.getValue());
		HelloFX.setRoot("Mainmenu", StartController.class);
	}

	public void createProjekt() throws IOException { // {Written by NA33AT0R and Jaller698}
		int estTid = Integer.valueOf("0"+est.getText());
		UserClient leaderpick = leaderPick.getValue();
		
		/*if (leaderpick != null) {
			ProjectClient proj = new ProjectClient(projektNavn.getText(), estTid, leaderpick);
			proj.setID();
		} else {
			ProjectClient proj = new ProjectClient(projektNavn.getText(), estTid);
			proj.setID();
		}*/
		Application.serverAPI.projectAddNewProject(Application.getCurrentActiveSession(), projektNavn.getText(), estTid, leaderpick);
		
		
		/*
		if (Integer.valueOf("0"+est.getText()) != null)
			 estTid = Integer.valueOf(est.getText());
		if (leaderPick.getValue() != null) {
			new ProjectClient(projektNavn.getText(), estTid, app.serverAPI.userGetUser(app.getCurrentActiveSession(), leaderPick.getValue().getId()));
		}
		else {
			new ProjectClient(projektNavn.getText(), estTid);
		}
		*/

		HelloFX.setRoot("Mainmenu", StartController.class);
		//System.out.println("Projekt tilføjet!");
		//System.out.println(app.getConfirmationMSG());
		if (app.getConfirmationMSG() != null) {
			confirmMSGPopup(null);
		}
	}

	public void logOut() throws IOException { // {Written by GaySupremacy}
		// sættes til -1 da index ikke kan være negativt. Tænker at vi implementerer et
		// tjek for det. Det er mest bare så der ikke sker noget
		// fucky wucky shit, men det burde egentlig aldrig blive et problem siden man
		// ikke kan komme nogen stedet fra login page/signup page uden at logge ind
		// og dermed skifte index. Bare extra safety.
		
		app.serverAPI.userLogOff(app.getCurrentActiveSession());
		
		app.setCurrentActiveSession(null);
		HelloFX.setRoot("Loginpage", StartController.class);
	}

	public static void confirmMSGPopup(ActionEvent e) { // {Written by Jaller698}
		alert.setAlertType(AlertType.INFORMATION);
		alert.setHeaderText(Application.getConfirmationMSG());
		alert.show();
	}

	public void toProjectCreater() throws IOException {
		// leaderPick.setItems(FXCollections.observableArrayList(data.getWorkers()));

		HelloFX.setRoot("ProjectCreater", StartController.class);
		// leaderPick.setItems(FXCollections.observableArrayList(data.getWorkers()));

	}

	public void viewProjects() throws IOException {
		HelloFX.setRoot("ProjectView", ProjectViewController.class);
	}

	public void go(ActionEvent e) throws IOException { // {Written by GaySupremacy}
		boolean checkSuccesful = false;
		
		String name = loginUsername.getText().toLowerCase();
		String password = loginPassword.getText();
		
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
			String session = app.serverAPI.userLogIn(name, password);
			
			if (session != null) {
				if (!session.isBlank()) {
					checkSuccesful = true;
					Application.setCurrentActiveSession(session);
					System.out.println(app.getCurrentActiveSession());
				}
			}
			
			
			/*for (Medarbejder M : app.workers.getAllUsers()) {
				if (M.navn.toLowerCase().equals(loginUsername.getText().toLowerCase()) == true
						&& M.password.equals(loginPassword.getText()) == true) {
					checkSuccesful = true;
					app.setCurrentActiveSession(M);
					System.out.println(app.getCurrentActiveSession());
				}
			}*/
		}
		if (!checkSuccesful) {
			System.out.println("You fucked it");
		} else {
			System.out.println("well done");

			HelloFX.setRoot("Mainmenu", StartController.class);

		}

	}

	public void goMain(ActionEvent e) throws IOException { // {Written by NA33AT0R}
		HelloFX.setRoot("Mainmenu", StartController.class);
	}

	public void goToLogin(ActionEvent e) throws IOException { // {Written by GaySupremacy}
		HelloFX.setRoot("Loginpage", StartController.class);
	}

	public void goToSignup(ActionEvent e) throws IOException { // {Written by GaySupremacy}
		HelloFX.setRoot("Signuppage", StartController.class);
	}

	public void addUser(ActionEvent e) throws IOException { // {Written by GaySupremacy}
		String userName = signupUsername.getText();
		String password = signupPassword.getText();
		if(userName.isBlank()) {
			app.setConfirmationMSG("Username must not be blank!");
			confirmMSGPopup(null);
			
			return;
		}
		
		if(password.isBlank()) {
			if(!password.equals(signupRepeatPassword.getText())) {
				app.setConfirmationMSG("Password does not match!");
				confirmMSGPopup(null);
				
				return;
			}
			
			app.setConfirmationMSG("Password must not be blank!");
			confirmMSGPopup(null);
			
			return;
		}
		
		UserClient newUser = app.serverAPI.userSignUp(userName, password); // signup and create new user
		String session = app.serverAPI.userLogIn(userName, password); // login the new user
		
		if (session == null) {
			app.setConfirmationMSG("Unknown error, try agaub");
			return;
		}
		if (session.isBlank()) {
			app.setConfirmationMSG("Unknown error, try agaub");
			return;
		}
		
		Application.setCurrentActiveSession(session); // saves the session
		
		
		System.out.println(app.serverAPI.userGetAllUsersAsList(session));

		HelloFX.setRoot("Mainmenu", StartController.class);
		
		/*else if(app.workers.getUser(userName) != null) {
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
			
			app.setCurrentActiveSession(newUser);
			
			System.out.println(app.workers.getAllUsers().toString());
			
			app.sessions.loginUser(userName, password);
			
			HelloFX.setRoot("Mainmenu", StartController.class);
		} 
		*/
	}

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException { // {Written by GaySupremacy}
		System.out.println("You clicked me!");
		label.setText("Hello World!");
		HelloFX.setRoot("Loginpage", StartController.class);
	}

	public void initialize() { // {Written by GaySupremacy}
		leaderPick.getItems().clear();
		leaderPick.setItems(FXCollections.observableArrayList(app.serverAPI.userGetAllUsersAsList(app.getCurrentActiveSession())));
		Emp.getItems().clear();
		Emp.setItems(FXCollections.observableArrayList(app.serverAPI.userGetAllUsersAsList(app.getCurrentActiveSession())));
		
		projectPick.getItems().clear();
		projectPick.setItems(FXCollections.observableArrayList(app.serverAPI.projectGetAllProjectsAsList(app.getCurrentActiveSession())));
		PP.getItems().clear();
		PP.setItems(FXCollections.observableArrayList(app.serverAPI.projectGetAllProjectsAsList(app.getCurrentActiveSession())));
		
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
