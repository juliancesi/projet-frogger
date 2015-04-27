package collisions;

import graphic.bean.ICollisionsProperty;

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
		this.nodesList = nodesList;
	}

	public void setPlayerNode(Node player) {
		playerNode = player;
	}

	public void setNodesList(Map<String, Node> nodesList) {
		this.nodesList = nodesList;
	}
	
	private boolean intersect(Node player, Node node) {
		return player.getBoundsInParent().intersects(node.getBoundsInParent());
	}

	public int checkBounds(Node player) {
		for(String key : nodesList.keySet()) {

			if(player != nodesList.get(key)) {
				Node node = nodesList.get(key);
				
				if(intersect(player, node)) {
					if(node instanceof ICollisionsProperty) {
						int coll = ((ICollisionsProperty) node).getCollisionsProperty();
						
						return coll;
					}
				}
			}
		}
		return 0;
	}
	
	public int checkBounds() {
		return checkBounds(playerNode);
	}
	
	public int checkBoundsFuture(Node player, Double[] future) {
		Node playerFuture = new Rectangle(player.getBoundsInParent().getMaxX(), player.getBoundsInParent().getMaxY() ,player.getBoundsInParent().getWidth(), player.getBoundsInParent().getHeight());
		double x = player.getLayoutX() + future[0];
		double y = player.getLayoutY() + future[1];
		playerFuture.relocate(x, y);
		
		System.out.println("frog: " + player.getBoundsInParent());
		System.out.println("future: " + playerFuture.getBoundsInParent());
		
		return checkBounds(playerFuture);
	}
}
