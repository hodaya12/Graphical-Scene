package geometries;


import java.util.ArrayList;
import java.util.List;

import primitives.*;

/**
 * class triangle has 3 points
 * @author hrozenta
 *
 */
public class Triangle extends Polygon
{
	/**
	 * constructor get 3 point
	 * @param p1 point 1
	 * @param p2 point 2
	 * @param p3 point 3
	 */
public Triangle(Point3D p1,Point3D p2, Point3D p3)
{
	this(new Color(java.awt.Color.BLACK),new Material(0, 0, 0),p1,p2,p3);
}
/**
 * constructor get 3 point and color
 * @param p1 point 1
 * @param p2 point 2
  * @param p3 point 3
  * @param emissionLight of the triangle
	 */
public Triangle(Color emissionLight,Point3D p1,Point3D p2, Point3D p3)
{
	this(emissionLight,new Material(0, 0, 0),p1,p2, p3);
	
}
/**
 * constructor get 3 point,color and material
 * @param emissionLight of the triangle
 * @param material of the triangle
 * @param p1 point 1
 * @param p2 point 2
 * @param p3 point 3
 */
public Triangle(Color emissionLight,Material material,Point3D p1,Point3D p2, Point3D p3)
{
	super(emissionLight,material,p1,p2, p3);
	
}
/**
 * get point and return the normal in that point
 * @param p point that the function return the normal in it
 */
public Vector getNormal(Point3D p)
{
	return _plane.getNormal().normalize().scale(-1);
}
/**
 * return a string that describe the triangle
 * @return string
 */
@Override
public String toString()
{
	return super.toString();
}
/**
 * find the intersections of the ray with the triangle. 
 * @param ray ray that the function find the intersection with it
 * @param max max distance that can be between the ray and the intersection point 
 * @return list of geo point
 */
@Override
public List<GeoPoint> findIntersections(Ray ray,double max)
{
	if(super.findIntersections(ray,max)!=null) {
		List<GeoPoint> l= new ArrayList<GeoPoint>(super.findIntersections(ray,max));
		l.get(0).setGeometry(this);
	return l;
	}
	return null;
}

}

