package unittests;

import java.awt.Color;

//import static org.junit.Assert.*;

import org.junit.Test;
import renderer.*;
public class ImageWriterTest {
/**
 * create grid
 */
	@Test
	public void test() {
		
		ImageWriter image= new ImageWriter("grid",1000,1600,500,800);
		 int Nx = image.getNx();
	        int Ny = image.getNy();
	        for (int i = 0; i < Ny; i++) {
	            for (int j = 0; j < Nx; j++) {
	                if (i % 50 == 0 || j % 50 == 0) {
	                    image.writePixel(j, i,new primitives.Color(  Color.PINK));
	                } else {
	                    image.writePixel(j, i, new primitives.Color( Color.BLUE));
	                }
		//image.writeToImage();
	            
	}}}
	
}
