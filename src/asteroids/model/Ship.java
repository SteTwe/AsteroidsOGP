package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import sun.util.resources.cldr.dua.CalendarData_dua_CM;

import java.awt.*;
import java.util.DoubleSummaryStatistics;
import java.util.logging.Level;


/**
 * A class of a ship involving a position, a velocity, an angle and a radius
 * 
 * @author Joachim & Stef
 * @version pre-alpha
 *
 * @invar       The radius of a ship must be higher than or equal to the minimum radius for all ships.
 *              | isValidRadius(getRadius))
 */

public class Ship {

    /**
     * Initialize a ship with x-position, y-position, velocity in x-direction, velocity in y-direction, angle, radius.
     *
     * @param positionX The x-coordinate of this new ship.
     * @param positionY The y-coordinate of this new ship.
     * @param velocityX The velocity in the x-direction of this new ship.
     * @param velocityY The velocity in the y-direction of this new ship.
     * @param angle     The angle (orientation) which this new ship is facing in.
     * @param radius    The radius of this new ship.
     * The radius of this new shipof this new ship.
     */
    public Ship(double positionX, double positionY, double velocityX, double velocityY, double angle, double radius) throws IllegalArgumentException {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setVelocityX(velocityX);
        this.setVelocityY(velocityY);
        this.setAngle(angle);
        //if (!isValidRadius(radius)) throw new IllegalArgumentException();
        this.radius = radius;
    }

    public Ship() throws IllegalArgumentException {
        this.setPositionX(0);
        this.setPositionY(0);
        this.setVelocityX(0);
        this.setVelocityY(0);
        this.setAngle(0);
        if (!isValidRadius(10)) throw new IllegalArgumentException();
        this.radius = 10;
    }

    /**
     * Return the x-coordinate of this ship.
     * @return
     *              the x-coordinate of this ship
     *              | this.positionX
     */
    @Basic
    public double getPositionX(){
        return this.positionX;
    }

    /**
     * Variable holding the x-coordinate of this ship.
     */
    private double positionX;

    /**
     * Return the y-coordinate of this ship.
     * @return
     *              the y-coordinate of this ship
     *              | this.positionY
     */
    @Basic
    public double getPositionY(){
        return this.positionY;
    }

    /**
     * Variable holding the y-coordiante of this ship.
     */
    private double positionY;

    /**
     * Set the x-coordinate of this ship with the given x-coordinate.
     * @param positionX
     *              The given position for this ship.
     */
    public void setPositionX(double positionX){
        if(isValidPositionX(positionX))
            this.positionX = positionX;
    }

    /**
     * Return true if x-coordinate is valid for this ship.
     * @param positionX
     *              The given position for this ship.
     * @return
     *              true if the position is a number
     *              | !Double.isNaN(positionX)
     */
    private boolean isValidPositionX(double positionX){
        return (!Double.isNaN(positionX));
    }

    /**
     * Set the y-coordinate of this ship to the given y-coordinate.
      * @param positionY
     *              The given y position for this ship.
     */
    public void setPositionY(double positionY){
        if(isValidPositionY(positionY))
            this.positionY = positionY;
    }

    /**
     * Return true if y-coordinate is valid for this ship.
     * @param positionY
     *              The given y position for this ship.
     * @return
     *              true if the position is a number
     *              | !Double.isNaN(positionY)
     */
    private boolean isValidPositionY(double positionY){
        return(!Double.isNaN(positionY));
    }

    /**
     * Return the velocity in x-direction of this ship.
     * @return
     *              the velocity in the x-direction of this ship
     *              | this.velocityX
     */
    public double getVelocityX(){
        return this.velocityX;
    }

    /**
     * Variable holding the velocity in the x-direction.
     */
    private double velocityX;

    /**
     * Return the velocity in x-direction of this ship.
     * @return
     *              the velocity in the x-direction of this ship
     *              | this.velocityY
     */
    public double getVelocityY(){
        return this.velocityY;
    }

    /**
     * Variable holding the velocity in the y-direction.
     */
    private double velocityY;

    private void setVelocityX(double velocityX) {
        if (isValidVelocityX(velocityX))
            this.velocityX = velocityX;
    }

    private void setVelocityY(double velocityY){
        if (isValidVelocityY(velocityY))
            this.velocityY = velocityY;
    }

    private boolean isValidVelocityX(double velocityX){
        return (!Double.isNaN(velocityX));
    }

    private boolean isValidVelocityY(double velocityY){
        return (!Double.isNaN(velocityY));
    }

    /**
     * Method computing the total velocity of this ship following the given formula.
     * @param velocityX
     *              The ship's velocity in the x-direction.
     * @param velocityY
     *              The ship's velocity in the y-direction.
     */
    private double computedVelocity(double velocityX, double velocityY){
        return (Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)));
    }

    /**
     * Checker if the total velocity is valid for this ship.
     * @param velocity
     *              The total velocity for this ship.
     * @return
     *              True if the velocity is valid for this ship meaning: a numbers and smaller than or
     *              equal to the speed of light.
     *              | ((!Double.isNaN(velocity) && (velocity <= SPEED_OF_LIGHT))
     *              |       return true
     */
    private boolean isValidVelocity(double velocity){
        return((!Double.isNaN(velocity))&&(velocity <= SPEED_OF_LIGHT));
    }

    /**
     * Constant holding the maximum speed.
     */
    private static double maxVelocity;

    /**
     * Constant holding the.
     */
    private static double SPEED_OF_LIGHT = 300000;

    /**
     * Return the angle (orientation) of this ship.
     * @return
     *              The angle of this ship.
     *              | return this.angle
     */
    public double getAngle(){
        return this.angle;
    }

    /**
     * Variable holding the angle of the ship.
     */
    private double angle;

    /**
     * Variable holding the minimum angle this ship must have, being zero; converted to radians.
     */
    //private double minAngle = Math.toRadians(0);

    /**
     * Variable holding the maximum angle this ship can have, being 360°;converted to radians.
     */
    private static double maxAngle = 2*Math.PI;

    /**
     * Set the new angle for this ship.
     * @param angle
     *              The given angle to be added to the current angle of this ship.
     * @pre         The given angle must be a valid angle for this ship
     */
    private void setAngle(double angle){
        assert isValidAngle(angle);
        if ((this.getAngle() + angle) < maxAngle) {
            this.angle = getAngle() + angle;
        } else if ((this.getAngle() + angle) > maxAngle){
            this.angle = ((getAngle() + angle) % maxAngle);
        }
    }

    private boolean isValidAngle(double angle){
        return (!Double.isNaN(angle));
    }

    /**
     * Return the radius of this ship.
     * @return
     *              Return the radius for this ship.
     *              | return this.radius
     */
    public double getRadius(){
        return this.radius;
    }

    /**
     * Variable holding the radius for this ship
     */
    private double radius;

    /**
     * Set a new radius for this ship.
     * @param radius
     *              The new radius for this ship.
     * @pre         The new radius must be valid.
     *              if (isValidRadius)
     * @post        If the new radius is valid for this ship, the ship's radius is set to the new radius.
     *              | this.radius = radius
     */
    public void setRadius(double radius){
        if (isValidRadius(radius))
            this.radius = radius;
        else
            this.radius = 10;
    }

    /**
     * Return if the given radius is a valid radius for this ship.
     * @param radius
     *              The given radius.
     * @pre         The radius should be larger than the minimum radius (which is 10km)
     *
     * @return      True if the given angle is larger than the minimum radius and must be a number.
     *              | return ((radius > minRadius) && (Double.isNaN(radius)))
     */
    private boolean isValidRadius(double radius){
        return ((radius > minRadius) && (Double.isNaN(radius)));
    }

    /**
     * Constant holding the minimum radius of a ship. Currently the ship's radius can't be changed during runtime.
     */
    private static final double minRadius = 10;

    /**
     * Move the ship for a certain amount of time (duration).
     * @param duration
     */
     protected void move(double duration) throws IllegalArgumentException{
        if(!isValidDuration(duration))
            throw new IllegalArgumentException("duration not valid");

        setPositionX(getPositionX() + duration * getVelocityX());
        setPositionY(getVelocityY() + duration * getVelocityY());
    }

    private boolean isValidDuration(double duration){
        return duration >= 0;
    }
}
