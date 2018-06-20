
package traficWindow;

import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;


import environnement.Car;
import environnement.LightPanel;
import environnement.SpeedPanel;
import javafx.scene.paint.Color;


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
			colorLight = 0x4169E1;
			mapCircle.setColor(colorLight);
			this.addMapElement(mapCircle);
			
		}
		

	}

}