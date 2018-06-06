package agent;

import environnement.Car;
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
  public float run(final Car body, final float maxSpeed) {
    return this.computeIDM(body.getMaxAcc(), body.getMinAcc(), body.getSpeed(), maxSpeed);
  }
  
  public float computeIDM(final float maxAcc, final float minAcc, final float currSpeed, final float maxSpeed) {
    double firstStep = Math.pow((currSpeed / maxSpeed), 4);
    double finalStep = (maxAcc * (1 - firstStep));
    if ((finalStep < ((-1) * minAcc))) {
      finalStep = minAcc;
    }
    if ((finalStep > maxAcc)) {
      finalStep = maxAcc;
    }
    return Double.valueOf(finalStep).floatValue();
  }
  
  @SyntheticMember
  public BehaviorSeeking() {
    super();
  }
}
