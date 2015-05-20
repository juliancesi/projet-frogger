package fr.cesi.graphic.fxmlcontroller;

import fr.cesi.collisions.CollisionsEngine;
import fr.cesi.collisions.ICollidable;
import fr.cesi.graphic.animation.MoveController;
import fr.cesi.graphic.bean.ICollisionsProperty;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class AbstractController implements Initializable {

	protected MoveController moveController = MoveController.getInstance();
	protected Map<String, Node> nodesList;
	
	@FXML
	Pane root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		readAllNodes(root);
	}
	
	protected void readAllNodes(Node parent) {
		assert parent instanceof Pane;
		if(parent instanceof Pane) {
			for(Node n : ((Pane) parent).getChildren()) {
				readAllNodes(n);
			}
		}
		else {
			bindProperty(parent);
		}
	}

	protected void bindProperty(Node node) {
		if(node instanceof ICollisionsProperty) {
			if(nodesList == null) {
				nodesList = new HashMap<String, Node>();
			}
			nodesList.put(node.getId(), node);
		}
		if(node instanceof ICollidable) {
			CollisionsEngine.getInstance().addNode(node);
		}
//		if(node instanceof RectangleTile) {
//			RectangleTile tile = (RectangleTile) node;
//			System.out.println(tile.getCollisionsProperty());
//			
//			System.out.println(tile.getAnimationMoveProperty());
//			
//			MoveAnimation animation = tile.getAnimationMoveProperty();
//			AbstractMoveAnimation animation2 = animation.getAnimation();
//			
//			if(animation2 instanceof SimpleTranslation) {
//				SimpleTranslation animation3 = (SimpleTranslation) animation2;
//				animation3.setTile(tile);
//			
//				animation3.play(KeyCode.UP);
//			}
//		
//		}
	}
	
	public Pane getRootPane() {
		return root;
	}
	
	public Node getNode(String id) {
		if(nodesList != null || nodesList.size() > 0) {
			return nodesList.get(id);
		}
		return null;
	}

	public Map<String, Node> getAllNodes() {
		return nodesList;
	}

}
