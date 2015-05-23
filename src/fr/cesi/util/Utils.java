package fr.cesi.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	
	public static URL loadResource(String path) {
		ClassLoader classLoader = Utils.class.getClassLoader();
		return classLoader.getResource(path);
	}
	
	public static InputStream readFile(String path) {
		Path url = Paths.get(path);
		try(InputStream reader = Files.newInputStream(url)) {
			return reader;
		} catch(IOException ioE) {
			System.err.printf("cannot read the specified file : \"%1$s\"", url).println();
		}
		return null;
	}

	public static void serialize(Object o, String name) {
		ObjectOutputStream outputStream = null;
		try(FileOutputStream file = new FileOutputStream(name)) {
			outputStream = new ObjectOutputStream(file);
			outputStream.writeObject(o);
			outputStream.flush();
		} catch(IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
	
	public static <T> T deserialize(Class<T> bean, String path) throws IOException {
		T newObject = null;
		
		ObjectInputStream inputStream = null;
		try(FileInputStream file = new FileInputStream(path)) {
			inputStream = new ObjectInputStream(file);
			newObject = (T) inputStream.readObject();
		} catch(IOException ioEx) {
//			ioEx.printStackTrace();
			System.err.printf("file not found : %1$s", ioEx);
		} catch (ClassNotFoundException classNotFoundEx) {
			classNotFoundEx.printStackTrace();
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			} catch (IOException ioCloseEx) {
//				ioCloseEx.printStackTrace();
				System.err.printf("file not found : %1$s", ioCloseEx);
			}

		}
		
		return newObject;
	}
	
}
