package unittests;
import primitives.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.List;
//import java.awt.List;

import org.junit.Test;

import java.util.ArrayList;

public class SphereTest {

    Sphere sphere = new Sphere(new Point3D(1, 0, 0),1d);

/**
 * Test method for {@link primitives.sphere#getNormal(vector n)}.
 */
    @Test
   public void getNormalTest1() {
        Sphere sp = new Sphere( new Point3D(0, 0, 1),1);
        assertEquals(new Vector(0,0,1),sp.getNormal(new Point3D(0,0,2)));
    }
/**
 *  Test method for {@link primitives.sphere#getNormal(vector n)}.
 */
    @Test
   public void getNormalTest2() {
        Sphere sp = new Sphere(new Point3D(0,0,1),1);
        assertNotEquals(new Vector(0,0,1),sp.getNormal(new Point3D(0,1,1)));
        System.out.println(sp.getNormal(new Point3D(0,1,1)));
    }
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere( new Point3D(1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
       assertEquals("Ray's line out of sphere", null,
                        sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));
        // TC02: Ray starts before and crosses the sphere (2 points)
  	Sphere sph = new Sphere(new Point3D(1,0,0),0.5);
      GeoPoint po1= new GeoPoint(sph,new Point3D(0.5,0,0));
      GeoPoint po2=new GeoPoint(sph, new Point3D(1.5,0,0));
      List<GeoPoint> result1 = sph.findIntersections(new Ray(new Point3D(0, 0, 0),
              new Vector(2,0, 0)));
      assertEquals("Wrong number of points", 2, result1.size());
      if (result1.get(0).getPoint().getX().get() > result1.get(1).getPoint().getX().get())
          result1 = List.of(result1.get(1), result1.get(0));
      assertEquals("the func of Intersections of sphere when have 2 intersections is fail",List.of(po1,po2),result1);
       GeoPoint p1 = new GeoPoint(sphere,new Point3D(0.0651530771650466, 0.355051025721682, 0));
        GeoPoint p2 =  new GeoPoint(sphere,new Point3D(1.53484692283495, 0.844948974278318, 0));
        List<GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getPoint().getX().get() > result.get(1).getPoint().getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        Sphere sp1 = new Sphere(new Point3D(1,0,0),2);
    	assertEquals("Ray starts inside the sphere",List.of( new GeoPoint(sp1,new Point3D(3,0,0))),
    			sp1.findIntersections(new Ray(new Point3D(0,0,0),new Vector(3,0,0))));
        // TC04: Ray starts after the sphere (0 points)
    	Sphere sp = new Sphere(new Point3D(1,0,0),0.5);
    	assertEquals("the func of Intersections of sphere when ray opposite of the direction of the sphere is fail",null,
    			sp.findIntersections(new Ray(new Point3D(0,0,0),new Vector(-0.25,0,0))));
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
    	 Sphere sp3 = new Sphere(new Point3D(2,0,1),Math.sqrt(5));
    	 assertEquals("Ray starts at sphere and goes inside",List.of( new GeoPoint(sp3,new Point3D(3,2,1))),
    			 sp3.findIntersections(new Ray(new Point3D(0,0,0),new Vector(3,2,1))));
    	// TC12: Ray starts at sphere and goes outside (0 points)
    	 Sphere sph3 = new Sphere(new Point3D(-1,0,0),1);
    	 assertEquals("Ray starts at sphere and goes outside",null,
    			 sph3.findIntersections(new Ray(new Point3D(0,0,0),new Vector(1,0,0))));
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
    	 Sphere sphere1 = new Sphere(new Point3D(1, 0, 0), 1d);
    	 p1 = new GeoPoint(sphere1, new Point3D(1,0,1));
         p2 =  new GeoPoint(sphere1,new Point3D(1,0,-1)) ;
         result = sphere1.findIntersections(new Ray(new Point3D(1, 0, 3),new Vector(0, 0, -1)));
         assertEquals("Wrong number of points", 2, result.size());
         assertEquals("Ray crosses sphere", List.of(p2, p1), List.of(result.get(0),result.get(1)));
        // TC14: Ray starts at sphere and goes inside (1 points)
         result = sphere1.findIntersections(new Ray(new Point3D(1, 0, 1),new Vector(0, 0, -1)));
         assertEquals("Wrong number of points", 1, result.size());
         assertEquals("Ray crosses sphere",  new GeoPoint(sphere1,new Point3D(1,0,-1)), result.get(0));
        // TC15: Ray starts inside (1 points)
         result = sphere1.findIntersections(new Ray(new Point3D(1, 0, 0.5),new Vector(0, 0, -1)));
         assertEquals("Wrong number of points", 1, result.size());
         assertEquals("Ray crosses sphere",  new GeoPoint(sphere1,new Point3D(1,0,-1)), result.get(0));
      
        // TC16: Ray starts at the center (1 points)
         Sphere sp5 = new Sphere(new Point3D(0,0,0),1);
         assertEquals("Ray starts at the center",List.of( new GeoPoint(sp5,new Point3D(0,0,1))),
    			 sp5.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0,0,1))));
        // TC17: Ray starts at sphere and goes outside (0 points)
         Sphere sp6 = new Sphere(new Point3D(-1,0,0),1);
         assertEquals("Ray starts at sphere and goes outside",null,
    			 sp6.findIntersections(new Ray(new Point3D(0,0,0),new Vector(1,0,0))));
        // TC18: Ray starts after sphere (0 points)
         Sphere sp7 = new Sphere(new Point3D(-1,0,0),0.75);
         assertEquals("Ray starts after sphere",null,
    			 sp7.findIntersections(new Ray(new Point3D(0,0,0),new Vector(1,0,0))));
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
         assertEquals("Ray crosses sphere", null, sphere1.findIntersections(new Ray(new Point3D(-1,0,1),new Vector(1, 0, 0))));
        // TC20: Ray starts at the tangent point
         Sphere sp9 = new Sphere(new Point3D(1,0,0),1);
         assertEquals("Ray starts at the tangent point",null,
    			 sp9.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0,1,0))));
        // TC21: Ray starts after the tangent point
         Sphere sp10 = new Sphere(new Point3D(0,2,0),Math.sqrt(1.3));
         assertEquals("Ray starts after the tangent point",null,
    			 sp10.findIntersections(new Ray(new Point3D(0,0,0),new Vector(-0.42,-1.84,-0.95))));
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
         Sphere sp11 = new Sphere(new Point3D(2,0,0),1);
         assertEquals("Ray starts after the tangent point",null,
    			 sp11.findIntersections(new Ray(new Point3D(0,0,0),new Vector(0,0,1))));
    }


    
}
