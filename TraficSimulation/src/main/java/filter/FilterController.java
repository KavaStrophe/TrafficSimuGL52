package filter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;

import configfile.CarConfig;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**Controller for the configuration window
 * 
 * @author Nahil
 *
 */
public class FilterController implements Initializable {
	
	@FXML
	private AnchorPane rootPane;
	@FXML
	private TableView<CarFilter> filterTableView;
	@FXML
	private TableColumn<CarFilter, String> carModelColumn;
	@FXML
	private TableColumn<CarFilter, String> minSpeedColumn;
	@FXML
	private TableColumn<CarFilter, String> maxSpeedColumn;
	@FXML
	private TableColumn<CarFilter, String> speedColumn;
	@FXML
	private TableColumn<CarFilter, String> colorColumn;
	@FXML
	private TableColumn<CarFilter, String> activateColumn;
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button upButton;
	@FXML
	private Button downButton;
	@FXML
	private Button enableButton;
	@FXML
	private CheckBox otherCheckbox;
	
	private Stage stage;


	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		otherCheckbox.selectedProperty().bindBidirectional(FilterConfiguration.getInstance().displayOtherCarsProperty());
		filterTableView.setItems(FilterConfiguration.getInstance().getFilterList());
		
		
		carModelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		carModelColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarFilter, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarFilter, String> t) {
	                    ((CarFilter) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setCarModel(t.getNewValue());
	                }
	            }
	        );
		
		minSpeedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		minSpeedColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarFilter, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarFilter, String> t) {
	                    ((CarFilter) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setMinSpeed(Float.parseFloat(t.getNewValue()));
	                }
	            }
	        );
		
		maxSpeedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		maxSpeedColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarFilter, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarFilter, String> t) {
	                    ((CarFilter) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setMaxSpeed(Float.parseFloat(t.getNewValue()));
	                }
	            }
	        );
		
		
		colorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		colorColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarFilter, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarFilter, String> t) {
	                    ((CarFilter) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setColor(Color.valueOf(t.getNewValue()));
	                }
	            }
	        );
		

		carModelColumn.setCellValueFactory(cellData -> cellData.getValue().carModelProperty());
		minSpeedColumn.setCellValueFactory(cellData -> cellData.getValue().minSpeedProperty().asString());
		maxSpeedColumn.setCellValueFactory(cellData -> cellData.getValue().maxSpeedProperty().asString());
		activateColumn.setCellValueFactory(cellData -> cellData.getValue().activeProperty().asString());
		colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty().asString());

		
		
		
		addButton.setOnAction((event) ->{ 
			FilterConfiguration.getInstance().getFilterList().add(new CarFilter(null, -1,-1,Color.WHITE, true));
		});
		
		deleteButton.setOnAction((event) -> {
			try{
				FilterConfiguration.getInstance().getFilterList().remove(filterTableView.getSelectionModel().getSelectedIndex());
			}catch(ArrayIndexOutOfBoundsException e){
				
			}
			
		});
		
		upButton.setOnAction((event) -> {
			try{
				
				Collections.swap( FilterConfiguration.getInstance().getFilterList(),filterTableView.getSelectionModel().getSelectedIndex(), filterTableView.getSelectionModel().getSelectedIndex()-1);
			

			}catch(ArrayIndexOutOfBoundsException e){
				
			}
			
		});
		
		downButton.setOnAction((event) -> {
			try{
				Collections.swap( FilterConfiguration.getInstance().getFilterList(),filterTableView.getSelectionModel().getSelectedIndex(), filterTableView.getSelectionModel().getSelectedIndex()+1);
		
			}catch(ArrayIndexOutOfBoundsException e){
				
			}
			
		});
		
		enableButton.setOnAction((event) ->{ 
			FilterConfiguration.getInstance().getFilterList().get(filterTableView.getSelectionModel().getSelectedIndex()).setActive(!FilterConfiguration.getInstance().getFilterList().get(filterTableView.getSelectionModel().getSelectedIndex()).getActive());
		});
	}
	
	

	public void setStage(Stage stage){
		this.stage = stage;
	}
}
