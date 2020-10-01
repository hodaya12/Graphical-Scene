package elements;

import primitives.*;
/**
 * interface LightSource
 * @author hrozenta
 *
 */
public interface LightSource {
	
	/**
	 * intensity getter
	 * @param p point that the function returned the intensity in it
	 * @return the intensity
	 */
	Color getIntensity(Point3D p);
	/**
	 * direction  getter
	 * @param p point that the function returned the direction of light in it
	 * @return the direction
	 */
	Vector getL(Point3D p);
	/**
	 * calculate the distance between the point and the light source
	 * @param point  point that the function returned the distance of light from it
	 * @return the distance between the point and the light source
	 */
	double getDistance(Point3D point);

}
