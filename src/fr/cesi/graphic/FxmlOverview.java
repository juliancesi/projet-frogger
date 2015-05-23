package fr.cesi.graphic;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import fr.cesi.graphic.fxmlcontroller.AbstractController;
import fr.cesi.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class FxmlOverview.
 */
public class FxmlOverview {
	
	/**
	 * The Enum FxmlView.
	 */
	public enum FxmlView {
		
		/** The ihm fxml. */
		IHM("MenuOverview.fxml"),
		
		/** The game fxml. */
		GAME("GameHexaTile.fxml"),
		
		/** The score fxml. */
		SCORE("ScoreOverview.fxml"),
		
		/** The settings. */
		SETTINGS("SettingsOverview.fxml"),
		;

		/** The fxml file. */
		private String fxmlFile;
		
		/**
		 * Instantiates a new fxml view.
		 *
		 * @param fxmlFile the fxml file
		 */
		FxmlView(String fxmlFile) {
			this.fxmlFile = fxmlFile;
		}

		/**
		 * Gets the view.
		 *
		 * @return the view
		 */
		public String getview() {
			return fxmlFile;
		}

	}
	
	/** The fxmlloader. */
	private static FXMLLoader fxmlloader;
	
	/** The last layout loaded. */
	private static Pane lastLayoutLoaded;
	
	/**
	 * Load a fxml view.
	 *
	 * @param view the view
	 * @return the pane
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Pane loadFxmlView(FxmlView view) throws IOException {
		lastLayoutLoaded = null;

		URL fxmlUrl = Utils.loadResource("resources/fxml/" + view.getview());
		
		try {
			fxmlloader = new FXMLLoader(fxmlUrl);
			fxmlloader.setLocation(fxmlUrl);
			lastLayoutLoaded = fxmlloader.load();
		} catch(IOException ioE) {
			System.err.printf("cannot load the fxml view", ioE);
		}
		return lastLayoutLoaded;
	}

	/**
	 * Gets the fxml controller of the actual view.
	 *
	 * @return the fxml controller
	 */
	public static AbstractController getFxmlController() {
		return fxmlloader.getController();
	}
}
