/**
 * 
 */

package unittests;
import geometries.*;
import primitives.*;

import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.util.ArrayList;

//import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author ריעות
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		//=============Equivalence Partition Tests=================
		//TC01:There is a simple single test here
		Tube tube=new Tube ( new Ray(new Point3D (0,0,1), new Vector(0,1,0)),1.0);
		assertEquals("Bad normal to tube", new Vector (0,0,1),tube.getNormal(new Point3D(0,0.5,2)));
	}
	/**
	 * Test method for {@link geometries.Tube#findIntersections(primitives.Point3D)}.
	 */
	@Test
	public void testFindIntersections()
	{
		Tube tube=new Tube(new Ray(new Point3D (0,0,1), new Vector(0,1,0)),1.0);
	//asserEquals("bad intersections to tube",new ArrayList()<Point3D>,tube.findIntersections());
	}
}
	

