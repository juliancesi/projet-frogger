package collisions;

import graphic.animation.MoveAnimation;
import graphic.bean.IAnimationMoveProperty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import util.Utils;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CollisionsEngine implements INodesMediator {

	private Node playerNode;
	private Map<String, Node> staticNodesList = new HashMap<String, Node>();
	private Map<String, Node> collidableNodesList = new HashMap<String, Node>();
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
		for(String nodeId : nodesList.keySet()) {
			if(!nodeId.equals(playerNode.getId())) {
				if(nodesList.get(nodeId) instanceof ICollidable) {
					collidableNodesList.put(nodeId, nodesList.get(nodeId));
				}
				else {
					staticNodesList.put(nodeId, nodesList.get(nodeId));
				}
			}
		}
	}

	private boolean rectanglesIntersect(Rectangle r1, Rectangle r2) {
		return r1.getBoundsInParent().intersects(r2.getBoundsInParent());
	}

	private boolean circlesIntersect(Circle c1, Circle c2) {
		double authorizedLength = (c1.getRadius() + c2.getRadius()) * (c1.getRadius() + c2.getRadius());
		
		double realLength = pointToPointPowLength(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());

		return compareLength(authorizedLength, realLength);
	}
	
	private boolean compareLength(double l1, double l2) {
		int res = Double.compare(l1, l2);

		if(res > 0) {
			return true;
		}
		return false;
	}
	
	private boolean pointOnRectSegments(double Cx, double Cy, double Ax, double Ay, double Bx, double By) {
		double ACx = Cx - Ax; 
		double ACy = Cy - Ay; 
		double ABx = Bx - Ax; 
		double ABy = By - Ay; 
		double BCx = Cx - Bx; 
		double BCy = Cy - By; 
		
		double s1 = (ACx * ABx) + (ACy * ABy); 
		double s2 = (BCx * ABx) + (BCy * ABy);
		
		if (s1 * s2 > 0) { 
			return false; 
		}
		return true; 
	}
	
	private double pointToPointPowLength(double x1, double y1, double x2, double y2) {
		double X = (x1 - x2) * (x1 - x2);
		double Y = (y1 - y2) * (y1 - y2);

		return X + Y;
	}
	
	private boolean circlePointIntersect(double x, double y, Circle c) {
		double authorizedLength = c.getRadius();
		double realLength = pointToPointPowLength(x, y, c.getCenterX(), c.getCenterY());
		
		return compareLength(authorizedLength, realLength);
	}
	
	private boolean pointRectangleIntersect(double x, double y, Rectangle r) {
		if(x >= r.getBoundsInParent().getMinX() && 
				x < r.getBoundsInParent().getMinX() + r.getBoundsInParent().getWidth() && 
				y >= r.getBoundsInParent().getMinY() && 
				y < r.getBoundsInParent().getMinY() + r.getBoundsInParent().getHeight()) {
			return true;
		}
		return false;
	}
	
	private boolean circleRectangleIntersect(Circle c, Rectangle r) {
		double xRect = r.getBoundsInParent().getMinX();
		double yRect = r.getBoundsInParent().getMinY();
		double wRect = r.getBoundsInParent().getWidth();
		double hRect = r.getBoundsInParent().getHeight();
		
		double xCirc = c.getCenterX();
		double yCirc = c.getCenterY();
		
		if(circlePointIntersect(xRect, yRect, c) || 
				circlePointIntersect(xRect + wRect, yRect, c) || 
				circlePointIntersect(xRect, yRect + hRect, c) || 
				circlePointIntersect(xRect + wRect, yRect + hRect, c)) {
			return true;
		}
		
		if(pointRectangleIntersect(xCirc, yCirc, r)) {
			return true;
		}

		boolean pointOnXSeg = pointOnRectSegments(xCirc, yCirc, xRect, yRect, xRect + wRect, yRect);
		boolean pointOnYSeg = pointOnRectSegments(xCirc, yCirc, xRect, yRect, xRect, yRect + hRect);
		 
		if (pointOnXSeg || pointOnYSeg) { 
			return true;
		}
		return false;
	}
	
	private boolean intersect(Node player, Node node, int tolerance) {
		Rectangle rectPlayer = Utils.nodeToShape(player, Rectangle.class);
		Circle circPlayer = Utils.nodeToShape(player, Circle.class);
		circPlayer.setRadius(circPlayer.getRadius() - (tolerance * (circPlayer.getRadius() / 100)));
		
		Rectangle rectNode = Utils.nodeToShape(node, Rectangle.class);
		Circle circNode = Utils.nodeToShape(node, Circle.class);
		circNode.setRadius(circNode.getRadius() - (tolerance * (circNode.getRadius() / 100)));
		
		if(!rectanglesIntersect(rectPlayer, rectNode)) {
			return false;
		}
		
		// Camion / Voiture
		if(node instanceof ICollidable) {
			return circleRectangleIntersect(circPlayer, rectNode);
		}

		return circlesIntersect(circPlayer, circNode);
	}

	public Node checkNodeCollisions(Node player) {
		for(String key : staticNodesList.keySet()) {
			Node node;
			if((node = checkCollisions(player, staticNodesList.get(key))) != null) {
				return node;
			}
		}
		return null;
	}

	public Node checkNodeCollisions() {
		return checkNodeCollisions(playerNode);
	}

	public Node checkCollisionsFuture(Double[] xy) {
		Node rectangleFuture = Utils.nodeToShape(playerNode, Rectangle.class);
		rectangleFuture.setId("frogFuture");
		double x = playerNode.getLayoutX() + playerNode.getTranslateX() + xy[0];
		double y = playerNode.getLayoutY() + playerNode.getTranslateY() + xy[1];
		rectangleFuture.relocate(x, y);

		return checkNodeCollisions(rectangleFuture);
	}

	//////////////////////////////////////////////////////////

	private Set<ICollidable> collidableNodes = new HashSet<ICollidable>();
	@Override
	public void addNode(Node node) {
		collidableNodes.add((ICollidable) node);
	}

	@Override
	public void sendNewRiskyNode(Node collidableNode) {
		for(ICollidable node : collidableNodes) {
			if(!((Node) node).getId().equals(collidableNode.getId())) {
				node.receiveRiskNode(collidableNode);
			}
		}
	}

	public Node checkCollisions(Node player, Node node) {
		if(intersect(player, node, 20)) {
			return node;
		}
		return null;
	}
	
	
}
