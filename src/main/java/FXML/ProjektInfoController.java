package FXML;

import java.util.ArrayList;

import application.Medarbejder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class ProjektInfoController {
	//ObservableList<Medarbejder> projektMedarbejdere = FXCollections.observableArrayList(new Medarbejder("Frank", "abe123"));
	//lige nu viser den alle medarbejdere der er registrerede.
	//Vi skal finde en måde at loade det valgte projekt. Find search metoden.
	ObservableList<Medarbejder> projektMedarbejdere = convertToOLM(StartController.alleMedarbejdere);
	public ListView<Medarbejder> medarbejderList = new ListView<Medarbejder>();
	public void initialize(){	
		medarbejderList.setItems(projektMedarbejdere);
		
		
		
		
	}
	public ObservableList<Medarbejder> convertToOLM(ArrayList<Medarbejder> a) {
		ObservableList<Medarbejder> o = FXCollections.observableArrayList();
		for(Medarbejder M : a) {
			o.add(M);
		}
		return o;
	}
}
