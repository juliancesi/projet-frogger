package graphic.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

public class ImageTile extends ImageView implements ICollisionsProperty {

	public ImageTile() {
		super();
	}
	
	private final IntegerProperty collisionsType = new SimpleIntegerProperty(this, "collisions", 0);
	
	@Override
	public void setCollisionsProperty(int value) {
		collisionsType.set(value);
	}

	@Override
	public int getCollisionsProperty() {
		return collisionsType.get();
	}

	@Override
	public IntegerProperty collisionsProperty() {
		return collisionsType;
	}
	
}
