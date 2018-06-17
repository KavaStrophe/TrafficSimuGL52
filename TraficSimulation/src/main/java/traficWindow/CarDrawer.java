package traficWindow;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.util.InformedArrayList;

public class CarDrawer
{

	public static MapElementLayer<MapCircle> carLayer;
	Rectangle2d drawArea;
	
	public CarDrawer(MapElementLayer<MapCircle> layer) {
		carLayer = layer;
    }

	public void drawCar(Point2d position) {
		double x = drawArea.getCenterX();
		double y = drawArea.getCenterY();
		MapCircle car = new MapCircle(x + position.getX(), y + position.getY(), 4);
		car.setColor(0x0091c8);
		this.carLayer.addMapElement(car);
	}
	
	public void draw(InformedArrayList<Point2d> car_elements, Rectangle2d area) {
		drawArea = area;
		for (int i = 0; i < car_elements.size(); ++i)
		{
			drawCar(car_elements.get(i));
		}
	}
}