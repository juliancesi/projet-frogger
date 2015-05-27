package fr.cesi.graphic.animation;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;


// TODO: Auto-generated Javadoc
/**
 * The Class Sprite.
 */
public class Sprite extends Transition {

	/** The image view. */
	private ImageView imageView;
	
	/** The width. */
	private double width;
	
	/** The height. */
	private double height;
	
	/** The columns. */
	private int columns;
	
	/** The count. */
	private int count;
	
	/** The offset y. */
	private int offsetY;
	
	/** The last index. */
	private int lastIndex;

	/**
	 * Instantiates a new sprite.
	 *
	 * @param imageView the image view
	 * @param width the width
	 * @param height the height
	 * @param columns the columns
	 * @param duration the duration
	 */
	public Sprite(ImageView imageView, double width, double height, 
			int columns, Duration duration) {
		this.imageView = imageView;
		this.width = width;
		this.height = height;
		this.count = columns + 1;
		this.columns = columns;
		
		setCycleDuration(duration);
		setInterpolator(Interpolator.LINEAR);
	}
	
	/**
	 * Animate.
	 *
	 * @param sens the sens
	 * @param cycleCount the cycle count
	 */
	public void animate(KeyCode sens, int cycleCount) {
		offsetY = keyToY(sens);
		setCycleCount(cycleCount);
		playFromStart();
	}
	
	/* (non-Javadoc)
	 * @see javafx.animation.Transition#interpolate(double)
	 */
	@Override
	protected void interpolate(double frac) {
		final int index = Math.min((int) Math.floor(frac * count), count - 1);
        if (index != lastIndex) {
            final double x = (index % columns) * width;
            final double y = offsetY * height;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
	}
	
	/**
	 * Key to y.
	 *
	 * @param sens the sens
	 * @return the int
	 */
	private int keyToY(KeyCode sens) {
		switch(sens) {
		case UP:
			return 0;
		case DOWN:
			return 1;
		case LEFT:
			return 2;
		case RIGHT:
			return 3;
		}
		return 5;
	}

}
