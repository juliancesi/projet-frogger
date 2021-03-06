package fr.cesi.application;

import static fr.cesi.graphic.FxmlOverview.FxmlView.*;
import static fr.cesi.graphic.FxmlOverview.getFxmlController;
import static fr.cesi.graphic.FxmlOverview.loadFxmlView;
import fr.cesi.graphic.GameLoop;
import fr.cesi.graphic.bean.HighScore;
import fr.cesi.graphic.fxmlcontroller.MenuController;
import fr.cesi.graphic.fxmlcontroller.SettingsController;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


// TODO: Auto-generated Javadoc
/**
 * The class to load and display scenes in the window.
 */
public class SceneLoader {

	/** The primary stage. */
	private Stage primaryStage;
	
	/** The game loop. */
	private GameLoop gameLoop;
	
	/** The instance. */
	private static SceneLoader instance;

	/**
	 * Gets the single instance of SceneLoader.
	 *
	 * @return single instance of SceneLoader
	 */
	public static SceneLoader getInstance() {
		return instance;
	}

	/**
	 * Instantiates and loads a scene in the window primaryStage .
	 *
	 * @param primaryStage the window
	 * @throws Exception the exception
	 */
	public SceneLoader(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Projet Frogger");
		primaryStage.setResizable(false);

		if(instance == null) {
			instance = this;
		}

		loadMenu(); // load the menu fxml
	}

	/**
	 * Sets the scene.
	 *
	 * @param newLayout the new scene
	 */
	public void setScene(Pane newLayout) {
		Scene scene = new Scene(newLayout);
		primaryStage.setScene(scene);
		scene.getRoot().requestFocus();
	}

	/**
	 * Load the menu view.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void loadMenu() throws IOException {
		setScene(loadFxmlView(IHM));
	}

	/**
	 * Load the game view.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void loadGame() throws IOException {
		setScene(loadFxmlView(GAME));

		if(gameLoop == null) {
			gameLoop = new GameLoop();
		}
		gameLoop.setController(getFxmlController());
		gameLoop.startGame();
	}

	/**
	 * Load the score view.
	 *
	 * @param score the score
	 * @param newHighScore the new high score
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void loadScore(HighScore score, boolean newHighScore) throws IOException {
		setScene(loadFxmlView(SCORE));
		MenuController controller = ((MenuController) getFxmlController());
		controller.setHighScore(score, newHighScore);
	}

	/**
	 * Load settings.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void loadSettings() throws IOException {
		setScene(loadFxmlView(SETTINGS));
		SettingsController controller = ((SettingsController) getFxmlController());
	}

	/**
	 * Launch et display the window.
	 */
	public void launch() {
		primaryStage.show();
	}
}
