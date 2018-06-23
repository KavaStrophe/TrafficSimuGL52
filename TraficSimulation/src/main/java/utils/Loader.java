package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.arakhne.afc.gis.io.shape.GISShapeFileReader;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.maplayer.TreeMapElementLayer;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.arakhne.afc.gis.road.primitive.RoadNetworkException;
import org.arakhne.afc.io.dbase.DBaseFileFilter;
import org.arakhne.afc.io.shape.ESRIBounds;
import org.arakhne.afc.io.shape.ShapeElementType;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.vmutil.FileSystem;

import environnement.PersonalizedRoadNetwork;
import traficWindow.RoadRenderer;

/**
 * Classe de chargement du fichier Shapefile
 * @author Nulboroth
 *
 */
public class Loader {
	/**
	 * Charge le fichier shapefile et son fichier de DB associé
	 * @param fileDesc Chemin du fichier
	 * @return RoadNetwork créé à partir du fichier
	 */
	@SuppressWarnings("unused")
	public static PersonalizedRoadNetwork loadShapeFile(String fileDesc) {
		File file;
		
		try {
			file = new File(RoadRenderer.class.getResource(fileDesc).toURI());
		} catch(URISyntaxException e) {
			file = new File(RoadRenderer.class.getResource(fileDesc).getPath());
		} 
		
		try {
			PersonalizedRoadNetwork network = null;
			MapElementLayer<MapElement> layer = null;

			final File dbfFile = FileSystem.replaceExtension(file, DBaseFileFilter.EXTENSION_DBASE_FILE);
			final URL dbfUrl;
			if (dbfFile.canRead()) {
				dbfUrl = dbfFile.toURI().toURL();
			} else {
				dbfUrl = null;
			}

			try (InputStream is = new FileInputStream(file)) {
				assert is != null;
				try (GISShapeFileReader reader = new GISShapeFileReader(is, null, dbfUrl)) {
					final Rectangle2d worldRect = new Rectangle2d();
					final ESRIBounds esriBounds = reader.getBoundsFromHeader();
					worldRect.setFromCorners(
							esriBounds.getMinX(),
							esriBounds.getMinY(),
							esriBounds.getMaxX(),
							esriBounds.getMaxY());

					if (reader.getShapeElementType() == ShapeElementType.POLYLINE) {
						reader.setMapElementType(RoadPolyline.class);
					}

					MapElement element;

					while ((element = reader.read()) != null) {
						if (element instanceof RoadPolyline) {
							if (network == null) {
								network = new PersonalizedRoadNetwork(worldRect);
							}
							final RoadPolyline sgmt = (RoadPolyline) element;
							try {
								network.addRoadSegment(sgmt);
							} catch (RoadNetworkException e) {
								throw new RuntimeException(e);
							}
						} else {
							if (layer == null) {
								layer = new TreeMapElementLayer<>(worldRect);
							}
							try {
								layer.addMapElement(element);
							} catch (RoadNetworkException e) {
								throw new RuntimeException(e);
							}
						}
					}
				}
			}
			if (network != null) {
				return network;
			}
			return network;
		} catch (IOException exception) {
			throw new IOError(exception);
		}
	}
}
