package application;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class Main extends Application {
	  public static Scene scene;
	    static int Num=0;
	
	    @Override
	    public void start(Stage stage) throws IOException {
	        scene = new Scene(loadFXML("DashboardManage"), 1370, 650);
	        // Load the application icon image
	        InputStream iconStream = getClass().getResourceAsStream("/application/icons/reading.png");
	        
	        if (iconStream != null) {
	            Image icon = new Image(iconStream);
	          	         
	            stage.getIcons().add(icon);
	        } else {
	            System.err.println("Icon image not found.");
	        }
	        stage.setMinHeight(500);
	        stage.setMinWidth(800);
	        stage.setMaximized(true);
	        stage.setIconified(false);
	        stage.setTitle("DASHBOARD");
	        stage.setScene(scene);
	        Num=1;
	        
	        stage.show();

	    }
	    
	

	    static void setRoot(String fxml) throws IOException {
	        Num=2;
	        scene.setRoot(loadFXML(fxml));
	    }

	    private static Parent loadFXML(String fxml) throws IOException {
	        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
	        return fxmlLoader.load();
	    }

	    @Override
	    public void stop(){
	        if(Num==2){
	        	
	        	DashboardManage.mySession.close();
	        	DashboardManage.factory.close();
	        }
	        System.out.println("Stage is closing");
	        // Save file
	    }

	    public static void main(String[] args) {
	        launch();
	    }

}
