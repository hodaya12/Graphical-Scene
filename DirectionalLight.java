package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * class DirectionalLight has only direction 
 * @author hrozenta
 *
 */
public class DirectionalLight extends Light implements LightSource{
	/**
	 * constructor get parameter
	 * @param intensity the intensity of the light
	 * @param direction the direction of the light
	 */
	public DirectionalLight(Color intensity,Vector direction) {
		super(intensity);
		_direction=direction;
		
	}
	private Vector _direction;
	
	/**
	 * intensity getter
	 * @param p point that the function returned the intensity in it
	 * @return the intensity
	 */
	public Color getIntensity(Point3D p) {
		return super.getIntensity();
	}
	/**
	 * direction getter
	 * @param p point that the function returned the direction in it
	 * @return
	 */
	public Vector getL(Point3D p) {
		return _direction;
	}
	/**
	 * calculate the distance between the point and the light source
	 * @param point  point that the function returned the distance of light from it
	 * @return the distance between the point and the light source
	 */
	@Override
	public double getDistance(Point3D point) {
		return Double.POSITIVE_INFINITY;
	}


}
