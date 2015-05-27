package fr.cesi.graphic.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;


// TODO: Auto-generated Javadoc
/**
 * The Class RectangleTile.
 */
public class RectangleTile extends Rectangle implements ICollisionsProperty {

	/**
	 * Instantiates a new rectangle tile.
	 */
	public RectangleTile() {
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
}
