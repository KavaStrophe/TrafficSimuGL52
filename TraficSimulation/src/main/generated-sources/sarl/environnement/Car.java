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
import org.arakhne.afc.gis.road.primitive.RoadConnection;
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
  
  public Car(final UUID id, final float length, final RoadConnection point, final float maxSpeed, final float maxAcc, final float minAcc, final Color color) {
    super(id, length, point, maxSpeed, maxAcc, minAcc);
    this.model = null;
    this.color = color;
  }
  
  public Car(final UUID id, final RoadConnection connect, final CarModel model) {
    this(id, model.getLength(), connect, model.getMaxSpeed(), model.getMaxAcc(), model.getMinAcc(), model.getColor());
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
  
  @Pure
  public float getMaxSpeed() {
    return this.model.getMaxSpeed();
  }
  
  @Pure
  public float getMaxAcc() {
    return this.model.getMaxAcc();
  }
  
  @Pure
  public float getMinAcc() {
    return this.model.getMinAcc();
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
