package filter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**JavaFX Application used to launch the filter window during the simulation
 * 
 * @author Nahil
 *
 */
public class FilterWindow extends Application {

	private FilterController controller;

	
	
	public FilterController getController() {
		return controller;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FilterWindow.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			controller = (FilterController)loader.getController();
			controller.setStage(primaryStage); 
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setY(100);
			primaryStage.setX(100);

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
