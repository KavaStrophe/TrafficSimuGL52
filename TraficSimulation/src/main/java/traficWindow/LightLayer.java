
package traficWindow;

import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;



import environnement.LightPanel;


/**LightLayer, used to display the light panels.
 * 
 * @author Nahil
 *
 */
public class LightLayer extends ArrayMapElementLayer<MapElement>
{
	

	public static final int LIGHT_RADIUS = 2;
	
	
	private Collection<LightPanel> lightList;

	
	
	/** Constructor for LightLayer. LightList is the collection which contains the LightPanel which need to be rendered.
	 * 
	 * @param carList
	 */
	public LightLayer(Collection<LightPanel> lightList) {
		super();
		this.lightList = lightList;
	}
	
	/**
	 * Update method for the LightLayer. Intended to be used at each frame. 
	 * 
	 * For each LightPanel in lightList, it will add a red or green circle on the layer, depending on the state of the light.
	 */
	public void update() {
		
		this.removeAllMapElements();
		for(LightPanel c : lightList) {
			
			int colorLight = 0;
			MapCircle mapCircle = new MapCircle(c.getPosition2d(), LIGHT_RADIUS);
			if(!c.getState())
				colorLight = 0xff0000;
			else 
				colorLight = 0x00ff00;
			mapCircle.setColor(colorLight);
			this.addMapElement(mapCircle);
			
		}
		

	}

}