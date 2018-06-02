package agent;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;

/**
 * @author Nicolas
 *  Comportement qui s'exécutera lorsque le conducteur n'aura aucune autre voiture dans son champ de vision
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class BehaviorSeeking {
  @SyntheticMember
  public BehaviorSeeking() {
    super();
  }
}
