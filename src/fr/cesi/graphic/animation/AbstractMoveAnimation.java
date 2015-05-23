package fr.cesi.graphic.animation;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractMoveAnimation.
 */
public abstract class AbstractMoveAnimation extends Transition {
	
	/** The gap x. */
	protected double gapX;
	
	/** The gap y. */
	protected double gapY;
	
	/**
	 * Sets the jump.
	 *
	 * @param gapX the gap x
	 * @param gapY the gap y
	 */
	public void setJump(double gapX, double gapY) {
		this.gapX = gapX;
		this.gapY = gapY;
	}
	
	/** The tile. */
	protected Node tile;
	
	/**
	 * Sets the tile.
	 *
	 * @param tile the new tile
	 */
	public abstract void setTile(Node tile);
	
	/** The y. */
	protected double x, y;
	
	/**
	 * Sets the direction.
	 *
	 * @param direction the new direction
	 */
	public void setDirection(KeyCode direction) {
		x = y = 0;
		switch(direction) {
		case UP:
			y = -gapY;
			break;
		case DOWN:
			y = gapY;
			break;
		case LEFT:
			x = -gapX;
			break;
		case RIGHT:
			x = gapX;
			break;
		default:
			break;
		}
		
		setCoordinates(x, y);
	}

	/**
	 * Sets the coordinates.
	 *
	 * @param x the x
	 * @param y the y
	 */
	protected void setCoordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the coordinates.
	 *
	 * @return the coordinates
	 */
	public Double[] getCoordinates() {
		if(x != 0 || y != 0) {
			return new Double[] {x, y};
		}
		return null;
	}
	
	/**
	 * Play animation.
	 */
	public abstract void playAnimation();
	
	/** The animation type. */
	private MoveAnimation animationType;

	/**
	 * Sets the animation type.
	 *
	 * @param animType the new animation type
	 */
	public void setAnimationType(MoveAnimation animType) {
		animationType = animType;
	}
	
	/**
	 * Gets the animation type.
	 *
	 * @return the animation type
	 */
	public MoveAnimation getAnimationType() {
		return animationType;
	}

	
	/* (non-Javadoc)
	 * @see javafx.animation.Transition#interpolate(double)
	 */
	@Override
	protected void interpolate(double frac) {
	}
}
