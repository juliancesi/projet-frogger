package fr.cesi.graphic.fxmlcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import fr.cesi.application.SceneLoader;
import fr.cesi.collisions.CollisionsEngine;
import fr.cesi.collisions.ICollidable;
import fr.cesi.graphic.animation.MoveController;
import fr.cesi.graphic.bean.ICollisionsProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractController.
 */
public abstract class AbstractController implements Initializable {

	/** The move controller. */
	protected MoveController moveController = MoveController.getInstance();
	
	/** The collisions nodes list. */
	protected Map<String, Node> collisionsNodesList;
	
	/** The other nodes list. */
	protected Map<String, Node> otherNodesList;
	
	/** The collisions engine. */
	protected CollisionsEngine collisionsEngine = new CollisionsEngine();
	
	/** The root pane. */
	@FXML
	Pane root;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		readAllNodes(root);
	}
	
	/**
	 * Read all nodes.
	 *
	 * @param parent the parent
	 */
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
	
	/** The image lst. */
	protected Map<String, Image> imageLst = new HashMap<String, Image>();
	
	/**
	 * Bind property.
	 *
	 * @param node the node
	 */
	protected void bindProperty(Node node) {
		if(node instanceof ICollidable) {
			((ICollidable) node).setCollisionsEngine(collisionsEngine);
			collisionsEngine.addNode(node);
		}		
		
		if(node instanceof ICollisionsProperty) {
			if(collisionsNodesList == null) {
				collisionsNodesList = new HashMap<String, Node>();
			}
			collisionsNodesList.put(node.getId(), node);
			
			Image nodeImg = ((ImageView) node).getImage();
			String nodeImgUrl = nodeImg.impl_getUrl();
			if(imageLst.containsKey(nodeImgUrl)) {
				((ImageView) node).setImage(imageLst.get(nodeImgUrl));
			}
			else {
				imageLst.put(nodeImgUrl, nodeImg);
			}
			
		}
		else {
			if(otherNodesList == null) {
				otherNodesList = new HashMap<String, Node>();
			}
			otherNodesList.put(node.getId(), node);
		}
		
	}
	
	/**
	 * Gets the root pane.
	 *
	 * @return the root pane
	 */
	public Pane getRootPane() {
		return root;
	}
	
	/**
	 * Gets the node.
	 *
	 * @param id the id
	 * @return the node
	 */
	public Node getNode(String id) {
		if((collisionsNodesList != null || collisionsNodesList.size() > 0) && collisionsNodesList.containsKey(id)) {
			return collisionsNodesList.get(id);
		}
		else if((otherNodesList != null || otherNodesList.size() > 0) && otherNodesList.containsKey(id)) {
			return otherNodesList.get(id);
		}
		return null;
	}

	/**
	 * Gets the all nodes.
	 *
	 * @return the all nodes
	 */
	public Map<String, Node> getAllNodes() {
		return collisionsNodesList;
	}
	
	/**
	 * Quitter.
	 */
	@FXML
	protected void quitter() {
		System.exit(0);
	}

	/**
	 * Menu.
	 */
	@FXML
	protected void menu() {
		try {
			SceneLoader.getInstance().loadMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
