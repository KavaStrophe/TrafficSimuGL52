package utils;

import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadSegment;

public class Road {

   final RoadSegment segment;
   final RoadConnection point;

   public Road(RoadConnection point, RoadSegment segment)
   {
     this.point = point;
     this.segment = segment;
   }

   public boolean equals(Object o)
   {
     if (! (o instanceof Road)) { return false; }
     Road p = (Road)o;
     return segment.equals(p.segment) && point.equals(p.point);
   } 
}
