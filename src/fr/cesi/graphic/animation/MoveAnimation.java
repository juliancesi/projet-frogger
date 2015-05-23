package fr.cesi.graphic.animation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.cesi.rules.RulesKeeper;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;


// TODO: Auto-generated Javadoc
/**
 * The Enum MoveAnimation.
 */
public enum MoveAnimation {
	
	TRANSLATE(SimpleTranslation.class, 75, 65, Duration.millis(200), null),
	LEFT_TRANSLATION(SimpleTranslation.class, 5, 0, Duration.millis(10), KeyCode.LEFT),
	RIGHT_TRANSLATION(SimpleTranslation.class, 5, 0, Duration.millis(10), KeyCode.RIGHT),
	;

	/** The animation. */
	private Class<? extends AbstractMoveAnimation> animation;
	
	/** The gap y. */
	private double gapX, gapY;
	
	/** The duration. */
	private Duration duration;
	
	/**
	 * Instantiates a new move animation.
	 *
	 * @param animation the animation
	 * @param gapX the gap x
	 * @param gapY the gap y
	 * @param duration the duration
	 */
	MoveAnimation(Class<? extends AbstractMoveAnimation> animation, double gapX, double gapY, Duration duration) {
		this.animation = animation;
		this.gapX = gapX;
		this.gapY = gapY;
		this.duration = duration;
	}

	/** The direction. */
	private KeyCode direction;
	
	/**
	 * Instantiates a new move animation.
	 *
	 * @param animation the animation
	 * @param gapX the gap x
	 * @param gapY the gap y
	 * @param duration the duration
	 * @param direction the direction
	 */
	MoveAnimation(Class<? extends AbstractMoveAnimation> animation, double gapX, double gapY, Duration duration, KeyCode direction) {
		this(animation, gapX, gapY, duration);
		this.direction = direction;
	}

	/**
	 * Gets the animation.
	 *
	 * @return the animation
	 */
	public AbstractMoveAnimation getAnimation() {
		Constructor<? extends AbstractMoveAnimation> constructor = null;
		AbstractMoveAnimation moveAnimation = null;
		
//		gapX = RulesKeeper.getInstance().getSpeed();
		
		try {
			if(direction != null) {
				constructor = animation.getConstructor(Double.TYPE, Double.TYPE, Duration.class, KeyCode.class);
				moveAnimation = constructor.newInstance(RulesKeeper.getInstance().getSpeed(), gapY, duration, direction);
			}
			else {
				constructor = animation.getConstructor(Double.TYPE, Double.TYPE, Duration.class);
				moveAnimation = constructor.newInstance(gapX, gapY, duration);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		moveAnimation.setAnimationType(this);
		return moveAnimation;
	}
}
