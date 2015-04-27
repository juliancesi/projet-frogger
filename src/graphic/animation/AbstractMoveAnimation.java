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
	public Double[] setDirection(KeyCode direction) {
		x = y = 0;
		switch(direction) {
		case UP:
			y = -jump;
			break;
		case DOWN:
			y = jump;
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
		
		return new Double[] {x, y};
	}

	public abstract void play();
	
	@Override
	protected void interpolate(double frac) {
	}
}
