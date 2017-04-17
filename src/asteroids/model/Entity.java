package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.DoubleSummaryStatistics;

/**
 * A class representing an Asteroid entity involving a position, a velocity, and a radius.
 * Created by joachim on 09/04/2017.
 * @author joachim
 */
public abstract class Entity {

    /**
     * Initialize a new entity with x-position, y-position, velocity in x-direction, velocity in y-direction, radius.
     *
     * @param positionX The x-coordinate of this new ship.
     * @param positionY The y-coordinate of this new ship.
     * @param velocityX The velocity in the x-direction of this new ship.
     * @param velocityY The velocity in the y-direction of this new ship.
     * @param radius    The radius of this new ship.
     */
    public Entity(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setVelocityX(velocityX);
        this.setVelocityY(velocityY);
        if (!isValidRadius(radius)) throw new IllegalArgumentException();
        this.radius = radius;
    }

    /**
     * Returning the x-coordiante of this entity's position.
     * @return  Returns the x-coordinate of this entity.
     *          | result == this.positionX
     */
    @Basic
    public double getPositionX(){
        return this.positionX;
    }

    public double positionX;

    /**
     * Returning the y-coordinate of this entity's position
     * @return  Returns the y-coordinate of this entity.
     *          | result == this.positionY
     */
    @Basic
    public double getPositionY(){
        return this.positionY;
    }

    public double positionY;

    /**
     * Return true if the x-coordinate is valid for this entity.
     *
     * @param positionX The given position for this ship.
     * @return true if the position is a number
     *          | result == !Double.isNaN(positionX)
     */
    private boolean isValidPositionX(double positionX){
        return (!Double.isNaN(positionX));
    }

    //TODO
    public void setPositionX(double positionX){
        if (isValidPositionX(positionX))
            this.positionX = positionX;
    }

    /**
     * Return true if the y-coordinate is valid for this entity.
     *
     * @param positionY The given position for this entity.
     * @return true if the position is a number
     *          | result == !Double.isNaN(positionY)
     */
    private boolean isValidPositionY(double positionY){
        return (!Double.isNaN(positionY));
    }

    /**
     * Set the y-coordinate of this entity to the given y-coordiante
     *
     * @param positionY The given y-position of this entity.
     * @pre The given positionY should be a valid positionY for this entity.
     *      | isValidPositionY(positionY)
     * @post The y-coordinate of this entity is equal to the given positionY.
     *      | new.getPositionY() == positionY;
     */
    public void setPositionY(double positionY){
        if (isValidPositionY(positionY))
            this.positionY = positionY;
    }

    /**
     * Returning this entity's velocity in the x-direction.
     * @return Returns the velocity of this entity in the x-direction.
     *          | result == this.velocityX
     */
    @Basic
    public double getVelocityX(){
        return this.velocityX;
    }

    /**
     * Variable containting the entity's velocity in the x-direction.
     */
    public double velocityX;

    /**
     * Returning this entity's velocity in the y-direction.
     * @return Returns the velocity of this entity in the y-direction.
     *          | result == this.velocityY
     */
    @Basic
    public double getVelocityY(){
        return this.velocityY;
    }
    /**
     * Variable containting the entity's velocity in the y-direction.
     */
    public double velocityY;

    /**
     * Set the velocity in the x-direction of this entity with the given velocityX.
     *
     * @param velocityX The given velocityX for this entity.
     * @pre The given velocityX should be a valid velocityX for this entity.
     *      | isValidVelocityX(velocityX)
     * @post The velocityX of this entity is equal to the given velocityX.
     *      | new.getVelocityX() == velocityX.
     */
    public void setVelocityX(double velocityX){
        if (isValidVelocityX(velocityX))
            this.velocityX = velocityX;
    }

    /**
     * Set the velocity in the y-direction of this entity with the given velocityY.
     *
     * @param velocityY The given velocityY for this entity.
     * @pre The given velocityY should be a valid velocityY for this entity.
     *      | isValidVelocityY(velocityY)
     * @post The velocityY of this entity is equal to the given velocityY.
     *      | new.getVelocity() == velocityY.
     */
    public void setVelocityY(double velocityY){
        if (isValidVelocityY(velocityY))
            this.velocityY = velocityY;
    }

    /**
     * Return true if the velocityX is valid for this entity.
     * @param velocityX The given velocityX for this entity.
     * @return true if the velocityX is a number
     *          | result == !Double.isNaN(velocityX)
     */
    private boolean isValidVelocityX(double velocityX){
        return(!Double.isNaN(velocityX));
    }

    /**
     * Return true if the velocityY is valid for this entity.
     * @param velocityY The given velocityY for this entity.
     * @return true if the velocityY is a number
     *          | result == !Double.isNaN(velocityY)
     */
    private boolean isValidVelocityY(double velocityY){
        return(!Double.isNaN(velocityY));
    }


    /**
     * Method computing the total velocity of this entity following the given formula.
     *
     * @param velocityX The entity's velocity in the x-direction.
     * @param velocityY The entity's velocity in the y-direction.
     * @post Gives the total velocity for this entity.
     * | return (Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)))
     */
     public double computeVelocity(double velocityX, double velocityY) {
        return (Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)));
    }

    public double getMaxVelocity(){
         return SPEED_OF_LIGHT;
    }

    private static double SPEED_OF_LIGHT = 300000;

    /**
     * Return the radius of this entity.
     *
     * @return Return the radius for this entity.
     * | result == radius
     */
    @Basic
    @Raw
    public double getRadius(){
        return radius;
    }

    private double radius;

    /**
     * Constant holding the minimum radius of an entity.
     */
    public double minRadius;

    /**
     * Return if the given radius is a valid radius for this entity.
     *
     * @param radius The given radius.
     * @return True if the given angle is larger than the minimum radius and must be a number.
     *          | return ((radius > minRadius) && (Double.isNaN(radius)))
     * @pre The radius should be larger than the minimum radius
     */
    public boolean isValidRadius(double radius){
        return ((radius > minRadius) && (Double.isNaN(radius)));
    }

    /**
     *
     * @param radius
     */
    public void setRadius(double radius){
        if (isValidRadius(radius))
            this.radius = radius;
        else
            this.radius = minRadius;
    }

    public abstract double getMass();


    /**
     * Move the entity for a certain amount of time (duration).
     *
     * @param duration  The amount of time to move.
     * @throws IllegalArgumentException The duration is les than zero.
     * @post The new position of this entity
     */
    public void move(double duration){
        if (!isValidDuration(duration))
            throw new IllegalArgumentException("duration not valid");
        setPositionX(getPositionX() + duration * getVelocityX());
        setPositionY(getPositionY() + duration * getVelocityY());
    }

    private boolean isValidDuration(double duration) {
        return duration >= 0;
    }


    private World world;

    public World getWorld(){
        return world;
    }

    //TODO
    public void setWorld(World world){

    }

    /**
     * Remove the world this entity is part of.
     * world is equal to null
     */
    public void removeWorld(){
        this.world = null;
    }

    /**
     * Terminate this entity.
     */
    public void terminate(){
        status = true;
    }

    /**
     * Return true if this entity's status (if it's already terminated or not)
     * @return
     */
    public boolean isTerminated(){
        return status;
    }

    /**
     * Boolean holding the standard value of this entity's status (not terminated => false)
     */
    private boolean status = false;


    /**
     * Method returning the time until a collision happens between this entity and a boundary.
     * @return
     */
    public double getTimeToCollisionWithBoudary(){

    }

    /**
     * Method returning a pair of coordinates that represent the location of collision between an entity and a boundary.
     * @return
     */
    public double[] getCollisionPositionWithBoundary() {

    }

    /**
     * Method executing the change in velocity in order to resolve a collision with a boundary.
     *
     */
    public void collideWithBoundary(){
        //if boundary is horizontal => negate y-velocity
        //if boundary is vertical => negate x-velocity

        // if ((getCollisionPositionWithBoundary(positionX) == 0) || (getCollisionPositionWithBoundary(positionX) == getWorld().getHeight())
        // negate y-velocity
        // if ((getCollisionPositionWithBoundary(positionY) == 0) || (getCollisionPositionWithBoundary(positionY) == getWorld().getWidth())
        // negate x-velocity

    }

    /**
     * method is implemented in Ship and Bullet
     */
    public abstract void collide(Entity entity);

}

