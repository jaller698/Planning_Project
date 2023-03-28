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
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class StartController {

	ArrayList<Medarbejder> alleMedarbejdere = new ArrayList<Medarbejder>();
	ArrayList<Projekt> alleProjekter = new ArrayList<Projekt>();

	@FXML
	private Button begin;
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


	public void go(ActionEvent e) throws IOException {
		boolean checkSuccesful = false;
		if ((loginUsername.getText() != null && loginPassword.getText() != null)) {
			System.out.println(
					loginUsername.getText() + " " + loginPassword.getText() + ", " + "thank you for your comment!");
			for (Medarbejder M : alleMedarbejdere) {
				if (M.navn.equals(loginUsername.getText()) && M.password.equals(loginPassword.getText())) {
					checkSuccesful = true;
				}
			}
		}
		if (!checkSuccesful) {
			System.out.println("You fucked it");
		} else {
			System.out.println("well done");
		}
	}
	public void goToLogin(ActionEvent e) throws IOException {
		HelloFX.setRoot("Loginpage");
	}
	public void goToSignup(ActionEvent e) throws IOException {
		HelloFX.setRoot("Signuppage");
	}
	public void addUser(ActionEvent e) throws IOException{
		System.out.println("gabriel er irriterende");
		if(signupPassword.getText().equals(signupRepeatPassword.getText())) {
			
			alleMedarbejdere.add(new Medarbejder(signupUsername.getText(), signupPassword.getText()));
			System.out.println(alleMedarbejdere.get(alleMedarbejdere.size()-1));
			HelloFX.setRoot("Mainmenu");
		} else {
			System.out.println("farvel");
		}
	}
	
	

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		System.out.println("You clicked me!");
		label.setText("Hello World!");
		HelloFX.setRoot("Loginpage");
	}

	@FXML
	public void startGame(ActionEvent e) throws IOException {
		HelloFX.setRoot("Loginpage");
	}

}
