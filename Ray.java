package primitives;

public class Ray
{
	 public static final double DELTA = 0.1;
	 private Point3D _p0;
	 private Vector _dir;
	 /**
	  * constractor get point3d and vector
	  * @param p0
	  * @param dir vector the direction 
	  */
public Ray(Point3D p0,Vector dir)
{	
	_p0=p0;
	_dir=dir.normalized();
}
/**
 * getter the p0
 * return p0
 * @return
 */
public Point3D getP0()
{
	return _p0;
}
/**
 * getter the dir
 * return direction
 * @return
 */
public Vector getDir()
{
	return _dir;
}
/**
 * equals between 2 rays
 */
@Override
public boolean equals(Object o)
{
	  if (this == o) return true;
      if (o == null) return false;
if (!(o instanceof Ray)) return false;
Ray n = (Ray)o;
return _p0.equals(n._p0) &&
_dir.equals(n._dir);
}
/**
 * tostring the p0 and dir of ray
 */
@Override
public String toString()
{
	return "p0:"+_p0+ " direction:"+_dir+"\n";
}
/**
 * the func mult the dir at t and add this to p0
 * @param t scalar to mult
 * @return the new point
 */
public Point3D getPoint(double t)
{
	Point3D p=_p0.add( _dir.scale(t));
	return p;
}
/**
 * Constructor create ray with shift of delta
 * @param p point
 * @param dir the vector direction
 * @param n normal
 */
public Ray(	Point3D p,Vector dir,Vector n)
{
	this(p.add(n.scale(n.dotProduct(dir)>0?DELTA:-DELTA)),dir);
}

}
