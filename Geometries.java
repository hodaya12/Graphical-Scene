package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
/**
 * class Geometries has list of intersectables
 * @author hrozenta
 *
 */
public class Geometries implements Intersectable{
 
	private List<Intersectable> listOfGeometry;
	/**
	 * listOfGeometry getter
	 * @return listOfGeometry
	 */
	public List<Intersectable> getListOfGeometry()
	{
		return listOfGeometry;
	}
	/**
	 * constructor that initailize a new list
	 */
	public Geometries()
	{
		listOfGeometry=new LinkedList<Intersectable>();
	}
	/**
	 * constructor that get geometries and add them to the listOfGeometry
	 * @param geometries geomtries
	 */
	public Geometries(Intersectable... geometries)
	{
		listOfGeometry=new LinkedList<Intersectable>();
		for(Intersectable g:geometries)
			listOfGeometry.add(g);
	}
	/**
	 * get geometries and add them to the listOfGeometry
	 * @param geometries geometries
	 */
	public void add(Intersectable... geometries)
	{
		for(Intersectable g:geometries)
		     listOfGeometry.add(g);
	}
	/**
	 * find the intersections of the ray with the geometries in listOfGeometry. 
	 * @param ray ray that the function find the intersection with it
	 * @param max max distance that can be between the ray and the intersection point 
	 * @return list of geo point
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray,double max) {
	List<GeoPoint> listPointIntersections=new LinkedList<GeoPoint>();
	for(int i=0;i<listOfGeometry.size();i++)
	{
		if(listOfGeometry.get(i).findIntersections(ray,max)!=null)
			listPointIntersections.addAll(listOfGeometry.get(i).findIntersections(ray,max));
	}
	if(listPointIntersections.size()==0)
		return null;
	return listPointIntersections;
	
	}
}
