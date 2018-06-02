/**
 * Tout objet mobile se trouvant sur la route
 */
package environnement;

import environnement.AbstractStaticObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.UUID;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class AbstractMobileObject extends AbstractStaticObject {
  private float maxLinearSpeed;
  
  private float maxLinearAcceleration;
  
  private ArrayList<RoadSegment> targets;
  
  private float currentLinearSpeed = 0;
  
  private float currentLinearAcceleration = 0;
  
  public AbstractMobileObject(final UUID id, final float length, final Point1d position, final float maxSpeed, final float maxAcc) {
    super(id, position, length);
    this.maxLinearSpeed = maxSpeed;
    this.maxLinearAcceleration = maxAcc;
    ArrayList<RoadSegment> _arrayList = new ArrayList<RoadSegment>();
    this.targets = _arrayList;
  }
  
  public void setMaxSpeed(final float maxSpeed) {
    this.maxLinearSpeed = maxSpeed;
  }
  
  public void setMaxAcc(final float maxAcc) {
    this.maxLinearAcceleration = maxAcc;
  }
  
  public void setCurrSpeed(final float speed) {
    this.currentLinearSpeed = speed;
  }
  
  public void setCurrAcc(final float acceleration) {
    this.currentLinearAcceleration = acceleration;
  }
  
  @Pure
  public Point1d move(final Point1d position, final float simulationDuration) {
    return null;
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
    AbstractMobileObject other = (AbstractMobileObject) obj;
    if (Float.floatToIntBits(other.maxLinearSpeed) != Float.floatToIntBits(this.maxLinearSpeed))
      return false;
    if (Float.floatToIntBits(other.maxLinearAcceleration) != Float.floatToIntBits(this.maxLinearAcceleration))
      return false;
    if (Float.floatToIntBits(other.currentLinearSpeed) != Float.floatToIntBits(this.currentLinearSpeed))
      return false;
    if (Float.floatToIntBits(other.currentLinearAcceleration) != Float.floatToIntBits(this.currentLinearAcceleration))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.maxLinearSpeed);
    result = prime * result + Float.floatToIntBits(this.maxLinearAcceleration);
    result = prime * result + Float.floatToIntBits(this.currentLinearSpeed);
    result = prime * result + Float.floatToIntBits(this.currentLinearAcceleration);
    return result;
  }
}
