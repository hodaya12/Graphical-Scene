package primitives;

public class Point3D 
{
	 private Coordinate _x;
	 private Coordinate _y;
	 private Coordinate _z;
	 public final static Point3D ZERO = new Point3D(0,0,0);
	 /**
	  * constractor get 3 coordinate
	  * @param x the first value
	  * @param y the second value
	  * @param z third value
	  */
	 public Point3D(Coordinate x,Coordinate y,Coordinate z)
	{
		_x=x;
		_y=y;
		_z=z;
	}
	 /**
	  * constractor get 3 number
	  * @param x first num
	  * @param y second num
	  * @param z third num
	  */
	public Point3D(double x, double y,double z)
	{
		Coordinate x1=new Coordinate(x);
		Coordinate y1=new Coordinate(y);
		Coordinate z1=new Coordinate(z);
		_x=x1;
		_y=y1;
		_z=z1;
	}
	/**
	 * constractor get point3D
	 * @param v other point
	 */
	public Point3D(final Point3D v)
	{
		_x=new Coordinate( v.getX());
		_y=new Coordinate(v.getY());
		_z=new Coordinate( v.getZ());		
	}
	/**
	 * return x
	 * @return coordinate x
	 */
	public Coordinate getX()
	{
		return _x;
	}
	/**
	 * return y
	 * @return coordinate y
	 */
	public Coordinate getY()
	{
		return _y;
	}
	/**
	 * return z
	 * @return coordinate z
	 */
	public Coordinate getZ()
	{
		return _z;
	}
	/**
	 * equals between 2 point
	 */
	@Override
	public boolean equals(Object o)
	 {
	      if (this == o) return true;
	      if (o == null) return false;
	 if (!(o instanceof Point3D)) return false;
	 Point3D n = (Point3D)o;
	 return _x.equals(n._x) &&
	 _y.equals(n._y)&&_z.equals(n._z);
	 }
/**
 * tostring all coordinate
 */
	@Override
	public String toString()
	{
		return "x:"+_x+" y:"+_y+" z:"+_z+"\n";
	}
	/**
	 * do sub point from point
	 * @param p second
	 * @return the substract
	 */
	public Vector subtract(Point3D p)
	{
		Vector v=new Vector(_x.get()-p.getX().get(),_y.get()-p.getY().get(),_z.get()-p.getZ().get());
		return v;
	}
	/**
	 * add point to point
	 * @param p second vector
	 * @return add all coordinate to other coordinate
	 */
	public Point3D add(Vector v)
	{
			return new Point3D(_x.get()+v.getHead().getX().get(),_y.get()+v.getHead().getY().get(),_z.get()+v.getHead().getZ().get());
	}
	/**
	 * return distance between 2 point
	 * @param p
	 * @return
	 */
	public double distance(Point3D p)
	{
		return Math.sqrt(distanceSquared(p));
	}
	/**
	 * return distance in squared between 2 point
	 * @param p
	 * @return
	 */
	public double distanceSquared(Point3D p)
	{
		return Math.pow(_x.get()-p.getX().get(),2)+Math.pow(_y.get()-p.getY().get(),2)+Math.pow(_z.get()-p.getZ().get(),2);
	}
	public Point3D subtract(Vector v) {
		
		return new Point3D(_x.get()-v.getHead().getX().get(),_y.get()-v.getHead().getY().get(),_z.get()-v.getHead().getZ().get());
	}
}
