package elements;


import primitives.*;

/**
 * class SpotLight has position and direction
 * @author hrozenta
 *
 */
public class SpotLight extends PointLight{
	/**
	 *  constructor get parameter
	 * @param direction the direction of the light 
	 * @param _position the position of the light 
	 * @param _kC discount factor
	 * @param _kL discount factor
	 * @param _kQ discount factor
	 * @param c color of the light
	 */
	public SpotLight(Color c,Point3D _position,Vector direction, double _kC,double _kL,double _kQ) {
		super(_position, _kC, _kL, _kQ, c);
		_direction=direction.normalized();
	}
private Vector _direction;
/**
 * intensity getter
 * @param p point that the function returned the intensity in it
 * @return the intensity
 */
public Color getIntensity(Point3D p) {
    double projection = _direction.dotProduct(getL(p));

    if (Util.isZero(projection)) {
        return Color.BLACK;
    }
    
    double factor = Math.max(0, projection);
    Color pointlightIntensity = super.getIntensity(p);

    return (pointlightIntensity.scale(factor));
}
}
