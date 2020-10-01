package geometries;

import java.util.List;

import primitives.*;

/**
 * class Cylinder  
 * @author hrozenta
 *
 */
public class Cylinder extends Tube
{
	private double _height;
	/**
	 * get point and return the normal in that point
	 * @param p point that the function return the normal in it
	 */
	public Vector getNormal(Point3D p)
	{
		return null;
	  // Vector v=new Vector(super.getAxisRay().getP0().subtract(p)).normalize();
	   
	}
	/**
	 * constructor get height, radius and ray
	 * @param h height of the cylinder
	 * @param r axis ray of the cylinder
	 * @param rad radius of the cylinder
	 */
	public Cylinder(double h,Ray r, double rad)
	{
		this(new Color(java.awt.Color.BLACK),h,r,rad);
	}
	/**
	 * constructor get emission light,height, radius and ray
	 * @param emissionLight color of the cylinder
	 * @param h height of the cylinder
	 * @param r axis ray of the cylinder
	 * @param rad radius of the cylinder
	 */
	public Cylinder(Color emissionLight,double h,Ray r, double rad)
	{
		this(emissionLight,new Material(0,0,0), h,r, rad);
	
	}
	/**
	 * constructor get height, radius,emission light and ray
	 * @param emissionLight color of the cylinder
	 * @param h height of the cylinder
	 * @param r axis ray of the cylinder
	 * @param rad radius of the cylinder
	 * @param material material of the cylinder
	 */
	public Cylinder(Color emissionLight,Material material,double h,Ray r, double rad)
	{
		super(emissionLight,material,rad,r);
		_height=h;
		
	}
	/**
	 * height getter
	 * @return the height of the cylinder
	 */
	public double getHeight()
	{
	   return _height;
	}
	/**
	 * return a string that describe the cylinder
	 * @return string
	 */
	@Override
	public String toString()
	{
		return "height"+_height+super.toString();
	}
	/**
	 * find the intersections of the ray with the cylinder. 
	 * @param ray ray that the function find the intersection with it
	 * @return list of geo point
	 */
	public List<GeoPoint> findIntersections(Ray ray)
	{
		return null;
	}
}
