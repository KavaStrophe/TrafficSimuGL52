package configfile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;


@JsonDeserialize(using = CarConfigDeserializer.class)
public class CarConfig {
	
	private StringProperty name;
	private FloatProperty lenght;
	private FloatProperty maxSpeed;
	private FloatProperty maxAccel;
	private FloatProperty maxDecel;
	private ObjectProperty<Color> color;
	private IntegerProperty agentNumber;
	
	

	public CarConfig(String name, float lenght, float maxSpeed, float maxAccel, float maxDecel,  Color color, int agentNumber) {
		super();
		this.name = new SimpleStringProperty(name);
		this.lenght =  new SimpleFloatProperty(lenght);
		this.maxSpeed = new SimpleFloatProperty(maxSpeed);
		this.maxAccel = new SimpleFloatProperty(maxAccel);
		this.maxDecel = new SimpleFloatProperty(maxDecel);
		this.color = new SimpleObjectProperty<Color>(color);
		this.agentNumber = new SimpleIntegerProperty(agentNumber);
	}
	
	
    public StringProperty nameProperty() {
        return name;
    }
	
    public FloatProperty lenghtProperty() {
        return lenght;
    }
    
    public FloatProperty maxSpeedProperty() {
        return maxSpeed;
    }
    
    public FloatProperty maxAccelProperty() {
        return maxAccel;
    }
    
    public FloatProperty maxDecelProperty() {
        return maxDecel;
    }
    
    public ObjectProperty<Color> colorProperty() {
        return color;
    }
    
    public IntegerProperty agentNumberProperty() {
        return agentNumber;
    }
    
    
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name); 
	}
	public float getLenght() {
		return lenght.get();
	}
	public void setLenght(float lenght) {
		this.lenght.set(lenght);
	}
	public float getMaxSpeed() {
		return maxSpeed.get();
	}
	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed.set(maxSpeed);;
	}
	public float getMaxAccel() {
		return maxAccel.get();
	}
	public void setMaxAccel(float maxAccel) {
		this.maxAccel.set(maxAccel);
	}
	public float getMaxDecel() {
		return maxDecel.get();
	}
	public void setMaxDecel(float maxDecel) {
		this.maxDecel.set(maxDecel);
	}
	public Color getColor() {
		return color.get();
	}
	public void setColor(Color color) {
		this.color.set(color);
	}

	public int getAgentNumber() {
		return agentNumber.get();
	}

	public void setAgentNumber(int agentNumber) {
		this.agentNumber.set(agentNumber);
	}
	
}
