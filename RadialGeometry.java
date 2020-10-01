package geometries;

import java.awt.Color;

import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
/**
 * abstract class radial geometry-geometry that round
 * @author hrozenta
 *
 */
public abstract class RadialGeometry extends Geometry 
{
private double _radius;
/**
 * constructor get radius
 * @param r radius
 */
public RadialGeometry (double r)
{
	this(new primitives.Color(Color.BLACK),r);
}
/**
 * constructor get emission light and radius
 * @param emissionLight of the geometry
 * @param r radius
 */
public RadialGeometry (primitives.Color emissionLight, double r)
{
	this(emissionLight,new Material(0, 0, 0) , r);
}
/**
 * constructor get emission light, radius and material
 * @param emissionLight of the geometry
 * @param r radius
 */
public RadialGeometry (primitives.Color emissionLight,Material material, double r)
{
	super(emissionLight,material);
	_radius=r;
}
/**
 * copy constructor get radial geometry
 * @param r radius
 */
public RadialGeometry(RadialGeometry r)
{
	_radius=r.getRadios();
}
/**
 * radius getter
 * @return radius
 */
public double getRadios()
{
	return _radius;
}
/**
 * get point and return the normal in that point
 * @param p point that the function return the normal in it
 */
public Vector getNormal(Point3D p)
{
	return null;
}
/**
 * return a string that describe the radialgeometry
 * @return string
 */
@Override
public String toString()
{
	return "radius:"+_radius+"\n";
}
}
