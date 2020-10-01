package unittests;

import primitives.Util;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class VectorTest {

/**
 *  Test method for {@link primitives.Vector#add(primitives.Vector)}.
 */
    @Test
   public   void testAdd() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, 0.0);

        v1 = v1.add(v2);
        System.out.println("v1 = " + v1.toString());
        assertEquals(new Vector(0.0,0.0,1.0),v1);
        //assertTrue(v1.equals(new Vector(0.0,0.0,1.0)));

        v2 = v2.add(v1);
        assertEquals(new Vector(-1.0,-1.0,1.0),v2);


    }

    /**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
	     Vector v1=new Vector(5,6,8);
	     Vector v2=new Vector(-1,-2,-3);
	     assertEquals("the func of subtract in vector is not correct",v1.subtract(v2),new Vector(6,8,11));
	}

    /**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v2=new Vector(1,2,3);
		assertEquals("the mult in scalar in vector not work good", v2.scale(9),new Vector(9,18,27));
	}
    /**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		Vector v= new Vector(2,3,4);
		assertEquals("the func of length doesn't work good",v.lengthSquared(),29,0);
	}
    /**
     *Test method for {@link primitives.Vector#length()}.
     */

@Test 
public void testLength() {
	Vector v= new Vector(2,3,4);
	assertEquals("the func of length doesn't work good",v.length(),Math.sqrt(29),0);
    }
/**
 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
 */
@Test
public void testDotProduct() {
	 Vector v1=new Vector(5,6,8);
     Vector v2=new Vector(1,2,3);
     assertEquals("the func of dot prodact in vector is not correct",v1.dotProduct(v2),41,0);
}
/**
 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
 */
@Test
public void testCrossProduct() {
	Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);

    // ============ Equivalence Partitions Tests ==============
    Vector v3 = new Vector(0, 3, -2);
    Vector vr = v1.crossProduct(v3);

    // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
    assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

    // Test cross-product result orthogonality to its operands
    assertTrue("crossProduct() result is not orthogonal to 1st operand", Util.isZero(vr.dotProduct(v1)));
    assertTrue("crossProduct() result is not orthogonal to 2nd operand",Util. isZero(vr.dotProduct(v3)));

    // =============== Boundary Values Tests ==================
    // test zero vector from cross-product of co-lined vectors
    try {
        v1.crossProduct(v2);
        fail("crossProduct() for parallel vectors does not throw an exception");
    } catch (Exception e) { }}
/**
 * Test method for {@link primitives.Vector#normalized()}.
 */
@Test
public void testNormalized() {
	Vector v= new Vector(2,3,4);
	
	assertEquals("the func of normalized doesn't work good",v.normalized(),new Vector(2.0/Math.sqrt(29),3.0/Math.sqrt(29),4.0/Math.sqrt(29)));
}
/**
 * Test method for {@link primitives.Vector#normalize()}.
 */
    @Test
   public void testNormalize()
    {
        /**
         *  Test takin
         */
        Vector v= new Vector(3.5,-5,10);
        v.normalize();
        assertEquals(1, v.length(),1e-10);
        /**
         * test lo takin
         */
        try {
            v = new Vector(0, 0, 0);
            fail("Didn't throw illegal argumrnt exception for zero paarmeters!");
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
        v = new Vector(1.0,-1.0,0.0);
        try{
            v.normalize();
        } catch(ArithmeticException e) {
            assertTrue(true);
        }
    }
    
}