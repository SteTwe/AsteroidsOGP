package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
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

    //TODO
    private boolean isValidPositionX(double positionX){

    }

    //TODO
    private void setPositionX(double positionX){

    }

    private boolean isValidPositionY(double positionY){

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
        return this.velocityX;
    }



}

