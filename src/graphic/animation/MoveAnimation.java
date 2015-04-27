package graphic.animation;

import javafx.util.Duration;


public enum MoveAnimation {
	TRANSLATE(new SimpleTranslation(100, Duration.millis(250))),
	;
	
	private AbstractMoveAnimation animation;
	MoveAnimation(AbstractMoveAnimation animation) {
		this.animation = animation;
	}
	
	public AbstractMoveAnimation getAnimation() {
		return animation;
	}
}
