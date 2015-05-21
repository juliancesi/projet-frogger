package fr.cesi.collisions;

import javafx.scene.Node;


public interface ICollidable {

	public void setCollisionsEngine(CollisionsEngine colEngine);

	public CollisionsEngine getCollisionsEngine();
	
	public void sendNewRiskyNode();
	
	public void receiveRiskNode(Node collidableNode);
	
	public boolean isCollided();
	
	public void setIsCollided();
}
