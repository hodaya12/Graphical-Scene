/**
 * 
 */
package unittests;
import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import primitives.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;

import org.junit.Test;
/**
import java.util.List;
 * @author ריעות
 *
 */
public class TriangleTest {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Triangle t=new Triangle(new Point3D(4,8,2),new Point3D(5,3,9),new Point3D(8,1,4));
		Vector v=new Vector(-39.0,-26.0,-13.0);
		assertEquals("the get normal of triangle is not good", t.getNormal(new Point3D(4.0,8.0,2.0)),v.normalize() );
	}
	/**
	 * 	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Point3D)}.

	 */
	@Test
	public void TriangleIntersectionPointsTest(){
		Triangle t=new Triangle(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
		// ============ Equivalence Partitions Tests ==============
		//TC01:Inside triangle(1 points)
		Triangle triangle=new Triangle(new Point3D(1,1,0),new Point3D(2,1,0),new Point3D(2,0,0));
		List<GeoPoint> result=triangle.findIntersections(new Ray(new Point3D(1.5,0.75,-1),new Vector(0,0,1)));
		assertEquals("Wrong number of points",1,result.size());
		assertEquals("Wrong ray's direction",new GeoPoint(triangle,new Point3D(1.5,0.75,0)),result.get(0));
		//TC02:Outside against edge(0 points)
		assertEquals("the func of inresections's triangle outside against edge is fail",null,
				t.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0.5,0.5,-3))));
		//TC03:Outside against vertex(0 points)
		
		assertEquals("the func of inresections's triangle outside against vertex  is fail",null,
				t.findIntersections(new Ray(new Point3D(0,0,0),new Vector(-1,0,0))));
		// =============== Boundary Values Tests ==================
		//TC11:On edge(0 points)
		assertEquals("the func of inresections's triangle outside on edge is fail",null,
				t.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0.5,0,0))));
		//TC12:In vertex(1 points)
		assertEquals("Wrong ray",null, triangle.findIntersections(new Ray(new Point3D(2,0,-1),new Vector(0,0,1))));	
		//TC13:On edge's continuation(0 points)
		assertEquals("the func of inresections's triangle on edge's continuation is fail",null,
				t.findIntersections(new Ray(new Point3D(0,0,0),new Vector(3,-2,0))));
		
		}

}
