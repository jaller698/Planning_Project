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


import java.io.IOException;


public class HelloFX extends Application {

	@FXML
	public Button begin;
	
	private static Scene scene;
	public static Stage cStage;

	// starter det hele, og bruges også senere til at tegne scenerne
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("heya");
		scene = new Scene(loadFXML("Start"));
		cStage = stage;
		stage.setTitle("MENU");
		stage.setScene(scene);
		stage.close();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
		cStage.sizeToScene();
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloFX.class.getResource(fxml + ".fxml"));
		Parent p = fxmlLoader.load();
		return p;
	}

	
	
}








