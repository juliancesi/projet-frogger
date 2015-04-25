package util;

import static org.junit.Assert.*;
import javafx.scene.input.KeyCode;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void keyToInt() {
		int res;
		assertEquals((res = Utils.keyToInt(KeyCode.UP)), 0);
		assertEquals((res = Utils.keyToInt(KeyCode.DOWN)), 1);
		assertEquals((res = Utils.keyToInt(KeyCode.LEFT)), 2);
		assertEquals((res = Utils.keyToInt(KeyCode.RIGHT)), 3);
		assertNull(Utils.keyToInt(KeyCode.A));
	}
}
