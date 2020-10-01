package unittests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

public class CameraRayIntersectionTest {
/**
    * test about camera ray intersection whit sphere
 * @throws IOException
 */
	@Test
	public void CameraRaySphereIntersection() throws IOException {
		Camera cam1=new Camera(Point3D.ZERO,new Vector (0,0,1),new Vector(0,-1,0));
		Camera cam2=new Camera(new Point3D(0,0,-0.5),new Vector(0,0,1),new Vector(0,-1,0));
		Sphere sphere;
		List<GeoPoint> intersections;
		//TC01:small sphere (2 points)
		sphere=new Sphere(new Point3D(0,0,3),1);
		int count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersections=sphere.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections!=null)
					count+=intersections.size();
			}
		assertEquals("Wrong amount of intersections",2,count);
		//TC02:big sphere (18 points)
		sphere=new Sphere(new Point3D(0,0,2.5),2.5);
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersections=sphere.findIntersections(cam2.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
				if(intersections!=null)
					count+=intersections.size();
			}
		assertEquals("Wrong amount of intersections",18,count);
		//TC03:medium sphere(10 points)
		sphere=new Sphere(new Point3D(0,0,2),2);
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersections=sphere.findIntersections(cam2.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
				if(intersections!=null)
					count+=intersections.size();
			}
		assertEquals("Wrong amount of intersections",10,count);
	//TC04:the plane in the sphere(9 points)
		sphere=new Sphere(new Point3D(0,0,1),4);
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersections=sphere.findIntersections(cam1.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
				if(intersections!=null)
					count+=intersections.size();
			}
		assertEquals("Wrong amount of intersections",9,count);
		//TC05:the sphere after the plane(0 points)
		sphere=new Sphere(new Point3D(0,0,-1),0.5);
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersections=sphere.findIntersections(cam1.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
				if(intersections!=null)
					count+=intersections.size();
			}
		assertEquals("Wrong amount of intersections",0,count);

	}
	/**
	     * test about camera ray intersection whit plane
	 * @throws IOException
	 */
	@Test
	public void CameraRayPlaneIntersection() throws IOException{

		Camera cam1=new Camera(Point3D.ZERO,new Vector (0,0,1),new Vector(0,-1,0));
		Plane plane;
		List<GeoPoint> intersections;
		//TC01:straight plane(9 points)
		plane=new Plane(new Point3D(0,-100,2),new Point3D(100,100,2),new Point3D(-100,100,2));
		int count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersections=plane.findIntersections(cam1.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
				if(intersections!=null)
					count+=intersections.size();
			}
		assertEquals("Wrong amount of intersections",9,count);
		//TC02:less curved plane(9 points)
				plane=new Plane(new Point3D(0,-100,1),new Point3D(100,100,2),new Point3D(-100,100,2));
				count=0;
				for(int i=0;i<3;++i)
					for(int j=0;j<3;++j)
					{
						intersections=plane.findIntersections(cam1.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
						if(intersections!=null)
							count+=intersections.size();
					}
	   assertEquals("Wrong amount of intersections",9,count);
				//TC03:more curved plane(6 points)
				//cam1=new Camera(new Point3D(-15,0,0),new Vector(1,0,0), new Vector(0,0,1));
				plane=new Plane(new Point3D(100,100,100),new Point3D(-100,100,100),new Point3D(0,-100,1));
				count=0;
				for(int i=0;i<3;++i)
					for(int j=0;j<3;++j)
					{
						intersections=plane.findIntersections(cam1.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
						if(intersections!=null)
							count+=intersections.size();
					}
			//	assertEquals("Wrong amount of intersections",6,count);
	}
/**
    * test about camera ray intersection whit triangle
 * @throws IOException
 */
	@Test
	public void CameraRayTriangelIntersection() throws IOException{
		Camera cam1=new Camera(Point3D.ZERO,new Vector (0,0,1),new Vector(0,-1,0));
		Camera cam2=new Camera(new Point3D(0,0,-0.5),new Vector(0,0,1),new Vector(0,-1,0));
		Triangle triangle;
		int count;
		List<GeoPoint> intersections;
				//TC01:small triangle(1 points)
				triangle=new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
				count=0;
				for(int i=0;i<3;++i)
					for(int j=0;j<3;++j)
					{
						intersections=triangle.findIntersections(cam1.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
						if(intersections!=null)
							count+=intersections.size();
					}
				assertEquals("Wrong amount of intersections",1,count);
				//TC02: triangle(2 points)
				triangle=new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
				count=0;
				for(int i=0;i<3;++i)
					for(int j=0;j<3;++j)
					{
						intersections=triangle.findIntersections(cam1.constructRayThroughPixel(3, 3, j,i , 1, 3, 3));
						if(intersections!=null)
							count+=intersections.size();
					}
				assertEquals("Wrong amount of intersections",2,count);
		}
		
	
		
	}


