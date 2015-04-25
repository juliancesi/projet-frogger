package util;

import javafx.scene.input.KeyCode;

public class Utils {
	
	public static Integer keyToInt(KeyCode key) {
		Integer index = null;
		switch(key) {
		case UP:
			index = 0;
			break;
		case DOWN:
			index = 1;
			break;
		case LEFT:
			index = 2;
			break;
		case RIGHT:
			index = 3;
			break;
		}
		return index;
	}
}
