package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

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

    @Basic
    public double getPositionX(){
        return this.positionX;
    }

    private double positionX;

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
    //TODO
    private void setPositionY(double positionY){

    }

    @Basic
    public double getVelocityX(){
        return this.velocityX;
    }

    private double velocityX;

    @Basic
    public double getVelocityY(){
        return this.velocityY;
    }

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



}

