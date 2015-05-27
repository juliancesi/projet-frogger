package fr.cesi.graphic.bean;

import javafx.beans.property.IntegerProperty;


// TODO: Auto-generated Javadoc
/**
 * The Interface ICollisionsProperty.
 */
public interface ICollisionsProperty {
	
	/**
	 * Sets the collisions property.
	 *
	 * @param value the new collisions property
	 */
	public void setCollisionsProperty(int value);

	/**
	 * Gets the collisions property.
	 *
	 * @return the collisions property
	 */
	public int getCollisionsProperty();
	
	/**
	 * Collisions property.
	 *
	 * @return the integer property
	 */
	public IntegerProperty collisionsProperty();
}
