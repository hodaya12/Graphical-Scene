package unittests;

import java.io.IOException;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
/**
 * @author ריעות
 */
public class ourTestLevel7{
	/**
	 * picture level 7
	 * @throws IOException
	 */
	@Test
	public void test3Geometry() throws IOException {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
	scene.setDistance(1000);
	scene.setBackground(Color.BLACK);
	scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
	

	scene.addGeometries(new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 1, 30,0.3,0.6), 
			new Point3D(0, 0, 200), 60), 
			new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 1, 30,0.3,0.9), 
					 new Point3D(-40, 70, 0),new Point3D(-70, 40, 0), new Point3D(-68, 68, 4)),
			new Triangle(new Color (java.awt.Color.BLUE),new Material(0.5, 1, 30,0.3,1),
					new Point3D(70,-40,0), new Point3D(40,-70,0), new Point3D(68,-68,4)),
			new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30,0.8, 0),  
					new Point3D(60, 50, 50), 20));

	scene.addLights(new SpotLight(new Color(400, 240, 0), 
			new Point3D(-85, 85, -140) , new Vector(1, -1, 3), 1, 1E-5, 1.5E-7),
			new SpotLight(new Color(400, 240, 0), 
					new Point3D(85,-85,-140) , new Vector(-1,1,3), 1, 1E-5, 1.5E-7));

	ImageWriter imageWriter = new ImageWriter("our7", 200, 200, 400, 400);
	Render render = new Render(imageWriter, scene,1.5,49);
	render.renderImage();
	render.writeToImage();
	}
	 }