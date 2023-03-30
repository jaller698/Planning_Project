package application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.File;
import FXML.*;


import java.io.IOException;


public class HelloFX extends Application {
	@FXML
	private Button begin;
	private static Scene scene;
	public static Stage cStage;
	Medarbejder h = new Medarbejder("Hans","heste123");
	Medarbejder l = new Medarbejder("Erik","fisk123");
	Medarbejder p = new Medarbejder("Peter","næbdyr123");
	// starter det hele, og bruges også senere til at tegne scenerne
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("heya");
		scene = new Scene(loadFXML("Frontpage", StartController.class));
		cStage = stage;
		stage.setTitle("MENU");
		stage.setScene(scene);
		stage.close();
		stage.show();
	}
	
	public static void main(String[] args) {

		launch(args);
	}
	
	public static <T> void setRoot(String fxml, Class<T> c) throws IOException {
		scene.setRoot(loadFXML(fxml,c));
		cStage.sizeToScene();
	}
	
	private static <T> Parent loadFXML(String fxml, Class<T> c) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(c.getResource(fxml + ".fxml"));
		Parent p = fxmlLoader.load();
		return p;
	}
	
	
	

	
	
}








