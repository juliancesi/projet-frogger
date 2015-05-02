package graphic.animation;

import javafx.scene.input.KeyCode;
import javafx.util.Duration;


public enum MoveAnimation {
	TRANSLATE(new SimpleTranslation(75, 65, Duration.millis(250))),
	VEHICULES(new SimpleTranslation(5, 0, Duration.millis(10), KeyCode.LEFT))
	;
	
	private AbstractMoveAnimation animation;
	MoveAnimation(AbstractMoveAnimation animation) {
		this.animation = animation;
	}
	
	public AbstractMoveAnimation getAnimation() {
		return animation;
	}
}
