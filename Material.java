package primitives;
/**
 * Material class features of the material of the geometry 
 * @author hrozenta
 *
 */
public class Material {
private double _kD,_kS,_kT,_kR;
int _nShininess;

/**
 * KD getter
 * @return the diffuse of the material
 */
public double getKD() {
	return _kD;
}
/**
 * kS getter
 * @return the specular of the material
 */
public double getKS() {
	return _kS;
}
/**
 * nShininess getter
 * @return the shininess of the material
 */
public int getNShininess() {
	return _nShininess;
}
/**
 * KT getter
 * @return   the transparency of the material
 */
public double getKT() {
	return _kT;
}
/**
 * KR getter
 * @return   the reflection of the material
 */
public double getKR() {
	return _kR;
}
/**
 * constructor get 5 parameters
 * @param kD diffuse of the material
 * @param kS specular of the material
 * @param nShininess shininess of the material
 */
public Material(double kD,double kS,int nShininess,double kT, double kR)
{
	_kD=kD;
	_kS=kS;
	_nShininess=nShininess;
	_kT=kT;
	_kR=kR;
}
/**
 * constructor get 3 parameters
 * @param kD diffuse of the material
 * @param kS specular of the material
 * @param nShininess shininess of the material
 */
public Material(double kD,double kS,int nShininess)
{
	this(kD,kS,nShininess,0,0);
}


}