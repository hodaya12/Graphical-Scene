package geometries;

import java.util.List;

import primitives.*;
/**
 * class tube
 * @author hrozenta
 *
 */
public class Tube extends RadialGeometry
{
	private Ray _axisRay;

	/**
	 * get point and return the normal in that point
	 * @param p point that the function return the normal in it
	 */
                @Override
                public Vector getNormal(Point3D p) {
      Point3D p0 = _axisRay.getP0();
      Vector v = _axisRay.getDir();
      //t = v (P – P0)
      double t = p.subtract(p0).dotProduct(v);
                  // O = P0 + tv
                  Point3D o=null;
                  if (!Util.isZero(t))// if it's close to 0, we'll get ZERO vector exception
                    o = p0.add(v.scale(t));
                  Vector n = p.subtract(o).normalize();
      return n;
                }
                /**
                 * constructor get emission light, material, radius and ray
                 * @param emissionLight color of the tube
                 * @param _material material of the tube
                 * @param _radius radius of the tube
                 * @param _ray axis ray
                 */
                public Tube(Color emissionLight, Material _material, double _radius, Ray _ray) {
                    super(Color.BLACK, _radius);
                    this._material = _material;
                    this._axisRay = new Ray(_ray.getP0(),_ray.getDir());

                }
                
	/**
	 * constructor get ray,emission light and radius
	 * @param emissionLight color of the tube
	 * @param r axis ray
	 * @param rad radius
	 */
	public Tube(Color emissionLight, Ray r, double rad)
	{
		this(emissionLight,new Material(0, 0, 0),rad,r);
		
	}
	/**
	 * constructor get ray and radius
	 * @param r axis ray
	 * @param rad radius
	 */
	public Tube(Ray r, double rad)
	{
		this(new Color(java.awt.Color.BLACK),new Material(0, 0, 0),rad,r);
		
	}
	/**
	 * axis ray getter
	 * @return axis ray
	 */
	public Ray getAxisRay()
	{
		return _axisRay;
	}
	/**
	 * return a string that describe the tube
	 * @return string
	 */
	@Override
	public String toString()
	{
		return "ray:"+_axisRay+super.toString();
	}
	/**
	 * find the intersections of the ray with the tube. 
	 * @param ray ray that the function find the intersection with it
	 * @param max max distance that can be between the ray and the intersection point 
	 * @return list of geo point
	 */
	public List<GeoPoint> findIntersections(Ray ray,double max)
	{
		return null;
	}
	/**
	 * shininess getter
	 * @return the shininess of the tube
	 */
	@Override
	public int getShininess() {
		return this._material.getNShininess();
	}
}
