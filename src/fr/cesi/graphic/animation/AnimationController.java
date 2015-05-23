package fr.cesi.graphic.animation;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import fr.cesi.graphic.bean.IAnimationMoveProperty;
import fr.cesi.rules.RulesKeeper;

public class AnimationController {
	
	private Map<Node, AbstractMoveAnimation> animatedNodes = new HashMap<Node, AbstractMoveAnimation>();
	
	private double stageWidth;
	private double stageHeight;
	
	private RulesKeeper rulesKeeper;
	
	public AnimationController(RulesKeeper rulesKeeper) {
		this.rulesKeeper = rulesKeeper;
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
