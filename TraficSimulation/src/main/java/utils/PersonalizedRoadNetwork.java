package utils;

import java.util.ArrayList;
import java.util.Collection;

import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.afp.Rectangle2afp;

import environnement.LightPanel;
import environnement.StopPanel;
import environnement.SpeedPanel;

public class PersonalizedRoadNetwork extends StandardRoadNetwork {
	private ArrayList<RoadConnection> impasses = new ArrayList<RoadConnection> ();
	private ArrayList<RoadConnection> threeRoadConnections = new ArrayList<RoadConnection>(); //Croisement entre 3 routes
	private ArrayList<RoadConnection> fourRoadConnections = new ArrayList<RoadConnection>(); //Croisement entre au moins 4 routes
	private ArrayList<StopPanel> stopPanel = new ArrayList();
	private ArrayList<LightPanel> lightPanel = new ArrayList();
	private ArrayList<SpeedPanel> speedPanel = new ArrayList();
	
	public PersonalizedRoadNetwork(Rectangle2afp<?, ?, ?, ?, ?, ?> originalBounds) {
		super(originalBounds);
	}

	public ArrayList<RoadConnection> getImpasses()
	{
		return this.impasses;
	}

	public ArrayList<RoadConnection> getThreeRoadConnections()
	{
		return this.threeRoadConnections;
	}

	public ArrayList<RoadConnection> getFourRoadConnections()
	{
		return this.fourRoadConnections;
	}
	
	public void analizeNetwork()
	{
		Collection<RoadSegment> allSegments = (Collection<RoadSegment>) this.getRoadSegments();

		for (RoadSegment segment : allSegments) {
			RoadConnection firstPoint = segment.getBeginPoint();
			if (!impasses.contains(firstPoint) && !threeRoadConnections.contains(firstPoint) && !fourRoadConnections.contains(firstPoint)) {
				if (firstPoint.isFinalConnectionPoint()) {
					this.impasses.add(firstPoint);
				} else if (firstPoint.getConnectedSegmentCount() == 3) {
					this.threeRoadConnections.add(firstPoint);
				} else if (firstPoint.getConnectedSegmentCount() == 4) {
					this.fourRoadConnections.add(firstPoint);
				}
			}

			RoadConnection lastPoint = segment.getEndPoint();
			if (!impasses.contains(firstPoint) && !threeRoadConnections.contains(firstPoint) && !fourRoadConnections.contains(firstPoint)) {
				if (lastPoint.isFinalConnectionPoint()) {
					this.impasses.add(lastPoint);
				} else if (lastPoint.getConnectedSegmentCount() == 3) {
					this.threeRoadConnections.add(lastPoint);
				} else if (lastPoint.getConnectedSegmentCount() == 3) {
					this.fourRoadConnections.add(lastPoint);
				}
			}
		}
	}
	
	public ArrayList<StopPanel> getStopPanel()
	{
		return this.stopPanel; 
	}
	public ArrayList<LightPanel> getLightPanel()
	{
		return this.lightPanel;
	}
	public ArrayList<SpeedPanel> getSpeedPanel()
	{
		return this.speedPanel;
	}
}
