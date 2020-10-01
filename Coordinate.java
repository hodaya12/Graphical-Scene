package primitives;

import static primitives.Util.*;
 
 

/**
 * Class Coordinate is the basic class representing a coordinate for Cartesian
 * coordinate system. The class is based on Util controlling the accuracy.
 * 
 * @author Dan Zilberstein
 */
public final class Coordinate {
    /**
     * Coordinate value, intentionally "package-friendly" due to performance
     * constraints
     */
    final double _coord;

    /**
     * Coordinate constructor receiving a coordinate value
     * 
     * @param coord coordinate value
     */
    public Coordinate(double coord) {
        // if it too close to zero make it zero
        _coord = alignZero(coord);
    }

    /**
     * Copy constructor for coordinate
     * 
     * @param other
     */
    public Coordinate(Coordinate other) {
        _coord = other._coord;
    }

    /**
     * Coordinate value getter
     * 
     * @return coordinate value
     */
    public double get() {
        return _coord;
    }

    /**
     * check if o and the object are equal
     * @param o object that is checked if it's equal to the other object
     * @return true if the two object are equal and else return false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
        return isZero(_coord - ((Coordinate)obj)._coord);
    }
    /**
     * return a string that describe the coordinate
     * @return string
     */
    @Override
    public String toString() {
        return "" + _coord;
    }
    /**
     * substract other from this object
     * @param other coordinate that is substracted from the object
     * @return the result coordinate
     */
    public Coordinate subtract(Coordinate other) {
    	return new Coordinate(_coord-other._coord);
    }
    /**
     * add other to this object
     * @param other coordinate that is added to the object
     * @return the result coordinate
     */
    public Coordinate add(Coordinate other) {
    	return new Coordinate(_coord+other._coord);
    }
}