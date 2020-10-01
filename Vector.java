package primitives;

public class Vector
{
 private Point3D _head;
 /**
  * constractor get point3D
  * @param head of vector
  */
public Vector(Point3D head)
{
	if(Point3D.ZERO.equals(head))
		throw new IllegalArgumentException();
	_head=head;
}
/**
 * constractor get 3 coordinates
 * @param x value first
 * @param y value second
 * @param z value third
 */
public Vector(Coordinate x,Coordinate y,Coordinate z)
{
	Point3D p=new Point3D(x,y,z);
	if(Point3D.ZERO.equals(p))
		throw new IllegalArgumentException();
	_head=p;
}
/**
 * constractor get 3 numbers
* @param x value first
 * @param y value second
 * @param z value third
 */
public Vector(double x,double y,double z)
{
	Point3D p=new Point3D(x,y,z);
	if(Point3D.ZERO.equals(p))
		throw new IllegalArgumentException();
	_head=p;
}
/**
 * constractor get vector
 * @param v copy vector
 */
public Vector(final Vector v)
{
	if(Point3D.ZERO.equals(v._head))
		throw new IllegalArgumentException();
	 _head=new Point3D (v._head);
}
/**
 * getter the head of vector
 * @return get head
 */
public Point3D getHead()
{
	return _head;
}
/**
 * equals between 2 vectors
 */
@Override
public boolean equals(Object o)
{
	  if (this == o) return true;
      if (o == null) return false;
if (!(o instanceof Vector)) return false;
Vector n = (Vector)o;
return _head.equals(n._head);

}
/**
 * tostring of vector head
 */
@Override
public String toString()
{
	return "head:"+_head+"\n";
}
/**
 * sub 2 vectors
 * @param v  the second vector
 * @return the subtract
 */
public Vector subtract(Vector v)
{
	return new Vector(_head.subtract(v._head));
}
/**
 * add 2 vectors
 * @param v the second vector
 * @return the add
 */
public Vector add(Vector v)
{
	return new Vector(v._head.add(new Vector(_head.getX(),_head.getY(),_head.getZ())));
}
/**
 * mult vector and number
 * @param b the scale to mult
 * @return the mult
 */
public Vector scale(double b)
{
	return new Vector(_head.getX().get()*b,_head.getY().get()*b,_head.getZ().get()*b);
}
/**
 * dot product
 * @param v the second vector
 * @return the dot product
 */
public double dotProduct (Vector v)
{ 
	double x1=_head.getX().get();
	double	y1=_head.getY().get();
	double z1=_head.getZ().get();
	double x2=	v._head.getX().get();
	double	y2=v._head.getY().get();
	double z2=v._head.getZ().get();
	return x1*x2+y1*y2+z1*z2;
}
/**
 * cross product
 * @param v the second vector
 * @return the cross product
 */
public Vector crossProduct(Vector v)
{
	double x1=	_head.getX().get();
double	y1=_head.getY().get();
double z1=_head.getZ().get();
double x2=	v._head.getX().get();
double	y2=v._head.getY().get();
double z2=v._head.getZ().get();
return new Vector(y1*z2-z1*y2,-x1*z2+z1*x2,x1*y2-x2*y1);
}
/**
 * doing dotprodact of the vector with himself
 * @return  length of vector squared
 */
public double lengthSquared()
{
	return this.dotProduct(this);
}
/**
 * send to func lengthSquared and doing sqrt of this
 * @return length of vector

 */
public double length()
{
	return Math.sqrt(this.lengthSquared());
}
/**
 * normalize of vector and change him
 * @return this new vector
 */
public Vector normalize()
{
	_head=new Point3D( _head.getX().get()/length(),_head.getY().get()/length(),_head.getZ().get()/length());
   return this;
}
/**
 * copy this vector and send him to func normalize
 * @return normalize of vector
 */
public Vector normalized()
{
	Vector v=new Vector(this);
	return v.normalize();
}

}
