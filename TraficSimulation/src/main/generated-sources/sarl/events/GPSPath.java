package events;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class GPSPath extends Event {
  @SyntheticMember
  public GPSPath() {
    super();
  }
  
  @SyntheticMember
  public GPSPath(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
