package configurationwindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**JavaFX Application used to launch the configuration window before the simulation
 * 
 * @author Nahil
 *
 */
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
	


}
