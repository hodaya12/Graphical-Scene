package elements;
//import renderer.*;
import primitives.*;
public class AmbientLight extends Light {
   
   /**
    * constructor get color and KA
    * @param intensity
    * @param kA
    */
   public AmbientLight(Color intensity,double kA)
   {
	  super(intensity.scale(kA));
	 
   }
   


}
