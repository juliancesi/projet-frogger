package fr.cesi.graphic.animation;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import fr.cesi.graphic.bean.IAnimationMoveProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AnimationController.
 */
public class AnimationController {
	
	/** The animated nodes. */
	private Map<Node, AbstractMoveAnimation> animatedNodes;
	
	/** The stage width. */
	private double stageWidth;
	
	/** The stage height. */
	private double stageHeight;
	
	/**
	 * Instantiates a new animation controller.
	 */
	public AnimationController() {
		animatedNodes = new HashMap<Node, AbstractMoveAnimation>();
	}

	/**
	 * Adds the node.
	 *
	 * @param node the node
	 */
	public void addNode(Node node) {
		if(!animatedNodes.containsKey(node)) {
			AbstractMoveAnimation animation = ((IAnimationMoveProperty) node).getMoveAnimation();
			animation.setTile(node);
			
			animatedNodes.put(node, animation);
		}
	}
	
	/**
	 * Gets the animated nodes.
	 *
	 * @return the animated nodes
	 */
	public Map<Node, AbstractMoveAnimation> getAnimatedNodes() {
		return animatedNodes;
	}
	
	/**
	 * Play animation.
	 *
	 * @param node the node
	 */
	public void playAnimation(Node node) {
		checkOutOfBounds(node);
		animatedNodes.get(node).playAnimation();
	}

	/**
	 * Play animation.
	 */
	public void playAnimation() {
		for(Node node : animatedNodes.keySet()) {
			playAnimation(node);
		}
	}
	
	/**
	 * Check out of bounds.
	 *
	 * @param node the node
	 */
	private void checkOutOfBounds(Node node) {
		if(node.getId().equals("frog") && ((node.getBoundsInParent().getMaxX() < 0))) {
			node.setLayoutX(stageWidth);
			node.setTranslateX(-node.getBoundsInLocal().getWidth());
		}
		else if(node.getId().equals("frog") && ((node.getBoundsInParent().getMaxX() > stageWidth))) {
			node.setLayoutX(0);
			node.setTranslateX(-node.getBoundsInLocal().getWidth());
		}
		
		if((((IAnimationMoveProperty) node).getAnimationMoveProperty() == MoveAnimation.LEFT_TRANSLATION) && 				
				(node.getBoundsInParent().getMaxX() < 0)) {
			node.setLayoutX(node.getParent().getBoundsInLocal().getWidth());
			node.setTranslateX(-node.getBoundsInLocal().getWidth());
		}
		else if((((IAnimationMoveProperty) node).getAnimationMoveProperty() == MoveAnimation.RIGHT_TRANSLATION) && 				
				(node.getBoundsInParent().getMinX() > stageWidth)) {
			node.setLayoutX(0);
			node.setTranslateX(-node.getBoundsInLocal().getWidth());
		}
	}

	/**
	 * Sets the bounds.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public void setBounds(double width, double height) {
		stageWidth = width;
		stageHeight = height;
	}
}
