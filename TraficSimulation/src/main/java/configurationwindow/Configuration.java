package configurationwindow;

import java.io.File;

import configfile.CarConfig;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

public class Configuration {
	
	
	
	private static volatile Configuration instance;
	
	private ObservableList<CarConfig> carConfigList = FXCollections.observableArrayList();
	private File shapeFile;
	private boolean isValid;
	
	
	
	
	private Configuration() {
		super();
	}

	
	public final static Configuration getInstance() {
		if(Configuration.instance == null) {
			synchronized(Configuration.class) {
				if(Configuration.instance == null) {
					Configuration.instance = new Configuration();
				}
			}
		}
		return instance;
	}

	public File getShapeFile() {
		return shapeFile;
	}


	public ObservableList<CarConfig> getCarConfigList(){

		
		return carConfigList;
		
	}

	public void launchConfigurationWindow() {
		isValid = false;
		LaunchWindow l = new LaunchWindow();
		Platform.setImplicitExit(false);
		new JFXPanel();
        Platform.runLater(() -> {
            try {
				l.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
	}

	public void setShapeFile(File selectedFile) {
		// TODO Auto-generated method stub
		shapeFile = selectedFile;
	}


	public boolean isValid() {
		return isValid;
	}


	public void setValid(boolean b) {
		isValid = b;
	}
}
