package graphic;

import graphic.animation.AbstractMoveAnimation;
import graphic.animation.MoveController;
import graphic.bean.IAnimationMoveProperty;
import graphic.fxmlcontroller.AbstractController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import collisions.CollisionsEngine;

public class GameLoop {

	private Timeline gameLoop;
	private MoveController moveController;
	private AbstractController controller;
	private IAnimationMoveProperty frog;
	private AbstractMoveAnimation frogAnimation;
	private CollisionsEngine collisionsEngine;

	public GameLoop(AbstractController controller) {
		this.controller = controller;
		this.frog = (IAnimationMoveProperty) controller.getNode("frog");
		frogAnimation = frog.getAnimationMoveProperty().getAnimation();
		frogAnimation.setTile((Node) frog); 

		collisionsEngine = CollisionsEngine.getInstance((Node) frog, controller.getAllNodes());

		buildGameLoop();
	}

	private void buildGameLoop() {
		Duration loopSpeed = Duration.millis(1000 / (float) 30);
		KeyFrame loopFrame = new KeyFrame(loopSpeed, actionEvent -> {
			// instructions called for each iteration
			// checks collisions
//			int collision = collisionsEngine.checkBounds();
//			switch(collision) {
//			case 1:
//				System.out.println("bloque");
//				break;
//			case 2:
//				System.out.println("mort");
//				break;
//			}

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
			Double[] future = frogAnimation.setDirection(moveController.getDirection());
			
			int collisionsFuture = collisionsEngine.checkBoundsFuture((Node) frog, future);
			System.out.println(collisionsFuture);
			
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
