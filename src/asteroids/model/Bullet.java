package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

/**
 * Created by stef on 2-4-17.
 * @author Joachim & Stef
 */
public class Bullet extends Entity{

    /**
     * Initialize a bullet with x-position, y-position, velocity in x-direction, velocity in y-direction, radius.
     *
     * @param positionX The x-coordinate of this new bullet.
     * @param positionY The y-coordinate of this new bullet.
     * @param velocityX The velocity in the x-direction of this new bullet.
     * @param velocityY The velocity in the y-direction of this new bullet.
     * @param radius    The radius of this new bullet.
     */

    public Bullet(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        super(positionX, positionY, velocityX, velocityY, radius);
    }

    //TODO
    public void setShip(Ship ship){

    }

    //TODO
    public Ship getShip(){

    }

    /**
     *
     * @return
     */
    public double getMass(){
        double radius = getRadius(); //getting getRadius from superclass Entity
        return(3/4 * Math.PI * Math.pow(radius, 3) * density);
    }

    /**
     * Constant holding the minimum radius of a bullet.
     */
    public static double minRadius = 1;

    @Basic
    public double getMinRadius(){
        return minRadius;
    }

    /**
     * Constant holding the density of a bullet following the given formula.
     * 7.8 * 10^12 kg/m^3
     */
    private double density = 7.8 * Math.pow(10, 12);

    //TODO
    public void terminate(){
        status = true;
    }

    //TODO
    public boolean isTerminated(){
        return status;
    }

    //TODO 
    private boolean status = false;


}
