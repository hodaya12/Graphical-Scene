package elements;
import java.io.IOException;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;

import geometries.*;
import primitives.*;
/**
 * class camera details about camera and functions
 * @author hrozenta
 *
 */
public class Camera {
private static final Random rnd = new Random();
private Point3D _p0;
private Vector _vTo;
private Vector _vUp;
private Vector _vRight;
/**
 * p0 getter
 * @return p0
 */
public Point3D getP0()
{
	return _p0;
}
/**
 * vTo getter
 * @return vTo
 */
public 	Vector getVto()
{
	return _vTo;
}
/**
 * vUp getter
 * @return vUp
 */
public Vector getVup()
{
	return _vUp;
}
/**
 * vRight getter
 * @return vRight
 */
public Vector getVright()
{
	return _vRight;
}
/**
 * Camera constructor accepting p0 and _Vto and _Vup vectors,
 * @param p0 the place of the camera
 * @param _Vto Z axis – view from camera into scene
 * @param _Vup Y axis – screen top to bottom
 * @throws IOException
 */
public Camera(Point3D p0,Vector _Vto,Vector _Vup)
{
	_p0=p0;
	_vTo=_Vto.normalized();
	_vUp=_Vup.normalized();
	_vRight=_Vto.normalized().crossProduct(_Vup.normalized());
}
/**
 * The function construct ray to the pixel
 * @param nX  Number of pixels in X axis 
 * @param nY  Number of pixels in Y axis 
 * @param j   Number of pixel in Y axis
 * @param i   Number of pixel in X axis
 * @param screenDistance The distance of the view plane from the camera 
 * @param screenWidth  The width of the view plane
 * @param screenHeight The height of the view plane
 * @return ray
 */
public Ray constructRayThroughPixel(int nX, int nY,int j, int i, double screenDistance, double screenWidth, double screenHeight)
{
	if (Util.isZero(screenDistance))
	{ throw new IllegalArgumentException("distance cannot be 0");}
	Point3D Pc = _p0.add(_vTo.scale(screenDistance));
	double Ry = screenHeight/nY;
	double Rx = screenWidth/nX;
	double yi =  ((i - nY/2d)*Ry + Ry/2d);
	double xj=   ((j - nX/2d)*Rx + Rx/2d);
	Point3D Pij = Pc;
	if (! Util.isZero(xj))
	{Pij = Pij.add(_vRight.scale(xj));}
	if (! Util.isZero(yi))
	{Pij = Pij.subtract(_vUp.scale(yi)); }// Pij.add(_vUp.scale(-yi))
	Vector Vij = Pij.subtract(_p0);
	return new Ray(_p0,Vij);
}

/**
 * 
 * @param mainRay
 * @param nX Number of pixels in X axis 
 * @param nY Number of pixels in Y axis 
 * @param i Number of pixel in X axis 
 * @param j Number of pixel in Y axis 
 * @param screenDistanceThe distance of the view plane from the camera
 * @param screenWidth The width of the view plane
 * @param screenHeight The height of the view plane
 * @param density the density of the rays
 * @param amount the amount of rays
 * @param radius the radius of the random rays
 * @return Beam of rays from different places in pixel
 */
public List<Ray> constructRayBeamThroughPixel(Ray mainRay, int nX, int nY, int i, int j, double screenDistance, double screenWidth, double screenHeight, double density, int amount,double radius) {
	if (Util.isZero(screenDistance)) {
		throw new IllegalArgumentException("distance cannot be 0");
	}

	List<Ray> rays = new LinkedList<>();

	Point3D Pc = _p0.add(_vTo.scale(screenDistance));

	double Ry = screenHeight / nY;
	double Rx = screenWidth / nX;

	double yi = ((i - nY / 2d) * Ry + Ry / 2d);
	double xj = ((j - nX / 2d) * Rx + Rx / 2d);

	Point3D Pij = Pc;

	if (!Util.isZero(xj)) {
		Pij = Pij.add(_vRight.scale(xj));
	}
	if (!Util.isZero(yi)) {
		Pij = Pij.subtract(_vUp.scale(yi)); // Pij.add(_vUp.scale(-yi))
	}
	double[] randomNumbers = rnd.doubles(amount * 2, radius * (-1), radius).distinct().toArray();
	while(amount * 2>randomNumbers.length)
	{
		double a= rnd.nextDouble()*radius;
		double b= rnd.nextDouble();
		if(b<0.5)
			a=a*-1;
		randomNumbers[randomNumbers.length]=a;
	}
	Point3D rayPoint = mainRay.getP0();
	for(int ii=0; ii <  randomNumbers.length-1; ii++) {
		Point3D pXY = Pij;
		pXY = pXY.add(_vUp.scale(randomNumbers[ii]).add(_vRight.scale(randomNumbers[ii + 1])));
		rays.add(new Ray(rayPoint, pXY.subtract(rayPoint).normalize()));
	}
	return rays;
	}

}