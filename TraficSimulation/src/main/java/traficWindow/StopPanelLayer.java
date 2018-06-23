
package traficWindow;

import java.util.Collection;

import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;


import environnement.StopPanel;



public class StopPanelLayer extends ArrayMapElementLayer<MapElement>
{
	

	public static final int STOP_PANEL_RADIUS = 2;
	
	
	private Collection<StopPanel> stopPanelList;

	
	
	/** Constructor for StopPanelLayer. stopList is the collection which contains the StopPanel which need to be rendered.
	 * 
	 * @param carList
	 */
	public StopPanelLayer(Collection<StopPanel> stopList) {
		super();
		this.stopPanelList = stopList;
	}
	
	
	/**
	 * Update method for the StopPanelLayer. Intended to be used at each frame. 
	 * 
	 * For each StopPanel in stopPanelList, it will add a red circle on the layer.
	 */
	public void update() {
		
		this.removeAllMapElements();
		for(StopPanel c : stopPanelList) {
			
			int colorLight = 0;
			MapCircle mapCircle = new MapCircle(c.getPosition2d(), STOP_PANEL_RADIUS);
			colorLight = 0x004500;
			mapCircle.setColor(colorLight);
			this.addMapElement(mapCircle);
			
		}
		

	}

}