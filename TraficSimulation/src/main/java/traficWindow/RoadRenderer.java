/*
 * $Id$
 * This file is a part of the Arakhne Foundation Classes, http://www.arakhne.org/afc
 *
 * Copyright (c) 2000-2012 Stephane GALLAND.
 * Copyright (c) 2005-10, Multiagent Team, Laboratoire Systemes et Transports,
 *                        Universite de Technologie de Belfort-Montbeliard.
 * Copyright (c) 2013-2018 The original authors, and other authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package traficWindow;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import org.arakhne.afc.gis.mapelement.GISElementContainer;
import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.ArrayMapElementLayer;
import org.arakhne.afc.gis.maplayer.GISLayerContainer;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.maplayer.MapLayer;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.arakhne.afc.gis.primitive.FlagContainer;
import org.arakhne.afc.gis.primitive.GISContainer;
import org.arakhne.afc.gis.ui.GisPane;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.text.TextUtil;
import org.arakhne.afc.util.InformedArrayList;
import org.arakhne.afc.vmutil.json.JsonBuffer;
import org.arakhne.afc.vmutil.locale.Locale;

/**
 * Application for viewing GIS primitives.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 14.0
 */
public class RoadRenderer extends Application {

	private volatile boolean dragging;
	private volatile MapElement selectedRoad;
	public static MapElementLayer<?> roadLayer;
	public static MapElementLayer<MapCircle> carLayer = new ArrayMapElementLayer<MapCircle>();
	public static MapElementLayer<MapCircle> panelLayer = new ArrayMapElementLayer<MapCircle>();
	
	public static CarDrawer car_drawer = new CarDrawer(carLayer);
	public static LightPanelDrawer light_panel_drawer = new LightPanelDrawer(panelLayer);
	public static RoadPanelDrawer road_panel_drawer = new RoadPanelDrawer(panelLayer);

	public static final CountDownLatch latch = new CountDownLatch(1);
    public static RoadRenderer renderer = null;
    
    private Stage stage;

	public static RoadRenderer waitForReturn() { 
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return renderer;
    }
	
	public static void setRoadRenderer(RoadRenderer roadRenderer) {
		renderer = roadRenderer;
        latch.countDown();
    }
	
	public RoadRenderer() {
		setRoadRenderer(this);
    }
	
	public static void main() {
        Application.launch();
    }
	
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void start(Stage primaryStage) throws InterruptedException {
		final MultiMapLayer layer = new MultiMapLayer<>();
		layer.addMapLayer(this.roadLayer);
		layer.addMapLayer(this.carLayer);
		layer.addMapLayer(this.panelLayer);

		final GISContainer container;
		container = layer;

		final BorderPane root = new BorderPane();

		final Label messageBar = new Label(""); //$NON-NLS-1$
		messageBar.setTextAlignment(TextAlignment.CENTER);

		final GisPane scrollPane = new GisPane(container);

		final String mouseLocationPattern = Locale.getString(RoadRenderer.class, "MOUSE_POSITION"); //$NON-NLS-1$

		scrollPane.setOnMouseMoved(event -> {
			final Point2d mousePosition = scrollPane.toDocumentPosition(event.getX(), event.getY());
			messageBar.setText(MessageFormat.format(mouseLocationPattern,
					TextUtil.formatDouble(event.getX(), 1),
					TextUtil.formatDouble(event.getY(), 1),
					TextUtil.formatDouble(mousePosition.getX(), 4),
					TextUtil.formatDouble(mousePosition.getY(), 4)));
		});

		scrollPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			this.dragging = true;
			final Point2d mousePosition = scrollPane.toDocumentPosition(event.getX(), event.getY());
			messageBar.setText(MessageFormat.format(mouseLocationPattern,
					TextUtil.formatDouble(event.getX(), 1),
					TextUtil.formatDouble(event.getY(), 1),
					TextUtil.formatDouble(mousePosition.getX(), 4),
					TextUtil.formatDouble(mousePosition.getY(), 4)));
		});

		scrollPane.setOnMouseReleased(event -> {
			if (!this.dragging) {
				final MapElement select1 = this.selectedRoad;
				this.selectedRoad = null;
				if (select1 != null) {
					select1.unsetFlag(FlagContainer.FLAG_SELECTED);
				}
				final MapElement select2 = getElementUnderMouse(scrollPane, event.getX(), event.getY());
				if (select2 != select1) {
					if (select2 != null) {
						select2.setFlag(FlagContainer.FLAG_SELECTED);
						this.selectedRoad = select2;
						if (event.isControlDown()) {
							// Force the loading of all the attributes.
							select2.getAllAttributeNames();
						}
						System.out.println(JsonBuffer.toString(select2));
					}
					scrollPane.drawContent();
					event.consume();
				}
			}
			this.dragging = false;
		});
		
		root.setCenter(scrollPane);
		root.setBottom(messageBar);

		final Scene scene = new Scene(root, 1024, 768);
		scene.getStylesheets().add(getClass().getResource("ressources/application.css").toExternalForm()); //$NON-NLS-1$

		primaryStage.setTitle(Locale.getString(RoadRenderer.class, "Trafic Simulation - Visualisation")); //$NON-NLS-1$
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/** Replies the element at the given mouse position.
	 *
	 * @param pane the element pane.
	 * @param x the x position of the mouse.
	 * @param y the y position of the mouse.
	 * @return the element.
	 * @since 15.0
	 */
	@SuppressWarnings({ "rawtypes" })
	public MapElement getElementUnderMouse(GisPane<?> pane, double x, double y) {
		final GISContainer model = pane.getDocumentModel();
		final Point2d mousePosition = pane.toDocumentPosition(x, y);
		final Rectangle2d selectionArea = pane.toDocumentRect(x - 2, y - 2, 5, 5);
		return getElementUnderMouse(model, mousePosition, selectionArea);
	}

	@SuppressWarnings({ "rawtypes" })
	private MapElement getElementUnderMouse(Object model, Point2d mousePosition, Rectangle2d selectionArea) {
		if (model instanceof GISElementContainer<?>) {
			return getElementUnderMouse((GISElementContainer) model, mousePosition, selectionArea);
		}
		if (model instanceof GISLayerContainer<?>) {
			return getElementUnderMouse((GISLayerContainer) model, mousePosition, selectionArea);
		}
		return null;
	}

	@SuppressWarnings({ "static-method" })
	private MapElement getElementUnderMouse(GISElementContainer<?> model, Point2d mousePosition, Rectangle2d selectionArea) {
		final Iterator<? extends MapElement> iterator = model.iterator(selectionArea);
		double dist = Double.MAX_VALUE;
		MapElement select = null;
		while (iterator.hasNext()) {
			final MapElement road = iterator.next();
			final double distance = Math.abs(road.getDistance(mousePosition));
			if (distance < dist) {
				dist = distance;
				select = road;
			}
		}
		return select;
	}

	private MapElement getElementUnderMouse(GISLayerContainer<?> model, Point2d mousePosition, Rectangle2d selectionArea) {
		final Iterator<? extends MapLayer> iterator = model.iterator();
		while (iterator.hasNext()) {
			final MapLayer layer = iterator.next();
			if (layer.isVisible() && layer.isClickable()) {
				final MapElement selected = getElementUnderMouse(layer, mousePosition, selectionArea);
				if (selected != null) {
					return selected;
				}
			}
		}
		return null;
	}
	
	public void setCars(InformedArrayList<Point2d> car_elements)
	{
		this.carLayer.removeAllMapElements();
		car_drawer.draw(car_elements, this.roadLayer.getMapElementAt(0).getGeoLocation().toBounds2D());
	}
	
	public void setPannels(InformedArrayList<Point2d> sign_elements, InformedArrayList<String> type_pannel, InformedArrayList<Point2d> light_elements, InformedArrayList<Boolean> light_state)
	{
		this.panelLayer.removeAllMapElements();
		road_panel_drawer.draw(sign_elements, type_pannel, this.roadLayer.getMapElementAt(0).getGeoLocation().toBounds2D());
		light_panel_drawer.draw(light_elements, light_state, this.roadLayer.getMapElementAt(0).getGeoLocation().toBounds2D());
	}
}
