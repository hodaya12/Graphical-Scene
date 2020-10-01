package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> _vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane _plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     * 
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex></li>
     *                                  </ul>
     */
    public Polygon(Color emissionLight, Material material,Point3D... vertices) {    	
    	super(emissionLight,material);
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        _vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        _plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3) return; // no need for more tests for a Triangle

        Vector n = _plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }
    /**
     * constructor get list of vertices
     * @param vertices
     */
    public Polygon(Point3D... vertices)
    {
    	this(new Color(java.awt.Color.BLACK),new Material(0, 0, 0),vertices);
    	
    }
    /**
     * constructor get list of vertices and color
     * @param emissionLight color
     * @param vertices
     */
    public Polygon(Color emissionLight,Point3D... vertices) {
    	this(emissionLight,new Material(0, 0, 0),vertices);

	}
    /**
     * get point and return the normal in that point
     * @param p point that the function return the normal in it
     */
	@Override
    public Vector getNormal(Point3D point) {
        return _plane.getNormal().normalize();
    }
    public List<GeoPoint> findIntersections(Ray ray, double max)
    {
    	Plane planeOfThisPolygon=new Plane(_vertices.get(0),_vertices.get(1).subtract(_vertices.get(0)).crossProduct(_vertices.get(2).subtract(_vertices.get(1))));//Create a Plane
		if(planeOfThisPolygon.findIntersections(ray,max)!=null) {
			List<GeoPoint> list=new ArrayList<GeoPoint>(planeOfThisPolygon.findIntersections(ray,max)); //calling the find intersections method of plane
			list.get(0).setGeometry(this);
			List<Vector> vectorList=new ArrayList<Vector>(); //vectorList contains the subtracting of P0 from the polygon vertexes
			for(int i=0;i<_vertices.size();i++) 
				vectorList.add(_vertices.get(i).subtract(ray.getP0()));
			List<Double> resultList=new ArrayList<Double>(); //resultList contains the dotProduct between the vector ray and the normal of each pyramid shell
			for(int i=0;i<_vertices.size();i++)
			{
				if(i+1==_vertices.size())
					resultList.add((vectorList.get(0).crossProduct(vectorList.get(i))).dotProduct(ray.getDir()));
				else
					resultList.add(vectorList.get(i+1).crossProduct(vectorList.get(i)).dotProduct(ray.getDir()));
			}
			boolean flag=false;
			int value=0;
			if(resultList.get(0)>0)//saving the sign of the first result in resultlist
				value=1;
			else
				if(resultList.get(0)<0)
					value=-1;
			for(int i=1;i<resultList.size();i++)//checking all the results in resultlist are in same sign
				if(resultList.get(i)>0&&value==-1||resultList.get(i)<0&&value==1||value==0||resultList.get(i)==0)
					flag=true;
			if(flag==false)
				return list;
			
		}
		return null;
    }
    /**
     * shininess getter
     * @return the shininess of the polygon
     */
	@Override
	public int getShininess() {
		return this._material.getNShininess();
	}
}
