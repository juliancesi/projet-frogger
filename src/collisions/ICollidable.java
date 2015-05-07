package collisions;

import javafx.scene.Node;


public interface ICollidable {

	public void sendNewRiskyNode();
	
	public void receiveRiskNode(Node collidableNode);
}
