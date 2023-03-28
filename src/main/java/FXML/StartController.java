package FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import FXML.ProjektViewController;

public class StartController {

	ArrayList<Medarbejder> alleMedarbejdere = new ArrayList<Medarbejder>();
	ArrayList<Projekt> alleProjekter = new ArrayList<Projekt>();

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
	public Button toLogin;
	@FXML
	public Button toSignup;
	
	@FXML
	TextField signupUsername = new TextField();
	@FXML
	TextField signupPassword = new TextField();
	@FXML
	TextField signupRepeatPassword = new TextField();
	@FXML 
	public Button signup;

	
	public int loginIndex;
	
	

	public void go(ActionEvent e) throws IOException {
		boolean checkSuccesful = false;
		if ((loginUsername.getText() != null && loginPassword.getText() != null)) {
			System.out.println(
					loginUsername.getText() + " " + loginPassword.getText() + ", " + "thank you for your comment!");
			for (int i = 0; i < alleMedarbejdere.size(); i++) {
				System.out.println(alleMedarbejdere.get(i).navn);
				if (alleMedarbejdere.get(i).navn.equals(loginUsername.getText()) == true && alleMedarbejdere.get(i).password.equals(loginPassword.getText()) == true) {
					checkSuccesful = true;
					//loginIndex = alleMedarbejdere.indexOf();
					loginIndex = i;
					System.out.println(loginIndex);
				}
			}
		}
		if (!checkSuccesful) {
			System.out.println("You fucked it");
		} else {
			System.out.println("well done");

			HelloFX.setRoot("projektview2", ProjektViewController.class);

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
			
			alleMedarbejdere.add(new Medarbejder(signupUsername.getText(), signupPassword.getText()));
			System.out.println(alleMedarbejdere.get(alleMedarbejdere.size()-1));
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
		alleMedarbejdere.add(h);
		alleMedarbejdere.add(l);
		alleMedarbejdere.add(p);


	}

}
