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


// TODO: Auto-generated Javadoc
/**
 * The Class MovableRectangleTile.
 */
public class MovableRectangleTile extends Rectangle implements ICollisionsProperty, IAnimationMoveProperty, ICollidable {

	/**
	 * Instantiates a new movable rectangle tile.
	 */
	public MovableRectangleTile() {
		super();
	}

	// Collisions property
	/** The collisions property. */
	private IntegerProperty collisionsProperty;
	
	/* (non-Javadoc)
	 * @see fr.cesi.graphic.bean.ICollisionsProperty#setCollisionsProperty(int)
	 */
	@Override
	public final void setCollisionsProperty(int value) {
		if(collisionsProperty != null || value != 0) {
			collisionsProperty().set(value);
		}
	}

	/* (non-Javadoc)
	 * @see fr.cesi.graphic.bean.ICollisionsProperty#getCollisionsProperty()
	 */
	@Override
	public final int getCollisionsProperty() {
		return collisionsProperty == null ? 0 : collisionsProperty.get();
	}

	/* (non-Javadoc)
	 * @see fr.cesi.graphic.bean.ICollisionsProperty#collisionsProperty()
	 */
	@Override
	public final IntegerProperty collisionsProperty() {
		if(collisionsProperty == null) {
			collisionsProperty = new SimpleIntegerProperty(this, "collisionsProperty", 0);
		}
		return collisionsProperty;
	}

	// Animation property
	/** The animation move property. */
	private ObjectProperty<MoveAnimation> animationMoveProperty;
	
	/* (non-Javadoc)
	 * @see fr.cesi.graphic.bean.IAnimationMoveProperty#setAnimationMoveProperty(fr.cesi.graphic.animation.MoveAnimation)
	 */
	@Override
	public final void setAnimationMoveProperty(MoveAnimation value) {
		if(animationMoveProperty != null || value != null) {
			animationMoveProperty().set(value);
			setMoveAnimation(value.getAnimation());
		}
	}

	/* (non-Javadoc)
	 * @see fr.cesi.graphic.bean.IAnimationMoveProperty#getAnimationMoveProperty()
	 */
	@Override
	public final MoveAnimation getAnimationMoveProperty() {
		return animationMoveProperty == null ? null : animationMoveProperty.get();
	}

	/* (non-Javadoc)
	 * @see fr.cesi.graphic.bean.IAnimationMoveProperty#animationMoveProperty()
	 */
	@Override
	public final ObjectProperty<MoveAnimation> animationMoveProperty() {
		if(animationMoveProperty == null) {
			animationMoveProperty = new SimpleObjectProperty<MoveAnimation>(this, "animationProperty", null);
		}
		return animationMoveProperty;
	}

	/** The move animation. */
	private AbstractMoveAnimation moveAnimation;
	
	/* (non-Javadoc)
	 * @see fr.cesi.graphic.bean.IAnimationMoveProperty#setMoveAnimation(fr.cesi.graphic.animation.AbstractMoveAnimation)
	 */
	@Override
	public void setMoveAnimation(AbstractMoveAnimation moveAnimation) {
		this.moveAnimation = moveAnimation;
	}
	
	/* (non-Javadoc)
	 * @see fr.cesi.graphic.bean.IAnimationMoveProperty#getMoveAnimation()
	 */
	@Override
	public AbstractMoveAnimation getMoveAnimation() {
		return moveAnimation;
	}

	
	/////////////////////////////////
	
	/** The collisions engine. */
	private CollisionsEngine collisionsEngine;
	
	/* (non-Javadoc)
	 * @see fr.cesi.collisions.ICollidable#setCollisionsEngine(fr.cesi.collisions.CollisionsEngine)
	 */
	@Override
	public void setCollisionsEngine(CollisionsEngine collisionsEngine) {
		this.collisionsEngine = collisionsEngine;
	}

	/* (non-Javadoc)
	 * @see fr.cesi.collisions.ICollidable#getCollisionsEngine()
	 */
	@Override
	public CollisionsEngine getCollisionsEngine() {
		return collisionsEngine;
	}
	
	/* (non-Javadoc)
	 * @see fr.cesi.collisions.ICollidable#sendNewRiskyNode()
	 */
	@Override
	public void sendNewRiskyNode() {
		collisionsEngine.sendNewRiskyNode(this);
	}

	/* (non-Javadoc)
	 * @see fr.cesi.collisions.ICollidable#receiveRiskNode(javafx.scene.Node)
	 */
	@Override
	public void receiveRiskNode(Node collidableNode) {
//		System.out.printf("[%2$s] J'ai reçu le node [%1$s]", collidableNode.getId(), this.getId()).println();
//		Node collisionsTo = collisionsEngine.checkCollisions(this, collidableNode);
//		if(collisionsTo != null) {
//			System.out.println("collision");
//		}

	}

	/* (non-Javadoc)
	 * @see fr.cesi.collisions.ICollidable#isCollided()
	 */
	@Override
	public boolean isCollided() {
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.cesi.collisions.ICollidable#setIsCollided()
	 */
	@Override
	public void setIsCollided() {
	}
	
}
