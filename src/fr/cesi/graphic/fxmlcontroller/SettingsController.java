package fr.cesi.graphic.fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import fr.cesi.application.CacheConfig;
import fr.cesi.graphic.animation.Sprite;
import fr.cesi.rules.RulesKeeper;
import fr.cesi.rules.RulesKeeper.Difficulty;

// TODO: Auto-generated Javadoc
/**
 * The Class SettingsController.
 */
public class SettingsController extends AbstractController {

	/** The frog. */
	@FXML
	private ImageView frog;
	
	/** The easy. */
	@FXML
	private RadioButton easy;
	
	/** The medium. */
	@FXML
	private RadioButton medium;
	
	/** The hard. */
	@FXML
	private RadioButton hard;
	
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
		
		setRadioButton();
	}
	
	/**
	 * Button checked.
	 *
	 * @param event the event
	 */
	@FXML
	protected void buttonChecked(MouseEvent event) {
		RadioButton source = (RadioButton) event.getSource();
		int diff = 0;
		switch(source.getId()) {
		case "easy":
			diff  = 0;
			break;
		case "medium":
			diff = 1;
			break;
		case "hard":
			diff = 2;
			break;
		}
		
		RulesKeeper.getInstance().changeMod(diff);
	}
	
	/**
	 * Sets the radio button.
	 */
	public void setRadioButton() {
		Difficulty diff = RulesKeeper.getInstance().getMod();
		switch(diff) {
		case EASY:
			easy.setSelected(true);
			break;
		case MEDIUM:
			medium.setSelected(true);
			break;
		case HARD:
			hard.setSelected(true);
			break;
		}
	}
	
}
