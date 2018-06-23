package configurationwindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import configfile.CarConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchWindow extends Application {

	private LaunchController controller;
	
	
	
	public LaunchController getController() {
		return controller;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LaunchWindow.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			controller = (LaunchController)loader.getController();
			controller.setStage(primaryStage); 
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void run(){
		launch();
	}

	public void run(String[] args){
		launch(args);
	}
	
	public File getShapeFile(){
		return controller.getShapeFile();
	}
	
	public List<CarConfig> getCarConfigList(){
		
		List<CarConfig> list = new ArrayList<CarConfig>();
		list.addAll(getCarConfigList());
			
		return list;
		
	}
}
