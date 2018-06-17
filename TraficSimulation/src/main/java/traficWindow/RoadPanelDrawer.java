package traficWindow;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.util.InformedArrayList;

public class RoadPanelDrawer
{

	public static MapElementLayer<MapCircle> signLayer;
	Rectangle2d drawArea;
	
	public RoadPanelDrawer(MapElementLayer<MapCircle> layer) {
		signLayer = layer;
    }

	public void drawPanel(Point2d position, int color) {
		double x = drawArea.getCenterX();
		double y = drawArea.getCenterY();
		MapCircle sign = new MapCircle(x + position.getX(), y + position.getY(), 3);
		sign.setColor(color);
		this.signLayer.addMapElement(sign);
	}
	
	public void draw(InformedArrayList<Point2d> sign_elements, InformedArrayList<String> type_pannel, Rectangle2d area) {
		drawArea = area;
		for (int i = 0; i < sign_elements.size(); ++i)
		{
			if(type_pannel.get(i) == "speed")
			{
				drawPanel(sign_elements.get(i), 0xf5f200);
			}
			else if(type_pannel.get(i) == "stop")
			{
				drawPanel(sign_elements.get(i), 0x6b0000);
			}
		}
	}
}