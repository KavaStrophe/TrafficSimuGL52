/**
 * Modèle indiquant les propriétés génériques que doivent posséder les Cars l'employant
 */
package configurationWindow;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.scene.paint.Color;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class CarModel {
  private float length;
  
  private float maxLinearSpeed;
  
  private float maxLinearAcceleration;
  
  private Color color;
  
  private int maxCountOfModel;
  
  public CarModel(final float length, final float maxSpeed, final float maxAcc, final Color color, final int maxNb) {
    this.length = length;
    this.maxLinearSpeed = maxSpeed;
    this.maxLinearAcceleration = maxAcc;
    this.color = color;
    this.maxCountOfModel = maxNb;
  }
  
  @Pure
  public float getLength() {
    return this.length;
  }
  
  @Pure
  public float getMaxSpeed() {
    return this.maxLinearSpeed;
  }
  
  @Pure
  public float getMaxAcc() {
    return this.maxLinearAcceleration;
  }
  
  @Pure
  public Color getColor() {
    return this.color;
  }
  
  @Pure
  public int getMaxModel() {
    return this.maxCountOfModel;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CarModel other = (CarModel) obj;
    if (Float.floatToIntBits(other.length) != Float.floatToIntBits(this.length))
      return false;
    if (Float.floatToIntBits(other.maxLinearSpeed) != Float.floatToIntBits(this.maxLinearSpeed))
      return false;
    if (Float.floatToIntBits(other.maxLinearAcceleration) != Float.floatToIntBits(this.maxLinearAcceleration))
      return false;
    if (other.maxCountOfModel != this.maxCountOfModel)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.length);
    result = prime * result + Float.floatToIntBits(this.maxLinearSpeed);
    result = prime * result + Float.floatToIntBits(this.maxLinearAcceleration);
    result = prime * result + this.maxCountOfModel;
    return result;
  }
}
