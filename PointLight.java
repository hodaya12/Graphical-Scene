package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;
/**
 * class PointLight has only position 
 * @author hrozenta
 *
 */
public class PointLight extends Light implements LightSource{
	/**
	 *  constructor get parameter
	 * @param position the position of the light
	 * @param kC discount factor
	 * @param kL discount factor
	 * @param kQ discount factor
	 * @param c color of the light
	 */
	public PointLight(Point3D position, double kC,double kL,double kQ,Color c) {
		super(c);
		_position=position;
		_kC= kC;_kL= kL;_kQ= kQ;
	}
	protected Point3D _position;
	protected double _kC,_kL,_kQ;
	/**
	 * intensity getter
	 * @param p point that the function returned the intensity in it
	 * @return the intensity
	 */
	public Color getIntensity(Point3D p) {
		double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
	}
	/**
	 * direction getter
	 * @param p point that the function returned the direction in it
	 * @return the direction
	 */
	public Vector getL(Point3D p) {
		if(p.equals(_position))
			return null;
		return p.subtract(_position).normalize();
	}
	/**
	 * calculate the distance between the point and the light source
	 * @param point  point that the function returned the distance of light from it
	 * @return the distance between the point and the light source
	 */
	@Override
	public double getDistance(Point3D point) {

		return Util.alignZero(_position.distance(point));
	}

}
