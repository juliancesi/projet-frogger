package graphic.animation;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;


public abstract class AbstractMoveAnimation extends Transition {
	
	protected int jump;
	public void setJump(int jump) {
		this.jump = jump;
	}
	
	protected Node tile;
	public abstract void setTile(Node tile);
	
	protected double x, y;
	public void setDirection(KeyCode direction) {
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

	public abstract void play();
	
	@Override
	protected void interpolate(double frac) {
	}
}
