package FXML;

import java.io.IOException;

import application.Projekt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ProjektViewController {
	@FXML
	public Button refresh;
	@FXML
	public Label welcome = new Label();
	@FXML
	public ListView<Projekt> projektList = new ListView<Projekt>();
	ObservableList<Projekt> data = FXCollections.observableArrayList(new Projekt("1"));
	
	@FXML
	public void refresh(ActionEvent e) throws IOException {
		projektList.setItems(data);
	}
	
	public void initialize() {
		welcome.setText("hej");
        


	}
}
