package events;

import environnement.Percept;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class NeedInfluence extends Event {
  public ArrayList<Percept> percepts;
  
  public NeedInfluence(final ArrayList<Percept> percepts) {
    this.percepts = percepts;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  /**
   * Returns a String representation of the NeedInfluence event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("percepts", this.percepts);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -1529845899L;
}
