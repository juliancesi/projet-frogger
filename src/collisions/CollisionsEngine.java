package collisions;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class CollisionsEngine {

	private Node playerNode;
	private Map<String, Node> nodesList;

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

	public Node checkNodeCollisions(Node player) {
		for(String key : nodesList.keySet()) {

			if(player != nodesList.get(key)) {
				Node node = nodesList.get(key);

				if(intersect(player, node)) {
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
