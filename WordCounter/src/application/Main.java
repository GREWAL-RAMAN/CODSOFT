package application;
	
import java.io.InputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("WordCounter.fxml"));
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			   // Load the application icon image
	        InputStream iconStream = getClass().getResourceAsStream("/application/icon/count.png");
	        
	        if (iconStream != null) {
	            Image icon = new Image(iconStream);
	          	         
	            primaryStage.getIcons().add(icon);
	        } else {
	            System.err.println("Icon image not found.");
	        }
	        primaryStage.setTitle("WORD COUNTER");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
