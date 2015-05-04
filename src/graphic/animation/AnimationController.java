package graphic.animation;

import graphic.bean.IAnimationMoveProperty;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.Interpolatable;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.Node;

public class AnimationController {
	
	private static AnimationController instance = new AnimationController();
	private Map<Node, Transition> animatedNodes = new HashMap<Node, Transition>();
	
	public static AnimationController getInstance() {
		return instance;
	}
	
	private AnimationController() {
	}

	public void addNode(Node node) {
		if(!animatedNodes.containsKey(node)) {
			animatedNodes.put(node, setAnimation(node));
		}
	}
	
	public void addAllNode(Map<String, Node> nodesList) {
		for(String key : nodesList.keySet()) {
			addNode(nodesList.get(key));
		}
	}
	
	public Map<Node, Transition> getAnimatedNodes() {
		return animatedNodes;
	}
	
	private Transition setAnimation(Node node) {
		AbstractMoveAnimation animation = ((IAnimationMoveProperty) node).getMoveAnimation();
		animation.setTile(node);
		return animation;
	}
	
	public void playAnimation(Node node) {
		checkOutOfBounds(node);
		animatedNodes.get(node).play();
	}

	public void playAnimation(String nodeId) {
		for(Node n : animatedNodes.keySet()) {
			if(n.getId().equals(nodeId)) {
				playAnimation(n);
			}
			else {
				System.out.println("non");
			}
		}
	}
	
	public void playAnimation() {
		for(Node node : animatedNodes.keySet()) {
			playAnimation(node);
		}
	}
	
	private void checkOutOfBounds(Node node) {
		if((((IAnimationMoveProperty) node).getAnimationMoveProperty() == MoveAnimation.LEFT_TRANSLATION) && 				
				(node.getBoundsInParent().getMaxX() < 0)) {
			node.setTranslateX(500);
		}
		if((((IAnimationMoveProperty) node).getAnimationMoveProperty() == MoveAnimation.RIGHT_TRANSLATION) && 				
				(node.getBoundsInParent().getMinX() > 788)) {
			node.setTranslateX(-788);
		}
		
	}
}
