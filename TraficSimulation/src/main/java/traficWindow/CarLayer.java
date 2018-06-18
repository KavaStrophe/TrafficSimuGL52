
package traficWindow;

import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;


import environnement.Car;


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
			
			// TODO : check filters
			
			MapCircle mapCircle = new MapCircle(c.getPosition2d(), CAR_RADIUS);
			mapCircle.setColor(Integer.decode(c.getColor().toString()));
			this.addMapElement(mapCircle);
			
		}
		

	}
	

}