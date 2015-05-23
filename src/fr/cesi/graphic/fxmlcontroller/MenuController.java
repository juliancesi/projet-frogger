package fr.cesi.graphic.fxmlcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import fr.cesi.application.SceneLoader;
import fr.cesi.graphic.animation.Sprite;
import fr.cesi.graphic.bean.HighScore;
import fr.cesi.rules.RulesKeeper;
import fr.cesi.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuController.
 */
public class MenuController extends AbstractController {

	/**
	 * Instantiates a new menu controller.
	 */
	public MenuController() {
	}
	
	/** The frog. */
	@FXML
	private ImageView frog;
	
	/** The quitter. */
	@FXML
	private Text quitter;
	
	/** The pseudo. */
	@FXML
	private Text pseudo;
	
	/** The score. */
	@FXML
	private Text score;
	
	/** The new high score. */
	@FXML
	private Pane newHighScore;
	
	/** The new score. */
	@FXML
	private Text newScore;
	
	/** The new pseudo. */
	@FXML
	private TextField newPseudo;


	/* (non-Javadoc)
	 * @see fr.cesi.graphic.fxmlcontroller.AbstractController#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		
		int columns = 7;
		int rows = 4;
		double width = frog.getImage().getWidth() / columns;
		double height = frog.getImage().getHeight() / rows;
		Duration duration = Duration.millis(500);
		Sprite spriteFrog = new Sprite(frog, width, height, columns, duration);
		spriteFrog.setCycleCount(Timeline.INDEFINITE);
		spriteFrog.play();
	}
	
	/**
	 * Play game.
	 */
	@FXML
	protected void playGame() {
		try {
			RulesKeeper.getInstance().initialize();
			SceneLoader.getInstance().loadGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Display scores.
	 */
	@FXML
	protected void displayScores() {
		try {
			HighScore highScore = null;
			highScore = Utils.deserialize(HighScore.class, "highscore");
			
			SceneLoader.getInstance().loadScore(highScore, false);
		} catch(IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
	
	/**
	 * Sets the high score.
	 *
	 * @param score the score
	 * @param newHighScore the new high score
	 */
	public void setHighScore(HighScore score, boolean newHighScore) {
		if(!newHighScore) {
			this.score.setText(String.valueOf(score.getScore()));
			this.pseudo.setText(score.getPseudo());
		}
		if(newHighScore && score != null) {
			this.newHighScore.setVisible(true);
			this.newScore.setText(String.valueOf(score.getScore()));
		}
	}
	
	/**
	 * Save new score.
	 */
	@FXML
	protected void saveNewScore() {
		HighScore highScore = new HighScore(newPseudo.getText(), Long.parseLong(newScore.getText()));
		this.pseudo.setText(highScore.getPseudo());
		this.score.setText(String.valueOf(highScore.getScore()));
		Utils.serialize(highScore, "highscore");
		this.newHighScore.setVisible(false);
	}
	
	/**
	 * Display settings.
	 */
	@FXML
	protected void displaySettings() {
		try {
			SceneLoader.getInstance().loadSettings();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
