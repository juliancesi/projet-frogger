package graphic.animation;

import javafx.util.Duration;


public enum MoveAnimation {
	TRANSLATE(new SimpleTranslation(50, Duration.millis(300))),
	;
	
	private AbstractMoveAnimation animation;
	MoveAnimation(AbstractMoveAnimation animation) {
		this.animation = animation;
	}
	
	public AbstractMoveAnimation getAnimation() {
		return animation;
	}
}
