package fr.cesi.graphic.bean;

import fr.cesi.graphic.animation.AbstractMoveAnimation;
import fr.cesi.graphic.animation.MoveAnimation;
import javafx.beans.property.ObjectProperty;

public interface IAnimationMoveProperty extends ITypeProperties {

	public void setAnimationMoveProperty(final MoveAnimation value);
	
	public MoveAnimation getAnimationMoveProperty();
	
	public ObjectProperty<MoveAnimation> animationMoveProperty();
	
	public void setMoveAnimation(AbstractMoveAnimation moveAnimation);

	public AbstractMoveAnimation getMoveAnimation();
}
