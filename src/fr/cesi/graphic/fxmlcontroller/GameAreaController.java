package fr.cesi.graphic.fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import fr.cesi.application.CacheConfig;
import fr.cesi.graphic.animation.AbstractMoveAnimation;
import fr.cesi.graphic.animation.MoveController.MoveKey;
import fr.cesi.graphic.animation.Sprite;
import fr.cesi.graphic.bean.ICollisionsProperty;
import fr.cesi.graphic.bean.MovableImageTile;

// TODO: Auto-generated Javadoc
/**
 * The Class GameAreaController.
 */
public class GameAreaController extends AbstractController {
	
	/** The frog. */
	@FXML
	private MovableImageTile frog;
	
	/** The frog clone. */
	@FXML
	private MovableImageTile frogClone;
	
	/** The begin position. */
	private Double[] beginPosition;
	
	/** The animation. */
	private AbstractMoveAnimation animation;
	
	/** The sprite anim. */
	private Sprite spriteAnim;
	
	/** The jump executed. */
	private boolean jumpExecuted = false;

	/** The timer. */
	private double timer;
	
	/** The cache config. */
	private CacheConfig cacheConfig = CacheConfig.getInstance();
	
	/**
	 * Instantiates a new game area controller.
	 */
	public GameAreaController() {
	}
	
	/* (non-Javadoc)
	 * @see fr.cesi.graphic.fxmlcontroller.AbstractController#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
	
	/**
	 * Move.
	 *
	 * @param event the event
	 */
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

	/** The timer bar. */
	@FXML
	private ProgressBar timerBar;
	
	/**
	 * Update timer bar.
	 *
	 * @param timer the timer
	 */
	public void updateTimerBar(float timer) {
		timerBar.setProgress(timer);
	}
	
	/**
	 * Lock home.
	 *
	 * @param home the home
	 */
	public void lockHome(Node home) {
		((ICollisionsProperty) home).collisionsProperty().set(4);
	}
	
}
