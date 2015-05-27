package fr.cesi.collisions;

import javafx.scene.Node;


// TODO: Auto-generated Javadoc
/**
 * The Interface INodesMediator.
 */
public interface INodesMediator {

	/**
	 * Adds the node.
	 *
	 * @param node the node
	 */
	public void addNode(Node node);
	
	/**
	 * Send new risky node.
	 *
	 * @param collidableNode the collidable node
	 */
	public void sendNewRiskyNode(Node collidableNode);
}
