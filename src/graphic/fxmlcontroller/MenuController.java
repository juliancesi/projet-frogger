package graphic.fxmlcontroller;

import graphic.animation.Sprite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import application.GraphicsEngine;

public class MenuController extends AbstractController {

	public MenuController() {
	}
	
	@FXML
	private ImageView frog;
	
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
			GraphicsEngine.getInstance().loadGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
