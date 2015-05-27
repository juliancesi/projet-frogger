package fr.cesi.graphic.bean;

import fr.cesi.graphic.animation.AbstractMoveAnimation;
import fr.cesi.graphic.animation.MoveAnimation;
import javafx.beans.property.ObjectProperty;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAnimationMoveProperty.
 */
public interface IAnimationMoveProperty {

	/**
	 * Sets the animation move property.
	 *
	 * @param value the new animation move property
	 */
	public void setAnimationMoveProperty(final MoveAnimation value);
	
	/**
	 * Gets the animation move property.
	 *
	 * @return the animation move property
	 */
	public MoveAnimation getAnimationMoveProperty();
	
	/**
	 * Animation move property.
	 *
	 * @return the object property
	 */
	public ObjectProperty<MoveAnimation> animationMoveProperty();
	
	/**
	 * Sets the move animation.
	 *
	 * @param moveAnimation the new move animation
	 */
	public void setMoveAnimation(AbstractMoveAnimation moveAnimation);

	/**
	 * Gets the move animation.
	 *
	 * @return the move animation
	 */
	public AbstractMoveAnimation getMoveAnimation();
}
