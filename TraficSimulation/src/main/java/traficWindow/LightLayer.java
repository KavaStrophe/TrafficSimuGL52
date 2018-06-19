
package traficWindow;

import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;


import environnement.Car;
import environnement.LightPanel;
import javafx.scene.paint.Color;


public class LightLayer extends ArrayMapElementLayer<MapElement>
{
	

	public static final int LIGHT_RADIUS = 2;
	
	
	
	private Collection<LightPanel> lightList;

	
	
	/** Constructor for CarLayer. carList is the collection which contains the cars which need to be rendered.
	 * 
	 * @param carList
	 */
	public LightLayer(Collection<LightPanel> lightList) {
		super();
		this.lightList = lightList;
	}
	
	
	/**
	 * Update method for the CarLayer. Intended to be used at each frame. 
	 * 
	 * For each Car in CarList, it will add a circle on the layer.
	 */
	public void update() {
		
		this.removeAllMapElements();
		for(LightPanel c : lightList) {
			
			// TODO : check filters
			int colorLight = 0;
			MapCircle mapCircle = new MapCircle(c.getPosition2d(), LIGHT_RADIUS);
			if(c.getState())
				colorLight = 0xFFFF0000;
			else 
				colorLight = 0x00FF0000;
			mapCircle.setColor(colorLight);
			this.addMapElement(mapCircle);
			
		}
		

	}

}