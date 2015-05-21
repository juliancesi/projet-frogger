package fr.cesi.graphic;

import fr.cesi.application.SceneLoader;
import fr.cesi.collisions.CollisionsEngine;
import fr.cesi.collisions.ICollidable;
import fr.cesi.graphic.animation.AbstractMoveAnimation;
import fr.cesi.graphic.animation.AnimationController;
import fr.cesi.graphic.animation.MoveAnimation;
import fr.cesi.graphic.animation.MoveController;
import fr.cesi.graphic.bean.IAnimationMoveProperty;
import fr.cesi.graphic.bean.ICollisionsProperty;
import fr.cesi.graphic.bean.MovableImageTile;
import fr.cesi.graphic.fxmlcontroller.AbstractController;
import fr.cesi.graphic.fxmlcontroller.GameAreaController;
import fr.cesi.rules.RulesKeeper;
import fr.cesi.rules.RulesKeeper.ScoreType;

import java.io.IOException;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class GameLoop {

	private Timeline gameLoop;
	private MoveController moveController;
	private AbstractController controller;
	private Node frog;
	private AbstractMoveAnimation frogAnimation;
	private CollisionsEngine collisionsEngine;
	private AnimationController animationController = AnimationController.getInstance();
	private RulesKeeper rulesKeeper = RulesKeeper.getInstance();

	public GameLoop(AbstractController controller) {
		this.controller = controller;
		this.frog = (Node) controller.getNode("frog");
		frogAnimation = ((IAnimationMoveProperty) frog).getAnimationMoveProperty().getAnimation();
		frogAnimation.setTile((Node) frog); 

		collisionsEngine = CollisionsEngine.getInstance((Node) frog, controller.getAllNodes());

		buildGameLoop();

		animationController.setBounds(controller.getRootPane().getBoundsInLocal().getWidth(), controller.getRootPane().getBoundsInLocal().getHeight());
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
			updateBar();

			// obstacles
			moveObjects();

			// move the player
			movePlayer();

			checkGameRules();
		});

		Timeline gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE); // while true
		gameLoop.getKeyFrames().add(loopFrame);

		setGameLoop(gameLoop);
	}

	private void checkGameRules() {
		Node nodeCollided = null;
		if(((ICollidable) frog).isCollided() && ((MovableImageTile) frog).getNodeCollided() != null) {
			nodeCollided = ((MovableImageTile) frog).getNodeCollided();
			nodeCollided = collisionsEngine.checkCollisions(frog, nodeCollided);
			if(nodeCollided == null) {
				((ICollidable) frog).setIsCollided();
				((MovableImageTile) frog).setNodeCollided(null);
			}
		}

		if(nodeCollided != null) {
			if(((ICollisionsProperty) nodeCollided).getCollisionsProperty() == 3) {
				MoveAnimation moveAnim = ((IAnimationMoveProperty) nodeCollided).animationMoveProperty().get();
				AbstractMoveAnimation anim = moveAnim.getAnimation();
				anim.setTile(frog);
				anim.setCycleCount(Timeline.INDEFINITE);
				anim.playAnimation();
			}
			else {
				roundFinished();
			}
		}

		if(rulesKeeper.getTimeToEnd() <= 0) {
			roundFinished();
		}
	}

	private void updateBar() {
		long timeInRest = rulesKeeper.getTimeToEnd();
		long timeSinceStart = rulesKeeper.getRound() - timeInRest;
		float pct = 100 - (timeSinceStart * 100 / rulesKeeper.getRound());
		
		((GameAreaController) controller).updateTimerBar(pct / 100);
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
			Integer collisionsType = checkCollisionsOnMove(xy, false);
			Integer collisionsMovableNodesType = checkCollisionsOnMove(xy, true);

			if(collisionsType != null) {
				if(collisionsType != 4) {
					frogAnimation.playAnimation();
					((ICollidable) frog).sendNewRiskyNode();
					getCrossingLines();
				} 
				
				if(collisionsType == 1 && collisionsMovableNodesType == null) {
					frogAnimation.playAnimation();
					roundFinished();
				} 

				if(collisionsType == 6) {
					rulesKeeper.updateScore(ScoreType.ROUND);
					roundFinished();
				} 
				
			}

		}

		moveController.reset();
	}
	
	private Double highestY;
	private int crossingLines = 0;
	private int getCrossingLines() {
		double frogY = frog.getBoundsInParent().getMinY();
		if(highestY == null) {
			highestY = frogY;
		}
		
		if(highestY > frogY) {
			highestY = frogY;
			crossingLines++;
			
			rulesKeeper.updateScore(ScoreType.LINE);
		}
		return crossingLines;
	}

	private Integer checkCollisionsOnMove(Double[] xy, boolean movableNodes) {
		Node collidedNode = null;
		if((collidedNode  = collisionsEngine.checkCollisionsFuture(xy, movableNodes)) != null) {
			int collisionProperty = ((ICollisionsProperty) collidedNode).getCollisionsProperty();
//			return Utils.binaryOperationAND(((ICollisionsProperty) frog).getCollisionsProperty(), collisionProperty);
			return collisionProperty;
		}
		return null;
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

	private SceneLoader gEngine = SceneLoader.getInstance();
	public void roundFinished() {
		stopGame();
		if(!rulesKeeper.getGameIsOver()) {
			((MovableImageTile) frog).setIsCollided();
			rulesKeeper.newRound();

			try {
				gEngine.loadGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			stopGame();
			try {
				gEngine.loadScore(rulesKeeper.getScore());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
