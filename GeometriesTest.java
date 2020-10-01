package unittests;
import geometries.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.List;
//import java.awt.List;

import org.junit.Test;

public class GeometriesTest {
/**
 * Test method for {@link geometries.Geometry#findIntersections()}.
 */
	@Test
	public void testFindIntsersections() {
		Geometries g=new Geometries();
		assertEquals("if the list is null the func is not work good",0, g.getListOfGeometry().size());
		Triangle t=new Triangle(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
		g.add(t);	
		assertEquals("if haven't intesection in list the func is not work good ",null,g.findIntersections(new Ray(new Point3D(0,0,0),new Vector(3,-2,0))));
		Plane p2=new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
		g.add(p2);
		assertEquals("if have one intesection in list the func is not work good",1,(g.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0.43,-1.19,1.76)))).size());	
		Plane p1=new Plane(new Point3D(-1,0,0),new Point3D(0,-1,0),new Point3D(0,0,-1));
		g.add(p1);
		Plane p3=new Plane(new Point3D(0,0,1),new Vector(0,0,3));
		g.add(p3);
		assertEquals("if have some intesection in list but no everyone the func is not work good",2,(g.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0.43,-1.19,1.76)))).size());
		Geometries g1=new Geometries();
	    g1.add(p2);
		g1.add(t);
		assertEquals("if everyone they intesection in list the func is not work good",2,(g1.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0.5,0.5,0.5)))).size());
	}

}
