package asteroids.model;
import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;
import java.lang.Math.*;


/**
 * A class of ships involving a position, a velocity, an angle and a radius
 * 
 * @author joachim & stef
 * @version pre-alpha
 * 
 * 
 */

public class Ship {

    /**
     *
     *
     * @param x
     * @param y
     * @param velocityX
     * @param velocityY
     * @param angle
     * @param radius
     */
    public Ship(double x, double y,double velocityX, double velocityY, double angle, double radius){
    }

    ///Region regarding Position///
    /**
     * 
     * @return positionX
     */
    public double getPositionX(){
        return this.positionX;
    }
    
    private double positionX;
	
    /**
     * 
     * @return positionY
     */
    public double getPositionY(){

        return this.PositionY;
    }

    private double PositionY;

    ///End Region regarding Position///

    ///Region regarding Velocity///

    /**
     *  Initialize the new velocity with the given velocity in direction x and the given velocity in direction y.
     * @param velocityX
     * @param velocityY
     * @param maximumVelocity
     *
     * @post 
     */
    public Velocity(double velocityX, double velocityY, double maximumVelocity){

    }

    /**
     *
     */
    private void isValidVelocity(velocity){
        if ((this.velocity >= 0) && (this.velocity < SPEED_OF_LIGHT_AS_DEFINED_BY_THE_ASSIGNMENT));
                return true;
    }

    /**
     * constant containing speed of light
     */
    public static double SPEED_OF_LIGHT_AS_DEFINED_BY_THE_ASSIGNMENT = 300000;

    /**
     *
     * @return  the velocity in direction X.
     */
    @Basic
    public double getVelocityX(){
        return this.velocityX;
    }

    private double velocityX;

    /**
     *
     * @return  the velocity in direction Y.
     */
    @Basic
    public double getVelocityY(){
        return this.velocityY;
    }

    private double velocityY;


    /**
     * Return the final velocity of this ship based on the velocity in direction X and the velocity in direction Y.
     *
     * @param velocityX
     *          | The velocity in direction X.
     * @param velocityY
     *          | The velocity in direction Y.
     *
     * @return  The total velocity of the ship in the direction x y.
     *
     *
     */
    public double calcVelocity(double velocityX, double velocityY){
        ///this.velocity = 0.0;
        this.velocity= Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
        return this.velocity;
    }

    public double velocity;
    ///End Region regarding Velocity///

    ///Region regarding Orientation///

    /**
     *  Returns the orientation of this Ship.
     *
     *  The orientation expresses a direction the Ship is facing in, as an angle in radians.
     */
    @Basic
    public double getOrientation(){
        return this.angle;
    }

    private double angle;

    /**
     * Expresses the minimum value the orientation of this Ship has to be.
     */
    private double minAngle = Math.toRadians(0);

    /**
     * Expresses the maximum value the orientation of this Ship can be.
     */
    private double maxAngle = Math.toRadians(360);

    /**
     *
     */
    public void setOrientation(){

    }
    ///End Region regarding Orientation///

    ///Region regarding Radius///

    /**
     * Expressing the minimum value of the radius of this ship.
     * May change in the future.
     */
    public double minRadius = 10;

    ///End Region regarding Radius///

    /**
     *
     * @param velocity
     * @param angle
     */
    public double thrust (double velocity, double angle){
        double thrust;
    }

}
