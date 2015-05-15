package graphic;

import graphic.fxmlcontroller.AbstractController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public enum FxmlOverview {
	IHM("/fxml/MenuOverview.fxml"),
	GAME("/fxml/GameHexaTile.fxml"),
	SCORE("/fxml/ScoreOverview.fxml"),
	;
	
	private String fxmlFile;
	private static FXMLLoader fxmlloader;
	private static Pane lastLayoutLoaded;
	
	FxmlOverview(String fxmlFile) {
		this.fxmlFile = fxmlFile;
	}
	
	public String getOverview() {
		return fxmlFile;
	}
	
	public static Pane loadFxmlView(FxmlOverview view) throws IOException {
		lastLayoutLoaded = null;

		URL fxmlUrl = Paths.get(new File("").getAbsolutePath() + view.getOverview()).toUri().toURL();
		InputStream fxmlStream = Files.newInputStream(Paths.get(new File("").getAbsolutePath() + view.getOverview()));
		
		try {
			fxmlloader = new FXMLLoader();
			fxmlloader.setLocation(fxmlUrl);
			lastLayoutLoaded = fxmlloader.load(fxmlStream);
		} catch(IOException ioE) {
			System.err.printf("cannot load the fxml view", ioE);
		}
		return lastLayoutLoaded;
	}

	public static AbstractController getFxmlController() {
		return fxmlloader.getController();
	}
}
