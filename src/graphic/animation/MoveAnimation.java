package graphic.animation;

import javafx.animation.Transition;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public enum MoveAnimation {
	TRANSLATE_UP(new SimpleTranslation(1, KeyCode.UP, Duration.millis(500))),
	TRANSLATE_LEFT(new SimpleTranslation(1, KeyCode.LEFT, Duration.millis(500))),
	TRANSLATE_DOWN(new SimpleTranslation(1, KeyCode.DOWN, Duration.millis(500))),
	TRANSLATE_RIGHT( new SimpleTranslation(1, KeyCode.RIGHT, Duration.millis(500))),
	;
	
	Transition animation;
	MoveAnimation(Transition animation) {
		this.animation = animation;
	}
}
