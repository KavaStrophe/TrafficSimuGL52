/**
 * Tous les objets perçus par l'agent
 */
package environnement;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class Percept {
  private String type;
  
  private Point1d position;
  
  private float speed;
  
  public Percept(final String type, final Point1d position, final float speed) {
    this.type = type;
    this.position = position;
    this.speed = speed;
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
    Percept other = (Percept) obj;
    if (!Objects.equals(this.type, other.type)) {
      return false;
    }
    if (Float.floatToIntBits(other.speed) != Float.floatToIntBits(this.speed))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.type);
    result = prime * result + Float.floatToIntBits(this.speed);
    return result;
  }
}
