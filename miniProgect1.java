package unittests;

import java.io.IOException;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class miniProgect1 {
@Test
/**
 * create picture for mini project 1 
 * @throws IOException
 */
	public void pictureMiniProject1() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
	scene.setDistance(1000);
	scene.setBackground(new Color(20,180,62));
	scene.setAmbientLight(new AmbientLight(new Color(255,255,255), 0.05));
	scene.addGeometries(
			
			//מקל שמאלי
			new Triangle(new Color(0,0,0),new Material(3,0,10,0,12),new Point3D(-214.82,-4.97,0),new Point3D(-187.1,-0.8,0),new Point3D(-161.15, -112.18,0)),
			new Polygon(new Color(251,246,181),new Material(1,0,7,0,0),new Point3D(-151.29,-155.3,0),new Point3D(-125.69,-154.89,0),new Point3D(-187.1,-0.8,0),new Point3D(-214.82,-4.97,0)),
			new Polygon(new Color(0,0,0),new Material(3,0,10,0,12),new Point3D(-187.1,-0.8,0),new Point3D(-214.82,-4.97,0),new Point3D(-247.12, 69.49,0),new Point3D(-240,155,0)),
			//מקל ימני
			new Triangle(new Color(0,0,0),new Material(3,0,10,0,12),new Point3D(-103.39,-109.8,0),new Point3D(-132.29,-2.16,0),new Point3D(-107.07,-2.08,0)),
			new Polygon(new Color(251,246,181),new Material(1,0,7,0,0),new Point3D(-107.07,-2.08,0),new Point3D(-132.29,-2.16,0),new Point3D(-103.39,-162.87,0),new Point3D(-78.69,-163.92,0)),
			new Polygon(new Color(0,0,0),new Material(3,0,10,0,12),new Point3D(-132.29,-2.16,0),new Point3D(-107.07,-2.08,0),new Point3D(-122.83,158.71,0),new Point3D(-165.39,159.76,0))
			
			,new Sphere(new Color(23,33,239),new Material(1,10,25,0,0),new Point3D(-57.9725, 92.23375,0),39), new Sphere(new Color(23,33,239),new Material(1,10,25,0,0),new Point3D(15.14483, 71.25226,30),39),
			new Sphere(new Color(116,26,236),new Material(1,10,25,0,0),new Point3D(83.81, 49.63,60),39), new Sphere(new Color(240,32,22),new Material(1,10,25,0,0),new Point3D(148.66343, 39.46211,90),40.39),
			new Sphere(new Color(18,244,61),new Material(1,10,25,0,0),new Point3D(211.60792,24.83865,120),39), new Sphere(new Color(240,32,22),new Material(1,10,25,0,0),new Point3D(-53.52188, 35.01149,30),39),
			new Sphere(new Color(244,244,18),new Material(1,10,25,0,0),new Point3D(15.78063, 21.02383,60),39), new Sphere(new Color(244,244,18),new Material(1,10,25,0,0),new Point3D(82.53993, 5.12876,90),39),
			new Sphere(new Color(160,43,219),new Material(1,10,25,0,0),new Point3D(139.12639, -12.03792,120),39), new Sphere(new Color(236,138,40),new Material(1,10,25,0,0),new Point3D(-49.07126, -10.13051,60),39),
			new Sphere(new Color(72,70,60),new Material(1,10,25,0,0),new Point3D(15.78063, -29.8404,90),39), new Sphere(new Color(18,244,61),new Material(1,10,25,0,0),new Point3D(77.45351, -39.37745,120),39),
			new Sphere(new Color(236,138,40),new Material(1,10,25,0,0),new Point3D(-43.34904, -50.8219,90),39), new Sphere(new Color(116,26,236),new Material(1,10,25,0,0),new Point3D(16.41643, -62.26635,120),39),
			new Sphere(new Color(160,43,219),new Material(1,10,25,0,0),new Point3D(-38.89842, -84.51945,120),39),new Sphere(new Color(229,228,223),new Material(1,10,25,0,0.1),new Point3D(207, -120,310),39)
			
			//,new Plane(new Color(20,180,62),new Material(0,0,20,0,0), new Point3D(0,0,700),new Vector(0,0,1))
			,new Polygon(new Color(20,180,62),new Material(0.5, 0.5, 60),new Point3D(-1000, 1000,350),new Point3D(1000, 1000,350),new Point3D(1000, -1000,350),new Point3D(-1000, -1000,350))
			);
		

	scene.addLights(
			new SpotLight(new Color(231, 231, 45), 
					new Point3D(150.92316, -108.04416,72), new Vector(-8.7868, 115.12265,0), 1, 1E-5, 1.5E-7)
		
			);

	ImageWriter imageWriter = new ImageWriter("billardTable", 480, 300 ,960, 600);
	Render render = new Render(imageWriter, scene,1.5,49);

	render.renderImage();
	render.writeToImage();
	}
}
