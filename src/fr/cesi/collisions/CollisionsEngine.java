package fr.cesi.collisions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.cesi.util.Utils;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * The Class CollisionsEngine.
 */
public class CollisionsEngine implements INodesMediator {

	/** The player node. */
	private Node playerNode;
	
	/** The static nodes list. */
	private Map<String, Node> staticNodesList = new HashMap<String, Node>();
	
	/** The collidable nodes list. */
	private Map<String, Node> collidableNodesList = new HashMap<String, Node>();
	
	/** The r player. */
	private double rPlayer;

	/** The instance. */
	public static CollisionsEngine instance;
	
	/**
	 * Gets the single instance of CollisionsEngine.
	 *
	 * @return single instance of CollisionsEngine
	 */
	public static CollisionsEngine getInstance() {
		if(instance == null) {
			instance = new CollisionsEngine();
		}
		return instance;
	}

	/**
	 * Gets the single instance of CollisionsEngine.
	 *
	 * @param player the player
	 * @param nodesList the nodes list
	 * @return single instance of CollisionsEngine
	 */
	public static CollisionsEngine getInstance(Node player, Map<String, Node> nodesList) {
		instance = new CollisionsEngine(player, nodesList);
		return getInstance();
	}

	/**
	 * Instantiates a new collisions engine.
	 */
	private CollisionsEngine() {
	}

	/**
	 * Instantiates a new collisions engine.
	 *
	 * @param player the player
	 * @param nodesList the nodes list
	 */
	public CollisionsEngine(Node player, Map<String, Node> nodesList) {
		this.playerNode = player;
		this.setNodesList(nodesList);
	}

	/**
	 * Sets the player node.
	 *
	 * @param player the new player node
	 */
	public void setPlayerNode(Node player) {
		playerNode = player;
	}

	/**
	 * Sets the nodes list.
	 *
	 * @param nodesList the nodes list
	 */
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

	/**
	 * Rectangles intersect.
	 *
	 * @param r1 the r1
	 * @param r2 the r2
	 * @return true, if successful
	 */
	private boolean rectanglesIntersect(Rectangle r1, Rectangle r2) {
		return r1.getBoundsInParent().intersects(r2.getBoundsInParent());
	}

	/**
	 * Circles intersect.
	 *
	 * @param c1 the c1
	 * @param c2 the c2
	 * @return true, if successful
	 */
	private boolean circlesIntersect(Circle c1, Circle c2) {
		double authorizedLength = (c1.getRadius() + c2.getRadius()) * (c1.getRadius() + c2.getRadius());
		
		double realLength = pointToPointPowLength(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());

		return compareLength(authorizedLength, realLength);
	}
	
	/**
	 * Compare length.
	 *
	 * @param l1 the l1
	 * @param l2 the l2
	 * @return true, if successful
	 */
	private boolean compareLength(double l1, double l2) {
		int res = Double.compare(l1, l2);

		if(res > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Point on rect segments.
	 *
	 * @param Cx the cx
	 * @param Cy the cy
	 * @param Ax the ax
	 * @param Ay the ay
	 * @param Bx the bx
	 * @param By the by
	 * @return true, if successful
	 */
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
	
	/**
	 * Point to point pow length.
	 *
	 * @param x1 the x1
	 * @param y1 the y1
	 * @param x2 the x2
	 * @param y2 the y2
	 * @return the double
	 */
	private double pointToPointPowLength(double x1, double y1, double x2, double y2) {
		double X = (x1 - x2) * (x1 - x2);
		double Y = (y1 - y2) * (y1 - y2);

		return X + Y;
	}
	
	/**
	 * Circle point intersect.
	 *
	 * @param x the x
	 * @param y the y
	 * @param c the c
	 * @return true, if successful
	 */
	private boolean circlePointIntersect(double x, double y, Circle c) {
		double authorizedLength = c.getRadius();
		double realLength = pointToPointPowLength(x, y, c.getCenterX(), c.getCenterY());
		
		return compareLength(authorizedLength, realLength);
	}
	
	/**
	 * Point rectangle intersect.
	 *
	 * @param x the x
	 * @param y the y
	 * @param r the r
	 * @return true, if successful
	 */
	private boolean pointRectangleIntersect(double x, double y, Rectangle r) {
		if(x >= r.getBoundsInParent().getMinX() && 
				x < r.getBoundsInParent().getMinX() + r.getBoundsInParent().getWidth() && 
				y >= r.getBoundsInParent().getMinY() && 
				y < r.getBoundsInParent().getMinY() + r.getBoundsInParent().getHeight()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Circle rectangle intersect.
	 *
	 * @param c the c
	 * @param r the r
	 * @return true, if successful
	 */
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
	
	/**
	 * Intersect.
	 *
	 * @param player the player
	 * @param node the node
	 * @param tolerance the tolerance
	 * @return true, if successful
	 */
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
		
		// movable nodes
		if(node instanceof ICollidable) {
			return circleRectangleIntersect(circPlayer, rectNode);
		}

		return circlesIntersect(circPlayer, circNode);
	}

	/**
	 * Check node collisions.
	 *
	 * @param player the player
	 * @param movableNodes the movable nodes
	 * @return the node
	 */
	public Node checkNodeCollisions(Node player, boolean movableNodes) {
		Map<String, Node> lst = null;
		if(movableNodes) {
			lst = collidableNodesList;
		}
		else {
			lst = staticNodesList;
		}
		for(String key : lst.keySet()) {
			Node node;
			if((node = checkCollisions(player, lst.get(key))) != null) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Check node collisions.
	 *
	 * @return the node
	 */
	public Node checkNodeCollisions() {
		return checkNodeCollisions(playerNode, false);
	}

	/**
	 * Check collisions future.
	 *
	 * @param xy the xy
	 * @param movableNodes the movable nodes
	 * @return the node
	 */
	public Node checkCollisionsFuture(Double[] xy, boolean movableNodes) {
		Node rectangleFuture = Utils.nodeToShape(playerNode, Rectangle.class);
		rectangleFuture.setId("frogFuture");
		double x = playerNode.getLayoutX() + playerNode.getTranslateX() + xy[0];
		double y = playerNode.getLayoutY() + playerNode.getTranslateY() + xy[1];
		rectangleFuture.relocate(x, y);

		return checkNodeCollisions(rectangleFuture, movableNodes);
	}

	//////////////////////////////////////////////////////////

	/** The collidable nodes. */
	private Set<ICollidable> collidableNodes = new HashSet<ICollidable>();
	
	/* (non-Javadoc)
	 * @see collisions.INodesMediator#addNode(javafx.scene.Node)
	 */
	@Override
	public void addNode(Node node) {
		collidableNodes.add((ICollidable) node);
	}

	/* (non-Javadoc)
	 * @see collisions.INodesMediator#sendNewRiskyNode(javafx.scene.Node)
	 */
	@Override
	public void sendNewRiskyNode(Node collidableNode) {
		for(ICollidable node : collidableNodes) {
			if(!((Node) node).getId().equals(collidableNode.getId()) && !node.isCollided()) {
				node.receiveRiskNode(collidableNode);
			}
		}
	}

	/**
	 * Check collisions.
	 *
	 * @param player the player
	 * @param node the node
	 * @return the node
	 */
	public Node checkCollisions(Node player, Node node) {
		if(intersect(player, node, 20)) {
			return node;
		}
		return null;
	}

/*	
	private Map<Integer, Map<Integer, Set<Node>>> sortedNodesList = new HashMap<Integer, Map<Integer, Set<Node>>>();
	public void addSortedNode(int minX, int maxX, int minY, int maxY, Node node) {
		// loop on x
		for(int x = minX; x <= maxX; x++) {
			if(sortedNodesList.get(x) == null) {
				sortedNodesList.put(x, new HashMap<Integer, Set<Node>>());
			}
			
			// loop on y
			for(int y  = minY; y <= maxY; y++) {
				if(sortedNodesList.get(x).get(y) == null) {
					sortedNodesList.get(x).put(y, new HashSet<Node>());
				}
				
				sortedNodesList.get(x).get(y).add(node);
			}
		}
	}
	
	public Map<Integer, Map<Integer, Set<Node>>> getSortedNodesList() {
		return sortedNodesList;
	}
	
	public Set<Node> getNodesList(int x, int y) {
		return sortedNodesList.get(x).get(y);
	}*/
	
}
