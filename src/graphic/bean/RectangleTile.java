package graphic.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;


public class RectangleTile extends Rectangle implements ICollisionsProperty {

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
}
