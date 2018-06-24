
package traficWindow;

import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;


import environnement.Car;
import filter.CarFilter;
import filter.FilterConfiguration;
import javafx.scene.paint.Color;

/**CarLayer, used to display the cars.
 * 
 * @author Nahil
 *
 */
public class CarLayer extends ArrayMapElementLayer<MapElement>
{
	

	public static final int CAR_RADIUS = 3;
	
	
	
	private Collection<Car> carList;

	
	
	/** Constructor for CarLayer. carList is the collection which contains the cars which need to be rendered.
	 * 
	 * @param carList
	 */
	public CarLayer(Collection<Car> carList) {
		super();
		this.carList = carList;
	}
	
	
	/**
	 * Update method for the CarLayer. Intended to be used at each frame. 
	 * 
	 * For each Car in CarList, it will add a circle on the layer.
	 */
	public void update() {
		
		this.removeAllMapElements();
		for(Car c : carList) {
			boolean displayed = false;
			for(CarFilter f : FilterConfiguration.getInstance().getFilterList()) {
				if(!displayed && f.getActive()) {
					if(f.getCarModel() == null || c.getModel().getName().equals(f.getCarModel())) {
						if((Math.abs(c.getSpeed())>f.getMinSpeed() || f.getMinSpeed() <= -1)&&(Math.abs(c.getSpeed())<f.getMaxSpeed() || f.getMaxSpeed() <= -1)){
							displayed = true;
							MapCircle mapCircle = new MapCircle(c.getPosition2d(), CAR_RADIUS);
							Color carColor = f.getColor();
							mapCircle.setColor(getIntFromColor(carColor.getRed(), carColor.getGreen(), carColor.getBlue()));
							this.addMapElement(mapCircle);
							
						}
					}
				}
			}
			
			
			if(!displayed && FilterConfiguration.getInstance().getDisplayOtherCars() ) {
				MapCircle mapCircle = new MapCircle(c.getPosition2d(), CAR_RADIUS);
				Color carColor = c.getColor();
				mapCircle.setColor(getIntFromColor(carColor.getRed(), carColor.getGreen(), carColor.getBlue()));
				this.addMapElement(mapCircle);
			}
		}
		

	}
	
	/**Convert Color to an int.
	 * 
	 * @param Red
	 * @param Green
	 * @param Blue
	 * @return
	 */
	private int getIntFromColor(double Red, double Green, double Blue){
	    int R = (int) Math.round(255 * Red);
	    int G = (int) Math.round(255 * Green);
	    int B = (int) Math.round(255 * Blue);

	    R = (R << 16) & 0x00FF0000;
	    G = (G << 8) & 0x0000FF00;
	    B = B & 0x000000FF;

	    return 0xFF000000 | R | G | B;
	}
	

}