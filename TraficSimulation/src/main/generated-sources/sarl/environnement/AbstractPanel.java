/**
 * Tous les panneaux
 */
package environnement;

import environnement.AbstractStaticObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.arakhne.afc.gis.road.primitive.RoadConnection;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class AbstractPanel extends AbstractStaticObject {
  @SyntheticMember
  public AbstractPanel(final UUID id, final RoadConnection point, final float lenght) {
    super(id, point, lenght);
  }
}
