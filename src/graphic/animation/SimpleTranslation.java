package graphic.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class SimpleTranslation extends AbstractMoveAnimation {

	private TranslateTransition animation;
	public SimpleTranslation(double jump, Duration duration) {
//		super(jump, duration);
		this.jump = jump;
		this.duration = duration;
		
		animation = new TranslateTransition(duration);
		animation.setCycleCount(1);
	}
	
	protected void changeCoordinates() {
		animation.setByX(x);
		animation.setByY(x);
	}
	
	public void play(KeyCode direction) {
		setDirection(direction);
		changeCoordinates();
		animation.playFromStart();
	}
	
	protected double x, y;
	protected void setDirection(KeyCode direction) {
		switch(direction) {
		case UP:
			y = jump;
			break;
		case DOWN:
			y = -jump;
			break;
		case LEFT:
			x = -jump;
			break;
		case RIGHT:
			x = jump;
			break;
		default:
			break;
		}
	}

}
