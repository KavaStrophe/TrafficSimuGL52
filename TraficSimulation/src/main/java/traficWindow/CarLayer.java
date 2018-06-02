
package traficWindow;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import org.arakhne.afc.attrs.collection.AttributeCollection;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.math.geometry.d2.afp.Rectangle2afp;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;

public class CarLayer extends MapElementLayer
{

	public CarLayer(UUID id, AttributeCollection attributeSource) {
		super(id, attributeSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMapElementCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MapElement getMapElementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection getAllMapElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMapElements(Collection elements) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addMapElement(MapElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMapElement(MapElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllMapElements() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator(Rectangle2afp bounds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Class getElementType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape2d<?> getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Rectangle2d calcBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}