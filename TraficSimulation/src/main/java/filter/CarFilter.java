package filter;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class CarFilter {

	private BooleanProperty active;
	
	private StringProperty carModel;
	private FloatProperty minSpeed;
	private FloatProperty maxSpeed;
	private ObjectProperty<Color> color;
	
	
	
	public CarFilter(String carModel, float minSpeed, float maxSpeed,
			Color color, boolean active) {
		super();
		this.carModel = new SimpleStringProperty(carModel);
		this.minSpeed = new SimpleFloatProperty(minSpeed);
		this.maxSpeed = new SimpleFloatProperty(maxSpeed);
		this.color = new SimpleObjectProperty<Color>(color);
		this.active = new SimpleBooleanProperty(active);
	}
	
	public boolean getActive() {
		return active.get();
	}
	public void setActive(boolean active) {
		this.active.set(active);
	}
	public String getCarModel() {
		return carModel.get();
	}
	public void setCarModel(String carModel) {
		this.carModel.set(carModel); 
	}
	public float getMinSpeed() {
		return minSpeed.get();
	}
	public void setMinSpeed(float minSpeed) {
		this.minSpeed.set(minSpeed);
	}
	public float getMaxSpeed() {
		return maxSpeed.get();
	}
	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed.set(maxSpeed);
	}
	public Color getColor() {
		return color.get();
	}
	public void setColor(Color color) {
		this.color.set(color);
	}
	
	public BooleanProperty activeProperty() {
		return active;
	}
	
	public StringProperty carModelProperty() {
		return carModel;
	}
	
	public FloatProperty minSpeedProperty() {
		return minSpeed;
	}
	
	public FloatProperty maxSpeedProperty() {
		return maxSpeed;
	}
	
	public ObjectProperty<Color> colorProperty() {
		return color;
	}
	
}
