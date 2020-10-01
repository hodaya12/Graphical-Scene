package unittests;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author ריעות
 *
 */
public class PlaneTest {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		Plane t=new Plane(new Point3D(4,8,2),new Point3D(5,3,9),new Point3D(8,1,4));Vector v=new Vector(39.0,26.0,13.0);
		assertEquals("the get normal of triangle is not good", t.getNormal(new Point3D(4.0,8.0,2.0)), v.normalized());
	}
	/**
	 * Test method for {@link geometries.Plane#findIntersections(vector.Point3D)}.
	 */
	@Test
	public void planeIntersectionPointsTest(){
	    // ============ Equivalence Partitions Tests ==============
		// **** Group: The Ray must be neither orthogonal nor parallel to the plane
		//TC01:Ray intersects the plane(1 points)
		Plane t=new Plane(new Point3D(0,0,0),new Vector(0,0,1));
	    assertEquals("Ray intersects the plane",List.of( new GeoPoint(t,new Point3D(1,1,0))),
				t.findIntersections(new Ray(new Point3D(0,0,-1),new Vector(1,1,1))));
		//TC02:Ray does not intersect the plane(0 points)
		assertEquals("Ray intersects the plane",null,
				t.findIntersections(new Ray(new Point3D(3,0,0),new Vector(6,0,0))));
		
		// =============== Boundary Values Tests ==================
		// **** Group:Ray is parallel to the plane
		//TC11:the ray included in the plane(0 points)
		Plane p4=new Plane(new Point3D(0, 0,0),new Point3D(0,1,0),new Point3D(0,0,5));
		assertEquals("the func of inresections's plane parallel to the plane and include him is fail",null,
				p4.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0,4,0))));
		//TC12:the ray not included in the plane(0 points)
		Plane p3=new Plane(new Point3D(1,0,0),new Point3D(1,1,0),new Point3D(1,1,1));
		assertEquals("the func of inresections's plane parallel to the plane and not include him is fail",null,
				p3.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0,1,0))));
		// **** Group:Ray is orthogonal to the plane
		//TC13:the plane before the p0(0 points)
		Plane p1=new Plane(new Point3D(0,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
		assertEquals("the func of inresections's plane before p0 is fail",null,
				p1.findIntersections(new Ray(new Point3D(0,0,0),new Vector(1,0,0))));
		//TC14:p0 after the plane(1 points)
		Plane p2=new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
		assertEquals("the func of inresections's plane the p0 after the plane is fail",List.of( new GeoPoint(p2,new Point3D(0.4,0.43,0.17))),
				p2.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0.8,0.86,0.34))));
		//TC15:plane in p0(0 points)
		Plane p=new Plane(new Point3D(0,0,0),new Vector(0,0,1));
		assertEquals("the func of inresections's plane in p0 is fail",null,
				p.findIntersections(new Ray(new Point3D(0,-1,0),new Vector(1,2,3))));
		//TC16:ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane(0 points)
		Plane p5=new Plane(new Point3D(0,3,7),new Point3D(0,1,0),new Point3D(0,0,1));
		assertEquals("the func of inresections's plane Ray is neither orthogonal nor parallel to the plane and begins in\r\n" + 
				"the same point which appears as reference point in the plane  is fail",null,
				p5.findIntersections(new Ray(new Point3D(0,0,0),new Vector(-0.5,-0.5,-0.5))));
		//TC17:ray is neither orthogonal nor parallel to and begins at the plane(0 points)
		Plane p6=new Plane(new Point3D(0,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
		assertEquals("the func of inresections's plane Ray is neither orthogonal nor parallel to the plane and begins in\r\n" + 
				"the same point which appears as reference point in the plane  is fail",null,
				p6.findIntersections(new Ray(new Point3D(0,0,0),new Vector(-0.5,-0.5,-0.5))));
		
	}

}
