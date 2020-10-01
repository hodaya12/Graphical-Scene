package elements;

import primitives.Color;
/**
 * class light abstract class
 * @author hrozenta
 *
 */
public abstract class  Light {
protected Color	_intensity;
/**
 * intensity getter
 * @return the intensity
 */
public Color getIntensity() {
return _intensity;}
/**
 * constructor get color
 * @param intensity the intensity of the light
 */
public Light(Color intensity) {
	_intensity=intensity;
}
}
