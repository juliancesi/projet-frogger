package graphic.animation;

import graphic.bean.IAnimationMoveProperty;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.Transition;
import javafx.scene.Node;

public class AnimationController {
	
	private static AnimationController instance = new AnimationController();
	private Map<Node, AbstractMoveAnimation> animatedNodes = new HashMap<Node, AbstractMoveAnimation>();
	
	private double stageWidth;
	private double stageHeight;
	
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
	
	public Map<Node, AbstractMoveAnimation> getAnimatedNodes() {
		return animatedNodes;
	}
	
	private AbstractMoveAnimation setAnimation(Node node) {
		AbstractMoveAnimation animation = ((IAnimationMoveProperty) node).getMoveAnimation();
		animation.setTile(node);
		return animation;
	}
	
	public void playAnimation(Node node) {
		checkOutOfBounds(node);
		animatedNodes.get(node).playAnimation();
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
			node.setLayoutX(node.getParent().getBoundsInLocal().getWidth());
			node.setTranslateX(-node.getBoundsInLocal().getWidth());
		}
		if((((IAnimationMoveProperty) node).getAnimationMoveProperty() == MoveAnimation.RIGHT_TRANSLATION) && 				
				(node.getBoundsInParent().getMinX() > stageWidth)) {
			node.setLayoutX(0);
			node.setTranslateX(-node.getBoundsInLocal().getWidth());
		}
		
	}

	public void setBounds(double width, double height) {
		stageWidth = width;
		stageHeight = height;
	}
}
