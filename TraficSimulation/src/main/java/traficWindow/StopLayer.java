
package traficWindow;

import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;


import environnement.Car;
import environnement.LightPanel;
import environnement.StopPanel;
import javafx.scene.paint.Color;


public class StopLayer extends ArrayMapElementLayer<MapElement>
{
	

	public static final int STOP_RADIUS = 2;
	
	
	private Collection<StopPanel> stopList;

	
	
	/** Constructor for LightLayer. LightList is the collection which contains the LightPanel which need to be rendered.
	 * 
	 * @param carList
	 */
	public StopLayer(Collection<StopPanel> stopList) {
		super();
		this.stopList = stopList;
		this.update();
	}
	
	
	/**
	 * Update method for the LightLayer. Intended to be used at each frame. 
	 * 
	 * For each LightPanel in lightList, it will add a red or green circle on the layer, depending on the state of the light.
	 */
	public void update() {
		
		this.removeAllMapElements();
		for(StopPanel c : stopList) {
			
			MapCircle mapCircle = new MapCircle(c.getPosition2d(10), STOP_RADIUS);
			mapCircle.setColor(0x0000ff);
			this.addMapElement(mapCircle);
			
		}
		

	}

}