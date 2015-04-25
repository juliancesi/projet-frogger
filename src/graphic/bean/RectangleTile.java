package graphic.bean;

import graphic.animation.AbstractMoveAnimation;
import graphic.animation.MoveAnimation;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Rectangle;


public class RectangleTile extends Rectangle implements ICollisionsProperty, IAnimationMoveProperty {

	public RectangleTile() {
		super();
	}

	// Collisions property
	private IntegerProperty collisionsProperty;
	@Override
	public final void setCollisionsProperty(int value) {
		if(collisionsProperty != null || value != 0) {
			collisionsProperty().set(value);
		}
	}

	@Override
	public final int getCollisionsProperty() {
		return collisionsProperty == null ? 0 : collisionsProperty.get();
	}

	@Override
	public final IntegerProperty collisionsProperty() {
		if(collisionsProperty == null) {
			collisionsProperty = new SimpleIntegerProperty(this, "collisionsProperty", 0);
		}
		return collisionsProperty;
	}

	// Animation property
	private ObjectProperty<MoveAnimation> animationMoveProperty;
	@Override
	public final void setAnimationMoveProperty(MoveAnimation value) {
		if(animationMoveProperty != null || value != null) {
			animationMoveProperty().set(value);
			setMoveAnimation(value.getAnimation());
		}
	}

	@Override
	public final MoveAnimation getAnimationMoveProperty() {
		return animationMoveProperty == null ? null : animationMoveProperty.get();
	}

	@Override
	public final ObjectProperty<MoveAnimation> animationMoveProperty() {
		if(animationMoveProperty == null) {
			animationMoveProperty = new SimpleObjectProperty<MoveAnimation>(this, "animationProperty", null);
		}
		return animationMoveProperty;
	}

	private AbstractMoveAnimation moveAnimation;
	@Override
	public void setMoveAnimation(AbstractMoveAnimation moveAnimation) {
		this.moveAnimation = moveAnimation;
	}
	
	@Override
	public AbstractMoveAnimation getMoveAnimation() {
		return moveAnimation;
	}
}
