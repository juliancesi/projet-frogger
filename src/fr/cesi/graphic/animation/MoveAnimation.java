package fr.cesi.graphic.animation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.input.KeyCode;
import javafx.util.Duration;


public enum MoveAnimation {
	TRANSLATE(SimpleTranslation.class, 75, 65, Duration.millis(200), null),
	LEFT_TRANSLATION(SimpleTranslation.class, 5, 0, Duration.millis(10), KeyCode.LEFT),
	RIGHT_TRANSLATION(SimpleTranslation.class, 5, 0, Duration.millis(10), KeyCode.RIGHT),
	;

	private Class<? extends AbstractMoveAnimation> animation;
	private double gapX, gapY;
	private Duration duration;
	MoveAnimation(Class<? extends AbstractMoveAnimation> animation, double gapX, double gapY, Duration duration) {
		this.animation = animation;
		this.gapX = gapX;
		this.gapY = gapY;
		this.duration = duration;
	}

	private KeyCode direction;
	MoveAnimation(Class<? extends AbstractMoveAnimation> animation, double gapX, double gapY, Duration duration, KeyCode direction) {
		this(animation, gapX, gapY, duration);
		this.direction = direction;
	}

	public AbstractMoveAnimation getAnimation() {
		Constructor<? extends AbstractMoveAnimation> constructor = null;
		AbstractMoveAnimation moveAnimation = null;
		try {
			if(direction != null) {
				constructor = animation.getConstructor(Double.TYPE, Double.TYPE, Duration.class, KeyCode.class);
				moveAnimation = constructor.newInstance(gapX, gapY, duration, direction);
			}
			else {
				constructor = animation.getConstructor(Double.TYPE, Double.TYPE, Duration.class);
				moveAnimation = constructor.newInstance(gapX, gapY, duration);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return moveAnimation;
	}
}
