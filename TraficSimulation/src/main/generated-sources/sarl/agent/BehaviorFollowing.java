package agent;

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
  public float computeIDM(final float maxAccAgent, final float maxDesAgent, final float distanceFromObject, final float objectSpeed, final float currSpeed, final float idealSpeed) {
    float finalSpeed = 0;
    float safetyDistance = ((currSpeed / 10) * 6);
    System.out.println(("Safety Distance : " + Float.valueOf(safetyDistance)));
    float diffSpeed = (currSpeed - objectSpeed);
    if ((diffSpeed < 0)) {
      diffSpeed = (diffSpeed * (-1));
    }
    System.out.println(("Diff Vitesse : " + Float.valueOf(diffSpeed)));
    float timeBeforeCollision = (distanceFromObject / currSpeed);
    System.out.println(("timeBeforeCollision : " + Float.valueOf(timeBeforeCollision)));
    finalSpeed = ((safetyDistance + (currSpeed * timeBeforeCollision)) / distanceFromObject);
    System.out.println(("First step : " + Float.valueOf(finalSpeed)));
    float _finalSpeed = finalSpeed;
    double _sqrt = Math.sqrt((maxAccAgent * maxDesAgent));
    float _floatValue = Double.valueOf(((2 * distanceFromObject) * _sqrt)).floatValue();
    float _divide = ((currSpeed * diffSpeed) / _floatValue);
    finalSpeed = (_finalSpeed + _divide);
    System.out.println(("Second step : " + Float.valueOf(finalSpeed)));
    finalSpeed = Double.valueOf(Math.pow(finalSpeed, 2)).floatValue();
    System.out.println(("Third step : " + Float.valueOf(finalSpeed)));
    float _floatValue_1 = Double.valueOf(Math.pow((currSpeed / idealSpeed), 4)).floatValue();
    float _minus = (1 - _floatValue_1);
    float _minus_1 = (_minus - finalSpeed);
    finalSpeed = _minus_1;
    System.out.println(("Fourth step : " + Float.valueOf(finalSpeed)));
    finalSpeed = (maxAccAgent * finalSpeed);
    return finalSpeed;
  }
  
  @SyntheticMember
  public BehaviorFollowing() {
    super();
  }
}
