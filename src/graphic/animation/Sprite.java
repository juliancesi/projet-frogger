package graphic.animation;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;


public class Sprite extends Transition {

	private ImageView imageView;
	private double width;
	private double height;
	private int columns;
	private int count;
	private int offsetY;
	
	private int lastIndex;

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
	
	public void animate(KeyCode sens, int cycleCount) {
		offsetY = keyToY(sens);
		setCycleCount(cycleCount);
		playFromStart();
	}
	
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
