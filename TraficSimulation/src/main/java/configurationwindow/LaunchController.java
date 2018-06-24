package configurationwindow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;

import configfile.CarConfig;
import environnement.Simulation;
import io.sarl.bootstrap.SRE;
import io.sarl.bootstrap.SREBootstrap;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class LaunchController implements Initializable {
	
	@FXML
	private AnchorPane rootPane;
	@FXML
	private Button importButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button importShapeFileButton;
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Button nextButton;
	@FXML
	private Label shapeFileNameLabel;
	@FXML
	private TableView<CarConfig> carTableView;
	@FXML
	private TableColumn<CarConfig, String> carNameColumn;
	@FXML
	private TableColumn<CarConfig, String> lenghtColumn;
	@FXML
	private TableColumn<CarConfig, String> maxSpeedColumn;
	@FXML
	private TableColumn<CarConfig, String> maxAccelerationColumn;
	@FXML
	private TableColumn<CarConfig, String> maxDecelerationColumn;
	@FXML
	private TableColumn<CarConfig, String> colorColumn;
	@FXML
	private TableColumn<CarConfig, String> agentsColumn;

	
	ObjectMapper objectMapper = new ObjectMapper();
	private Stage stage;
	private LaunchWindow launcher;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		

		carNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		carNameColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarConfig, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarConfig, String> t) {
	                    ((CarConfig) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setName(t.getNewValue());
	                }
	            }
	        );
		lenghtColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		lenghtColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarConfig, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarConfig, String> t) {
	                    ((CarConfig) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setLenght(Float.parseFloat(t.getNewValue()));
	                }
	            }
	        );
		maxSpeedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		maxSpeedColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarConfig, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarConfig, String> t) {
	                    ((CarConfig) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setMaxSpeed(Float.parseFloat(t.getNewValue()));
	                }
	            }
	        );
		maxAccelerationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		maxAccelerationColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarConfig, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarConfig, String> t) {
	                    ((CarConfig) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setMaxAccel(Float.parseFloat(t.getNewValue()));
	                }
	            }
	        );
		maxDecelerationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		maxDecelerationColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarConfig, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarConfig, String> t) {
	                    ((CarConfig) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setMaxDecel(Float.parseFloat(t.getNewValue()));
	                }
	            }
	        );
		colorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		colorColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarConfig, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarConfig, String> t) {
	                    ((CarConfig) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setColor(Color.valueOf(t.getNewValue()));
	                }
	            }
	        );
		agentsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		agentsColumn.setOnEditCommit(
	            new EventHandler<CellEditEvent<CarConfig, String>>() {
	                @Override
	                public void handle(CellEditEvent<CarConfig, String> t) {
	                    ((CarConfig) t.getTableView().getItems().get(
	                            t.getTablePosition().getRow())
	                            ).setAgentNumber(Integer.parseInt(t.getNewValue()));
	                }
	            }
	        );
		
		
		carNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		lenghtColumn.setCellValueFactory(cellData -> cellData.getValue().lenghtProperty().asString());
		maxSpeedColumn.setCellValueFactory(cellData -> cellData.getValue().maxSpeedProperty().asString());
		maxAccelerationColumn.setCellValueFactory(cellData -> cellData.getValue().maxAccelProperty().asString());
		maxDecelerationColumn.setCellValueFactory(cellData -> cellData.getValue().maxDecelProperty().asString());
		colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty().asString());
		agentsColumn.setCellValueFactory(cellData -> cellData.getValue().agentNumberProperty().asString());
		
		
		
		addButton.setOnAction((event) ->{ 
			Configuration.getInstance().getCarConfigList().add(new CarConfig("New Car", 0,0,0,0,Color.WHITE,0));
		});
		
		deleteButton.setOnAction((event) -> {
			try{
				Configuration.getInstance().getCarConfigList().remove(carTableView.getSelectionModel().getSelectedIndex());
			}catch(ArrayIndexOutOfBoundsException e){
				
			}
			
		});
		
		importButton.setOnAction((event) -> {
			

		    FileChooser fileChooser = new FileChooser();
		    fileChooser.setTitle("Open Car List File");
		    fileChooser.getExtensionFilters().addAll(
		            new ExtensionFilter("Car Files", "*.car"),
		            new ExtensionFilter("All Files", "*.*"));
		    
		    fileChooser.setInitialDirectory(new File("./"));
		    File selectedFile = fileChooser.showOpenDialog(stage);
		    
		    if (selectedFile != null) {
			    try {
					ArrayList<CarConfig> importedCarConfig = objectMapper.readValue(selectedFile, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, CarConfig.class));
					Configuration.getInstance().getCarConfigList().clear();
					Configuration.getInstance().getCarConfigList().addAll(importedCarConfig);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    }
		});
		
		saveButton.setOnAction((event) -> {
			
		    FileChooser fileChooser = new FileChooser();
		    fileChooser.setTitle("Save Car List as");
		    fileChooser.getExtensionFilters().addAll(
		            new ExtensionFilter("Car Files", "*.car"),
		            new ExtensionFilter("All Files", "*.*"));
		    fileChooser.setInitialDirectory(new File("./"));
		    
		    File selectedFile = fileChooser.showSaveDialog(stage);
		    
		    if (selectedFile != null) {
		       try {
		    	   objectMapper.writeValue(selectedFile, Configuration.getInstance().getCarConfigList());
		       } catch (IOException e) {
		    	   // TODO Auto-generated catch block
		    	   e.printStackTrace();
		       }
		    }
		});
		
		
		importShapeFileButton.setOnAction((event) -> {
			

		    FileChooser fileChooser = new FileChooser();
		    fileChooser.setTitle("Open ShapeFile File");
		    fileChooser.getExtensionFilters().addAll(
		            new ExtensionFilter("ShapeFile Files", "*.shp"),
		            new ExtensionFilter("All Files", "*.*"));
		    
		    fileChooser.setInitialDirectory(new File("./"));
		    File selectedFile = fileChooser.showOpenDialog(stage);
		    
		    if (selectedFile != null) {
		    	Configuration.getInstance().setShapeFile(selectedFile);
		    	shapeFileNameLabel.setText(Configuration.getInstance().getShapeFile().getAbsolutePath());
		    	if(!Configuration.getInstance().getCarConfigList().isEmpty()){
		    		nextButton.setDisable(false);
		    	}
		    }
		});
		
		
		cancelButton.setOnAction((event)->{
			stage.close();
		});
		
		nextButton.setOnAction((event)->{
			Configuration.setValid(true);
			stage.close();
			
		});
	}
	
	
	
	public void setLauncher(LaunchWindow launcher) {
		this.launcher = launcher;
		carTableView.setItems(Configuration.getInstance().getCarConfigList());
		
		
		Configuration.getInstance().getCarConfigList().addListener(new ListChangeListener<CarConfig>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends CarConfig> c) {
				if(Configuration.getInstance().getCarConfigList().isEmpty()){
					nextButton.setDisable(true);
				}else if(Configuration.getInstance().getShapeFile() != null){
					nextButton.setDisable(false);
				}
				
			}
			
		});
	}


	public void setStage(Stage stage){
		this.stage = stage;
	}
}
