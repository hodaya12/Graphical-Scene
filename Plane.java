package geometries;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.*;
/**
 * class plane, has point that on the plane and vector normal
 * @author hrozenta
 *
 */
public class Plane extends Geometry
{
private Point3D _p;
private Vector _normal;
/**
 * constructor get 3 points
 * @param p1 point 1
 * @param p2 point 2
 * @param p3 point 3
 */
public Plane(Point3D p1,Point3D p2, Point3D p3)
{
	this(new Color(java.awt.Color.BLACK),new Material(0, 0, 0),p1,p2,p3);
	
}
/**
 * constructor get 3 points and emission
 * @param emission emission of the plane
 * @param p1 point 1
 * @param p2 point 2
 * @param p3 point 3
 */
public Plane(Color emission,Point3D p1,Point3D p2, Point3D p3)
{
	this(emission,new Material(0,0,0),p1,p2,p3);
}
/**
 * constructor get 3 points, emission and material
 * @param material material of the plane
 * @param emission emission of the plane
 * @param p1 point 1
 * @param p2 point 2
 * @param p3 point 3
 */
public Plane(Color emissionLight,Material material,Point3D p1,Point3D p2, Point3D p3)
{
	super(emissionLight,material);
	_p=p1;    
	_normal=(p1.subtract(p2)).crossProduct(p1.subtract(p3)).normalized();
	
}


/**
 * constructor get point and vector normal
 * @param p point
 * @param n normal
 */
public Plane(Point3D p,Vector n)
{
	this(new Color(java.awt.Color.BLACK),new Material(0,0,0),p,n);
}
/**
 * constructor get point, color and normal 
 * @param c emission of the plane
 * @param p point
 * @param n normal
 */
public Plane(Color c,Point3D p,Vector n)
{
	this(c,new Material(0,0,0),p,n);
}
/**
 * constructor get emission, material, point and normal
 * @param emission  emission of the plane
 * @param material material of the plane
 * @param p point
 * @param n normal
 */
public Plane(Color emission,Material material,Point3D p,Vector n)
{
	super(emission,material);
	_p=p;
	_normal=n.normalize();;
}
/**
 * get point and return the normal in that point
 * @param p point that the function return the normal in it
 */
public Vector getNormal(Point3D p)
{
	return _normal;
}
/**
 * return a string that describe the plane
 * @return string
 */
@Override
public String toString()
{
	return "point:"+_p+" normal:"+_normal+"\n";
}
/**
 * find the intersections of the ray with the plane. 
 * @param ray ray that the function find the intersection with it
 * @param max max distance that can be between the ray and the intersection point 
 * @return list of geo point
 */
public List<GeoPoint> findIntersections(Ray ray,double max)
{
    Vector p0Q;
    try {
        p0Q = _p.subtract(ray.getP0());
    } catch (IllegalArgumentException e) {
        return null; // ray starts from point Q - no intersections
    }

    double nv = _normal.dotProduct(ray.getDir());
    if (Util.isZero(nv)) // ray is parallel to the plane - no intersections
        return null;

    double t = Util.alignZero(_normal.dotProduct(p0Q) / nv);
    double tdist=Util.alignZero(max-t);

    if (t <= 0 || tdist<=0) {
        return null;
    }

    GeoPoint geo = new GeoPoint(this, ray.getPoint(t));
    return List.of(geo);

	/*Point3D p0=new Point3D(ray.getP0());
	Vector V=new Vector(ray.getDir());
	if(!_p.equals(p0))
	{
	Vector v=_p.subtract(p0);
	if(_normal.dotProduct(V)!=0)
	{
	double t=_normal.dotProduct(v)/(_normal.dotProduct(V));
	if(t>0)
	{
		V=V.scale(t);
		p0=p0.add(V);
		List<Point3D> l= new LinkedList<Point3D>();
		l.add(p0);
		return l;
	}
	}
	}
	return null;*/
}
/**
 * return point
 * @return
 */
/**
 * point getter
 * @return the point
 */
public Point3D getP()
{
	return _p;
}
/**
 * noraml getter
 * @return normal
 */
public Vector getNormal()
{
	return _normal;
}
/**
 * shininess getter
 * @return the shininess of the plane
 */
@Override
public int getShininess() {
	return this._material.getNShininess();

	
}

}
