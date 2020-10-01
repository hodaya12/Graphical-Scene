package scene;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import elements.*;
import geometries.*;
import primitives.Color;
import primitives.Point3D;

public class Scene {
	private String _name;
	private Color _background;
	private AmbientLight _ambientLight;
	private Geometries _geometries;
	private Camera _camera;
	private double _distance;
	private List<LightSource> _lights=new LinkedList<LightSource>();
    /**
     * return the list of light
     * @return light
     */
	public List<LightSource> getLights(){
    	return _lights;
    }
	/**
	 * add lights to list
	 * @param lights
	 */
	public void addLights(LightSource... lights) 
	{for(LightSource l: lights)
		   _lights.add(l);}
	/**
	 * CONSATRACTOR get name
	 * @param name of the scene
	 */
	public Scene(String name) {
    	_name=name;
    	_geometries=new Geometries();
    }
	/**
	 * getter the name of the scene
	 * @return the name
	 */
    public String getName() {
    	return _name;
    }
    /**
     * getter the background 
     * @return the background
     */
	public Color getBackground() {
		return _background;
	}
	/**
	 * getter the ambient light
	 * @return the ambient light
	 */
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}
	/**
	 * getter geometries
	 * @return the geometries
	 */
	public Geometries getGeometries() {
		return new Geometries( _geometries);
	}
	/**
	 * create new camera whit p0, vto, vup
	 * @return camera
	 */
	public Camera getCamera()  {
		return new Camera( _camera.getP0(),_camera.getVto(),_camera.getVup());
    }
	/**
	 * getter the distance
	 * @return
	 */
	public double getDistance() {
		return _distance;
	}
	/**
	 * setting the backgroud
	 * @param background new background
	 */
	public void setBackground(Color background ) {
		_background=background;
	}
	/**
	 * setting the ambient light
	 * @param AmbientLight new ambient light
	 */
	public void setAmbientLight(AmbientLight AmbientLight)
	{
		_ambientLight=AmbientLight;
	}
	/**
	 * setting the camera
	 * @param Camera new camera
	 */
	public void setCamera(Camera Camera)
	{
		_camera=Camera;
	}
	/**
	 * setting the distance
	 * @param Distance new distance
	 */
	public void setDistance(double Distance)
	{
		_distance=Distance;
	}
	/**
	 * add geometries 
	 * @param geometries also geometries
	 */
	public void addGeometries(Intersectable... geometries) {
		for(Intersectable i: geometries)
		   _geometries.add(i);
	}

}
