package filter;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

/**ConfigurationFilter singleton for the simulation. Keep all the Filter properties. 
 * 
 * @author Nahil
 *
 */
public class FilterConfiguration {
	
	
	
	private static volatile FilterConfiguration instance;
	
	//List of CarFilter
	private ObservableList<CarFilter> filterList = FXCollections.observableArrayList();
	private BooleanProperty displayOtherCars = new SimpleBooleanProperty(true);
	
	
	
	
	private FilterConfiguration() {
		super();
	}

	
	public final static FilterConfiguration getInstance() {
		if(FilterConfiguration.instance == null) {
			synchronized(FilterConfiguration.class) {
				if(FilterConfiguration.instance == null) {
					FilterConfiguration.instance = new FilterConfiguration();
				}
			}
		}
		return instance;
	}



	/**
	 * Launch the FilterWindow used to set the filters
	 */
	public void launchFilterWindow() { 
		FilterWindow l = new FilterWindow();
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



	public ObservableList<CarFilter> getFilterList() {
		return filterList;
	}

	
	public BooleanProperty displayOtherCarsProperty() {
		return displayOtherCars;
	}
	
	public boolean getDisplayOtherCars() {
		return displayOtherCars.get();
	}

}
