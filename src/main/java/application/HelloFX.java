package application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloFX extends Application {

	
	
    @Override
    public void start(Stage stage) throws IOException {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(l, 640, 480);
        stage.setScene(scene);
        stage.show();
    }
	 

    public static void main(String[] args) {

        launch();

    
        
        
    }

}

