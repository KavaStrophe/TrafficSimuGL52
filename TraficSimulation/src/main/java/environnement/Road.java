package environnement;

import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadSegment;

/**
 * Combinaison d'un RoadConnection et d'un RoadSegment
 * @author Nulboroth
 *
 */
public class Road {

   final RoadSegment segment;
   final RoadConnection point;

   /**
	 * @param point RoadConnection à stocker
	 * @param segment RoadSegment à stocker
	 */
public Road(RoadConnection point, RoadSegment segment)
   {
     this.point = point;
     this.segment = segment;
   }

   @Override
public boolean equals(Object o)
   {
     if (! (o instanceof Road)) { return false; }
     Road p = (Road)o;
     return this.segment.equals(p.segment) && this.point.equals(p.point);
   } 
}
