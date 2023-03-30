package FXML;

import java.io.IOException;
import java.util.ArrayList;

import application.HelloFX;
import application.Medarbejder;
import application.Projekt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ProjektViewController {
	@FXML
	public Button refresh;
	@FXML 
	Button logout;
	@FXML
	public Label welcome = new Label();
	@FXML
	public ListView<Projekt> projektList = new ListView<Projekt>();
	@FXML
	ObservableList<Projekt> projektData = convertToOL(StartController.alleMedarbejdere.get(StartController.loginIndex).p);
	
	public Button backToMain;
	public Button searchProjekter;
	public TextField projektInput = new TextField();
	public void searchProjekter() throws IOException {
		//medarbejderList.setItems(projektMedarbejdere);
//		for(Medarbejder P : projektMedarbejdere) {
//			System.out.println(P);
//		}
		HelloFX.setRoot("Projektinfo", ProjektInfoController.class);
		//initialize();

	}
	public void backToMain() throws IOException {
		HelloFX.setRoot("Mainmenu", StartController.class);
	}
	@FXML
	public void refresh(ActionEvent e) throws IOException {
		projektList.setItems(projektData);
	}
	public void logOut() throws IOException {
		//sættes til -1 da index ikke kan være negativt. Tænker at vi implementerer et tjek for det. Det er mest bare så der ikke sker noget
		//fucky wucky shit, men det burde egentlig aldrig blive et problem siden man ikke kan komme nogen stedet fra login page/signup page uden at logge ind
		//og dermed skifte index. Bare extra safety. 
		StartController.loginIndex = -1;
		HelloFX.setRoot("Loginpage", StartController.class);
		
	}
	public void initialize() {
		welcome.setText("hej " + StartController.alleMedarbejdere.get(StartController.loginIndex).navn);
		System.out.println("initializing projektviewcontroller");
		projektList.setItems(projektData);
	}

	public ObservableList<Projekt> convertToOL(ArrayList<Projekt> a) {
		ObservableList<Projekt> o = FXCollections.observableArrayList();
		for(Projekt P : a) {
			o.add(P);
		}
		return o;
	}

}
