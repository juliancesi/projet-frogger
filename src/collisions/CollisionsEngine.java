package collisions;

import graphic.animation.AbstractMoveAnimation;
import graphic.animation.MoveAnimation;
import graphic.bean.IAnimationMoveProperty;
import graphic.bean.ICollisionsProperty;

import java.util.Map;

import util.Utils;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CollisionsEngine {

	private Node playerNode;
	private Map<String, Node> nodesList;
	private double rPlayer;

	public static CollisionsEngine instance;
	public static CollisionsEngine getInstance() {
		if(instance == null) {
			instance = new CollisionsEngine();
		}
		return instance;
	}

	public static CollisionsEngine getInstance(Node player, Map<String, Node> nodesList) {
		instance = new CollisionsEngine(player, nodesList);
		return getInstance();
	}

	private CollisionsEngine() {
	}

	public CollisionsEngine(Node player, Map<String, Node> nodesList) {
		this.playerNode = player;
		this.setNodesList(nodesList);
	}

	public void setPlayerNode(Node player) {
		playerNode = player;
	}

	private void setNodesList(Map<String, Node> nodesList) {
		this.nodesList = nodesList;
		this.nodesList.remove(playerNode.getId());
	}

	private boolean intersect(Node player, Node node) {
		return player.getBoundsInParent().intersects(node.getBoundsInParent());
	}

	private boolean circleIntersect(Node player, Node node, int tolerance) {
		// rayon
		rPlayer = ((player.getBoundsInLocal().getWidth() + player.getBoundsInLocal().getHeight()) / 2) / 2; // le vide autour de la grenouille n'est pas compté
		rPlayer -= tolerance * (rPlayer / 100);
		double rNode = ((node.getBoundsInLocal().getWidth() + node.getBoundsInLocal().getHeight()) / 2) / 2;
		rNode -= tolerance * (rNode / 100);
		
		// centre
		double xPlayer = player.getBoundsInParent().getMinX() + rPlayer;
		double yPlayer = player.getBoundsInParent().getMinY() + rPlayer;
		double xNode = node.getBoundsInParent().getMinX() + rNode;
		double yNode = node.getBoundsInParent().getMinY() + rNode;
		
		double authorizedLength = Math.pow(rPlayer + rNode, 2);

		double X = Math.pow(xPlayer - xNode, 2);
		double Y = Math.pow(yPlayer - yNode, 2);
	
		double realLength = X + Y;
		
		// Camion / Voiture
		MoveAnimation animation = ((IAnimationMoveProperty) node).getAnimationMoveProperty();
		if(animation == MoveAnimation.LEFT_TRANSLATION || animation == MoveAnimation.RIGHT_TRANSLATION) {
			return intersect(player, node);
		}
		
		if(realLength < authorizedLength) {
			return true;
		}
		
		return false;
	}
	
	public Node checkNodeCollisions(Node player) {
		for(String key : nodesList.keySet()) {

			if(player != nodesList.get(key)) {
				Node node = nodesList.get(key);

				if(circleIntersect(player, node, 20)) {
					return node;
				}
			}
		}
		return null;
	}

	public Node checkNodeCollisions() {
		return checkNodeCollisions(playerNode);
	}

	public Node checkCollisionsFuture(Double[] xy) {
		Node rectangleFuture = new Rectangle(playerNode.getBoundsInParent().getWidth(), playerNode.getBoundsInParent().getHeight());
		rectangleFuture.setId("frogFuture");
		double x = playerNode.getLayoutX() + playerNode.getTranslateX() + xy[0];
		double y = playerNode.getLayoutY() + playerNode.getTranslateY() + xy[1];
		rectangleFuture.relocate(x, y);
		
		return checkNodeCollisions(rectangleFuture);
	}
}
