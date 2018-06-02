/**
 * Tous les objets immobiles se situant sur la route
 */
package environnement;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class AbstractStaticObject {
  private Point1d position;
  
  private UUID id;
  
  private float length;
  
  private RoadConnection entryPoint;
  
  public AbstractStaticObject(final UUID id, final Point1d position, final float lenght) {
    this.id = id;
    this.position = position;
    this.length = this.length;
  }
  
  @Pure
  public Boolean onSameSegment(final AbstractStaticObject object) {
    if (((this.position.getSegment() == object.position.getSegment()) && 
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
  public Point1d getPosition() {
    return this.position;
  }
  
  public void setPosition(final Point1d position) {
    this.position = position;
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
    if (!Objects.equals(this.id, other.id)) {
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
    result = prime * result + Objects.hashCode(this.id);
    result = prime * result + Float.floatToIntBits(this.length);
    return result;
  }
}
