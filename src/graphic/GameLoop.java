package graphic;

import graphic.animation.AbstractMoveAnimation;
import graphic.animation.AnimationController;
import graphic.animation.MoveController;
import graphic.bean.IAnimationMoveProperty;
import graphic.bean.ICollisionsProperty;
import graphic.fxmlcontroller.AbstractController;
import graphic.fxmlcontroller.GameAreaController;

import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;
import rules.RulesKeeper;
import util.Utils;
import collisions.CollisionsEngine;
import collisions.ICollidable;

public class GameLoop {

	private Timeline gameLoop;
	private MoveController moveController;
	private AbstractController controller;
	private Node frog;
	private AbstractMoveAnimation frogAnimation;
	private CollisionsEngine collisionsEngine;
	private AnimationController animationController = AnimationController.getInstance();
	private RulesKeeper rulesKeeper = new RulesKeeper(60);
	
	public GameLoop(AbstractController controller) {
		this.controller = controller;
		this.frog = (Node) controller.getNode("frog");
		frogAnimation = ((IAnimationMoveProperty) frog).getAnimationMoveProperty().getAnimation();
		frogAnimation.setTile((Node) frog); 

		collisionsEngine = CollisionsEngine.getInstance((Node) frog, controller.getAllNodes());

		buildGameLoop();
		
		Map<String, Node> nodesList;
		for(String key : (nodesList = controller.getAllNodes()).keySet()) {
			if(nodesList.get(key) instanceof ICollidable) {
				IAnimationMoveProperty node = (IAnimationMoveProperty) nodesList.get(key);
				if(node.getMoveAnimation() != null) {
					animationController.addNode((Node) node);
				}
			}
		}
	}

	private void buildGameLoop() {
		Duration loopSpeed = Duration.millis(1000 / (float) 30);
		KeyFrame loopFrame = new KeyFrame(loopSpeed, actionEvent -> {
			// instructions called for each iteration
			// timer
			rulesKeeper.checkTimer();
			updateBar(rulesKeeper.getTimeToEnd());
			
			// obstacles
			moveObjects();
			
			// checks collisions
			Node collidedNode = collisionsEngine.checkNodeCollisions();
			if(collidedNode != null) {
				if(collidedNode instanceof ICollisionsProperty) {
					int collision = ((ICollisionsProperty) collidedNode).getCollisionsProperty();
					if((Utils.binaryOperationAND(((ICollisionsProperty) frog).getCollisionsProperty(), collision)) == 1) {
						System.out.println("mort !");
					}
				}
			}

			// move the player
			movePlayer();
		});

		Timeline gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE); // while true
		gameLoop.getKeyFrames().add(loopFrame);

		setGameLoop(gameLoop);
	}

	private void updateBar(long timer) {
		((GameAreaController) controller).updateTimerBar(timer);
	}

	private void moveObjects() {
		animationController.playAnimation();
	}

	private void movePlayer() {
		if(moveController == null) {
			moveController = MoveController.getInstance();
		}
		if(moveController.isInMove()) {
			frogAnimation.setDirection(moveController.getDirection());

			Double[] xy = frogAnimation.getCoordinates();

			Node collidedNode = null;
			if((collidedNode  = collisionsEngine.checkCollisionsFuture(xy)) != null) {
				int collisionProperty = ((ICollisionsProperty) collidedNode).getCollisionsProperty();

				int res = Utils.binaryOperationAND(((ICollisionsProperty) frog).getCollisionsProperty(), collisionProperty);
				if(res != 0) {
					frogAnimation.playAnimation();
					((ICollidable) frog).sendNewRiskyNode();
				}
			}

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
		rulesKeeper.startTimer();
	}

	public void stopGame() {
		getGameLoop().stop();
	}
}
