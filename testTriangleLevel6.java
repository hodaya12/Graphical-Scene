package unittests;

import java.io.IOException;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class testTriangleLevel6 {

/**
 *  create picture of triangle with all light
 * @throws IOException
 */
	 @Test
	    public void trianglesAllLight() throws IOException {
	        Scene scene = new Scene("Test scene");
	        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
	        scene.setDistance(1000);
	        scene.setBackground(Color.BLACK);
	        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

	        scene.addGeometries(
	                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
	                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
	                new Triangle(new Color(java.awt.Color.GRAY), new Material(0.8, 0.2, 300),
	                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

	        scene.addLights(new DirectionalLight(new Color(48, 0, 154), new Vector(0, 0, 1)),
	        		new PointLight(new Point3D(10, 10, 130),1, 0.0005, 0.0005,new Color(30, 102, 124)),
	        		new SpotLight(new Color(108, 65, 46), new Point3D(30, -10, 130),new Vector(-2, 2, 1),1.0, 0.0001, 0.000005));

	        ImageWriter imageWriter = new ImageWriter("trianglesAllLight", 200, 200, 500, 500);
	        Render render = new Render(imageWriter, scene,1.5,49);

	        render.renderImage();
	        render.writeToImage();
	    }
}