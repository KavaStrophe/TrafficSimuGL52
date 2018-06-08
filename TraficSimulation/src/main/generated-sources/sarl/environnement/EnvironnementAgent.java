/**
 * Agent d'environnement à lancer pour démarrer la simulation
 */
package environnement;

import agent.Conducteur;
import com.google.common.base.Objects;
import configurationWindow.CarModel;
import configurationWindow.ConfRenderer;
import environnement.Car;
import environnement.InfluenceAgent;
import events.BeginLoop;
import events.EndLoop;
import events.GPSPath;
import events.GPSPathReturn;
import events.NeedInfluence;
import events.SendedInfluence;
import events.TargetReached;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import io.sarl.util.IdentifierScope;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import javax.inject.Inject;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.road.path.RoadPath;
import org.arakhne.afc.gis.road.path.astar.RoadAStar;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import traficWindow.RoadRenderer;
import utils.Loader;

/**
 * @author Nicolas
 */
@SarlSpecification("0.7")
@SarlElementType(18)
@SuppressWarnings("all")
public class EnvironnementAgent extends Agent {
  private StandardRoadNetwork roadNetwork;
  
  private ArrayList<UUID> agents = new ArrayList<UUID>();
  
  private HashMap<UUID, Car> agentBodies = new HashMap<UUID, Car>();
  
  private HashMap<UUID, InfluenceAgent> influences = new HashMap<UUID, InfluenceAgent>();
  
  private ArrayList<RoadConnection> impasses = new ArrayList<RoadConnection>();
  
  private ArrayList<RoadConnection> crois2 = new ArrayList<RoadConnection>();
  
  private ArrayList<RoadConnection> crois3 = new ArrayList<RoadConnection>();
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    this.roadNetwork = Loader.loadShapeFile("ressources/ciechanow.shp");
    Collection<? extends RoadSegment> segments = this.roadNetwork.getRoadSegments();
    RoadNetworkLayer _roadNetworkLayer = new RoadNetworkLayer(this.roadNetwork);
    RoadRenderer.roadLayer = _roadNetworkLayer;
    RoadRenderer.render();
    ConfRenderer.render();
  }
  
  @SyntheticMember
  private void $behaviorUnit$BeginLoop$1(final BeginLoop occurrence) {
    HashMap<UUID, InfluenceAgent> _hashMap = new HashMap<UUID, InfluenceAgent>();
    this.influences = _hashMap;
    this.computePerceptions();
  }
  
  @SyntheticMember
  private void $behaviorUnit$EndLoop$2(final EndLoop occurrence) {
    try {
      for (final UUID conduc : this.agents) {
        boolean _containsKey = this.agentBodies.containsKey(conduc);
        if (_containsKey) {
          this.consumeInfluence(this.agentBodies.get(conduc), this.influences.get(conduc));
        }
      }
      Thread.sleep(150);
      Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
      BeginLoop _beginLoop = new BeginLoop();
      _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.wake(_beginLoop);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @SyntheticMember
  private void $behaviorUnit$SendedInfluence$3(final SendedInfluence occurrence) {
    this.influences.put(occurrence.getSource().getUUID(), occurrence.influence);
    int _size = this.influences.size();
    int _size_1 = this.agentBodies.size();
    boolean _equals = (_size == _size_1);
    if (_equals) {
      Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
      EndLoop _endLoop = new EndLoop();
      _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.wake(_endLoop);
    }
  }
  
  @SyntheticMember
  private void $behaviorUnit$TargetReached$4(final TargetReached occurrence) {
    UUID id = occurrence.getSource().getUUID();
    this.removeAgent(id);
  }
  
  @SyntheticMember
  private void $behaviorUnit$GPSPath$5(final GPSPath occurrence) {
    RoadPath roads = null;
    Car _get = this.agentBodies.get(occurrence.getSource().getUUID());
    boolean _notEquals = (!Objects.equal(_get, null));
    if (_notEquals) {
      RoadConnection entryPoint = this.agentBodies.get(occurrence.getSource().getUUID()).getEntryPoint();
      RoadConnection finalPoint = null;
      do {
        double _random = Math.random();
        int _size = this.impasses.size();
        double _multiply = (_random * _size);
        finalPoint = this.impasses.get(Long.valueOf(Math.round(_multiply)).intValue());
      } while(Objects.equal(finalPoint, entryPoint));
      RoadAStar aStar = new RoadAStar();
      roads = aStar.solve(entryPoint.getPoint(), finalPoint, this.roadNetwork);
    }
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    GPSPathReturn _gPSPathReturn = new GPSPathReturn(roads);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_gPSPathReturn);
  }
  
  protected Car addAgent(final CarModel model) {
    Car _xblockexpression = null;
    {
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      UUID id = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.spawn(Conducteur.class);
      double _random = Math.random();
      int _size = this.impasses.size();
      double _multiply = (_random * _size);
      int numSpawner = Double.valueOf(Math.floor(_multiply)).intValue();
      UUID _randomUUID = UUID.randomUUID();
      RoadConnection _get = this.impasses.get(numSpawner);
      Car car = new Car(_randomUUID, _get, model);
      this.agents.add(id);
      _xblockexpression = this.agentBodies.put(id, car);
    }
    return _xblockexpression;
  }
  
  protected void removeAgent(final UUID id) {
    this.agentBodies.remove(id);
    this.agents.remove(id);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    Destroy _destroy = new Destroy();
    IdentifierScope _identifierScope = new IdentifierScope(id);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_destroy, _identifierScope);
  }
  
  protected void computePerceptions() {
    for (final UUID id : this.agents) {
      boolean _containsKey = this.agentBodies.containsKey(id);
      if (_containsKey) {
        Car agentBody = this.agentBodies.get(id);
        ArrayList<RoadSegment> percepts = this.computePerceptionsFor(agentBody);
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        NeedInfluence _needInfluence = new NeedInfluence(percepts, agentBody);
        IdentifierScope _identifierScope = new IdentifierScope(id);
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_needInfluence, _identifierScope);
      }
    }
  }
  
  @Pure
  protected ArrayList<RoadSegment> computePerceptionsFor(final Car body) {
    return new ArrayList<RoadSegment>();
  }
  
  @Pure
  protected void consumeInfluence(final Car car, final InfluenceAgent agent) {
  }
  
  protected void loadPotentialPanelPositions() {
    Collection<? extends RoadSegment> allSegments = this.roadNetwork.getRoadSegments();
    for (final RoadSegment segment : allSegments) {
      {
        RoadConnection firstPoint = segment.getBeginPoint();
        if ((((!this.impasses.contains(firstPoint)) && (!this.crois2.contains(firstPoint))) && (!this.crois3.contains(firstPoint)))) {
          boolean _isFinalConnectionPoint = firstPoint.isFinalConnectionPoint();
          if (_isFinalConnectionPoint) {
            this.impasses.add(firstPoint);
          } else {
            int _connectedSegmentCount = firstPoint.getConnectedSegmentCount();
            boolean _equals = (_connectedSegmentCount == 3);
            if (_equals) {
              this.crois2.add(firstPoint);
            } else {
              int _connectedSegmentCount_1 = firstPoint.getConnectedSegmentCount();
              boolean _greaterThan = (_connectedSegmentCount_1 > 3);
              if (_greaterThan) {
                this.crois3.add(firstPoint);
              }
            }
          }
        }
        RoadConnection lastPoint = segment.getEndPoint();
        if ((((!this.impasses.contains(firstPoint)) && (!this.crois2.contains(firstPoint))) && (!this.crois3.contains(firstPoint)))) {
          boolean _isFinalConnectionPoint_1 = lastPoint.isFinalConnectionPoint();
          if (_isFinalConnectionPoint_1) {
            this.impasses.add(lastPoint);
          } else {
            int _connectedSegmentCount_2 = lastPoint.getConnectedSegmentCount();
            boolean _equals_1 = (_connectedSegmentCount_2 == 3);
            if (_equals_1) {
              this.crois2.add(lastPoint);
            } else {
              int _connectedSegmentCount_3 = lastPoint.getConnectedSegmentCount();
              boolean _greaterThan_1 = (_connectedSegmentCount_3 > 3);
              if (_greaterThan_1) {
                this.crois3.add(lastPoint);
              }
            }
          }
        }
      }
    }
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $0$getSkill(DefaultContextInteractions.class)) : $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || $0$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING = $0$getSkill(Logging.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @Extension
  @ImportedCapacityFeature(Behaviors.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Behaviors.class, ($0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || $0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $0$getSkill(Behaviors.class)) : $0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS)", imported = Behaviors.class)
  private Behaviors $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class);
    }
    return $castSkill(Behaviors.class, this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$TargetReached(final TargetReached occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$TargetReached$4(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SendedInfluence(final SendedInfluence occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SendedInfluence$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$GPSPath(final GPSPath occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$GPSPath$5(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$BeginLoop(final BeginLoop occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$BeginLoop$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$EndLoop(final EndLoop occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$EndLoop$2(occurrence));
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
  
  @SyntheticMember
  public EnvironnementAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public EnvironnementAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public EnvironnementAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
