/**
 * Evènement déclenché par un agent renvoyant à l'environnement son influence
 */
package events;

import environnement.InfluenceAgent;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(15)
@SuppressWarnings("all")
public class SendedInfluence extends Event {
  public final InfluenceAgent influence;
  
  public SendedInfluence(final InfluenceAgent influence) {
    this.influence = influence;
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
   * Returns a String representation of the SendedInfluence event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("influence", this.influence);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -1783363558L;
}
