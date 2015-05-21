package fr.cesi.graphic.fxmlcontroller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import fr.cesi.collisions.CollisionsEngine;
import fr.cesi.collisions.ICollidable;
import fr.cesi.graphic.animation.MoveController;
import fr.cesi.graphic.bean.ICollisionsProperty;

public abstract class AbstractController implements Initializable {

	protected MoveController moveController = MoveController.getInstance();
	protected Map<String, Node> collisionsNodesList;
	protected Map<String, Node> otherNodesList;
	
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
		if(node instanceof ICollidable) {
			CollisionsEngine.getInstance().addNode(node);
		}		
		
		if(node instanceof ICollisionsProperty) {
			if(collisionsNodesList == null) {
				collisionsNodesList = new HashMap<String, Node>();
			}
			collisionsNodesList.put(node.getId(), node);
		}
		else {
			if(otherNodesList == null) {
				otherNodesList = new HashMap<String, Node>();
			}
			otherNodesList.put(node.getId(), node);
		}
	}
	
	public Pane getRootPane() {
		return root;
	}
	
	public Node getNode(String id) {
		if((collisionsNodesList != null || collisionsNodesList.size() > 0) && collisionsNodesList.containsKey(id)) {
			return collisionsNodesList.get(id);
		}
		else if((otherNodesList != null || otherNodesList.size() > 0) && otherNodesList.containsKey(id)) {
			return otherNodesList.get(id);
		}
		return null;
	}

	public Map<String, Node> getAllNodes() {
		return collisionsNodesList;
	}

}
