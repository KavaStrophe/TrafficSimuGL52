package environnement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.afp.Rectangle2afp;

import environnement.AbstractStaticObject;
import environnement.Car;
import environnement.LightPanel;
import environnement.StopPanel;
import environnement.SpeedPanel;

/**
 * Classe désignant l'environnement de la simulation
 * @author Nulboroth
 *
 */
public class PersonalizedRoadNetwork extends StandardRoadNetwork {
	private ArrayList<RoadConnection> impasses = new ArrayList<> ();
	private ArrayList<RoadConnection> threeRoadConnections = new ArrayList<>(); //Croisement entre 3 routes
	private ArrayList<RoadConnection> fourRoadConnections = new ArrayList<>(); //Croisement entre au moins 4 routes
	private ArrayList<StopPanel> stopPanel = new ArrayList<>();
	private ArrayList<LightPanel> lightPanel = new ArrayList<>();
	private ArrayList<SpeedPanel> speedPanel = new ArrayList<>();
	
	/**
	 * @param originalBounds
	 */
	public PersonalizedRoadNetwork(Rectangle2afp<?, ?, ?, ?, ?, ?> originalBounds) {
		super(originalBounds);
	}

	/**
	 * Liste des RoadConnection n'étant connectées qu'à un seul segment et étant donc des impasses
	 * @return Liste des RoadConnection 
	 */
	public ArrayList<RoadConnection> getImpasses()
	{
		return this.impasses;
	}


	/**
	 * Liste des RoadConnection étant connectées à trois segments
	 * @return Liste des RoadConnection 
	 */
	public ArrayList<RoadConnection> getThreeRoadConnections()
	{
		return this.threeRoadConnections;
	}

	/**
	 * Liste des RoadConnection étant connectées à quatre segments
	 * @return Liste des RoadConnection 
	 */
	public ArrayList<RoadConnection> getFourRoadConnections()
	{
		return this.fourRoadConnections;
	}
	
	/**
	 * Analyse l'environnement afin de pouvor en tirer les connections particulière (1,3 ou 4 segments connectés)
	 */
	public void analizeNetwork()
	{
		Collection<RoadSegment> allSegments = (Collection<RoadSegment>) this.getRoadSegments();
		HashSet<RoadConnection> allRoadConnections = new HashSet<>();
		for (RoadSegment segment : allSegments) {
			allRoadConnections.add( segment.getBeginPoint());
			allRoadConnections.add( segment.getEndPoint());
		}
		for(RoadConnection point : allRoadConnections)
		{
			if (point.getConnectedSegmentCount() == 1) {
				this.impasses.add(point);
			} else if (point.getConnectedSegmentCount() == 3) {
				this.threeRoadConnections.add(point);
			} else if (point.getConnectedSegmentCount() == 4) {
				this.fourRoadConnections.add(point);
			}
		}
	}
	
	/**
	 * Retourne la liste des panneaux stop
	 * @return Liste des panneaux stop
	 */
	public ArrayList<StopPanel> getStopPanel()
	{
		return this.stopPanel; 
	}
	
	/**
	 * Retourne la liste des feux de circualtion
	 * @return Liste des feux de circualtion
	 */
	public ArrayList<LightPanel> getLightPanel()
	{
		return this.lightPanel;
	}
	
	/**
	 * Retourne la liste des panneaux de limite de vitesse
	 * @return Liste des panneaux de limite de vitesse
	 */
	public ArrayList<SpeedPanel> getSpeedPanel()
	{
		return this.speedPanel;
	}
	
	/**
	 * Ajoute un panneau de limite de vitesse à l'endroit indiqué. 
	 * ATTENTION : Pour des raisons graphiques, les panneaux sont ajoutés des deux côtés de la route !
	 * @param segment Segment sur lequel ajouter le panneau
	 * @param speedLimit Limite de vitesse du panneau
	 * @param distance Position sur le segment du panneau 
	 */
	public void addSpeedPanelOnThisRoadSegment(RoadSegment segment, int speedLimit, float distance)
	{
		UUID id = UUID.randomUUID();
		
		SpeedPanel panel1 = new SpeedPanel(id, segment.getBeginPoint(), segment, distance, speedLimit);
		SpeedPanel panel2 = new SpeedPanel(id, segment.getEndPoint(), segment, distance, speedLimit);

		this.speedPanel.add(panel1);
		this.speedPanel.add(panel2);
		addObjectToThisSegment("PANEL", panel1, segment); //$NON-NLS-1$
		addObjectToThisSegment("PANEL", panel2, segment); //$NON-NLS-1$
	}
	
	/**
	 * Ajoute un panneau STOP sur la RoadConnection
	 * @param point Croisement sur lequel ajouter le panneau 
	 */
	public void addStopPanelOnThisRoadConnection(RoadConnection point) {
		int randomNumSegment = (int) Math.round(Math.random() * 2);
		RoadSegment segmentFinal = point.getConnectedSegment(randomNumSegment);
		RoadConnection endPoint;
		float distance = 20;

		if(segmentFinal.getEndPoint() == point)
		{
			endPoint = segmentFinal.getBeginPoint();
			distance = (float) (segmentFinal.getLength() - distance);
		}
		else
		{
			endPoint = segmentFinal.getEndPoint();
		}
		addStopPanel(distance, segmentFinal, endPoint);
	}
	/**
	 * Créée un panneau STOP et le rattache au segment selon son point d'entrée et sa position
	 * 
	 * @param position Position du panneau sur le segment
	 * @param segment Segment sur lequel ajouter le panneau
	 * @param entryPoint Point d'entrée du panneau
	 * @return Panneau Stop
	 */
	public StopPanel addStopPanel(float position, RoadSegment segment, RoadConnection entryPoint)
	{
		UUID id = UUID.randomUUID();
			
		StopPanel panel = new StopPanel(id, entryPoint, segment, position);
		this.stopPanel.add(panel);
		addObjectToThisSegment("PANEL", panel, segment); //$NON-NLS-1$
		return panel;
	}
	/**
	 * Ajoute un feu de signalisation et le rattache au segment selon son point d'entrée et sa position
	 * 
	 * @param position Position du panneau sur le segment
	 * @param segment Segment sur lequel ajouter le panneau
	 * @param entryPoint Point d'entrée du panneau
	 * @param state État de départ du feu (false conseillé)
	 * @return Feu de signalisation
	 */
	public LightPanel addLightPanel(float position, RoadSegment segment, RoadConnection entryPoint, boolean state)
	{
		UUID id = UUID.randomUUID();
		LightPanel panel = new LightPanel(id, entryPoint, segment, position, state);
		this.lightPanel.add(panel);
		addObjectToThisSegment("PANEL", panel, segment); //$NON-NLS-1$
		return panel;
	}
	/**
	 * Ajoute un panneau de limite de vitesse et le rattache au segment selon son point d'entrée et sa position
	 * 
	 * @param position Position du panneau sur le segment
	 * @param segment Segment sur lequel ajouter le panneau
	 * @param entryPoint Point d'entrée du panneau
	 * @param speedLimit Limite de vitesse indiquée
	 * @return Panneau de limite de vitesse
	 */
	public SpeedPanel addSpeedPanel(float position, RoadSegment segment, RoadConnection entryPoint, int speedLimit){
		UUID id = UUID.randomUUID();
		SpeedPanel panel = new SpeedPanel(id, entryPoint, segment, position, speedLimit);
		this.speedPanel.add(panel);
		addObjectToThisSegment("PANEL", panel, segment); //$NON-NLS-1$
		return panel;
	}	
	/**
	 * Ajoute un objet sur le segment sélectionné
	 * 
	 * @param type Type de l'objet (CAR ou PANEL)
	 * @param obj Objet à ajouter
	 * @param segment Segment sur lequel ajouter l'objet
	 */
	public void addObjectToThisSegment(String type, AbstractStaticObject obj, RoadSegment segment){
		segment.addUserData(type, obj);
	}
	/**
	 * Supprime un objet de son segment
	 * 
	 * @param type Type de l'objet (CAR ou PANEL)
	 * @param obj Objet à ajouter
	 */
	public void removeObjectFromHisSegment(String type, AbstractStaticObject obj) {
		obj.getSegment().removeUserData(type, obj);

	}
	/**
	 * Déplace une voiture sur le segment désigné
	 * 
	 * @param car Voiture à bouger
	 * @param segment Segment d'accueil de la voiture
	 */
	public void moveCarToSegment(Car car, RoadSegment segment) {
		removeObjectFromHisSegment("CAR", car); //$NON-NLS-1$
		addObjectToThisSegment("CAR", car, segment); //$NON-NLS-1$
	}
}
