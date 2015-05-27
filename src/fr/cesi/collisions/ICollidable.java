package fr.cesi.collisions;

import javafx.scene.Node;


// TODO: Auto-generated Javadoc
/**
 * The Interface ICollidable.
 */
public interface ICollidable {

	/**
	 * Sets the collisions engine.
	 *
	 * @param colEngine the new collisions engine
	 */
	public void setCollisionsEngine(CollisionsEngine colEngine);

	/**
	 * Gets the collisions engine.
	 *
	 * @return the collisions engine
	 */
	public CollisionsEngine getCollisionsEngine();
	
	/**
	 * Send new risky node.
	 */
	public void sendNewRiskyNode();
	
	/**
	 * Receive risk node.
	 *
	 * @param collidableNode the collidable node
	 */
	public void receiveRiskNode(Node collidableNode);
	
	/**
	 * Checks if is collided.
	 *
	 * @return true, if is collided
	 */
	public boolean isCollided();
	
	/**
	 * Sets the is collided.
	 */
	public void setIsCollided();
}
