package traficWindow;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.util.InformedArrayList;

public class LightPanelDrawer
{

	public static MapElementLayer<MapCircle> trafficLightLayer;
	Rectangle2d drawArea;
	
	public LightPanelDrawer(MapElementLayer<MapCircle> layer) {
		trafficLightLayer = layer;
    }

	public void drawLight(Point2d position, int color) {
		double x = drawArea.getCenterX();
		double y = drawArea.getCenterY();
		MapCircle light = new MapCircle(x + position.getX(), y + position.getY(), 3);
		light.setColor(color);
		this.trafficLightLayer.addMapElement(light);
	}
	
	public void draw(InformedArrayList<Point2d> light_elements, InformedArrayList<Boolean> light_state, Rectangle2d area) {
		drawArea = area;
		for (int i = 0; i < light_elements.size(); ++i)
		{
			if(light_state.get(i) == true)
			{
				drawLight(light_elements.get(i), 0xff0000);
			}
			else
			{
				drawLight(light_elements.get(i), 0x00ff00);
			}
		}
	}
}