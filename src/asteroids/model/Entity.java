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

    private double positionX;

    /**
     * Returning the y-coordinate of this entity's position
     * @return  Returns the y-coordinate of this entity.
     *          | result == this.positionY
     */
    @Basic
    public double getPositionY(){
        return this.positionY;
    }

    private double positionY;

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
    private void setPositionX(double positionX){
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
    private void setPositionY(double positionY){
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
    private double velocityX;

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
    private double velocityY;

    private void setVelocityX(double velocityX){
        if (isValidVelocityX(velocityX))
            this.velocityX = velocityX;
    }

    private void setVelocityY(double velocityY){
        if (isValidVelocityY(velocityY))
            this.velocityY = velocityY;
    }

    private boolean isValidVelocityX(double velocityX){
        return(!Double.isNaN(velocityX));
    }

    private boolean isValidVelocityY(double velocityY){
        return(!Double.isNaN(velocityY));
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

    private World world;

    public World getWorld(){
        return world;
    }

    //TODO
    public void setWorld(World world){

    }

    //TODO
    public void removeWorld(){

    }

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

    public double getTimeToCollisionWithBoudary(){

    }



}

