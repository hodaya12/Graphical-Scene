package geometries;
import java.util.List;
import primitives.*;
/**
 * interface intersectable- has the function findintersections
 * @author hrozenta
 *
 */
public interface Intersectable {
	List<GeoPoint> findIntersections(Ray ray,double max);
	/**
	 * default implementation, find the intersections of the ray with the geometry. 
	 * @param ray ray that the function find the intersection with it
	 * @return list of geo point
	 */
	default List<GeoPoint> findIntersections(Ray ray)
	{
		return findIntersections(ray,Double.POSITIVE_INFINITY);
	}
	/**
	 * static internal Auxiliary class
	 * @authorÔ‰Ô„È‰ ¯ÈÚÂ˙
	 *
	 */
	public static class GeoPoint {
	    private Geometry geometry;
	    private Point3D point;
	    /**
	     * Geometry setter
	     * @param g geometry
	     */
	    public void setGeometry(Geometry g)
	    {
	    	geometry=g;
	    }
	    /**
	     * point setter
	     * @param p point
	     */
	    public void setPoint(Point3D p)
	    {
	    	point=p;
	    }
	    /**
	     * geometry getter
	     * @return geometry
	     */
	    public Geometry getGeometry()
	    {
	    	return geometry;
	    }
	    /**
	     * point getter
	     * @return point
	     */
	    public Point3D getPoint()
	    {
	    	return point;
	    }
	    /**
	     * constructor getting geometry and point
	     * @param g geometry
	     * @param p point
	     */
	    public GeoPoint(Geometry g,Point3D p)
	    {
	    	geometry=g;
	    	point=p;
	    }
	    /**
	     * check if o and the object are equal
	     * @param o object that is checked if it's equal to the other object
	     * @return true if the two object are equal and else return false
	     */
	    public boolean equals(Object o)
	    {
	    	  if (this == o) return true;
	          if (o == null) return false;
	          if (!(o instanceof GeoPoint)) return false;
	          	GeoPoint n = (GeoPoint)o;
	          return geometry.equals(n.geometry)&&point.equals(n.point);
	    	
	    }
	}
 
}
