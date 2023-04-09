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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import FXML.ProjektViewController;

public class StartController {
	Application app = new Application();
	@FXML
	private Button begin;
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
	private Button viewProjects;
	private Button logOut;
	@FXML 
	public TextField projektNavn = new TextField();
	
	@FXML
	private static Alert alert = new Alert(AlertType.NONE);
	
	public static int loginIndex;
	
	public void createProjekt() throws IOException {
		Projekt p = new Projekt(projektNavn.getText());
		//Application.alleProjekter.add(p);
		app.getMedarbejder().addProjekt(p);
		HelloFX.setRoot("Mainmenu", StartController.class);
		System.out.println("Projekt tilføjet!");
		System.out.println(app.getConfirmationMSG());
		if(app.getConfirmationMSG() != null) {
			confirmMSGPopup(null);
		}
	}
	public void logOut() throws IOException {
		//sættes til -1 da index ikke kan være negativt. Tænker at vi implementerer et tjek for det. Det er mest bare så der ikke sker noget
		//fucky wucky shit, men det burde egentlig aldrig blive et problem siden man ikke kan komme nogen stedet fra login page/signup page uden at logge ind
		//og dermed skifte index. Bare extra safety. 
		StartController.loginIndex = -1;
		HelloFX.setRoot("Loginpage", StartController.class);			
	}
	public static void confirmMSGPopup(ActionEvent e) {
		alert.setAlertType(AlertType.INFORMATION);
		alert.setHeaderText(Application.getConfirmationMSG());
		alert.show();
	}

	
	public void toProjektCreater() throws IOException {
		HelloFX.setRoot("ProjektCreater", StartController.class);

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
				if (M.navn.toLowerCase().equals(loginUsername.getText().toLowerCase()) == true && M.password.equals(loginPassword.getText()) == true) {
					checkSuccesful = true;
					loginIndex = app.workers.getUserID(M);
					Application.setMedarbejder(M);
					System.out.println(app.getMedarbejder());
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
	public void goToLogin(ActionEvent e) throws IOException {
		HelloFX.setRoot("Loginpage",StartController.class);
	}
	public void goToSignup(ActionEvent e) throws IOException {
		HelloFX.setRoot("Signuppage", StartController.class);
	}
	public void addUser(ActionEvent e) throws IOException{
		System.out.println("gabriel er irriterende");
		if(signupPassword.getText().equals(signupRepeatPassword.getText())) {
			
			app.workers.addUser(new Medarbejder(signupUsername.getText(), signupPassword.getText()));
			System.out.println(app.workers.getUser(app.workers.getAllUsers().length-1));
			loginIndex = app.workers.getAllUsers().length-1;
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
		
		Medarbejder h = new Medarbejder("Hans","heste123");
		Medarbejder l = new Medarbejder("Erik","fisk123");
		Medarbejder p = new Medarbejder("Peter","næbdyr123");
		app.workers.addUser(h);
		app.workers.addUser(l);
		app.workers.addUser(p);
		/*
		h.p.add(new Projekt("1h"));
		h.p.add(new Projekt("2h"));
		h.p.add(new Projekt("3h"));
		h.p.get(0).addAktivitet(new Aktivitet("næbdyr0", 4755));
		h.p.get(0).addAktivitet(new Aktivitet("næbdyr1", 565));
		h.p.get(0).addAktivitet(new Aktivitet("næbdyr2", 34));
		h.p.get(0).addAktivitet(new Aktivitet("næbdyr3", 32));
		h.p.get(0).addAktivitet(new Aktivitet("næbdyr4", 32));
		h.p.get(1).addAktivitet(new Aktivitet("fisk1", 789));
		h.p.get(1).addAktivitet(new Aktivitet("fisk2", 456));
		h.p.get(1).addAktivitet(new Aktivitet("fisk3", 258));
		h.p.get(1).addAktivitet(new Aktivitet("fisk4", 1234));
		h.p.get(1).addAktivitet(new Aktivitet("fisk5", 420));
		l.p.add(new Projekt("1l"));
		l.p.add(new Projekt("2l"));
		l.p.add(new Projekt("3l"));
		*/


	}


}
