package collisions;

import javafx.scene.Node;


public interface INodesMediator {

	public void addNode(Node node);
	
	public void sendNewRiskyNode(Node collidableNode);
}
