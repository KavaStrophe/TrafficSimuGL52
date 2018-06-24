package utils;

import traficWindow.RoadRenderer;

public class ThreadComputer {
	public static RoadRenderer startRoadRenderer() {
    new Thread() {
        @Override
        public void run() {
            javafx.application.Application.launch(RoadRenderer.class);
        }
    }.start();
    RoadRenderer roadNetworRenderer = RoadRenderer.waitForReturn();
    return roadNetworRenderer;
}
}