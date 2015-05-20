package fr.cesi.util;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Utils {

	public static int binaryOperationAND(int... arg) {
		int res = 0;
		for(int i = 0; i < arg.length - 1; i++) {
			res += arg[i] & arg[i + 1];
		}
		return res;
	}

	public static int binaryOperationOR(int... arg) {
		int res = 0;
		for(int i = 0; i < arg.length - 1; i++) {
			res += arg[i] | arg[i + 1];
		}
		return res;
	}

	public static int binaryOperationXOR(int... arg) {
		int res = 0;
		for(int i = 0; i < arg.length - 1; i++) {
			res += arg[i] ^ arg[i + 1];
		}
		return res;
	}

	public static <T> T nodeToShape(Node node, Class<T> classTo) {
		double x = node.getBoundsInParent().getMinX();
		double y = node.getBoundsInParent().getMinY();
		double w = node.getBoundsInParent().getWidth();
		double h = node.getBoundsInParent().getHeight();

		double radius = (w + h) / 2 / 2;

		Shape shape = null;
		if(classTo == Rectangle.class) {
			shape = new Rectangle(x, y, w, h);
		}
		if(classTo == Circle.class) {
			shape =new Circle(x + radius, y + radius, radius);
		}

		if(shape != null) {
			shape.setId(node.getId());
		}
		return (T) shape;
	}

	public static Integer tryParseToInt(String s) {
		Integer parsed = null;
		try {
			parsed = Integer.parseInt(s); 
		} catch(NumberFormatException integerEx) {
			integerEx.printStackTrace();
		}

		return parsed;
	}

	public static Double tryParseToDouble(String s) {
		Double parsed = null;
		try {
			parsed = Double.parseDouble(s); 
		} catch(NumberFormatException doubleEx) {
			doubleEx.printStackTrace();
		}
		
		return parsed;
	}
	
	public static boolean isNull(String s) {
		return s == null || s.isEmpty();
	}
	
	public static boolean isNull(Integer i) {
		return i == null || i == 0;
	}

}
