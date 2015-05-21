package fr.cesi.graphic.fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import fr.cesi.application.CacheConfig;
import fr.cesi.graphic.animation.AbstractMoveAnimation;
import fr.cesi.graphic.animation.MoveController.MoveKey;
import fr.cesi.graphic.animation.Sprite;
import fr.cesi.graphic.bean.MovableImageTile;

public class GameAreaController extends AbstractController {
	
	@FXML
	private MovableImageTile frog;
	@FXML
	private MovableImageTile frogClone;
	private Double[] beginPosition;
	
	private AbstractMoveAnimation animation;
	private Sprite spriteAnim;
	private boolean jumpExecuted = false;

	private double timer;
	
	private CacheConfig cacheConfig = CacheConfig.getInstance();
	
	public GameAreaController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		frog.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.BLACK.deriveColor(0, 0, 0, 0.5), 5, 0, 5, 5));
		
		super.initialize(location, resources);
		animation = frog.getAnimationMoveProperty().getAnimation();
		animation.setTile(frog);
		
		beginPosition = new Double[] {frog.getBoundsInParent().getMinX(), frog.getBoundsInParent().getMinY()};
		
		timer = animation.cycleDurationProperty().get().toMillis();
		
		int columns = 7;
		int rows = 4;
		double width = frog.getImage().getWidth() / columns;
		double height = frog.getImage().getHeight() / rows;
		Duration duration = Duration.millis(300);
		spriteAnim = new Sprite(frog, width, height, columns, duration);
	
		for(int i = 0; i < cacheConfig.getLifesNumber(); i++) {
			ImageView life = (ImageView) getNode("life" + i);
			
			int columnsFly = 3;
			int rowsFly = 1;
			double wFly = life.getImage().getWidth() / columnsFly;
			double hFly = life.getImage().getHeight() / rowsFly;
			Sprite spriteFly = new Sprite(life, wFly, hFly, columnsFly, Duration.millis(100));
			spriteFly.setCycleCount(Timeline.INDEFINITE);
			spriteFly.play();
		}
		
	}
	
	@FXML
	protected void move(KeyEvent event) {
		if(event.getEventType() == KeyEvent.KEY_PRESSED) {
			
			KeyCode code = event.getCode();
			if(MoveKey.containsKey(code)) {
				double lastTyped = System.currentTimeMillis();

				if(!jumpExecuted && lastTyped >= timer) {
					jumpExecuted = true;

					moveController.setMove(code);

					spriteAnim.animate(code, 1);
				}
			}
		}
		if(event.getEventType() == KeyEvent.KEY_RELEASED && MoveKey.containsKey(event.getCode()) && jumpExecuted) {
			jumpExecuted = false;
		}
	}

	@FXML
	private ProgressBar timerBar;
	public void updateTimerBar(float timer) {
		timerBar.setProgress(timer);
	}
	
	public void resetFrogPosition() {
//		System.out.printf("x:%1s, y:%2s", beginPosition[0], beginPosition[1]).println();
//		System.out.printf("tx:%1s, ty:%2s", frog.getTranslateX(), frog.getTranslateY()).println();
		
		double nX = frog.getTranslateX();
		double nY = frog.getTranslateY();
		
		frog.setLayoutX(beginPosition[0] - nX);
		frog.setLayoutY(beginPosition[1] - nY);
	}
	
}
