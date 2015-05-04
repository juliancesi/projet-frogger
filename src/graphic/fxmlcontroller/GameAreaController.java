package graphic.fxmlcontroller;

import graphic.animation.AbstractMoveAnimation;
import graphic.animation.MoveController.MoveKey;
import graphic.animation.Sprite;
import graphic.bean.ImageTile;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class GameAreaController extends AbstractController {
	
	@FXML
	private ImageTile frog;
	@FXML
	private ImageView lifes;
	
	private AbstractMoveAnimation animation;
	private Sprite spriteAnim;
	private boolean jumpExecuted = false;

	private double timer;
	
	public GameAreaController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		frog.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.BLACK.deriveColor(0, 0, 0, 0.5), 5, 0, 5, 5));
		
		super.initialize(location, resources);
		animation = frog.getAnimationMoveProperty().getAnimation();
		animation.setTile(frog);
		timer = animation.cycleDurationProperty().get().toMillis();
		
		int columns = 7;
		int rows = 4;
		double width = frog.getImage().getWidth() / columns;
		double height = frog.getImage().getHeight() / rows;
		Duration duration = Duration.millis(300);
		spriteAnim = new Sprite(frog, width, height, columns, duration);
	
		int columnsFly = 3;
		int rowsFly = 1;
		double wFly = lifes.getImage().getWidth() / columnsFly;
		double hFly = lifes.getImage().getHeight() / rowsFly;
		Sprite spriteFly = new Sprite(lifes, wFly, hFly, columnsFly, Duration.millis(100));
		spriteFly.setCycleCount(Timeline.INDEFINITE);
		spriteFly.play();
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
		timerBar.setProgress(timer / 60);
	}
	
}
