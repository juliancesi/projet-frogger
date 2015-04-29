package collisions;

import static org.junit.Assert.*;
import graphic.bean.RectangleTile;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import org.junit.Before;
import org.junit.Test;

public class CollisionsEngineTest {

	private Rectangle rec1;
	private Rectangle rec2;
	private RectangleTile rec1Property;
	private RectangleTile rec2Property;
	private CollisionsEngine colEng;
	
	@Before
	public void prepareMap() {
		rec1 = new Rectangle(0, 0, 50, 50);
		rec2 = new Rectangle(0, 0, 50, 50);
		
		rec1Property = new RectangleTile();
		rec1Property.relocate(0, 0);
		rec1Property.setWidth(50);
		rec1Property.setHeight(50);
		rec1Property.setCollisionsProperty(1);
		
		rec2Property = new RectangleTile();
		rec2Property.relocate(0, 0);
		rec2Property.setWidth(50);
		rec2Property.setHeight(50);
		rec2Property.setCollisionsProperty(1);
		
		Map<String, Node> list = new HashMap<String, Node>();
		list.put("rec2Property", rec2Property);
		colEng = new CollisionsEngine(rec1Property, list);
	}
	
	@Test
	public void intersectTheory() {
		assertTrue(rec1.getBoundsInParent().intersects(rec2.getBoundsInParent()));
		
		rec1.setLayoutX(55);
		assertTrue(!rec1.getBoundsInParent().intersects(rec2.getBoundsInParent()));
	}
	
	private boolean intersect(Node a, Node b) {
		return a.getBoundsInParent().intersects(b.getBoundsInParent());
	}
	
	@Test
	public void checkNodeCollisionsOK_KO() {
		assertTrue(intersect(rec1Property, rec2Property));
		
		assertEquals(colEng.checkNodeCollisions(), rec2Property);
		
		rec1Property.setLayoutX(55);
		assertNull(colEng.checkNodeCollisions());
	}
	
	@Test
	public void checkCollisionsFutureOK_KO() {
		assertEquals(colEng.checkCollisionsFuture(new Double[] {0.0, 0.0}), rec2Property);
		
		assertNull(colEng.checkCollisionsFuture(new Double[] {55.0, 0.0}));
	}

	@Test
	public void checkCollisionsFutureTranslateOK_KO() {
		rec2Property.setTranslateY(55);
		assertEquals(colEng.checkCollisionsFuture(new Double[] {0.0, 55.0}), rec2Property);
		
		assertNull(colEng.checkCollisionsFuture(new Double[] {55.0, 0.0}));
	}
	
}
