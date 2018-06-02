/**
 * Corps de l'agent Conducteur
 */
package environnement;

import configurationWindow.CarModel;
import environnement.AbstractMobileObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import javafx.scene.paint.Color;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car extends AbstractMobileObject {
  private CarModel model;
  
  private Color color;
  
  public Car(final UUID id, final float length, final Point1d position, final float maxSpeed, final float maxAcc, final Color color) {
    super(id, length, position, maxSpeed, maxAcc);
    this.model = null;
    this.color = color;
  }
  
  public Car(final UUID id, final Point1d position, final CarModel model) {
    this(id, model.getLength(), position, model.getMaxSpeed(), model.getMaxAcc(), model.getColor());
  }
  
  public CarModel setModel(final CarModel model) {
    CarModel _xblockexpression = null;
    {
      this.color = model.getColor();
      this.setLength(model.getLength());
      this.setMaxSpeed(model.getMaxSpeed());
      this.setMaxAcc(model.getMaxAcc());
      _xblockexpression = this.model = model;
    }
    return _xblockexpression;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
}
