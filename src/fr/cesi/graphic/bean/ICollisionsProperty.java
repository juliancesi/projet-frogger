package fr.cesi.graphic.bean;

import javafx.beans.property.IntegerProperty;


public interface ICollisionsProperty extends ITypeProperties {
	
	public void setCollisionsProperty(int value);

	public int getCollisionsProperty();
	
	public IntegerProperty collisionsProperty();
}
