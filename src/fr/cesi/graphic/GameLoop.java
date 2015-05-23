/*
 * 
 */
package fr.cesi.graphic;

import java.io.IOException;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import fr.cesi.application.SceneLoader;
import fr.cesi.collisions.CollisionsEngine;
import fr.cesi.collisions.ICollidable;
import fr.cesi.graphic.animation.AbstractMoveAnimation;
import fr.cesi.graphic.animation.AnimationController;
import fr.cesi.graphic.animation.MoveAnimation;
import fr.cesi.graphic.animation.MoveController;
import fr.cesi.graphic.bean.HighScore;
import fr.cesi.graphic.bean.IAnimationMoveProperty;
import fr.cesi.graphic.bean.ICollisionsProperty;
import fr.cesi.graphic.bean.MovableImageTile;
import fr.cesi.graphic.fxmlcontroller.AbstractController;
import fr.cesi.graphic.fxmlcontroller.GameAreaController;
import fr.cesi.rules.RulesKeeper;
import fr.cesi.rules.RulesKeeper.ScoreType;
import fr.cesi.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class GameLoop.
 */
public class GameLoop {

	/** The game loop. */
	private Timeline gameLoop;
	
	/** The move controller. */
	private MoveController moveController;
	
	/** The controller. */
	private AbstractController controller;
	
	/** The frog. */
	private Node frog;
	
	/** The frog animation. */
	private AbstractMoveAnimation frogAnimation;
	
	/** The collisions engine. */
	private CollisionsEngine collisionsEngine;
	
	/** The animation controller. */
	private AnimationController animationController;
	
	/** The rules keeper. */
	private RulesKeeper rulesKeeper = RulesKeeper.getInstance();
	
	/** The media. */
	private MediaPlayer media;
	
	/**
	 * Instantiates a new game loop.
	 */
	public GameLoop() {
	}
	
	/**
	 * Instantiates a new game loop.
	 *
	 * @param controller the controller
	 */
	public GameLoop(AbstractController controller) {
		setController(controller);
	}
	
	/**
	 * Initialize.
	 */
	public void initialize() {
		this.frog = (Node) controller.getNode("frog");
		frogAnimation = ((IAnimationMoveProperty) frog).getAnimationMoveProperty().getAnimation();
		frogAnimation.setTile((Node) frog); 

		collisionsEngine = new CollisionsEngine((Node) frog, controller.getAllNodes());

		buildGameLoop();
		
		animationController = new AnimationController();
	
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
		
		media = new MediaPlayer(new Media(Utils.loadResource("resources/sounds/coaa.mp3").toString()));
		
		System.out.printf("Difficulté: %1$s", rulesKeeper.getMod()).println();
		System.out.printf("Nombre de vies restantes: %1$s", rulesKeeper.getLifes()).println();
		System.out.printf("Vitesse: %1$s", rulesKeeper.getSpeed()).println();
	}
	
	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(AbstractController controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Builds the game loop.
	 */
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

	/**
	 * Check game rules.
	 */
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

	/**
	 * Update bar.
	 */
	private void updateBar() {
		long timeInRest = rulesKeeper.getTimeToEnd();
		long timeSinceStart = rulesKeeper.getRound() - timeInRest;
		float pct = 100 - (timeSinceStart * 100 / rulesKeeper.getRound());
		
		((GameAreaController) controller).updateTimerBar(pct / 100);
	}

	/**
	 * Move objects.
	 */
	private void moveObjects() {
		animationController.playAnimation();
	}

	/**
	 * Move player.
	 */
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
					media.stop();
					media.play();
					frogAnimation.playAnimation();
					((ICollidable) frog).sendNewRiskyNode();
					getCrossingLines();
				} 
				
				if(collisionsType == 1 && collisionsMovableNodesType == null) {
					media.stop();
					media.play();
					frogAnimation.playAnimation();
					roundFinished();
				} 

				if(collisionsType == 6) {
					((GameAreaController) controller).lockHome(nextNode);
					rulesKeeper.updateScore(ScoreType.ROUND);
					roundFinished();
				} 
				
			}

		}

		moveController.reset();
	}
	
	/** The highest y. */
	private Double highestY;
	
	/** The crossing lines. */
	private int crossingLines = 0;
	
	/**
	 * Gets the crossing lines.
	 *
	 * @return the crossing lines
	 */
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

	/** The next node. */
	private Node nextNode;
	
	/**
	 * Check collisions on move.
	 *
	 * @param xy the xy
	 * @param movableNodes the movable nodes
	 * @return the integer
	 */
	private Integer checkCollisionsOnMove(Double[] xy, boolean movableNodes) {
		Node collidedNode = null;
		if((collidedNode  = collisionsEngine.checkCollisionsFuture(xy, movableNodes)) != null) {
			int collisionProperty = ((ICollisionsProperty) collidedNode).getCollisionsProperty();
//			return Utils.binaryOperationAND(((ICollisionsProperty) frog).getCollisionsProperty(), collisionProperty);
			if(collidedNode != null) nextNode = collidedNode;
			return collisionProperty;
		}
		return null;
	}

	/**
	 * Sets the game loop.
	 *
	 * @param gameLoop the new game loop
	 */
	private void setGameLoop(Timeline gameLoop) {
		this.gameLoop = gameLoop;
	}

	/**
	 * Gets the game loop.
	 *
	 * @return the game loop
	 */
	public Timeline getGameLoop() {
		return gameLoop;
	}

	/**
	 * Start game.
	 */
	public void startGame() {
		getGameLoop().playFromStart();
		rulesKeeper.startTimer();
	}

	/**
	 * Stop game.
	 */
	public void stopGame() {
		getGameLoop().stop();
	}

	/** The g engine. */
	private SceneLoader gEngine = SceneLoader.getInstance();
	
	/**
	 * Round finished.
	 */
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
				HighScore highScore = Utils.deserialize(HighScore.class, "highscore");
				
				if(highScore == null || (highScore != null && highScore.getScore() < rulesKeeper.getScore())) {
					gEngine.loadScore(new HighScore("", rulesKeeper.getScore()), true);
				}
				else {
					gEngine.loadScore(highScore, false);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
