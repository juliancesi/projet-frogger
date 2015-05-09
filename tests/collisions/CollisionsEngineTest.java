package collisions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import graphic.bean.RectangleTile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
		rec1.setId("rec1");
		rec2 = new Rectangle(0, 0, 50, 50);
		rec2.setId("rec2");

		rec1Property = new RectangleTile();
		rec1Property.relocate(0, 0);
		rec1Property.setWidth(50);
		rec1Property.setHeight(50);
		rec1Property.setCollisionsProperty(1);
		rec1Property.setId("rec1Property");
		
		rec2Property = new RectangleTile();
		rec2Property.relocate(0, 0);
		rec2Property.setWidth(50);
		rec2Property.setHeight(50);
		rec2Property.setCollisionsProperty(1);
		rec2Property.setId("rec2Property");

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

	@Test
	public void addSortedNodes() {
		Node node1 = new Rectangle(0, 0, 10, 10); // minX:0 / maxX:10 + r:5 / minY:0 / maxY:10 + 5
		node1.setId("node1");
		int minX1 = 0; int maxX1 = 2;
		int minY1 = 0; int maxY1 = 2;
		
		Node node2 = new Rectangle(50, 50, 10, 10); // minX:50 - r:5 / maxX:60 + r:5 / minY:50 - r:5 / maxY:60 + r:5
		node2.setId("node2");
		int minX2 = 4; int maxX2 = 6;
		int minY2 = 4; int maxY2 = 6;
		
		Node node3 = new Rectangle(65, 65, 10, 10); // minX:65 - r:5 / maxX:75 + r:5 / minY:65 - r:5 / maxY:75 + r:5
		node3.setId("node3");
		int minX3 = 6; int maxX3 = 8;
		int minY3 = 6; int maxY3 = 8;
		
		Set<Node> s0 = new HashSet<Node>();
		s0.add(node1);
		
		Set<Node> s1 = new HashSet<Node>();
		s1.add(node2);
		
		Set<Node> s2 = new HashSet<Node>();
		s2.add(node2);
		s2.add(node3);
		
		Set<Node> s3 = new HashSet<Node>();
		s3.add(node3);
		
		Map<Integer, Set<Node>> X012 = new HashMap<Integer, Set<Node>>();
		X012.put(0, s0);
		X012.put(1, s0);
		X012.put(2, s0);
		
		Map<Integer, Set<Node>> X45 = new HashMap<Integer, Set<Node>>();
		X45.put(4, s1);
		X45.put(5, s1);
		X45.put(6, s1);
		
		Map<Integer, Set<Node>> X6 = new HashMap<Integer, Set<Node>>();
		X6.put(4, s1);
		X6.put(5, s1);
		X6.put(6, s2);
		X6.put(7, s3);
		X6.put(8, s3);
		
		Map<Integer, Set<Node>> X78 = new HashMap<Integer, Set<Node>>();
		X78.put(6, s3);
		X78.put(7, s3);
		X78.put(8, s3);
		
		Map<Integer, Map<Integer, Set<Node>>> compareList = new HashMap<Integer, Map<Integer,Set<Node>>>();
		compareList.put(0, X012);
		compareList.put(1, X012);
		compareList.put(2, X012);
		compareList.put(4, X45);
		compareList.put(5, X45);
		compareList.put(6, X6);
		compareList.put(7, X78);
		compareList.put(8, X78);

		////////////////////////////////////////
		
		colEng.addSortedNode(minX1, maxX1, minY1, maxY1, node1);
		colEng.addSortedNode(minX2, maxX2, minY2, maxY2, node2);
		colEng.addSortedNode(minX3, maxX3, minY3, maxY3, node3);
		
		Map<Integer, Map<Integer, Set<Node>>> listRet = colEng.getSortedNodesList();
		
		assertEquals("Taille totale: 8", compareList.size(), listRet.size());
		assertEquals("Taille en x=0", compareList.get(0).size(), listRet.get(0).size());
		assertEquals("Taille en x=6 et y=6", compareList.get(6).get(6).size(), listRet.get(6).get(6).size());

		Set<Node> compListX4Y4 = compareList.get(4).get(4);
		Set<Node> retListX4Y4 = colEng.getNodesList(4, 4);
		
		Iterator<Node> expectedIt = compListX4Y4.iterator();
		Node expectedNode = expectedIt.next();
		Iterator<Node> realIt = retListX4Y4.iterator();
		Node realNode = realIt.next();

		assertEquals(expectedNode, realNode);
		
	}
	
}
