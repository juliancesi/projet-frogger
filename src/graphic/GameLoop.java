package graphic;

import graphic.animation.AbstractMoveAnimation;
import graphic.animation.MoveController;
import graphic.bean.IAnimationMoveProperty;
import graphic.fxmlcontroller.AbstractController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class GameLoop {

	private Timeline gameLoop;
	private MoveController moveController;
	private AbstractController controller;
	private IAnimationMoveProperty frog;
	private AbstractMoveAnimation frogAnimation;
	
	public GameLoop(AbstractController controller) {
		this.controller = controller;
		this.frog = (IAnimationMoveProperty) controller.getNode("frog");
		frogAnimation = frog.getAnimationMoveProperty().getAnimation();
		frogAnimation.setTile((Node) frog); 
		
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
		if(moveController.isInMove()) {
			frogAnimation.setDirection(moveController.getDirection());
			frogAnimation.play();
		}
		
		moveController.reset();
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
