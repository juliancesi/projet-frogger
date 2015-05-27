package fr.cesi.graphic.animation;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;

// TODO: Auto-generated Javadoc
/**
 * The Class MoveController.
 */
public class MoveController {
	
	/**
	 * The Enum MoveKey.
	 */
	public enum MoveKey {
		
		/** The up. */
		UP(KeyCode.UP),
		
		/** The down. */
		DOWN(KeyCode.DOWN),
		
		/** The left. */
		LEFT(KeyCode.LEFT),
		
		/** The right. */
		RIGHT(KeyCode.RIGHT);
		
		/** The code. */
		private KeyCode code;
		
		/**
		 * Instantiates a new move key.
		 *
		 * @param code the code
		 */
		MoveKey(KeyCode code) {
			this.code = code;
		}
		
		/** The authorized keys. */
		private static Set<KeyCode> authorizedKeys;
		
		/**
		 * Contains key.
		 *
		 * @param key the key
		 * @return true, if successful
		 */
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
	
	/** The move states. */
	private Boolean[] moveStates;
	
	/** The in move. */
	private boolean inMove;
	
	/** The instance. */
	private static MoveController instance = new MoveController();

	/**
	 * Gets the single instance of MoveController.
	 *
	 * @return single instance of MoveController
	 */
	public static MoveController getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new move controller.
	 */
	private MoveController() {
		inMove = false;
		moveStates = new Boolean[] {
				false,
				false,
				false,
				false
		};
	}

	/**
	 * Sets the move.
	 *
	 * @param key the new move
	 */
	public void setMove(KeyCode key) {
		Integer index = keyToInt(key);
		if(index != null) {	
			setState(index);
			setInMove();}
	}

	/**
	 * Sets the state.
	 *
	 * @param index the new state
	 */
	private void setState(int index) {
		moveStates[index] = !moveStates[index];
	}

	/**
	 * Gets the state.
	 *
	 * @param key the key
	 * @return the state
	 */
	public boolean getState(KeyCode key) {
		int index = keyToInt(key);
		return moveStates[index];
	}
	
	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public KeyCode getDirection() {
		for(int i = 0; i < moveStates.length; i++) {
			if(moveStates[i]) {
				return intToKey(i);
			}
		}
		return null;
	}

	/**
	 * Key to int.
	 *
	 * @param key the key
	 * @return the integer
	 */
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

	/**
	 * Int to key.
	 *
	 * @param index the index
	 * @return the key code
	 */
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

	/**
	 * Checks if is in move.
	 *
	 * @return true, if is in move
	 */
	public boolean isInMove() {
		return inMove;
	}

	/**
	 * Sets the in move.
	 */
	private void setInMove() {
		inMove = !inMove;
	}

	/**
	 * Reset.
	 */
	public void reset() {
		inMove = false;
		for(int i = 0; i < moveStates.length; i++) {
			moveStates[i] = false;
		}
	}
}
