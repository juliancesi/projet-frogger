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
import fr.cesi.util.Utils;

public class MenuController extends AbstractController {

	public MenuController() {
	}
	
	@FXML
	private ImageView frog;
	@FXML
	private Text quitter;
	@FXML
	private Text pseudo;
	@FXML
	private Text score;
	
	@FXML
	private Pane newHighScore;
	@FXML
	private Text newScore;
	@FXML
	private TextField newPseudo;


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
	
	@FXML
	protected void playGame() {
		try {
			SceneLoader.getInstance().loadGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void quitter() {
		System.exit(0);
	}

	@FXML
	protected void menu() {
		try {
			SceneLoader.getInstance().loadMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	
	@FXML
	protected void saveNewScore() {
		HighScore highScore = new HighScore(newPseudo.getText(), Long.parseLong(newScore.getText()));
		this.pseudo.setText(highScore.getPseudo());
		this.score.setText(String.valueOf(highScore.getScore()));
		Utils.serialize(highScore, "highscore");
		this.newHighScore.setVisible(false);
	}
}
