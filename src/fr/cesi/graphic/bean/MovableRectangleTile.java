package fr.cesi.graphic.bean;

import fr.cesi.collisions.CollisionsEngine;
import fr.cesi.collisions.ICollidable;
import fr.cesi.graphic.animation.AbstractMoveAnimation;
import fr.cesi.graphic.animation.MoveAnimation;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;


public class MovableRectangleTile extends Rectangle implements ICollisionsProperty, IAnimationMoveProperty, ICollidable {

	public MovableRectangleTile() {
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

	
	/////////////////////////////////
	
	private CollisionsEngine collisionsEngine;
	@Override
	public void setCollisionsEngine(CollisionsEngine collisionsEngine) {
		this.collisionsEngine = collisionsEngine;
	}

	@Override
	public CollisionsEngine getCollisionsEngine() {
		return collisionsEngine;
	}
	
	@Override
	public void sendNewRiskyNode() {
		collisionsEngine.sendNewRiskyNode(this);
	}

	@Override
	public void receiveRiskNode(Node collidableNode) {
//		System.out.printf("[%2$s] J'ai reçu le node [%1$s]", collidableNode.getId(), this.getId()).println();
//		Node collisionsTo = collisionsEngine.checkCollisions(this, collidableNode);
//		if(collisionsTo != null) {
//			System.out.println("collision");
//		}

	}

	@Override
	public boolean isCollided() {
		return false;
	}

	@Override
	public void setIsCollided() {
	}
	
}
