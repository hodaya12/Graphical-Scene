package geometries;
import geometries.Intersectable.GeoPoint;
import primitives.*;
/**
 * class Geometry has material and emission color
 * @author hrozenta
 *
 */
public abstract class Geometry implements Intersectable
{
	protected Material _material;
	protected Color _emmission ;
	public abstract Vector getNormal(Point3D p) ;
	public Color getEmmission() {
		return _emmission;
	}
	/**
	 * material getter
	 * @return the material of the geometry
	 */
	public Material getMaterial() {
		return _material;
	}
	/**
	 * constructor accepting emission color and material
	 * @param emmission color of the geometry
	 * @param material material of the geometry
	 */
	public Geometry(Color emmission, Material material) {
		_emmission=emmission;
		_material=material;
	}
	/**
	 * constructor accepting color emission
	 * @param emmission color of the geometry
	 */
	public Geometry(Color emmission) {
		this(emmission, new Material(0, 0, 0)); 
		
	}
	/**
	 * constructor that initializing the emission color to black
	 */
	public Geometry() {
		_emmission=Color.BLACK;
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
    if (!(o instanceof Geometry)) return false;
    Geometry n = (Geometry)o;
    return _emmission.equals(n.getEmmission());

    }
	/**
	 * abstract function-shininess getter
	 * @return the shininess of the geometry
	 */
	public abstract int getShininess();
	
}
