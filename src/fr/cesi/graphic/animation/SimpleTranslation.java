package fr.cesi.graphic.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import fr.cesi.collisions.ICollidable;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleTranslation.
 */
public class SimpleTranslation extends AbstractMoveAnimation {

	/** The animation. */
	private TranslateTransition animation;
	
	/**
	 * Instantiates a new simple translation.
	 *
	 * @param gapX the gap x
	 * @param gapY the gap y
	 * @param duration the duration
	 */
	public SimpleTranslation(double gapX, double gapY, Duration duration) {
		animation = new TranslateTransition(duration);
		this.setJump(gapX, gapY);
		animation.setCycleCount(1);
	}
	
	/**
	 * Instantiates a new simple translation.
	 *
	 * @param gapX the gap x
	 * @param gapY the gap y
	 * @param duration the duration
	 * @param direction the direction
	 */
	public SimpleTranslation(double gapX, double gapY, Duration duration, KeyCode direction) {
		this(gapX, gapY, duration);
		this.setDirection(direction);
	}

	/* (non-Javadoc)
	 * @see fr.cesi.graphic.animation.AbstractMoveAnimation#setTile(javafx.scene.Node)
	 */
	public void setTile(Node tile) {
		animation.setNode(tile);
	}
	
	/**
	 * Change coordinates.
	 */
	private void changeCoordinates() {
		animation.setByX(x);
		animation.setByY(y);
	}
	
	/* (non-Javadoc)
	 * @see fr.cesi.graphic.animation.AbstractMoveAnimation#playAnimation()
	 */
	@Override
	public void playAnimation() {
		changeCoordinates();
		((ICollidable) animation.getNode()).sendNewRiskyNode();
		animation.play();
	}
}
