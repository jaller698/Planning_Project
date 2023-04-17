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
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import FXML.ProjektViewController;

public class StartController {

	public static ArrayList<Medarbejder> alleMedarbejdere = new ArrayList<Medarbejder>();
	public static ArrayList<Projekt> alleProjekter = new ArrayList<Projekt>();

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
<<<<<<< Updated upstream
	public Button signup;
	public Button viewProjects;
	public Button logOut;
	@FXML 
	public TextField projektNavn = new TextField();
	
=======
	private Button signup;
	@FXML 
	private Button viewProjects;
	@FXML 
	private Button logOut;
	@FXML 
	public TextField projektNavn = new TextField();
	
	@FXML
	private static Alert alert = new Alert(AlertType.NONE);
	@FXML
	private ChoiceBox<Medarbejder> leaderPick = new ChoiceBox<Medarbejder>(FXCollections.observableArrayList(app.alleMedarbejdere));;
	
	
	
>>>>>>> Stashed changes
	public static int loginIndex;
	public static Medarbejder currentMedarbejder;
	
	public void createProjekt() throws IOException {
<<<<<<< Updated upstream
		alleProjekter.add(new Projekt(projektNavn.getText()));
		alleMedarbejdere.get(loginIndex).addProjekt(alleProjekter.get(alleProjekter.size()-1));
=======
		Projekt p = new Projekt(projektNavn.getText());
		Application.alleProjekter.add(p); //hvis noget går galt udkommentær denne linje
		app.getMedarbejder().addProjekt(p);
		
		
		
		
>>>>>>> Stashed changes
		HelloFX.setRoot("Mainmenu", StartController.class);
	}
	public void logOut() throws IOException {
		//sættes til -1 da index ikke kan være negativt. Tænker at vi implementerer et tjek for det. Det er mest bare så der ikke sker noget
		//fucky wucky shit, men det burde egentlig aldrig blive et problem siden man ikke kan komme nogen stedet fra login page/signup page uden at logge ind
		//og dermed skifte index. Bare extra safety. 
		StartController.loginIndex = -1;
		currentMedarbejder = null;
		HelloFX.setRoot("Loginpage", StartController.class);			
	}
	
	
	public void toProjektCreater() throws IOException {
		HelloFX.setRoot("ProjektCreater", StartController.class);
		
		leaderPick.getItems().addAll(app.alleMedarbejdere);

	}
	public void viewProjects() throws IOException {
		HelloFX.setRoot("projektview2", ProjektViewController.class);
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
			for (Medarbejder M : alleMedarbejdere) {
				if (M.navn.equals(loginUsername.getText()) == true && M.password.equals(loginPassword.getText()) == true) {
					checkSuccesful = true;
					loginIndex = alleMedarbejdere.indexOf(M);
					currentMedarbejder = M;
					System.out.println(loginIndex);
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
			
			alleMedarbejdere.add(new Medarbejder(signupUsername.getText(), signupPassword.getText()));
			System.out.println(alleMedarbejdere.get(alleMedarbejdere.size()-1));
			loginIndex = alleMedarbejdere.size()-1;
			currentMedarbejder = alleMedarbejdere.get(loginIndex);
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


//		alleMedarbejdere.add(h);
//		alleMedarbejdere.add(l);
//		alleMedarbejdere.add(p);
//		h.p.add(new Projekt("1h"));
//		h.p.add(new Projekt("2h"));
//		h.p.add(new Projekt("3h"));
//		l.p.add(new Projekt("1l"));
//		l.p.add(new Projekt("2l"));
//		l.p.add(new Projekt("3l"));


	}


}
