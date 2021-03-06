package fr.cesi.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

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
		assertTrue("null string", Utils.isNull((String) null));
	}
	
	@Test
	public void serialize() {
		String test = "test";
		Utils.serialize(test, "serialize.test");
		File file = new File("serialize.test");
		assertTrue(file.exists());
	}
	
	@Test
	public void deserialize() {
		String test = null;
		try {
			test = Utils.deserialize(String.class, "serialize.test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(test.toString(), "test");
	}
}
