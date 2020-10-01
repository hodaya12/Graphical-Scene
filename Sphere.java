package geometries;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.*;
/**
 * class sphere, has a center point and radius
 * @author hrozenta
 *
 */
public class Sphere extends RadialGeometry
{
private Point3D _center;
/**
 * get point and return the normal in that point
 * @param p point that the function return the normal in it
 */
public Vector getNormal(Point3D p)
{
	Vector n=new Vector(p.subtract(_center));
	n.normalize();
	return n;
}
/**
 * constructor get point and radius
 * @param p point
 * @param rad radius
 */
public Sphere(Point3D p,double rad)
{
	this(new Color(java.awt.Color.BLACK),p,rad);
	
}
/**
 * constructor get point,radius and emissionlight
 * @param emissionLight of the sphere
 * @param p center point
 * @param rad radius
 */
public Sphere(Color emissionLight,Point3D p,double rad)
{
	this(emissionLight,new Material(0, 0, 0),p,rad);

}
/**
 * constructor get emissionLight,material, center point and radius
 * @param emissionLight of the sphere
 * @param material of the sphere
 * @param p center point
 * @param rad radius
 */
public Sphere(Color emissionLight,Material material,Point3D p,double rad)
{
	super(emissionLight,material,rad);
	_center=p;
}
/**
 * center point getter
 * @return the center point
 */
public Point3D getCenter()
{
	return _center;
}
/**
 * return a string that describe the sphere
 * @return string
 */
@Override
public String toString()
{
	return "center:"+_center+super.toString();
}
/**
 * find the intersections of the ray with the sphere. 
 * @param ray ray that the function find the intersection with it
 * @param max max distance that can be between the ray and the intersection point 
 * @return list of geo point
 */
public List<GeoPoint> findIntersections(Ray ray,double max)
{
	double d=0;double tm=0;
	if(!_center.equals(ray.getP0()))
	{
		Vector L =new Vector(_center.subtract(ray.getP0()));
		tm=L.dotProduct(ray.getDir());
		d=Math.sqrt((L.lengthSquared()-(tm*tm)));
	}
	if(d>this.getRadios())
		return null;
	double th=Math.sqrt(this.getRadios()*this.getRadios()-d*d);
	if(th==0)
		return null;
	double t1=tm+th;
	double t2=tm-th;
double t1dist=Util.alignZero(max-t1);
double t2dist=Util.alignZero(max-t2);
	List<GeoPoint> l=new ArrayList<GeoPoint>();
	if(t1>0 && t1dist>0)
		l.add(new GeoPoint(this,ray.getP0().add( ray.getDir().scale(t1))));
	if(t2>0 && t2dist>0)
		l.add(new GeoPoint(this,ray.getP0().add( ray.getDir().scale(t2))));
	if(l.size()==0)
			return null;
	return l;
	
}
/**
 * shininess getter
 * @return the shininess of the sphere
 */
@Override
public int getShininess() {
	return this._material.getNShininess();
}


}
