package fr.cesi.graphic.animation;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;

public class MoveController {
	
	public enum MoveKey {
		UP(KeyCode.UP),
		DOWN(KeyCode.DOWN),
		LEFT(KeyCode.LEFT),
		RIGHT(KeyCode.RIGHT);
		
		private KeyCode code;
		MoveKey(KeyCode code) {
			this.code = code;
		}
		
		private static Set<KeyCode> authorizedKeys;
		public static boolean containsKey(KeyCode key) {
			if(authorizedKeys == null) {
				authorizedKeys = new HashSet<KeyCode>();
				for(MoveKey k : MoveKey.values()) {
					authorizedKeys.add(k.code);
				}
			}
			if(authorizedKeys.contains(key)) {
				return true;
			}
			return false;
		}	}
	
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
	
	public KeyCode getDirection() {
		for(int i = 0; i < moveStates.length; i++) {
			if(moveStates[i]) {
				return intToKey(i);
			}
		}
		return null;
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
			default:
				break;
		}
		return index;
	}

	private KeyCode intToKey(Integer index) {
		KeyCode key = null;
		switch(index) {
		case 0:
			key = KeyCode.UP;
			break;
		case 1:
			key = KeyCode.DOWN;
			break;
		case 2:
			key = KeyCode.LEFT;
			break;
		case 3:
			key = KeyCode.RIGHT;
			break;
			default:
				break;
		}
		return key;
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
