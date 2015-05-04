package graphic.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class SimpleTranslation extends AbstractMoveAnimation {

	private TranslateTransition animation;
	public SimpleTranslation(double gapX, double gapY, Duration duration) {
		animation = new TranslateTransition(duration);
		this.setJump(gapX, gapY);
		animation.setCycleCount(1);
	}
	
	public SimpleTranslation(double gapX, double gapY, Duration duration, KeyCode direction) {
		this(gapX, gapY, duration);
		this.setDirection(direction);
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
