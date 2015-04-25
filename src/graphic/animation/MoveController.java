package graphic.animation;

import javafx.scene.input.KeyCode;

public class MoveController {

	private Boolean[] moveStates;
	private boolean inMove;

	private static MoveController instance = new MoveController();

	public static MoveController getInstance() {
		return instance;
	}

	private MoveController() {
		inMove = false;
		moveStates = new Boolean[] {
				false,
				false,
				false,
				false
		};
	}

	public void setMove(KeyCode key) {
		Integer index = keyToInt(key);
		if(index != null) {	
			setState(index);
			setInMove();}
	}

	private void setState(int index) {
		moveStates[index] = !moveStates[index];
	}

	public boolean getState(KeyCode key) {
		int index = keyToInt(key);
		return moveStates[index];
	}

	private Integer keyToInt(KeyCode key) {
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

	public boolean isInMove() {
		return inMove;
	}

	private void setInMove() {
		inMove = !inMove;
	}

	public void reset() {
		inMove = false;
		for(int i = 0; i < moveStates.length; i++) {
			moveStates[i] = false;
		}
	}

}
