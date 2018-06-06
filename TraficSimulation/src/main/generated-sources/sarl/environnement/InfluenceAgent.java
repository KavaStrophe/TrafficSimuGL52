/**
 * Influence retournée par l'agent
 */
package environnement;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.gis.road.path.RoadPath;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class InfluenceAgent {
  private float acceleration;
  
  private RoadPath roads;
  
  public InfluenceAgent(final RoadPath roads, final float acceleration) {
    this.acceleration = acceleration;
    this.roads = roads;
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
    InfluenceAgent other = (InfluenceAgent) obj;
    if (Float.floatToIntBits(other.acceleration) != Float.floatToIntBits(this.acceleration))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.acceleration);
    return result;
  }
}
