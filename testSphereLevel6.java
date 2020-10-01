package unittests;

import java.io.IOException;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
/**
 *  create picture of sphere with all light
 * @author ריעות
 *
 */
public class testSphereLevel6 {
@Test
public void sphereAllLight() throws IOException {
    Scene scene = new Scene("Test scene");
    scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
    scene.setDistance(1000);
    scene.setBackground(new Color(java.awt.Color.WHITE));
    scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

    scene.addGeometries(
            new Sphere(new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 500),  new Point3D(0, 0, 50),50));

    scene.addLights(new DirectionalLight(new Color(600, 300, 0), new Vector(1, -1, 1)),
    		new PointLight(new Point3D(0,0, -50), 1, 0.00001, 0.000001,new Color(214, 214, 16)),
    		new SpotLight(new Color(22, 239, 17),new Point3D( 281,-209,-476),new Vector(-27,3,92),1.0, 0.00001, 0.00000001));

    ImageWriter imageWriter = new ImageWriter("sphereAllLight", 150, 150, 500, 500);
    Render render = new Render(imageWriter, scene);

    render.renderImage();
    render.writeToImage();
}}