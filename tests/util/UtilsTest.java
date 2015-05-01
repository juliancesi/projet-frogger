package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void binaryOperation() {
		int resAND = Utils.binaryOperationAND(1, 1);		
		int resOR = Utils.binaryOperationOR(1, 1);
		int resXOR = Utils.binaryOperationXOR(1, 1);
		
		assertEquals("1&1 operation failed", resAND, 1);
		assertEquals("1|1 operation failed", resOR, 1);
		assertEquals("1^1 operation failed", resXOR, 0);
		
		resAND = Utils.binaryOperationAND(2, 2);		
		resOR = Utils.binaryOperationOR(2, 2);
		resXOR = Utils.binaryOperationXOR(2, 2);
		
		assertEquals("2&1 operation failed", resAND, 2);
		assertEquals("2|1 operation failed", resOR, 2);
		assertEquals("2^1 operation failed", resXOR, 0);
		
		resAND = Utils.binaryOperationAND(3, 1);		
		resOR = Utils.binaryOperationOR(3, 1);
		resXOR = Utils.binaryOperationXOR(3, 1);
		
		assertEquals("3&1 operation failed", resAND, 1);
		assertEquals("3|1 operation failed", resOR, 3);
		assertEquals("3^1 operation failed", resXOR, 2);
		
	}
	
	@Test
	public void isNull() {
		assertTrue("empty string", Utils.isNull(""));
		assertTrue("null string", Utils.isNull(null));
		
	}
}
