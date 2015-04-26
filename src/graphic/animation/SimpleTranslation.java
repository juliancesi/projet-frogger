package graphic.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class SimpleTranslation extends AbstractMoveAnimation {

	private TranslateTransition animation;
	public SimpleTranslation(int jump, Duration duration) {
		animation = new TranslateTransition(duration);
		this.setJump(jump);
		animation.setCycleCount(1);
	}

	public void setTile(Node tile) {
		animation.setNode(tile);
	}
	
	private void changeCoordinates() {
		animation.setByX(x);
		animation.setByY(y);
	}
	
	@Override
	public void play() {
		System.out.println(animation.getByX());
		
		changeCoordinates();
		animation.play();
		
		System.out.println("translate: " + animation.getNode().getTranslateX() + " - " + animation.getNode().getTranslateY());
		System.out.println("layout: " + animation.getNode().getLayoutX() + " - " + animation.getNode().getLayoutY());
		System.out.println("parentbounds: " + animation.getNode().getBoundsInParent());
	}
}
