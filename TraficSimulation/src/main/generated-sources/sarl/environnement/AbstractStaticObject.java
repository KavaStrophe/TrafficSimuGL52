/**
 * Tous les objets immobiles se situant sur la route
 */
package environnement;

import com.google.common.base.Objects;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class AbstractStaticObject {
  private float position;
  
  private UUID id;
  
  private float length;
  
  private RoadConnection entryPoint;
  
  private RoadSegment segment;
  
  public AbstractStaticObject(final UUID id, final RoadConnection point, final float lenght) {
    this.segment = point.getConnectedSegment(0);
    this.entryPoint = point;
    this.id = id;
    RoadConnection _beginPoint = this.segment.getBeginPoint();
    boolean _equals = Objects.equal(point, _beginPoint);
    if (_equals) {
      this.position = 0;
    } else {
      this.position = Double.valueOf(this.segment.getLength()).floatValue();
    }
    this.length = this.length;
  }
  
  @Pure
  public Boolean onSameSegment(final AbstractStaticObject object) {
    if (((this.segment == object.segment) && 
      (this.entryPoint == object.entryPoint))) {
      return Boolean.valueOf(true);
    } else {
      return Boolean.valueOf(false);
    }
  }
  
  public void setLength(final float length) {
    this.length = length;
  }
  
  @Pure
  public float getPosition() {
    return this.position;
  }
  
  public void setPosition(final Point1d position) {
    this.setPosition(position);
  }
  
  @Pure
  public UUID getID() {
    return this.id;
  }
  
  public void setSegment(final RoadSegment segment) {
    this.segment = segment;
  }
  
  @Pure
  public RoadSegment getSegment() {
    return this.segment;
  }
  
  public void setEntryPoint(final RoadConnection point) {
    this.entryPoint = point;
  }
  
  @Pure
  public RoadConnection getEntryPoint() {
    return this.entryPoint;
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
    AbstractStaticObject other = (AbstractStaticObject) obj;
    if (Float.floatToIntBits(other.position) != Float.floatToIntBits(this.position))
      return false;
    if (!java.util.Objects.equals(this.id, other.id)) {
      return false;
    }
    if (Float.floatToIntBits(other.length) != Float.floatToIntBits(this.length))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.position);
    result = prime * result + java.util.Objects.hashCode(this.id);
    result = prime * result + Float.floatToIntBits(this.length);
    return result;
  }
}
