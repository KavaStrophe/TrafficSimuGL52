
package traficWindow;

import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;



import environnement.SpeedPanel;


/**SpeedPanelLayer, used to display the speedPanels.
 * 
 * @author Nahil
 *
 */
public class SpeedPanelLayer extends ArrayMapElementLayer<MapElement>
{
	

	public static final int SPEED_PANEL_RADIUS = 2;
	
	
	private Collection<SpeedPanel> speedPanelList;

	
	
	/** Constructor for SpeedPanelLayer. speedList is the collection which contains the SpeedPanel which need to be rendered.
	 * 
	 * @param carList
	 */
	public SpeedPanelLayer(Collection<SpeedPanel> speedList) {
		super();
		this.speedPanelList = speedList;
	}
	
	
	/**
	 * Update method for the SpeedPanelLayer. Intended to be used at each frame. 
	 * 
	 * For each SpeedPanel in speedPanelList, it will add a red circle on the layer.
	 */
	public void update() {
		
		this.removeAllMapElements();
		for(SpeedPanel c : speedPanelList) {
			
			int colorLight = 0;
			MapCircle mapCircle = new MapCircle(c.getPosition2d(), SPEED_PANEL_RADIUS);
			if(c.getIndicatedSpeed() == 30)
				colorLight = 0xC7F703;
			else if(c.getIndicatedSpeed() == 50)
				colorLight = 0x84CA03;
			else if(c.getIndicatedSpeed() == 70)
				colorLight = 0x03BD28;
			else if(c.getIndicatedSpeed() == 90)
				colorLight = 0x004F10;
			mapCircle.setColor(colorLight);
			this.addMapElement(mapCircle);
			
		}
		

	}

}