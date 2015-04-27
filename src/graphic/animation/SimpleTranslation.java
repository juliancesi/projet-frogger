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
		changeCoordinates();
		animation.play();
	}
}
