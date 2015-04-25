package graphic;

import graphic.animation.MoveController;
import graphic.fxmlcontroller.AbstractController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoop {

	private Timeline gameLoop;
	private MoveController moveController;
	
	public GameLoop(Class<? extends AbstractController> gameAreaController) {
		
		buildGameLoop();
	}
	
	private void buildGameLoop() {
		Duration loopSpeed = Duration.millis(1000 / (float) 60);
		KeyFrame loopFrame = new KeyFrame(loopSpeed, actionEvent -> {
			// instructions called for each iteration
			// checks collisions
			
			// checks user event
			
			// move the player
			movePlayer();			
		});
		
		Timeline gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE); // while true
		gameLoop.getKeyFrames().add(loopFrame);
		
		setGameLoop(gameLoop);
	}
	
	private void movePlayer() {
		if(moveController == null) {
			moveController = MoveController.getInstance();
		}
	}

	private void setGameLoop(Timeline gameLoop) {
		this.gameLoop = gameLoop;
	}
	
	public Timeline getGameLoop() {
		return gameLoop;
	}
	
	public void startGame() {
		getGameLoop().playFromStart();
	}
	
	public void stopGame() {
		getGameLoop().stop();
	}
}
