package renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import elements.Camera;
import elements.LightSource;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;
/**
 * class render all the details about render and functions. 
 */
public class Render {
  private ImageWriter _imageWriter;
  private Scene _scene;
  private static final int MAX_CALC_COLOR_LEVEL = 10;
  private static final double MIN_CALC_COLOR_K = 0.001;
  private double _supersamplingRate;
  private int _rayCounter;
  private int _threads = 1;
  private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
  private boolean _print = false; // printing progress percentage
  private int _adaptiveSuperSamplingLevel; //adaptive super sampling recursion level

  /**
     /**
	   * constructor get 2 parameter
	   * @param imageWriter producing a non-optimized jpeg image
	   * @param scene details about the scene of the render
	   */
 public Render(ImageWriter imageWriter, Scene scene) {
	this(imageWriter,scene,0,0,0);
}

 /**
  * constructor get imageWriter, scene, adaptive Super Sampling Level
  * @param imageWriter producing a non-optimized jpeg image
  * @param scene details about the scene of the render
  * @param adaptiveSuperSamplingLevel adaptive Super Sampling recursion Level
  */
public Render(ImageWriter imageWriter, Scene scene,int adaptiveSuperSamplingLevel) {
		this(imageWriter,scene,0,0,adaptiveSuperSamplingLevel);
	}

/**
  * constructor get imageWriter,  scene supersamplingRate, ray counter
  * @param imageWriter producing a non-optimized jpeg image
  * @param scene details about the scene of the render
  * @param supersamplingRate density of the rays
  * @param RayCounter tne number of rays
  */
  public Render(ImageWriter imageWriter, Scene scene,double supersamplingRate,int RayCounter) {
	  this(imageWriter,scene,supersamplingRate,RayCounter,0);
		}
/**
 * construtor get all parameter of render 
*@param imageWriter producing a non-optimized jpeg image
  * @param scene details about the scene of the render
  * @param supersamplingRate density of the rays
  * @param RayCounter tne number of rays
 * @param adaptiveSuperSamplingLevel adaptive Super Sampling recursion Level
 */
 public Render(ImageWriter imageWriter, Scene scene,double supersamplingRate,int RayCounter,int adaptiveSuperSamplingLevel) {
	  _imageWriter=imageWriter;
	_scene=scene;
	if(supersamplingRate>1)
		_supersamplingRate=1;
	else { 
		if(_supersamplingRate<0)
			  supersamplingRate=0;
		else {
		_supersamplingRate=supersamplingRate;
		}
	}
	if(RayCounter<0)
		_rayCounter=0;
	else {
		_rayCounter=RayCounter;
		} 
	_adaptiveSuperSamplingLevel=adaptiveSuperSamplingLevel;
  }
 /**
  * create a new ray from p in the same direction with shift of delta.
  * @param n normal
  * @param p point the returned ray start in it.
  * @param ray the ray that refract
  * @return the refracted ray
  */
  public Ray constructRefractedRay(Vector n,Point3D p,Ray v) {
	  return new Ray(p,v.getDir(),n);
  }
  /**
   * create a new ray from p that in the same angle to the normal.
   * @param n normal
   * @param p point the returned ray start in it.
   * @param ray the ray that reflect
   * @return the reflected ray
   */
public Ray constructReflectedRay(Vector n,Point3D p, Ray ray) {
	Vector v= ray.getDir();
	double vn=v.dotProduct(n);
	if(vn==0) {
		return null;
	}
	Vector r=v .subtract(n.scale(2*vn));
		return new Ray(p,r,n);
}   
/**
 * the function find the all the intersections points of the ray and send it to closestpoint. 
 * @param ray that the function is finding intersection to it.
 * @return the closest point from all the intersections
 * @throws IOException
 */
private GeoPoint findClosestIntersection(Ray ray)  {
	if(_scene.getGeometries().findIntersections(ray)==null)
		return null;
	List<GeoPoint> l=new ArrayList<GeoPoint>(_scene.getGeometries().findIntersections(ray));
	 return closestPoint(l,ray);
}
/**
 * if supersamplingrate is 0 so send the main ray and else send beam of rays. for each pixel the function calcuate the closest point
 *  that intersect it and call calccolor to calculate the color and call imagewriter
 */
  public void renderImage()  {
	  final int nX = _imageWriter.getNx();
		final int nY = _imageWriter.getNy();
		final double dist = _scene.getDistance();
		final double width = _imageWriter.getWidth();
		final double height = _imageWriter.getHeight();
		final Camera camera = _scene.getCamera();
		final Pixel thePixel = new Pixel(nY, nX);
		Intersectable geometries = _scene.getGeometries();
		java.awt.Color background = _scene.getBackground().getColor();
		// Generate threads
		Thread[] threads = new Thread[_threads];
    	  for (int i = _threads - 1; i >= 0; --i) {
    		  threads[i] = new Thread(() -> {
    				Pixel pixel = new Pixel();
    				Color resultingColor;
    				while (thePixel.nextPixel(pixel)) {
    					if(_supersamplingRate==0&& _adaptiveSuperSamplingLevel == 0)//without supersampling
    						resultingColor =  getPixelRayColorWithoutSupersampling(camera, geometries, background, dist, nX, nY, width, height, pixel.row, pixel.col);
    					else {
	                        if (_adaptiveSuperSamplingLevel == 0)// with supersampling
	                            resultingColor = getPixelRayColorSuperSampling(camera, geometries, background, dist, nX, nY, width, height, pixel.row, pixel.col);
	                        else { // with adaptive supersampling
	                        	double Ry = height / nY;
	                    		double Rx = width / nX;
	                    	
	                    		double yi = (( pixel.row- nY / 2d) * Ry /2+Ry/2d);
	                    		double xj = ((pixel.col - nX / 2d) * Rx/2+Rx/2d );
	                    		Point3D Pij =camera.getP0().add(camera.getVto().scale(_scene.getDistance()));
	                    	
	                    		if (!Util.isZero(xj)) {
	                    			Pij = Pij.add(camera.getVright().scale(xj));
	                    		}
	                    		if (!Util.isZero(yi)) {
	                    			Pij = Pij.subtract(camera.getVup().scale(yi)); 
	                    		}
	                    		
	                            resultingColor = getPixelRayColorAdaptiveSuperSampling(camera, background,Pij,dist, Rx,Ry,_adaptiveSuperSamplingLevel);
	                        }
	                    }
    					 _imageWriter.writePixel(pixel.col, pixel.row, resultingColor);
    				}}); }
	// Start threads
		for (Thread thread : threads) thread.start();

		// Wait for all threads to finish
		for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
		if (_print) System.out.printf("\r100%%\n");
      }
      
  private Color getPixelRayColorAdaptiveSuperSampling(Camera camera, java.awt.Color background, Point3D pc,double dist,double Rx,double Ry,int level) {
	  		List<Color> pixelColor=new LinkedList<Color>();
		GeoPoint closestPoint;
		List<Ray> rays=new LinkedList<Ray>();
		Point3D pij=pc;
		pij.add(camera.getVright().scale(Rx/2));
		pij.add(camera.getVup().scale(Ry/2));
		rays.add( new Ray(camera.getP0(),pij.subtract(camera.getP0()).normalize()));
		
		pij=pc;
		pij.add(camera.getVright().scale(-Rx/2d));
		pij.add(camera.getVup().scale(Ry/2d));
		rays.add( new Ray(camera.getP0(),pij.subtract(camera.getP0()).normalize()));
		
		pij=pc;
		pij.add(camera.getVright().scale(Rx/2d));
		pij.add(camera.getVup().scale(-Ry/2d));
		rays.add( new Ray(camera.getP0(),pij.subtract(camera.getP0()).normalize()));
		
		pij=pc;
		pij.add(camera.getVright().scale(-Rx/2d));
		pij.add(camera.getVup().scale(-Ry/2d));
		rays.add( new Ray(camera.getP0(),pij.subtract(camera.getP0()).normalize()));
			
for(Ray ray:rays) {
	 closestPoint = findClosestIntersection(ray);
	  if (closestPoint == null) 
		  pixelColor.add( new Color(background));
	  else
		  pixelColor.add(calcColor(closestPoint,ray));
}
if(pixelColor.get(0)==pixelColor.get(1)&&pixelColor.get(2)==pixelColor.get(0)&&pixelColor.get(0)==pixelColor.get(0))
	return pixelColor.get(0);
if(0==level)
	return (pixelColor.get(0).add(pixelColor.get(1)).add(pixelColor.get(2)).add(pixelColor.get(3))).reduce(4);
Point3D pijleftup=pc;
pijleftup.add(camera.getVright().scale(-Rx/4d));
pijleftup.add(camera.getVup().scale(Ry/4d));

Point3D pijleftdown=pc;
pijleftdown.add(camera.getVright().scale(-Rx/4d));
pijleftdown.add(camera.getVup().scale(-Ry/4d));

Point3D pijrightdown=pc;
pijrightdown.add(camera.getVright().scale(Rx/4d));
pijrightdown.add(camera.getVup().scale(-Ry/4d));

Point3D pijrightup=pc;
pijrightup.add(camera.getVright().scale(Rx/4d));
pijrightup.add(camera.getVup().scale(Ry/4d));

return (getPixelRayColorAdaptiveSuperSampling(camera,background,pijleftup ,dist,Rx/2d, Ry/2d, level-1) 
.add(getPixelRayColorAdaptiveSuperSampling(camera,background,pijleftdown ,dist,Rx/2d, Ry/2d, level-1))
.add(getPixelRayColorAdaptiveSuperSampling(camera,background,pijrightup ,dist,Rx/2d, Ry/2d, level-1))
.add(getPixelRayColorAdaptiveSuperSampling(camera,background,pijrightdown ,dist, Rx/2d, Ry/2d, level-1))).reduce(4);
}
 
private Color getPixelRayColorSuperSampling(Camera camera, Intersectable geometries, java.awt.Color background,double dist, int nX, int nY, double width, double height, int row, int col) {
	Ray mainray = camera.constructRayThroughPixel(nX, nY, col, row, dist, width, height);
	  double radius = Math.min(_imageWriter.getWidth() / _imageWriter.getNx() , _imageWriter.getHeight() / _imageWriter.getNy()) *_supersamplingRate;
    List<Ray> rays = camera.constructRayBeamThroughPixel(mainray,nX, nY, col, row, dist, width, height,_supersamplingRate, _rayCounter,radius);
    GeoPoint closestPoint;
    Color avarage;
   closestPoint = findClosestIntersection(mainray);
   if (closestPoint == null) {
 	  avarage=new Color(background);
       } else {
     	 avarage= (calcColor(closestPoint,mainray));
   avarage=avarage.scale(3);
       }
   for (Ray ray:rays) {
  	 closestPoint = findClosestIntersection(ray);
    if (closestPoint == null) {
  	  avarage=avarage.add(new Color(background));
        } else {
      	 avarage= avarage.add((calcColor(closestPoint,ray)));
        }
    }
   avarage=avarage.reduce(rays.size()+2);
   return avarage;
}

private Color getPixelRayColorWithoutSupersampling(Camera camera, Intersectable geometries, java.awt.Color background, double dist, int nX,int nY, double width, double height, int row, int col)  {
	Color pixelColor=new Color(Color.BLACK);
	  Ray ray = camera.constructRayThroughPixel(nX, nY, col,row, dist, width, height);
	  GeoPoint closestPoint = findClosestIntersection(ray);
	  if (closestPoint == null) 
		  pixelColor= new Color(background);
	  else
	  pixelColor = calcColor(closestPoint,ray);
	 return pixelColor;
}
/**
 * get interval and color and after interval writepixel in that color
 * @param interval the size of the gap of the grid
 * @param color the color of the grid
 */  
  public void printGrid(int interval, java.awt.Color color)
  {

	double width=this._imageWriter.getWidth();
	double height=this._imageWriter.getHeight();
	for(int row=0;row<height;row++)
		for(int col=interval;col<width;col+=interval) {
			_imageWriter.writePixel(col, row,new primitives.Color(color));
			_imageWriter.writePixel(row, col,new primitives.Color(color));
		}
  }
  /**
   * the function calculate the color in the point considering emission light,shininess,all the lights in the scene, reflection and refraction.
   * @param geopoint the point that the function calculates the color in it
   * @param inRay the ray from the camera to the geopoint
   * @param level the max deep of the recursion
   * @param k stopping condition for the recursion
   * @return the color in the point
   * @throws IOException
   */
  private Color calcColor(GeoPoint geopoint,Ray inRay, int level, double k)  {

	  if(level==1||k<MIN_CALC_COLOR_K)
		  return Color.BLACK;
	  Color result = geopoint.getGeometry().getEmmission();
	  Vector v = geopoint.getPoint().subtract(_scene.getCamera().getP0()).normalize();
	  Vector n = geopoint.getGeometry().getNormal(geopoint.getPoint()).normalize();
	  int nShininess = geopoint.getGeometry().getMaterial().getNShininess();
	  double kd = geopoint.getGeometry().getMaterial().getKD();
	  double ks = geopoint.getGeometry().getMaterial().getKS();
	  double kr = geopoint.getGeometry().getMaterial().getKR();
	  double kt = geopoint.getGeometry().getMaterial().getKT();
	  double kkr=k*kr;
	  double kkt=k*kt;
	  result=getLightSouresColores(geopoint, result,v, n, nShininess, kd, k,ks);
	  if (kkr > MIN_CALC_COLOR_K) {
		  Ray reflectedRay = constructReflectedRay(n, geopoint.getPoint(), inRay);
		  GeoPoint reflectedPoint =findClosestIntersection(reflectedRay);
		  if (reflectedPoint != null)
			  result = result.add(calcColor(reflectedPoint, reflectedRay,level -1, kkr).scale(kr));
	 } 
	  if (kkt > MIN_CALC_COLOR_K) {
		  Ray refractedRay = constructRefractedRay(n,geopoint.getPoint(), inRay) ;
		  GeoPoint refractedPoint =findClosestIntersection(refractedRay);
		  if (refractedPoint != null)
		  result =result.add(calcColor(refractedPoint, refractedRay,level- 1, kkt).scale(kt));
	  }
	  
	  return result;  
  }
  /**
   * the function calculate the impact of all the light sources on the geopoint
   * @param geopoint the point that the function calculates the impact of the light sources in it
   * @param result the final color in this geo point that calculated in calccolor
   * @param v vector from the camera to the geopoint 
   * @param n normal to the geo point
   * @param nShininess shininess
   * @param kd diffuse factor of the geopoint
   * @param k  stopping condition for the recursion
   * @param ks specular factor of the geopoint
   * @return the color that light sources give to the point
   */
private Color getLightSouresColores(GeoPoint geopoint,Color result, Vector v, Vector n, int nShininess, double kd, double k,double ks) {
	for (LightSource lightSource : _scene.getLights()) {
	  Vector l = lightSource.getL(geopoint.getPoint());
	  if (n.dotProduct(l)*n.dotProduct(v) > 0) {
		 double ktr = transparency(lightSource, l, n, geopoint);
	  if (ktr * k > MIN_CALC_COLOR_K) 
	  {
	  Color lightIntensity = lightSource.getIntensity(geopoint.getPoint()).scale(ktr);
	  result = result.add(calcDiffusive(kd, Util.alignZero(n.dotProduct(l)), lightIntensity),
			  calcSpecular(ks, l, n, Util.alignZero(n.dotProduct(l)), v, nShininess, lightIntensity));
	  }
	  }//lightSource.getIntensity(geopoint.getPoint())
	  }
	return result;
}
/**
 * wrapping function to the calccolr
 * @param geopoint the point that the function calculates the color in it
 * @param ray the ray from the camera to the geopoint
 * @return the color in the point
 * @throws IOException
 */
  private Color calcColor(GeoPoint gp, Ray ray) {	
	  return calcColor(gp,ray,MAX_CALC_COLOR_LEVEL,1.0).add(_scene.getAmbientLight().getIntensity()) ;
  }
  /**
   * calculate the impact of the specular factor
   * @param ks  specular factor
   * @param l the direction of the light
   * @param n the normal
   * @param nl n.dotProduct(l)
   * @param v vector from the camera to the geopoint
   * @param nShininess shininess
   * @param ip light intensity
   * @return the color that the specular factor adds to the point
   */  
private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
	  l.normalize(); 
	  n.normalize();
	  v.normalize();
	  double p = nShininess;
      Vector R = l.add(n.scale(-2 * nl)).normalized(); // nl must not be zero!
      double minusVR = -Util.alignZero(R.dotProduct(v));
      if (minusVR <= 0) {
          return Color.BLACK; // view from direction opposite to r vector
      }
      return ip.scale(ks * Math.pow(minusVR, p));
}
/**
 * calculate the impact of the diffuse factor
 * @param kd diffuse factor
 * @param nl  n.dotProduct(l)
 * @param ip light intensity
 * @return the color that the diffuse factor adds to the point
 */
private Color calcDiffusive(double kd, double nl, Color ip) {
	if (nl < 0) nl = -nl;
    return ip.scale(nl * kd);
}
/**
 * return true if nl is positive and else false
 * @param nl the number that the function check its sign
 * @return
 */
private Boolean sign(double nl) {
	if(nl>0)
		return true;
	return false;
}
/**
 * the function calculate the closest point that intersect the ray.
 * @param points list of points
 * @param ray ray that the function return the closest point that intersect it. 
 * @return the closest point that intersect the ray
 * @throws IOException
 */
public GeoPoint closestPoint(List<GeoPoint> points,Ray ray) {
	  GeoPoint result=null;
	  double mindistance=Double.MAX_VALUE;
	  Point3D p0=ray.getP0();
	  for(GeoPoint p:points)
	  {
		  if(p0.distance(p.getPoint())<mindistance) {
			  result=p;
			  mindistance=p0.distance(p.getPoint());
		  }
	  }
	  return result;
  }
/**
 * the function call the function writeToImage of imageWriter
 */
public void writeToImage()
  {
	_imageWriter.writeToImage();  
  }
  private boolean unshaded(LightSource light,Vector l, Vector n, GeoPoint geopoint)
  {
	  Vector lightDirection = l.scale(-1); // from point to light source
	  Ray lightRay = new Ray(geopoint.getPoint(), lightDirection,n);
	  List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay,light.getDistance(geopoint.getPoint()));
	  if (intersections ==null) 
		  return true;
	  double lightDistance = light.getDistance(geopoint.getPoint());
	  for (GeoPoint gp : intersections) {
	  if (Util.alignZero(gp.getPoint().distance(geopoint.getPoint()) - lightDistance) <= 0)
		  return false;  
	  if(geopoint.getGeometry().getMaterial().getKT()==0)
		  return true;

	  }
	  return true;
  }
  /**
   * transparency
   * @param ls
   * @param l
   * @param n
   * @param geopoint
   * @return
   */
  private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geopoint)
  {
		  Vector lightDirection = l.scale(-1); // from point to light source
		  Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
		  List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
		  if (intersections ==null) 
			  return 1.0;
		  double lightDistance = ls.getDistance(geopoint.getPoint());
		  double ktr = 1.0;
		  for (GeoPoint gp : intersections) {
			  if (Util.alignZero(gp.getPoint().distance(geopoint.getPoint()) - lightDistance) <= 0) {
				  ktr *= gp.getGeometry().getMaterial().getKT();
			if (ktr < MIN_CALC_COLOR_K) 
			  return 0.0;
		  }
		  }
		  return ktr;
  }
  
  /**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}

	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		_print = true;
		return this;
	}

  
	/**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}

	
	
}

  
