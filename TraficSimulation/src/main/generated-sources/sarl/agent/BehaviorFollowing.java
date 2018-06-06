package agent;

import environnement.Car;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;

/**
 * @author Nicolas
 * 
 * Comportement que le conducteur adoptera lorsqu'une voiture se situera dans son champs de vision
 */
@SarlSpecification("0.7")
@SarlElementType(10)
@SuppressWarnings("all")
public class BehaviorFollowing {
  public float run(final Car body, final float distanceFromObject, final float maxSpeed) {
    return this.computeIDM(body.getMaxAcc(), body.getMinAcc(), distanceFromObject, body.getSpeed(), maxSpeed);
  }
  
  public float computeIDM(final float maxAccAgent, final float maxDesAgent, final float distanceFromObject, final float currSpeed, final float idealSpeed) {
    float finalAcc = 0;
    float safetyDistance = ((currSpeed / 10) * 6);
    float diffSpeed = (idealSpeed - currSpeed);
    if ((diffSpeed < 0)) {
      diffSpeed = (diffSpeed * (-1));
    }
    float timeBeforeCollision = (distanceFromObject / idealSpeed);
    double firstPart = Math.pow((currSpeed / idealSpeed), 4);
    float secondPart1 = ((safetyDistance + (currSpeed * timeBeforeCollision)) / distanceFromObject);
    double _sqrt = Math.sqrt((maxAccAgent * maxDesAgent));
    double _multiply = ((2 * distanceFromObject) * _sqrt);
    double secondPart2 = ((currSpeed * diffSpeed) / _multiply);
    double secondPart = Math.pow((secondPart1 + secondPart2), 2);
    finalAcc = Double.valueOf((maxAccAgent * ((1 - firstPart) - secondPart))).floatValue();
    if ((finalAcc < ((-1) * maxDesAgent))) {
      finalAcc = maxDesAgent;
    }
    if ((finalAcc > maxAccAgent)) {
      finalAcc = maxAccAgent;
    }
    return Float.valueOf(finalAcc).floatValue();
  }
  
  @SyntheticMember
  public BehaviorFollowing() {
    super();
  }
}
